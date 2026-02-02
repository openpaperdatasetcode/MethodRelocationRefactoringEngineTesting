package com.refactor.movemethod;

import java.io.IOException;
import java.util.function.Function;

// Sealed interface for source_class permits feature
sealed interface RecordSealedInterface permits SourceRecord {}

// Source record class (public modifier, same package, permits + anonymous inner + local inner class)
public record SourceRecord(String id) implements RecordSealedInterface {
    // Anonymous inner class (source_class feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Instance method to refactor (default access, base type return, target param - per_condition)
    int refactorMethod(TargetRecord targetParam) {
        // Variable call feature
        sourceAnonymous.run();
        String varCall = targetParam.data();

        // Local inner class (source_class feature)
        class SourceLocalInner {
            int processValue(String val) {
                return val.length();
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        int result = localInner.processValue(varCall);

        // SynchronizedStatement (protected modifier, obj.field=2, pos: source)
        SyncObject syncObj = new SyncObject();
        protected synchronized (syncObj) {
            syncObj.field = 2; // obj.field target_feature with value 2
            result += syncObj.field;
        }

        // IOException feature (no_new_exception - no explicit throw new)
        try {
            if (targetParam == null || targetParam.data().isEmpty()) {
                throw new IOException("Target data is empty (code: 2)");
            }
        } catch (IOException e) {
            result = 2; // Fallback to 2 (target_feature value)
        }

        // No_new_exception feature (no explicit throw new Exception)
        return result; // Base type (int) return
    }
}

// Helper class for SynchronizedStatement obj.field
class SyncObject {
    int field;
}

// Target record class (public modifier, member inner class target_feature)
public record TargetRecord(String data) {
    // Member inner class (target_feature)
    public class TargetMemberInner {
        String getProcessedData() {
            return data + "_processed";
        }
    }

    // Constructor initializing inner class
    public TargetRecord {
        TargetMemberInner inner = new TargetMemberInner();
        data = inner.getProcessedData();
    }
}

// Others_class for call_method (private modifier, normal + method reference, expression pos, returns int)
class OthersClass {
    // Call method (private modifier, others_class type, expression pos, returns int)
    private int callMethod() {
        SourceRecord source = new SourceRecord("source_id");
        TargetRecord target = new TargetRecord("target_data");

        // Expression position + instanceReference::methodName target_feature
        Function<TargetRecord, Integer> methodRef = source::refactorMethod;
        // Normal feature (standard method call in expression)
        int result = methodRef.apply(target) + 2;

        return result;
    }
}