package com.refactoring.test;

strictfp class SourceClass {
    // Source contains target class field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // Local inner class (source_class feature)
            class LocalInnerClass {
                int getValue() {
                    return SourceClass.this.targetField.innerClass.getValue();
                }
            }
            LocalInnerClass local = new LocalInnerClass();
            local.getValue();
        }
    };

    // Method to be refactored (normal, base type return, protected access, position: source)
    protected int targetMethod() {
        // Constructor invocation
        TargetClass.InnerTargetClass innerInstance = targetField.new InnerTargetClass();
        
        // While statement
        int count = 0;
        while (count < 5) {
            // Uses outer this (uses_outer_this)
            String outerThisValue = SourceClass.this.toString();
            
            // Variable call
            int targetValue = innerInstance.getValue();
            count += targetValue;
        }
        
        // No new exception (no_new_exception)
        return count;
    }
}

public class TargetClass {
    // Member inner class (target_feature)
    class InnerTargetClass {
        int getValue() {
            return 1;
        }
    }
}