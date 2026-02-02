package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Others class for method_feature: others_class
class OthersClass<T> {
    // Static method for ClassName.methodName(arguments) feature
    public static <U> TargetClass<U> createTarget(U value) {
        return new TargetClass<>(value); // constructor feature, method_feature: 1
    }
}

// Parent class for source class extends feature
class ParentGenericClass<T> {
    protected T parentValue;

    public ParentGenericClass(T value) {
        this.parentValue = value;
    }

    // Instance method for access_instance_method feature
    protected String parentInstanceMethod(T value) {
        return value.toString() + "_parent_1"; // method_feature: 1
    }
}

// Source class: generic public class, same package as target, type parameter, extends, member inner, static nested class
public class SourceClass<T extends CharSequence> extends ParentGenericClass<T> {
    // Outer private field for access_outer_private feature
    private final T outerPrivateField = (T) "outer_private_1"; // method_feature: 1
    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClass<>((T) "initial_target_1");

    // Static nested class (source feature)
    static class SourceStaticNested<U> {
        public static U processValue(U val) {
            return (U) (val.toString() + "_static_1"); // method_feature: 1
        }
    }

    // Member inner class (source feature)
    public class SourceInnerClass {
        public T processInner(T value) {
            return (T) (value.toString() + "_inner_1"); // method_feature: 1
        }
    }

    // Source_inner_rec (inner recursive class for method_position)
    public class SourceInnerRec {
        // Constructor feature: public modifier, 1, others_class, constructor, ClassName.methodName(), pos=array initialization, return TargetClass Type
        public TargetClass<T> createTargetInstance() {
            // pos: array initialization (parameter for ClassName.methodName)
            T[] initArray = (T[]) new CharSequence[]{"init_1", "init_2"}; // method_feature: 1
            // ClassName.methodName(arguments) + others_class + constructor
            TargetClass<T> target = OthersClass.createTarget(SourceStaticNested.processValue(initArray[0]));
            return target;
        }

        // Method to refactor: instance, List<String> return, protected access, method types parameter is:none, in source_inner_rec
        protected List<String> methodToRefactor(TargetClass<T> targetParam) {
            // Method types parameter is:none (no additional type params on method)
            List<String> result = new ArrayList<>();

            // Variable call (target class)
            T targetValue = targetParam.getValue();
            result.add(targetValue.toString());

            // Constructor feature call (array initialization)
            TargetClass<T> newTarget = createTargetInstance();
            result.add(newTarget.getValue().toString());

            // Access_outer_private feature (access outer class private field)
            result.add(SourceClass.this.outerPrivateField.toString());

            // Access_instance_method feature (call parent instance method)
            String parentResult = SourceClass.this.parentInstanceMethod(targetValue); // extends feature
            result.add(parentResult);

            // Access member inner class instance method
            SourceInnerClass innerHelper = new SourceInnerClass();
            T innerProcessed = innerHelper.processInner(targetValue);
            result.add(innerProcessed.toString());

            // Variable call for targetField (per_condition)
            result.add(targetField.getValue().toString() + "_field_1"); // method_feature: 1

            // No_new_exception feature
            try {
                Integer.parseInt(targetValue.toString());
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                result.add("formatted_" + targetValue + "_1"); // method_feature: 1
            }

            return result;
        }
    }

    // Constructor (extends feature)
    public SourceClass(T value) {
        super(value);
    }

    // Helper method to invoke refactored method
    public List<String> invokeMethod(TargetClass<T> target) {
        SourceInnerRec innerRec = new SourceInnerRec();
        return innerRec.methodToRefactor(target);
    }
}

// Target class: generic no-modifier class, local inner class feature
class TargetClass<T> {
    private final T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Local inner class (target_feature)
    public List<String> processValue(T input) {
        class LocalInnerProcessor {
            String process(T val) {
                return val.toString() + "_local_1"; // method_feature: 1
            }
        }
        List<String> result = new ArrayList<>();
        result.add(new LocalInnerProcessor().process(input));
        return result;
    }

    // Variable call getter
    public T getValue() {
        return value;
    }
}