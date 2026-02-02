package com.refactoring.test;

import java.util.List;

// Parent class for target class extends feature
class TargetParentClass<T> {
    protected T parentField;
}

// Target generic class (abstract modifier, extends feature)
abstract class TargetClass<T> extends TargetParentClass<T> {
    T targetField; // For per_condition (source contains this field)

    // call_method (public modifier, normal + instanceReference.methodName(arguments), pos=ternary operators, return String)
    public String targetMethod(T value) {
        return value == null ? "null" : value.toString();
    }

    // Abstract method (required for abstract class)
    public abstract T abstractMethod();
}

// Source generic class (private modifier, same package, static nested + member inner class)
private class SourceClass<T extends Number> {
    // Static nested class (source_feature)
    static class SourceStaticNested<U> {
        U nestedField;
    }

    // Member inner class (source_feature)
    class SourceMemberInner<U> {
        volatile int volatileField = 1; // For numbers:1, volatile modifier, FieldAccess
    }

    // Method to be refactored (instance, TargetClass Type return, strictfp access, source position)
    strictfp <U> TargetClass<U> moveMethod(TargetClass<U> targetParam) {
        // Per_condition: source contains the field of the target (access targetParam.targetField)
        if (targetParam == null) {
            // Constructor invocation
            TargetClass<U> newTarget = new TargetClass<U>() {
                @Override
                public U abstractMethod() {
                    return null;
                }
            };
            return newTarget;
        }

        // Break statement
        for (int i = 0; i < 5; i++) {
            if (i == 1) { // 1 from numbers feature
                break;
            }
            targetParam.targetField = (U) Integer.valueOf(i);
        }

        // Continue statement
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                continue;
            }
            sum += i;
        }
        targetParam.targetField = (U) Integer.valueOf(sum);

        // FieldAccess (numbers:1, volatile modifier, exp:FieldAccess)
        SourceMemberInner<U> inner = new SourceMemberInner<>();
        inner.volatileField = 1; // 1 from numbers, volatile modifier, FieldAccess
        targetParam.targetField = (U) Integer.valueOf(inner.volatileField);

        // Variable call
        U varCall = targetParam.targetField; // Access target field (per_condition)
        targetParam.targetField = (U) Integer.valueOf(varCall.hashCode() + 1);

        // Raw type feature
        List rawList = new java.util.ArrayList(); // Raw type (no generic)
        rawList.add(targetParam.targetField);

        // call_method pos=ternary operators
        String callResult = (varCall == null) ? targetParam.targetMethod((U) "default") : targetParam.targetMethod(varCall); // instanceReference.methodName(arguments)
        rawList.add(callResult);

        // Return statement
        return targetParam;

        // No new exception
    }
}