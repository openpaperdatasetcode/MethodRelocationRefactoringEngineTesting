package com.refactor;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

// Source class: normal, public, same package as target, static nested + local inner class
public class SourceClass {
    // Static nested class (source feature)
    static class SourceStaticNested {}

    // Source inner recursive class (source_inner_rec - method position)
    class SourceInnerRec {
        // Method to refactor: instance, List<String> return, default modifier, contains target parameter (per_condition)
        List<String> methodToMove(TargetClass<String>... targetParams) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
            // Type declaration statement
            class LocalTypeDeclaration {}
            LocalTypeDeclaration localTypeInstance = new LocalTypeDeclaration();

            // Used by reflection
            Method method = SourceInnerRec.class.getDeclaredMethod("methodToMove", TargetClass[].class);
            method.setAccessible(true);
            method.invoke(this, (Object) targetParams);

            // Variable call (target parameter access)
            List<String> result = new ArrayList<>();
            if (targetParams.length > 0) {
                result.add(targetParams[0].getValue());
            }

            // Local inner class (source feature)
            class SourceLocalInner {
                void localMethod() {}
            }
            new SourceLocalInner().localMethod();

            // Requires throws (reflection exceptions declared)
            return result;
        }
    }
}

// Target class: normal, public, type parameter + anonymous inner class (target_feature)
public class TargetClass<T> {
    private T value;

    public TargetClass(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(value);
        }
    };
}