package com.example;

import java.lang.reflect.Method;

// Source class (default modifier, normal class, same package, two member inner classes)
class SourceClass {
    // per_condition: source contains the field of the target
    private TargetClass targetField = new TargetClass();

    // First member inner class (source_class feature)
    class MemberInnerClass1 {}

    // Second member inner class (source_class feature)
    class MemberInnerClass2 {}

    // Method to be refactored (instance, TargetClass return, protected access, position: source)
    protected TargetClass targetMethod() {
        // Variable call
        String targetValue = targetField.getValue();

        // NullPointerException
        if (targetValue == null) {
            throw new NullPointerException("Target value is null");
        }

        // Varargs method call in exception throwing statements (pos: exception throwing statements)
        try {
            varargsMethod(1, "arg1", "arg2"); // 1, target, varargs, this.methodName(arguments)
        } catch (IllegalArgumentException e) {
            // No new exception
            System.err.println("Exception: " + e.getMessage());
        }

        // While statement
        int count = 0;
        while (count < 3) {
            // Expression statement
            targetField.setValue(targetValue + "_" + count);
            count++;
        }

        // Depends on static field
        String staticFieldValue = TargetClass.STATIC_FIELD;
        targetField.setValue(staticFieldValue);

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception e) {
            // No new exception
            System.err.println("Reflection error: " + e.getMessage());
        }

        // No new exception
        return targetField;
    }

    // Varargs method (default modifier, void return, 1, target, varargs)
    void varargsMethod(int num, String... args) { // this.methodName(arguments)
        if (num != 1) {
            throw new IllegalArgumentException("Invalid number: " + num);
        }
        for (String arg : args) {
            targetField.setValue(targetField.getValue() + arg);
        }
    }
}

// Target class (sealed modifier, normal class, extends, local inner class target_feature)
sealed class TargetClass extends SuperTargetClass permits TargetSubClass {
    // Static field for depends_on_static_field
    public static final String STATIC_FIELD = "TargetStaticField";

    private String value = "targetValue";

    public String getValue() {
        // Local inner class (target_feature)
        class LocalInnerClass {
            String format(String val) {
                return val.trim();
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        return local.format(value);
    }

    public void setValue(String value) {
        this.value = value;
    }
}

// Super class for target_class extends feature
class SuperTargetClass {}

// Permitted subclass for sealed target class
final class TargetSubClass extends TargetClass {}