package refactoring.test;

// Source record class: public, same package as target, member inner + anonymous inner classes
public record SourceRecord(String data) {
    // Member inner class (source feature)
    public class SourceInnerClass {
        // Recursive inner class (method_position: source_inner_rec)
        public class SourceRecursiveInner {
            // Method to be refactored: varargs, void return, default access, source_inner_rec
            void refactorMethod(TargetRecord.TargetInnerClass targetParam, String... none) { // parameter type:none (varargs)
                // Variable call (target inner class field)
                targetParam.value = "refactor";
                // Raw type usage
                TargetRecord rawTarget;
                rawTarget = new TargetRecord(null);

                // Super constructor invocation (in anonymous inner class)
                Runnable anonymous = new Runnable() {
                    {
                        super(); // super constructor invocation
                    }
                    @Override
                    public void run() {
                        // Synchronized statement
                        synchronized (targetParam) {
                            targetParam.counter++;
                        }
                    }
                };
                anonymous.run();

                // Exception throwing statements with generic method call
                try {
                    if (targetParam.counter < 1) {
                        throw new IllegalArgumentException();
                    }
                    // Generic method: protected, pos=exception throwing, return Target type
                    TargetRecord.TargetInnerClass genericResult = genericMethod(1); // 1 (method_feature)
                } catch (IllegalArgumentException e) {
                    // No new exception
                    e.printStackTrace();
                }

                // Super keywords usage
                SourceRecursiveInner.super.getClass();

                // No new exception, void return
            }

            // Generic method with specified features
            protected <T> TargetRecord.TargetInnerClass genericMethod(T param) {
                // inner_class feature + new ClassName().method()
                TargetRecord.TargetInnerClass inner = new TargetRecord("test").new TargetInnerClass();
                inner.counter = (Integer) param; // 1 (method_feature)
                return inner;
            }
        }
    }

    // Anonymous inner class (source feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            new SourceInnerClass().new SourceRecursiveInner();
        }
    };
}

// Target record class: default modifier, member inner class feature
record TargetRecord(String recordData) {
    // Member inner class (target_inner - target for method)
    class TargetInnerClass {
        String value;
        int counter = 1;
    }
}