package com.refactor;

// Source class: abstract, public, same package as target, local inner + anonymous inner class
public abstract class SourceClass {
    // Method to refactor: varargs, base type (int) return, public, contains target parameter (per_condition)
    public int methodToMove(TargetClass... targetParams) {
        // Overloading feature (private modifier, 1, source, overloading, this.methodName(arguments) in Lambda)
        Runnable lambda = () -> this.overloadMethod(1);
        
        // Super constructor invocation (implicit super() for Object constructor)
        super();
        
        // Switch case
        int switchVar = 1;
        switch (switchVar) {
            case 1:
                variableCall(targetParams);
                break;
            default:
                break;
        }
        
        // Variable call (target parameter access)
        int targetVar = targetParams.length > 0 ? targetParams[0].innerField : 0;
        
        // Local inner class (source feature)
        class LocalInnerClass {
            void localMethod() {}
        }
        new LocalInnerClass().localMethod();
        
        // Anonymous inner class (source feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                SourceClass.this.methodToMove(targetParams);
            }
        };
        
        // No new exception thrown
        return targetVar + switchVar;
    }

    // Overloading method 1 (void return, private)
    private void overloadMethod(int param) {}
    
    // Overloading method 2 (overloading feature)
    private void overloadMethod(String param) {}

    // Variable call helper method
    private void variableCall(TargetClass[] params) {}
}

// Target class: abstract, default modifier, member inner class (target_feature)
abstract class TargetClass {
    // Target field (used in variable call)
    int innerField = 1;
    
    // Member inner class (target_feature)
    class TargetInnerClass {
        void innerMethod() {}
    }
}