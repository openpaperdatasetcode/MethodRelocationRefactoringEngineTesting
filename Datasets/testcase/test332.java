package com.refactoring.movemethod;

// Strictfp normal source class, same package as target, implements interface
strictfp class SourceClass implements Runnable {
    // Field for this.field reference in ThrowStatement
    private int sourceField = 2;

    // Varargs method (default access, void return, contains target parameter)
    void methodToMove(PrivateTargetClass targetParam, String... args) {
        // Variable call
        String localVar = "variableCall";
        int numLiteral = 1; // NumberLiteral with 1, private modifier (via enclosing scope)

        // If statement
        if (args.length > numLiteral) {
            // Expression statement
            localVar = localVar.toUpperCase();
        }

        // Access instance method (target class instance method)
        targetParam.instanceMethod();

        // Uses outer this (outer class this reference)
        Runnable outerThisRef = SourceClass.this::run;

        // Static ThrowStatement (this.field + 2, same_package_target position)
        static {
            try {
                if (new SourceClass().sourceField == 2) {
                    throw new IllegalArgumentException("Field value: " + new SourceClass().sourceField);
                }
            } catch (IllegalArgumentException e) {
                // No new exception (reuse existing, no new instantiation)
            }
        }
    }

    @Override
    public void run() {
        // Implements Runnable interface method
    }
}

// Private normal target class (same package as source)
private class PrivateTargetClass {
    // Local inner class (target_feature)
    public void targetMethod() {
        class LocalInnerClass {
            private String innerValue = "localInner";
        }
        LocalInnerClass localInner = new LocalInnerClass();
    }

    // Instance method for access_instance_method feature
    public void instanceMethod() {
        // Method implementation
    }
}