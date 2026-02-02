package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Interface for target class implements feature
interface TargetInterface<T> {
    T getFieldValue();
}

// Target class (normal class, protected modifier, type parameter + implements + local inner class)
protected class TargetClass<T> implements TargetInterface<T> {
    T targetField; // For per_condition (source contains this field)

    // Implements interface method
    @Override
    public T getFieldValue() {
        return this.targetField;
    }

    // Local inner class (target_feature)
    class TargetLocalInner {
        void updateField(T value) {
            TargetClass.this.targetField = value;
        }
    }
}

// Source class (normal class, private modifier, same package, member inner + local inner class)
private class SourceClass {
    // Member inner class (source_feature)
    class SourceMemberInner {
        T processValue(T value) {
            return value;
        }
    }

    // Method to be refactored (normal, Object return, protected access, source position)
    @RefactorAnnotation // has_annotation feature
    protected Object moveMethod(TargetClass<String> targetParam) {
        // Per_condition: source contains the field of the target
        if (targetParam == null) {
            return null;
        }
        String targetField = targetParam.targetField; // Access target field

        // ExpressionStatement (protected modifier, this.field, 1, pos=source)
        protected void expressionStatement() {
            targetParam.targetField = "value_" + 1; // this.field (targetParam.field) + 1 from target_feature
        }
        expressionStatement();

        // Variable call
        String varCall = targetParam.getFieldValue(); // Variable call to target field
        targetParam.targetField = varCall + "_modified";

        // Local inner class (source_feature)
        class LocalInnerClass {
            Object modifyTarget(TargetClass<String> target) {
                target.new TargetLocalInner().updateField(target.targetField + "_local");
                return target.targetField;
            }
        }
        Object modified = new LocalInnerClass().modifyTarget(targetParam);

        // No new exception
        return modified;
    }
}