package com.refactoring.test;

import java.lang.reflect.Method;

// Others class for generic method_feature "others_class"
class OthersClass {
    public static <T> int processInnerField(TargetClass.TargetInner inner) {
        return inner.innerField + 1; // 1 from method_feature
    }
}

// Target class (normal class, public modifier, member inner class feature)
public class TargetClass {
    // Target inner class (target_inner)
    public class TargetInner {
        int innerField; // For per_condition and variable call
    }
}

// Source class (normal class, private modifier, same package, local inner + member inner class)
private class SourceClass {
    // Member inner class (source_feature)
    class SourceMemberInner {
        // Inner recursive class (source_inner_rec)
        class SourceInnerRecursive {
            // Generic method (default modifier, method_feature:1/others_class/generic/OuterClass.InnerClass.methodName(), pos:Lambda expressions, return base type)
            <T> int genericMethod(TargetClass.TargetInner target) {
                // Lambda expressions position
                Runnable lambda = () -> {
                    // OuterClass.InnerClass.methodName()
                    int val = OthersClass.processInnerField(target); // 1 from method_feature
                    target.innerField = val;
                };
                lambda.run();
                return target.innerField;
            }

            // Overloading method 1 (type:overloading)
            private void moveMethod(TargetClass.TargetInner targetParam) {
                moveMethod(targetParam, 1);
            }

            // Overloading method 2 (main refactoring method, void return, private access, source_inner_rec)
            private void moveMethod(TargetClass.TargetInner targetParam, int num) {
                // Per_condition: contains target parameter
                if (targetParam == null) {
                    return;
                }

                // Generic method call (pos:Lambda expressions)
                int genericResult = genericMethod(targetParam);

                // Constructor invocation
                TargetClass targetInstance = new TargetClass();
                TargetClass.TargetInner newInner = targetInstance.new TargetInner();

                // Variable call
                targetParam.innerField = newInner.innerField + genericResult + num; // Access target field (per_condition)

                // Used by reflection
                try {
                    Method getClassMethod = TargetClass.TargetInner.class.getMethod("getClass");
                    getClassMethod.invoke(targetParam);
                    targetParam.innerField = 100;
                } catch (Exception e) {
                    // No new exception (handle reflection exceptions internally)
                }

                // Local inner class (source_feature)
                class LocalInnerClass {
                    void updateInnerField(TargetClass.TargetInner inner) {
                        inner.innerField += 5;
                    }
                }
                new LocalInnerClass().updateInnerField(targetParam);

                // No new exception
            }
        }
    }
}