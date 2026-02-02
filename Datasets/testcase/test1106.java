package com.example;

// Source class (strictfp modifier, normal class, same package, two static nested classes)
strictfp class SourceClass {
    // First static nested class (source_class feature)
    static class StaticNestedClass1 {}

    // Second static nested class (source_class feature)
    static class StaticNestedClass2 {}

    // Method to be refactored (varargs, void return, default access, position: source)
    void targetMethod(TargetClass param, String... args) { // per_condition: target parameter
        // Constructor invocation
        TargetClass newTarget = new TargetClass("initValue");

        // Super keywords usage
        super.toString();

        // Variable call
        String targetValue = param.getValue();
        for (String arg : args) {
            targetValue += arg;
        }
        param.setValue(targetValue);

        // Override violation (attempt to override final method)
        @Override // Compile error: override_violation (simulate with final method override)
        public final String toString() {
            return super.toString() + targetValue;
        }

        // No new exception
    }

    // Final method to simulate override violation context
    public final String toString() {
        return "SourceClass";
    }
}

// Target class (public modifier, normal class, empty target_feature)
public class TargetClass {
    private String value;

    public TargetClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}