// Source class package (different from target)
package com.source;

import com.target.TargetClass;
import java.util.function.Consumer;

public class SourceClass {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Protected field for access_outer_protected feature
    protected String outerProtectedValue = "protectedValue";
    // Instance field for access_instance_field feature
    private int instanceField = 100;
    // Value for lambda feature
    String value = "sourceLambdaValue";

    // Local inner class (source_class feature)
    class LocalInnerClass {
        public void innerMethod() {}
    }

    // Static nested class (source_class feature)
    static class StaticNestedClass {
        public static void staticMethod() {}
    }

    // Method to refactor: varargs, void return, private access, source position
    private void methodToRefactor(String... args) {
        // Variable call feature
        String localVar = "localVar";
        localVar = args.length > 0 ? args[0] : localVar;

        // Access outer protected field (access_outer_protected)
        localVar += SourceClass.this.outerProtectedValue;

        // Access instance field (access_instance_field)
        int fieldValue = SourceClass.this.instanceField;

        // Lambda: () -> System.out.println(this.value)
        Consumer<Void> lambda = () -> System.out.println(this.value);
        lambda.accept(null);

        // ConstructorInvocation: private modifier, ClassName.field, 3, pos: diff_package_others
        privateConstructorInvocation();

        // Overloading method call (parameter list of constructor)
        new OverloadConstructor(1, new LocalInnerClass()).callOverload();

        // No new exception feature (no 'new Exception()' statements)
    }

    // ConstructorInvocation implementation (diff_package_others position)
    private void privateConstructorInvocation() {
        TargetClass.TargetInnerClass.innerStaticField = 3; // ClassName.field, 3
    }

    // Overloading method (private modifier, base type return, overloading feature)
    private int overloadingMethod(int num, LocalInnerClass inner) { // 1, inner_class, overloading
        super.toString(); // super.methodName()
        return num * fieldValue;
    }

    // Overload constructor (parameter list position for overloading)
    class OverloadConstructor {
        public OverloadConstructor(int num, LocalInnerClass inner) {
            overloadingMethod(num, inner); // Call overloading method
        }

        public void callOverload() {
            overloadingMethod(1, new LocalInnerClass());
        }
    }

    // No new exception (no exception instantiation in entire method)
}
}

// Target class package (different from source)
package com.target;

import com.source.SourceClass;

public class TargetClass {
    // Member inner class (target_feature)
    public class MemberInnerClass {
        public void innerMethod() {}
    }

    // Target inner class for method relocation
    public static class target_inner {
        // Placeholder for moved method
        private void methodToRefactor(String... args) {
            SourceClass source = new SourceClass();
            source.methodToRefactor(args);
        }
    }

    // Inner class for ConstructorInvocation (ClassName.field)
    static class TargetInnerClass {
        static int innerStaticField; // Field for ClassName.field = 3
    }
}