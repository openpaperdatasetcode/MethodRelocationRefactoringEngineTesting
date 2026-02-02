// Source package (different from target package)
package sourcepkg;

import targetpkg.TargetClass;
import java.util.List;

// Source class: normal, protected modifier, different package, empty features
protected class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>("initial_value");

    /**
     * Instance method to be refactored (all specified features)
     * @param <T> generic type with bounds (with_bounds feature)
     */
    @SuppressWarnings("unchecked") // has_annotation
    public <T extends CharSequence & Comparable<T>> void refactorMethod() {
        // Variable call (target class field)
        targetField.value = (T) "refactored_value";
        targetField.counter = 1;

        // Empty statement
        ;

        // TryStatement: private modifier, source pos, obj.field + 1
        private void tryLogic() {
            try {
                TargetClass<T> tempTarget = (TargetClass<T>) targetField;
                tempTarget.innerClass.innerValue = (T) "try_block_1"; // obj.field + 1 (target_feature)
                // Access target inner class field (obj.field)
                tempTarget.innerClass.counter = 1; // 1 (target_feature)
            } catch (ClassCastException e) {
                // No new exception
                targetField.counter = -1;
            }
        }
        tryLogic();

        // with_bounds feature (generic type with multiple bounds)
        T boundedValue = (T) "bounded_1";
        targetField.value = boundedValue;

        // No new exception, void return
    }
}

// Target package (different from source package)
package targetpkg;

/**
 * Javadoc for TargetClass (target_feature: javadoc)
 * Normal class with member inner class feature
 * @param <T> generic type parameter
 */
// Target class: normal, public modifier, javadoc + member inner class features
public class TargetClass<T extends CharSequence> { // with_bounds compatibility
    T value;
    int counter;

    // Member inner class (target_feature)
    public class TargetInnerClass {
        T innerValue;
        int counter = 1;

        public TargetInnerClass(T value) {
            this.innerValue = value;
        }
    }

    // Lazy initialization of inner class
    private TargetInnerClass innerClass;

    // Constructor
    public TargetClass(T value) {
        this.value = value;
        this.innerClass = new TargetInnerClass(value);
    }

    // Getter for inner class (variable call access)
    public TargetInnerClass getInnerClass() {
        return innerClass;
    }

    // Alias for direct variable call
    public TargetInnerClass innerClass() {
        return innerClass;
    }
}