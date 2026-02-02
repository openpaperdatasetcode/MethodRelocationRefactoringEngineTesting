package com.refactoring.movemethod;

// Others class for static method_feature (others_class)
class OthersClass {
    // Static method for instanceReference.methodName(arguments) + method_feature "3"
    public static TargetClass staticMethod(String arg) {
        TargetClass target = new TargetClass();
        target.setTargetField(arg + "_3"); // method_feature "3"
        return target; // return TargetClass Type
    }
}

// Source class: normal class, private modifier, same package, anonymous inner class, local inner class
private class SourceClass {
    // Member inner class (method_position: source_inner)
    public abstract class InnerSourceClass<T extends Number & Comparable<T>> { // with_bounds feature
        /**
         * Method javadoc feature
         * Abstract strictfp method processing TargetClass with static feature
         * @param targetParam the target class parameter (per_condition)
         * @return void (abstract method return type)
         */
        @Override
        strictfp public abstract void moveableAbstractMethod(TargetClass targetParam); // abstract + strictfp + void return

        // Static feature: protected modifier, method_feature [3, others_class, static, instanceReference.methodName()], pos=object initialization, return TargetClass Type
        protected static <U extends Number & Comparable<U>> TargetClass staticLogic(U param) { // with_bounds
            // pos=object initialization
            TargetClass instanceRef = new TargetClass(); // object initialization
            // instanceReference.methodName(arguments) + others_class + static + "3"
            TargetClass result = OthersClass.staticMethod(instanceRef.getTargetField() + "_3");
            // Expression statement feature
            result.setTargetField(result.getTargetField() + "_expr3"); // expression statement
            return result; // return TargetClass Type
        }

        // Helper method for feature demonstration
        public void helperMethod(TargetClass targetParam) {
            // Variable call feature
            String localVar = targetParam.getTargetField();
            localVar = String.valueOf(3); // method_feature "3"

            // Expression statement feature
            localVar = localVar.toUpperCase(); // expression statement
            targetParam.setTargetField(localVar); // expression statement

            // With_bounds feature (use bounded generic type)
            T boundedVal = (T) Integer.valueOf(3); // method_feature "3" + with_bounds
            localVar += boundedVal.toString();

            // Anonymous inner class (source feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    localVar += "_anonymous3"; // method_feature "3"
                }
            };
            anonymousRunnable.run();

            // Local inner class (source feature)
            class LocalInnerSource {
                void updateTarget(TargetClass target) {
                    target.setTargetField(localVar);
                }
            }
            new LocalInnerSource().updateTarget(targetParam);

            // no_new_exception feature (no custom exceptions instantiated/thrown)
        }
    }

    // Concrete implementation of abstract inner class
    public class ConcreteInnerSource extends InnerSourceClass<Integer> {
        @Override
        public void moveableAbstractMethod(TargetClass targetParam) {
            helperMethod(targetParam);
            staticLogic(3); // method_feature "3"
        }
    }
}

// Target class: normal class, private modifier, same package, target_feature: javadoc
/**
 * Javadoc feature for TargetClass
 * This private normal class is the target for the abstract method in SourceClass
 */
private class TargetClass {
    private String targetField = "targetInit3"; // method_feature "3"

    // Accessor methods for variable call
    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }
}