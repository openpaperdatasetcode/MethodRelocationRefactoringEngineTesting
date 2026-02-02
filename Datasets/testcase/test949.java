package com.refactoring.movemethod;

// Source enum class (default modifier, same package)
enum SourceEnum {
    INSTANCE1, INSTANCE2;

    // Member inner class (source feature)
    class SourceMemberInner {}
    // Static nested class (source feature)
    static class SourceStaticNested {}

    // Target field reference (per_condition: source contains target field)
    private static TargetEnum.TargetField targetFieldRef = TargetEnum.TargetField.VALUE;

    // Method to test: static, return TargetEnum type, private access, source position
    private static TargetEnum processTarget(TargetEnum targetParam) {
        TargetEnum result = TargetEnum.DEFAULT;
        
        // Enhanced for statement
        for (TargetEnum enumVal : TargetEnum.values()) {
            // Variable call (target field/parameter)
            String fieldVal = enumVal.targetField;
            // Expression statement
            fieldVal = fieldVal + "_processed";
        }

        // For statement (pos for overloading method)
        for (int i = 0; i < 1; i++) {
            // Overloading method call (1, target, overloading, new ClassName().method())
            result = overloadedMethod(targetParam, 1);
        }

        // SwitchStatement (protected modifier, obj.field, 1, source pos)
        protectedSwitchStatement(targetParam);

        // No new exception (empty try-catch)
        try {
            // No exception thrown
        } catch (Exception e) {
            // Do nothing
        }

        return result;
    }

    // SwitchStatement with required features (protected modifier)
    protected static void protectedSwitchStatement(TargetEnum target) {
        // Switch on obj.field with value 1
        switch (target.getNumericField()) {
            case 1:
                target.targetField = "matched";
                break;
            default:
                break;
        }
    }

    // Overloading method (private modifier, return TargetEnum, for pos)
    private static TargetEnum overloadedMethod(TargetEnum target, int val) {
        // new ClassName().method()
        return new TargetEnum.LocalInnerHelper().getTargetInstance(target);
    }

    // Overloaded method (to satisfy overloading feature)
    private static TargetEnum overloadedMethod(TargetEnum target) {
        return target;
    }
}

// Target enum class (public modifier, extends Enum implicitly + local inner class)
public enum TargetEnum {
    DEFAULT, OPTION1, OPTION2;

    // Target field (per_condition: source contains this field)
    String targetField = "default_val";
    private int numericField = 1;

    // TargetField enum (nested for field reference)
    enum TargetField {
        VALUE
    }

    // Extends feature (enum implicitly extends java.lang.Enum)
    @Override
    public String toString() {
        return super.toString();
    }

    // Local inner class (target feature)
    class LocalInnerHelper {
        TargetEnum getTargetInstance(TargetEnum target) {
            return target;
        }
    }

    // Getter for numeric field (obj.field access)
    public int getNumericField() {
        return numericField;
    }
}