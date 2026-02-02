package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source class (default modifier, same package, member inner + anonymous inner class)
class SourceClass {
    protected String outerProtectedField = "protectedValue"; // For access_outer_protected

    // Member inner class (source feature)
    class SourceMemberInner {
        int innerField = 10;
    }

    // Anonymous inner class (source feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(SourceClass.this.outerProtectedField); // uses_outer_this
        }
    };

    // Method to be refactored (instance, void return, default access, source position)
    void moveMethod(TargetClass targetParam) {
        // Per_condition: contains target parameter
        if (targetParam == null) {
            throw new IllegalArgumentException("Target parameter cannot be null"); // exception throwing statements
        }

        // Recursion method (type:recursion, modifier:default, method_feature:1/target/recursion/instanceReference.methodName(arguments))
        default int recursiveMethod(TargetClass target, int count) {
            if (count <= 1) { // 1 from method_feature
                return target.targetField;
            }
            // instanceReference.methodName(arguments)
            return targetParam.instanceMethod(count) + recursiveMethod(target, count - 1); // recursion
        }

        // Constructor invocation
        TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
        SourceMemberInner memberInner = new SourceMemberInner();

        // Enhanced for statement (typo "enhganced" as per spec)
        List<TargetClass> targetList = new ArrayList<>();
        targetList.add(targetParam);
        for (TargetClass tc : targetList) {
            tc.targetField += 1;
        }

        // Type declaration statement
        RawTypeExample rawType = new RawTypeExample();
        rawType.process(targetParam);

        // Variable call + access_outer_protected + uses_outer_this
        String varCall = SourceClass.this.outerProtectedField;
        targetParam.targetField = varCall.length() + memberInner.innerField;

        // Override violation
        class OverrideViolation extends TargetClass {
            // Invalid override (different return type)
            @Override
            public int instanceMethod(int num) { // Parent returns String - violation
                return num * 2;
            }
        }

        // Raw type feature
        class RawTypeExample {
            void process(TargetClass target) {
                List rawList = new ArrayList(); // raw type
                rawList.add(target.targetField);
            }
        }

        // No new exception (only pre-defined IllegalArgumentException)
    }
}

// Target class (public modifier, static nested class feature)
public class TargetClass {
    int targetField;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        void updateField(TargetClass target, int value) {
            target.targetField = value;
        }
    }

    // Instance method for instanceReference.methodName(arguments)
    public String instanceMethod(int num) {
        return String.valueOf(targetField + num);
    }
}