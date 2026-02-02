package com.refactoring.movemethod;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

// Annotation for call_method pos=attribute values of annotations
@Retention(RetentionPolicy.RUNTIME)
@interface CallMethodAnnotation {
    String value() default "new SourceClass.StaticNestedSource().sourceCallMethod()"; // new ClassName().method()
}

// Source class: normal class, private modifier, same package, permits, local inner class, static nested class
private sealed class SourceClass permits SourceClass.StaticNestedSource { // permits feature
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    
    // Static nested class (source feature)
    static final class StaticNestedSource extends SourceClass { // permits implementation
        // Call_method: source, default modifier, normal, new ClassName().method(), return void
        default void sourceCallMethod() {}
    }

    // Method: varargs, return TargetClass Type, public access, source position
    @CallMethodAnnotation // call_method pos=annotation attribute values
    public TargetClass.MemberInnerTarget moveableVarargsMethod(String... args) throws IOException { // IOException feature
        // Variable call feature
        String localVar = targetField.targetField;
        localVar = args.length > 0 ? args[0] : localVar;

        // ConstructorInvocation: private modifier, obj.field, 1, pos=source
        private TargetClass.MemberInnerTarget createInnerInstance() {
            TargetClass.MemberInnerTarget innerObj = new TargetClass().new MemberInnerTarget(); // constructor invocation
            innerObj.innerField = "1"; // target_feature "1"
            localVar = innerObj.innerField; // target_feature "obj.field"
            return innerObj;
        }
        TargetClass.MemberInnerTarget targetInner = createInnerInstance();

        // Instance feature: default modifier, method_feature [1, target, instance, superTypeReference.methodName()], pos=instance code blocks, return void
        {
            default void instanceLogic() {
                Runnable superTypeRef = (Runnable) targetField; // superTypeReference (TargetClass implements Runnable)
                superTypeRef.run(); // superTypeReference.methodName(arguments)
                int num = 1; // method_feature "1"
                targetInner.innerMethod(); // method_feature "target", "instance"
            }
            instanceLogic();
        }

        // Enhanced for statement feature
        for (String arg : args) { // enhganced for statement (typo as per input)
            localVar += arg;
        }

        // Super constructor invocation feature
        class SuperConstructorClass extends SourceClass {
            SuperConstructorClass() {
                super(); // super constructor invocation
            }
        }
        new SuperConstructorClass();

        // Synchronized statement feature
        synchronized (targetInner) {
            localVar = "synchronizedValue";
        }

        // Try statement feature
        try {
            if (args == null) throw new IOException(); // IOException feature
        } catch (IOException e) {
            // no_new_exception feature (no custom exceptions instantiated)
            localVar = "error";
        }

        // Used_by_reflection feature
        try {
            Method method = this.getClass().getDeclaredMethod("moveableVarargsMethod", String[].class);
            method.setAccessible(true);
            method.invoke(this, (Object) args);
        } catch (Exception e) {
            // no_new_exception feature
            return targetInner;
        }

        // no_new_exception feature (no custom exceptions thrown)
        return targetInner;
    }

    // Local inner class (source feature)
    void methodWithLocalInner() {
        class LocalInnerSource {
            String localField = "localValue";
        }
        LocalInnerSource localInner = new LocalInnerSource();
    }
}

// Target class: normal class, public modifier, target_feature: implements, member inner class
public class TargetClass implements Runnable { // implements feature
    String targetField = "targetValue";

    // Member inner class (target_inner - method's target class)
    class MemberInnerTarget {
        String innerField = "innerValue";

        void innerMethod() {}
    }

    @Override
    public void run() {} // implements Runnable
}