package com.refactoring.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Functional interface for target_class implements feature
interface TargetFunctionalInterface {
    String processField(String value);
}

// Parent class for target_class extends feature
class TargetParentClass {
    protected String parentField = "parent_default";
    protected String parentMethod(String input) {
        return input + "_parent_processed";
    }
}

// Target class (normal class, default modifier, extends + implements + local inner class)
class TargetClass extends TargetParentClass implements TargetFunctionalInterface {
    String targetField; // For per_condition (source contains this field)

    // Implements interface method (target_feature: implements)
    @Override
    public String processField(String value) {
        return value + "_impl_processed";
    }

    // Local inner class (target_feature)
    public void updateField(String suffix) {
        class TargetLocalInner {
            String combineField(String base) {
                return base + "_local_inner_" + suffix;
            }
        }
        this.targetField = new TargetLocalInner().combineField(this.targetField);
    }
}

// Source class (normal class, public modifier, same package, type parameter + local inner + static nested class)
public class SourceClass<T extends CharSequence> { // type parameter feature

    // Static nested class (source_feature)
    static class SourceStaticNested {
        static void validateField(String field) throws IllegalArgumentException {
            if (field == null || field.isEmpty()) {
                throw new IllegalArgumentException("Field is null/empty");
            }
        }
    }

    // Method to be refactored (instance, Object return, final access, source position)
    public final Object moveMethod(TargetClass targetParam) throws IllegalArgumentException { // requires_throws
        // Per_condition: source contains the field of the target
        if (targetParam == null) {
            throw new IllegalArgumentException("TargetClass parameter is null"); // throw statement
        }

        // If statement
        if (targetParam.targetField == null) {
            targetParam.targetField = "default_value"; // expression statement
        }

        // Variable call (access target field - per_condition)
        String varCall = targetParam.targetField;

        // Assert statement
        assert varCall != null && !varCall.isEmpty() : "Target field cannot be null/empty";

        // Expression statement (field modification)
        targetParam.targetField = targetParam.processField(varCall) + "_source_modified";

        // Local inner class (source_feature)
        class SourceLocalInner {
            String enhanceField(String input) {
                return input.toUpperCase() + "_local_inner_enhanced";
            }
        }
        targetParam.targetField = new SourceLocalInner().enhanceField(targetParam.targetField);

        // Used by reflection
        try {
            // Access target field via reflection
            Field targetField = TargetClass.class.getDeclaredField("targetField");
            targetField.setAccessible(true);
            String reflectVal = (String) targetField.get(targetParam);
            targetField.set(targetParam, reflectVal + "_reflection_modified");

            // Invoke target method via reflection
            Method updateMethod = TargetClass.class.getMethod("updateField", String.class);
            updateMethod.invoke(targetParam, "reflection_suffix");
        } catch (Exception e) {
            throw new IllegalArgumentException("Reflection operation failed", e); // throw statement + requires_throws
        }

        // Validate field via static nested class (triggers throw statement if invalid)
        SourceStaticNested.validateField(targetParam.targetField);

        // No new exception (only declared requires_throws)
        return targetParam.targetField;
    }
}