package com.refactoring.test;

import java.io.IOException;

// Target class (normal class, empty modifier, static nested class feature)
class TargetClass {
    String targetField; // For per_condition (source contains this field)
    int instanceField = 0; // For access_instance_field feature

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        static void writeField(String field) throws IOException {
            // Simulate IO operation that throws IOException
            if (field == null || field.isEmpty()) {
                throw new IOException("Field is null/empty (IO operation failed)");
            }
            System.out.println("IO write success: " + field);
        }
    }

    // Instance method to update instance field
    public void incrementInstanceField() {
        this.instanceField++;
    }
}

// Source abstract class (normal class, abstract modifier, same package, member inner + local inner class)
abstract class SourceClass {
    // Member inner class (source_feature)
    class SourceMemberInner {
        void validateTarget(TargetClass target) throws IOException {
            if (target.targetField == null) {
                throw new IOException("Target field is null (validation failed)");
            }
        }
    }

    // Method to be refactored (instance, void return, strictfp access, source position)
    strictfp void processTarget(TargetClass targetParam) {
        // Per_condition: source contains the field of the target
        if (targetParam == null) {
            targetParam = new TargetClass();
            targetParam.targetField = "default_target_field";
        }

        // Type declaration statement
        SourceMemberInner innerValidator = new SourceMemberInner();
        int loopCount = 0;

        // Do statement
        do {
            try {
                // Variable call (access target field - per_condition)
                String varCall = targetParam.targetField;
                
                // Access_instance_field feature
                targetParam.incrementInstanceField();
                varCall += "_instance_field_" + targetParam.instanceField;

                // IOException feature (call static nested class method that throws IOEx)
                TargetClass.TargetStaticNested.writeField(varCall);

                // Update target field
                targetParam.targetField = varCall + "_processed_" + loopCount;

                loopCount++;
            } catch (IOException e) {
                // Handle IOException gracefully (no_new_exception thrown)
                targetParam.targetField = "io_error_recovery_" + loopCount;
                targetParam.incrementInstanceField(); // Access instance field on error
                loopCount++;
            }
        } while (loopCount < 3); // Do-while loop termination condition

        // Local inner class (source_feature)
        class SourceLocalInner {
            void finalizeTarget(TargetClass target) {
                target.targetField += "_local_inner_processed";
                // Access instance field in local inner class
                target.instanceField = 10;
            }
        }

        // Use local inner class to process target
        new SourceLocalInner().finalizeTarget(targetParam);

        // No new exception thrown (all exceptions handled internally)
    }

    // Abstract method required for abstract class modifier
    public abstract void abstractMethod();
}