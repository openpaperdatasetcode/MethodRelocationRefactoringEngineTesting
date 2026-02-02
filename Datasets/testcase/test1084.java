package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Source class (strictfp modifier, normal class, same package, member inner class, static nested class)
strictfp class SourceClass {
    // Member inner class (source_class feature)
    class MemberInnerSourceClass {
        // SuperConstructorInvocation usage (protected modifier, ClassName.field, 1, pos: inner class)
        protected MemberInnerSourceClass() {
            super(); // SuperConstructorInvocation
            String classNameField = TargetClass.STATIC_FIELD; // ClassName.field
            int num = 1; // target_feature 1
            System.out.println(classNameField + num);
        }
    }

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass<T extends CharSequence> {}

    // Method to be refactored (varargs, TargetClass return, private access, position: source)
    private TargetClass targetMethod(TargetClass param, String... args) { // per_condition: target parameter
        // Constructor invocation
        TargetClass newTarget = new TargetClass();
        MemberInnerSourceClass innerInstance = new MemberInnerSourceClass();

        // With_bounds (generic bounds)
        List<? extends String> boundedList = new ArrayList<>();
        for (String arg : args) {
            boundedList.add(arg);
        }

        // Variable call
        String targetValue = param.getValue();
        // Expression statement
        param.updateValue(targetValue + "_updated");

        // Access instance method
        int methodResult = param.calculateLength(targetValue);

        // Overload exist feature (overloaded methods)
        overloadMethod(1);
        overloadMethod(1, "suffix");

        // No new exception (no_new_exception)
        return newTarget;
    }

    // Overload method 1 (overload_exist feature)
    private int overloadMethod(int num) {
        return num * 2;
    }

    // Overload method 2 (overload_exist feature)
    private int overloadMethod(int num, String str) {
        return num + str.length();
    }
}

// Target class (public modifier, normal class, javadoc target_feature)
/**
 * TargetClass with Javadoc (target_feature)
 * This class is used as the target for the refactoring method
 */
public class TargetClass {
    // ClassName.field for SuperConstructorInvocation
    public static final String STATIC_FIELD = "targetStaticField"; // ClassName.field
    private String value = "targetValue";

    public String getValue() {
        return value;
    }

    public void updateValue(String newValue) {
        this.value = newValue;
    }

    public int calculateLength(String str) {
        return str.length();
    }
}