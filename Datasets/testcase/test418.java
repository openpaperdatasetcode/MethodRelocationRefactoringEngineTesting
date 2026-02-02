import java.io.IOException;

// Different package simulation - source record in com.source
package com.source;

import com.target.TargetRecord;

// Source record class: sealed modifier, different package, static nested/anonymous inner class
public sealed record SourceRecord(String sourceField) permits SourceRecord.SourceStaticNested {
    // Static nested class (fulfills source_class feature)
    public static final class SourceStaticNested extends SourceRecord {
        public SourceStaticNested() {
            super("static_nested");
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(sourceField);
        }
    };

    // Base method for override_violation
    public String baseMethod() {
        return sourceField;
    }

    // Target method: instance, base type return, strictfp access, in source record
    public strictfp int processData(TargetRecord param) throws IOException {
        // VariableDeclarationStatement (private modifier, obj.field=1, pos:diff_package_others)
        private int objFieldVal = 1; // target_feature: obj.field, numbers:1 (diff_package_others)
        
        // Variable call to target parameter (fulfills per_condition)
        int targetVar = param.targetField();
        
        // Override_violation: inner class with incompatible return type override
        class OverrideViolation extends SourceStaticNested {
            @Override
            public Integer baseMethod() { // Incompatible return type (String vs Integer)
                return objFieldVal;
            }
        }
        
        // IOException feature (conditional throw)
        if (targetVar > 10) {
            throw new IOException("Target field value exceeds threshold");
        }
        
        // No new exception thrown (no_new_exception)
        return targetVar + objFieldVal; // Base type return (int)
    }
}

// Target record class: protected modifier, local inner class (target_feature)
package com.target;

protected record TargetRecord(int targetField) {
    public void outerMethod() {
        // Local inner class (fulfills target_feature)
        class TargetLocalInner {
            int getLocalValue() {
                return targetField;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        System.out.println(localInner.getLocalValue());
    }
}