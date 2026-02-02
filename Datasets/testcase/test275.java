package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Different package for DoStatement's position requirement
package com.other;
import com.refactoring.movemethod.SourceRecord;
import com.refactoring.movemethod.TargetRecord;
import java.util.List;

// Private DoStatement class as specified (diff_package_others position)
private class DoStatement {
    // this.field feature
    private String field;

    public DoStatement(String field) {
        this.field = field;
    }

    // Feature "1" (numeric literal usage)
    public int process() {
        return this.field.length() + 1;
    }
}

// Back to original package
package com.refactoring.movemethod;
import com.other.DoStatement;
import java.util.ArrayList;
import java.util.List;

// Annotation for method (has_annotation feature)
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorCandidate {}

// Source record class (protected modifier, same package as target)
protected record SourceRecord(String sourceField, static String staticField) {
    // Multiple member inner classes (feature requirement)
    class MemberInner1 {
        String innerData;
    }

    class MemberInner2 {
        int innerValue;
    }

    // Instance method to refactor (protected access, returns List<String>)
    // Override_violation: method with same signature as supertype but incompatible (no actual super, simulate violation)
    @RefactorCandidate // has_annotation feature
    public List<String> moveTargetMethod(TargetRecord targetParam) {
        // NullPointerException risk (feature)
        if (targetParam == null) {
            throw new NullPointerException("Target parameter is null");
        }

        // uses_outer_this feature
        SourceRecord outerThis = SourceRecord.this;

        // depends_on_static_field feature
        String staticVal = SourceRecord.staticField;

        // access_instance_field feature
        String targetField = targetParam.targetField();

        // variable call feature
        List<String> result = new ArrayList<>();
        result.add(staticVal);
        result.add(targetField);

        // type declaration statement feature
        DoStatement doStmt = new DoStatement(outerThis.sourceField());
        int processed = doStmt.process();

        // Add processed value (uses "1" feature from DoStatement indirectly)
        result.add(String.valueOf(processed));

        // Override_violation simulation: intentional incompatible return type if overridden
        @SuppressWarnings("unused")
        List<Integer> invalidOverride = new ArrayList<>();

        return result;
    }
}

// Target record class (default modifier, same package as source)
record TargetRecord(String targetField) {
    // Method containing local inner class (target_feature: local inner class)
    public List<String> acceptMethod() {
        // Local inner class
        class LocalInner {
            String localData = "local";

            String getLocalData() {
                return localData;
            }
        }

        LocalInner local = new LocalInner();
        List<String> list = new ArrayList<>();
        list.add(local.getLocalData());
        return list;
    }
}