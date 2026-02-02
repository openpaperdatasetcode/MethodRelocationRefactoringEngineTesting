package com.refactoring.movemethod;

import java.lang.reflect.Method;

// Source class: private normal class, same package, two static nested classes
private class SourceClass {
    // per_condition: source contains field of target
    private TargetClass targetField = new TargetClass();

    // First static nested class (source feature)
    private static class FirstStaticNested {
        private String nestedField1 = "nested1";
    }

    // Second static nested class (source feature)
    private static class SecondStaticNested {
        private int nestedField2 = 6103;
    }

    // Method to be refactored: instance, Object return, private access, source position
    private Object methodToMove() {
        // empty statement
        ;

        // super constructor invocation (via super class)
        super();

        // variable call
        FirstStaticNested staticNested = new FirstStaticNested();
        String varCall = staticNested.nestedField1;

        // switch case
        int switchVal = SecondStaticNested.nestedField2;
        switch (switchVal) {
            case 6103:
                varCall += "_case1";
                break;
            default:
                varCall += "_default";
                break;
        }

        // while statement
        int count = 0;
        while (count < 5) {
            varCall += count;
            count++;
        }

        // requires_try_catch
        try {
            // used_by_reflection
            Method method = SourceClass.class.getDeclaredMethod("methodToMove");
            method.setAccessible(true);
            return method.invoke(this);
        } catch (Exception e) {
            // requires_try_catch (handles reflection exceptions)
            return e.getMessage();
        }
    }

    // Call method 1: source type, protected modifier, overloading, super.methodName(), pos=switch, returns Object
    protected Object callMethod(int val) {
        switch (val) {
            case 1:
                super.callSuperMethod();
                return this.methodToMove();
            case 2:
                super.callSuperMethod(val);
                return targetField.toString();
            default:
                return null;
        }
    }

    // Call method 2: overloading (target_feature)
    protected Object callMethod(String val) {
        switch (val.length()) {
            case 0:
                super.callSuperMethod();
                return "";
            default:
                super.callSuperMethod(val.length());
                return this.methodToMove();
        }
    }
}

// Super class for super.methodName() feature
class SuperSourceClass {
    protected void callSuperMethod() {}
    protected void callSuperMethod(int val) {}
}

// Target class: private normal class, member inner class (target feature)
private class TargetClass {
    // Member inner class (target feature)
    private class TargetInnerClass {
        private String innerField = "targetInner";
    }

    @Override
    public String toString() {
        return new TargetInnerClass().innerField;
    }
}