package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for call_method pos=the attribute values of annotations
@Retention(RetentionPolicy.RUNTIME)
@interface CallMethodAnnotation {
    String value() default "this.subMethod(\"callArg\")"; // target_feature: this.methodName(arguments)
}

// Sub class of TargetClass's inner class (sub_class type for call_method)
class TargetInnerSubClass<T> extends TargetClass<T>.InnerTargetClass {
    public TargetInnerSubClass(TargetClass<T> outerInstance) {
        outerInstance.super();
    }

    // Protected method for call_method (normal feature)
    protected String subMethod(String arg) {
        return arg + "_subClassResult";
    }
}

// Source class: generic class, public modifier, same package, anonymous inner class, local inner class
public class SourceClass<T> {
    // Static field for depends_on_static_field feature
    private static String staticSourceField = "staticInitVal";
    // per_condition: source contains target class field
    private TargetClass<T> targetField = new TargetClass<>();

    // Member inner class (method_position: source_inner)
    public class InnerSourceClass {
        // Method: instance, return Object, public access, source_inner position
        @CallMethodAnnotation // call_method pos=the attribute values of annotations
        public Object moveableInstanceMethod(TargetClass<T>.InnerTargetClass targetParam) { // per_condition: contains target_inner parameter
            // Variable call feature
            String localVar = targetParam.innerField;
            localVar = staticSourceField; // Access static field

            // depends_on_static_field feature
            staticSourceField = localVar + "_updatedStatic"; // Modify static field
            targetParam.innerField = staticSourceField; // Use static field to update target

            // Continue statement feature
            for (int i = 0; i < 5; i++) {
                if (i == 2) {
                    continue; // Continue statement
                }
                localVar += i;
            }

            // Call_method: sub_class, protected modifier, normal, this.methodName(arguments), pos=annotations, return String
            protected String callMethodLogic() {
                TargetInnerSubClass<T> subInstance = new TargetInnerSubClass<>(targetField);
                return subInstance.this.subMethod(localVar); // this.methodName(arguments) + normal feature
            }
            String subClassResult = callMethodLogic();
            localVar = subClassResult;

            // Anonymous inner class (source feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    localVar += "_anonymous";
                }
            };
            anonymousRunnable.run();

            // Local inner class (source feature)
            class LocalInnerSource {
                void updateTarget(TargetClass<T>.InnerTargetClass target) {
                    target.innerField = localVar;
                }
            }
            new LocalInnerSource().updateTarget(targetParam);

            // no_new_exception feature (no custom exceptions instantiated/thrown)
            return localVar;
        }
    }
}

// Target class: generic class, private modifier, same package, target_feature: type parameter, static nested class
private class TargetClass<U> {
    private U targetField;

    // Inner target class (target_inner - method's target class)
    class InnerTargetClass {
        String innerField = "innerTargetVal";
    }

    // Static nested class (target_feature)
    public static class StaticNestedTarget<V> { // type parameter feature for static nested class
        V nestedField;
    }

    // Type parameter usage in target class (target_feature: type parameter)
    public void setTargetField(U targetField) {
        this.targetField = targetField;
    }

    public U getTargetField() {
        return targetField;
    }
}