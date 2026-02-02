package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

protected class SourceClass<T> {
    // Source contains target class field (per_condition)
    private TargetClass targetField = new TargetClass();
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "outerProtectedValue";

    // Type parameter (source_class feature)
    private T typeParam;

    /**
     * Varargs method to be refactored
     * @param args variable arguments list
     * @return List<String> containing processed values
     */
    protected List<String> targetMethod(String... args) { // varargs, List<String> return, protected access, position: source
        List<String> result = new ArrayList<>();
        
        // Local inner class (source_class feature)
        class LocalInnerClass {
            Object process(int num) { // 1 in method_feature, return Object
                // OuterClass.InnerClass.methodName() (method_feature)
                return TargetClass.StaticNestedTargetClass.staticMethod(num);
            }
        }

        // Anonymous inner class (source_class feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                result.add("anonymousInner");
            }
        };
        anonymousInner.run();

        // Access outer protected field
        String outerValue = this.outerProtectedField;
        result.add(outerValue);

        // Property assignment with normal method (pos: property assignment)
        LocalInnerClass inner = new LocalInnerClass(); // inner_class in method_feature
        Object propValue = inner.process(1); // 1 in method_feature, normal method type
        result.add(propValue.toString());

        // Variable call
        String targetValue = targetField.innerRecord.value();
        result.add(targetValue);

        // This.methodName(arguments)
        this.helperMethod(args, 1);

        // No new exception (no_new_exception)
        return result;
    }

    // Helper method for this.methodName(arguments)
    private void helperMethod(String[] args, int num) {
        for (String arg : args) {
            // Variable call to target inner record
            targetField.innerRecord = new TargetClass.InnerTargetRecord(arg + num);
        }
    }
}

protected class TargetClass {
    // Static nested class (target_feature)
    static class StaticNestedTargetClass {
        public static Object staticMethod(int num) {
            return "staticMethod-" + num;
        }
    }

    // Inner record (target class: target_inner_rec)
    record InnerTargetRecord(String value) {}
    InnerTargetRecord innerRecord = new InnerTargetRecord("targetInnerRecord");
}