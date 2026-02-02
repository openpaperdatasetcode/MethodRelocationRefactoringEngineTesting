package com.example;

import java.lang.reflect.Method;
import java.util.function.Function;

// Source generic abstract class (abstract modifier, generic, same package, local inner class, anonymous inner class)
abstract class SourceClass<T extends CharSequence> {
    // Member inner class (method_position: source_inner)
    class InnerSourceClass<U> {
        // Overloading method 1 (overloading type, Object return, private access)
        private Object targetMethod(TargetClass<String>.InnerTargetClass param) { // per_condition: target parameter
            // Variable call (target_inner)
            String targetValue = param.getValue();

            // Used by reflection
            try {
                Method method = InnerSourceClass.class.getDeclaredMethod("targetMethod", TargetClass.InnerTargetClass.class);
                method.setAccessible(true);
                method.invoke(this, param);
            } catch (Exception e) {
                // No new exception
                System.err.println("Reflection error: " + e.getMessage());
            }

            // Exception throwing statements with call_method (pos: exception throwing statements)
            String callResult;
            try {
                if (targetValue.isEmpty()) {
                    throw new IllegalArgumentException("Empty target value");
                }
                SubClass subClass = new SubClass();
                callResult = subClass.callMethod(targetValue); // sub_class, private, normal, (parameters) -> methodBody
            } catch (IllegalArgumentException e) {
                callResult = e.getMessage();
            }

            // No new exception
            return callResult;
        }

        // Overloading method 2 (overloading feature)
        private Object targetMethod(String suffix) {
            return "processed_" + suffix;
        }

        // Local inner class (source_class feature)
        private void localInnerClassDemo() {
            class LocalInnerClass<V> {
                V process(V val) { return val; }
            }
            LocalInnerClass<String> local = new LocalInnerClass<>();
            local.process("test");
        }

        // Anonymous inner class (source_class feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner in source inner class");
            }
        };
    }

    // Sub class for call_method (sub_class)
    static class SubClass extends SuperClass {
        // Call method (private modifier, normal, (parameters) -> methodBody, return String)
        private String callMethod(String param) {
            // Lambda expression: (parameters) -> methodBody
            Function<String, String> lambda = (s) -> s.toUpperCase() + "_lambda";
            return lambda.apply(param);
        }
    }

    // Super class for sub_class hierarchy
    static class SuperClass {}
}

// Target generic class (public modifier, generic, static nested class target_feature)
public class TargetClass<T> {
    // Static nested class (target_feature)
    public static class StaticNestedTargetClass<U> {}

    // Inner target class (target_inner)
    public class InnerTargetClass {
        private T value;

        public InnerTargetClass(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}