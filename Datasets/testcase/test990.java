package com.refactoring.test;

// Target class (normal class, public modifier, type parameter + member inner class features)
public class TargetClass<T> {
    T targetField; // For per_condition (source contains this field)
    int thisField = 2; // For AssertStatement this.field + 2

    // Member inner class (target_feature)
    public class TargetInnerClass {
        void updateField(T value) {
            TargetClass.this.targetField = value;
        }
    }
}

// Source class (normal class, final modifier, same package, member inner + local inner class)
final class SourceClass {
    // Member inner class (source_feature, source_inner for method_position)
    class SourceInnerClass {
        // AssertStatement (private modifier, this.field, 2, pos=source)
        private <T> void assertStatement(TargetClass<T> target) {
            assert target.thisField == 2 : "this.field must be 2"; // this.field + 2
            assert target.targetField != null : "targetField cannot be null (2)";
        }

        // Overloading method 1
        Object moveMethod(TargetClass<String> targetParam) {
            return moveMethod(targetParam, "default_2");
        }

        // Overloading method 2 (method to be refactored)
        <T> Object moveMethod(TargetClass<T> targetParam, T defaultValue) {
            // Per_condition: contains target parameter
            if (targetParam == null) {
                return defaultValue;
            }

            // Type declaration statement
            TargetClass<T>.TargetInnerClass targetInner = targetParam.new TargetInnerClass();
            T typeVar;

            // AssertStatement invocation (pos=source)
            assertStatement(targetParam);

            // For statement
            for (int i = 0; i < 2; i++) { // 2 from target_feature
                targetInner.updateField((T) (defaultValue + "_loop_" + i));
            }

            // Super constructor invocation (Object superclass)
            Object superObj = new Object();
            superObj.toString();

            // Variable call (access target field - per_condition)
            T varCall = targetParam.targetField;
            targetParam.targetField = (T) (varCall + "_var_modified_2");

            // Local inner class (source_feature)
            class SourceLocalInner {
                T getProcessedValue(T value) {
                    return (T) (value + "_local_inner_2");
                }
            }
            SourceLocalInner localInner = new SourceLocalInner();
            T result = localInner.getProcessedValue(targetParam.targetField);

            // No new exception
            return result;
        }
    }
}