package com.refactoring.movemethod;

// Source class: normal, strictfp modifier, same package, local inner + member inner classes
strictfp class SourceClass {
    // Member inner class (source feature)
    private class SourceMemberInner {
        private String innerField = "source_member_inner_6131";
    }

    // Method to be refactored: instance, base type (int) return, public access, source position
    // method types parameter is:base type (int) + target class parameter
    public int methodToMove(int baseParam, TargetClass targetParam) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            // NullPointerException feature
            throw new NullPointerException("TargetClass parameter cannot be null (ID:6131)");
        }

        // variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerField;

        // Local inner class (source feature) - declared in refactored method
        class SourceLocalInner {
            private int processBaseType(int val) {
                return val * 6131;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        int processedVal = localInner.processBaseType(baseParam);

        // no_new_exception (no explicit new Exception instantiation beyond NPE requirement)
        return processedVal + varCall.length() + targetParam.getTargetValue();
    }
}

// Target class: normal, public modifier, local inner class (target_feature)
public class TargetClass {
    private int targetValue = 6131;

    public int getTargetValue() {
        // Local inner class (target_feature)
        class TargetLocalInner {
            private int validateValue(int val) {
                return val > 0 ? val : 0;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        return localInner.validateValue(targetValue);
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }
}