package com.example;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomMethodAnnotation {}

// Source normal class (default modifier, same package, anonymous inner class, static nested class)
class SourceClass {
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();
    // Private outer field for access_outer_private
    private String outerPrivateField = "sourcePrivateField_5666";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {
        void helperMethod() {}
    }

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in SourceClass");
        }
    };

    // Method to be refactored (instance, List<String> return, protected access, position: source)
    @CustomMethodAnnotation // has_annotation feature
    protected List<String> targetMethod() {
        // Variable call (target class member inner class access)
        TargetClass.MemberInnerClass innerTarget = targetField.new MemberInnerClass();
        String targetValue = innerTarget.getValue();

        // Access outer private field
        String privateValue = this.outerPrivateField; // access_outer_private

        // Switch statement
        int switchCase = targetValue.length();
        String switchResult = "";
        switch (switchCase) {
            case 5:
                switchResult = "case_5";
                break;
            case 10:
                switchResult = "case_10";
                break;
            default:
                switchResult = "default_case";
                break;
        }

        // While statement
        int count = 0;
        List<String> resultList = new ArrayList<>();
        while (count < 3) {
            resultList.add(targetValue + "_" + count + "_" + switchResult);
            count++;
        }

        // Overload exist (call overloaded method)
        overloadMethod(1);
        overloadMethod(privateValue);

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception e) {
            // No new exception (only reuse existing exception types)
            resultList.add("reflection_error: " + e.getMessage());
        }

        // No new exception (no custom exception instantiation)
        return resultList;
    }

    // Overloading method 1 (overload_exist feature)
    private void overloadMethod(int num) {
        targetField.setValue(targetField.getValue() + "_num_" + num);
    }

    // Overloading method 2 (overload_exist feature)
    private void overloadMethod(String str) {
        targetField.setValue(targetField.getValue() + "_str_" + str);
    }
}

// Target normal class (default modifier, member inner class target_feature)
class TargetClass {
    private String value = "targetValue_5666";

    // Member inner class (target_feature)
    public class MemberInnerClass {
        public String getValue() {
            return TargetClass.this.value;
        }
    }

    // Getter/Setter for variable call
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}