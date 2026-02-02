package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {}

// Parent class for target class extends feature
class TargetParentClass {}

// Interface for target class implements feature
interface TargetInterface {
    int targetInterfaceMethod();
}

// Source class (public modifier, same package, type parameter + local inner + member inner class)
public class SourceClass<T extends CharSequence> {
    private T typeParamField;

    // Member inner class (source feature)
    public class SourceMemberInner {
        // Inner recursive class (source_inner_rec)
        class SourceInnerRecursive {
            private int instanceField = 42; // For access_instance_field

            // Method to be refactored (instance, void return, default access)
            @MethodAnnotation // has_annotation feature (duplicated as per spec)
            void moveMethod(TargetClass targetParam) throws Exception {
                // Per_condition: contains target parameter
                if (targetParam == null) {
                    throw new IllegalArgumentException("Target parameter is null");
                }

                // Super constructor invocation
                TargetParentClass superInstance = new TargetParentClass();

                // Variable call + access_instance_field
                int varCall = this.instanceField;
                targetParam.targetField = varCall;

                // Local inner class (source feature)
                class LocalInnerClass {
                    void processTarget(TargetClass target) {
                        target.targetField *= 2;
                    }
                }
                new LocalInnerClass().processTarget(targetParam);

                // Requires throws (method declares throws Exception)
                // Array initialization with call_method (pos: array initialization)
                int[] intArray = {targetParam.callMethod(10), targetParam.callMethod(20)};

                // No new exception beyond declared throws
            }
        }
    }
}

// Target class (protected modifier, extends + implements + member inner class)
protected class TargetClass extends TargetParentClass implements TargetInterface {
    int targetField; // For variable call/access_instance_field

    // Member inner class (target feature)
    public class TargetMemberInner {
        int innerField;
    }

    // Overridden method from interface (for overriding feature)
    @Override
    public int targetInterfaceMethod() {
        return this.targetField;
    }

    // call_method (target type, private modifier, overriding + this.methodName(arguments))
    private int callMethod(int param) {
        // this.methodName(arguments) feature
        return this.targetInterfaceMethod() + param;
    }
}