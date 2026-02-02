package com.refactoring.movemethod;

// Source class: record, public, same package as target, two static nested classes
public record SourceClass(String sourceField) {
    // Static field for depends_on_static_field feature
    private static int staticField = 42;

    // Per_condition: method contains target parameter (enforced in method signature)
    
    // First static nested class (source feature)
    static class FirstStaticNested {
        public static int processValue(int val) {
            return val * staticField;
        }
    }

    // Second static nested class (source feature)
    static class SecondStaticNested {
        public static int adjustValue(int val) {
            return val + staticField;
        }
    }

    // Method to refactor: varargs, base type (int) return, default access, in source record
    int methodToRefactor(TargetClass.TargetInner... targetParams) {
        int result = 0;

        // Constructor invocation (target inner class)
        TargetClass targetInstance = new TargetClass("target_data");
        TargetClass.TargetInner newInner = targetInstance.new TargetInner();

        // Variable call (target parameters and inner class)
        for (TargetClass.TargetInner param : targetParams) {
            result += param.innerValue();
            result += newInner.innerValue();
        }

        // Depends on static field
        result = FirstStaticNested.processValue(result);
        result = SecondStaticNested.adjustValue(result);

        // No new exception feature
        try {
            if (result < 0) {
                throw new IllegalArgumentException("Negative result");
            }
        } catch (IllegalArgumentException e) {
            // No throw new exception, only handle
            result = Math.abs(result);
        }

        return result; // Base type (int) return
    }
}

// Target class: record, private, same package as source, member inner class feature
private record TargetClass(String targetField) {
    // Target_inner (target inner class)
    public class TargetInner {
        private final int innerValue = 10;

        public int innerValue() {
            return innerValue;
        }
    }
}