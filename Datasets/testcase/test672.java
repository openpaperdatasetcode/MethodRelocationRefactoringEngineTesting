package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Parent class for method_feature: parent_class
class ParentGenericClass<T> {
    protected List<String> parentVarargsMethod(T... params) {
        List<String> result = new ArrayList<>();
        for (T param : params) {
            result.add(param.toString() + "_parent_2"); // method_feature: 2
        }
        return result;
    }
}

// Source class: generic final class, same package as target, local inner + member inner class
final class SourceClass<T extends CharSequence> extends ParentGenericClass<T> {
    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClass<>((T) "initial_target_2");

    // Member inner class (source feature)
    public class SourceInnerClass {
        public T processInnerValue(T value) {
            return (T) (value.toString() + "_inner_2"); // method_feature: 2
        }
    }

    // Varargs feature: protected modifier, 2, parent_class, varargs, super.methodName(), pos=switch, return List<String>
    @Override
    protected List<String> parentVarargsMethod(T... params) {
        List<String> result = new ArrayList<>();
        // pos: switch statement
        switch (params.length) {
            case 2: // method_feature: 2
                // super.methodName(arguments)
                result.addAll(super.parentVarargsMethod(params)); // method_feature: parent_class
                break;
            default:
                result.add("default_varargs_2"); // method_feature: 2
                break;
        }
        return result;
    }

    // Method to refactor: normal, Object return, private access, in source class
    private Object methodToRefactor(TargetClass<T> targetParam) {
        // Variable call (target class and static nested class)
        T targetValue = targetParam.getValue();
        TargetClass.TargetStaticNested<T> nested = targetParam.new TargetStaticNested<>();
        nested.setNestedValue((T) (targetValue + "_nested_2")); // method_feature: 2

        // Depends_on_inner_class (use member inner class)
        SourceInnerClass innerHelper = new SourceInnerClass();
        T processedInner = innerHelper.processInnerValue(targetValue);

        // Varargs feature call (pos=switch)
        List<String> varargsResult = this.parentVarargsMethod(targetValue, processedInner); // method_feature: varargs

        // Used_by_reflection feature
        try {
            // Reflectively invoke target static nested class method
            Method nestedMethod = TargetClass.TargetStaticNested.class.getDeclaredMethod("getNestedValue");
            nestedMethod.setAccessible(true);
            T reflectValue = (T) nestedMethod.invoke(nested);
            targetParam.setValue(reflectValue);
        } catch (Exception e) {
            // No_new_exception feature
            targetParam.setValue((T) ("reflection_error_2")); // method_feature: 2
        }

        // Local inner class (source feature)
        class LocalInnerProcessor {
            Object process(T input) {
                return input.toString() + "_local_2"; // method_feature: 2
            }
        }
        Object localResult = new LocalInnerProcessor().process(targetParam.getValue());

        // Variable call for targetField (per_condition)
        localResult = localResult.toString() + "_" + targetField.getValue();

        // Return statement
        return localResult;
    }

    // Helper method to invoke refactored method
    public Object invokeMethod(TargetClass<T> target) {
        return methodToRefactor(target);
    }
}

// Target class: generic no-modifier class, static nested class feature
class TargetClass<T> {
    private T value;

    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        private U nestedValue;

        public U getNestedValue() {
            return nestedValue;
        }

        public void setNestedValue(U nestedValue) {
            this.nestedValue = nestedValue;
        }
    }

    // Constructor
    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Variable call getters/setters
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    // Factory method for static nested class
    public <U> TargetStaticNested<U> new TargetStaticNested() {
        return new TargetStaticNested<>();
    }
}