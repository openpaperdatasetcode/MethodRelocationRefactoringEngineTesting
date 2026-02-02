package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.function.Function;

// Permitted subclass for source class
class SourceSubClass<T extends Number> extends SourceClass<T> {}

// Source generic class (private modifier, generic, same package)
private class SourceClass<T extends Comparable<T>> permits SourceSubClass {
    // Target class field to satisfy pre-condition
    TargetClass<String> targetField;

    // Static nested class (duplicated as per feature requirement)
    static class StaticNestedClass1 {
        static <E> E staticMethod1(E param) { return param; }
    }

    // Static nested class (duplicated as per feature requirement)
    private static class StaticNestedClass2<F extends CharSequence> {
        static F staticMethod2(F param) { return param; }
    }

    // Inner class containing the method to be moved
    class SourceInnerClass<G> {
        private G innerVar;

        // Method to be moved (strictfp, instance, returns TargetClass type, source_inner position)
        strictfp TargetClass<T> moveableMethod() {
            // NullPointerException feature (declared, no new instantiation - no_new_exception)
            if (targetField == null) {
                throw new NullPointerException();
            }

            // Accessor with public modifier, object initialization position, lambda body
            public Function<Integer, TargetClass<T>> accessor = (Integer num1) -> {
                // Number 1 feature
                if (num1 == 1) {
                    // Others_class reference
                    return new OthersClass().createTargetInstance(targetField);
                }
                return targetField;
            };

            // this.var = var feature
            this.innerVar = (G) "test_value";

            // Used by reflection feature
            try {
                Method method = TargetClass.class.getDeclaredMethod("instanceMethod", String.class);
                method.invoke(targetField, "reflection_call");
            } catch (Exception e) {
                // No new exception instantiation
            }

            // Variable call feature
            String varCall = targetField.anonymousInnerResult;

            // With bounds feature (generic bound usage)
            T boundedVar = (T) Integer.valueOf(10);
            boundedVar.compareTo((T) Integer.valueOf(5));

            // Access instance method feature
            targetField.instanceMethod(varCall);

            // Return TargetClass type
            return accessor.apply(1);
        }
    }
}

// Others class for accessor feature
class OthersClass<H> {
    public <I extends CharSequence> TargetClass<I> createTargetInstance(TargetClass<I> template) {
        return template;
    }
}

// Target generic class (abstract modifier, generic)
abstract class TargetClass<J extends CharSequence> {
    String anonymousInnerResult;

    // Anonymous inner class as target feature
    {
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                anonymousInnerResult = "anonymous_result";
            }
        };
        anonymousInner.run();
    }

    // Instance method for access_instance_method feature
    strictfp String instanceMethod(String param) {
        return param + "_processed";
    }

    // Overloading feature (call_method target)
    strictfp String instanceMethod(Integer param) {
        return param.toString();
    }

    // Expression position call: new ClassName().method()
    String overloadedCall = new TargetClass<String>() {
        @Override
        public strictfp String instanceMethod(String param) {
            return super.instanceMethod(param);
        }
    }.instanceMethod("overload_test");
}