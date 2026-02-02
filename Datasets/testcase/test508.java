// Source package (different from target)
package com.refactor.source;

import com.refactor.target.TargetRecord;
import com.refactor.other.DiffPackageOthers;
import java.util.function.Function;

// Sealed interface to host abstract method (record can't have abstract methods directly)
sealed interface SourceRecordInterface permits SourceRecord {
    // Abstract method (default access, returns Object, target param - per_condition)
    Object refactorMethod(TargetRecord targetParam);
}

// Source record class (public modifier, different package, empty feature list)
public record SourceRecord(String id) implements SourceRecordInterface {
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "source_static_1"; // Value 1 for target_feature

    // Abstract method implementation (fulfills all method features)
    @Override
    public Object refactorMethod(TargetRecord targetParam) {
        // Variable call feature
        String varCall = this.id + "_" + STATIC_FIELD;

        // Type declaration statement feature
        class LocalTypeDeclaration {
            String process(String val) {
                return val + "_processed_1"; // Value 1 for target_feature
            }
        }
        LocalTypeDeclaration localType = new LocalTypeDeclaration();
        varCall = localType.process(varCall);

        // If statement feature
        if (targetParam == null) {
            varCall = "null_target_1"; // Value 1 for target_feature
        } else {
            varCall += targetParam.data();
        }

        // ExpressionMethodReference (numbers=2, abstract modifier)
        abstract Function<Integer, String> methodRef = String::valueOf;
        varCall += methodRef.apply(2); // Number 2 feature

        // AssertStatement (static modifier, ClassName.field=1, pos: diff_package_others)
        static {
            DiffPackageOthers diffObj = new DiffPackageOthers();
            assert diffObj.FIELD == 1 : "Field value must be 1"; // ClassName.field target_feature (value 1)
        }

        // Instance method (public modifier, 1 + source + instance + new ClassName().method(), instance code blocks pos)
        { // Instance code blocks position
            public void instanceMethod() {
                // new ClassName().method() target_feature + Number 1
                new SourceRecord("instance_1").refactorMethod(targetParam); // source + instance feature
            }
            instanceMethod();
        }

        // Depends_on_static_field feature
        varCall += STATIC_FIELD;

        // No_new_exception feature (no explicit throw new Exception)
        return varCall;
    }
}

// Diff package class for AssertStatement pos: diff_package_others
package com.refactor.other;

public class DiffPackageOthers {
    public static final int FIELD = 1; // Value 1 for AssertStatement target_feature
}

// Target package (different from source)
package com.refactor.target;

import com.refactor.source.SourceRecord;
import java.util.function.Consumer;

// Target record class (public modifier, member inner class target_feature)
public record TargetRecord(String data) {
    // Member inner class (target_feature)
    public class TargetMemberInner {
        String validate(String input) {
            return input == null ? "default_1" : input; // Value 1 for target_feature
        }

        public TargetRecord process(TargetRecord rec) {
            return new TargetRecord(validate(rec.data()));
        }
    }

    // Constructor initializing inner class
    public TargetRecord {
        TargetMemberInner inner = new TargetMemberInner();
        data = inner.validate(data);
    }
}

// Others_class for call_method (private modifier, instance + method reference, exception throwing pos)
package com.refactor.others;

import com.refactor.source.SourceRecord;
import com.refactor.target.TargetRecord;

class OthersClass {
    // Call method (private modifier, others_class type, exception throwing pos, void return)
    private void callMethod() {
        SourceRecord source = new SourceRecord("caller_1");
        TargetRecord target = new TargetRecord("target_1");

        try {
            // Instance feature (call instance method)
            source.refactorMethod(target);
            // instanceReference::methodName target_feature
            Consumer<TargetRecord> consumer = source::refactorMethod;
            consumer.accept(target);
        } catch (AssertionError e) {
            // Exception throwing statements position
            throw new RuntimeException("Assert failed: " + e.getMessage());
        }
    }
}