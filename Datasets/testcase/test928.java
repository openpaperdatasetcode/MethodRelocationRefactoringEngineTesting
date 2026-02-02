// Different package for VariableDeclarationStatement pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetClass;

public class DiffPackageOthers {
    // VariableDeclarationStatement (private modifier, super.field, 1, pos=diff_package_others)
    public static <T> void varDeclStatement(TargetClass<T>.TargetInnerRec target) {
        private int superFieldVal = 1; // 1 from target_feature
        target.superField = superFieldVal; // super.field access
    }
}

// Main package
package com.refactoring.test;

import com.refactoring.others.DiffPackageOthers;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for call_method pos=the attribute values of annotations
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {
    String value() default "";
}

// Parent class for super.field feature
class TargetParentClass {
    protected int superField;
}

// Target generic class (default modifier, anonymous inner class feature)
class TargetClass<T> extends TargetParentClass {
    T targetField; // For per_condition (source contains this field)
    static int staticField = 1; // For depends_on_static_field

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        T innerField;
        TargetInnerRec nestedInner; // Recursive inner class feature

        // Instance method for access_instance_method
        public T getInnerField() {
            return this.innerField;
        }

        // Overload exist methods
        public void overloadMethod() {}
        public void overloadMethod(T value) { // overload_exist feature
            this.innerField = value;
        }

        // Method chain for call_method obj.m1().m2().m3()
        public TargetInnerRec m1() { return this; }
        public TargetInnerRec m2() { return this; }
        public String m3() { return this.innerField.toString(); }
    }

    // Anonymous inner class (target_feature)
    Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous: " + targetField);
        }
    };
}

// Source generic class (protected modifier, same package, permits + static nested + anonymous inner class)
protected sealed class SourceClass<T extends CharSequence> permits SourceSubClass { // permits feature
    protected T outerProtectedField; // For access_outer_protected

    // Static nested class (source_feature)
    static class SourceStaticNested<U> {
        static <V> void helperMethod(TargetClass<V>.TargetInnerRec inner) {
            inner.overloadMethod();
        }
    }

    // Inner recursive class (source_inner_rec)
    class SourceInnerRecursive<U> {
        // call_method (public modifier, instance + obj.m1().m2().m3(), pos:annotation attribute, return String)
        @RefactorAnnotation(value = "#{targetInner.m1().m2().m3()}") // pos:annotation attribute values
        public <V> String callMethod(TargetClass<V>.TargetInnerRec targetInner) {
            return targetInner.m1().m2().m3(); // obj.m1().m2().m3()
        }

        // Method to be refactored (instance, Object return, protected access, source_inner_rec)
        protected <V> Object moveMethod(TargetClass<V>.TargetInnerRec targetParam) {
            // Per_condition: source contains the field of the target (access targetParam.innerField)
            if (targetParam == null) {
                return null;
            }

            // VariableDeclarationStatement (pos=diff_package_others)
            DiffPackageOthers.varDeclStatement(targetParam);

            // Empty statement feature
            ;

            // Access outer protected field
            SourceClass.this.outerProtectedField = (T) targetParam.getInnerField(); // access_outer_protected

            // Variable call
            V varCall = targetParam.getInnerField(); // Access target field (per_condition)
            targetParam.overloadMethod(varCall); // overload_exist feature

            // Access instance method
            V instanceMethodVal = targetParam.getInnerField();

            // Depends on static field
            targetParam.innerField = (V) (instanceMethodVal + "_" + TargetClass.staticField);

            // Anonymous inner class (source_feature)
            Runnable sourceAnonymous = new Runnable() {
                @Override
                public void run() {
                    SourceStaticNested.helperMethod(targetParam);
                }
            };
            sourceAnonymous.run();

            // call_method invocation (annotation attribute pos)
            String callResult = callMethod(targetParam);

            // No new exception
            return new Object[] {varCall, callResult, TargetClass.staticField};
        }
    }

    // Abstract method (required for sealed class implementation)
    public abstract void abstractMethod();
}

// Permits subclass for SourceClass
final class SourceSubClass<T extends CharSequence> extends SourceClass<T> {
    @Override
    public void abstractMethod() {}
}