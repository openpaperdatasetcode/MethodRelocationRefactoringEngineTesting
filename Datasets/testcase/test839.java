package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorTestAnnotation {}

// Target generic class: private modifier, empty target_feature, same package as source
class TargetContainer {
    private static class TargetClass<T> {
        public T instanceField;

        public TargetClass(T instanceField) {
            this.instanceField = instanceField;
        }

        // Overload method 1 for overload_exist feature
        public void process(T value) {
            this.instanceField = value;
        }

        // Overload method 2 for overload_exist feature
        public void process(T[] values) {
            this.instanceField = values[0];
        }
    }
}

// Source generic class: public modifier, type parameter, local inner + member inner class (source_feature)
public class SourceClass<S> {
    // Member inner class (source_feature)
    public class MemberInnerClass {
        public String innerData = "memberInnerData";

        public String getInnerData() {
            return innerData;
        }
    }

    // Instance method: public access, void return type, target parameter (per_condition)
    @RefactorTestAnnotation // has_annotation feature
    public void refactorMethod(TargetContainer.TargetClass<S> targetParam) {
        // ArrayCreation feature: numbers=1, public modifier, exp=ArrayCreation
        public S[] array = (S[]) new Object[1]; // 1 element for numbers=1

        // Variable call feature
        S varCall = targetParam.instanceField;

        // Access instance field feature
        targetParam.instanceField = (S) "updatedInstanceField";

        // Synchronized statement feature
        synchronized (this) {
            MemberInnerClass inner = new MemberInnerClass();
            System.out.println(inner.getInnerData());
        }

        // Switch statement feature
        int switchVal = varCall != null ? 1 : 0;
        switch (switchVal) {
            case 1:
                targetParam.process(varCall);
                break;
            case 0:
                targetParam.process(array);
                break;
            default:
                break;
        }

        // Overload_exist feature (call overloaded methods)
        targetParam.process(varCall);
        targetParam.process(array);

        // Local inner class (source_feature)
        class LocalInnerClass {
            public void printData() {
                System.out.println(targetParam.instanceField);
            }
        }
        new LocalInnerClass().printData();

        // No new exception thrown feature
    }
}