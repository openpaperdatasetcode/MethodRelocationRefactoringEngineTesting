package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Source class (default modifier, same package, type parameter + static nested + anonymous inner class)
class SourceClass<T extends CharSequence> {
    private T typeParamField;

    // Static nested class (source feature)
    static class SourceStaticNested {
        // Inner class for method position (source_inner)
        class SourceInnerClass {
            // Method to be refactored (varargs, List<String> return, protected access)
            protected List<String> moveMethod(TargetClass... targetParams) {
                // Per_condition: contains target parameter (targetParams)
                List<String> result = new ArrayList<>();
                if (targetParams == null || targetParams.length == 0) {
                    return result;
                }

                // Variable call
                for (TargetClass target : targetParams) {
                    String varCall = target.targetField;
                    result.add(varCall);
                }

                // Anonymous inner class (source feature)
                Runnable anonymousInner = new Runnable() {
                    @Override
                    public void run() {
                        result.forEach(System.out::println);
                    }
                };
                anonymousInner.run();

                // Do-while loop with call_method (pos:do-while)
                int count = 0;
                do {
                    // call_method: inner_class type, synchronized modifier, normal + ClassName.methodName(arguments)
                    Object callResult = new InnerCallClass().callMethod(targetParams[0], count);
                    result.add(callResult.toString());
                    count++;
                } while (count < targetParams.length);

                // No new exception
                return result;
            }

            // Inner class for call_method (inner_class type)
            class InnerCallClass {
                // call_method (synchronized modifier, normal + ClassName.methodName(arguments))
                synchronized Object callMethod(TargetClass param, int num) {
                    // ClassName.methodName(arguments)
                    return TargetClass.TargetStaticNested.processField(param.targetField, num);
                }
            }
        }
    }
}

// Target class (non-sealed modifier, static nested class feature)
non-sealed class TargetClass {
    String targetField = "targetValue"; // For variable call

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static Object processField(String field, int num) {
            return field + "_processed_" + num;
        }
    }
}