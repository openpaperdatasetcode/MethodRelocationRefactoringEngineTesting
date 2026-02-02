package com.refactoring.movemethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

// Supporting interface for target enum implements feature
interface TargetEnumInterface {}

// Source enum class (default modifier, same package, two anonymous inner classes)
enum SourceEnum {
    INSTANCE1, INSTANCE2;

    // Source inner record (method_position: source_inner_rec)
    record SourceInnerRec<T>(T data) {
        // Normal method (default access, Object return, generic type parameter)
        <U extends Number & Comparable<U>> Object processTarget(AbstractTargetEnum<U> targetParam) {
            // Per_condition: contains target parameter (variable call)
            U targetValue = targetParam.getTargetValue();
            
            // With_bounds: use generic type with bounds (U extends Number & Comparable<U>)
            SourceInnerRec<U> boundedRec = new SourceInnerRec<>(targetValue);
            
            // Access instance field of target
            targetParam.instanceField = "accessed_field";

            // Used_by_reflection: access target enum via reflection
            Object result = null;
            try {
                Class<?> targetClazz = AbstractTargetEnum.class;
                Field field = targetClazz.getDeclaredField("instanceField");
                field.setAccessible(true);
                String fieldVal = (String) field.get(targetParam);

                Method method = targetClazz.getDeclaredMethod("getTargetValue");
                result = method.invoke(targetParam);
            } catch (NoSuchFieldException | IllegalAccessException |
                     NoSuchMethodException | InvocationTargetException e) {
                // No_new_exception: catch without throwing new exception
                result = "reflection_error";
            }

            // No_new_exception (fallback try-catch)
            try {
                return boundedRec.data();
            } catch (Exception e) {
                return result;
            }
        }
    }

    // First anonymous inner class (source feature)
    Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            SourceInnerRec<Integer> rec = new SourceInnerRec<>(1);
            rec.processTarget(AbstractTargetEnum.TARGET_INSTANCE);
        }
    };

    // Second anonymous inner class (source feature)
    List<String> anonymousInner2 = new java.util.ArrayList<>() {{
        add(SourceInnerRec.class.getSimpleName());
    }};
}

// Target abstract enum class (abstract modifier, type parameter, implements, member inner class)
abstract enum AbstractTargetEnum<T extends CharSequence> implements TargetEnumInterface {
    TARGET_INSTANCE;

    // Instance field for access_instance_field feature
    String instanceField;
    private T targetValue;

    // Member inner class (target feature)
    class TargetMemberInner {
        void updateValue(T value) {
            AbstractTargetEnum.this.targetValue = value;
        }
    }

    // Getter for target value (variable call support)
    public T getTargetValue() {
        return targetValue;
    }

    // Abstract method (required for abstract enum)
    public abstract void abstractMethod();
}