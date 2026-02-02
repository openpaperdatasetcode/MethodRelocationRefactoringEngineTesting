package com.refactor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Consumer;

// Source class: normal, private modifier, same package as target, local inner + static nested class
private class SourceClass {
    // Annotation for has_annotation feature
    @Retention(RetentionPolicy.RUNTIME)
    @interface MethodAnno {}

    // Static nested class (source feature)
    static class SourceStaticNested {}

    /**
     * Method to refactor: static, base type (int) return, protected access, contains target parameter (per_condition)
     */
    @MethodAnno // has_annotation
    protected static int methodToMove(TargetClass... targetParams) {
        // Overriding feature (default modifier, 1, others_class, overriding, super.methodName() in array initialization)
        TargetClass[] targetArray = new TargetClass[]{
            new OverrideClass() {
                @Override
                public TargetClass overrideMethod(int param) {
                    return super.overrideMethod(1); // super.methodName(), param=1
                }
            }.overrideMethod(1)
        };

        // Constructor invocation (target inner recursive class)
        TargetClass.TargetInnerRec innerRecInstance = new TargetClass().new TargetInnerRec();

        // Enhanced for statement
        int sum = 0;
        for (TargetClass target : targetParams) {
            // Variable call (target parameter access)
            sum += target.innerRecInstance.value;
        }

        // Type declaration statement
        class LocalTypeDeclaration {}
        LocalTypeDeclaration localType = new LocalTypeDeclaration();

        // NullPointerException (no new exception thrown - use existing exception type)
        try {
            if (targetParams == null) {
                throw new NullPointerException("Target parameters cannot be null");
            }
        } catch (NullPointerException e) {
            // No new exception thrown
            return 0;
        }

        // Override violation (invalid override - return type mismatch example)
        class OverrideViolationClass extends OverrideClass {
            // Override violation: return type differs (should be TargetClass, here int)
            @Override
            public int overrideMethod(int param) { // Compile error (override violation)
                return 1;
            }
        }

        // Local inner class (source feature)
        class SourceLocalInner {
            void processTarget(TargetClass target) {
                sum += target.innerRecInstance.value;
            }
        }
        new SourceLocalInner().processTarget(targetArray[0]);

        // Call method: others_class type, private modifier, instance + instanceReference::methodName in if/else
        Consumer<TargetClass> consumer;
        if (sum > 0) {
            consumer = OthersClass::instanceMethod;
        } else {
            consumer = OthersClass::instanceMethod;
        }
        consumer.accept(targetArray[0]);

        // No new exception thrown
        return sum;
    }
}

// Others class for call_method feature
class OthersClass {
    // Call method: private modifier, instance, instanceReference::methodName
    private void instanceMethod(TargetClass target) {}
}

// Override base class for overriding feature (others_class)
class OverrideClass {
    public TargetClass overrideMethod(int param) {
        return new TargetClass();
    }
}

// Target class: normal, private modifier, local inner class (target_feature)
private class TargetClass {
    // Target inner recursive class (target_inner_rec)
    class TargetInnerRec {
        int value = 1;

        // Recursive method (inner_rec feature)
        public int recursiveMethod(int param) {
            return param > 0 ? recursiveMethod(param - 1) : 0;
        }
    }

    // Inner recursive class instance
    TargetInnerRec innerRecInstance = new TargetInnerRec();

    // Local inner class (target_feature)
    public void targetMethod() {
        class TargetLocalInner {
            void localMethod() {}
        }
        new TargetLocalInner().localMethod();
    }
}