// Target class package (different from source)
package com.target;

// Super class for TargetClass extends feature
class TargetSuperClass {
    protected int superField = 1; // For PrefixExpression numbers=1
}

// Target class: default modifier (empty modifier), extends + member inner class (target_feature)
class TargetClass extends TargetSuperClass {
    public int instanceField = 5; // For access_instance_field feature

    // Member inner class (target_feature)
    public class TargetInner {
        private String innerData;

        public TargetInner(String innerData) {
            this.innerData = innerData;
        }

        public String getInnerData() {
            return innerData;
        }

        // Method for override_violation feature
        public void process(int val) {}
    }

    // Method for override_violation (invalid signature)
    public void process(String val) {}
}

// Source class package (different from target)
package com.source;

import com.target.TargetClass;

// Source class: public modifier, static nested + member inner class (source_feature)
public class SourceClass {
    // Protected outer field for access_outer_protected feature
    protected int outerProtectedField = 10;

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static String formatData(String data) {
            return "formatted_" + data;
        }
    }

    // Member inner class (source_feature)
    public class SourceMemberInner {
        public int calculate(int a, int b) {
            return a + b;
        }
    }

    // Normal method: default access, Object return type, target parameter (per_condition)
    Object refactorMethod(TargetClass targetParam) {
        // Variable call feature
        int varCall = targetParam.instanceField;
        TargetClass.TargetInner targetInner = targetParam.new TargetInner("test_data");

        // Access outer protected feature
        int outerProtected = this.outerProtectedField;

        // Access instance field feature
        int instanceFieldVal = targetParam.instanceField;

        // PrefixExpression feature: numbers=1, protected modifier, exp=PrefixExpression
        protected int prefixResult = ++targetParam.superField; // PrefixExpression (++) with 1 (superField initial value)

        // If statement feature
        String ifResult = "";
        if (prefixResult == 1) {
            ifResult = targetInner.getInnerData();
        } else {
            ifResult = SourceStaticNested.formatData(targetInner.getInnerData());
        }

        // Switch statement feature
        int switchVal = varCall;
        String switchResult = "";
        switch (switchVal) {
            case 5:
                switchResult = "Case 5: " + instanceFieldVal;
                break;
            case 10:
                switchResult = "Case 10: " + outerProtected;
                break;
            default:
                switchResult = "Default case";
        }

        // Override_violation feature (invalid override signature)
        TargetClass.TargetInner invalidOverride = new TargetClass.TargetInner("invalid") {
            @Override
            public void process(int val) { // override_violation (mismatched parameter type vs parent process(String))
                superField = val;
            }
        };

        // Use member inner class
        SourceMemberInner inner = new SourceMemberInner();
        int calcResult = inner.calculate(prefixResult, outerProtected);

        // No new exception thrown feature
        return new Object() {
            @Override
            public String toString() {
                return String.format("ifResult: %s, switchResult: %s, calcResult: %d", 
                        ifResult, switchResult, calcResult);
            }
        };
    }
}