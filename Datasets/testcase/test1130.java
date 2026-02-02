import java.util.ArrayList;
import java.util.List;

// Annotation for recursion method pos: the attribute values of annotations
@interface RecursionAnnotation {
    String value() default "";
}

// Super interface for source_class implements feature
interface SourceInterface {
    default void defaultMethod() {}
}

// Parent class for recursion method_feature: parent_class
class ParentClass {
    protected void recursiveParentMethod(int count) throws Exception {
        if (count <= 0) {
            return;
        }
        super.toString(); // super.methodName()
        recursiveParentMethod(count - 1); // recursion
    }
}

// Source abstract class (abstract modifier, same package, implements, two static nested classes)
abstract class SourceClass extends ParentClass implements SourceInterface {
    // per_condition: source contains the field of the target
    private final AbstractTargetClass targetField = new ConcreteTargetClass();

    // First static nested class (source_class feature)
    static class StaticNestedClass1 {}

    // Second static nested class (source_class feature)
    static class StaticNestedClass2 {}

    // Method to be refactored (instance, List<String> return, final access, position: source)
    // method types parameter is:none
    public final List<String> targetMethod() throws Exception { // requires_throws
        List<String> result = new ArrayList<>();
        
        // Variable call (target class access)
        String targetValue = targetField.getValue();
        result.add(targetValue);

        // Recursion method call in annotation attribute values (pos: the attribute values of annotations)
        @RecursionAnnotation(value = String.valueOf(recursiveMethod(1))) // 1, parent_class, recursion
        class AnnotatedClass {}

        // Break statement
        outerLoop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1) {
                    break outerLoop; // break statement
                }
                result.add(targetValue + "_loop_" + i + "_" + j);
            }
        }

        // Override violation (attempt to override final method)
        @Override // Compile error: Cannot override final method
        public final String toString() {
            return targetValue;
        }

        // Call method in if/else conditions (pos: if/else conditions)
        if (result.size() > 0) {
            SubClass.callMethod(targetField); // sub_class, private, static, obj.m1().m2().m3()
        } else {
            SubClass.callMethod(targetField);
        }

        return result;
    }

    // Recursion method (public modifier, 1, parent_class, recursion, super.methodName(), return void)
    public void recursiveMethod(int count) throws Exception {
        super.recursiveParentMethod(count); // super.methodName()
        if (count < 3) {
            recursiveMethod(count + 1); // recursion
        }
    }

    // Final method to trigger override violation
    public final String toString() {
        return "SourceClass";
    }

    // Sub class for call_method (sub_class type, private modifier)
    private static class SubClass {
        // Call method (static, obj.m1().m2().m3(), return void)
        public static void callMethod(AbstractTargetClass target) {
            target.staticNestedMethod().m1().m2().m3(); // obj.m1().m2().m3()
        }
    }

    // Abstract method (required for abstract source class)
    public abstract void abstractSourceMethod();
}

/**
 * Target abstract class (default modifier, javadoc, static nested class target_feature)
 * Javadoc feature compliance
 */
abstract class AbstractTargetClass {
    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        public StaticNestedTargetClass m1() { return this; }
        public StaticNestedTargetClass m2() { return this; }
        public void m3() {}
    }

    private String value = "targetValue_5806";

    // Static nested method for obj.m1().m2().m3()
    public StaticNestedTargetClass staticNestedMethod() {
        return new StaticNestedTargetClass();
    }

    // Variable call: getter
    public String getValue() {
        return value;
    }

    // Abstract method (required for abstract target class)
    public abstract void abstractTargetMethod();
}

// Concrete implementation of target abstract class (for compilation)
class ConcreteTargetClass extends AbstractTargetClass {
    @Override
    public void abstractTargetMethod() {}
}