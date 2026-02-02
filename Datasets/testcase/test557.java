package com.refactor;

import java.lang.reflect.Method;

// Source class: normal, default modifier, same package as target, two static nested classes
class SourceClass {
    // First static nested class (source feature)
    static class SourceStaticNested1 {}
    // Second static nested class (source feature)
    static class SourceStaticNested2 {}

    // Source inner recursive class (source_inner_rec - method position)
    class SourceInnerRec {
        // Overloading method 1: returns TargetClass type, private access, contains target parameter (per_condition)
        private TargetClass methodToMove(TargetClass targetParam) {
            return methodToMove(targetParam, "default");
        }

        // Overloading method 2 (overloading type), core method to refactor
        private TargetClass methodToMove(TargetClass targetParam, String str) {
            // Empty statement
            ;

            // Super constructor invocation (implicit super() for Object)
            super();

            // Super keywords usage
            super.toString();

            // Requires try-catch (for reflection)
            try {
                // Used by reflection
                Method method = SourceInnerRec.class.getDeclaredMethod("methodToMove", TargetClass.class, String.class);
                method.setAccessible(true);
                method.invoke(this, targetParam, str);

                // Variable call (target parameter access)
                String targetVar = targetParam.staticNestedField;

                // Depends on inner class (source inner recursive class dependency)
                SourceInnerRec.DependentInner dependent = new SourceInnerRec.DependentInner();
                dependent.useTarget(targetParam);
            } catch (Exception e) {
                // Requires try-catch, no new exception thrown
                return new TargetClass();
            }

            // Call method: sub_class type, public modifier, constructor + OuterClass.InnerClass.methodName() in instance code blocks
            TargetSubClass subInstance = new TargetSubClass() {
                {
                    // Instance code block (pos: instance code blocks)
                    TargetClass.StaticNested.staticMethod();
                }
            };

            // No new exception thrown
            return subInstance;
        }

        // Dependent inner class for depends_on_inner_class feature
        class DependentInner {
            void useTarget(TargetClass target) {}
        }
    }
}

// Target class: normal, strictfp modifier, implements interface + static nested class (target_feature)
strictfp class TargetClass implements SampleInterface {
    // Target field (used in variable call)
    public String staticNestedField = "targetValue";

    // Static nested class (target_feature)
    static class StaticNested {
        public static void staticMethod() {}
    }

    @Override
    public void interfaceMethod() {
        // Implements feature - interface method implementation
    }
}

// Interface for target class implements feature
interface SampleInterface {
    void interfaceMethod();
}

// Sub class for call_method (type: sub_class)
public class TargetSubClass extends TargetClass {
    // Call method: public modifier, constructor + OuterClass.InnerClass.methodName()
    public TargetSubClass() {
        super();
    }
}