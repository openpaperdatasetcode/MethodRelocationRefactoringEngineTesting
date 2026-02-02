package refactoring.test;

// Source enum: non-sealed modifier, same package, static nested + anonymous inner classes
non-sealed enum SourceEnum {
    INSTANCE;

    // Source contains target field (per_condition)
    private static TargetEnum.TargetInner targetField = TargetEnum.VALUE1.new TargetInner();

    // Static nested class (source feature)
    public static class SourceStaticNested {
        int nestedValue = 1;
    }

    // Anonymous inner class (source feature)
    private static final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            targetField.counter = SourceStaticNested.nestedValue;
        }
    };

    // Static method to be refactored (all specified features)
    protected static void refactorMethod() {
        // Variable call (target inner class field)
        targetField.value = "refactor_value";
        // Expression statement
        targetField.counter += 1;

        // If/else conditions with call_method invocation
        if (targetField.counter > 0) {
            int callResult = SourceEnum.callMethod(targetField); // ClassName.methodName(arguments)
            targetField.counter = callResult;
        } else {
            targetField.counter = 0;
        }

        // Trigger anonymous inner class logic
        sourceAnonymous.run();

        // No new exception, void return
    }

    // Call_method: source type, protected modifier, normal type, ClassName.methodName(), if/else pos, int return
    protected static int callMethod(TargetEnum.TargetInner inner) {
        // Normal method feature
        inner.value = "call_method_updated";
        // Return base type (int)
        return inner.counter + 1;
    }
}

// Target enum: protected modifier, anonymous inner class feature
protected enum TargetEnum {
    VALUE1("one"), VALUE2("two");

    private final String data;

    TargetEnum(String data) {
        this.data = data;
    }

    // Target inner class (target_inner)
    public class TargetInner {
        String value;
        int counter = 0;

        // Anonymous inner class (target_feature)
        private final Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                // No new exception
                value = "target_anonymous_" + data;
            }
        };

        public void triggerAnonymous() {
            targetAnonymous.run();
        }
    }
}