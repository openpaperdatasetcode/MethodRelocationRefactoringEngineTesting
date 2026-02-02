package com.refactoring.test;

protected class SourceClass {
    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {}
    };

    // Method to be refactored (instance, TargetClass return, protected access, position: source)
    protected TargetClass targetMethod(TargetClass param) { // per_condition: target parameter
        // Constructor invocation
        TargetClass.InnerTargetClass innerInstance = param.new InnerTargetClass();
        
        // CharacterLiteral (numbers:1, public modifier, exp:CharacterLiteral)
        public char charLiteral = '1'; // 1
        
        // Synchronized statement
        synchronized (param) {
            // Variable call
            String targetFieldValue = innerInstance.field;
            
            // Switch case
            switch (charLiteral) {
                case '1':
                    // Depends on inner class
                    int processed = innerInstance.processValue(1);
                    break;
                default:
                    processed = 0;
                    break;
            }

            // Inner class with LabeledStatement (pos: inner class)
            class LocalInnerClass {
                // LabeledStatement (private modifier, obj.field, 1, pos: inner class)
                private void labeledBlock() {
                    label:
                    for (int i = 0; i < 1; i++) { // 1
                        String objField = innerInstance.field; // obj.field
                        if (objField != null) break label;
                    }
                }
            }
            LocalInnerClass local = new LocalInnerClass();
            local.labeledBlock();
        }
        
        // No new exception (no_new_exception)
        return param;
    }
}

// Target class (normal, abstract modifier, member inner class target_feature)
abstract class TargetClass {
    // Member inner class (target_feature)
    class InnerTargetClass {
        String field = "targetInnerField"; // obj.field
        
        int processValue(int num) {
            return num * 2;
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}