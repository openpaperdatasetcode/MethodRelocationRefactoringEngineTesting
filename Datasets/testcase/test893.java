package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * Target generic class with javadoc (target_feature)
 * Extends ParentClass, contains static nested class
 * @param <T> Generic type parameter
 */
class TargetClass<T> extends ParentClass<T> {
    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        U nestedField;
    }

    /**
     * Target inner class (target_inner)
     * For per_condition and variable call
     * @param <V> Inner generic type
     */
    public class TargetInner<V> {
        V innerField; // obj.field for IfStatement feature

        // Final method for override_violation
        public final void finalMethod() {}
    }
}

// Parent class for varargs method_feature "parent_class"
class ParentClass<T> {
    T parentField;
}

// Source generic class (public modifier, same package, two anonymous inner classes)
public class SourceClass<T extends CharSequence> { // with_bounds (T extends CharSequence)
    private String outerPrivateField = "outerPrivateValue"; // access_outer_private

    // Inner recursive class (source_inner_rec)
    class SourceInnerRecursive<U> {
        // Instance code block for varargs pos=instance code blocks
        {
            // Varargs method (public modifier, method_feature:3/parent_class/varargs/outerInstance.new InnerClass().methodName())
            public <V> TargetClass<V>.TargetInner<V> varargsMethod(TargetClass<V> outerInstance, V... values) {
                super.toString(); // parent_class reference
                TargetClass<V>.TargetInner<V> inner = outerInstance.new TargetInner<>(); // outerInstance.new InnerClass()
                inner.innerField = values.length > 0 ? values[0] : (V) "3"; // 3 from method_feature
                inner.innerField = (V) inner.innerField.toString() + "_processed";
                return inner; // return TargetClass Type
            }
        }

        // IfStatement (protected modifier, obj.field, 2, pos=same_package_target)
        protected <V> void ifStatement(TargetClass<V>.TargetInner<V> target) {
            if (target.innerField == null || !target.innerField.toString().contains("2")) { // obj.field + 2
                target.innerField = (V) "value_2";
            }
        }

        // Method to be refactored (instance, TargetClass Type return, default access, source_inner_rec)
        <V> TargetClass<V>.TargetInner<V> moveMethod(TargetClass<V>.TargetInner<V> targetParam) {
            // Per_condition: contains target parameter
            if (targetParam == null) {
                TargetClass<V> outer = new TargetClass<>();
                return outer.new TargetInner<>();
            }

            // First if statement (IfStatement feature)
            ifStatement(targetParam);

            // Second if statement (additional if statement feature)
            if (targetParam.innerField != null) {
                targetParam.innerField = (V) targetParam.innerField.toString() + "_if_modified";
            }

            // Type declaration statement
            TargetClass<T>.TargetStaticNested<String> staticNested = new TargetClass<>().new TargetStaticNested<>();
            Consumer<TargetClass<V>.TargetInner<V>> consumer = t -> {};

            // Used by reflection
            try {
                Method innerMethod = TargetClass.TargetInner.class.getDeclaredMethod("finalMethod");
                innerMethod.invoke(targetParam);
            } catch (Exception e) {
                // No new exception (handle reflection exceptions internally)
            }

            // Variable call
            V varCall = targetParam.innerField; // Access target field (per_condition)
            targetParam.innerField = (V) varCall.toString() + "_var_call";

            // Access outer private field
            String privateVal = SourceClass.this.outerPrivateField;
            targetParam.innerField = (V) targetParam.innerField.toString() + "_" + privateVal;

            // Override violation (attempt to override final method)
            class OverrideViolationClass extends TargetClass<V>.TargetInner<V> {
                @Override
                public void finalMethod() {} // Compile error (override_violation)
            }

            // Varargs method call (instance code blocks pos)
            TargetClass<V> outerInstance = new TargetClass<>();
            targetParam = varargsMethod(outerInstance, (V) "arg1", (V) "arg2");

            // First anonymous inner class (source_feature)
            Runnable anonymous1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println(targetParam.innerField);
                }
            };
            anonymous1.run();

            // Second anonymous inner class (duplicate anonymous inner class feature)
            Runnable anonymous2 = new Runnable() {
                @Override
                public void run() {
                    staticNested.nestedField = "anonymous2_processed";
                }
            };
            anonymous2.run();

            // No new exception
            return targetParam;
        }
    }
}