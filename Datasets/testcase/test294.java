package com.refactoring.movemethod;

import java.util.function.Function;

// Functional interface for target class implementation
interface GenericProcessor<T> {
    T process(T input);
}

// Source generic class (public modifier, same package, member inner + anonymous inner class)
public class SourceClass<T> {
    // Target class field to satisfy pre-condition
    TargetClass<String> targetField = new TargetClass<>();

    // Static field for depends_on_static_field feature
    private static int staticField = 1;

    // Member inner class (source feature)
    public class SourceMemberInner<U> {
        U innerValue;
    }

    // Anonymous inner class (source feature)
    Function<T, T> anonymousInner = new Function<>() {
        @Override
        public T apply(T t) {
            return t;
        }
    };

    // Inner record class containing the accessor method (source_inner_rec position)
    public record SourceInnerRec<X>(SourceClass<X> outerInstance) {
        /**
         * Method javadoc for the final accessor method
         * Accessor method to be moved (final, void return, source_inner_rec position)
         */
        final void getTargetAccessor() {
            // TryStatement (private modifier, same_package_target pos, obj.field + 1 features)
            private TargetClass<String> tryTargetObj = outerInstance.targetField;
            try {
                int tryVar = tryTargetObj.memberInner.targetField + 1; // obj.field + 1
                // Break statement feature
                loop:
                for (int i = 0; i < 5; i++) {
                    if (i == tryVar) break loop;
                    // Variable call feature
                    String varCall = String.valueOf(tryVar) + outerInstance.staticField;
                }
            } catch (Exception e) {
                // No new exception instantiation (no_new_exception feature)
            }

            // Constructor invocation feature
            SourceMemberInner<String> innerObj = outerInstance.new SourceMemberInner<>();
            TargetClass<String>.TargetMemberInner targetInner = tryTargetObj.new TargetMemberInner();

            // Depends_on_static_field feature
            innerObj.innerValue = String.valueOf(outerInstance.staticField);

            // Return this; feature (within nested method)
            Function<SourceInnerRec<X>, SourceInnerRec<X>> returnThisFunc = rec -> {
                return rec; // return this equivalent for the record instance
            };
            returnThisFunc.apply(this);

            // Variable call with target field
            String finalVarCall = tryTargetObj.process("test") + innerObj.innerValue;
            System.out.println(finalVarCall);
        }
    }
}

/**
 * Target generic class with javadoc, implements interface and member inner class
 * @param <V> Generic type parameter
 */
protected class TargetClass<V> implements GenericProcessor<V> {
    // Member inner class (target feature)
    public class TargetMemberInner {
        int targetField = 10;
    }

    // Implementation of GenericProcessor interface
    @Override
    public V process(V input) {
        return input;
    }
}