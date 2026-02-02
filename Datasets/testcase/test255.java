package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Sub class for call_method feature (sub_class type)
class TargetEnumSubClass {
    // Chain methods for obj.m1().m2().m3() feature
    private TargetEnumSubClass m1() { return this; }
    private TargetEnumSubClass m2() { return this; }
    private List<String> m3() {
        List<String> result = new ArrayList<>();
        result.add("subClassResult");
        return result;
    }

    // Constructor feature for call_method
    public TargetEnumSubClass(TargetEnum.InnerTargetClass targetInner) {}
}

// Source class: enum class, strictfp modifier, same package, type parameter, local inner class, static nested class
strictfp enum SourceEnum<T extends CharSequence> { // Type parameter feature
    INSTANCE;

    // per_condition: source contains target enum field
    private TargetEnum targetField = TargetEnum.TARGET_INSTANCE;
    private String sourcePrivateField = "sourcePrivateVal"; // For access_outer_private

    // Static nested class (source feature)
    public static class StaticNestedSource<U> { // Type parameter in static nested class
        U nestedField;
    }

    // Abstract method holder (enum can't have abstract methods directly, use inner interface)
    private interface AbstractMethodHolder {
        // Method: abstract, return TargetEnum Type, private access (via inner interface), source position
        TargetEnum.InnerTargetClass moveableAbstractMethod(TargetEnum.InnerTargetClass targetParam);
    }

    // Implement abstract method in anonymous inner class
    private AbstractMethodHolder methodHolder = new AbstractMethodHolder() {
        @Override
        public TargetEnum.InnerTargetClass moveableAbstractMethod(TargetEnum.InnerTargetClass targetParam) { // per_condition: contains target_inner parameter
            // Variable call feature
            String localVar = targetParam.innerField;
            localVar = sourcePrivateField;

            // Access_outer_private feature (access outer enum's private field)
            localVar = SourceEnum.this.sourcePrivateField;

            // Local inner class (source feature)
            class LocalInnerSource {
                void updateTarget(TargetEnum.InnerTargetClass target) {
                    target.innerField = localVar;
                }
            }
            new LocalInnerSource().updateTarget(targetParam);

            // call_method: sub_class, private modifier, constructor, obj.m1().m2().m3(), pos=exception throwing statements, return List<String>
            private List<String> callMethodLogic() {
                List<String> callResult = null;
                try {
                    if (targetParam == null) {
                        throw new IllegalArgumentException("Null target parameter"); // exception throwing statements pos
                    }
                    // Constructor feature + obj.m1().m2().m3() chain call
                    TargetEnumSubClass subInstance = new TargetEnumSubClass(targetParam); // constructor
                    callResult = subInstance.m1().m2().m3(); // obj.m1().m2().m3()
                } catch (IllegalArgumentException e) {
                    callResult = new ArrayList<>();
                }
                return callResult;
            }
            List<String> subClassResult = callMethodLogic();
            targetParam.innerField = subClassResult.toString();

            // no_new_exception feature (no custom exceptions instantiated/thrown)
            return targetParam; // Return TargetClass Type (TargetEnum.InnerTargetClass)
        }
    };

    // Expose abstract method call
    public TargetEnum.InnerTargetClass callAbstractMethod(TargetEnum.InnerTargetClass targetParam) {
        return methodHolder.moveableAbstractMethod(targetParam);
    }
}

// Target class: enum class, private modifier, same package, target_feature: anonymous inner class
private enum TargetEnum {
    TARGET_INSTANCE;

    // Inner target class (target_inner - method's target class)
    class InnerTargetClass {
        String innerField = "innerTargetVal";
    }

    // Anonymous inner class (target_feature)
    void useAnonymousClass() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                new InnerTargetClass().innerField = "anonymousUpdated";
            }
        };
        anonymousRunnable.run();
    }
}