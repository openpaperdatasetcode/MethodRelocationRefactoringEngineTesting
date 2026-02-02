package refactoring.test;

// Source record class: default modifier, same package, local inner + static nested classes
record SourceRecord(String data) {
    // Static nested class (source feature)
    static class SourceStaticNested {
        int nestedValue;
    }

    // Member inner class for source_inner_rec structure
    class SourceInnerClass {
        // Recursive inner class (method_position: source_inner_rec)
        class SourceRecursiveInner {
            // Instance method to be refactored (all specified features)
            protected void refactorMethod(TargetRecord.TargetInnerClass targetParam) { // per_condition: target parameter
                // Variable call (target inner class field)
                targetParam.innerData = data;
                targetParam.counter = 1;

                // If statement
                if (targetParam.counter < 2) {
                    targetParam.counter++;
                    // Depends on inner class
                    TargetRecord.TargetInnerClass innerInstance = new TargetRecord("dep").new TargetInnerClass();
                    targetParam.innerData = innerInstance.innerData + "_updated";
                }

                // Local inner class (source feature)
                class SourceLocalInner {
                    void processTarget() {
                        targetParam.innerData = "local_" + targetParam.innerData;
                        // No new exception
                    }
                }
                new SourceLocalInner().processTarget();

                // Anonymous inner class usage (target_feature)
                targetParam.runnable = new Runnable() {
                    @Override
                    public void run() {
                        targetParam.counter++;
                        // No new exception
                    }
                };
                targetParam.runnable.run();

                // No new exception, void return
            }
        }
    }
}

// Target record class: non-sealed modifier, anonymous inner class feature
non-sealed record TargetRecord(String recordData) implements SealedInterface {
    // Target inner class (target_inner)
    class TargetInnerClass {
        String innerData = recordData;
        int counter;
        Runnable runnable;
    }

    // Anonymous inner class (target_feature)
    private final Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            // No new exception
            new TargetInnerClass().innerData = "anonymous_" + recordData;
        }
    };

    @Override
    public void execute() {
        targetAnonymous.run();
    }
}

// Sealed interface for non-sealed record implementation
sealed interface SealedInterface permits TargetRecord {
    void execute();
}