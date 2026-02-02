package com.example;

import java.util.ArrayList;
import java.util.List;

// Source class (protected modifier, normal class, same package, two static nested classes)
protected class SourceClass {
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();

    // First static nested class (source_class feature)
    static class StaticNestedClass1 {}

    // Second static nested class (source_class feature)
    static class StaticNestedClass2 {}

    // Method to be refactored (instance, Object return, public access, position: source)
    public Object targetMethod() throws Exception { // requires_throws
        // Variable call (target_inner_rec)
        TargetClass.InnerRecord innerRec = targetField.new InnerRecord("initValue");
        String targetValue = innerRec.value();

        // Uses outer this (outer class instance reference)
        SourceClass outerThis = SourceClass.this;

        // Varargs method call in collection operations (pos: collection operations)
        List<Object> collection = new ArrayList<>();
        collection.add(varargsMethod(1, outerThis, "arg1", "arg2")); // 1, inner_class, varargs, instanceReference.methodName(arguments)

        // Do statement
        int count = 0;
        do {
            targetValue += "_" + count;
            count++;
        } while (count < 3);

        // Property assignment with call_method (pos: property assignment)
        String callResult;
        callResult = new InnerHelper().callMethod(innerRec); // inner_class, protected, accessor, instanceReference.methodName(arguments)
        collection.add(callResult);

        return collection;
    }

    // Varargs method (public modifier, Object return, 1, inner_class, varargs)
    public Object varargsMethod(int num, SourceClass instanceRef, String... args) {
        // instanceReference.methodName(arguments)
        return instanceRef.processArgs(num, args);
    }

    // Helper method for varargs
    private Object processArgs(int num, String... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        for (String arg : args) {
            sb.append(arg);
        }
        return sb.toString();
    }

    // Inner class for call_method (inner_class, protected modifier)
    class InnerHelper {
        // Call method (accessor, instanceReference.methodName(arguments), return String)
        protected String callMethod(TargetClass.InnerRecord instanceRef) {
            // instanceReference.methodName(arguments) (accessor method call)
            return instanceRef.updateValue("processed"); // accessor feature
        }
    }
}

// Target class (default modifier, normal class, member inner class target_feature)
class TargetClass {
    // Inner record (target_inner_rec) - member inner class (target_feature)
    public class InnerRecord {
        private String value;

        public InnerRecord(String value) {
            this.value = value;
        }

        // Accessor methods
        public String value() { return value; } // accessor
        public String updateValue(String suffix) { // accessor
            this.value += "_" + suffix;
            return this.value;
        }
    }
}