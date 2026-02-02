package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.function.Function;

// Source class with private modifier, same package as target
private class SourceClass {
    // Static nested class (required feature)
    public static class StaticNestedClass {}

    // Permits (via sealed class hierarchy in nested context)
    sealed interface PermitsInterface permits PermitsImpl {}
    final class PermitsImpl implements PermitsInterface {}

    // Anonymous inner class (required feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class");
        }
    };

    // Target method: varargs, base type return, public, contains target parameter
    public int methodToMove(TargetClass targetParam, String... args) {
        // Type declaration statement
        int count = 0;
        String localVar;

        // Variable call
        localVar = "variable call";
        count = localVar.length() + args.length;

        // Used by reflection (access target class members)
        try {
            Method method = TargetClass.class.getDeclaredMethod("innerClassMethod", String.class);
            method.setAccessible(true);
            method.invoke(targetParam, localVar);
        } catch (Exception e) {
            // No new exception (only catch, no new Exception instantiation)
            count = -1;
        }

        // Call inner class method with functional interface context
        FunctionalInterfaceImpl functional = new FunctionalInterfaceImpl();
        functional.innerClassMethod("test");

        return count; // Base type return (int)
    }

    // Functional interface for call_method position
    @FunctionalInterface
    protected interface FunctionalInterface {
        String innerClassMethod(String input);
    }

    // Inner class with overloaded protected methods (call_method features)
    protected class FunctionalInterfaceImpl implements FunctionalInterface {
        // Overloading 1
        @Override
        public String innerClassMethod(String input) {
            return innerClassMethod(input, 0);
        }

        // Overloading 2 (overload)
        protected String innerClassMethod(String input, int num) {
            // super.methodName(arguments)
            return super.toString() + input + num;
        }
    }
}

/**
 * Javadoc for TargetClass (required target_feature)
 * This is the target class for Move Method refactoring
 */
class TargetClass implements Runnable { // Implements (required target_feature)
    // Member inner class (required target_feature)
    public class MemberInnerClass {
        public String process(String data) {
            return data.toUpperCase();
        }
    }

    // Method called via reflection
    private String innerClassMethod(String input) {
        MemberInnerClass inner = new MemberInnerClass();
        return inner.process(input);
    }

    @Override
    public void run() {
        // Implementation for Runnable
    }
}