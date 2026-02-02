// Target class package (different from source)
package com.refactoring.target;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Target record class (public modifier, static nested class feature)
public record TargetClass(String targetField) {
    // Static field for ThrowStatement ClassName.field + 1
    public static final int STATIC_FIELD = 1;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        static void validateField(String field) {
            if (field == null || field.isEmpty()) {
                throw new IllegalArgumentException("Field invalid: " + STATIC_FIELD);
            }
        }
    }

    // Compact constructor for validation
    public TargetClass {
        TargetStaticNested.validateField(targetField);
    }
}

// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.target.RefactorAnnotation;

// Functional interface for source_class implements feature
interface SourceInterface {
    void processTarget(TargetClass target) throws IllegalArgumentException;
}

// Source record class (public modifier, different package, implements + two static nested classes)
@RefactorAnnotation // has_annotation feature
public record SourceClass(String sourceField) implements SourceInterface {
    // First static nested class (source_feature)
    static class SourceStaticNestedOne {
        // ThrowStatement (private modifier, ClassName.field, 1, pos=source)
        private static void throwStatement(TargetClass target) {
            if (target.targetField() == null) {
                // ClassName.field + 1 from target_feature
                throw new IllegalArgumentException("Target field null: " + TargetClass.STATIC_FIELD);
            }
        }
    }

    // Second static nested class (source_feature)
    static class SourceStaticNestedTwo {
        static void updateField(TargetClass target, String suffix) {
            // Variable call (access target field via record accessor)
            String newField = target.targetField() + "_modified_" + TargetClass.STATIC_FIELD;
            // Reconstruct target record (immutable)
            new TargetClass(newField);
        }
    }

    // Method to be refactored (normal, void return, protected access, source position)
    @Override
    @RefactorAnnotation // has_annotation feature
    protected void processTarget(TargetClass targetParam) throws IllegalArgumentException { // requires_throws
        // Per_condition: contains target parameter
        if (targetParam == null) {
            // ThrowStatement invocation (pos=source)
            SourceStaticNestedOne.throwStatement(targetParam);
            return;
        }

        // Variable call (access target field - per_condition)
        String varCall = targetParam.targetField();
        if (varCall.length() < TargetClass.STATIC_FIELD) { // 1 from target_feature
            // ThrowStatement (private modifier, ClassName.field, 1)
            SourceStaticNestedOne.throwStatement(targetParam);
        }

        // Use static nested class to update field
        SourceStaticNestedTwo.updateField(targetParam, "_source_processed");

        // Requires throws (declared exception, no new exception thrown)
    }
}