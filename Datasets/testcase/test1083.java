package com.refactoring.test;

import java.lang.reflect.Method;

// Source record class (public modifier, same package, anonymous inner class, member inner class)
public record SourceRecord(String sourceData) {
    // Source contains target field (per_condition)
    private final TargetRecord targetField = new TargetRecord("targetData");

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {}

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // Variable call in anonymous inner class
            String innerValue = targetField.innerRecord.value();
            System.out.println("Anonymous inner: " + innerValue);
        }
    };

    // Method to be refactored (instance, TargetRecord return, default access, position: source)
    TargetRecord targetMethod() {
        // Super keywords usage
        super.toString();

        // Variable call
        TargetRecord.InnerTargetRecord innerRec = targetField.innerRecord;
        String targetValue = innerRec.value();

        // Expression statement
        innerRec = new TargetRecord.InnerTargetRecord(targetValue + "_updated");
        targetField.innerClass.updateInnerRecord(innerRec);

        // Used by reflection
        try {
            Method method = SourceRecord.class.getDeclaredMethod("targetMethod");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception e) {
            // No new exception (no_new_exception) - handle without throwing new exceptions
            System.err.println("Reflection error: " + e.getMessage());
        }

        // Execute anonymous inner class logic
        anonymousInner.run();

        // No new exception (no_new_exception)
        return targetField;
    }
}

// Target record class (default modifier, same package, member inner class target_feature)
record TargetRecord(String data) {
    // Member inner class (target_feature)
    class InnerTargetClass {
        void updateInnerRecord(InnerTargetRecord record) {
            // Update logic for inner record
            TargetRecord.this.innerRecord = record;
        }
    }

    // Inner target record (target_inner_rec)
    record InnerTargetRecord(String value) {}

    // Inner class instance
    final InnerTargetClass innerClass = new InnerTargetClass();
    // Inner record instance (target_inner_rec)
    InnerTargetRecord innerRecord = new InnerTargetRecord("initialInnerValue");
}