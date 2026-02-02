package com.refactoring.movemethod;

// Same package others class for LabeledStatement pos
class SamePackageOthers {
    public static int staticField = 3; // ClassName.field + 3 feature
}

// Super class for record extends (records implicitly extend java.lang.Record, use abstract class workaround)
abstract class RecordSuperClass {
    protected int protectedMethod(int val) {
        return val + 3;
    }
}

// Source record class (default modifier, same package, extends feature)
record SourceRecord(String sourceField) extends RecordSuperClass {
    // Target field reference (per_condition: source contains target field)
    private TargetRecord targetFieldRef;

    // Final instance method (base type return, source position)
    public final int processTarget(TargetRecord targetParam) {
        // Variable call (target parameter access)
        if (targetParam == null) {
            // NullPointerException
            throw new NullPointerException("Target parameter is null");
        }
        targetFieldRef = targetParam;

        // Type declaration statement
        String targetVal = targetParam.targetField();
        // Expression statement
        targetVal = targetVal.toUpperCase();

        // LabeledStatement (private modifier, ClassName.field, 3, pos: same_package_others)
        privateLabeledStatement(targetParam);

        // Labeled statement (duplicate feature)
        label: {
            if (targetVal.length() > 3) {
                break label;
            }
            targetVal += "_extended";
        }

        // Do statement
        int count = 0;
        do {
            // Access instance method
            targetParam.targetInner().updateField(targetVal);
            // this.methodName(arguments)
            TargetRecord innerResult = this.protectedInstanceMethod(targetParam, 3);
            count++;
        } while (count < 3);

        // Try statement
        int result = 0;
        try {
            // call_method pos: do-while
            do {
                result = targetParam.synchronizedMethod(count);
            } while (result < 3);
        } catch (Exception e) {
            // No_new_exception
            result = -1;
        }

        return result;
    }

    // Private LabeledStatement method
    private void privateLabeledStatement(TargetRecord target) {
        // same_package_others pos
        samePackageLabel: {
            // ClassName.field + 3 feature
            if (SamePackageOthers.staticField == 3) {
                target.targetInner().setFieldValue(3);
                break samePackageLabel;
            }
        }
    }

    // Protected instance method (Static code blocks pos, TargetClass Type return)
    protected TargetRecord protectedInstanceMethod(TargetRecord target, int num) {
        // Static code blocks (pos)
        static {
            System.out.println("Static block for instance method");
        }
        // 3: literal value
        if (num == 3) {
            // this.methodName(arguments)
            this.protectedMethod(num);
            return target;
        }
        return target;
    }
}

// Target record class (default modifier, member inner class feature)
record TargetRecord(String targetField) {
    // Member inner class (target_feature)
    class TargetInner {
        private String fieldValue;

        public void updateField(String val) {
            this.fieldValue = val;
        }

        public void setFieldValue(int val) {
            this.fieldValue = String.valueOf(val);
        }
    }

    // Getter for inner class
    public TargetInner targetInner() {
        return new TargetInner();
    }

    // call_method: type=target, modifier=synchronized, overriding, this.methodName, pos=do-while
    @Override
    public synchronized int synchronizedMethod(int val) {
        // Overriding (override Object's hashCode)
        super.hashCode();
        // this.methodName(arguments)
        return this.calculateValue(val);
    }

    // Helper method for this.methodName
    private int calculateValue(int val) {
        return val * 3;
    }
}