// Others class for call_method feature
package com.refactoring.others;
import com.refactoring.movemethod.TargetClass;

public class OthersClass<T> {
    // call_method: others_class type, protected modifier, accessor, OuterClass.InnerClass.methodName(), pos=array initialization, return int
    protected int callMethod(TargetClass<T>.TargetInnerRec... inners) {
        // pos: array initialization
        TargetClass<T>.TargetInnerRec[] innerArray = (TargetClass<T>.TargetInnerRec[]) new TargetClass.TargetInnerRec[3]; // target_feature: 3
        
        int sum = 0;
        for (TargetClass<T>.TargetInnerRec inner : inners) {
            // accessor feature (getter) + OuterClass.InnerClass.methodName()
            sum += TargetClass.TargetInnerRec.class.getSimpleName().length() + inner.getInnerValue().toString().length();
        }
        return sum;
    }
}

// Back to source package
package com.refactoring.movemethod;

import com.refactoring.others.OthersClass;

// Source class: abstract generic class, same package as target, no extra features
abstract class SourceClass<T extends CharSequence> {
    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClass<>((T) "initial_target_3");

    // ConstructorInvocation feature: private modifier, obj.field, 3, pos=source, return Object
    private TargetClass<T>.TargetInnerRec createInnerRec(TargetClass<T> outer) {
        TargetClass<T>.TargetInnerRec inner = outer.new TargetInnerRec();
        inner.innerField = 3; // obj.field feature, target_feature: 3
        return inner;
    }

    // Method to refactor: instance, Object return, protected access, in source
    protected Object methodToRefactor(TargetClass<T>.TargetInnerRec innerParam) {
        // Per_condition: method contains target parameter
        if (innerParam == null) {
            innerParam = createInnerRec(targetField); // ConstructorInvocation call (pos=source)
        }

        // Type declaration statement
        class ProcessedType<U> {
            private U value;
            ProcessedType(U val) { this.value = val; }
            U getVal() { return value; }
        }

        // Variable call (target inner recursive class)
        T innerValue = innerParam.getInnerValue();
        
        // Type declaration statement usage
        ProcessedType<T> processed = new ProcessedType<>(innerValue);
        innerParam.setInnerValue(processed.getVal());

        // requires_try_catch feature
        try {
            // obj.field access (target_feature: 3)
            int fieldValue = innerParam.innerField;
            innerParam.setInnerValue((T) (innerValue + "_field_" + fieldValue));
            
            // Call call_method (others_class type)
            int callResult = new OthersClass<T>().callMethod(innerParam, targetField.getInnerRec());
            innerParam.setInnerValue((T) (innerParam.getInnerValue() + "_call_" + callResult));
        } catch (Exception e) {
            // requires_try_catch handling
            innerParam.setInnerValue((T) "safe_value_3"); // target_feature: 3
        }

        // Variable call for targetField (per_condition)
        innerParam.setInnerValue((T) (innerParam.getInnerValue() + "_" + targetField.getValue()));

        return innerParam.getInnerValue();
    }

    // Abstract method (required for abstract source class)
    public abstract T abstractSourceMethod();
}

// Target class: private generic class, member inner class feature
private class TargetClass<T> {
    private T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Member inner class (target_feature)
    public class TargetInnerRec {
        // obj.field feature (target_feature: 3)
        int innerField;
        private T innerValue;

        public TargetInnerRec() {
            this.innerValue = (T) "inner_rec_3"; // target_feature: 3
        }

        // Variable call getters/setters (accessor feature)
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Getter for inner recursive class (per_condition variable call)
    public TargetInnerRec getInnerRec() {
        return new TargetInnerRec();
    }

    public T getValue() {
        return value;
    }
}