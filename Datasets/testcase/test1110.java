package com.example;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

// Source enum class (default modifier, enum, same package, member inner class, static nested class)
enum SourceEnum {
    VALUE1, VALUE2;

    // per_condition: source contains the field of the target
    private final TargetEnum targetField = TargetEnum.TARGET1;

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Member inner class (source_class feature)
    class MemberInnerClass {}

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String data) {
        // Method to be refactored (instance, List<String> return, public access)
        public List<String> targetMethod() {
            // Super constructor invocation (enum's implicit super)
            super();

            // Super keywords usage
            super.toString();

            // Type declaration statement
            TargetEnum targetInstance;
            List<String> resultList = new ArrayList<>();

            // Instance method call in expression (pos: expression)
            targetInstance = instanceMethod(2); // 2, others_class, instance, obj.m1().m2().m3()

            // Variable call
            String targetValue = targetInstance.innerClass.getValue();
            resultList.add(targetValue);

            // Raw type usage
            List rawList = new ArrayList(); // raw_type
            rawList.add(targetValue);

            // Functional interfaces with call_method (pos: functional interfaces)
            Consumer<TargetEnum> consumer = (t) -> new SubClass().callMethod(t); // sub_class, private, accessor, super.methodName(arguments)
            consumer.accept(targetInstance);

            // No new exception
            return new ArrayList<>(rawList);
        }

        // Instance method (public modifier, TargetEnum return, 2, others_class, instance)
        public TargetEnum instanceMethod(int num) {
            // obj.m1().m2().m3() chain call
            OthersClass helper = new OthersClass();
            return helper.m1().m2().m3(num, targetField);
        }
    }

    // Others class for obj.m1().m2().m3()
    static class OthersClass {
        OthersClass m1() { return this; }
        OthersClass m2() { return this; }
        TargetEnum m3(int num, TargetEnum target) {
            return target;
        }
    }

    // Sub class for call_method (sub_class)
    static class SubClass extends SuperClass {
        // Call method (private modifier, accessor, super.methodName(arguments), return void)
        private void callMethod(TargetEnum target) {
            // super.methodName(arguments)
            super.processTarget(target.innerClass); // accessor feature (call accessor method)
        }
    }

    // Super class for super.methodName(arguments)
    static class SuperClass {
        protected void processTarget(TargetEnum.InnerClass inner) {
            // Accessor method call
            inner.setValue("processed_" + inner.getValue());
        }
    }
}

// Target enum class (default modifier, enum, member inner class target_feature)
enum TargetEnum {
    TARGET1, TARGET2;

    // Member inner class (target_feature)
    public class InnerClass {
        private String value = "targetInnerValue";

        // Accessor methods
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }

    // Inner class instance for variable call
    public InnerClass innerClass = new InnerClass();
}