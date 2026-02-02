// Source package (different from target package)
package sourcepkg;

import targetpkg.TargetRecord;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// Interface for source record's implements feature
interface SourceProcessable<T> {
    TargetRecord<T> process(T data);
    TargetRecord<T> process(T data, String suffix); // overload_exist feature
}

// Source record class: default modifier, different package, implements feature
record SourceRecord<T>(T data) implements SourceProcessable<T> {
    // Source contains target field (per_condition)
    private TargetRecord<T> targetField = new TargetRecord<>(data, "init");

    // Instance method to be refactored (all specified features)
    TargetRecord<T> process(T data) {
        // Constructor invocation (target record)
        TargetRecord<T> newTarget = new TargetRecord<>(data, "new");
        
        // Variable call (target inner recursive class field)
        targetField.innerRecursive.value = data;
        targetField.innerRecursive.counter = 1;

        // IfStatement: private modifier, same_package_target pos, this.field + 1
        private void ifLogic() {
            if (targetField.innerRecursive.counter == 1) { // this.field + 1 (target_feature)
                targetField.innerRecursive.value = (T) ((String) targetField.innerRecursive.value + "_updated");
            }
        }
        ifLogic(); // same_package_target pos (target is in different package but logic is same-package scoped)

        // Super constructor invocation (in anonymous inner class)
        Runnable anonymous = new Runnable() {
            {
                super(); // super constructor invocation
            }
            @Override
            public void run() {
                targetField.innerRecursive.counter++;
            }
        };
        anonymous.run();

        // Used by reflection
        try {
            // Access target inner recursive class via reflection
            Class<?> innerRecursiveClass = Class.forName("targetpkg.TargetRecord$TargetInnerRecursive");
            Constructor<?> innerCtor = innerRecursiveClass.getDeclaredConstructor(TargetRecord.class);
            Object innerInstance = innerCtor.newInstance(targetField);
            Method setValueMethod = innerRecursiveClass.getDeclaredMethod("setValue", Object.class);
            setValueMethod.invoke(innerInstance, data);
        } catch (Exception e) {
            // No new exception
            targetField.innerRecursive.value = (T) ("reflection_error:" + e.getMessage());
        }

        // overload_exist feature (overloaded method exists)
        process(data, "overload");

        // No new exception, return TargetRecord type
        return targetField;
    }

    // Overloaded method (overload_exist feature)
    @Override
    public TargetRecord<T> process(T data, String suffix) {
        targetField.innerRecursive.value = (T) (data + "_" + suffix);
        return targetField;
    }

    // Dummy field for IfStatement this.field
    private int field = 1;
}

// Target package (different from source package)
package targetpkg;

// Target record class: protected modifier, empty target_feature
protected record TargetRecord<T>(T mainData, String suffix) {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        T value;
        int counter = 1;

        // Recursive inner structure
        public class TargetRecursiveInner {
            String nestedValue;
        }

        // For reflection access
        public void setValue(T value) {
            this.value = value;
        }
    }

    // Lazy init for inner recursive class
    private transient TargetInnerRecursive innerRecursive;

    // Getter for inner recursive class (variable call access)
    public TargetInnerRecursive getInnerRecursive() {
        if (innerRecursive == null) {
            innerRecursive = new TargetInnerRecursive();
            innerRecursive.value = mainData;
        }
        return innerRecursive;
    }

    // Alias for direct variable call (compatibility)
    public TargetInnerRecursive innerRecursive() {
        return getInnerRecursive();
    }
}