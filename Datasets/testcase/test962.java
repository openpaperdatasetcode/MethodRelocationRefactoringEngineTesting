package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Others class for call_method feature
class OthersClass {
    // Overloading method 1
    public int processTarget(TargetClass target, int val) {
        return val * 2;
    }

    // Overloading method 2 (overloading feature)
    public int processTarget(TargetClass target, String val) {
        return val.length() + 2;
    }
}

// Supporting interface for target class implements feature
interface TargetInterface {
    String getProcessedValue();
}

// Strictfp normal source class (same package, anonymous inner + static nested class)
strictfp class SourceClass {
    // Static nested class (source feature)
    static class SourceStaticNested {
        public String getNestedValue(String val) {
            return val.toUpperCase();
        }
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            try {
                new SourceClass().processTarget(new TargetClass());
            } catch (Exception e) {
                // no_new_exception
                e.printStackTrace();
            }
        }
    };

    @RefactorAnnotation // has_annotation feature
    // Instance method (protected access, base type return, source position)
    protected int processTarget(TargetClass targetParam) {
        // Per_condition: contains target parameter (variable call)
        if (targetParam == null) {
            targetParam = new TargetClass();
        }
        String targetVal = targetParam.getTargetField(); // variable call

        // Overloading method call (pos: for statement)
        List<String> overloadResult = overloadingMethod(targetParam, 2);

        // call_method invocation (pos: do-while)
        OthersClass others = new OthersClass();
        int callResult = 0;
        int count = 0;
        do {
            // instanceReference.methodName(arguments) + overloading
            callResult = others.processTarget(targetParam, count == 0 ? 2 : "val_" + count);
            count++;
        } while (count < 2);

        // no_new_exception (empty try-catch)
        try {
            targetParam.setTargetField(targetVal + "_processed_" + callResult);
        } catch (Exception e) {
            // Do not throw new exception
            callResult = -1;
        }

        return callResult + overloadResult.size();
    }

    // Overloading method 1 (default modifier, List<String> return, pos: for)
    List<String> overloadingMethod(TargetClass target, int val) {
        List<String> result = new ArrayList<>();
        // pos: for statement
        for (int i = 0; i < val; i++) {
            // obj.m1().m2().m3() chain
            String chainVal = new SourceStaticNested()
                    .getNestedValue(target.getTargetField()) // m1()
                    .concat("_") // m2() (String method)
                    .repeat(2); // m3() (String method, 2 feature)
            result.add(chainVal);
        }
        return result;
    }

    // Overloading method 2 (overloading feature)
    List<String> overloadingMethod(TargetClass target, String val) {
        List<String> result = new ArrayList<>();
        result.add(val + "_" + 2); // 2 feature
        return result;
    }
}

// Public normal target class (implements + anonymous inner class)
public class TargetClass implements TargetInterface {
    private String targetField = "default_target_value";

    // Anonymous inner class (target feature)
    TargetInterface anonymousInner = new TargetInterface() {
        @Override
        public String getProcessedValue() {
            return targetField + "_anonymous";
        }
    };

    // Variable call support methods
    public String getTargetField() {
        return anonymousInner.getProcessedValue();
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    @Override
    public String getProcessedValue() {
        return targetField;
    }
}