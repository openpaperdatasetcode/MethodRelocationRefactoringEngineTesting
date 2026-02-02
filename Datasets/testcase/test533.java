package com.refactor;

import java.util.List;

// Source class: generic, private, same package as target, has anonymous + member inner class
private class SourceClass<T> {
    // Target class field reference (per_condition)
    private TargetClass<String> targetField = new TargetClass<>();
    
    // Member inner class
    class MemberInnerClass {
        protected void innerMethod() {}
    }
    
    // Method to refactor: instance, void return, protected, in source class
    protected void methodToMove() {
        // ContinueStatement (public modifier, super.field, pos: source)
        public continueLabel:
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                super.field = 1;
                continue continueLabel;
            }
        }
        
        // Instance feature in switch (return TargetClass type, this.methodName(arguments))
        int switchVar = 3;
        switch (switchVar) {
            case 3:
                TargetClass<String> instance = this.innerClassInstanceMethod();
                break;
            default:
                break;
        }
        
        // Labeled statement
        labeledBlock: {
            if (targetField != null) break labeledBlock;
        }
        
        // Variable call
        int localVar = targetField.staticNestedField;
        localVar += 10;
        
        // Uses outer this
        SourceClass<T> outerThis = SourceClass.this;
        outerThis.targetField = new TargetClass<>();
        
        // No new exception thrown
        
        // Anonymous inner class (source class feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                SourceClass.this.methodToMove();
            }
        };
    }
    
    // Helper method for instance feature (returns TargetClass type)
    private TargetClass<String> innerClassInstanceMethod() {
        return new TargetClass<>();
    }
    
    // Super field definition for ContinueStatement
    protected int field;
}

// Target class: generic, protected, has type parameter + static nested class
protected class TargetClass<U> {
    // Target inner class (target_inner)
    public static class StaticNestedClass {
        // Target inner class method placeholder
        protected void targetMethod() {}
    }
    
    // Static nested field (used in variable call)
    public int staticNestedField = 1;
    
    // Type parameter usage
    private U typeParam;
    
    public TargetClass() {}
}