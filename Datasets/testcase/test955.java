package com.refactoring.movemethod;

import java.io.IOException;

// Supporting interface for source class implements feature
interface SourceInterface<T> {
    int processTarget(TargetClass target) throws IOException;
}

// Source normal class (default modifier, same package, type parameter + implements)
class SourceClass<T extends CharSequence> implements SourceInterface<T> {
    // Target field reference (per_condition: source contains target field)
    private TargetClass targetFieldRef;
    // Field for this.field feature in VariableDeclarationStatement
    private String sourceField = "source_field";

    // Instance method (protected access, base type return)
    @Override
    protected int processTarget(TargetClass targetParam) throws IOException {
        // Per_condition: contains target parameter (variable call)
        if (targetParam == null) {
            throw new IOException("Target parameter cannot be null"); // requires_throws
        }
        targetFieldRef = targetParam;

        // VariableDeclarationStatement (volatile modifier, this.field, 1, pos: source)
        volatile int volatileVar = 1; // 1 feature
        this.sourceField = "updated_" + volatileVar; // this.field feature

        // Type declaration statement
        String targetVal = targetFieldRef.getTargetField();
        // Raw type usage (raw_type feature)
        TargetClass.TargetMemberInner rawInner = new TargetClass().new TargetMemberInner(); // Raw type

        // Labeled statement
        label: {
            if (targetVal.length() > 1) {
                volatileVar += 10;
                break label;
            }
            volatileVar += 5;
        }

        // While statement
        int count = 0;
        while (count < 3) {
            // Variable call (access target instance method)
            targetFieldRef.updateField(targetVal + "_" + count);
            count++;
        }

        // Return statement
        return volatileVar + count;
    }
}

// Target normal class (protected modifier, member inner class feature)
protected class TargetClass {
    // Target field for per_condition
    private String targetField = "default_target_value";

    // Member inner class (target feature)
    public class TargetMemberInner {
        private String innerField;

        public void setInnerField(String val) {
            this.innerField = val;
        }

        public String getInnerField() {
            return innerField;
        }
    }

    // Getter/Setter for variable call
    public String getTargetField() {
        return targetField;
    }

    public void updateField(String val) {
        this.targetField = val;
    }
}