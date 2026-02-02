package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Super class for source class extends feature
class SuperGenericClass<T> {
    protected T superField;

    public SuperGenericClass(T superField) {
        this.superField = superField;
    }
}

// Source class: generic class, public modifier, extends, same package as target
public class SourceClass<T extends CharSequence> extends SuperGenericClass<T> {
    // Super constructor invocation (required for extends)
    public SourceClass(T superField) {
        super(superField); // super constructor invocation
    }

    // Method to refactor: instance, void return, public, in source class
    // Per_condition: contains target class parameter
    public void methodToRefactor(TargetClass<String>.TargetStaticNestedClass targetParam) {
        // Variable call (target parameter field)
        String targetValue = targetParam.nestedField;
        
        // Type declaration statement
        List<String> dataList;
        
        // Expression statement
        dataList = new ArrayList<>();
        
        // Constructor invocation
        SourceClass<String> sourceInstance = new SourceClass<>("test");
        
        // Synchronized statement
        synchronized (this) {
            dataList.add(targetValue);
        }
        
        // Static method feature: private modifier, 2, source, static, new ClassName().method(), pos=ternary operators, void return
        private static void staticHelperMethod() {
            int count = 2; // method_feature: 2
            // Ternary operator position
            String value = count > 0 ? new SourceClass<>("ternary").superField.toString() : "";
            // new ClassName().method()
            new SourceClass<>("static").methodToRefactor(new TargetClass<String>().new TargetStaticNestedClass());
        }
        staticHelperMethod();

        // Feature: numbers=1, modifier=public, exp=TypeLiteral
        public void typeLiteralFeature() {
            Integer literal = 1; // numbers=1
            List<String> typeLiteral = new ArrayList<String>(); // TypeLiteral (explicit generic type)
            typeLiteral.add(targetValue);
        }
        typeLiteralFeature();

        // Feature: otherObject.process(this);
        OtherProcessor processor = new OtherProcessor();
        processor.process(this);

        // Feature: uses_outer_this
        class InnerClass {
            void useOuterThis() {
                String outerValue = SourceClass.this.superField.toString(); // uses_outer_this
                dataList.add(outerValue);
            }
        }
        new InnerClass().useOuterThis();

        // No new exception feature
        try {
            Integer.parseInt(targetValue);
        } catch (NumberFormatException e) {
            // No throw new exception
            dataList.add("parse_error");
        }
    }
}

// Target class: generic class, no modifier, static nested class feature
class TargetClass<U> {
    // Target_inner_rec (static nested class)
    public class TargetStaticNestedClass {
        public String nestedField = "target_nested_field";
    }
}

// OtherProcessor for otherObject.process(this) feature
class OtherProcessor {
    public void process(SourceClass<?> source) {
        // Dummy implementation
    }
}

// Call method class: others_class type, default modifier, static, obj.m1().m2().m3(), pos=while, return Object
class CallerClass {
    // call_method: default modifier, static feature, obj.m1().m2().m3(), pos=while, return Object
    Object callMethod() {
        SourceClass<String> sourceObj = new SourceClass<>("caller");
        TargetClass<String>.TargetStaticNestedClass targetParam = new TargetClass<String>().new TargetStaticNestedClass();
        
        // Position: while loop
        int i = 0;
        while (i < 3) {
            // obj.m1().m2().m3() feature
            sourceObj.methodToRefactor(targetParam); // m1
            String result = targetParam.nestedField.toUpperCase().substring(0, 5); // m2().m3()
            i++;
        }
        
        // Static feature for call_method
        return CallerClass.staticHelper();
    }

    // Static helper for call_method's static feature
    private static Object staticHelper() {
        return new TargetClass<Integer>().new TargetStaticNestedClass();
    }
}