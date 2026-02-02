package refactoring.test;

import java.io.IOException;

// Source class: normal, private modifier, same package as target, local inner + static nested classes
private class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    private static class SourceStaticNested {
        int nestedValue = 2;
    }

    // Member inner class (method_position: source_inner)
    private class SourceInnerClass {
        // Method to be refactored: private instance, base type return, source_inner position
        private int refactorMethod() throws IOException { // requires_throws
            // Raw type usage (method feature)
            TargetClass rawTarget;
            rawTarget = new TargetClass(); // raw_type

            // Variable call (target class field)
            targetField.counter = 0;
            rawTarget.counter = 2; // 2 (EmptyStatement target_feature)

            // Switch case (method feature)
            switch (targetField.counter) {
                case 0:
                    // EmptyStatement: private modifier, source pos, this.field feature
                    private void emptyStatementLogic() {
                        this.localField = 2; // this.field + 2 (target_feature)
                        ; // EmptyStatement
                    }

                    int localField = 0;
                    emptyStatementLogic();
                    
                    // Break statement (method feature)
                    break;
                default:
                    break;
            }

            // Local inner class (source feature)
            class LocalInnerClass {
                void processTarget() {
                    targetField.innerClassInstance = targetField.new TargetInnerClass();
                }
            }
            new LocalInnerClass().processTarget();

            // Return base type (int)
            return targetField.counter;
        }
    }
}

// Target class: normal, private modifier, member inner class feature
private class TargetClass {
    int counter;
    TargetInnerClass innerClassInstance;

    // Member inner class (target_feature)
    private class TargetInnerClass {
        int innerCounter = 2;
    }
}