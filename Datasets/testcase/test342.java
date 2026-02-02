package com.refactoring.movemethod;

import com.refactoring.others.OtherPackageClass;

// Protected normal source class (two static nested classes; same package as target)
protected class SourceClass extends ParentClass {
    // Source contains target inner class field (satisfies per_condition)
    private TargetClass.TargetMemberInner targetInnerField = new TargetClass().new TargetMemberInner();
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "protected_value";

    // First static nested class (source feature)
    public static class SourceStaticNested1 {}
    // Second static nested class (source feature)
    public static class SourceStaticNested2 {}

    // Varargs method (protected access, base type return, target inner param)
    protected int varargsMethod(TargetClass.TargetMemberInner targetInnerParam, String... args) {
        // Type declaration statement
        int localVar;
        String strVar;
        OtherPackageClass otherInstance;

        // Variable call
        localVar = 0;
        strVar = outerProtectedField;

        // Access outer protected field
        String protectedVal = SourceClass.this.outerProtectedField;

        // Constructor invocation
        SourceStaticNested1 staticNested = new SourceStaticNested1();

        // ExpressionStatement (public modifier, obj.field + 2, diff_package_others pos)
        public: {
            otherInstance = new OtherPackageClass();
            localVar = otherInstance.otherField + targetInnerParam.innerField + 2; // obj.field + 2 (diff_package_others)
        }

        // Access target instance method
        localVar += targetInnerParam.innerMethod();

        // Requires try-catch block
        try {
            if (targetInnerParam == null) {
                throw new NullPointerException("Target inner parameter is null");
            }
            // Object initialization with call_method (parent_class, private, instance, instanceReference::methodName)
            ParentClass parentInstance = new ParentClass() {
                // Instance reference method reference in object initialization
                private final java.util.function.IntSupplier supplier = this::privateInstanceMethod;
            };
            localVar += parentInstance.privateInstanceMethod();
        } catch (NullPointerException e) {
            localVar = -1;
        }

        // Process varargs
        for (String arg : args) {
            localVar += arg.length();
        }

        return localVar; // Base type return (int)
    }
}

// Parent class for call_method (parent_class type)
class ParentClass {
    // Private instance method (call_method: private, instance, int return)
    private int privateInstanceMethod() {
        return 5;
    }
}

// Default modifier target class (member inner class feature)
class TargetClass {
    // Member inner class (target_inner)
    public class TargetMemberInner {
        // Instance field for obj.field feature
        public int innerField = 2;

        // Instance method for access_instance_method feature
        public int innerMethod() {
            return innerField * 2;
        }
    }
}

// Different package class for ExpressionStatement pos (diff_package_others)
package com.refactoring.others;

public class OtherPackageClass {
    public int otherField = 10;
}