package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

// Super class for super keywords usage
class SuperSourceClass {
    protected String superField = "super_value";
}

// Source class: public normal class, same package as target, anonymous inner + static nested class
public class SourceClass extends SuperSourceClass {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outer_private_data";
    // Static field for depends_on_static_field feature
    private static int staticField = 99;
    // Per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>("initial_target_value");

    // Static nested class (source feature)
    static class SourceStaticNested {
        public static String processStatic(int val) {
            return "static_processed_" + val;
        }
    }

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        // Overload method 1 (overload_exist feature)
        TargetClass<String> methodToRefactor(TargetClass<String>.TargetInnerRec... targetParams) {
            TargetClass<String> result = new TargetClass<>("default_result");
            
            // Variable call (target inner recursive class)
            for (TargetClass<String>.TargetInnerRec param : targetParams) {
                String innerVal = param.getInnerValue();
                
                // Super keywords usage
                innerVal += SuperSourceClass.super.superField;
                
                // Access outer private field
                innerVal += SourceClass.this.outerPrivateField;
                
                // Depends on static field
                innerVal += SourceStaticNested.processStatic(staticField);
                
                // Used by reflection feature
                try {
                    Field innerField = TargetClass.TargetInnerRec.class.getDeclaredField("innerValue");
                    innerField.setAccessible(true);
                    innerField.set(param, innerVal);
                    
                    Method setMethod = TargetClass.TargetInnerRec.class.getDeclaredMethod("setInnerValue", String.class);
                    setMethod.invoke(param, innerVal + "_reflection");
                } catch (Exception e) {
                    // No_new_exception feature: handle without throwing new exception
                    param.setInnerValue("reflection_error_" + innerVal);
                }

                // No_new_exception additional handling
                try {
                    Integer.parseInt(innerVal);
                } catch (NumberFormatException e) {
                    param.setInnerValue("formatted_" + innerVal);
                }
            }

            // Anonymous inner class (source feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Processed target value: " + targetField.getInnerRec().getInnerValue());
                }
            };
            anonymousRunnable.run();

            // Assign processed target field as result (per_condition)
            result = targetField;
            return result;
        }

        // Overload method 2 (overload_exist feature)
        TargetClass<String> methodToRefactor(TargetClass<String>.TargetInnerRec param, String extra) {
            TargetClass<String> baseResult = methodToRefactor(param);
            param.setInnerValue(param.getInnerValue() + extra);
            return baseResult;
        }
    }

    // Anonymous inner class (source feature)
    public void sourceAnonymousFeature() {
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anonymous class: " + outerPrivateField);
            }
        };
        anonymous.run();
    }
}

// Target class: public normal class, type parameter + static nested class features
public class TargetClass<T> {
    // Static nested class (target feature)
    public static class TargetStaticNested {
        public static <U> U getStaticValue(U val) {
            return val;
        }
    }

    private T value;
    private TargetInnerRec innerRec;

    public TargetClass(T initialValue) {
        this.value = initialValue;
        this.innerRec = new TargetInnerRec(initialValue);
    }

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private T innerValue;

        public TargetInnerRec(T value) {
            this.innerValue = value;
        }

        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T value) {
            this.innerValue = value;
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TargetInnerRec getInnerRec() {
        return innerRec;
    }
}