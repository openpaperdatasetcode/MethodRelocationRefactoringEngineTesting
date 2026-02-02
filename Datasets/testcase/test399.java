package refactoring.test;

// Same-package others class for SynchronizedStatement pos
class SyncHelperClass {
    // For SynchronizedStatement same_package_others pos
    public static <T extends TargetEnum.TargetInner> void syncUpdate(T inner) {
        // SynchronizedStatement: protected modifier, obj.field + 2
        protected Object lock = new Object();
        synchronized (lock) {
            inner.counter = 2; // obj.field + 2 (target_feature)
            inner.value = "sync_updated_2"; // obj.field
        }
    }
}

// Source enum: default modifier, same package, double local inner classes
enum SourceEnum {
    INSTANCE;

    // Static final method to be refactored (all specified features)
    public static final void refactorMethod(TargetEnum.TargetInner targetParam) { // per_condition + static type + final access
        // Variable call (target inner class field)
        targetParam.value = "init_value";
        targetParam.counter = 0;

        // SynchronizedStatement: same_package_others pos (call helper class)
        SyncHelperClass.syncUpdate(targetParam);

        // If statement
        if (targetParam.counter == 2) {
            // Switch case
            switch (targetParam.value) {
                case "sync_updated_2":
                    targetParam.value = "if_switch_updated";
                    break;
                default:
                    // Throw statement (no_new_exception - rethrow existing)
                    try {
                        throw new IllegalArgumentException("Invalid value");
                    } catch (IllegalArgumentException e) {
                        targetParam.value = "error_" + e.getMessage();
                    }
                    break;
            }
        }

        // Object initialization with call_method invocation (pos: object initialization)
        TargetEnum.TargetInner newInner = new SourceInnerHelper().callMethod(targetParam);

        // First local inner class (source feature)
        class SourceLocalInnerOne {
            void validateTarget(TargetEnum.TargetInner inner) {
                if (inner.counter != 2) {
                    throw new IllegalStateException("Counter must be 2"); // throw statement
                }
            }
        }
        new SourceLocalInnerOne().validateTarget(targetParam);

        // Second local inner class (source feature - duplicate)
        class SourceLocalInnerTwo {
            void updateValue(TargetEnum.TargetInner inner) {
                inner.value = "local_inner_updated";
            }
        }
        new SourceLocalInnerTwo().updateValue(targetParam);

        // No new exception, void return
    }

    // Inner class for call_method (type:inner_class)
    static class SourceInnerHelper {
        // Call_method: default modifier, instance type, (parameters) -> methodBody, object initialization pos, TargetClass return
        public TargetEnum.TargetInner callMethod(TargetEnum.TargetInner param) {
            // (parameters) -> methodBody (lambda feature)
            java.util.function.Function<TargetEnum.TargetInner, TargetEnum.TargetInner> lambda = (inner) -> {
                inner.value = "lambda_updated"; // methodBody
                inner.counter = 2;
                return inner; // instance feature
            };

            // Object initialization pos for lambda invocation
            TargetEnum.TargetInner result = lambda.apply(param);
            return result;
        }
    }
}

// Target enum: public modifier, static nested class feature
public enum TargetEnum {
    VALUE1("one"), VALUE2("two");

    private final String data;

    TargetEnum(String data) {
        this.data = data;
    }

    // Target inner class (target_inner)
    public class TargetInner {
        String value;
        int counter;

        // For object initialization in call_method
        public TargetInner() {
            this.value = "default_inner";
            this.counter = 0;
        }
    }

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedValue = 2;

        // Helper method for target inner class creation
        public static TargetInner createInner(TargetEnum enumInstance) {
            return enumInstance.new TargetInner();
        }
    }
}