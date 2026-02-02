package com.example;

import java.util.List;
import java.util.ArrayList;

// Source enum class (default modifier, enum, same package, static nested class, member inner class)
enum SourceEnum {
    VALUE1, VALUE2;

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Member inner class (source_class feature)
    class MemberInnerClass {
        // Call method (inner_class, strictfp modifier, instance, obj.m1().m2().m3(), return List<String>)
        strictfp List<String> callMethod(TargetEnum param) throws Exception { // pos: exception throwing statements
            if (param == null) {
                throw new Exception("Null target enum");
            }
            // obj.m1().m2().m3() chain call
            return new ChainHelper().m1().m2().m3(param.getValue());
        }

        // Helper class for obj.m1().m2().m3()
        static class ChainHelper {
            ChainHelper m1() { return this; }
            ChainHelper m2() { return this; }
            List<String> m3(String val) {
                List<String> list = new ArrayList<>();
                list.add(val);
                return list;
            }
        }
    }

    // Method to be refactored (varargs, void return, protected access, position: source)
    protected void targetMethod(TargetEnum param, String... args) throws Exception { // per_condition: target parameter, requires_throws
        // ReturnStatement (private modifier, obj.field, 1, pos: source)
        private String returnBlock() {
            String objField = param.value; // obj.field
            int num = 1; // target_feature: 1
            if (num == 1) {
                return objField + num; // ReturnStatement
            }
            return "";
        }
        String returnVal = returnBlock();

        // Expression statement
        param.setValue(returnVal);

        // Lambda expression: () -> System.out.println(this.value)
        Runnable lambda = () -> System.out.println(this.name() + ": " + param.getValue());
        lambda.run();

        // Variable call
        for (String arg : args) {
            String targetValue = param.getValue() + arg;
            param.setValue(targetValue);
        }

        // Call method (inner_class)
        MemberInnerClass inner = new MemberInnerClass();
        inner.callMethod(param);
    }
}

// Target enum class (default modifier, enum, local inner class target_feature)
enum TargetEnum {
    TARGET1, TARGET2;

    String value = "targetValue";

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