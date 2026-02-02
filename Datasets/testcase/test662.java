// Source class: normal, default modifier, same package, anonymous inner + static nested class
class SourceClass {
    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        int nestedField = 1;
    }

    // Member inner class for source_inner_rec (recursive inner method)
    class InnerSourceClass {
        // Target method: instance, base type return, final access, source_inner_rec position
        final int sourceInnerRecMethod(TargetClass targetParam) {
            // Variable call
            String varCall = String.valueOf(targetParam.targetField);
            int counter = 1;

            // WhileStatement: private modifier, obj.field, 1, source pos
            private whileLoopBlock: {
                while (counter <= 1) {
                    // Access obj.field (targetParam.targetField) with value 1
                    targetParam.targetField = counter;
                    varCall = String.valueOf(targetParam.targetField);
                    counter++;
                }
            }

            // Override violation (invalid override attempt)
            try {
                new Object() {
                    @Override
                    public int toString() { // Violation: Object.toString() returns String
                        return targetParam.targetField;
                    }
                };
            } catch (Exception e) {
                // No new exception thrown
            }

            // Anonymous inner class (source_class feature)
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(varCall);
                }
            }.run();

            // Recursive call (source_inner_rec)
            if (counter <= 2) {
                return sourceInnerRecMethod(targetParam);
            }

            // No new exception thrown, return base type
            return targetParam.targetField;
        }
    }

    // Trigger method to invoke inner recursive method
    public int triggerMethod(TargetClass targetParam) {
        return new InnerSourceClass().sourceInnerRecMethod(targetParam);
    }
}

// Target class: normal, public modifier, no target features
public class TargetClass {
    // Field for obj.field access in WhileStatement
    int targetField = 1;
}