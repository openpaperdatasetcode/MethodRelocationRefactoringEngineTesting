package com.refactoring.movemethod;

import java.util.List;

// Source generic class (default modifier, same package)
class SourceClass<T extends Number & Comparable<T>> {
    // Member inner class (source feature)
    class SourceMemberInner {}

    // Local inner class (source feature)
    public void sourceMethodWithLocalInner() {
        class LocalInnerClass {
            void process() {
                TargetClass<T> target = new TargetClass<>();
                TargetClass<T>.TargetInner inner = target.new TargetInner();
                SourceClass.this.processTarget(inner);
            }
        }
        new LocalInnerClass().process();
    }

    // Instance method (public access, return TargetClass Type, source position)
    public TargetClass<T>.TargetInner processTarget(TargetClass<T>.TargetInner targetParam) {
        // Per_condition: contains target parameter (variable call)
        T targetValue = targetParam.getValue();
        
        // With_bounds: use generic type with bounds
        T[] array = (T[]) new Number[]{1, 2, 3};
        // ArrayAccess with number 1 and final modifier
        final int arrayIndex = 1;
        T arrayVal = array[arrayIndex];

        // Switch case statement
        switch (targetValue.intValue()) {
            case 1:
                targetParam.setValue(arrayVal);
                break;
            case 2:
                targetParam.setValue(targetValue);
                break;
            default:
                break;
        }

        // No_new_exception (empty try-catch)
        try {
            // No exception thrown
        } catch (Exception e) {
            // Do nothing
        }

        return targetParam;
    }
}

/**
 * Javadoc for target generic class (target_feature: javadoc)
 * @param <T> Generic type with bounds
 */
private class TargetClass<T extends CharSequence> extends BaseClass {
    // Static nested class (target_feature)
    static class TargetStaticNested<U extends Number> {}

    // Target inner class (target_inner)
    class TargetInner {
        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}

// Super class for target_class extends feature
class BaseClass {}