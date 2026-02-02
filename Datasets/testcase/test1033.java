package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Parent class for source_class extends feature
class RecordParent {
    protected String parentMethod(String input) {
        return input + "_parent_extends";
    }
}

// Target private record class (type parameter + anonymous inner class features)
private record TargetRecord<T extends CharSequence>(T targetField) { // type parameter with bounds
    // Anonymous inner class (target_feature)
    private final Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous: " + targetField + "_anon");
        }
    };

    // Overloading method 1 for call_method feature
    protected Object overloadMethod() {
        return targetField + "_overload1";
    }

    // Overloading method 2 (overloading feature)
    protected Object overloadMethod(String suffix) {
        return targetField + "_overload2_" + suffix;
    }

    // SuperTypeReference method for call_method
    protected Object superTypeMethod(TargetRecord<T> target) {
        // superTypeReference.methodName(arguments) (call CharSequence method)
        return target.targetField().length() + "_superType";
    }

    public void invokeAnonymous() {
        anonymousRunnable.run();
    }
}

// Source record class (default modifier, same package, extends + two anonymous inner classes)
record SourceRecord(String sourceField) extends RecordParent {
    // has_annotation feature
    @RefactorAnnotation
    // Method to be refactored (static, base type return, public access, source position)
    // method types parameter is:Target class
    public static <T extends CharSequence> int moveMethod(TargetRecord<T> targetParam) {
        // Per_condition: source contains the field of the target
        if (targetParam == null) {
            return 0;
        }

        // Variable call (access target field via record accessor)
        T varCall = targetParam.targetField();
        String processed = parentMethod(varCall.toString()); // extends feature usage

        // call_method (target type, protected modifier, overloading + superTypeReference.methodName(), pos=object initialization, return Object)
        Object callResult;
        // Object initialization position
        TargetRecord<T> newTarget = new TargetRecord<>((T) (processed + "_init"));
        // Overloading + superTypeReference.methodName()
        callResult = newTarget.overloadMethod("call");
        callResult = newTarget.superTypeMethod(newTarget);

        // Invoke target's anonymous inner class
        newTarget.invokeAnonymous();

        // First anonymous inner class (source_feature)
        Runnable anonymous1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anon1: " + varCall);
            }
        };
        anonymous1.run();

        // Second anonymous inner class (source_feature)
        Runnable anonymous2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anon2: " + callResult);
            }
        };
        anonymous2.run();

        // No new exception
        return varCall.length(); // base type return (int)
    }
}