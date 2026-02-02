package com.refactoring.test;

import java.sql.SQLException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Consumer;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Source class (private modifier, same package, type parameter + two static nested classes)
private class SourceClass<T extends Number> {
    // First static nested class (source feature)
    static class StaticNestedOne {}

    // Second static nested class (source feature)
    static class StaticNestedTwo {
        // Inner recursive class (source_inner_rec)
        class InnerRecursiveClass<T> {
            private int innerField = 1;

            // Method to be refactored (generic, void return, protected access)
            @RefactorAnnotation // has_annotation feature
            protected <U extends String> void moveMethod(TargetClass.TargetInnerRec targetParam) {
                // Per_condition: source contains target's field
                int targetField = targetParam.targetField;

                // Variable call
                int varCall = this.innerField;
                targetParam.targetField = varCall + targetField;

                // Raw type feature
                java.util.List rawList = new java.util.ArrayList();
                rawList.add(targetField);

                // ConstructorInvocation (private modifier, super.field, 1, pos=source)
                private class ConstructorInvocationClass extends SuperClass {
                    public ConstructorInvocationClass() {
                        super.field = 1; // super.field + 1 from target_feature
                    }
                }
                new ConstructorInvocationClass();

                // SuperMethodInvocation (numbers:1, modifier:default, exp:SuperMethodInvocation)
                default void superMethodInvocation() {
                    super.toString(); // Super method invocation
                }
                superMethodInvocation();

                // this(arguments) feature
                new InnerRecursiveClass<>(this.innerField);

                // SQLException feature (no new exception - declared only)
                try {
                    if (targetParam.targetField < 1) {
                        throw new SQLException("Invalid target field value");
                    }
                } catch (SQLException e) {
                    // Functional interface with call_method (pos: functional interfaces)
                    Consumer<TargetClass.TargetInnerRec> consumer = (t) -> targetParam.callMethod();
                    consumer.accept(targetParam);
                }

                // No new exception (only declared SQLException, no new custom exceptions)
            }

            // Constructor for this(arguments)
            public InnerRecursiveClass(int val) {
                this.innerField = val;
            }
        }
    }

    // Super class for ConstructorInvocation super.field
    static class SuperClass {
        int field;
    }
}

// Target class (private modifier, anonymous inner class feature)
private class TargetClass {
    // Target inner recursive class (target_inner_rec)
    public static class TargetInnerRec {
        int targetField;

        // call_method (target type, default modifier, constructor + new ClassName().method())
        void callMethod() {
            // Constructor feature + new ClassName().method()
            TargetInnerRec inner = new TargetInnerRec();
            inner.processField();

            // Anonymous inner class (target_feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    targetField = 1;
                }
            };
            anonymous.run();
        }

        private void processField() {
            this.targetField *= 2;
        }
    }
}