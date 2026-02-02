package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {}

// Source class: generic class, final modifier, same package, local inner class, static nested class
final class SourceClass<T extends Object> {
    protected String outerProtectedField = "outerProtectedValue";
    private String outerField = "outerFieldValue";

    // Static nested class (source feature)
    static class StaticNestedSource<U> {
        // Member inner class with recursive structure (source_inner_rec - method position)
        class InnerRecursiveClass<V> {
            // Method: instance, void return, private access, source_inner_rec position
            // per_condition: contains target class parameter (TargetClass.InnerRecursiveTarget)
            @MethodAnnotation // has_annotation feature
            private <W extends CharSequence> void moveableInstanceMethod(TargetClass<?>.InnerRecursiveTarget<W> targetParam) {
                // VariableDeclarationStatement: private, obj.field, 2, pos=inner class
                private class InnerVarDeclClass {
                    private int num = 2; // target_feature "2"
                    private String objField = targetParam.targetField; // target_feature "obj.field"
                }
                InnerVarDeclClass varDeclInstance = new InnerVarDeclClass();

                // Variable call feature
                String localVar = varDeclInstance.objField;
                localVar = SourceClass.this.outerField; // OuterClass.this.x feature

                // access_outer_protected feature
                String protectedVal = SourceClass.this.outerProtectedField;

                // access_instance_field feature
                String instanceFieldVal = targetParam.instanceField;

                // NullPointerException feature
                if (targetParam == null) {
                    throw new NullPointerException("Target parameter is null");
                }

                // method types parameter is:generic (method has generic type parameter W)
                W genericParam = targetParam.genericField;
                if (genericParam == null) return;

                // no_new_exception feature (no new custom exceptions thrown)
                System.out.println(genericParam.toString());
            }
        }

        // Local inner class (source feature)
        void methodWithLocalInner() {
            class LocalInnerSource {
                String localField = "localInnerValue";
            }
            LocalInnerSource localInner = new LocalInnerSource();
        }
    }
}

// Target class: generic class, private modifier, target_feature: static nested class
private class TargetClass<X extends Number> {
    // Static nested class (target_feature)
    static class StaticNestedTarget<Y> {
        Y nestedField;
    }

    // Inner recursive class (target_inner_rec - method's target class)
    class InnerRecursiveTarget<Z> {
        String targetField = "targetFieldValue";
        String instanceField = "instanceFieldValue";
        Z genericField;
    }
}