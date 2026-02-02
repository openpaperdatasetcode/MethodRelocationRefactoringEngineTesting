package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    String value() default "processed_6480";
}

// Interface for target enum implements feature
interface TargetInterface {
    String getInnerValue();
}

// Source enum class: protected, same package as target, anonymous inner + member inner class
protected enum SourceEnum {
    INSTANCE;

    // Per_condition: source contains target enum field
    private TargetEnum targetField = TargetEnum.VALUE1;
    private final Object lock = new Object();

    // Member inner class (source feature)
    class SourceInnerClass {
        // call_method: inner_class type, private modifier, static, obj.m1().m2().m3(), pos=constructor param list, return List<String>
        private List<String> callMethod(TargetEnum.TargetInner inner) {
            List<String> result = new ArrayList<>();
            // pos: the parameter list of constructors (pass method chain result)
            TargetEnum.TargetInner newInner = new TargetEnum.TargetInner(inner.m1().m2().m3()); // obj.m1().m2().m3()
            // target_feature: static (call static method)
            result.add(TargetEnum.StaticHelper.staticProcess(newInner.getInnerValue()));
            result.add(newInner.getInnerValue());
            return result;
        }

        // Static helper method for call_method static feature
        public static String staticHelper(String s) {
            return s + "_static_6480";
        }
    }

    // Method to refactor: instance, List<String> return, public access, in source enum
    @ProcessAnnotation("method_6480") // has_annotation feature
    public List<String> methodToRefactor(TargetEnum.TargetInner... targetParams) {
        List<String> result = new ArrayList<>();
        
        // Type declaration statement
        @ProcessAnnotation("type_decl_6480") // has_annotation feature
        class ProcessedString<T extends CharSequence & Comparable<T>> { // with_bounds feature
            private T value;
            ProcessedString(T val) { this.value = val; }
            T getValue() { return value; }
        }

        // For statement
        for (int i = 0; i < targetParams.length; i++) {
            TargetEnum.TargetInner param = targetParams[i];
            // Variable call (target inner class)
            String innerValue = param.getInnerValue();
            
            // Synchronized statement
            synchronized (lock) {
                innerValue += "_sync_" + i;
            }

            // With_bounds feature usage
            ProcessedString<String> processed = new ProcessedString<>(innerValue);
            result.add(processed.getValue());

            // Depends_on_inner_class feature (use member inner class)
            SourceInnerClass innerHelper = new SourceInnerClass();
            result.addAll(innerHelper.callMethod(param));
        }

        // Variable call for targetField (per_condition)
        result.add(targetField.getInner().getInnerValue());

        // No_new_exception feature
        try {
            Integer.parseInt(targetField.getInner().getInnerValue());
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            result.add("formatted_" + targetField.getInner().getInnerValue());
        }

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anonymous: " + result);
            }
        };
        anonymousRunnable.run();

        return result;
    }
}

// Target enum class: public, implements interface, anonymous inner class feature
public enum TargetEnum implements TargetInterface {
    VALUE1, VALUE2;

    // Static helper for call_method static feature
    public static class StaticHelper {
        public static String staticProcess(String s) {
            return s + "_target_static_6480";
        }
    }

    // Target_inner (target inner class)
    public class TargetInner {
        private String innerValue;

        public TargetInner() {
            this.innerValue = "target_inner_6480";
            // Anonymous inner class (target_feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Target anonymous: " + innerValue);
                }
            };
            anonymous.run();
        }

        public TargetInner(String value) {
            this.innerValue = value;
        }

        // Method chaining for obj.m1().m2().m3()
        public String m1() { return innerValue + "_m1"; }
        public String m2() { return this.m1() + "_m2"; }
        public String m3() { return this.m2() + "_m3_6480"; }

        @Override // implements feature
        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }

    // Get inner class instance (per_condition variable call)
    public TargetInner getInner() {
        return new TargetInner();
    }

    @Override // implements feature
    public String getInnerValue() {
        return this.name() + "_enum_value_6480";
    }
}