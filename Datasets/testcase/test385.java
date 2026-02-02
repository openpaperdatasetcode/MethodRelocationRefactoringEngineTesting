package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Interface for source class implements feature
interface SourceProcessable {
    List<String> process();
    List<String> process(String param);
}

// Source class: normal, protected modifier, same package, implements + double local inner classes
protected class SourceClass implements SourceProcessable {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class (method_position: source_inner)
    class SourceInnerClass {
        /**
         * First overloading method (method javadoc feature)
         * @return List<String> processed result
         */
        @SuppressWarnings("rawtypes") // has_annotation (duplicate feature)
        List<String> process() {
            return process("default");
        }

        /**
         * Second overloading method (overloading type, method javadoc)
         * @param param additional parameter for overloading
         * @return List<String> processed result
         */
        @Deprecated // has_annotation
        List<String> process(String param) {
            List<String> result = new ArrayList<>();
            
            // Variable call (target inner recursive class field)
            targetField.innerRecursive.counter = 2;
            targetField.innerRecursive.recursiveInner.value = param;
            // Access instance method
            String instanceResult = targetField.innerRecursive.processData();
            result.add(instanceResult);

            // ForStatement: private modifier, same_package_target pos, this.field + 2
            private void forLogic() {
                for (int i = 0; i < 2; i++) {
                    this.field = 2; // this.field (target_feature)
                    result.add("for:" + i);
                }
            }
            forLogic();

            // While statement
            int j = 0;
            while (j < 2) {
                result.add("while:" + j);
                j++;
            }

            // Try statement + requires_try_catch
            try {
                // Access instance method with potential exception
                targetField.innerRecursive.throwableMethod();
            } catch (RuntimeException e) {
                // Requires try-catch (no new exception)
                result.add("error:" + e.getMessage());
            }

            // First local inner class (source feature)
            class LocalInnerOne {
                void updateTarget() {
                    targetField.innerRecursive.counter++;
                }
            }

            // Second local inner class (source feature - duplicate)
            class LocalInnerTwo {
                void checkOverride() {
                    // Override violation (invalid attempt to override final method)
                    class InvalidOverride extends TargetClass {
                        @Override
                        public final void targetFinalMethod() { // Compile error: final method override
                            // No new exception
                        }
                    }
                }
            }

            new LocalInnerOne().updateTarget();
            new LocalInnerTwo().checkOverride();

            return result;
        }

        // Dummy field for ForStatement this.field
        private int field;
    }

    // Implement interface methods (overloading)
    @Override
    public List<String> process() {
        return new SourceInnerClass().process();
    }

    @Override
    public List<String> process(String param) {
        return new SourceInnerClass().process(param);
    }
}

// Target class: normal, sealed modifier, local inner class feature
sealed class TargetClass permits TargetClass.TargetInnerRecursive {
    TargetInnerRecursive innerRecursive = new TargetInnerRecursive();

    public final void targetFinalMethod() {}

    // Target inner recursive class (target_inner_rec)
    final class TargetInnerRecursive {
        int counter = 2;
        TargetRecursiveInner recursiveInner = new TargetRecursiveInner();

        String processData() {
            // Local inner class (target_feature)
            class TargetLocalInner {
                String formatData() {
                    return recursiveInner.value + "_" + counter;
                }
            }
            return new TargetLocalInner().formatData();
        }

        void throwableMethod() {
            if (counter < 2) {
                throw new RuntimeException("Invalid counter");
            }
        }

        // Recursive inner structure
        class TargetRecursiveInner {
            String value;
        }
    }
}