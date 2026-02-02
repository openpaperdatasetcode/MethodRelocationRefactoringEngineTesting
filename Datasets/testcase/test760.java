package com.refactoring.movemethod;

// Source class: generic, public, same package as target, no additional features
public class SourceClass<T> {
    // Recursive inner class (for method_position: source_inner_rec)
    public class SourceInnerRecursive {
        private String innerField = "sourceInnerField";

        // Method to be refactored: instance, TargetClass Type return, final access, in source_inner_rec
        public final TargetClass<T>.TargetInnerRecursive methodToMove(TargetClass<T>.TargetInnerRecursive targetParam) {
            // per_condition: method contains target inner recursive class parameter
            if (targetParam == null) {
                return new TargetClass<T>().new TargetInnerRecursive();
            }

            // Super constructor invocation (via inner class super)
            super();

            // this.methodName(arguments) feature
            this.innerHelperMethod(targetParam.innerValue);

            // Variable call feature
            String varCall = this.innerField;
            targetParam.innerData = varCall;

            // No_new_exception (no explicit new Exception instantiation)
            if (targetParam.innerValue < 0) {
                return targetParam; // No custom exception thrown
            }

            // Return TargetClass Type (target_inner_rec)
            return targetParam;
        }

        private void innerHelperMethod(int value) {
            targetParam.innerValue = value * 2;
        }
    }

    // Call method: source type, default modifier, static, new ClassName().method(), pos=switch, returns TargetClass Type
    TargetClass<T>.TargetInnerRecursive callMethod(int switchCase) {
        switch (switchCase) {
            case 1:
                return new SourceClass<T>().new SourceInnerRecursive().methodToMove(new TargetClass<T>().new TargetInnerRecursive());
            case 2:
                TargetClass<T>.TargetInnerRecursive innerRec = new TargetClass<T>().new TargetInnerRecursive();
                innerRec.innerValue = 42;
                return new SourceClass<T>().new SourceInnerRecursive().methodToMove(innerRec);
            default:
                return new TargetClass<T>().new TargetInnerRecursive();
        }
    }
}

// Target class: generic, default modifier, contains local inner class (target_feature)
class TargetClass<T> {
    // Recursive inner class (target_inner_rec)
    public class TargetInnerRecursive {
        int innerValue;
        String innerData;

        // Method containing local inner class (target_feature: local inner class)
        public void targetMethod() {
            // Local inner class
            class TargetLocalInner {
                void processData(TargetInnerRecursive rec) {
                    rec.innerData = "processed";
                }
            }
            new TargetLocalInner().processData(this);
        }
    }
}