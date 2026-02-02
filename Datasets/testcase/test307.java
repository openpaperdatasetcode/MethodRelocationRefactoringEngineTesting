package com.refactor;

import com.other.DiffPackageClass;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Function;

// Different package class for SynchronizedStatement pos (diff_package_target)
package com.other;
import com.refactor.TargetRecord;

public class DiffPackageClass {
    // SynchronizedStatement: private modifier, obj.field + 1, pos: diff_package_target
    public static void syncLogic(TargetRecord target) {
        private Object lock = new Object();
        synchronized (lock) {
            target.field = "value_1"; // obj.field + 1 feature
        }
    }
}

// Back to refactor package
package com.refactor;
import com.other.DiffPackageClass;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RecordMethodAnn {
    String value() default "source_inner";
}

// Source class: record class, public modifier, same package as target, member inner + local inner class
public record SourceRecord(String sourceField) {
    // Member inner class feature
    public class MemberInnerClass {
        // Method: varargs, return TargetClass Type (TargetRecord), private access, source_inner position
        @RecordMethodAnn // has_annotation feature
        private TargetRecord processTarget(TargetRecord... targetParams) {
            // Variable call (target parameter)
            TargetRecord target = targetParams.length > 0 ? targetParams[0] : new TargetRecord("default");
            
            // SynchronizedStatement from diff_package_target
            DiffPackageClass.syncLogic(target);
            
            // otherObject.process(this) feature
            new OtherProcessor().process(SourceRecord.this);
            
            // No new exception - wrap existing if any
            try {
                target = TargetRecord.StaticNestedClass.modifyTarget(target);
            } catch (Exception e) {
                throw new RuntimeException(e); // No new exception instantiation
            }
            
            return target;
        }
    }

    // Local inner class feature (in compact constructor)
    public SourceRecord {
        class LocalInnerClass {
            void callInnerMethod(TargetRecord target) {
                new MemberInnerClass().processTarget(target);
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        local.callInnerMethod(new TargetRecord("local"));
    }

    // call_method: source type, protected modifier, overloading + ClassName.methodName(arguments), pos: Lambda expressions, returns Object
    protected Object callMethod(TargetRecord target) {
        // Lambda expressions position for call_method
        Function<TargetRecord, Object> lambda = SourceRecord::callMethod; // ClassName.methodName(arguments)
        return lambda.apply(target);
    }

    // Overloading feature for call_method
    protected Object callMethod(TargetRecord target, String suffix) {
        return target.field() + suffix;
    }

    // Other object for otherObject.process(this) feature
    static class OtherProcessor {
        public void process(SourceRecord source) {
            // Process source record instance
        }
    }
}

// Target class: record class, default modifier, static nested class feature
record TargetRecord(String field) {
    // Static nested class feature
    public static class StaticNestedClass {
        public static TargetRecord modifyTarget(TargetRecord target) {
            return new TargetRecord(target.field() + "_modified");
        }
    }

    // Compact constructor to allow field modification (for obj.field feature)
    public TargetRecord {
        // Empty compact constructor
    }
}