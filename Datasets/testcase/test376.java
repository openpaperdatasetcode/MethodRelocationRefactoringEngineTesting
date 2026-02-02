package com.refactoring.movemethod;

import java.util.function.Consumer;

// Functional interface for target enum implements feature
interface EnumProcessable {
    void process(String data);
}

// Custom annotation for has_annotation feature
@interface EnumRefactorAnnotation {}

// Source enum class (public, same package, extends feature - enums implicitly extend Enum)
public enum SourceClass {
    SOURCE_1(1), SOURCE_2(2);

    private final int value;

    SourceClass(int value) {
        this.value = value;
    }

    // Overload exist feature (overloaded methods)
    public int getValue() {
        return value;
    }

    public int getValue(String suffix) {
        return value + suffix.length();
    }

    // Instance method to refactor (default access, return Object, source position)
    @EnumRefactorAnnotation // has_annotation feature
    Object refactorMethod(TargetClass targetParam) {
        // Variable call feature
        int localVar = this.getValue();
        localVar = this.getValue("suffix"); // overload_exist feature

        // PostfixExpression (private modifier, numbers=2)
        int postfixVar = 2; // numbers=2
        localVar = postfixVar++; // PostfixExpression (private modifier context)

        // Requires try-catch feature
        Object result;
        try {
            // Process target parameter (per_condition: method has target parameter)
            result = targetParam.process(localVar);
            // Access target's static nested class
            result = TargetClass.TargetStaticNested.formatResult(result.toString());
        } catch (NullPointerException e) {
            // No new exception thrown, handle existing
            result = "default_" + localVar;
        }

        return result;
    }
}

// Target enum class (private modifier, implements + static nested class feature)
private enum TargetClass implements EnumProcessable {
    TARGET_1("A"), TARGET_2("B");

    private final String data;

    TargetClass(String data) {
        this.data = data;
    }

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static String formatResult(String input) {
            return input + "_formatted";
        }
    }

    // Implement EnumProcessable interface (implements feature)
    @Override
    public void process(String data) {
        System.out.println(this.data + ":" + data);
    }

    public Object process(int num) {
        return this.data + "_" + num;
    }
}