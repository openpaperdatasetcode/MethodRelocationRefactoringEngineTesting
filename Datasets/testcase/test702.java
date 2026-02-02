package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Super class for source class extends feature
sealed class SuperSourceClass permits SourceClass, OtherPermittedClass {}

// Permitted class for source class permits feature
final class OtherPermittedClass extends SuperSourceClass {}

// Source class: protected, extends, permits, static nested class, anonymous inner class, same package as target
protected sealed class SourceClass extends SuperSourceClass permits OtherPermittedClass {
    // Static nested class (source class feature)
    static class SourceStaticNestedClass {
        String innerData = "innerData";
    }

    // Overloading method 1: default access, TargetClass return, contains target parameter (per_condition)
    TargetClass<String> methodToRefactor(TargetClass<String> targetParam) {
        // Variable call (target parameter field)
        String targetValue = targetParam.nestedClass.getData();
        
        // Depends on inner class (use static nested class)
        SourceStaticNestedClass innerObj = new SourceStaticNestedClass();
        targetValue += innerObj.innerData;
        
        // While statement
        int count = 5;
        while (count > 0) {
            targetValue += count;
            count--;
        }
        
        // Used by reflection
        try {
            Method method = TargetClass.class.getDeclaredMethod("getNestedClass");
            method.setAccessible(true);
            method.invoke(targetParam);
        } catch (Exception e) {
            // No new exception (no throw new)
            targetValue = "reflection_error";
        }
        
        // Anonymous inner class (source class feature)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetValue);
            }
        };
        runnable.run();
        
        return targetParam;
    }

    // Overloading method 2 (overloading feature)
    TargetClass<Integer> methodToRefactor(TargetClass<Integer> targetParam, int extra) {
        // No new exception
        try {
            targetParam.nestedClass.setData(String.valueOf(extra));
        } catch (NullPointerException e) {
            // No throw new exception
        }
        return targetParam;
    }
}

// Target class: protected, type parameter, static nested class, same package as source
protected class TargetClass<T> {
    // Static nested class (target class feature)
    static class TargetStaticNestedClass {
        private String data = "targetData";
        
        String getData() {
            return data;
        }
        
        void setData(String data) {
            this.data = data;
        }
    }

    // Field for static nested class
    TargetStaticNestedClass nestedClass = new TargetStaticNestedClass();

    // Getter for nested class (used in reflection)
    TargetStaticNestedClass getNestedClass() {
        return nestedClass;
    }
}