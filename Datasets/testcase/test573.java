package com.refactor;

import java.util.function.Function;

// Source class: enum, public modifier, same package as target, type parameter + local inner + anonymous inner class
public enum SourceEnum<T> {
    INSTANCE;

    // Target class field reference (per_condition)
    private final TargetEnum targetField = TargetEnum.VALUE1;

    // Source inner recursive class (source_inner_rec - method position)
    class SourceInnerRec {
        // Method to refactor: normal type, Object return, final access modifier, in source_inner_rec
        public final Object methodToMove() {
            // Instance feature (protected modifier, 1, parent_class, instance, ClassName::methodName in instance code blocks)
            protected TargetEnum instanceResult;
            {
                // Instance code block (pos: instance code blocks)
                Function<Integer, TargetEnum> func = TargetEnum::getInstance;
                instanceResult = func.apply(1);
            }

            // Variable call (target field access)
            String targetVar = targetField.innerClassInstance.innerField;

            // Local inner class (source feature)
            class SourceLocalInner {
                void localMethod() {}
            }
            new SourceLocalInner().localMethod();

            // Anonymous inner class (source feature)
            Runnable anonymousInner = new Runnable() {
                @Override
                public void run() {
                    SourceInnerRec.this.methodToMove();
                }
            };

            // No new exception thrown
            return new Object[]{targetVar, instanceResult, anonymousInner};
        }
    }
}

// Parent class for instance feature (parent_class)
class TargetEnumParent {
    public TargetEnum getInstance(int param) {
        return TargetEnum.VALUE1;
    }
}

// Target class: enum, default modifier, member inner class (target_feature)
enum TargetEnum extends TargetEnumParent {
    VALUE1, VALUE2;

    // Member inner class (target_feature)
    class TargetInnerClass {
        String innerField = "targetInnerValue";
    }

    // Inner class instance (for variable call)
    TargetInnerClass innerClassInstance = new TargetInnerClass();
}