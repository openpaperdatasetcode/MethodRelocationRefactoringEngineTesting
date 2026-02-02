// Others package for method_feature: others_class
package com.refactoring.others;
import com.refactoring.target.TargetClass;

public class OthersClass<T> {
    public TargetClass<T> processTarget(TargetClass<T> target) {
        target.setValue((T) (target.getValue() + "_others_1")); // method_feature: 1
        return target;
    }
}

// Source package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.target.TargetSuperClass;
import com.refactoring.others.OthersClass;

// Sealed interface with permits for source class feature
sealed interface SourceSealed<T> permits SourceClass<T>, SourceClass.SourceStaticNested<T> {}

// Super class for source class extends feature
class SourceSuperClass<T> {
    protected T superField = (T) "source_super_3"; // target_feature: 3
}

// Source class: generic final class, different package from target, extends, permits, local inner, static nested class
final class SourceClass<T extends CharSequence> extends SourceSuperClass<T> implements SourceSealed<T> {
    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClass<>((T) "initial_target_3"); // target_feature: 3

    // Static nested class (source feature)
    static final class SourceStaticNested<U> implements SourceSealed<U> {
        public static <V> V processValue(V val) {
            return (V) (val.toString() + "_static_3"); // target_feature: 3
        }
    }

    // ExpressionStatement feature: public modifier, super.field, 3, pos=inner class
    public class ExprStmtInner { // pos: inner class
        public void processExpr(TargetClass<T>.TargetInner inner) {
            // ExpressionStatement feature
            inner.setInnerValue((T) (SourceClass.super.superField + "_expr_3")); // super.field, target_feature: 3
            // Additional expression statement
            inner.innerField = 3; // target_feature: 3
        }
    }

    // Static method feature: public modifier, 1, others_class, static, instanceReference.methodName(), pos=object initialization, return TargetClass Type
    public static <U extends CharSequence> TargetClass<U> staticHelperMethod(U value) {
        // pos: object initialization (instanceReference as initialized object)
        OthersClass<U> othersInstance = new OthersClass<>(); // others_class
        TargetClass<U> target = new TargetClass<>(value); // object initialization
        // instanceReference.methodName(arguments)
        return othersInstance.processTarget(target); // method_feature: static, 1
    }

    // Overloading method 1 (method type: overloading)
    strictfp int methodToRefactor(TargetClass<T>.TargetInner... innerParams) {
        int result = 0;

        // Type declaration statement
        class ProcessedType {
            private T value;
            ProcessedType(T val) { this.value = val; }
            T getVal() { return value; }
        }

        // Process varargs parameters
        for (TargetClass<T>.TargetInner param : innerParams) {
            // Variable call (target inner class)
            T innerValue = param.getInnerValue();
            result += innerValue.length();

            // ExpressionStatement feature call (inner class)
            new ExprStmtInner().processExpr(param);

            // Static method feature usage (object initialization)
            TargetClass<T> staticTarget = staticHelperMethod((T) ("static_init_1")); // method_feature: 1
            result += staticTarget.getValue().length();

            // Requires_try_catch feature
            try {
                Integer.parseInt(innerValue.toString());
            } catch (NumberFormatException e) {
                // Requires_try_catch handling
                param.setInnerValue((T) ("formatted_" + innerValue));
            }

            // Local inner class (source feature)
            class LocalInnerProcessor {
                T process(T input) {
                    return (T) (input + "_local_3"); // target_feature: 3
                }
            }
            T processed = new LocalInnerProcessor().process(innerValue);
            result += processed.length();
        }

        // Variable call for targetField (per_condition)
        result += targetField.getInner().getInnerValue().length();

        // Call call_method (target type)
        targetField.callMethod(new TargetClass<T>.TargetInner[] { // pos: array initialization
            targetField.new TargetInner(),
            targetField.new TargetInner()
        });

        return result; // Base type (int) return
    }

    // Overloading method 2 (overload_exist feature)
    strictfp int methodToRefactor(TargetClass<T>.TargetInner inner, String extra) {
        int baseResult = methodToRefactor(inner);
        return baseResult + extra.length() + 3; // target_feature: 3
    }
}

// Target package
package com.refactoring.target;

// Super class for target class extends feature
class TargetSuperClass<T> {
    protected T superValue = (T) "target_super_3"; // target_feature: 3
}

// Target class: generic protected class, extends, static nested class feature
protected class TargetClass<T extends CharSequence> extends TargetSuperClass<T> {
    private T value;
    private final TargetInner inner = new TargetInner();

    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        public static U staticProcess(U val) {
            return (U) (val.toString() + "_target_static_3"); // target_feature: 3
        }
    }

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Target_inner (target inner class)
    public class TargetInner {
        private T innerValue;
        int innerField; // For super.field/obj.field feature

        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T value) {
            this.innerValue = value;
        }

        // Abstract method for call_method abstract feature
        public abstract static class AbstractInner {
            public abstract void abstractMethod(T value);
        }
    }

    // call_method: target type, final modifier, abstract, ClassName::methodName, pos=array initialization, return void
    public final void callMethod(TargetInner[] innerArray) {
        // pos: array initialization (innerArray as parameter)
        for (TargetInner inner : innerArray) {
            // target_feature: abstract (implement abstract method)
            TargetInner.AbstractInner abstractImpl = new TargetInner.AbstractInner() {
                @Override
                public void abstractMethod(T value) {
                    // ClassName::methodName (method reference)
                    inner.setInnerValue(TargetStaticNested::staticProcess.apply(value));
                }
            };
            abstractImpl.abstractMethod((T) (this.superValue + "_call_3")); // target_feature: 3
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TargetInner getInner() {
        return inner;
    }
}

// Target super class (for target class extends feature)
package com.refactoring.target;

class TargetSuperClass<T> {
    protected T superValue;
}