// Target class package (different from source)
package com.refactoring.target;

import java.util.ArrayList;
import java.util.List;

// Parent class for target_class extends feature
class TargetParentClass<T> {
    protected T parentField;

    protected T getParentField() {
        return parentField;
    }
}

// Target class (normal class, private modifier, type parameter + extends + member inner class)
private class TargetClass<T extends CharSequence> extends TargetParentClass<T> {
    T targetField; // For per_condition (source contains this field)

    // Member inner class (target_feature) - recursive (target_inner_rec)
    public class TargetInnerRec {
        T innerField;
        TargetInnerRec nestedInner; // Recursive feature

        public TargetInnerRec(T value) {
            this.innerField = value;
            this.nestedInner = new TargetInnerRec(null); // Recursive constructor
        }

        public T getInnerField() {
            return innerField;
        }

        public void setInnerField(T value) {
            this.innerField = value;
        }
    }
}

// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.List;
import java.util.ArrayList;

// Functional interface for source_class implements feature
interface SourceFunctionalInterface {
    <T extends CharSequence> List<String> processTarget(TargetClass<T>.TargetInnerRec target);
}

// Source class (normal class, public modifier, different package, implements feature)
public class SourceClass implements SourceFunctionalInterface {

    // Method to be refactored (instance, List<String> return, protected access, source position)
    @Override
    protected <T extends CharSequence> List<String> processTarget(TargetClass<T>.TargetInnerRec targetParam) {
        List<String> result = new ArrayList<>();
        // Per_condition: source contains the field of the target (target_inner_rec)
        if (targetParam == null) {
            // Throw statement
            throw new IllegalArgumentException("TargetInnerRec parameter cannot be null");
        }

        // Requires try-catch
        try {
            // Variable call (access target inner field - per_condition)
            T varCall = targetParam.getInnerField();
            if (varCall == null) {
                varCall = (T) "default_value";
                targetParam.setInnerField(varCall);
            }

            // Collect field values (including recursive nested inner)
            result.add("Inner field: " + varCall);
            TargetClass<T>.TargetInnerRec current = targetParam.nestedInner;
            int nestedCount = 1;
            while (current != null) {
                result.add("Nested inner field (" + nestedCount + "): " + (current.getInnerField() != null ? current.getInnerField() : "null"));
                current = current.nestedInner;
                nestedCount++;
            }

            // Modify field and add to result
            T modifiedVal = (T) (varCall + "_source_modified");
            targetParam.setInnerField(modifiedVal);
            result.add("Modified inner field: " + modifiedVal);

        } catch (ClassCastException e) {
            // Requires try-catch handling
            result.add("Error processing target: " + e.getMessage());
            // Re-throw wrapped exception (throw statement)
            throw new RuntimeException("Failed to process TargetInnerRec", e);
        }

        return result;
    }
}