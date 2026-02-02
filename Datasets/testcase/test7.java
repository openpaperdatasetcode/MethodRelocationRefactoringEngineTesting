package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.ArrayList;

// Same package others class for SwitchStatement pos=same_package_others
class SamePackageOthers {
    public static int switchVal = 1;
}

// Source class: normal class, public modifier, different package with target, permits, static nested, member inner
public sealed class SourceClass permits SourceClass.StaticNestedSource {
    private String sourceField = "sourceFieldValue"; // this.field for SwitchStatement

    // Static nested class (source feature)
    static final class StaticNestedSource extends SourceClass {}

    // Member inner class (source feature)
    class MemberInnerSource {
        void innerMethod() {}
    }

    // Method: overloading, return Object, private access, source position
    // per_condition: contains target class parameter (TargetClass<T>)
    private <T> Object moveableMethod(TargetClass<T> targetParam) {
        // Variable call feature
        String localVar = sourceField;
        localVar = targetParam.targetField;

        // SwitchStatement: private modifier, this.field, 1, pos=same_package_others
        private void switchLogic() {
            switch (SamePackageOthers.switchVal) { // pos=same_package_others
                case 1: // target_feature "1"
                    localVar = SourceClass.this.sourceField; // target_feature "this.field"
                    break;
                default:
                    localVar = "default";
            }
        }
        switchLogic();

        // Constructor invocation feature
        TargetClass<String> newTargetInstance = new TargetClass<>("newInstanceValue");

        // Do statement feature
        int counter = 0;
        do {
            localVar += counter++;
        } while (counter < 3);

        // Super keywords feature
        class SuperClassUsage extends SourceClass {
            void useSuper() {
                super.sourceField = "superValue"; // super keywords
            }
        }
        new SuperClassUsage().useSuper();

        // Requires_try_catch feature
        try {
            int parsedVal = Integer.parseInt(localVar);
            newTargetInstance.setTargetField(String.valueOf(parsedVal));
        } catch (NumberFormatException e) {
            localVar = "0";
        }

        return newTargetInstance;
    }

    // Overloading method (overloading type feature)
    private <T> Object moveableMethod(TargetClass<T> targetParam, String additionalParam) {
        // Variable call feature
        String localVar = targetParam.getTargetField() + additionalParam;
        
        // Requires_try_catch feature
        try {
            return Integer.valueOf(localVar);
        } catch (NumberFormatException e) {
            return localVar;
        }
    }
}