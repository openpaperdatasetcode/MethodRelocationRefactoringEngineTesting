package refactoring.test;

import java.util.function.Consumer;

// Source class: normal, public modifier, same package, double static nested classes
public class SourceClass {
    // First static nested class (source feature)
    public static class SourceStaticNestedOne {
        int value = 0;
    }

    // Second static nested class (source feature - duplicate)
    public static class SourceStaticNestedTwo {
        String data;
    }

    // Member inner abstract class (method_position: source_inner)
    public abstract class SourceInnerClass {
        // Abstract method to be refactored (all specified features)
        @SuppressWarnings("unused") // has_annotation
        public abstract void refactorMethod(TargetClass<String> targetParam); // per_condition + abstract type

        // Concrete implementation holder for abstract method logic
        public void refactorMethodImpl(TargetClass<String> targetParam) {
            // Variable call (target generic class field)
            targetParam.value = "test_value";
            targetParam.counter = 1;

            // Constructor invocation (target class)
            TargetClass<Integer> intTarget = new TargetClass<>(100);

            // Labeled statement
            processLabel: {
                // If statement
                if (targetParam.counter > 0) {
                    // Switch case
                    switch (targetParam.value.length()) {
                        case 0:
                            throw new IllegalArgumentException("Empty value"); // throw statement
                        case 9:
                            targetParam.value += "_updated";
                            break processLabel; // break labeled statement
                        default:
                            break;
                    }
                }

                // Lambda expression: () -> System.out.println(this.value)
                Consumer<TargetClass<String>> lambda = t -> {
                    System.out.println(this.value); // this.value in lambda
                    t.value = "lambda_updated";
                };
                lambda.accept(targetParam);
            }

            // Override violation (invalid attempt to override final method)
            class InvalidOverride extends TargetClass<String> {
                public InvalidOverride(String value) {
                    super(value);
                }

                @Override
                public final void printValue() { // Compile error: final method override
                    // No new exception
                }
            }

            // Throw statement (no_new_exception - rethrow existing)
            try {
                if (intTarget.value < 0) {
                    throw new RuntimeException("Negative value");
                }
            } catch (RuntimeException e) {
                // No new exception instantiation
                throw e;
            }
        }

        // Field for lambda this.value reference
        private String value = "inner_class_value";
    }

    // Helper method to instantiate inner class
    public void executeRefactor(TargetClass<String> target) {
        new SourceInnerClass() {
            @Override
            public void refactorMethod(TargetClass<String> targetParam) {
                refactorMethodImpl(targetParam);
            }
        }.refactorMethod(target);
    }
}

// Target class: normal, default modifier, type parameter feature
class TargetClass<T> { // type parameter (target_feature)
    T value;
    int counter;

    // Constructor for constructor invocation feature
    public TargetClass(T value) {
        this.value = value;
    }

    // Final method for override_violation feature
    public final void printValue() {
        System.out.println(value);
    }
}