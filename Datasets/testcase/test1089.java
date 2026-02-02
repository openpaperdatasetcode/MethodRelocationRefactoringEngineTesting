package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.List;

// Source enum class (public modifier, same package, member inner class, anonymous inner class)
public enum SourceEnum {
    VALUE1, VALUE2;

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in enum");
        }
    };

    // Method to be refactored (instance, TargetEnum return, default access, position: source)
    TargetEnum targetMethod(TargetEnum.InnerTargetRecord param) { // per_condition: target parameter
        // Switch case
        switch (this) {
            case VALUE1:
                // Variable call
                String targetValue = param.value();
                // Access instance method
                int methodResult = TargetEnum.StaticNestedClass.processValue(targetValue);
                break;
            case VALUE2:
                methodResult = 0;
                break;
            default:
                methodResult = -1;
                break;
        }

        // Raw type usage
        List rawList = new java.util.ArrayList(); // raw_type feature
        rawList.add(param);

        // Override violation (enum's final methods cannot be overridden)
        @Override // Compile error: override_violation (name() is final in Enum)
        public final String name() {
            return super.name() + "_modified";
        }

        // Used by reflection
        try {
            Method method = SourceEnum.class.getDeclaredMethod("targetMethod", TargetEnum.InnerTargetRecord.class);
            method.setAccessible(true);
            method.invoke(this, param);
        } catch (Exception e) {
            // No new exception (no_new_exception)
            System.err.println("Reflection error: " + e.getMessage());
        }

        // No new exception (no_new_exception)
        return TargetEnum.VALUE_A;
    }
}

// Target enum class (private modifier, static nested class target_feature)
private enum TargetEnum {
    VALUE_A, VALUE_B;

    // Static nested class (target_feature)
    static class StaticNestedClass {
        public static int processValue(String value) {
            return value.length();
        }
    }

    // Inner target record (target_inner_rec)
    record InnerTargetRecord(String value) {}
}