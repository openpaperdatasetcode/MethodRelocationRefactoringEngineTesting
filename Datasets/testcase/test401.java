import java.util.function.Function;

// Source enum class: protected modifier, same package, anonymous inner/static nested class
protected enum SourceEnum {
    INSTANCE;

    // Static nested class (fulfills source_class feature)
    static class SourceStaticNested {}

    // Member inner class (first level)
    class SourceInnerClass {
        // Nested inner class (source_inner_rec) containing target method
        class SourceInnerRecClass {
            // Target class field (fulfills per_condition: source contains target's field)
            TargetEnum targetField = TargetEnum.VALUE_1;

            // Target method: instance, base type return, private access, in source_inner_rec
            private int processData(TargetEnum param) {
                // Super constructor invocation (enum implicit super)
                super();

                // Variable call to target parameter
                int targetVar = param.getTargetValue();

                // Access instance method (access_instance_method)
                int instanceVal = this.calculateValue(targetVar);

                // ExpressionMethodReference (numbers:2, modifier:private)
                Function<Integer, Integer> func = this::doubleValue; // ExpressionMethodReference
                int doubled = func.apply(2); // numbers:2

                // No new exception thrown (no_new_exception)
                return targetVar + instanceVal + doubled; // Base type return (int)
            }

            // Private helper method for ExpressionMethodReference
            private int doubleValue(int num) {
                return num * 2;
            }

            // Instance method for access_instance_method
            private int calculateValue(int val) {
                return val + 10;
            }
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(INSTANCE);
        }
    };
}

// Target enum class: public modifier, has static nested class (target_feature)
public enum TargetEnum {
    VALUE_1(5), VALUE_2(10);

    private final int targetValue;

    // Static nested class (fulfills target_feature)
    public static class TargetStaticNested {}

    TargetEnum(int targetValue) {
        this.targetValue = targetValue;
    }

    // Getter for variable call
    public int getTargetValue() {
        return targetValue;
    }
}