package com.refactoring.movemethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// Super class for super constructor invocation
class SuperGenericClass<T> {
    protected T superField;

    public SuperGenericClass(T value) {
        this.superField = value;
    }
}

/**
 * Source generic abstract class with local inner and static nested class features
 * @param <T> Generic type parameter
 */
abstract class SourceClass<T extends Number> extends SuperGenericClass<T> {
    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClass<>((T) Integer.valueOf(6487));

    // Static nested class (source feature)
    static class SourceStaticNested<U> {
        public static U processValue(U val) {
            return (U) Integer.valueOf(((Number) val).intValue() * 2);
        }
    }

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        /**
         * call_method: inner_class type, protected modifier, normal, this.methodName(), pos=property assignment, return void
         * @param inner Target inner recursive class instance
         */
        protected void callMethod(TargetClass<T>.TargetInnerRec inner) {
            // pos: property assignment
            T processedValue = this.processInnerValue(inner); // this.methodName(arguments)
            inner.setValue(processedValue); // property assignment
        }

        /**
         * Normal method for this.methodName() feature
         * @param inner Target inner recursive class instance
         * @return Processed value
         */
        private T processInnerValue(TargetClass<T>.TargetInnerRec inner) {
            return (T) Integer.valueOf(((Number) inner.getValue()).intValue() + 6487);
        }

        /**
         * Method to refactor: normal, base type (int) return, protected access, in source_inner
         * @param targetParams Varargs of target inner recursive class
         * @return Processed integer result
         */
        protected int methodToRefactor(TargetClass<T>.TargetInnerRec... targetParams) {
            // Super constructor invocation
            super SuperGenericClass((T) Integer.valueOf(6487));

            int result = 0;

            // Local inner class (source feature)
            class LocalInnerProcessor<U extends Number> {
                U process(U input) {
                    return (U) Integer.valueOf(((Number) input).intValue() + 100);
                }
            }

            // For loop to process varargs
            for (TargetClass<T>.TargetInnerRec param : targetParams) {
                // Constructor invocation (target inner recursive class)
                TargetClass<T>.TargetInnerRec newInner = targetField.new TargetInnerRec((T) Integer.valueOf(6487));

                // Variable call (target inner class)
                T innerValue = param.getValue();
                result += ((Number) innerValue).intValue();

                // Throw statement
                if (((Number) innerValue).intValue() < 0) {
                    throw new IllegalArgumentException("Value cannot be negative: " + innerValue);
                }

                // Used_by_reflection feature
                try {
                    // Reflectively invoke constructor
                    Constructor<TargetClass.TargetInnerRec> ctor = 
                        TargetClass.TargetInnerRec.class.getDeclaredConstructor(TargetClass.class, Number.class);
                    ctor.setAccessible(true);
                    TargetClass<T>.TargetInnerRec reflectInner = 
                        (TargetClass<T>.TargetInnerRec) ctor.newInstance(targetField, 6487);

                    // Reflectively invoke method
                    Method setMethod = TargetClass.TargetInnerRec.class.getDeclaredMethod("setValue", Number.class);
                    setMethod.invoke(reflectInner, ((Number) innerValue).intValue() * 2);
                    
                    result += ((Number) reflectInner.getValue()).intValue();
                } catch (Exception e) {
                    // No_new_exception feature
                    result += Math.abs(((Number) innerValue).intValue());
                }

                // Use local inner class
                LocalInnerProcessor<T> processor = new LocalInnerProcessor<>();
                T processed = processor.process(innerValue);
                result += ((Number) processed).intValue();

                // Call call_method
                this.callMethod(param);
            }

            // Variable call for targetField (per_condition)
            result += ((Number) targetField.getInnerRec().getValue()).intValue();

            // No_new_exception additional handling
            try {
                if (result > Integer.MAX_VALUE / 2) {
                    throw new ArithmeticException("Result too large");
                }
            } catch (ArithmeticException e) {
                result = Integer.MAX_VALUE / 2;
            }

            return result; // Base type (int) return
        }
    }

    // Abstract method (required for abstract class)
    public abstract T abstractMethod();
}

/**
 * Target generic public class with javadoc and member inner class features
 * @param <T> Generic type parameter (Number subtype)
 */
public class TargetClass<T extends Number> {
    private final T value;
    private final TargetInnerRec innerRec;

    /**
     * Constructor for TargetClass
     * @param initialValue Initial value for the target class
     */
    public TargetClass(T initialValue) {
        this.value = initialValue;
        this.innerRec = new TargetInnerRec(initialValue);
    }

    /**
     * Getter for inner recursive class instance
     * @return TargetInnerRec instance
     */
    public TargetInnerRec getInnerRec() {
        return innerRec;
    }

    /**
     * Member inner class (target_feature) - recursive inner class
     */
    public class TargetInnerRec {
        private T innerValue;

        /**
         * Constructor for TargetInnerRec
         * @param value Initial inner value
         */
        public TargetInnerRec(T value) {
            this.innerValue = value;
        }

        /**
         * Getter for inner value
         * @return Current inner value
         */
        public T getValue() {
            return innerValue;
        }

        /**
         * Setter for inner value
         * @param value New inner value
         */
        public void setValue(T value) {
            this.innerValue = value;
        }
    }
}