// Diff package target helper for ThrowStatement pos
package com.refactoring.others;
import com.refactoring.movemethod.SourceRecord;
import com.refactoring.movemethod.TargetRecord;
import java.util.List;

public class DiffPackageTargetHelper {
    // ThrowStatement feature: private modifier, obj.field, 1, pos=diff_package_target
    private static <T> void throwStatementLogic(TargetRecord<T>.TargetInner inner, SourceRecord source) {
        // pos: diff_package_target
        inner.innerField = 1; // obj.field feature, target_feature: 1
        if (inner.getInnerValue() == null) {
            throw new IllegalArgumentException("Inner value is null (1)"); // target_feature: 1
        }
    }
}

// Back to source package
package com.refactoring.movemethod;
import com.refactoring.others.DiffPackageTargetHelper;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for numbers=1, exp=Annotation feature
@Retention(RetentionPolicy.RUNTIME)
@SuppressWarnings("unused")
@interface ProcessAnnotation {
    int value() default 1;
}

// Sealed interface with permits for source record feature
sealed interface RecordSealed permits SourceRecord, SourceRecord.SourceStaticNested {}

// Source record class: protected, same package as target, permits, anonymous inner, static nested class
protected record SourceRecord(String sourceField) implements RecordSealed {
    // Static field for depends_on_static_field feature
    private static final int STATIC_FIELD = 1; // numbers=1
    // Per_condition: source contains target record field
    private final TargetRecord<String> targetField = new TargetRecord<>("initial_target_1", 1); // target_feature: 1

    // Static nested class (source feature)
    static final class SourceStaticNested implements RecordSealed {
        public static <T> String process(T value) {
            return value.toString() + "_static_" + STATIC_FIELD; // numbers=1
        }
    }

    // Base class for overriding feature
    static class BaseOverrideClass {
        public List<String> processTarget(TargetRecord<String>.TargetInner inner) {
            return new ArrayList<>();
        }
    }

    // Overriding class
    static class OverrideClass extends BaseOverrideClass {
        // Overriding feature: public modifier, 2, target, overriding, obj.m1().m2().m3(), pos=if/else, return List<String>
        @Override
        public List<String> processTarget(TargetRecord<String>.TargetInner inner) {
            List<String> result = new ArrayList<>();
            // pos: if/else conditions
            if (inner.innerField == 1) { // target_feature: 1
                // obj.m1().m2().m3() feature
                String chain = inner.m1().m2().m3(); // method_feature: target
                result.add(chain + "_2"); // method_feature: 2
            } else {
                result.add("default_2"); // method_feature: 2
            }
            return result;
        }
    }

    // call_method: source type, protected modifier, constructor, OuterClass.InnerClass.methodName(), pos=if/else, return TargetRecord Type
    protected <T> TargetRecord<T> callMethod(TargetRecord<T>.TargetInner inner) {
        // pos: if/else conditions
        if (inner.innerField == 1) { // numbers=1
            // target_feature: constructor (new TargetInner instance)
            TargetRecord<T>.TargetInner newInner = inner.getOuterRecord().new TargetInner((T) "new_inner_1");
            // target_feature: OuterClass.InnerClass.methodName()
            String processed = SourceRecord.SourceStaticNested.process(newInner.getInnerValue());
            return new TargetRecord<>((T) processed, 1); // numbers=1
        } else {
            return inner.getOuterRecord();
        }
    }

    // Method to refactor: normal, void return, final access, in source record
    @ProcessAnnotation(1) // numbers=1, public modifier, exp=Annotation
    public final <T> void methodToRefactor(TargetRecord<T>.TargetInner targetParam) {
        // Super keywords (record implicit super constructor reference)
        Object superRef = super.toString();
        
        // Variable call (target inner class and source static field)
        T innerValue = targetParam.getInnerValue();
        // uses_outer_this feature
        T outerThisVal = (T) SourceRecord.this.sourceField();
        
        // ThrowStatement feature call (diff_package_target)
        DiffPackageTargetHelper.throwStatementLogic(targetParam, this);

        // Expression statement
        innerValue = (T) (innerValue + "_exp_" + STATIC_FIELD); // depends_on_static_field
        targetParam.setInnerValue(innerValue); // expression statement

        // Assert statement
        assert targetParam.innerField == 1 : "Inner field must be 1"; // target_feature: 1

        // Overriding method call
        List<String> overrideResult = new OverrideClass().processTarget((TargetRecord<String>.TargetInner) targetParam);

        // call_method invocation
        TargetRecord<T> callResult = this.callMethod(targetParam);

        // No_new_exception feature
        try {
            Integer.parseInt(innerValue.toString());
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            targetParam.setInnerValue((T) ("formatted_" + innerValue));
        }

        // Anonymous inner class (source feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                // uses_outer_this feature
                System.out.println("Outer this: " + SourceRecord.this.sourceField());
                System.out.println("Target value: " + targetField.getInner().getInnerValue());
            }
        };
        anonymous.run();
    }
}

// Target record class: default modifier, type parameter, member inner class feature
record TargetRecord<T>(T value, int field) {
    // Type parameter (target_feature)
    private final TargetInner inner = new TargetInner(value);

    // Member inner class (target_feature)
    public class TargetInner {
        private T innerValue;
        int innerField = 1; // target_feature: 1

        public TargetInner(T value) {
            this.innerValue = value;
        }

        // Method chaining for obj.m1().m2().m3()
        public String m1() { return innerValue.toString() + "_m1"; }
        public String m2() { return this.m1() + "_m2"; }
        public String m3() { return this.m2() + "_m3_" + field; }

        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T value) {
            this.innerValue = value;
        }

        public TargetRecord<T> getOuterRecord() {
            return TargetRecord.this;
        }
    }

    public TargetInner getInner() {
        return inner;
    }
}