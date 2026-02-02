package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Target class (normal class, protected modifier, type parameter feature)
protected class TargetClass<T> {
    // Static field for ConstructorInvocation ClassName.field + 2
    public static final int STATIC_FIELD = 2;
    // Instance field for access_instance_field
    public T instanceField;

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        T innerField; // For per_condition (source contains this field)
        TargetInnerRec nestedInner; // Recursive feature

        public TargetInnerRec() {
            // Super constructor invocation (Object superclass)
            super();
        }

        public TargetInnerRec(T initialValue) {
            this.innerField = initialValue;
            this.nestedInner = new TargetInnerRec(); // Recursive constructor invocation
        }
    }
}

// Sealed parent class for source_class permits feature
sealed class SourceSealedParent permits SourceClass, SourceSubClass {}

// Source class (normal class, default modifier, same package, permits + two member inner classes)
final class SourceClass extends SourceSealedParent {
    // First member inner class (source_feature)
    class SourceMemberInnerOne {
        // ConstructorInvocation (private modifier, ClassName.field, 2, pos=same_package_target)
        private <T extends CharSequence> TargetClass<T>.TargetInnerRec constructorInvocation(T value) { // with_bounds (T extends CharSequence)
            TargetClass<T> outerTarget = new TargetClass<>();
            outerTarget.instanceField = value;
            // ClassName.field + 2 from target_feature
            TargetClass<T>.TargetInnerRec inner = outerTarget.new TargetInnerRec((T) (value + "_" + TargetClass.STATIC_FIELD));
            return inner;
        }
    }

    // Second member inner class (source_feature)
    class SourceMemberInnerTwo {
        // Inner recursive class (source_inner_rec for method_position)
        class SourceInnerRecursive {
            // Method to be refactored (instance, List<String> return, public access, source_inner_rec)
            public <T extends CharSequence> List<String> moveMethod(TargetClass<T>.TargetInnerRec targetParam) { // with_bounds
                List<String> result = new ArrayList<>();
                // Per_condition: contains target parameter (target_inner_rec)
                if (targetParam == null) {
                    throw new NullPointerException("TargetInnerRec cannot be null (2)"); // NullPointerException + 2
                }

                // ConstructorInvocation (pos=same_package_target)
                SourceMemberInnerOne innerOne = new SourceMemberInnerOne();
                TargetClass<T>.TargetInnerRec newInner = innerOne.constructorInvocation((T) ("init_" + TargetClass.STATIC_FIELD));

                // Super keywords (call SealedParent method)
                super.toString();

                // Variable call (access target inner field - per_condition)
                T varCall = targetParam.innerField;
                if (varCall == null) {
                    varCall = (T) ("default_" + TargetClass.STATIC_FIELD);
                }
                targetParam.innerField = (T) (varCall + "_var_modified_2");

                // Access instance field
                TargetClass<T> outerTarget = (TargetClass<T>) targetParam.getClass().getEnclosingInstance();
                outerTarget.instanceField = targetParam.innerField; // access_instance_field

                // Process recursive structure
                TargetClass<T>.TargetInnerRec current = targetParam;
                while (current != null) {
                    result.add(current.innerField != null ? current.innerField.toString() : "null");
                    current = current.nestedInner;
                }
                result.add(newInner.innerField.toString());

                // No new exception
                return result;
            }
        }
    }

    // Permits subclass implementation
    @Override
    public String toString() {
        return "SourceClass_" + TargetClass.STATIC_FIELD;
    }
}

// Permits subclass for SourceSealedParent
final class SourceSubClass extends SourceSealedParent {}