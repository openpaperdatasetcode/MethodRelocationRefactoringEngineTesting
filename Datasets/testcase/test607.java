package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Super class for source class extends feature & superTypeReference
class SuperSourceClass<T> {
    protected T superField;

    public SuperSourceClass(T value) {
        this.superField = value;
    }

    public T getSuperValue() {
        return superField;
    }
}

// Source class: generic, protected, same package as target, extends, static nested, member inner
protected class SourceClass<T extends CharSequence> extends SuperSourceClass<T> {
    // Protected outer field for access_outer_protected feature
    protected T outerProtectedField;
    // Instance field for access_instance_field feature
    private String instanceField = "sourceInstanceField6434";

    public SourceClass(T value) {
        super(value);
        this.outerProtectedField = value;
    }

    // Static nested class (source class feature)
    static class StaticNestedClass<U> {
        public static <V> V processTarget(TargetClass<V> target) {
            return target.innerClass.getValue();
        }
    }

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        // Method to refactor: instance, List<String> return, protected access, in source_inner
        // Per_condition: contains target class parameter
        protected List<String> methodToRefactor(TargetClass<T> targetParam) throws NullPointerException { // requires_throws (NPE)
            List<String> result = new ArrayList<>();
            
            // Variable call (target parameter's inner class)
            T targetValue = targetParam.innerClass.getValue();
            result.add(targetValue.toString());

            // Access outer protected field
            result.add(SourceClass.this.outerProtectedField.toString());
            // Access instance field
            result.add(SourceClass.this.instanceField);

            // Enhanced for statement (iterate over target inner class data)
            for (String s : targetParam.getInnerDataList()) { // enhanced for statement
                result.add("enhanced_for_" + s);
            }

            // Static method feature: private modifier, 1, inner_class, static, superTypeReference.methodName(), pos=exception throwing, return TargetClass Type
            private static <V> TargetClass<V> staticHelperMethod(V value) {
                try {
                    // pos: exception throwing statements
                    if (value == null) {
                        throw new NullPointerException("Value is null"); // NullPointerException feature
                    }
                    // superTypeReference.methodName(arguments) (call super class method)
                    SuperSourceClass<V> superRef = new SuperSourceClass<>(value);
                    V superValue = superRef.getSuperValue();
                    
                    // method_feature: 1 (numeric literal)
                    TargetClass<V> target = new TargetClass<>(superValue + "_1"); // 1
                    // method_feature: inner_class (use target inner class)
                    target.innerClass.setValue(target.getValue());
                    return target;
                } catch (NullPointerException e) {
                    throw new NullPointerException("Static helper NPE: " + e.getMessage());
                }
            }
            // Call static helper method
            TargetClass<T> staticTarget = staticHelperMethod(targetParam.getValue());
            result.add("static_helper_" + staticTarget.getValue());

            // Depends on inner class (local inner class)
            class LocalInnerProcessor { // depends_on_inner_class
                void processResult() {
                    result.add("inner_processed_" + targetValue);
                }
            }
            new LocalInnerProcessor().processResult();

            // Used by reflection feature
            try {
                Method setMethod = TargetClass.T.class.getDeclaredMethod("setValue", Object.class);
                setMethod.setAccessible(true);
                setMethod.invoke(targetParam.innerClass, "reflection_" + targetValue);
                result.add("reflection_success");
            } catch (Exception e) {
                // Propagate NPE (requires_throws)
                throw new NullPointerException("Reflection error: " + e.getMessage());
            }

            // NullPointerException validation (requires_throws)
            if (result.isEmpty()) {
                throw new NullPointerException("Result list is empty");
            }

            return result;
        }

        // call_method: inner_class type, protected modifier, static, ClassName::methodName, pos=do-while, return void
        protected void callMethod(TargetClass<T> targetParam) {
            int count = 0;
            // pos: do-while statement
            do {
                // target_feature: static (call static nested class method via method reference)
                Function<TargetClass<T>, T> methodRef = StaticNestedClass::<T>processTarget; // ClassName::methodName
                result.add("method_ref_" + methodRef.apply(targetParam));
                count++;
            } while (count < 1); // align with method_feature: 1
        }
    }
}

// Target class: generic, public, same package as source, member inner class feature
public class TargetClass<U> {
    private U value;
    // Member inner class (target_feature)
    public class InnerClass {
        private U innerValue;

        public U getValue() {
            return innerValue;
        }

        public void setValue(U value) {
            this.innerValue = value;
        }
    }

    // Instance of inner class (used in source class variable call)
    public InnerClass innerClass = new InnerClass();

    public TargetClass(U initialValue) {
        this.value = initialValue;
        this.innerClass.setValue(initialValue);
    }

    public U getValue() {
        return value;
    }

    public List<String> getInnerDataList() {
        List<String> list = new ArrayList<>();
        list.add("inner1");
        list.add("inner2");
        return list;
    }
}