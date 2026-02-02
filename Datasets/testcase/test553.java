package com.refactor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: record, sealed modifier, same package as target, local inner + anonymous inner class
sealed record SourceRecord(String id, int value) permits SourceRecord.SubRecord {
    // Target class field reference is covered via method parameter (per_condition)
    @Override
    @CustomAnnotation // has_annotation feature
    public final int hashCode() { // overriding method (Object.hashCode), final, base type return (int)
        // Do statement
        int doVar = 0;
        do {
            doVar++;
        } while (doVar < 3);

        // Labeled statement
        labelBlock: {
            if (doVar == 3) break labelBlock;
        }

        // Expression statement
        doVar = doVar * 2;

        // FieldAccess (numbers:3, private modifier)
        private int field1 = 3;
        private int field2 = 3;
        private int field3 = 3;
        int fieldAccessSum = this.field1 + this.field2 + this.field3;

        // Variable call (target parameter access)
        TargetRecord targetParam = new TargetRecord("test", 3);
        int targetVar = targetParam.value();

        // Access instance method
        targetParam.innerClassInstance.instanceMethod();

        // Local inner class (source feature)
        class LocalInnerClass {
            void localMethod() {}
        }
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.localMethod();

        // Anonymous inner class (source feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                SourceRecord.this.hashCode();
            }
        };

        // No new exception thrown
        return doVar + fieldAccessSum + targetVar;
    }

    // Overriding method with target parameter (per_condition)
    @CustomAnnotation
    public final int methodToMove(TargetRecord... targetParams) { // overriding (assumed), final, base type return
        // Do statement
        int count = 0;
        do {
            count++;
        } while (count < 3);

        // Labeled statement
        loopLabel:
        for (int i = 0; i < 5; i++) {
            if (i == 3) break loopLabel;
        }

        // Expression statement
        count += targetParams.length;

        // FieldAccess (numbers:3, private modifier)
        private int fa1 = 3;
        private int fa2 = 3;
        private int fa3 = 3;
        int faSum = fa1 + fa2 + fa3;

        // Variable call (target parameter variable call)
        int varCall = targetParams.length > 0 ? targetParams[0].value() : 0;

        // Access instance method
        if (targetParams.length > 0) {
            targetParams[0].innerClassInstance.instanceMethod();
        }

        // Local inner class (source feature)
        class MethodLocalInner {
            void method() {}
        }
        new MethodLocalInner().method();

        // Anonymous inner class (source feature)
        Runnable anon = new Runnable() {
            @Override
            public void run() {
                methodToMove(targetParams);
            }
        };

        // No new exception thrown
        return count + faSum + varCall;
    }

    // Sealed permit sub-record
    non-sealed record SubRecord(String id, int value) extends SourceRecord {
        public SubRecord() {
            super("", 0);
        }
    }
}

// Target class: record, private modifier, member inner class (target_feature)
private record TargetRecord(String name, int value) {
    // Member inner class (target_feature)
    class TargetInnerClass {
        void instanceMethod() {}
    }

    // Inner class instance
    TargetInnerClass innerClassInstance = new TargetInnerClass();
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}