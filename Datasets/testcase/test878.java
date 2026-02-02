package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Source class (private modifier, same package, type parameter + two local inner classes)
private class SourceClass<T extends Number> {
    private T typeParamField;

    // Inner recursive class (source_inner_rec)
    class SourceInnerRecursive {
        // Method to be refactored (instance, void return, public access)
        @RefactorAnnotation // has_annotation feature
        public void moveMethod(TargetClass targetParam) {
            // Per_condition: source contains target's field
            int targetField = targetParam.targetField;

            // ExpressionStatement (private modifier, ClassName.field, 1, pos=same_package_target)
            private void expressionStatement() {
                TargetClass.staticField = 1; // ClassName.field + 1 from target_feature
                targetParam.targetField = TargetClass.staticField;
            }
            expressionStatement();

            // Empty statement feature
            ;

            // If statement
            if (targetField > 0) {
                targetParam.targetField *= 2;
            }

            // Constructor invocation
            TargetClass.TargetMemberInner memberInner = targetParam.new TargetMemberInner();
            FirstLocalInner firstLocal = new FirstLocalInner();

            // Type declaration statement
            SecondLocalInner secondLocal = new SecondLocalInner();

            // Variable call
            int varCall = firstLocal.processValue(targetField);
            targetParam.targetField = varCall;

            // Depends_on_inner_class (uses local inner classes)
            secondLocal.updateTarget(targetParam);

            // Array initialization with call_method (pos:array initialization)
            TargetClass[] targetArray = {new InnerCallClass().callMethod(targetParam)};

            // No new exception
        }

        // First local inner class (source feature)
        class FirstLocalInner {
            int processValue(int val) {
                return val + 1; // 1 from ExpressionStatement target_feature
            }
        }

        // Second local inner class (source feature - duplicate local inner class)
        class SecondLocalInner {
            void updateTarget(TargetClass target) {
                target.targetField += 10;
            }
        }

        // Inner class for call_method (inner_class type)
        class InnerCallClass {
            // Base method for overriding
            protected TargetClass callMethod(TargetClass param) {
                return param;
            }

            // call_method (private modifier, overriding + instanceReference::methodName)
            @Override
            private TargetClass callMethod(TargetClass param) {
                // instanceReference::methodName feature
                param.new TargetMemberInner().processField(param::getTargetField);
                return param;
            }
        }
    }
}

// Target class (abstract modifier, member inner class feature)
abstract class TargetClass {
    static int staticField; // For ExpressionStatement ClassName.field
    int targetField; // For variable call/access (per_condition)

    // Member inner class (target_feature)
    public class TargetMemberInner {
        void processField(java.util.function.Supplier<Integer> supplier) {
            targetField = supplier.get() * 2;
        }
    }

    // Getter for instanceReference::methodName
    public Integer getTargetField() {
        return this.targetField;
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}