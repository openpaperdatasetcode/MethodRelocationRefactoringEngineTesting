package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// Annotation for has_annotation feature
@interface MethodAnnotation {}

// Source class: enum class, public modifier, same package, local inner class (duplicate feature)
public enum SourceEnum {
    INSTANCE;

    // Member inner class (method_position: source_inner)
    public class InnerSourceClass {
        // Method: normal, return List<String>, final access, source_inner position
        // per_condition: contains target enum parameter (TargetEnum.InnerTargetClass)
        @MethodAnnotation // has_annotation feature
        public final List<String> moveableMethod(TargetEnum.InnerTargetClass targetParam) {
            List<String> result = new ArrayList<>();
            String outerField = SourceEnum.this.name(); // uses_outer_this feature

            // Local inner class (source feature - duplicate)
            class LocalInnerSource1 {
                void helper() {
                    // ContinueStatement: static modifier, obj.field, 1, pos=same_package_target
                    static void continueLogic(TargetEnum.InnerTargetClass obj) {
                        int num = 1; // target_feature "1"
                        for (String s : Arrays.asList("a", "b", "c")) {
                            if (obj.targetField.equals(s)) { // target_feature "obj.field"
                                continue; // continue statement feature
                            }
                            result.add(s);
                        }
                    }
                }
            }

            // Local inner class (source feature - duplicate)
            class LocalInnerSource2 {
                void dummy() {}
            }

            // Invoke continue statement logic (same_package_target position)
            LocalInnerSource1 local1 = new LocalInnerSource1();
            local1.helper();
            LocalInnerSource1.continueLogic(targetParam);

            // Constructor: protected modifier, method_feature [2, source, constructor, method ref], pos=switch, return List<String>
            switch (targetParam.ordinal()) {
                case 2: // method_feature "2"
                    TargetEnum.InnerTargetClass newInstance = new TargetEnum.InnerTargetClass(); // constructor invocation feature
                    result = newInstance::buildList; // instanceReference::methodName
                    break;
                default:
                    result.add(outerField);
            }

            // Variable call feature
            String localVar = targetParam.targetField;
            result.add(localVar);

            // Return statement feature
            return result; // no_new_exception feature (no custom exceptions instantiated)
        }
    }
}

// Target class: enum class, protected modifier, target_feature: javadoc, local inner class
protected enum TargetEnum {
    /**
     * Javadoc feature for target enum
     * This is a sample javadoc comment
     */
    TARGET_INSTANCE;

    // Inner target class (target_inner - method's target class)
    public class InnerTargetClass {
        String targetField = "targetValue";

        // Constructor invocation support
        protected InnerTargetClass() {} // constructor feature (protected modifier)

        // Method reference support (instanceReference::methodName)
        List<String> buildList() { // return_type List<String>
            return new ArrayList<>(Arrays.asList("2", "source", "constructor")); // method_feature values
        }

        // Local inner class (target_feature)
        void methodWithLocalInner() {
            class LocalInnerTarget {
                int value = 2;
            }
            LocalInnerTarget local = new LocalInnerTarget();
        }
    }
}