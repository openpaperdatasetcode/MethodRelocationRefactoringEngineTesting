package com.example;

import java.util.List;
import java.util.ArrayList;

// Source class (protected modifier, normal class, same package, member inner class, anonymous inner class)
protected class SourceClass {
    // per_condition: source contains the field of the target
    private TargetClass targetField = new TargetClass();

    // Member inner class (source_class feature) - method_position: source_inner
    class InnerSourceClass {
        // Overloading method 1 (overloading type, TargetClass return, protected access)
        protected TargetClass targetMethod() {
            return targetMethod("default");
        }

        // Overloading method 2 (overloading feature)
        protected TargetClass targetMethod(String suffix) {
            // Super constructor invocation
            super();

            // Type declaration statement
            List<String> resultList;
            String targetValue;

            // Property assignment with instance method call (pos: property assignment)
            resultList = instanceMethod(1); // 1, source, instance, new ClassName().method()

            // Variable call
            targetValue = targetField.getValue();

            // Expression statement
            targetField.setValue(targetValue + suffix);

            // No new exception
            return targetField;
        }

        // Instance method (default modifier, List<String> return, 1, source, instance)
        List<String> instanceMethod(int num) { // 1 in method_feature, instance type
            // new ClassName().method() (method_feature)
            return new HelperClass().process(num); // source feature
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            InnerSourceClass inner = new InnerSourceClass();
            inner.targetMethod();
        }
    };

    // Helper class for new ClassName().method()
    class HelperClass {
        List<String> process(int num) {
            List<String> list = new ArrayList<>();
            list.add("processed_" + num);
            return list;
        }
    }
}

// Target class (protected modifier, normal class, local inner class target_feature)
protected class TargetClass {
    private String value = "targetValue";

    public String getValue() {
        // Local inner class (target_feature)
        class LocalInnerClass {
            String formatValue(String val) {
                return val.trim();
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        return local.formatValue(value);
    }

    public void setValue(String value) {
        this.value = value;
    }
}