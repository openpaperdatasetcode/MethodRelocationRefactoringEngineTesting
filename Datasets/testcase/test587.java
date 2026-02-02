package refactoring.test;

// Source class: private enum, same package as target, static nested + member inner classes
private enum SourceEnum {
    INSTANCE;

    // Source contains target field (per_condition)
    private TargetEnum targetField = TargetEnum.VALUE1;

    // Static nested class (source feature)
    private static class SourceStaticNested {
        int nestedValue = 1;
    }

    // Member inner class (source feature)
    private class SourceMemberInner {
        int innerValue;
    }

    // Method to be refactored: private instance, base type return, source position
    private int refactorMethod() {
        // Variable call (target enum field)
        targetField.counter = 0;
        // ArrayAccess expression (numbers:1, protected modifier, exp:ArrayAccess)
        protected int[] arr = new int[1];
        arr[0] = 1; // ArrayAccess with 1

        // Assert statement
        assert targetField.counter == 0 : "Counter should be zero";
        
        // Synchronized statement
        synchronized (targetField) {
            targetField.counter++;
        }

        // While statement
        int i = 0;
        while (i < 1) {
            // Expression statement
            targetField.counter += arr[0];
            i++;
        }

        // Throw statement (no_new_exception - rethrow existing)
        try {
            if (targetField.counter != 1) {
                throw new IllegalStateException(); // Throw statement
            }
        } catch (IllegalStateException e) {
            // No new exception instantiation
            throw e;
        }

        // Anonymous inner class usage (target_feature)
        targetField.runnable = new Runnable() {
            @Override
            public void run() {
                targetField.counter = 1;
            }
        };

        // Return base type (int)
        return targetField.counter;
    }
}

// Target class: abstract enum, anonymous inner class feature
abstract enum TargetEnum {
    VALUE1 {
        @Override
        public abstract void abstractMethod();
    },
    VALUE2 {
        @Override
        public abstract void abstractMethod();
    };

    int counter = 1;
    Runnable runnable;

    public abstract void abstractMethod();

    // Anonymous inner class (target_feature)
    {
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                counter = 1;
            }
        };
        anonymous.run();
    }
}