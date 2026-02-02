package com.refactor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface EnumMethodAnn {
    String value() default "enum_instance";
}

// Target enum class: public modifier, type parameter + static nested class features
public enum TargetEnum<T> {
    INSTANCE("default_3"); // 3 feature

    // this.field for ContinueStatement feature
    T field;

    // Static nested class (target_feature)
    public static class StaticNestedClass<T> {
        public static void modifyField(TargetEnum<T> target, T value) {
            target.field = value;
        }
    }

    // Constructor with type parameter
    TargetEnum(T field) {
        this.field = field;
    }

    // Getter/Setter for field
    public T getField() {
        return field;
    }

    public void setField(T field) {
        this.field = field;
    }
}

// Source enum class: private modifier, same package as target, static nested + local inner class
private enum SourceEnum {
    INSTANCE;

    // Static nested class feature
    private static class StaticNestedHelper {
        public static <T> void validateTarget(TargetEnum<T> target) {
            if (target.getField() == null) {
                target.setField((T) "static_3"); // 3 feature
            }
        }
    }

    // Method: instance, return Object, final access, in source class
    @EnumMethodAnn // has_annotation feature
    public final <T> Object processTarget(TargetEnum<T> targetParam) {
        // Variable call (target parameter)
        TargetEnum<T> target = targetParam;

        // ContinueStatement: private modifier, this.field + 3, pos: source
        private ContinueBlock: {
            for (int i = 0; i < 5; i++) {
                if (i == 3) { // 3 feature
                    target.setField((T) "continue_3"); // this.field + 3
                    continue; // ContinueStatement
                }
                // Expression statement
                target.setField((T) (target.getField() + "_expr_" + i));
            }
        }

        // If statement
        if (target.getField().equals("continue_3")) { // 3 feature
            // Local inner class feature
            class LocalInnerClass {
                public void processTarget(TargetEnum<T> t) {
                    t.setField((T) "local_3"); // 3 feature
                }
            }
            new LocalInnerClass().processTarget(target);
        }

        // Expression statement
        TargetEnum.StaticNestedClass.modifyField(target, (T) "expr_3"); // 3 feature

        // Use static nested helper
        StaticNestedHelper.validateTarget(target);

        // No new exception - wrap existing if any
        try {
            return target.getField();
        } catch (Exception e) {
            throw new RuntimeException(e); // No new exception instantiation
        }
    }
}