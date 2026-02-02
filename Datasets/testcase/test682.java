package com.refactoring.movemethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Parent class for super constructor invocation
class ParentSourceClass {
    protected String parentField;

    public ParentSourceClass(String value) {
        this.parentField = value;
    }
}

// Source class: final normal class, same package as target, member inner + static nested class
final class SourceClass extends ParentSourceClass {
    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "static_6571"; // id=6571
    // Per_condition: source contains target class field
    private final TargetClass<String> targetField = new TargetClass<>("initial_target_6571");

    // Static nested class (source feature)
    static class SourceStaticNested<T extends CharSequence> { // with_bounds (T extends CharSequence)
        public static <U extends String> U processValue(U val) { // with_bounds (U extends String)
            return (U) (val + "_" + SourceClass.STATIC_FIELD);
        }
    }

    // Member inner class (source_inner - method_position)
    public class SourceInnerClass {
        // Super constructor invocation (via parent class)
        public SourceInnerClass() {
            super(); // super constructor invocation
        }

        // Method to refactor: instance, Object return, final access, in source_inner
        public final Object methodToRefactor(TargetClass<String>.TargetInnerRec innerParam) {
            // Per_condition: method contains target parameter
            if (innerParam == null) {
                return new Object();
            }

            // Super constructor invocation verification
            SourceClass.this.parentField = "super_ctor_6571";

            // Variable call (target inner recursive class)
            String innerValue = innerParam.getInnerValue();
            
            // With_bounds feature (use bounded generic static nested class)
            String processed = SourceStaticNested.<String>processValue(innerValue); // with_bounds
            innerParam.setInnerValue(processed);

            // Depends_on_static_field feature (use static field)
            innerParam.setInnerValue(innerParam.getInnerValue() + "_" + SourceClass.STATIC_FIELD);

            // Used_by_reflection feature
            try {
                // Access target inner field via reflection
                Field field = TargetClass.TargetInnerRec.class.getDeclaredField("innerValue");
                field.setAccessible(true);
                field.set(innerParam, field.get(innerParam) + "_reflection_6571");

                // Invoke target inner method via reflection
                Method method = TargetClass.TargetInnerRec.class.getDeclaredMethod("innerMethod");
                method.setAccessible(true);
                method.invoke(innerParam);
            } catch (Exception e) {
                // No_new_exception feature (no throw new exception, handle only)
                innerParam.setInnerValue("safe_value_6571");
            }

            // Variable call for targetField (per_condition)
            innerParam.setInnerValue(innerParam.getInnerValue() + "_" + targetField.getValue());

            // No_new_exception additional handling
            try {
                Integer.parseInt(innerParam.getInnerValue());
            } catch (NumberFormatException e) {
                innerParam.setInnerValue("formatted_6571");
            }

            return innerParam.getInnerValue();
        }
    }

    // Super constructor invocation (source class constructor)
    public SourceClass() {
        super("super_source_6571"); // super constructor invocation
    }
}

// Target class: private normal class, anonymous inner class feature
private class TargetClass<T> {
    private T value;

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private T innerValue;

        public TargetInnerRec() {
            // Anonymous inner class (target_feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    innerValue = (T) ("anonymous_inner_6571");
                }
            };
            anonymous.run();
        }

        // Method for reflection invocation
        public String innerMethod() {
            return innerValue.toString() + "_inner_method_6571";
        }

        // Variable call getters/setters
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }
    }

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    public T getValue() {
        return value;
    }
}