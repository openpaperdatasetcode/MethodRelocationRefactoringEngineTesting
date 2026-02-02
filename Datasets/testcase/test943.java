package com.refactoring.movemethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

// Super class for overriding method
abstract class SuperSourceClass<T extends Number> {
    public abstract TargetClass<T> processTarget(TargetClass<T> param) throws Exception;
}

// Generic source class (public modifier, same package)
public class SourceClass<T extends Number & Comparable<T>> extends SuperSourceClass<T> {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "PRIVATE_DATA";

    // First anonymous inner class (source feature)
    Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            TargetClass<T> target = new TargetClass<>();
            try {
                processTarget(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    // Second anonymous inner class (source feature)
    Function<T, String> anonymousInner2 = new Function<>() {
        @Override
        public String apply(T t) {
            return t.toString() + outerPrivateField;
        }
    };

    // Overriding method (public access, return TargetClass Type, source position)
    @Override
    public TargetClass<T> processTarget(TargetClass<T> targetParam) throws Exception {
        // Per_condition: contains target parameter (variable call)
        T targetValue = targetParam.getValue();
        
        // Super constructor invocation (via anonymous subclass)
        SuperClass superObj = new SuperClass() {};

        // NullPointerException handling
        if (targetValue == null) {
            throw new NullPointerException("Target value is null");
        }

        // Functional interfaces (pos for generic method)
        Function<TargetClass<T>, Integer> func = this::protectedGenericMethod;
        int genericResult = func.apply(targetParam);

        // Access outer private field
        targetParam.setAuxField(outerPrivateField + "_" + genericResult);

        // Requires_throws: declare checked exception (Exception)
        // Used_by_reflection: access target class via reflection
        try {
            Class<?> targetClazz = TargetClass.class;
            Constructor<?> constructor = targetClazz.getConstructor();
            Object targetObj = constructor.newInstance();
            Method method = targetClazz.getMethod("setValue", Number.class);
            method.invoke(targetObj, targetValue);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new Exception("Reflection error", e);
        }

        return targetParam;
    }

    // Generic method (protected modifier, base type return, functional interfaces pos)
    protected <U extends TargetClass<T>> int protectedGenericMethod(U target) {
        // 1: literal value usage
        int literal = 1;
        // new ClassName().method()
        T value = new TargetClass<T>().getValue();
        // target feature: use target class instance
        return value.intValue() + literal;
    }
}

// Generic target class (public modifier)
public class TargetClass<T extends Number> {
    // Anonymous inner class (target feature)
    Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            setValue(0);
        }
    };

    private T value;
    private String auxField;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getAuxField() {
        return auxField;
    }

    public void setAuxField(String auxField) {
        this.auxField = auxField;
    }
}

// Super class for super constructor invocation
class SuperClass {
    public SuperClass() {}
}