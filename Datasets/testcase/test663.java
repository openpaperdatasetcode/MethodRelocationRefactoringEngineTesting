// Source class: enum, private modifier, same package, two static nested classes
private enum SourceClass {
    INSTANCE;

    // First static nested class (source_class feature)
    public static class StaticNestedClass1 {}
    // Second static nested class (source_class feature)
    public static class StaticNestedClass2 {}

    // Source contains target field (per_condition)
    private final TargetClass targetField = TargetClass.VALUE_1;

    // Target method: instance, void return, private access, source position
    private void sourceMethod() {
        // Variable call (access target field)
        String varCall = targetField.name();
        
        // Constructor invocation (static nested class constructor)
        StaticNestedClass1 nestedObj = new StaticNestedClass1();
        
        // Requires try-catch block
        try {
            // Access target enum instance (no exception, but try-catch is required per spec)
            TargetClass anotherTarget = TargetClass.valueOf(varCall);
            varCall = anotherTarget.toString();
        } catch (IllegalArgumentException e) {
            // Catch block to satisfy requires_try_catch (no new exception thrown)
            varCall = "default";
        }
    }

    // Enum constructor to trigger method
    SourceClass() {
        sourceMethod();
    }
}

// Target class: enum, protected modifier, no target features
protected enum TargetClass {
    VALUE_1, VALUE_2, VALUE_3;

    // No additional features per target_feature spec
}