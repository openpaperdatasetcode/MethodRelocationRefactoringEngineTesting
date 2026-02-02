package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source normal class (strictfp modifier, same package, no additional features)
strictfp class SourceClass {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;
    private int instanceField = 1; // access_instance_field feature

    // Normal method to refactor (private, return List<String>, source position)
    private List<String> refactorMethod(TargetClass targetParam) throws Exception { // requires_throws
        List<String> result = new ArrayList<>();
        
        // Variable call feature
        int localVar = this.instanceField;
        
        // Expression statement feature
        localVar += 10;
        
        // Constructor invocation feature
        TargetClass newTarget = new TargetClass("init", localVar);
        
        // Try statement feature
        try {
            // Break statement feature
            loop:
            for (int i = 0; i < 5; i++) {
                if (i == 3) {
                    break loop; // break statement
                }
                result.add(String.valueOf(i));
            }
            // Access instance field
            this.targetField = newTarget;
            // Process target parameter (per_condition: method has target parameter)
            result.add(targetParam.getData());
        } catch (NullPointerException e) {
            throw new Exception("Processing error", e); // requires_throws
        }
        
        // Static method in functional interfaces (pos: functional interfaces, method_feature: 1/inner_class/static/ClassName.methodName)
        Consumer<Integer> consumer = num -> TargetClass.TargetInner.staticMethod(num, result); // ClassName.methodName(arguments)
        consumer.accept(1); // method_feature: 1
        
        return result;
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass("default", 0);
    }
}

// Target normal class (strictfp modifier, anonymous inner class feature)
strictfp class TargetClass {
    private String data;
    private int value;

    // Inner class for method_feature: inner_class
    public static class TargetInner {
        // Static method (public modifier, return void, method_feature: static/1)
        public static void staticMethod(int num, List<String> list) { // method_feature: 1
            list.add("static_processed_" + num);
        }
    }

    // Constructor for invocation
    public TargetClass(String data, int value) {
        this.data = data;
        this.value = value;
    }

    // Anonymous inner class (target_feature)
    public Runnable getAnonymousRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(TargetClass.this.data);
            }
        };
    }

    // Getter for data
    public String getData() {
        return data;
    }
}