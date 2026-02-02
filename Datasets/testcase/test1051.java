package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

strictfp class SourceClass {
    // Source contains target class field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {}
    };

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {}

    // Method to be refactored (normal, List<String> return, private access, position: source)
    private List<String> targetMethod() {
        // Type declaration statement
        List<String> resultList = new ArrayList<>();
        
        // Access instance method (access_instance_method)
        String innerValue = targetField.getInnerClassValue();
        // Variable call
        resultList.add(targetField.innerClass.field);
        
        // ThrowStatement (private modifier, obj.field, 1, pos: source)
        try {
            if (resultList.isEmpty()) {
                private String objField = targetField.innerClass.field; // obj.field
                throw new IllegalStateException(objField + 1); // 1
            }
        } catch (IllegalStateException e) {
            // No new exception (no_new_exception)
            resultList.add("fallback");
        }

        // Switch statement with call_method (pos: switch)
        switch (innerValue) {
            case "test":
                // outerInstance.new InnerClass().methodName() (call_method target_feature)
                TargetClass outerInstance = new TargetClass();
                OtherClass.staticMethod(outerInstance.new InnerTargetClass().getValue());
                break;
            default:
                break;
        }

        return resultList;
    }
}

strictfp class TargetClass extends SuperTargetClass { // extends (target_feature)
    // Member inner class (target_feature)
    class InnerTargetClass {
        String getValue() {
            return "targetInnerValue";
        }
    }

    final InnerTargetClass innerClass = new InnerTargetClass();
    String field = "targetFieldValue";

    public String getInnerClassValue() { // Instance method for access
        return innerClass.getValue();
    }
}

// Super class for target_class extends feature
class SuperTargetClass {}

// Others_class (call_method type)
class OtherClass {
    // public modifier, static (call_method target_feature), return void
    public static void staticMethod(String param) {}
}