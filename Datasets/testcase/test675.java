package com.refactoring.movemethod;

import java.util.function.Consumer;

// Subclass for method_feature: sub_class
class TargetSubClass<T> extends TargetClass<T> {
    public TargetSubClass(T value) {
        super(value);
    }

    // Accessor method for method_feature: accessor
    @Override
    public void setValue(T value) {
        super.setValue(value + "_sub_1"); // method_feature: 1
    }
}

// Source class: generic public class, same package as target, member inner + local inner class
public class SourceClass<T extends CharSequence> {
    // Member inner class (source_inner - method_position)
    public class SourceInnerClass {
        // Accessor feature: public modifier, 1, sub_class, accessor, instanceReference::methodName, pos=switch, return void
        public void accessorMethod(TargetClass<T> target) {
            TargetSubClass<T> subTarget = new TargetSubClass<>(target.getValue()); // method_feature: sub_class
            
            // pos: switch statement
            switch (target.getValue().length()) {
                case 1: // method_feature: 1
                    // instanceReference::methodName (method reference for accessor)
                    Consumer<TargetClass<T>> accessor = TargetClass<T>::setValue; // accessor feature
                    accessor.accept(subTarget);
                    break;
                default:
                    subTarget.setValue((T) ("default_1")); // method_feature: 1
                    break;
            }
        }

        // Overloading method 1 (method type: overloading)
        int methodToRefactor(TargetClass<T> targetParam) {
            // Type declaration statement
            class ProcessedType {
                private T value;
                ProcessedType(T val) { this.value = val; }
                T getVal() { return value; }
            }

            // Per_condition: method contains target parameter
            int result = 0;
            
            // Variable call (target class)
            T targetValue = targetParam.getValue();
            result += targetValue.length();

            // Accessor feature call (pos=switch)
            accessorMethod(targetParam);

            // Local inner class (source feature)
            class LocalInnerProcessor {
                int process(T input) {
                    return input.length() * 1; // method_feature: 1
                }
            }
            result += new LocalInnerProcessor().process(targetValue);

            // Type declaration statement usage
            ProcessedType processed = new ProcessedType(targetValue);
            result += processed.getVal().length();

            // No_new_exception feature
            try {
                Integer.parseInt(targetValue.toString());
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                targetParam.setValue((T) ("formatted_" + targetValue + "_1")); // method_feature: 1
                result += 1; // method_feature: 1
            }

            // Call call_method (source type)
            callMethod(targetParam);

            return result; // Base type (int) return
        }

        // Overloading method 2 (overload_exist feature)
        int methodToRefactor(TargetClass<T> targetParam, String extra) {
            int baseResult = methodToRefactor(targetParam);
            return baseResult + extra.length() + 1; // method_feature: 1
        }

        // call_method: source type, default modifier, normal, OuterClass.InnerClass.methodName(), pos=for, return void
        void callMethod(TargetClass<T> target) {
            // pos: for statement
            for (int i = 0; i < 1; i++) { // method_feature: 1
                // OuterClass.InnerClass.methodName()
                SourceClass.SourceInnerClass.this.accessorMethod(target); // normal feature
            }
        }
    }

    // Helper method to invoke refactored method
    public int invokeMethod(TargetClass<T> target) {
        SourceInnerClass inner = new SourceInnerClass();
        return inner.methodToRefactor(target);
    }
}

// Target class: generic public class, no extra features (target_feature: empty)
public class TargetClass<T> {
    private T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    // Accessor methods (get/set for accessor feature)
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}