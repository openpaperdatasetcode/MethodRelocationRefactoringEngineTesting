// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source generic final class (different package, no features)
public final class SourceClass<T extends Number> {
    // Instance method (public access, List<String> return, no generic type params for method)
    public List<String> processTarget(TargetClass<T> targetParam) {
        List<String> result = new ArrayList<>();
        // Per_condition: contains target parameter (variable call)
        if (targetParam == null) {
            try {
                throw new IOException("Target parameter is null");
            } catch (IOException e) {
                // No_new_exception: catch without throwing new
                result.add("error: " + e.getMessage());
                return result;
            }
        }

        // Do statement
        int count = 0;
        do {
            // Variable call (target field access)
            T targetValue = targetParam.getValue();
            result.add(targetValue.toString());
            
            // Depends_on_inner_class: use target's member inner class
            TargetClass<T>.TargetMemberInner inner = targetParam.new TargetMemberInner();
            inner.updateValue(targetValue);
            
            // Uses_outer_this: access outer class instance
            this.helperMethod(inner);
            
            // Varargs method call (pos: constructor parameter list)
            TargetClass<T> constructorParam = new TargetClass<>(targetValue);
            publicVarargsMethod(constructorParam, 2, inner);
            
            count++;
        } while (count < 2);

        return result;
    }

    // Varargs method (public modifier, void return, constructor parameter list pos)
    public void publicVarargsMethod(TargetClass<T> target, int num, TargetClass<T>.TargetMemberInner... inners) {
        // 2: literal value
        if (num == 2) {
            // instanceReference::methodName (method reference)
            Consumer<T> consumer = target::setValue;
            for (TargetClass<T>.TargetMemberInner inner : inners) {
                consumer.accept(inner.getValue());
            }
        }
    }

    // Helper method for uses_outer_this
    private void helperMethod(TargetClass<T>.TargetMemberInner inner) {
        // Empty implementation
    }
}

// Target class package (different from source)
package com.refactoring.target;

import java.util.function.Consumer;

// Supporting interface for target implements feature
interface TargetInterface<T> {
    void processValue(T value);
}

// Target generic protected class (implements + member inner class)
protected class TargetClass<T extends Number> implements TargetInterface<T> {
    private T value;

    // Constructor (for varargs method pos: parameter list)
    public TargetClass(T value) {
        this.value = value;
    }

    // Member inner class (target feature)
    public class TargetMemberInner {
        private T innerValue;

        public void updateValue(T val) {
            this.innerValue = val;
            // Uses_outer_this: access outer target class instance
            TargetClass.this.setValue(val);
        }

        public T getValue() {
            return innerValue;
        }
    }

    // Getter/Setter for variable call
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    // Implement interface method
    @Override
    public void processValue(T value) {
        this.value = value;
    }
}