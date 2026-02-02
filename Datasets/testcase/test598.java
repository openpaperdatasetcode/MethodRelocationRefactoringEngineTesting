package com.refactoring.movemethod;

// Permitted classes for source class permits feature
final class PermittedClass1 extends SourceClass {}
final class PermittedClass2 extends SourceClass {}

// Same package others class for ConstructorInvocation pos
class SamePackageHelper {
    public static <T> void invokeConstructor(SourceClass.InnerClass.RecursiveInnerClass innerObj, TargetClass targetParam) {
        innerObj.constructorLogic(targetParam);
    }
}

// Source class: abstract, non-sealed, permits, member inner class, local inner class, same package as target
public abstract non-sealed class SourceClass permits PermittedClass1, PermittedClass2 {
    // Static field for depends_on_static_field feature
    private static int staticField = 1; // For ConstructorInvocation target_feature: 1
    // Super class for super.field usage
    static class SuperSourceClass {
        protected int superField = 1; // target_feature: super.field
    }

    // Member inner class (source class feature)
    class InnerClass extends SuperSourceClass {
        // Recursive inner class (source_inner_rec - method position)
        class RecursiveInnerClass {
            // Abstract method to refactor: private access, TargetClass return, in source_inner_rec
            // Per_condition: contains target class parameter
            private abstract TargetClass methodToRefactor(TargetClass targetParam);

            // ConstructorInvocation feature: private modifier, super.field, 1, pos=same_package_others
            private void constructorLogic(TargetClass targetParam) {
                // super.field usage
                int count = super.superField; // target_feature: super.field
                // Constructor invocation with static field (target_feature: 1)
                TargetClass newTarget = new TargetClass();
                newTarget.setValue(count * SourceClass.staticField);
                // pos: same_package_others
                SamePackageHelper.invokeConstructor(this, newTarget);
            }

            // Recursion method feature: public modifier, 2, source, recursion, (parameters) -> methodBody, pos=expression, return base type
            public int recursiveMethod(int num) {
                // method_feature: 2
                if (num <= 2) return num;
                // (parameters) -> methodBody (lambda in expression position)
                java.util.function.IntUnaryOperator recursiveLambda = (n) -> recursiveMethod(n - 1) + recursiveMethod(n - 2);
                // pos: expression
                int result = recursiveLambda.applyAsInt(num);
                // depends_on_static_field
                result += SourceClass.staticField;
                return result; // base type return
            }

            // Expression statement + variable call + no_new_exception
            public void helperLogic(TargetClass targetParam) {
                // Variable call (target parameter field)
                String targetValue = targetParam.getValue();
                // Expression statement
                targetValue += "_processed_" + staticField;
                
                // No new exception feature
                try {
                    Integer.parseInt(targetValue);
                } catch (NumberFormatException e) {
                    // No throw new exception
                    targetValue = "default_" + staticField;
                }

                // Local inner class (source class feature)
                class LocalInnerClass {
                    void printValue() {
                        System.out.println(targetValue);
                    }
                }
                new LocalInnerClass().printValue();
            }
        }
    }
}

// Target class: abstract, no modifier, local inner class feature, same package as source
abstract class TargetClass {
    private String value = "targetValue";

    public String getValue() {
        // Local inner class (target_feature)
        class TargetLocalInnerClass {
            String processValue() {
                return value + "_local";
            }
        }
        return new TargetLocalInnerClass().processValue();
    }

    public void setValue(int num) {
        this.value = String.valueOf(num);
    }
}