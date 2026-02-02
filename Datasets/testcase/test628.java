// Diff package others class for SuperConstructorInvocation pos
package com.refactoring.others;
import com.refactoring.movemethod.SourceRecord;
import com.refactoring.movemethod.TargetRecord;

public class DiffPackageHelper {
    // SuperConstructorInvocation feature: static modifier, obj.field, 2, pos=diff_package_others
    public static <T> TargetRecord<T> invokeSuperConstructor(SourceRecord.SourceInnerRec inner, TargetRecord<T> target) {
        // pos: diff_package_others
        int fieldVal = 2; // target_feature: 2
        target.innerField = fieldVal; // obj.field feature
        // SuperConstructorInvocation (record constructor call)
        TargetRecord<T> newTarget = new TargetRecord<>(target.value(), target.innerField);
        inner.processTarget(newTarget);
        return newTarget;
    }
}

// Back to source package
package com.refactoring.movemethod;
import com.refactoring.others.DiffPackageHelper;
import java.util.function.Supplier;

// Interface for target record implements feature
interface RecordInterface<T> {
    T getValue();
    int getInnerField();
}

// Source record class: default modifier, same package as target, no extra features
record SourceRecord(String sourceField) {
    // Per_condition: source contains target record field
    private final TargetRecord<String> targetField = new TargetRecord<>("initial_target_2", 2); // target_feature: 2

    // Source_inner_rec (inner recursive class for method_position)
    class SourceInnerRec {
        // Method to refactor: instance, TargetRecord return, default access, in source_inner_rec
        <T> TargetRecord<T> methodToRefactor(TargetRecord<T> targetParam) {
            // Variable call (target record fields)
            T targetValue = targetParam.value();
            int innerField = targetParam.getInnerField();
            
            // SuperConstructorInvocation feature call (diff_package_others)
            TargetRecord<T> superCtorTarget = DiffPackageHelper.invokeSuperConstructor(this, targetParam);

            // Depends_on_inner_class feature (local inner class)
            class InnerProcessor<T> {
                T process(T input) {
                    return (T) (input.toString() + "_processed_2"); // target_feature: 2
                }
            }
            InnerProcessor<T> processor = new InnerProcessor<>();
            T processedValue = processor.process(targetValue);

            // Assert statement
            assert innerField == 2 : "Inner field must be 2"; // target_feature: 2

            // No_new_exception feature
            try {
                Integer.parseInt(processedValue.toString());
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                processedValue = (T) ("formatted_" + processedValue);
            }

            // Create new target record (return TargetClass Type)
            TargetRecord<T> result = new TargetRecord<>(processedValue, 2); // target_feature: 2
            
            // Variable call for targetField (per_condition)
            result.innerField = targetField.getInnerField();

            return result;
        }

        // Helper method for DiffPackageHelper
        <T> void processTarget(TargetRecord<T> target) {
            target.innerField = 2; // target_feature: 2
        }
    }
}

// Target record class: protected, implements interface, anonymous inner class feature
protected record TargetRecord<T>(T value, int innerField) implements RecordInterface<T> {
    // Anonymous inner class (target_feature)
    private final Supplier<T> valueSupplier = new Supplier<T>() {
        @Override
        public T get() {
            return value;
        }
    };

    // Implements interface methods
    @Override
    public T getValue() {
        return valueSupplier.get();
    }

    @Override
    public int getInnerField() {
        return innerField;
    }
}