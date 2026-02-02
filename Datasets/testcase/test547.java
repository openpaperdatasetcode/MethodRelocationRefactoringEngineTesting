package com.refactor;

import java.lang.reflect.Method;

// Source class: enum, private, same package as target, two static nested classes
private enum SourceEnum {
    INSTANCE;

    // First static nested class
    private static class StaticNested1 {}
    // Second static nested class
    private static class StaticNested2 {}

    // Method to refactor: varargs, Object return, protected, in source enum, has target parameter (per_condition)
    protected Object methodToMove(TargetEnum... targetParams) {
        // Super constructor invocation (enum implicitly extends Enum, invoke super constructor)
        super("SourceEnum", 0);

        // SuperMethodInvocation (numbers:1, volatile modifier)
        volatile int count = 1;
        for (int i = 0; i < count; i++) {
            super.toString(); // Super method invocation (Enum.toString())
        }

        // Variable call (target parameter variable call)
        String varCall = targetParams.length > 0 ? targetParams[0].name() : "";

        // No new exception thrown

        // Used by reflection
        try {
            Method method = SourceEnum.class.getDeclaredMethod("methodToMove", TargetEnum[].class);
            method.setAccessible(true);
            method.invoke(INSTANCE, (Object) targetParams);
        } catch (Exception e) {
            // No new exception thrown (catch reflection exceptions without throwing new ones)
        }

        return targetParams.length > 0 ? targetParams[0] : new Object();
    }
}

// Target class: enum, abstract, static nested class
abstract enum TargetEnum {
    VALUE1, VALUE2;

    // Static nested class (target feature)
    protected static class TargetStaticNested {}
}