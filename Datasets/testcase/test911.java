// Target class package (different from source)
package com.target;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorMethodAnnotation {}

// Target inner record (target_inner_rec - target class of method)
public record TargetInnerRec(String id, int value) {}

// Target class: default modifier, local inner class (target_feature)
class TargetClass<T> {
    public T targetData;

    public TargetClass(T targetData) {
        this.targetData = targetData;
    }

    // Local inner class (target_feature)
    public TargetInnerRec processData() {
        class LocalInnerClass {
            private TargetInnerRec generateRec() {
                return new TargetInnerRec("REC_" + targetData, (Integer) targetData);
            }
        }
        return new LocalInnerClass().generateRec();
    }

    // Overload method 1 for overload_exist feature
    public int calculate(T data) {
        return (Integer) data * 2;
    }

    // Overload method 2 for overload_exist feature
    public int calculate(String data) {
        return data.length();
    }
}

// Source class package (different from target)
package com.source;

import com.target.TargetClass;
import com.target.TargetInnerRec;
import com.target.RefactorMethodAnnotation;

// Source abstract class: abstract modifier, type parameter (source_feature)
public abstract class SourceClass<T> {
    // Private outer field for access_outer_private feature
    private int outerPrivateField = 5;

    // Inner class (source_inner - method_position)
    public class SourceInner {
        // Instance method: private access, void return type, target parameter (per_condition)
        @RefactorMethodAnnotation // has_annotation feature
        private void refactorMethod(TargetClass<T> targetParam) {
            // Variable call feature
            T varCall = targetParam.targetData;
            TargetInnerRec innerRec = targetParam.processData();

            // Access outer private feature
            int outerPrivate = SourceClass.this.outerPrivateField;

            // Switch statement feature
            int switchVal = (Integer) varCall;
            switch (switchVal) {
                case 1:
                    System.out.println("Case 1: " + innerRec.id());
                    break;
                case 2:
                    System.out.println("Case 2: " + innerRec.value());
                    break;
                default:
                    System.out.println("Default case");
            }

            // Overload_exist feature (call overloaded methods)
            int overload1 = targetParam.calculate(varCall);
            int overload2 = targetParam.calculate(innerRec.id());

            // Requires_try_catch feature
            try {
                if (overload1 < 0 || overload2 < 0) {
                    throw new IllegalArgumentException("Invalid calculation result");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Handled exception: " + e.getMessage());
            }

            // No new exception thrown (only handle existing ones)
            System.out.println("Outer private: " + outerPrivate + ", Overload 1: " + overload1 + ", Overload 2: " + overload2);
        }
    }
}