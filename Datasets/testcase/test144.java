package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Annotation for has_annotation feature
@interface MethodMetadata {}

// Source class: abstract class, abstract modifier, same package, type parameter, member inner class (duplicate feature as specified)
abstract class SourceClass<T extends CharSequence & Comparable<T>> { // type parameter with bounds (with_bounds)
    // per_condition: source contains target class field
    protected TargetClass<String> targetField = new TargetClass<>();
    private String sourceVar = "sourceValue";

    // Member inner class (source feature - duplicate as specified)
    class MemberInnerSource1 {
        // Member inner recursive class (source_inner_rec - method position)
        class MemberInnerRecursive {
            // Method: instance, return List<String>, default access, source_inner_rec position
            @MethodMetadata // has_annotation feature
            List<String> moveableInstanceMethod() {
                // Super constructor invocation (via inner class super)
                new SuperInnerClass();

                // Variable call feature
                String localVar = sourceVar;
                localVar = targetField.targetField;
                localVar = TargetClass.staticTargetMethod().toString();

                // Override_violation feature (attempt to override final method)
                class OverrideViolationClass extends TargetClass<String> {
                    @Override
                    public final void finalMethod() {} // Compile error (override violation)
                }

                // With_bounds feature (type parameter with bounds usage)
                T boundedParam = (T) "boundedValue";
                List<T> boundedList = new ArrayList<>();
                boundedList.add(boundedParam);

                // Used_by_reflection feature
                try {
                    Method method = this.getClass().getDeclaredMethod("moveableInstanceMethod");
                    method.setAccessible(true);
                    method.invoke(this);
                } catch (Exception e) {
                    // no_new_exception feature (no custom exceptions instantiated)
                    return new ArrayList<>();
                }

                // Call_method: target, public, static, new ClassName().method(), pos=property assignment, return Object
                Object assignedProperty = new TargetClass<String>().instanceTargetMethod();
                targetField.property = assignedProperty;

                // no_new_exception feature (no new exceptions thrown)
                List<String> result = new ArrayList<>();
                result.add(localVar);
                return result;
            }
        }
    }

    // Member inner class (source feature - duplicate as specified)
    class MemberInnerSource2 {
        void dummyMethod() {}
    }

    // Super inner class for super constructor invocation
    class SuperInnerClass {
        SuperInnerClass() {
            super(); // Super constructor invocation
        }
    }
}

// Target class: abstract class, protected modifier, target_feature: implements, local inner class
protected abstract class TargetClass<U extends Number> implements Runnable { // implements feature
    String targetField = "targetValue";
    Object property; // For call_method property assignment

    // Local inner class (target_feature)
    void methodWithLocalInner() {
        class LocalInnerTarget {
            String localField = "localInnerValue";
        }
        LocalInnerTarget localInner = new LocalInnerTarget();
    }

    // Target inner recursive class (target_inner_rec - method's target class)
    class TargetInnerRecursive {
        void recursiveMethod() {
            new TargetInnerRecursive().recursiveMethod(); // Recursive inner class usage
        }
    }

    // Call_method: public, static, return Object (target_feature: static)
    public static Object staticTargetMethod() {
        return "staticTargetValue";
    }

    // Call_method: new ClassName().method() (target_feature)
    public Object instanceTargetMethod() {
        return new TargetInnerRecursive();
    }

    // Final method for override_violation feature
    public final void finalMethod() {}

    @Override
    public void run() {} // Implements Runnable
}