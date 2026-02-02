package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Same package others class for IfStatement pos
class SamePackageHelper {
    public static void executeIfLogic(SourceClass sourceObj, TargetClass<?> targetParam) {
        sourceObj.ifLogic(targetParam);
    }
}

// Super class for super keywords usage
class SuperSourceClass {
    protected String superField = "superValue";
}

// Interface for target class implements feature
interface TargetInterface<T> {
    T getValue();
}

// Source class: public, same package as target, anonymous inner class, local inner class
public class SourceClass extends SuperSourceClass {
    // Per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>("initialValue");

    // IfStatement feature: protected modifier, ClassName.field, 3, pos=same_package_others
    protected void ifLogic(TargetClass<?> targetParam) {
        int threshold = 3; // target_feature: 3
        // ClassName.field usage (TargetClass.StaticNestedClass.staticField)
        if (targetParam.getStaticNestedField() == threshold) {
            targetParam.setValue("matched_" + threshold);
        }
    }

    // Method to refactor: varargs, TargetClass return, final access, no type parameters, in source class
    @RefactorAnnotation // has_annotation feature
    final TargetClass<String> methodToRefactor(Object... args) {
        // Variable call (target field and its members)
        String targetValue = targetField.getValue();
        targetValue += targetField.getStaticNestedField();

        // Constructor invocation (target class and its static nested class)
        TargetClass<String> newTarget = new TargetClass<>("newInstance");
        TargetClass.StaticNestedClass staticNested = new TargetClass.StaticNestedClass();

        // Super keywords (access super class field)
        targetValue += SuperSourceClass.super.superField;

        // Depends on inner class (local inner class)
        class LocalInnerClass { // local inner class (source feature)
            String processValue(String input) {
                return input + "_processedByInner";
            }
        }
        LocalInnerClass innerProcessor = new LocalInnerClass();
        newTarget.setValue(innerProcessor.processValue(targetValue));

        // IfStatement pos: same_package_others
        SamePackageHelper.executeIfLogic(this, newTarget);

        // Used by reflection feature
        try {
            Method setValueMethod = TargetClass.class.getDeclaredMethod("setValue", String.class);
            setValueMethod.setAccessible(true);
            setValueMethod.invoke(newTarget, targetValue + "_reflection");
        } catch (Exception e) {
            // No new exception feature (no throw new exception)
            newTarget.setValue("reflection_error");
        }

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(newTarget.getValue());
            }
        };
        anonymousRunnable.run();

        return newTarget; // TargetClass Type return
    }
}

// Target class: default modifier, type parameter, extends, implements, static nested class
class TargetClass<T> extends SuperSourceClass implements TargetInterface<T> {
    // Static nested class (target feature)
    public static class StaticNestedClass {
        public static int staticField = 3; // For IfStatement target_feature: 3 (ClassName.field)
    }

    private T value;

    public TargetClass(T initialValue) {
        super(); // super constructor for extends feature
        this.value = initialValue;
    }

    @Override
    public T getValue() { // implements feature
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getStaticNestedField() {
        return StaticNestedClass.staticField;
    }
}