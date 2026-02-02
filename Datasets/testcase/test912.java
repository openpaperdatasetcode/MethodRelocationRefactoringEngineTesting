package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Consumer;

// Annotation for constructor feature pos (attribute values of annotations)
@Retention(RetentionPolicy.RUNTIME)
@interface ConstructorAnnotation {
    String value() default "obj.m1().m2().m3()";
}

// Interface for TargetClass implements feature
interface TargetProcessable {
    String process(String input);
}

// Target inner record (target_inner_rec - target class of method)
public record TargetInnerRec(String id, int value) {
    // Chain methods for obj.m1().m2().m3() feature
    public TargetInnerRec m1() { return this; }
    public TargetInnerRec m2() { return this; }
    public TargetInnerRec m3() { return this; }
}

// Target class: default modifier, implements interface, anonymous inner class (target_feature)
class TargetClass implements TargetProcessable {
    public int thisField = 2; // this.field + 2 for ForStatement

    // Anonymous inner class (target_feature)
    private final TargetProcessable anonymousProcessor = new TargetProcessable() {
        @Override
        public String process(String input) {
            return input + "_processed_" + thisField;
        }
    };

    // Instance method for access_instance_method feature
    public TargetInnerRec createInnerRec() {
        return new TargetInnerRec("REC_001", thisField);
    }

    @Override
    public String process(String input) {
        return anonymousProcessor.process(input);
    }
}

// Super class for super constructor invocation and super keywords
class SourceSuperClass {
    protected void superMethod() {
        System.out.println("Super method executed");
    }
}

// Source class: private modifier, anonymous inner + member inner class (source_feature)
private class SourceClass extends SourceSuperClass {
    // Anonymous inner class (source_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // uses_outer_this feature
            System.out.println("Outer this: " + SourceClass.this.toString());
        }
    };

    // Member inner class (source_feature)
    public class SourceMemberInner {
        public void helperMethod(TargetClass target) {
            System.out.println("Member inner: " + target.thisField);
        }
    }

    // Constructor feature: private modifier, 1, target, constructor, obj.m1().m2().m3(), pos=annotation attributes, return Object
    @ConstructorAnnotation("obj.m1().m2().m3()") // pos: annotation attribute values
    private Object targetConstructorFeature(TargetClass target) {
        // obj.m1().m2().m3() + constructor feature (1 for method_feature)
        TargetInnerRec innerRec = target.createInnerRec().m1().m2().m3();
        return new TargetInnerRec(innerRec.id(), 1); // method_feature: 1
    }

    // ForStatement feature: public modifier, this.field, 2, pos=source
    public void forStatementFeature(TargetClass target) {
        for (int i = 0; i < target.thisField; i++) { // this.field + 2
            System.out.println("For loop iteration: " + i);
        }
    }

    // call_method: source type, final modifier, instance + super.methodName(), pos=Lambda expressions, return void
    private final void sourceCallMethod(TargetClass target) {
        // pos=Lambda expressions
        Consumer<TargetClass> lambda = (t) -> {
            super.superMethod(); // super.methodName() feature
            new SourceMemberInner().helperMethod(t); // instance feature
        };
        lambda.accept(target);
    }

    // Instance method: protected access, Object return type, target parameter (per_condition)
    @ConstructorAnnotation // has_annotation feature
    protected Object refactorMethod(TargetClass targetParam) {
        // Super constructor invocation (method feature)
        super();

        // Super keywords feature
        super.superMethod();

        // Variable call feature
        int varCall = targetParam.thisField;

        // Access instance method feature
        TargetInnerRec innerRec = targetParam.createInnerRec();

        // ForStatement feature execution
        forStatementFeature(targetParam);

        // Synchronized statement feature
        synchronized (this) {
            anonymousInner.run();
        }

        // Constructor feature execution
        Object constructorResult = targetConstructorFeature(targetParam);

        // call_method execution
        sourceCallMethod(targetParam);

        // uses_outer_this feature
        SourceClass outerThis = SourceClass.this;

        // No new exception thrown feature
        return new Object() {
            @Override
            public String toString() {
                return varCall + "_" + innerRec.value() + "_" + constructorResult.toString();
            }
        };
    }
}