// Source record package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetRecord;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation6108 {}

// Super class for super.field and super constructor invocation
class SuperSourceRecord {
    protected int superField = 1; // Target_feature: "1" for ReturnStatement & SuperFieldAccess
}

// Source record class: default modifier, different package from target, no additional features
record SourceRecord(String sourceData) extends SuperSourceRecord {
    // Method to be refactored: varargs, TargetClass Type return, public access, source position
    @RefactorAnnotation6108 // has_annotation feature
    public TargetRecord methodToMove(TargetRecord targetParam, String... varargs) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            // Super constructor invocation
            super();
            // Constructor invocation (target record)
            targetParam = new TargetRecord("default_6108");
        }

        // this.var = var feature
        this.sourceData = varargs.length > 0 ? varargs[0] : "default_source";

        // variable call feature
        String varCall = this.sourceData;

        // numbers: "1", modifier: default, exp: SuperFieldAccess
        default int superFieldAccess() {
            return super.superField; // SuperFieldAccess expression, value 1
        }
        int superVal = superFieldAccess();

        // ReturnStatement feature (type: ReturnStatement, modifier: protected, pos: source)
        protected TargetRecord returnStatementLogic() {
            if (super.superField == 1) { // target_feature: "1"
                targetParam = targetParam.withUpdatedData(varCall + "_" + super.superField); // target_feature: super.field
                return targetParam;
            }
            return targetParam;
        }

        // no_new_exception (no explicit new Exception instantiation)
        if (varCall.isEmpty()) {
            return returnStatementLogic();
        }

        return returnStatementLogic();
    }
}

// Target record package
package com.refactoring.target;

// Target record class: abstract modifier, static nested class (target_feature)
abstract record TargetRecord(String targetData) {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private int nestedVal = 6108;
    }

    // Factory method for constructor invocation
    public TargetRecord withUpdatedData(String newData) {
        return new TargetRecordImpl(newData);
    }

    // Concrete implementation of abstract record
    private static record TargetRecordImpl(String targetData) extends TargetRecord {
        TargetRecordImpl(String targetData) {
            super(targetData);
        }
    }
}