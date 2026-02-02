package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Supporting annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorTestAnnotation {}

// Checked exception for requires_throws
class CustomCheckedException extends Exception {}

private class SourceClass<T extends Comparable<T>> {
    // Member inner class
    class SourceMemberInner {}
    // Static nested class
    static class SourceStaticNested<T> {}

    // Source inner class (method_position: source_inner)
    class SourceInnerClass {
        @RefactorTestAnnotation // has_annotation feature
        int processTarget(TargetClass<T> targetParam) throws CustomCheckedException {
            // Variable call (per_condition: contains target parameter)
            T targetData = targetParam.getTargetData();
            int result = 0;

            // Override_violation: Implement interface method but change return type (violation)
            TargetInterface invalidOverride = new TargetInterface() {
                @Override
                public void interfaceMethod() {} // Correct signature
                // Violation: Attempt to override with different return type (compile error if strict, simulates violation)
                public int interfaceMethod(int val) { return 0; }
            };

            // requires_throws: Declare checked exception (no new exception thrown)
            if (targetData.compareTo(targetParam.getDefaultData()) < 0) {
                // if/else conditions (call_method pos)
                if (targetData != null) {
                    // Call target's private method with constructor and lambda feature
                    String callResult = targetParam.privateTargetMethod(
                        new TargetClass.StaticNestedTarget<>(),
                        (param) -> "Processed: " + param
                    );
                    result = callResult.length();
                } else {
                    result = -1;
                }
            }

            return result;
        }
    }
}

// Target class (generic, default modifier)
class TargetClass<T extends Comparable<T>> {
    // Static nested class (target_feature)
    static class StaticNestedTarget<U> {}

    // Target field
    private T targetData;
    private final T defaultData;

    // Constructor
    public TargetClass(T defaultData) {
        this.defaultData = defaultData;
    }

    // Instance method for variable call
    public T getTargetData() {
        return targetData;
    }

    public T getDefaultData() {
        return defaultData;
    }

    // call_method: type=target, modifier=private, features=constructor + lambda, pos=if/else
    private String privateTargetMethod(StaticNestedTarget<T> nested, java.util.function.Function<T, String> func) {
        return func.apply(defaultData);
    }
}

// Supporting interface for override_violation
interface TargetInterface {
    void interfaceMethod();
}