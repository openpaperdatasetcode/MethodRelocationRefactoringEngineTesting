package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Source abstract class (default modifier, same package, type parameter + local inner + member inner class)
abstract class SourceClass<T extends CharSequence> {
    // Member inner class (source feature)
    class SourceMemberInner {
        String innerField = "memberInnerValue";

        // Inner class for method position (source_inner)
        class SourceInnerClass {
            // Method to be refactored (varargs, Object return, default access)
            @RefactorAnnotation // has_annotation feature
            Object moveMethod(TargetClass<T>.TargetInnerRec... targetParams) {
                // Per_condition: contains target parameter (targetParams)
                if (targetParams == null || targetParams.length == 0) {
                    return null;
                }

                // Type declaration statement
                LocalInnerProcessor<T> processor = new LocalInnerProcessor<>();
                TargetClass<T>.TargetInnerRec firstTarget = targetParams[0];

                // Variable call
                String varCall = firstTarget.innerField;
                firstTarget.innerField = varCall + "_modified";

                // Depends_on_inner_class (uses local inner class)
                processor.processTarget(firstTarget);

                // Override violation
                class OverrideViolation extends TargetClass<T>.TargetInnerRec {
                    // Invalid override (different return type)
                    @Override
                    public int getValue() { // Parent returns String - violation
                        return innerField.length();
                    }
                }

                // No new exception
                return firstTarget;
            }

            // Local inner class (source feature, used for depends_on_inner_class)
            class LocalInnerProcessor<U> {
                void processTarget(TargetClass<U>.TargetInnerRec target) {
                    target.innerField = "processed_" + target.innerField;
                }
            }
        }
    }

    // Abstract method (required for abstract class)
    public abstract T getSourceValue();
}

// Target abstract class (protected modifier, type parameter + local inner class)
protected abstract class TargetClass<V> {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        String innerField;

        // Method for override violation
        public String getValue() {
            return innerField;
        }

        // Local inner class (target feature)
        class TargetLocalInner {
            void validateField() {
                if (innerField == null) {
                    innerField = "default";
                }
            }
        }
    }

    // Abstract method (required for abstract class)
    public abstract V getTargetValue();
}