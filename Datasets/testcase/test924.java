package com.refactoring.test;

// Interface for target class implements feature
interface TargetInterface {
    void processField();
}

// Target class (normal class, protected modifier, implements + static nested class)
protected class TargetClass implements TargetInterface {
    // Static nested class (target_feature)
    public static class TargetStaticNested {}

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        int innerField; // For per_condition (source contains this field)
        TargetInnerRec nestedInner; // Recursive inner class feature

        @Override
        public void processField() {
            this.innerField *= 2;
        }
    }

    @Override
    public void processField() {}
}

// Source class (normal class, default modifier, same package, member inner + static nested class)
class SourceClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        static int helperVal = 3; // For numbers:3 feature
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // Method to be refactored (instance, TargetClass.TargetInnerRec return, private access, source_inner position)
        private TargetClass.TargetInnerRec moveMethod(TargetClass.TargetInnerRec targetParam) throws IllegalArgumentException { // requires_throws
            // Per_condition: source contains the field of the target (access targetParam.innerField)
            if (targetParam == null) {
                throw new IllegalArgumentException("Target parameter cannot be null"); // requires_throws
            }

            // Do statement feature
            int count = 0;
            do {
                targetParam.innerField += count;
                count++;
            } while (count < 3); // 3 from numbers feature

            // Expression statement feature
            targetParam.innerField = targetParam.innerField + SourceStaticNested.helperVal;

            // NullLiteral feature (numbers:3, modifier:public, exp:NullLiteral)
            public void nullLiteralMethod() {
                targetParam.nestedInner = null; // NullLiteral expression
                if (targetParam.nestedInner == null) {
                    targetParam.innerField = 3; // 3 from numbers feature
                }
            }
            nullLiteralMethod();

            // Variable call (access target field - per_condition)
            int varCall = targetParam.innerField;
            targetParam.innerField = varCall + 3;

            // Recursive inner class initialization
            if (targetParam.nestedInner == null) {
                targetParam.nestedInner = new TargetClass().new TargetInnerRec();
                targetParam.nestedInner.innerField = targetParam.innerField;
            }

            // Requires throws (only declared exception, no new exception thrown)
            return targetParam;
        }
    }
}