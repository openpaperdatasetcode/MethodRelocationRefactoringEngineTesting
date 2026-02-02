package com.refactoring.test;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

/**
 * Source enum class implementation meeting all specified requirements
 */
public enum SourceEnum {
    INSTANCE;

    private String outerField = "sourceOuterValue"; // For uses_outer_this

    // Method to be refactored (instance, TargetEnum return, public access, source position)
    public TargetEnum moveMethod(TargetEnum targetParam) throws ReflectiveOperationException {
        // Per_condition: source contains target's field
        String targetField = targetParam.targetField;

        // Super constructor invocation (Object superclass)
        Object superObj = new Object();

        // Inner class for VariableDeclarationStatement pos=inner class
        class InnerClassForVariableDecl {
            // VariableDeclarationStatement (transient modifier, obj.field, 1, pos=inner class)
            transient int transientField = targetParam.targetField.length() + 1; // obj.field + 1 from target_feature
        }
        InnerClassForVariableDecl innerVarClass = new InnerClassForVariableDecl();

        // Type declaration statement
        LocalInnerProcessor processor = new LocalInnerProcessor();
        processor.processTarget(targetParam);

        // Variable call + uses_outer_this
        String varCall = SourceEnum.this.outerField;
        targetParam.targetField = targetField + "_" + varCall + "_" + innerVarClass.transientField;

        // Used by reflection
        try {
            Field targetFieldRef = TargetEnum.class.getDeclaredField("targetField");
            targetFieldRef.setAccessible(true);
            targetFieldRef.set(targetParam, "reflected_" + targetParam.targetField);

            Method targetMethodRef = TargetEnum.class.getDeclaredMethod("getTargetField");
            targetMethodRef.setAccessible(true);
            String reflectedVal = (String) targetMethodRef.invoke(targetParam);
            targetParam.targetField = reflectedVal;
        } catch (ReflectiveOperationException e) {
            throw e; // requires_throws
        }

        // No new exception beyond declared ReflectiveOperationException
        return targetParam;
    }

    // Static code block for accessor method pos=Static code blocks
    static {
        // Accessor method (type:accessor, modifier:private, method_feature:1/inner_class/accessor/super.methodName())
        class StaticBlockInner {
            private int getTargetFieldValue(TargetEnum target) {
                super.toString(); // super.methodName()
                return target.targetField.length() + 1; // 1 from method_feature
            }
        }
        StaticBlockInner staticInner = new StaticBlockInner();
        staticInner.getTargetFieldValue(TargetEnum.VALUE1);
    }

    // Local inner class (source feature)
    class LocalInnerProcessor {
        void processTarget(TargetEnum target) {
            target.targetField = "processed_" + target.targetField;
        }
    }

    // Anonymous inner class (source feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(SourceEnum.this.outerField); // uses_outer_this
        }
    };
}

/**
 * Javadoc feature for TargetEnum (target_feature)
 * This enum includes static nested class and target field for per_condition
 */
enum TargetEnum {
    VALUE1("value1"),
    VALUE2("value2");

    String targetField; // For variable call/access (per_condition)

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static void updateField(TargetEnum target, String value) {
            target.targetField = value;
        }
    }

    TargetEnum(String targetField) {
        this.targetField = targetField;
    }

    // Accessor method (getter) for accessor feature
    public String getTargetField() {
        return this.targetField;
    }
}