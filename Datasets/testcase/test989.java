// Different package for ReturnStatement pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetRecord;

public class DiffPackageOthers {
    // ReturnStatement (private modifier, obj.field, 1, pos=diff_package_others)
    private static <T> String returnStatement(TargetRecord<T> target) {
        if (target.field() == null) {
            return "default_" + 1; // 1 from target_feature
        }
        return target.field().toString() + "_" + 1; // obj.field access + 1
    }
}

// Main package
package com.refactoring.test;

import com.refactoring.others.DiffPackageOthers;

// Target record class (private modifier, type parameter + anonymous inner class features)
private record TargetRecord<T>(T field) {
    // Anonymous inner class (target_feature)
    private final Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner: " + field + "_" + 1);
        }
    };

    // Compact constructor with super constructor invocation (implicit for record)
    public TargetRecord {
        // Super constructor invocation for record (Object superclass)
        super();
    }

    public void invokeAnonymous() {
        anonymousRunnable.run();
    }
}

// Source record class (default modifier, same package, two static nested classes)
record SourceRecord(String sourceField) {
    // First static nested class (source_feature)
    static class SourceStaticNestedOne {
        static <T> void validateField(T field) throws IllegalArgumentException {
            if (field == null) {
                throw new IllegalArgumentException("Field null: " + 1);
            }
        }
    }

    // Second static nested class (source_feature)
    static class SourceStaticNestedTwo {
        static <T> void processField(TargetRecord<T> target) {
            target.invokeAnonymous();
        }
    }

    // Method to be refactored (static, void return, default access, source position)
    static <T> void moveMethod(TargetRecord<T> targetParam) throws IllegalArgumentException { // requires_throws
        // Per_condition: source contains the field of the target
        if (targetParam == null) {
            throw new IllegalArgumentException("Target null: " + 1); // requires_throws
        }

        // ReturnStatement invocation (pos=diff_package_others)
        String returnResult = DiffPackageOthers.returnStatement(targetParam);

        // Super constructor invocation (record's implicit super (Object))
        targetParam.toString(); // Triggers super method (indirect super constructor invocation)

        // Variable call (access target field - per_condition)
        T varCall = targetParam.field();
        SourceStaticNestedOne.validateField(varCall); // requires_throws

        // Process target with static nested class
        SourceStaticNestedTwo.processField(targetParam);

        // Additional ReturnStatement usage (private modifier)
        if (varCall instanceof String s && s.length() == 1) {
            return; // ReturnStatement with 1 from target_feature
        }
    }
}