package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Source enum class (private modifier, same package, member inner + static nested class)
private enum SourceEnum {
    INSTANCE;

    protected String outerProtectedField = "protectedSourceValue"; // For access_outer_protected
    private int instanceField = 100; // For access_instance_field

    // Static nested class (source feature)
    static class SourceStaticNested {
        String nestedField = "staticNestedValue";
    }

    // Member inner class (source feature)
    class SourceMemberInner {
        // Inner class for method_position:source_inner
        class SourceInnerClass {
            // Method to be refactored (instance, Object return, default access)
            @RefactorAnnotation // has_annotation feature
            Object moveMethod(TargetEnum targetParam) {
                // Per_condition: contains target parameter
                if (targetParam == null) {
                    throw new NullPointerException("Target parameter cannot be null"); // NullPointerException feature
                }

                // Type declaration statement
                SourceStaticNested staticNested = new SourceStaticNested();
                TargetEnum.TargetMemberInner targetInner = targetParam.new TargetMemberInner();

                // Access outer protected field
                String protectedVal = SourceEnum.this.outerProtectedField;

                // Access instance field
                int instanceVal = SourceEnum.INSTANCE.instanceField;

                // Variable call
                String targetField = targetParam.targetField;
                targetParam.targetField = targetField + "_" + protectedVal + "_" + instanceVal;

                // Used by reflection
                try {
                    // Access target field via reflection
                    Field targetFieldRef = TargetEnum.class.getDeclaredField("targetField");
                    targetFieldRef.setAccessible(true);
                    targetFieldRef.set(targetParam, "reflected_" + targetParam.targetField);

                    // Access target inner class method via reflection
                    Method innerMethod = TargetEnum.TargetMemberInner.class.getMethod("modifyField", String.class);
                    innerMethod.invoke(targetInner, targetParam.targetField);
                } catch (Exception e) {
                    // No new exception (handle reflection exceptions internally)
                }

                // No new exception (only pre-defined NullPointerException)
                return targetParam.targetField;
            }
        }
    }
}

// Target enum class (default modifier, member inner class feature)
enum TargetEnum {
    VALUE1, VALUE2;

    String targetField = "defaultTargetValue"; // For variable call/access

    // Member inner class (target_feature)
    class TargetMemberInner {
        void modifyField(String input) {
            TargetEnum.this.targetField = input + "_modifiedByInner";
        }
    }
}