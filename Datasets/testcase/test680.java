package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Others class for call_method feature
class OthersClass {
    // call_method: others_class type, public modifier, instance, ClassName::methodName, pos=ternary operators, return List<String>
    public static <T> List<String> callMethod(TargetClass<T> target) {
        // target_feature: ClassName::methodName (method reference)
        Function<TargetClass<T>, List<String>> methodRef = TargetClass::processTarget; // ClassName::methodName
        // pos: ternary operators
        boolean flag = target != null;
        return flag ? methodRef.apply(target) : new ArrayList<>(); // instance feature (call instance method)
    }
}

/**
 * Source abstract class: protected modifier, same package as target, type parameter feature
 * @param <T> Generic type parameter (CharSequence subtype)
 */
protected abstract class SourceClass<T extends CharSequence> {
    // Per_condition: source contains target class field
    private static final TargetClass<String> targetField = new TargetClassImpl<>("initial_target_1"); // numbers=1

    // Source_inner_rec (inner recursive class for method_position)
    public static class SourceInnerRec {
        // Volatile boolean literal for numbers=1, modifier=volatile, exp=BooleanLiteral
        private volatile boolean volatileFlag = true; // numbers=1, BooleanLiteral (true), volatile modifier

        // Method to refactor: static, List<String> return, private access, in source_inner_rec
        private static <T> List<String> methodToRefactor(TargetClass<T> targetParam) {
            // Raw type feature (use raw TargetClass)
            TargetClass rawTarget = new TargetClassImpl("raw_type_1"); // numbers=1

            // Try statement + requires_try_catch feature
            try {
                // Variable call (target class)
                T targetValue = targetParam.getValue();
                if (targetValue == null) {
                    throw new IllegalArgumentException("Target value is null");
                }

                // Depends_on_inner_class (use target's local inner class)
                List<String> innerProcessed = targetParam.processTarget();

                // BooleanLiteral feature (volatileFlag)
                if (new SourceInnerRec().volatileFlag) { // numbers=1
                    innerProcessed.add("boolean_literal_true_1"); // numbers=1
                }

                // Call overloaded method (overload_exist)
                innerProcessed.addAll(methodToRefactor(targetParam, "extra_1")); // numbers=1

                // Return statement
                return innerProcessed;
            } catch (Exception e) {
                // Requires_try_catch handling
                List<String> errorList = new ArrayList<>();
                errorList.add("error_handling_1"); // numbers=1
                return errorList;
            }
        }

        // Overloaded method (overload_exist feature)
        private static <T> List<String> methodToRefactor(TargetClass<T> targetParam, String extra) {
            List<String> result = new ArrayList<>();
            result.add(targetParam.getValue().toString() + "_" + extra);
            return result;
        }

        // Helper method to invoke refactored method
        public static <T> List<String> invokeStaticMethod(TargetClass<T> target) {
            return methodToRefactor(target);
        }
    }

    // Abstract method (required for abstract source class)
    public abstract T abstractSourceMethod();
}

/**
 * Target abstract class: protected modifier, javadoc + local inner class features
 * @param <T> Generic type parameter (CharSequence subtype)
 */
protected abstract class TargetClass<T extends CharSequence> {
    private T value;

    /**
     * Constructor for TargetClass
     * @param initialValue Initial value for the target class
     */
    protected TargetClass(T initialValue) {
        this.value = initialValue;
    }

    /**
     * Instance method for call_method instance feature
     * @return Processed list of strings
     */
    public List<String> processTarget() {
        // Local inner class (target_feature)
        class LocalInnerProcessor {
            String process(T val) {
                return val.toString() + "_local_inner_1"; // numbers=1
            }
        }

        List<String> result = new ArrayList<>();
        result.add(new LocalInnerProcessor().process(this.value));
        return result;
    }

    // Variable call getter
    public T getValue() {
        return value;
    }

    // Abstract method (required for abstract target class)
    public abstract void abstractTargetMethod();
}

// Concrete implementation of target abstract class (for instantiation)
class TargetClassImpl<T extends CharSequence> extends TargetClass<T> {
    public TargetClassImpl(T initialValue) {
        super(initialValue);
    }

    @Override
    public void abstractTargetMethod() {
        // Empty implementation for abstract class instantiation
    }
}