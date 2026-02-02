package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Custom annotation for numbers:1 + exp:Annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface AccessorAnnotation {
    int value() default 1; // numbers:1
}

// Target class (normal class, public modifier, empty target_feature)
public class TargetClass {
    String targetField; // For per_condition (source contains this field)

    // Getter (accessor) for target field
    public String getTargetField() {
        return targetField;
    }

    // Setter (accessor) for target field
    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }
}

// Source class (normal class, default modifier, same package, static nested + member inner class)
class SourceClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        // Helper method with bounds for with_bounds feature
        public static <T extends CharSequence> String processField(T field) {
            return field.toString() + "_processed_" + 1; // numbers:1
        }
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // Accessor method to modify target field (with bounds)
        public <T extends CharSequence> void updateTargetField(TargetClass target, T value) {
            target.setTargetField(SourceStaticNested.processField(value));
        }
    }

    // Method to be refactored (accessor type, TargetClass return, public access, source position)
    @AccessorAnnotation(1) // numbers:1 + exp:Annotation (modifier: default)
    public <T extends CharSequence> TargetClass getProcessedTarget(TargetClass targetParam) { // with_bounds (T extends CharSequence)
        // Per_condition: source contains the field of the target
        if (targetParam == null) {
            targetParam = new TargetClass();
            targetParam.setTargetField("default_" + 1); // numbers:1
        }

        // Variable call (access target field - per_condition)
        String varCall = targetParam.getTargetField();

        // With_bounds usage (T extends CharSequence)
        T boundedValue = (T) (varCall + "_bounded_" + 1);
        new SourceMemberInner().updateTargetField(targetParam, boundedValue);

        // Used_by_reflection feature
        try {
            // Access target field via reflection
            Field field = TargetClass.class.getDeclaredField("targetField");
            field.setAccessible(true);
            String reflectVal = (String) field.get(targetParam);
            field.set(targetParam, reflectVal + "_reflection_" + 1);

            // Invoke accessor method via reflection
            Method setter = TargetClass.class.getMethod("setTargetField", String.class);
            setter.invoke(targetParam, (String) field.get(targetParam) + "_invoke_" + 1);
        } catch (Exception e) {
            // No new exception thrown (handle gracefully)
            targetParam.setTargetField("reflection_error_" + 1);
        }

        // No new exception
        return targetParam; // Return TargetClass Type (accessor return type)
    }
}