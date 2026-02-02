// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for call_method pos=attribute values of annotations
@Retention(RetentionPolicy.RUNTIME)
@interface CallMethodAnnotation {
    String value() default "TargetClass.overloadedMethod(1)"; // ClassName.methodName(arguments)
}

// Super class for source_class extends feature
class SuperSourceClass {
    protected void superVarargsMethod(String... args) {}
}

// Functional interface for source_class implements feature
interface SourceInterface {}

// Source class: generic class, private modifier, different package with target, extends, implements, member inner, local inner
private class SourceClass<T extends Number> extends SuperSourceClass implements SourceInterface {
    // Member inner recursive class (source_inner_rec - method position)
    class InnerRecursiveSource<U> {
        // Method: varargs, return TargetClass Type, public access, source_inner_rec position
        // per_condition: contains target class parameter (TargetClass<V>.InnerRecursiveTarget)
        @CallMethodAnnotation // call_method pos=annotation attribute values
        public <V> TargetClass<V>.InnerRecursiveTarget moveableVarargsMethod(TargetClass<V>.InnerRecursiveTarget targetParam, String... args) {
            // Variable call feature
            String localVar = targetParam.innerField;
            localVar = args.length > 0 ? args[0] : "default";

            // Varargs feature: synchronized modifier, method_feature [1, sub_class, varargs, super.methodName()], pos=exception throwing, return void
            synchronized void varargsLogic(String... varargs) {
                if (varargs == null) {
                    throw new IllegalArgumentException(); // exception throwing statements pos
                }
                super.superVarargsMethod(varargs); // super.methodName()
                int num = 1; // method_feature "1"
                SubTargetClass<V> subInstance = new SubTargetClass<>(); // method_feature "sub_class"
                subInstance.varargsHelper(varargs); // method_feature "varargs"
            }
            varargsLogic(args);

            // Switch statement feature
            switch (localVar.length()) {
                case 1:
                    localVar = "case1";
                    break;
                default:
                    localVar = "default";
            }

            // Try statement feature
            try {
                int parsed = Integer.parseInt(localVar);
                targetParam.innerField = String.valueOf(parsed);
            } catch (NumberFormatException e) {
                // no_new_exception feature (no custom exceptions instantiated)
                targetParam.innerField = "0";
            }

            // Depends_on_inner_class feature
            LocalInnerHelper helper = new LocalInnerHelper();
            helper.updateField(targetParam, localVar);

            // no_new_exception feature (no custom exceptions thrown)
            return targetParam;
        }

        // Local inner class (source feature)
        class LocalInnerHelper {
            void updateField(TargetClass<V>.InnerRecursiveTarget target, String val) {
                target.innerField = val;
            }
        }
    }

    // Local inner class (source feature)
    void methodWithLocalInner() {
        class LocalInnerSource {
            String localField = "localSourceValue";
        }
        LocalInnerSource localInner = new LocalInnerSource();
    }
}

// Sub class of TargetClass (sub_class feature)
class SubTargetClass<W> extends TargetClass<W> {
    void varargsHelper(String... args) {}
}
// Target class package
package com.refactoring.target;

// Target class: generic class, default modifier, different package with source, target_feature: local inner class
class TargetClass<X> {
    // Inner recursive class (target_inner_rec - method's target class)
    class InnerRecursiveTarget {
        String innerField = "innerRecValue";

        // Recursive inner class usage
        void recursiveMethod() {
            new InnerRecursiveTarget().recursiveMethod();
        }
    }

    // Local inner class (target_feature)
    void methodWithLocalInner() {
        class LocalInnerTarget {
            X innerField;
            int value = 1;
        }
        LocalInnerTarget localInner = new LocalInnerTarget();
    }

    // Call_method: protected modifier, overloading, ClassName.methodName(arguments), return int
    protected static int overloadedMethod(int num) {
        return num;
    }

    // Overloading method (overloading feature)
    protected static int overloadedMethod(String str) {
        return str.length();
    }
}