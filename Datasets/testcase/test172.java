package com.refactoring.movemethod;

// Source class: normal class, public modifier, same package, empty features
public class SourceClass {
    // Member inner class (method_position: source_inner)
    class InnerSourceClass {
        // Method: overloading, return TargetClass Type, public access, source_inner position
        // per_condition: contains target class parameter (TargetClass)
        public TargetClass overloadingMethod(TargetClass targetParam) {
            // Variable call feature
            String localVar = targetParam.targetField;
            localVar = "updatedValue";

            // Expression statement feature
            targetParam.targetField = localVar;
            System.out.println(targetParam.targetField);

            // No_new_exception feature (no custom exceptions instantiated)
            return targetParam;
        }

        // Overloading method (overloading type feature)
        public TargetClass overloadingMethod(TargetClass targetParam, String additionalParam) {
            // Variable call feature
            String localVar = additionalParam + targetParam.targetField;
            
            // Expression statement feature
            targetParam.targetField = localVar;
            
            // No_new_exception feature
            return targetParam;
        }
    }
}

// Target class: normal class, public modifier, target_feature: static nested class
public class TargetClass {
    String targetField = "targetValue";

    // Static nested class (target_feature)
    static class StaticNestedTarget {
        void nestedMethod() {}
    }
}