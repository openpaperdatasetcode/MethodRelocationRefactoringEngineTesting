package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Parent class for method_feature "parent_class"
class SourceParentClass {
    protected String parentMethod(int num) {
        return "parent_value_" + num; // Number 1 for method_feature
    }
}

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

// Source abstract normal class (abstract modifier, same package, local inner + anonymous inner class)
abstract class SourceClass extends SourceParentClass {
    // Anonymous inner class (source_class feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class: 1"); // Number 1 for method_feature
        }
    };

    // First has_annotation feature
    @CustomAnnotation
    // Overloading method 1 (private access, void return, source position, target param - per_condition)
    private void refactorMethod(TargetClass targetParam) {
        // Variable call feature
        sourceAnonymous.run();
        String varCall = targetParam.getData();

        // Synchronized statement feature
        Object lock = new Object();
        synchronized (lock) {
            varCall += "_synchronized_1"; // Number 1 for method_feature
        }

        // Local inner class (source_class feature)
        class SourceLocalInner {
            String process(String val) {
                return val + "_local_inner_1"; // Number 1 for method_feature
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        varCall = localInner.process(varCall);

        // Instance method (public modifier, 1 + parent_class + instance + OuterClass.InnerClass.methodName(), if/else pos)
        public List<String> instanceMethod() {
            List<String> result = new ArrayList<>();
            // If/else conditions position
            if (targetParam != null) {
                // OuterClass.InnerClass.methodName() target_feature + Number 1
                result.add(TargetClass.TargetStaticNested.staticMethod(1)); // parent_class + instance feature
                // Parent class method call (Number 1)
                result.add(super.parentMethod(1));
            } else {
                result.add("default_1");
            }
            return result;
        }

        // Second has_annotation feature (duplicate as specified)
        @SuppressWarnings("unused")
        String annotatedVar = varCall;

        // No_new_exception feature (no explicit throw new Exception)
        instanceMethod();
    }

    // Overloading method 2 (overloading feature)
    private void refactorMethod(TargetClass targetParam, String extra) {
        refactorMethod(targetParam);
        System.out.println(extra + "_overload_1"); // Number 1 for method_feature
    }
}

// Target normal class (protected modifier, static nested class target_feature)
protected class TargetClass {
    private String data;

    public TargetClass(String data) {
        this.data = data;
    }

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static String staticMethod(int num) {
            return "static_nested_" + num; // Number 1 for method_feature
        }
    }

    public String getData() {
        return data;
    }
}

// Others_class for call_method (default modifier, normal + new ClassName().method(), object initialization pos)
class OthersClass {
    // Call method (default modifier, others_class type, object initialization pos, returns Object)
    Object callMethod() {
        // Object initialization position
        SourceClass source = new SourceClass() {};
        TargetClass target = new TargetClass("target_1");
        
        // new ClassName().method() target_feature + normal feature
        source.refactorMethod(target); // Normal method call
        return new TargetClass.TargetStaticNested(); // New instance of static nested class
    }
}