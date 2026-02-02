package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

// Interface for source class implements feature
interface SourceInterface<T> {
    List<String> process(T target);
}

/**
 * Sealed generic source class with implements, static nested and member inner class features
 * @param <T> Generic type parameter (CharSequence subtype)
 */
sealed class SourceClass<T extends CharSequence> implements SourceInterface<T> 
permits SourceClass.SourceStaticNested {

    // Static nested class (source feature)
    static final class SourceStaticNested<U> extends SourceClass<U> {
        @Override
        public List<String> process(U target) {
            return new ArrayList<>();
        }
    }

    // Member inner class (source feature)
    class SourceInnerClass {
        public String adjustValue(T value) {
            return value.toString() + "_adjusted";
        }
    }

    /**
     * Method to refactor: overloading, List<String> return, final access, method javadoc, no type params, in source
     * <p>Features: empty statement, variable call, overload_exist, requires_throws</p>
     * @param targetParam Target generic class instance
     * @return Processed list of strings
     * @throws IOException If target parameter is null
     */
    @Override
    public final List<String> process(T targetParam) throws IOException {
        // Method types parameter is:none (method has no additional type params)
        List<String> result = new ArrayList<>();
        
        // Requires_throws validation
        if (targetParam == null) {
            throw new IOException("Target parameter cannot be null");
        }

        // Empty statement
        ;

        // Variable call (target class via generic param)
        TargetClass<T> target = new TargetClass<>(targetParam);
        result.add(target.getValue().toString());

        // Use member inner class
        SourceInnerClass inner = new SourceInnerClass();
        result.add(inner.adjustValue(target.getValue()));

        // Call overloaded method (overload_exist)
        result.addAll(process(target, "extra_param"));

        // Call call_method (target type)
        result.addAll(target.callMethod(new TargetClass.TargetInner[] { // pos: array initialization
            target.new TargetInner(), 
            target.new TargetInner()
        }));

        return result;
    }

    /**
     * Overloaded method (overload_exist feature)
     * @param targetParam Target generic class instance
     * @param extra Extra string parameter
     * @return Extended list of strings
     */
    public final List<String> process(TargetClass<T> targetParam, String extra) {
        List<String> result = new ArrayList<>();
        result.add(targetParam.getValue().toString() + "_" + extra);
        return result;
    }
}

/**
 * Target generic class with javadoc and local inner class features
 * @param <T> Generic type parameter (CharSequence subtype)
 */
class TargetClass<T extends CharSequence> {
    private final T value;

    /**
     * Constructor for TargetClass
     * @param initialValue Initial value for the target class
     */
    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    /**
     * Getter for the target value
     * @return Current value
     */
    public T getValue() {
        return value;
    }

    /**
     * Member inner class for OuterClass.InnerClass.methodName() feature
     */
    public class TargetInner {
        /**
         * Normal method for target_feature: normal
         * @return Processed string
         */
        public String innerMethod() {
            // Local inner class (target_feature)
            class LocalInnerProcessor {
                String process(T val) {
                    return val.toString() + "_local_inner";
                }
            }
            LocalInnerProcessor processor = new LocalInnerProcessor();
            return processor.process(TargetClass.this.value);
        }
    }

    /**
     * call_method: target type, public modifier, normal, OuterClass.InnerClass.methodName(), pos=array initialization, return List<String>
     * @param innerArray Array of TargetInner instances (pos: array initialization)
     * @return Processed list of strings
     */
    public List<String> callMethod(TargetInner[] innerArray) {
        List<String> result = new ArrayList<>();
        for (TargetInner inner : innerArray) {
            // OuterClass.InnerClass.methodName()
            result.add(TargetClass.TargetInner.class.getSimpleName() + "." + inner.innerMethod());
        }
        return result;
    }
}