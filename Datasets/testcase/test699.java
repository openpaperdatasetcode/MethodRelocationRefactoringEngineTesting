package com.refactor.movemethod;

// Source record class (default modifier, same package as target, anonymous + member inner class features)
record SourceRecord(String id, TargetRecord targetField) { // per_condition: contains target class field
    // Member inner class (source_class feature)
    class SourceMemberInner {
        int innerValue = 1;
    }

    // Anonymous inner class (source_class feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Instance method to be moved (meets all method features)
    public int methodToRefactor() { // return_type: base type (int)
        // Expression statement feature
        int localVar = 0;
        SourceMemberInner innerInstance = new SourceMemberInner();
        
        // Variable call feature
        localVar = innerInstance.innerValue;
        sourceAnonymous.run();

        // Requires try-catch feature
        try {
            // SwitchStatement (private modifier, ClassName.field + 1 target_feature, pos: source)
            switch (targetField.targetValue()) {
                case 1:
                    localVar = TargetRecord.TARGET_FIELD; // ClassName.field target_feature (value 1)
                    break;
                default:
                    localVar = 0;
                    break;
            }

            // Throw statement feature
            if (localVar < 0) {
                throw new IllegalArgumentException("Invalid value: " + localVar);
            }
        } catch (NullPointerException e) {
            localVar = 1; // Fallback to 1 (target_feature value)
        }

        // Return statement feature
        return localVar;
    }
}

// Target record class (public modifier, anonymous inner class target_feature)
public record TargetRecord(String name, int targetValue) {
    // ClassName.field (target_feature for SwitchStatement, value 1)
    public static final int TARGET_FIELD = 1;

    // Anonymous inner class (target_feature)
    private final Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class");
        }
    };

    // Target_inner (inner context for target class)
    public int getInnerValue() {
        targetAnonymous.run();
        return targetValue;
    }
}