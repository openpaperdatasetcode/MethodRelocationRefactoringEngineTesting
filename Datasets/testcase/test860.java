package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Interface for target class implements feature
interface TargetInterface {
    default String getFieldValue() {
        return "default";
    }
}

// Source class (protected modifier, same package, member inner + static nested class)
protected class SourceClass {
    private String outerPrivateField = "privateOuterValue"; // For access_outer_private

    // Static nested class (source feature)
    static class SourceStaticNested {
        // Member inner class (source feature)
        class SourceInnerClass {
            private int instanceField = 42; // For access_instance_field

            // Method to be refactored (instance, void return, private access, source_inner position)
            private void moveMethod(TargetClass targetParam) {
                // Per_condition: contains target parameter
                if (targetParam == null) {
                    throw new IllegalArgumentException("Target parameter cannot be null"); // exception throwing statements
                }

                // Static nested method (type:static, modifier:protected, method_feature:1/target/static/super.methodName())
                protected static List<String> staticMethod(TargetClass target) {
                    super.toString(); // super.methodName()
                    List<String> list = new ArrayList<>();
                    list.add(target.targetField);
                    list.add(String.valueOf(1)); // 1 from method_feature
                    return list;
                }

                // Call static method (pos: exception throwing statements)
                List<String> staticResult = staticMethod(targetParam);

                // Type declaration statement
                InnerClassProcessor processor = new InnerClassProcessor();

                // ExpressionMethodReference (numbers:1, modifier:public, exp:ExpressionMethodReference)
                public Function<String, Integer> methodRef = String::length;
                int refResult = methodRef.apply(targetParam.targetField) + 1; // 1 from numbers

                // Variable call + access_outer_private + access_instance_field
                String varCall = SourceClass.this.outerPrivateField;
                int instanceVal = this.instanceField;
                targetParam.targetField = varCall + "_" + instanceVal + "_" + refResult;

                // Depends_on_inner_class (uses member inner class)
                processor.processTarget(targetParam);

                // No new exception (only pre-defined IllegalArgumentException)
            }

            // Inner class for depends_on_inner_class feature
            class InnerClassProcessor {
                void processTarget(TargetClass target) {
                    target.targetField = "processed_" + target.targetField;
                }
            }
        }
    }
}

// Target class (protected modifier, implements + member inner class)
protected class TargetClass implements TargetInterface {
    String targetField; // For variable call/access

    // Member inner class (target_feature)
    public class TargetMemberInner {
        void updateField(String value) {
            TargetClass.this.targetField = value;
        }
    }
}