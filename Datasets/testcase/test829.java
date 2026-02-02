package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Super class for super constructor invocation
class SuperAbstractTarget {
    protected int superField = 3;
    public SuperAbstractTarget(int value) {
        this.superField = value;
    }
}

// Target abstract class: default modifier, static nested class (target_feature), same package as source
abstract class TargetClass extends SuperAbstractTarget {
    public static String staticField = "targetStaticField";
    
    // Static nested class (target_inner - target class of method)
    public static class TargetInner {
        public int innerField = 2;
        
        public TargetInner() {}
        
        public TargetInner(int value) {
            this.innerField = value;
        }
    }

    public TargetClass() {
        super(3); // Super constructor invocation
    }

    // Overload method for overload_exist feature
    public TargetInner createInner(int value) {
        return new TargetInner(value);
    }

    public TargetInner createInner() {
        return new TargetInner();
    }
}

// Subclass for call_method (sub_class type)
public class TargetSubClass extends TargetClass {
    @Override
    public TargetInner createInner() {
        return new TargetInner(2);
    }
}

// Annotation for exp:Annotation feature
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnotation {
    int value() default 2;
}

// Source abstract class: abstract modifier, empty features, same package as target
abstract class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass.TargetInner targetField = new TargetClass.TargetInner(3);

    // Inner recursive class (source_inner_rec - method_position)
    class SourceInnerRecursive {
        // BreakStatement feature: private modifier, ClassName.field, 3, pos=diff_package_others
        private void breakFeature() {
            int count = 0;
            loop:
            while (true) {
                count++;
                // ClassName.field + 3
                if (count == TargetClass.superField) {
                    break loop; // BreakStatement
                }
            }
        }

        // Instance method: private access, TargetClass.TargetInner return type (TargetClas Type)
        @MethodAnnotation(2) // numbers=2, public modifier, exp=Annotation
        private TargetClass.TargetInner refactorMethod() {
            // Empty statement
            ;

            // Super constructor invocation
            TargetClass targetInstance = new TargetClass() {};

            // Variable call
            int varCall = targetField.innerField;

            // Overload_exist (call overloaded method)
            TargetClass.TargetInner overloaded1 = targetInstance.createInner();
            TargetClass.TargetInner overloaded2 = targetInstance.createInner(2);

            // With_bounds
            int bound = 5;
            for (int i = 0; i < bound; i++) { // Bound check
                if (i >= bound - 1) break;
            }

            // call_method: sub_class type, public modifier, constructor + lambda, pos=do-while, return_type=Object
            do {
                TargetSubClass subClass = new TargetSubClass(); // constructor feature
                // (parameters) -> methodBody (lambda)
                java.util.function.Function<Integer, Object> lambda = (param) -> subClass.createInner(param);
                Object result = lambda.apply(2);
            } while (varCall < 3);

            // No new exception thrown
            return targetField;
        }
    }
}