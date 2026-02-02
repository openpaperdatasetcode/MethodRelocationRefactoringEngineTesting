package com.refactor;

// Source class: normal, default modifier, same package as target, two static nested classes
class SourceClass {
    // Protected field for access_outer_protected feature
    protected static String outerProtectedField = "protectedValue";

    // First static nested class (source feature)
    static class SourceStaticNested1 {}
    // Second static nested class (source feature)
    static class SourceStaticNested2 {}

    // Abstract class for abstract method feature
    static abstract class AbstractHelperClass {
        // Abstract method (synchronized modifier, 1, others_class, abstract, super.methodName())
        public abstract synchronized TargetClass abstractMethod(int param);

        @Override
        public String toString() {
            return super.toString(); // super.methodName() usage
        }
    }

    // Method to refactor: static, Object return, public access, contains target parameter (per_condition)
    public static Object methodToMove(TargetClass... targetParams) {
        // SuperConstructorInvocation (protected modifier, obj.field, 2, pos: source)
        protected class SuperConstructorClass extends AbstractHelperClass {
            private int field = 2;
            public SuperConstructorClass() {
                super(); // Super constructor invocation
                this.field = 2; // obj.field = 2
            }
        }
        SuperConstructorClass superInstance = new SuperConstructorClass();

        // Abstract method feature (in while loop)
        int count = 1;
        while (count > 0) {
            TargetClass abstractResult = superInstance.abstractMethod(1);
            count--;
        }

        // Type declaration statement
        class LocalTypeDeclaration {}
        LocalTypeDeclaration localType = new LocalTypeDeclaration();

        // Variable call (target parameter access)
        Object targetVar = null;
        if (targetParams.length > 0) {
            targetVar = targetParams[0].staticNestedField;
        }

        // Access outer protected field
        String protectedAccess = SourceClass.outerProtectedField;

        // Call method: source type, public modifier, varargs + ClassName.methodName(arguments) in switch
        int switchVar = 1;
        switch (switchVar) {
            case 1:
                SourceClass.callMethod("arg1", "arg2");
                break;
            default:
                break;
        }

        // Return statement
        return new Object[]{targetVar, protectedAccess, superInstance};
    }

    // Call method: source type, public modifier, varargs, ClassName.methodName(arguments)
    public static void callMethod(String... args) {
        // Varargs feature implementation
    }
}

// Target class: normal, private modifier, static nested class (target_feature)
private class TargetClass {
    // Target field (used in variable call)
    public String staticNestedField = "targetValue";

    // Static nested class (target_feature)
    static class TargetStaticNested {}
}