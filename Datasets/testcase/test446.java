package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source class: generic class, protected modifier, same package as target
// Features: static nested class (duplicate as specified)
protected class SourceClass<T extends CharSequence> {
    // Per_condition: source contains the field of the target
    private TargetClass<String> targetField = new TargetClass<>();
    
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "protectedOuterValue";
    // Instance field for access_instance_field feature
    private int instanceField = 50;
    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "staticSourceField";

    // Static nested class (source_class feature - first occurrence)
    public static class FirstStaticNestedClass {
        public static <E> E getValue(E input) {
            return input;
        }
    }

    // Static nested class (source_class feature - duplicate occurrence)
    public static class SecondStaticNestedClass {
        public static String process(String input) {
            return input.toUpperCase();
        }
    }

    // Varargs method (type: varargs), return TargetClass Type, final access, position: source
    final TargetClass<String> moveCandidateMethod(T... varargsParams) {
        // Variable call (method feature)
        String varCall = FirstStaticNestedClass.getValue(STATIC_FIELD);
        
        // Access outer protected (method feature)
        String protectedAccess = this.outerProtectedField + varCall;
        
        // Access instance field (method feature)
        int instanceValue = this.instanceField;
        
        // Depends on static field (method feature)
        String staticFieldValue = SecondStaticNestedClass.process(STATIC_FIELD);
        
        // Raw type (method feature)
        List rawList = new ArrayList();
        rawList.add(protectedAccess);
        rawList.add(staticFieldValue);
        
        // No new exception (method feature - no throw new Exception)
        try {
            // Access target_inner (target class inner member)
            TargetClass<String>.MemberInnerClass targetInner = targetField.new MemberInnerClass();
            targetInner.setData(protectedAccess + instanceValue);
            
            // Call others_class method in array initialization (call_method pos: array initialization)
            String[] arr = {
                CallerClass.callMethod(targetField, staticFieldValue.length())
            };
            targetInner.appendData(arr[0]);
        } catch (Exception e) {
            // No new exception instantiation
            targetField.setDefaultValue(e.getMessage());
        }
        
        // Variable call for target field
        targetField.setData(protectedAccess + STATIC_FIELD + instanceValue);
        return targetField;
    }
}

// Target class: generic class, no modifier, target_feature: member inner class
class TargetClass<U> {
    private String data;

    // Member inner class (target_feature)
    public class MemberInnerClass {
        private String innerData;

        public void setData(String input) {
            this.innerData = input;
        }

        public void appendData(String input) {
            this.innerData += input;
        }

        public String getInnerData() {
            return this.innerData;
        }
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDefaultValue(String defaultValue) {
        this.data = defaultValue;
    }

    public String getData() {
        return this.data;
    }
}

// Call method class: others_class, protected modifier, target_feature: normal, super.methodName(arguments)
// pos: array initialization, return_type: String
class CallerClass extends CallerParent {
    protected static String callMethod(TargetClass<String> target, int count) {
        // Array initialization (pos)
        String[] tempArr = new String[count];
        for (int i = 0; i < count; i++) {
            tempArr[i] = target.getData() + i;
        }
        
        // super.methodName(arguments) (target_feature)
        String superResult = super.parentProcess(tempArr);
        
        // Normal method feature (target_feature)
        return processResult(superResult);
    }

    private static String processResult(String input) {
        return input != null ? input : "defaultResult";
    }
}

// Parent class for super.methodName() feature
class CallerParent {
    protected static String parentProcess(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }
}