package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

/**
 * TargetClass with javadoc (target_feature) and member inner class
 * Private modifier, normal class type
 */
private class TargetClass {
    String targetField; // For per_condition and variable call

    // Member inner class (target_feature)
    public class TargetMemberInner {
        void updateField(String value) {
            TargetClass.this.targetField = value;
        }

        // Method for override_violation feature (final method to cause override violation)
        public final void finalMethod() {}
    }
}

// Source class (normal class, default modifier, same package, type parameter + member inner + local inner class)
class SourceClass<T extends CharSequence> {
    private T typeParamField;

    // Member inner class (source_feature)
    class SourceMemberInner {
        // Inner recursive class (source_inner_rec)
        class SourceInnerRecursive {
            // Method attempting to override final method (override_violation)
            class OverrideViolationClass extends TargetClass.TargetMemberInner {
                // Override violation: attempting to override final method
                @Override
                public void finalMethod() {} // Compile error expected (override_violation)
            }

            // Method to be refactored (normal, List<String> return, private access, source_inner_rec)
            private List<String> moveMethod(TargetClass targetParam) {
                // Per_condition: contains target parameter
                List<String> result = new ArrayList<>();
                if (targetParam == null) {
                    // Throw statement feature
                    throw new IllegalArgumentException("Target parameter cannot be null");
                }

                // Variable call feature
                String varCall = targetParam.targetField;
                if (varCall == null) {
                    varCall = "default_value";
                }

                // Requires try-catch feature
                try {
                    TargetClass.TargetMemberInner inner = targetParam.new TargetMemberInner();
                    inner.updateField(varCall + "_modified");
                    result.add(targetParam.targetField);
                } catch (Exception e) {
                    result.add("Exception caught: " + e.getMessage());
                }

                // Local inner class (source_feature)
                class LocalInnerClass {
                    void processResult(List<String> list) {
                        list.add("processed_by_local_inner");
                    }
                }
                new LocalInnerClass().processResult(result);

                return result;
            }
        }
    }
}