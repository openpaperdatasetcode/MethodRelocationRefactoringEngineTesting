package com.refactoring.test;

import java.lang.reflect.Method;

// Others class for static method_feature "others_class"
class OthersClass {
    // Static method (protected modifier, method_feature:3/others_class/static/ClassName.methodName(), pos=constructor params, return Object)
    protected static Object staticMethod(TargetClass target) {
        return target.targetField + "_static_" + 3;
    }
}

// Target class (normal class, protected modifier, anonymous inner class feature)
protected class TargetClass {
    String targetField; // For per_condition (source contains this field)

    // Anonymous inner class (target_feature)
    private final Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            targetField += "_anonymous_" + 3;
        }
    };

    // Constructor with parameter list (for static method pos)
    public TargetClass(Object initVal) {
        this.targetField = initVal.toString() + "_ctor_" + 3;
    }

    public void invokeAnonymous() {
        anonymousRunnable.run();
    }
}

// Source class (normal class, public modifier, same package, two member inner classes)
public class SourceClass {
    // First member inner class (source_feature)
    class SourceMemberInnerOne {
        // Inner recursive class (source_inner_rec for method_position)
        class SourceInnerRecursive {
            // Overloading method 1
            final void moveMethod(TargetClass targetParam, String suffix) {
                moveMethod(targetParam);
            }

            /**
             * Method javadoc for refactored method
             * Processes target parameter and demonstrates overloading + static method features
             * @param targetParam TargetClass instance (per_condition: contains target parameter)
             */
            // Overloading method 2 (method to be refactored)
            final void moveMethod(TargetClass targetParam) {
                // Per_condition: contains target parameter
                if (targetParam == null) {
                    return; // return statement
                }

                // Static method call (pos=constructor parameter list)
                TargetClass newTarget = new TargetClass(OthersClass.staticMethod(targetParam)); // ClassName.methodName(arguments) + 3

                // Variable call (access target field - per_condition)
                String varCall = targetParam.targetField;
                targetParam.targetField = varCall + "_var_modified_" + 3;

                // Used by reflection
                try {
                    Method invokeMethod = TargetClass.class.getMethod("invokeAnonymous");
                    invokeMethod.invoke(targetParam);
                    Method getFieldMethod = TargetClass.class.getDeclaredField("targetField");
                    getFieldMethod.setAccessible(true);
                    getFieldMethod.set(targetParam, getFieldMethod.get(targetParam) + "_reflection_" + 3);
                } catch (Exception e) {
                    // No new exception
                }

                // Invoke target's anonymous inner class
                targetParam.invokeAnonymous();

                // return statement (early return example)
                if (targetParam.targetField.length() > 10) {
                    return;
                }

                // No new exception
            }
        }
    }

    // Second member inner class (source_feature)
    class SourceMemberInnerTwo {
        static void helperMethod(TargetClass target) {
            target.targetField += "_member_inner_two_" + 3;
        }
    }
}