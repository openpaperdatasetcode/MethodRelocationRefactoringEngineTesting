package com.refactor;

import java.lang.reflect.Method;

// Source class: record, public, same package as target, two member inner classes
public record SourceRecord(String id, long value) {
    // Target class field reference (per_condition)
    private final TargetRecord<String> targetField = new TargetRecord<>("targetId", "targetValue");

    // First member inner class (source feature)
    class SourceInner1 {}

    // Second member inner class (source_inner_rec - method position)
    class SourceInner2 {
        // Method to refactor: instance, void return, default modifier, in source_inner_rec
        void methodToMove() {
            // Constructor invocation
            TargetRecord<String> targetInstance = new TargetRecord<>("newId", "newValue");

            // Try statement
            try {
                // Used by reflection
                Method method = SourceInner2.class.getDeclaredMethod("methodToMove");
                method.setAccessible(true);
                method.invoke(this);

                // Variable call (target field access)
                String targetVar = targetField.value();
                TargetRecord.TargetInner innerInstance = targetField.new TargetInner();
                innerInstance.innerMethod();
            } catch (Exception e) {
                // No new exception thrown
            }

            // Call method: inner_class, default modifier, instance, super.methodName(arguments) in instance code blocks
            TargetRecord.TargetInner callInnerInstance = new TargetRecord.TargetInner() {
                {
                    // Instance code block (pos: instance code blocks)
                    super.innerMethod();
                }
            };
        }
    }
}

// Target class: record, default modifier, type parameter + member inner class (target_feature)
record TargetRecord<T>(String id, T value) {
    // Member inner class (target_feature)
    class TargetInner {
        // Call method: default modifier, instance, super.methodName(arguments)
        Object innerMethod() {
            return super.toString();
        }
    }
}