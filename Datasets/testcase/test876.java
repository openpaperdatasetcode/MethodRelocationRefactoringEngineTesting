package com.refactoring.test;

import java.sql.SQLException;
import java.util.function.Function;

// Source class (default modifier, same package, member inner + local inner class)
class SourceClass {
    // Member inner class (source feature)
    class SourceMemberInner {
        int processValue(int val) {
            return val * 2;
        }
    }

    // Method to be refactored (instance, TargetClass return, private access, source position)
    private TargetClass moveMethod(TargetClass targetParam) {
        // Per_condition: source contains target's field
        if (targetParam == null) {
            throw new IllegalArgumentException("Target parameter cannot be null"); // Exception throwing statements for call_method
        }
        int targetField = targetParam.targetField;

        // SQLException feature (declared, no new custom exception)
        try {
            if (targetField < 0) {
                throw new SQLException("Invalid target field value");
            }
        } catch (SQLException e) {
            // call_method in exception throwing statements (pos:exception throwing statements)
            Object callResult = targetParam.callMethod(targetField);
            targetParam.targetField = (int) callResult;
        }

        // Constructor invocation
        TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
        SourceMemberInner memberInner = new SourceMemberInner();

        // With bounds (generic with bounds)
        class BoundedGeneric<T extends Number & Comparable<T>> {
            T process(T val) {
                return val;
            }
        }
        BoundedGeneric<Integer> bounded = new BoundedGeneric<>();
        int boundedVal = bounded.process(targetField);

        // Variable call
        int varCall = memberInner.processValue(boundedVal);
        targetParam.targetField = varCall;

        // Local inner class (source feature)
        class LocalInnerClass {
            void updateTarget(TargetClass target) {
                target.targetField += 10;
            }
        }
        new LocalInnerClass().updateTarget(targetParam);

        // No new exception (only pre-defined SQLException/IllegalArgumentException)
        return targetParam;
    }
}

// Target class (protected modifier, static nested class feature)
protected class TargetClass {
    int targetField; // For variable call/access (per_condition)

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedField = 5;
    }

    // call_method (target type, public modifier, normal + superTypeReference.methodName(arguments))
    public Object callMethod(int param) {
        // SuperTypeReference.methodName(arguments)
        super.toString();
        return param * new TargetStaticNested().nestedField;
    }
}