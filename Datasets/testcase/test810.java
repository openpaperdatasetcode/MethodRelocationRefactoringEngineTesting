package com.refactoring.movemethod;

// Functional interface for target implements feature
interface TargetCalculator {
    int calculate(int a, int b);
}

// Source class: normal, private modifier, same package, member inner + local inner classes
private class SourceClass {
    // Member inner class (source feature)
    private class SourceMemberInner {
        private String innerField = "source_member_inner_6153";
        
        // Helper method for depends_on_inner_class feature
        public String processData(String data) {
            return data + "_processed_by_inner";
        }
    }

    // ConstructorInvocation feature helper class (for super.field access)
    static class ConstructorHelper extends TargetClass {
        // ConstructorInvocation feature (type: ConstructorInvocation, modifier: static, pos: same_package_target)
        public static TargetClass createTargetInstance() {
            TargetClass target = new TargetClass();
            // target_feature: super.field (access TargetClass's parent field via super)
            int superFieldVal = 3; // target_feature: "3"
            target.setSuperField(superFieldVal);
            return target;
        }
    }

    // Method to be refactored: instance, base type (int) return, protected access, source position
    protected int methodToMove(TargetClass.TargetInnerRecursive targetParam) {
        // per_condition: method contains target inner recursive parameter
        if (targetParam == null) {
            // ConstructorInvocation feature - call static constructor helper
            TargetClass target = ConstructorHelper.createTargetInstance();
            targetParam = target.new TargetInnerRecursive();
        }

        // variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerField;

        // continue statement feature
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            if (i == 3) { // target_feature: "3"
                continue; // continue statement
            }
            sum += i;
        }

        // expression statement feature
        targetParam.setData(varCall + "_sum_" + sum); // expression statement

        // local inner class (source feature)
        class SourceLocalInner {
            public int validateData(String data) {
                return data == null ? 0 : data.length();
            }
        }
        int dataLen = new SourceLocalInner().validateData(targetParam.getData());

        // depends_on_inner_class feature (rely on target's inner class method)
        int innerResult = targetParam.calculateData(3); // target_feature: "3"

        // no_new_exception (no explicit new Exception instantiation)
        return sum + dataLen + innerResult;
    }
}

// Target class: normal, public modifier, implements + anonymous inner class (target_features)
public class TargetClass {
    // super.field for ConstructorInvocation feature
    protected int superField;

    public void setSuperField(int val) {
        this.superField = val;
    }

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive implements TargetCalculator {
        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        // Recursive method (recursive class context)
        public int calculateData(int val) {
            if (val <= 0) return superField; // target_feature: super.field
            return val + calculateData(val - 1);
        }

        // implements feature - TargetCalculator interface method
        @Override
        public int calculate(int a, int b) {
            // anonymous inner class (target_feature)
            TargetCalculator anonymousCalc = new TargetCalculator() {
                @Override
                public int calculate(int x, int y) {
                    return x * y + superField; // target_feature: super.field
                }
            };
            return anonymousCalc.calculate(a, b);
        }
    }

    // Call method: target type, final modifier, overloading, pos=do-while, returns void
    public final void callMethod() {
        // overloading feature - call overloaded method
        callMethod(new TargetInnerRecursive());
    }

    // Overloaded call method (target_feature: overloading)
    public final void callMethod(TargetInnerRecursive inner) {
        int count = 0;
        // pos: do-while statement
        do {
            // target_feature: new ClassName().method()
            SourceClass source = new SourceClass();
            int result = source.methodToMove(inner); // new ClassName().method()
            inner.setData(inner.getData() + "_result_" + result);
            count++;
        } while (count < 3); // target_feature: "3"
    }
}