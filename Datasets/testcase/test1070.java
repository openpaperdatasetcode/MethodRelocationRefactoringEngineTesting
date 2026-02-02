package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.Arrays;

protected class SourceClass {
    // Source contains target class field (per_condition)
    private TargetClass targetField = new TargetClass();
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "outerProtectedValue";

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {}

    // Method to be refactored (instance, void return, public access, position: source)
    @SuppressWarnings("unused")
    public void targetMethod() {
        // Local inner class (source_class feature)
        class LocalInnerClass {
            // TryStatement (private modifier, this.field, 3, pos: inner class)
            private void tryBlock() {
                String thisField = SourceClass.this.targetField.toString(); // this.field
                int num = 3; // target_feature 3
                try {
                    if (num > 0) throw new RuntimeException();
                } catch (Exception e) {
                    // No new exception (no_new_exception)
                }
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.tryBlock();

        // Expression statement
        targetField.innerClass.field = "updated";
        // Access outer protected field
        String outerValue = this.outerProtectedField;

        // Continue statement
        for (int i = 0; i < 5; i++) {
            if (i == 2) continue;
            // Variable call
            String targetValue = targetField.innerClass.getFieldValue();
        }

        // Array initialization with varargs method (pos: array initialization)
        String[] arr = new String[]{String.valueOf(varargsMethod(1, targetField, "arg1", "arg2"))}; // 1 in method_feature, target, varargs

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception e) {
            // No new exception (no_new_exception)
        }
    }

    // Varargs method (public modifier, void return, target, instanceReference.methodName(arguments))
    public void varargsMethod(int num, TargetClass target, String... args) { // 1 in method_feature, varargs
        // instanceReference.methodName(arguments)
        target.innerClass.setFieldValue(Arrays.toString(args)); // target in method_feature
    }
}

public class TargetClass {
    // Member inner class (target_feature)
    class InnerTargetClass {
        String field = "targetInnerField";

        String getFieldValue() {
            return field;
        }

        void setFieldValue(String value) {
            this.field = value;
        }
    }

    final InnerTargetClass innerClass = new InnerTargetClass();
}