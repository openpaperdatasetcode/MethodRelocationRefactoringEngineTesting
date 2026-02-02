import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {}

// Parent class for source class extends + super constructor invocation
sealed class ParentClass<T> permits SourceClass<T> {
    protected String parentField;

    public ParentClass(String parentField) {
        this.parentField = parentField;
    }
}

// Source generic normal class (protected modifier, same package as target, type parameter + extends + permits)
non-sealed class SourceClass<T extends CharSequence> extends ParentClass<T> {
    // Field of target class (satisfies per_condition)
    private TargetClass targetField = new TargetClass();

    // First local inner class (source_class feature)
    public void methodWithFirstLocalInner() {
        class FirstLocalInnerClass {
            T processData(T data) {
                return data;
            }
        }
        new FirstLocalInnerClass().processData((T) "First local inner data");
    }

    // Second local inner class (source_class feature)
    public void methodWithSecondLocalInner() {
        class SecondLocalInnerClass {
            int calculateLength(T data) {
                return data.length();
            }
        }
        new SecondLocalInnerClass().calculateLength((T) "Second local inner data");
    }

    // Super constructor invocation (source class constructor)
    public SourceClass(String parentField) {
        super(parentField); // Super constructor invocation
    }

    // Overloading method 1 (strictfp access, void return)
    @ProcessAnnotation // has_annotation feature
    strictfp void processTarget() {
        processTarget("default");
    }

    // Overloading method 2 (overloading feature, strictfp access, void return)
    @ProcessAnnotation // has_annotation feature
    strictfp void processTarget(T... values) {
        // Normal method with specified features (private modifier, pos: for, return void)
        private void helperMethod() {
            int val = 3; // method_feature: 3
            // method_feature: inner_class + (parameters) -> methodBody (lambda expression)
            Runnable lambda = () -> { // (parameters) -> methodBody
                TargetClass.InnerStaticNested nested = targetField.new InnerStaticNested();
                nested.setCounter(val);
            };

            // pos: for loop
            labeled: // Labeled statement
            for (int i = 0; i < val; i++) {
                if (i == 2) {
                    break labeled; // Labeled break (labeled statement feature)
                }
                lambda.run();
                // Variable call to target static nested class
                targetField.getStaticNested().setData("Loop iteration: " + i);
            }
        }

        // Invoke helper method
        helperMethod();

        // Expression statement
        targetField.getStaticNested().setCounter(targetField.getStaticNested().getCounter() + 1);
        // Variable call to target class
        for (T val : values) {
            targetField.getStaticNested().setData(targetField.getStaticNested().getData() + "_" + val);
        }
    }
}

// Target normal class (public modifier, same package as source)
public class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNestedClass {
        private String data;
        private int counter = 0;

        // Variable call methods
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }
    }

    // Inner class for lambda usage (inner_class in method_feature)
    public class InnerStaticNested {
        private int counter;

        public void setCounter(int counter) {
            this.counter = counter;
        }
    }

    private final TargetStaticNestedClass staticNested = new TargetStaticNestedClass();

    public TargetStaticNestedClass getStaticNested() {
        return staticNested;
    }
}