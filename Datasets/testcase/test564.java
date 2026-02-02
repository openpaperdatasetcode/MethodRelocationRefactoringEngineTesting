package com.refactor;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

// Source class: normal, private modifier, same package as target, two member inner classes
private class SourceClass {
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();
    
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "protectedValue";

    // First member inner class (source feature)
    class SourceInner1 {}
    // Second member inner class (source feature)
    class SourceInner2 {}

    // Method to refactor: instance, void return, private, method types parameter is:none
    private void methodToMove() {
        // Static feature (default modifier, 1, target, static, super.methodName() in switch)
        int switchVar = 1;
        switch (switchVar) {
            case 1:
                List<String> staticResult = TargetClass.staticMethod();
                super.toString(); // Super keywords usage
                break;
            default:
                break;
        }

        // Super keywords (additional usage)
        super.hashCode();

        // Variable call (target field access)
        String targetVar = targetField.staticNestedField;

        // Access outer protected field
        String protectedAccess = SourceClass.this.outerProtectedField;

        // Call method: source type, protected modifier, accessor + outerInstance.new InnerClass().methodName() in Lambda
        Consumer<Void> lambda = (v) -> SourceClass.this.new SourceInner1().innerAccessorMethod();

        // No new exception thrown
    }

    // Call method: source type, protected modifier, accessor, outerInstance.new InnerClass().methodName()
    protected void innerAccessorMethod() {
        new SourceInner2().method();
    }
}

// Target class: normal, protected modifier, static nested class (target_feature)
protected class TargetClass {
    // Target field (used in variable call)
    public String staticNestedField = "targetValue";

    // Static nested class (target_feature)
    static class TargetStaticNested {}

    // Static method for static feature (super.methodName() context)
    public static List<String> staticMethod() {
        return new ArrayList<>();
    }
}