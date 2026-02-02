package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Target class (normal class, empty modifier, member inner class feature)
class TargetClass {
    String targetField; // For per_condition (source contains this field)
    public static final int STATIC_FIELD = 1; // For IfStatement ClassName.field + 1

    // Target inner class (target_inner)
    public class TargetInner {
        String innerField;

        // Final static method for call_method feature
        public static final TargetClass staticMethod(TargetClass target) {
            // superTypeReference.methodName(arguments) (call Object method)
            target.targetField = target.targetField + "_superType_" + STATIC_FIELD;
            return target;
        }

        // Overload exist method 1
        public void updateInnerField(String value) {
            this.innerField = value + "_inner_1";
        }

        // Overload exist method 2 (overload_exist feature)
        public void updateInnerField(String value, boolean append) {
            this.innerField = append ? this.innerField + "_" + value : value;
        }

        public String getInnerField() {
            return innerField;
        }
    }

    // Constructor for super constructor invocation
    public TargetClass() {
        this.targetField = "default_target_1";
    }

    public TargetClass(String initialValue) {
        this.targetField = initialValue;
    }
}

// Source class (normal class, public modifier, same package, member inner + local inner class)
public class SourceClass {
    // Member inner class (source_feature)
    class SourceMemberInner {
        // Inner recursive class (source_inner_rec for method_position)
        class SourceInnerRecursive {
            // IfStatement (public modifier, ClassName.field, 1, pos=source)
            public void ifStatement(TargetClass.TargetInner targetInner) {
                // ClassName.field + 1 from target_feature
                if (TargetClass.STATIC_FIELD == 1) {
                    targetInner.updateInnerField("if_true_" + TargetClass.STATIC_FIELD);
                } else {
                    targetInner.updateInnerField("if_false_" + TargetClass.STATIC_FIELD);
                }
            }

            // call_method (target type, final modifier, static + superTypeReference.methodName(), pos=functional interfaces, return TargetClass Type)
            private final TargetClass callMethod(TargetClass target) {
                // pos=functional interfaces (lambda context)
                Function<TargetClass, TargetClass> processFunc = TargetClass.TargetInner::staticMethod;
                // superTypeReference.methodName(arguments) + static feature
                return processFunc.apply(target);
            }

            // Overload exist method 1 (process single target inner)
            public List<String> processTarget(TargetClass.TargetInner targetInner) {
                List<String> result = new ArrayList<>();
                result.add(targetInner.getInnerField());
                return result;
            }

            // Overload exist method 2 (overload_exist feature)
            public List<String> processTarget(TargetClass.TargetInner targetInner, int count) {
                List<String> result = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    result.add(targetInner.getInnerField() + "_overload_" + i);
                }
                return result;
            }

            // Method to be refactored (instance, List<String> return, default access, source_inner_rec position)
            public List<String> moveMethod(TargetClass.TargetInner targetParam) {
                List<String> result = new ArrayList<>();
                // Per_condition: contains target parameter (target_inner)
                if (targetParam == null) {
                    return result;
                }

                // Super constructor invocation (Object superclass)
                super.toString();

                // Constructor invocation
                TargetClass newTarget = new TargetClass("new_target_1");
                TargetClass.TargetInner newInner = newTarget.new TargetInner();

                // Variable call (access target inner field - per_condition)
                String varCall = targetParam.getInnerField();
                if (varCall == null) {
                    varCall = "default_var_1";
                    targetParam.updateInnerField(varCall);
                }

                // IfStatement invocation (pos=source)
                ifStatement(targetParam);

                // call_method invocation (pos=functional interfaces)
                TargetClass calledTarget = callMethod(newTarget);
                result.add("Called target field: " + calledTarget.targetField);

                // Enhanced for statement (typo as per input: enhganced)
                List<String> items = new ArrayList<>();
                items.add("item1");
                items.add("item2");
                items.add("item3");
                for (String item : items) {
                    if (item.equals("item2")) {
                        continue; // continue statement
                    }
                    targetParam.updateInnerField(varCall + "_" + item);
                    result.add(targetParam.getInnerField());
                }

                // Overload exist feature (call overloaded method)
                result.addAll(processTarget(targetParam));
                result.addAll(processTarget(targetParam, 2));

                // Local inner class (source_feature)
                class SourceLocalInner {
                    void finalizeProcessing(TargetClass.TargetInner inner) {
                        inner.updateInnerField(inner.getInnerField() + "_local_inner_1", true);
                    }
                }
                new SourceLocalInner().finalizeProcessing(targetParam);
                result.add("Final inner field: " + targetParam.getInnerField());

                // No new exception
                return result;
            }
        }
    }

    // Helper method to create inner recursive instance and invoke refactored method
    public List<String> executeMoveMethod(TargetClass.TargetInner targetInner) {
        SourceMemberInner memberInner = new SourceMemberInner();
        SourceMemberInner.SourceInnerRecursive innerRecursive = memberInner.new SourceInnerRecursive();
        return innerRecursive.moveMethod(targetInner);
    }
}