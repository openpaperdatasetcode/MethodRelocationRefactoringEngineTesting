package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SourceClass {
    // Source contains target class field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {}

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Inner class (method_position: source_inner)
    class InnerSourceClass {
        // Method to be refactored (varargs, void return, private access)
        private void targetMethod(String... args) {
            // Type declaration statement
            List<Object> resultList = new ArrayList<>();
            
            // For statement
            for (int i = 0; i < args.length; i++) {
                // Variable call
                String innerValue = targetField.innerClass.getValue();
                resultList.add(args[i] + "-" + innerValue);
                
                // Collection operations with recursion (pos: collection operations)
                Object recursiveResult = recursiveMethod(1, resultList); // 1 in method_feature
                
                // Call method (inner_class, final modifier, instance, (parameters) -> methodBody, pos: expression, return int)
                final Function<Integer, Integer> innerCall = (num) -> innerMethod(num); // (parameters) -> methodBody
                int callResult = innerCall.apply(i);
                resultList.add(callResult);
            }
        }

        // Recursion method (protected modifier, return Object, target, recursion, (parameters) -> methodBody)
        protected Object recursiveMethod(int num, List<Object> list) { // 1 in method_feature
            if (num <= 0) {
                return list;
            }
            // (parameters) -> methodBody reference
            Function<Integer, Integer> lambda = (n) -> n * 2;
            list.add(lambda.apply(num));
            // Recursion feature (target class reference in recursion)
            return recursiveMethod(num - 1, targetField.innerClass.getList()); // target in method_feature
        }

        // Inner call method (instance, return int)
        private int innerMethod(int num) {
            return num * 3;
        }
    }
}

class TargetClass {
    // Inner target class (target class: target_inner_rec)
    class InnerTargetClass {
        private String value = "targetValue";
        private List<Object> list = new ArrayList<>();

        String getValue() {
            return value;
        }

        List<Object> getList() {
            return list;
        }
    }

    final InnerTargetClass innerClass = new InnerTargetClass();
}