package com.example;

import java.util.List;
import java.util.ArrayList;

// Source class (default modifier, normal class, same package, permits, anonymous inner class, static nested class)
sealed class SourceClass permits SourceSubClass { // permits feature
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in source class");
        }
    };

    // Method to be refactored (instance, TargetClass return, public access, position: source)
    public TargetClass targetMethod() {
        // Variable call
        TargetClass.InnerTargetClass innerTarget = targetField.new InnerTargetClass(); // target_inner
        String targetValue = innerTarget.getValue();

        // With_bounds (generic bounds)
        List<? extends CharSequence> boundedList = new ArrayList<>();
        boundedList.add(targetValue);

        // Overloading method call in exception throwing statements (pos: exception throwing statements)
        try {
            int result = overloadingMethod(3); // 3, inner_class, overloading, obj.m1().m2().m3()
            if (result < 0) {
                throw new IllegalArgumentException("Invalid result");
            }
        } catch (IllegalArgumentException e) {
            // No new exception
            System.err.println("Exception: " + e.getMessage());
        }

        // Depends on inner class (target's local inner class)
        targetField.invokeLocalInner();

        // No new exception
        return targetField;
    }

    // Overloading method 1 (private modifier, base type return, 3, inner_class, overloading)
    private int overloadingMethod(int num) {
        // obj.m1().m2().m3() chain call
        return new ChainHelper().m1().m2().m3(num);
    }

    // Overloading method 2 (overloading feature)
    private int overloadingMethod(String str) {
        return str.length() + 3;
    }

    // Helper class for obj.m1().m2().m3() chain call
    private static class ChainHelper {
        ChainHelper m1() { return this; }
        ChainHelper m2() { return this; }
        int m3(int num) { return num * 2; }
    }
}

// Permitted subclass for sealed source class (permits feature)
final class SourceSubClass extends SourceClass {}

// Target class (private modifier, normal class, local inner class target_feature)
private class TargetClass {
    // Inner target class (target_inner)
    class InnerTargetClass {
        private String value = "targetInnerValue";

        public String getValue() {
            return value;
        }
    }

    // Local inner class (target_feature)
    void invokeLocalInner() {
        class LocalInnerClass {
            String process(String val) {
                return val + "_processed";
            }
        }
        LocalInnerClass local = new LocalInnerClass(); // depends_on_inner_class
        InnerTargetClass inner = new InnerTargetClass();
        System.out.println(local.process(inner.getValue()));
    }
}