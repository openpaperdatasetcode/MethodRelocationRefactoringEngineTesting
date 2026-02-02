package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class SourceClass {
    // Per_condition: source contains the field of the target
    private TargetClass targetField = new TargetClass();

    // Static nested class (source_class feature)
    public static class StaticNestedClass {
        public String helperMethod(String input) {
            return input.toUpperCase();
        }
    }

    // Member inner class (source_class feature)
    public class MemberInnerClass {
        String value = "innerValue";
    }

    // Instance method (method type: instance) - protected access, return List<String>, position: source
    protected List<String> moveCandidateMethod() {
        List<String> result = new ArrayList<>();
        // Variable call (method feature)
        MemberInnerClass inner = new MemberInnerClass();
        String varCall = inner.value;
        result.add(varCall);

        // Super constructor invocation (method feature)
        super();

        // If statement (method feature)
        if (targetField != null) {
            // Raw type (method feature)
            Vector rawVector = new Vector();
            rawVector.add("rawTypeElement");
            result.addAll(rawVector);

            // Collection operations with recursion (recursion pos: collection operations)
            List<String> recursiveResult = recursiveMethod(result.stream()
                    .map(s -> s + "_processed")
                    .collect(Collectors.toList()), 3);
            result.addAll(recursiveResult);

            // Lambda: () -> System.out.println(this.value) (method feature)
            Runnable lambda = () -> System.out.println(this.targetField.new MemberInnerClass().innerField);

            // Override violation (method feature) - intentional violation of override rules
            @SuppressWarnings("unused")
            class OverrideViolationClass extends TargetClass.MemberInnerClass {
                // Invalid override (return type mismatch, access modifier narrowing)
                private String innerMethod() {
                    return "violation";
                }
            }
        }

        // No new exception (method feature - no throw new Exception)
        try {
            result.add(targetField.new MemberInnerClass().innerMethod());
        } catch (Exception e) {
            result.add(e.getMessage());
        }
        return result;
    }

    // Recursion method (type: recursion, public modifier, return base type)
    // method_feature: ["3", "source", "recursion", "(parameters) -> methodBody"]
    public int recursiveMethod(List<String> list, int count) {
        // (parameters) -> methodBody (method_feature)
        list.forEach(s -> {
            StaticNestedClass staticNested = new StaticNestedClass();
            staticNested.helperMethod(s);
        });
        if (count <= 0) {
            return 0;
        }
        // Recursive call
        return recursiveMethod(list, count - 1);
    }
}

public class TargetClass {
    // Member inner class (target_class target_feature)
    public class MemberInnerClass {
        String innerField = "targetInnerField";

        public String innerMethod() {
            return innerField;
        }
    }
}