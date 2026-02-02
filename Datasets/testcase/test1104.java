package com.example;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

// Source enum class (sealed modifier, enum, same package, static nested class, member inner class)
sealed enum SourceEnum permits SourceSubEnum {
    VALUE1, VALUE2;

    // per_condition: source contains the field of the target
    private final TargetEnum targetField = TargetEnum.TARGET1;

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Member inner class (source_class feature)
    class MemberInnerClass {}

    // Method to be refactored (varargs, TargetEnum return, private access, position: source)
    @CustomAnnotation // has_annotation feature
    private TargetEnum targetMethod(String... args) {
        // ConstructorInvocation (private modifier, obj.field, 1, pos: source)
        private void constructorInvocBlock() {
            TargetEnum.InnerClass innerObj = targetField.new InnerClass();
            String objField = innerObj.field; // obj.field
            TargetEnum temp = TargetEnum.TARGET1; // 1 in target_feature (ordinal 0, simulate with 1)
        }
        constructorInvocBlock();

        // Lambda expression: () -> System.out.println(this.value)
        Runnable lambda = () -> System.out.println(this.name() + ": " + targetField.value);
        lambda.run();

        // Variable call
        String targetValue = targetField.getValue();
        for (String arg : args) {
            targetValue += arg;
        }

        // Overload exist (call overloading method)
        overloadMethod(1);
        overloadMethod("suffix");

        // Call method in Lambda expressions (pos: Lambda expressions)
        java.util.function.Supplier<List<String>> supplier = () -> callMethod(targetField, 1); // instanceReference.methodName(arguments)
        supplier.get();

        // No new exception
        return targetField;
    }

    // Overloading method 1 (overload_exist feature)
    private int overloadMethod(int num) {
        return num * 2;
    }

    // Overloading method 2 (overload_exist feature)
    private int overloadMethod(String str) {
        return str.length();
    }

    // Call method (source, protected modifier, instance, instanceReference.methodName(arguments), return List<String>)
    protected List<String> callMethod(TargetEnum instanceRef, int arg) {
        List<String> list = new ArrayList<>();
        list.add(instanceRef.getValue() + "_" + arg);
        return list;
    }
}

// Permitted subclass for sealed source enum
final enum SourceSubEnum extends SourceEnum {}

// Target enum class (public modifier, enum, member inner class target_feature)
public enum TargetEnum {
    TARGET1, TARGET2;

    String value = "targetValue";

    // Member inner class (target_feature)
    class InnerClass {
        String field = "innerFieldValue";
    }

    public String getValue() {
        return value;
    }
}