package com.refactoring.movemethod;

import java.lang.reflect.Method;

// Super class for source_class extends feature
class SuperSourceClass {
    protected String superField = "superValue";
}

// Source class: normal class, default modifier, same package, extends, local inner class, member inner class
class SourceClass extends SuperSourceClass {
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Static field for depends_on_static_field feature
    private static String staticSourceField = "staticInitValue";

    // Member inner class (source feature)
    class MemberInnerSource {
        // Call_method: inner_class, protected modifier, overloading, outerInstance.new InnerClass().methodName(), return int
        protected int innerMethod() {
            return 1; // method_feature "1"
        }

        // Overloading method for call_method feature
        protected int innerMethod(String param) {
            return param.length() + 1;
        }
    }

    // Method: overloading, return base type (int), protected access, source position
    protected int moveableMethod() {
        // Variable call feature
        String localVar = staticSourceField;
        localVar = targetField.targetField;
        localVar = targetField.new InnerTargetClass().innerField;

        // Instance feature: private modifier, method_feature [1, source, instance, new ClassName().method()], pos=array initialization, return void
        private void instanceLogic() {
            Runnable[] runnables = new Runnable[]{
                () -> new SourceClass().new MemberInnerSource().innerMethod() // new ClassName().method() + array initialization pos
            };
            int num = 1; // method_feature "1"
            localVar = num + "_source"; // method_feature "source", "instance"
        }
        instanceLogic();

        // Depends_on_static_field feature
        staticSourceField = localVar + "_updatedStatic";

        // Used_by_reflection feature
        try {
            Method method = this.getClass().getDeclaredMethod("moveableMethod");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception e) {
            // no_new_exception feature (no custom exceptions instantiated)
            return 0;
        }

        // Call_method: inner_class, pos=exception throwing statements, return int
        int callResult;
        try {
            if (localVar.isEmpty()) {
                throw new IllegalArgumentException("Empty variable"); // exception throwing statements pos
            }
            // outerInstance.new InnerClass().methodName()
            callResult = this.new MemberInnerSource().innerMethod(localVar); // overloading feature
        } catch (IllegalArgumentException e) {
            callResult = this.new MemberInnerSource().innerMethod(); // overloading feature
        }

        // Local inner class (source feature)
        class LocalInnerSource {
            void updateVar() {
                localVar = String.valueOf(callResult);
            }
        }
        new LocalInnerSource().updateVar();

        // no_new_exception feature (no custom exceptions thrown)
        return callResult; // base type (int) return
    }

    // Overloading method (overloading type feature)
    protected int moveableMethod(String additionalParam) {
        // Variable call feature
        String localVar = targetField.targetField + additionalParam;
        
        // no_new_exception feature
        return localVar.length();
    }
}

// Target class: normal class, public modifier, same package, target_feature: javadoc, static nested class
/**
 * Javadoc feature for TargetClass
 * This class contains static nested class and inner target class for method targeting
 */
public class TargetClass {
    String targetField = "targetInitValue";

    // Inner target class (target_inner - method's target class)
    class InnerTargetClass {
        String innerField = "innerTargetValue";
    }

    // Static nested class (target_feature)
    public static class StaticNestedTarget {
        static int staticNestedValue = 1;
    }
}