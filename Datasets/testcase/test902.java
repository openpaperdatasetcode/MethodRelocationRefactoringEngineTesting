package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Super class for super constructor invocation
class GenericSuperClass<T> {
    protected T superData;

    public GenericSuperClass(T superData) {
        this.superData = superData;
    }
}

// Target generic class: protected modifier, type parameter + local inner class (target_feature)
protected class TargetClass<T> {
    public T objField = (T) Integer.valueOf(2); // obj.field + 2 for VariableDeclarationStatement

    // Local inner class (target_feature)
    public void process(T data) {
        class LocalInnerClass {
            private T innerData;

            public LocalInnerClass(T innerData) {
                this.innerData = innerData;
            }

            public T getData() {
                return innerData;
            }
        }
        LocalInnerClass inner = new LocalInnerClass(data);
        System.out.println(inner.getData());
    }

    // Method for override_violation (invalid override signature)
    public void overrideMethod(List<T> list) {}
}

// Source generic class: protected modifier, extends, two static nested classes (source_feature)
protected class SourceClass<T> extends GenericSuperClass<T> {
    // Source contains target field (per_condition)
    private TargetClass<T> targetField = new TargetClass<>();

    // First static nested class (source_feature)
    public static class FirstStaticNested<U> {
        public U nestedData;

        public FirstStaticNested(U nestedData) {
            this.nestedData = nestedData;
        }
    }

    // Second static nested class (source_feature)
    public static class SecondStaticNested<V> {
        public static <V> void staticMethod(V data) {
            System.out.println(data);
        }
    }

    // VariableDeclarationStatement feature: private modifier, obj.field, 2, pos=source
    private void variableDeclarationFeature() {
        T localVar = targetField.objField; // obj.field + 2, pos=source
    }

    // Overloading method 1 (no parameters)
    public void refactorMethod() {
        // Super constructor invocation (method feature)
        super((T) "superValue");

        // Type declaration statement (method feature)
        List<T> typeDecl;
        typeDecl = new ArrayList<>();

        // Expression statement (method feature)
        typeDecl.add(targetField.objField);

        // Variable call feature
        T varCall = targetField.objField;

        // Execute VariableDeclarationStatement feature
        variableDeclarationFeature();

        // Try statement (method feature)
        try {
            // Override violation (invalid parameter type for override)
            TargetClass<T> invalidOverride = new TargetClass<T>() {
                @Override
                public void overrideMethod(List<String> list) {} // override_violation (parameter mismatch)
            };
        } catch (Exception e) {
            // No new exception thrown feature
        }

        // No new exception thrown feature
    }

    // Overloading method 2 (with parameter) - overloading feature
    public void refactorMethod(T data) {
        targetField.process(data);
        SecondStaticNested.staticMethod(data);
    }
}