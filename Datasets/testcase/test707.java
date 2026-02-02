package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;

protected class SourceClass {
    protected String outerProtectedField = "outerValue";

    // First member inner class
    class FirstInnerClass {}

    // Second member inner class (source_inner_rec)
    class SecondInnerClass {
        private String innerVariable = "innerVar";

        protected List<String> methodToRefactor(TargetClass.InnerTargetClass targetParam) {
            // Type declaration statement
            List<String> resultList = new ArrayList<>();
            // Constructor invocation
            FirstInnerClass innerInstance = new FirstInnerClass();
            // Variable call
            resultList.add(innerVariable);
            // Access outer protected member
            resultList.add(SourceClass.this.outerProtectedField);
            // Access instance method
            resultList.add(SourceClass.this.instanceMethod());
            // Return statement
            resultList.add(targetParam.getTargetValue());
            return resultList;
        }
    }

    protected String instanceMethod() {
        return "instanceMethodValue";
    }

    // call_method: synchronized, source type, in switch, overloading, void return
    public synchronized void callMethod(int option) {
        switch (option) {
            case 1:
                new SecondInnerClass().methodToRefactor(new TargetClass.InnerTargetClass());
                break;
            case 2:
                callMethod("stringParam");
                break;
            default:
                break;
        }
    }

    // Overloading feature for call_method
    public synchronized void callMethod(String param) {
        SourceClass outerInstance = new SourceClass();
        // outerInstance.new InnerClass().methodName() feature
        outerInstance.new SecondInnerClass().methodToRefactor(new TargetClass.InnerTargetClass());
        // Used by reflection feature
        try {
            Method method = SecondInnerClass.class.getDeclaredMethod("methodToRefactor", TargetClass.InnerTargetClass.class);
            method.setAccessible(true);
            method.invoke(outerInstance.new SecondInnerClass(), new TargetClass.InnerTargetClass());
        } catch (Exception e) {
            // No new exception feature (only catch, no throw new)
        }
    }
}

private class TargetClass {
    // target_inner (target class for the method)
    class InnerTargetClass {
        private String targetValue = "targetValue";

        public String getTargetValue() {
            return targetValue;
        }
    }
}