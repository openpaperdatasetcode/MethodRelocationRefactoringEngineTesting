package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source class: normal class, default modifier, same package as target
// Features: implements, anonymous inner class, member inner class
class SourceClass implements SampleInterface {
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "sourceProtectedValue";

    // Member inner class (source_class feature)
    public class MemberInnerClass {
        // call_method: type=inner_class, modifier=protected, target_feature: overloading, outerInstance.new InnerClass().methodName()
        // pos: ternary operators, return_type: List<String>
        protected List<String> callMethod(TargetClass target, int type) {
            // Overloading feature (two overloaded methods)
            return type == 1 ? overloadedMethod(target) : overloadedMethod(target, "suffix");
        }

        // Overloaded method 1
        private List<String> overloadedMethod(TargetClass target) {
            return overloadedMethod(target, "");
        }

        // Overloaded method 2
        private List<String> overloadedMethod(TargetClass target, String suffix) {
            // outerInstance.new InnerClass().methodName() (target_feature)
            TargetClass.MemberInnerClass targetInner = target.new MemberInnerClass();
            List<String> result = new ArrayList<>();
            result.add(targetInner.innerMethod() + suffix);
            return result;
        }
    }

    // Instance method (type: instance), return base type (int), default access, position: source
    // Per_condition: contains parameter of the target (TargetClass param)
    int moveCandidateMethod(TargetClass param) {
        // Variable call (method feature)
        MemberInnerClass innerObj = new MemberInnerClass();
        String varCall = innerObj.toString();
        int result = varCall.length();

        // Access outer protected (method feature)
        result += this.outerProtectedField.length();

        // Constructor invocation (method feature)
        TargetClass newTarget = new TargetClass();

        // this(arguments) (method feature) - via anonymous inner class constructor
        Runnable anonymous = new Runnable() {
            public Runnable() {
                this.run(); // this(arguments) equivalent in context
            }

            @Override
            public void run() {
                result += 5;
            }
        };
        anonymous.run();

        // With bounds (method feature)
        <T extends Number & Comparable<T>> T boundedMethod(T input) {
            return input;
        }
        result += boundedMethod(10).intValue();

        // Override violation (method feature)
        class OverrideViolation extends TargetClass.MemberInnerClass {
            // Invalid override (access modifier narrowing + return type mismatch)
            private int innerMethod() {
                return 0;
            }
        }

        // For loop with recursion (recursion pos: for)
        for (int i = 0; i < 1; i++) { // method_feature: "1"
            // Recursion method call (instanceReference.methodName(arguments))
            Object recursiveResult = recursiveMethod(param, 1);
            result += ((Number) recursiveResult).intValue();
            
            // Break statement (method feature)
            if (result > 20) break;
        }

        // Call inner class method in ternary operators (call_method pos: ternary operators)
        List<String> callResult = (param != null) 
            ? innerObj.callMethod(param, 1) 
            : innerObj.callMethod(newTarget, 2);
        result += callResult.size();

        // No new exception (method feature - no throw new Exception)
        try {
            recursiveMethod(param, 0);
        } catch (Exception e) {
            // No new exception instantiation
            result -= e.getMessage().length();
        }

        return result;
    }

    // Recursion method (type: recursion, private modifier, return Object)
    // method_feature: ["1", "target", "recursion", "instanceReference.methodName(arguments)"]
    private Object recursiveMethod(TargetClass target, int count) {
        if (count <= 0) {
            return 0;
        }
        // InstanceReference.methodName(arguments) + recursion
        return target.new MemberInnerClass().innerMethod().length() + (int) recursiveMethod(target, count - 1);
    }

    // Anonymous inner class (source_class feature)
    SampleInterface anonymousInner = new SampleInterface() {};
}

// Interface for source_class implements feature
interface SampleInterface {}

// Target class: normal class, private modifier, target_feature: implements, member inner class
private class TargetClass implements TargetInterface {
    // Member inner class (target_feature)
    public class MemberInnerClass {
        public String innerMethod() {
            return "targetInnerValue";
        }
    }
}

// Interface for target_class implements feature
interface TargetInterface {}