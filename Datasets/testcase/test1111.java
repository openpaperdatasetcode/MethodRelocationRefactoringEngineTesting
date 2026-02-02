package com.example;

// Source class (private modifier, normal class, same package, static nested class, anonymous inner class)
private class SourceClass {
    // per_condition: source contains the field of the target
    private TargetClass targetField = new TargetClass();
    // transient modifier field for ExpressionStatement
    transient String field = "sourceTransientField";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in source class");
        }
    };

    // Method to be refactored (instance, void return, protected access, position: source)
    protected void targetMethod() {
        // ExpressionStatement (transient modifier, this.field, 2, pos: source)
        transient void exprStatementBlock() {
            this.field = "updated_" + 2; // this.field, 2 in target_feature
            System.out.println(this.field); // ExpressionStatement
        }
        exprStatementBlock();

        // Type declaration statement
        String targetValue;
        int labeledVar;

        // Variable call
        targetValue = targetField.getValue();

        // Labeled statement
        label: {
            labeledVar = targetValue.length();
            if (labeledVar > 0) break label;
        }

        // Assert statement
        assert labeledVar > 0 : "Labeled variable is zero";

        // No new exception
    }
}

// Target class (default modifier, normal class, anonymous inner class target_feature)
class TargetClass {
    private String value = "targetValue";

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in target class");
        }
    };

    public String getValue() {
        return value;
    }
}