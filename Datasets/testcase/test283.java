package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

// Functional interface for source record implementation
interface RecordProcessor {
    void process();
}

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RecordMethodAnnotation {}

// Permitted subclass for source record permits feature
non-sealed class SourceRecordSub extends SourceRecord {}

// Source record class (default modifier, same package, implements + permits + member inner + local inner class)
sealed record SourceRecord(String id) implements RecordProcessor permits SourceRecordSub {
    // Target class field to satisfy pre-condition
    private final TargetRecord targetField = new TargetRecord("target_id", "target_value");

    // Member inner class (source feature)
    public class SourceMemberInner {
        // Inner record class containing the method to be moved (source_inner_rec position)
        public record SourceInnerRec(SourceRecord outerInstance) {
            /**
             * Instance method to be moved (final, returns TargetRecord type, source_inner_rec position)
             * @return TargetRecord instance
             */
            @RecordMethodAnnotation
            public final TargetRecord moveableMethod() {
                // Constructor feature (default modifier, exception throwing pos, 1 + sub_class + constructor + this.methodName(arguments))
                default TargetRecordSub constructorObj;
                try {
                    constructorObj = new TargetRecordSub("sub_1", "sub_value"); // Number 1 feature (sub_1)
                    // this.methodName(arguments) call
                    Object constructorResult = this.moveableMethod("overload_arg");
                } catch (Exception e) {
                    throw new RuntimeException(e); // No new custom exception (no_new_exception)
                }

                // Labeled statement feature
                label:
                for (int i = 0; i < 5; i++) {
                    if (i == 1) break label;
                    // this.var = var feature
                    this.outerInstance.targetField.innerClass.innerField = "updated_value";
                }

                // Used by reflection feature
                try {
                    Method method = TargetRecord.class.getDeclaredMethod("innerClassAccessor");
                    method.invoke(outerInstance.targetField);
                } catch (Exception e) {
                    // No new exception instantiation
                }

                // Variable call feature
                String varCall = outerInstance.targetField.innerClass.innerField + outerInstance.id();

                // No new exception instantiation (no_new_exception feature)
                System.out.println(varCall);

                // Return TargetRecord type
                return outerInstance.targetField;
            }

            // Overloaded method for overload_exist feature
            public final Object moveableMethod(String overloadParam) {
                return overloadParam + outerInstance.targetField.value();
            }

            // Local inner class (source feature)
            private class SourceLocalInner {
                int localField = 10;
            }
        }
    }

    // Implementation of RecordProcessor interface
    @Override
    public void process() {
        new SourceMemberInner().new SourceInnerRec(this).moveableMethod();
    }
}

// Target record class (public modifier, member inner class target feature)
public record TargetRecord(String id, String value) {
    // Member inner class (target feature)
    public class TargetMemberInner {
        String innerField = "target_inner_field";
    }

    // Instance of member inner class
    private final TargetMemberInner innerClass = new TargetMemberInner();

    // Accessor method for reflection feature
    public TargetMemberInner innerClassAccessor() {
        return innerClass;
    }
}

// Subclass for constructor feature (sub_class)
class TargetRecordSub extends TargetRecord {
    public TargetRecordSub(String id, String value) {
        super(id, value);
    }
}