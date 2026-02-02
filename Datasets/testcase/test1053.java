package com.refactoring.testcase;

import java.util.ArrayList;
import java.util.List;

strictfp class SourceClass {
    // Target class field in source class (per_condition)
    private TargetClass.InnerTargetClass targetField;

    static class InnerSourceClass1 {}

    static class InnerSourceClass2 {
        // Overriding method (type: overriding)
        @Override
        @SuppressWarnings("unused")
        private List<String> targetMethod() {
            // Type declaration statement
            List<String> resultList = new ArrayList<>();
            // Expression statement
            resultList.add("test");
            // Variable call
            String value = targetField.getValue();
            // For loop with call_method (pos: for)
            for (int i = 0; i < 5; i++) {
                // new ClassName().method() (call_method feature)
                String callResult = new OtherClass().callMethod();
                resultList.add(callResult);
                // Break statement
                if (i == 2) break;
            }
            // Return statement
            return resultList;
        }
    }
}

class TargetClass {
    // Local inner class (target_feature)
    class InnerTargetClass {
        private String value;

        // Constructor (call_method feature)
        public InnerTargetClass() {
            this.value = "default";
        }

        public String getValue() {
            return this.value;
        }
    }
}

// Others_class (call_method type: others_class)
class OtherClass {
    // default modifier (call_method modifier: default)
    String callMethod() {
        return "called";
    }
}