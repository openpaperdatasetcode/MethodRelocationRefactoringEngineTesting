package com.refactoring.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Target class (normal class, default modifier, static nested class feature)
class TargetClass {
    int value = 10; // For lambda () -> System.out.println(this.value)

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        // Final method for override_violation
        public final void finalMethod() {}
    }

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        String innerField; // For per_condition and variable call
        TargetInnerRec nestedInner; // Recursive inner class feature

        // Method for reflection access
        public void setInnerField(String value) {
            this.innerField = value;
        }
    }
}

// Source class (normal class, public modifier, same package, static nested + local inner class)
public class SourceClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        static void helperMethod(TargetClass.TargetInnerRec inner) {
            inner.innerField = "static_nested_processed";
        }
    }

    // Method to be refactored (varargs, Object return, public access, source position)
    public Object moveMethod(TargetClass.TargetInnerRec... targetParams) {
        // Per_condition: contains target parameter (varargs)
        if (targetParams == null || targetParams.length == 0) {
            return null;
        }

        // Super constructor invocation (Object superclass)
        Object superObj = new Object();

        // While statement
        int count = 0;
        while (count < 3) {
            for (TargetClass.TargetInnerRec target : targetParams) {
                target.innerField = "while_iter_" + count;
            }
            count++;
        }

        // Lambda expression: () -> System.out.println(this.value)
        TargetClass outerTarget = new TargetClass();
        Runnable lambda = () -> System.out.println(outerTarget.value);
        lambda.run();

        // Variable call (access target field - per_condition)
        List<String> varResults = new ArrayList<>();
        for (TargetClass.TargetInnerRec target : targetParams) {
            String varCall = target.innerField;
            varResults.add(varCall);
            target.innerField = varCall + "_var_modified";
        }

        // Override violation (attempt to override final method)
        class OverrideViolationClass extends TargetClass.TargetStaticNested {
            @Override
            public void finalMethod() {} // Compile error (override_violation)
        }

        // Used by reflection
        try {
            Method setFieldMethod = TargetClass.TargetInnerRec.class.getMethod("setInnerField", String.class);
            for (TargetClass.TargetInnerRec target : targetParams) {
                setFieldMethod.invoke(target, "reflection_set_" + target.innerField);
            }
        } catch (Exception e) {
            // Handle reflection exceptions (no new exception thrown)
            varResults.add("Reflection error: " + e.getMessage());
        }

        // IOException handling (feature: IOException)
        try {
            if (varResults.isEmpty()) {
                throw new IOException("No variable results");
            }
        } catch (IOException e) {
            varResults.add("IOException caught: " + e.getMessage());
        }

        // Local inner class (source_feature)
        class LocalInnerClass {
            void processRecursive(TargetClass.TargetInnerRec inner) {
                if (inner.nestedInner == null) {
                    inner.nestedInner = new TargetClass().new TargetInnerRec();
                    inner.nestedInner.innerField = "nested_inner_processed";
                }
                SourceStaticNested.helperMethod(inner);
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        for (TargetClass.TargetInnerRec target : targetParams) {
            localInner.processRecursive(target);
        }

        // No new exception
        return varResults;
    }
}