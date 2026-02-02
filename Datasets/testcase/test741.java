package refactoring.test;

// Source class: enum, public modifier, same package as target, features: anonymous inner class, static nested class
public enum SourceEnum {
    INSTANCE;

    // Per condition: source contains target class field
    private TargetEnum targetField = TargetEnum.VALUE_1;

    // Static nested class (source_class feature)
    public static class SourceStaticNested {
        int nestedValue;

        public SourceStaticNested(int value) {
            this.nestedValue = value;
        }
    }

    // Method to be refactored: varargs, return base type (int), public access, position source
    public int moveMethod(Integer... args) {
        // Variable call feature
        int sum = 0;
        // Type declaration statement feature
        SourceStaticNested nestedObj;
        nestedObj = new SourceStaticNested(0);

        // Requires_try_catch feature
        try {
            for (Integer arg : args) {
                sum += arg != null ? arg : 0;
                // Anonymous inner class (source_class feature)
                Runnable runnable = new Runnable() {
                    // Super constructor invocation feature
                    {
                        super();
                    }

                    @Override
                    public void run() {
                        System.out.println("Processing arg: " + arg);
                    }
                };
                runnable.run();
            }
        } catch (NullPointerException e) {
            sum = -1;
        }

        nestedObj.nestedValue = sum;
        // Use target field to satisfy per_condition
        sum += targetField.getNestedClass().calculate(sum);
        return sum;
    }
}

/**
 * Javadoc for TargetEnum (target_feature: javadoc)
 * Enum class for move method refactoring target
 */
// Target class: enum, private modifier, same package, target_feature: javadoc, static nested class
private enum TargetEnum {
    VALUE_1, VALUE_2;

    // Static nested class (target_feature)
    static class TargetStaticNested {
        int calculate(int value) {
            return value * 2;
        }
    }

    public TargetStaticNested getNestedClass() {
        return new TargetStaticNested();
    }
}