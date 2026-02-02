package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature
@interface AbstractMethodAnnotation {}

// Sub class of TargetClass for constructor feature (sub_class)
class SubTargetClass<T> extends TargetClass<T> {
    public SubTargetClass(T data) {
        super(data);
    }

    // SuperTypeReference.methodName() support
    public int baseTypeMethod(int val) {
        return val + 1; // method_feature "1"
    }
}

// Source class: generic class, private modifier, same package, type parameter, static nested class, member inner class
private abstract class SourceClass<T extends Number> { // Abstract method requires abstract class
    // per_condition: source contains target class field
    private TargetClass<T> targetField = new TargetClass<>(10);

    // Static nested class (source feature)
    static class StaticNestedSource<U> {
        U nestedField;
    }

    // Member inner recursive class (source_inner_rec - method position)
    public abstract class InnerRecursiveSource<U> {
        // Method: abstract, return TargetClass Type, final access (abstract + final is allowed in interface; adjusted for class)
        @AbstractMethodAnnotation // has_annotation feature
        public abstract TargetClass<T> moveableAbstractMethod();

        // Helper method containing constructor feature logic
        protected final TargetClass<T> methodLogic() {
            // Variable call feature
            T localVar = targetField.targetData();
            Integer rawVal = (Integer) localVar; // raw_type usage

            // Constructor feature: public modifier, method_feature [1, sub_class, constructor, superTypeReference.methodName()], pos=switch, return base type (int)
            int baseResult;
            switch (rawVal) {
                case 1: // method_feature "1"
                    TargetClass<T> subInstance = new SubTargetClass<>(localVar); // constructor invocation + sub_class
                    baseResult = ((SubTargetClass<T>) subInstance).baseTypeMethod(1); // superTypeReference.methodName(arguments)
                    break;
                default:
                    baseResult = rawVal.intValue();
            }

            // For statement feature
            for (int i = 0; i < baseResult; i++) {
                localVar = (T) Integer.valueOf(i);
            }

            // Raw_type feature
            List rawList = new ArrayList(); // raw type usage
            rawList.add(localVar);

            // Constructor invocation feature
            TargetClass<T> newTargetInstance = new TargetClass<>(localVar);

            // no_new_exception feature (no custom exceptions instantiated/thrown)
            return newTargetInstance;
        }
    }

    // Member inner class (source feature)
    class MemberInnerSource<V> {
        V innerField;
    }
}

// Target class: generic class, public modifier, same package, target_feature: static nested class
public class TargetClass<T> {
    T targetData;

    // Constructor for constructor invocation feature
    public TargetClass(T targetData) {
        this.targetData = targetData;
    }

    // Static nested class (target_feature)
    public static class StaticNestedTarget<U> {
        U nestedField;
    }

    // Getter for variable call feature
    public T targetData() {
        return targetData;
    }
}