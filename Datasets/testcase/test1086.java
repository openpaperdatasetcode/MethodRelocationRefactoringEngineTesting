package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.List;

// Source class (private modifier, normal class, same package, permits, member inner class, static nested class)
private sealed class SourceClass permits SourceSubClass { // permits feature
    // Member inner class (source_class feature) - method_position: source_inner
    class InnerSourceClass {
        // Method to be refactored (instance, Object return, default access)
        Object targetMethod(TargetClass.InnerTargetClass param) { // per_condition: target parameter
            // Requires try-catch
            try {
                // Constructor invocation
                TargetClass targetInstance = new TargetClass();
                TargetClass.InnerTargetClass innerInstance = targetInstance.new InnerTargetClass();

                // Do statement
                int count = 0;
                do {
                    // Variable call
                    String targetValue = param.getValue();
                    count++;
                    // Raw type usage
                    List rawList = new java.util.ArrayList(); // raw_type feature
                    rawList.add(targetValue);
                } while (count < 3);

                // Override violation (attempt to override final method)
                @Override // Compile error: override_violation
                public final String toString() {
                    return super.toString();
                }

                // Used by reflection
                Method method = InnerSourceClass.class.getDeclaredMethod("targetMethod", TargetClass.InnerTargetClass.class);
                method.setAccessible(true);
                method.invoke(this, param);

                return innerInstance;
            } catch (Exception e) { // requires_try_catch
                // Fallback for exception handling
                return null;
            }
        }
    }

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}
}

// Permitted subclass for sealed source class (permits feature)
final class SourceSubClass extends SourceClass {}

// Target class (public modifier, normal class, anonymous inner class target_feature)
public class TargetClass {
    // Inner target class (target_inner)
    class InnerTargetClass {
        private String value = "targetInnerValue";

        public String getValue() {
            return value;
        }
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            InnerTargetClass inner = new InnerTargetClass();
            System.out.println(inner.getValue());
        }
    };
}