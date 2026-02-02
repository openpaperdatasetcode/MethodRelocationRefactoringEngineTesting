package com.refactor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: generic, public, same package as target, extends + implements + permits + member inner + anonymous inner class
sealed public class SourceClass<T extends Number> extends SuperClass<T> implements SampleInterface<T> permits SourceSubClass<T> {
    // Member inner class (source feature)
    class SourceMemberInner {}

    // Annotation for has_annotation feature
    @Retention(RetentionPolicy.RUNTIME)
    @interface MethodAnno {}

    /**
     * Method to refactor: varargs, returns TargetClass type, default access, contains target parameter (per_condition)
     */
    @MethodAnno // has_annotation
    TargetClass<String> methodToMove(TargetClass<T>... targetParams) {
        // Super constructor invocation (implicit super() for Object, explicit for parent class)
        super();

        // Constructor invocation (target class)
        TargetClass<String> targetInstance = new TargetClass<>();

        // Throw statement (predefined exception, no new exception instantiated)
        try {
            if (targetParams == null) {
                throw new IllegalArgumentException("Target parameters cannot be null");
            }
        } catch (IllegalArgumentException e) {
            // No new exception thrown
            return new TargetClass<>();
        }

        // Type declaration statement
        class LocalTypeDeclaration<T> {}
        LocalTypeDeclaration<String> localType = new LocalTypeDeclaration<>();

        // Numbers:2, abstract modifier, exp:NullLiteral
        abstract class NullLiteralClass {
            private final T nullVal1 = null;
            private final T nullVal2 = null;
        }

        // Variable call (target parameter access)
        TargetClass<T>.TargetInnerRec innerRec = null;
        if (targetParams.length > 0) {
            innerRec = targetParams[0].new TargetInnerRec();
            String targetVar = innerRec.innerField;
        }

        // Anonymous inner class (source feature)
        SampleInterface<T> anonymousInner = new SampleInterface<T>() {
            @Override
            public void interfaceMethod() {
                SourceClass.this.methodToMove(targetParams);
            }
        };

        // No new exception thrown
        return targetInstance;
    }

    @Override
    public void interfaceMethod() {
        // Implements feature - interface method implementation
    }
}

// Permits subclass for source class feature
non-sealed class SourceSubClass<T extends Number> extends SourceClass<T> {}

// Super class for source class extends feature
class SuperClass<T> {}

// Interface for source class implements feature
interface SampleInterface<T> {
    void interfaceMethod();
}

// Target class: generic, default modifier, no additional features (target_feature: [])
class TargetClass<U> {
    // Target inner recursive class (target_inner_rec)
    class TargetInnerRec {
        String innerField = "targetInnerValue";
    }
}