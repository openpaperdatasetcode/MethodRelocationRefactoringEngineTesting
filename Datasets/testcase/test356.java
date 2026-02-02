package com.refactoring.test;

/**
 * Target record class with javadoc and local inner class
 */
public record TargetClass(String targetField, int targetInt) {

    /**
     * Instance method for access in moved method
     */
    protected String instanceMethod(String input) {
        return input + "_processed";
    }

    // Local inner class as required target feature
    class LocalInnerClass {
        String innerMethod() {
            return targetField;
        }
    }
}

/**
 * Source record class with static nested and anonymous inner classes
 */
protected record SourceClass(TargetClass targetInstance) {

    // Static nested class as required source feature
    protected static class StaticNestedClass {
        static String staticNestedMethod() {
            return "static_nested";
        }
    }

    // Method to be moved (varargs, default access, returns List<String>)
    List<String> moveableMethod(String... varargs) {
        // VariableDeclarationStatement (private modifier, accesses target class field)
        private int targetFieldAccess = targetInstance.targetInt();
        private String nestedValue = StaticNestedClass.staticNestedMethod();

        // Anonymous inner class as required source feature
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetInstance.targetField());
            }
        };
        anonymousInner.run();

        // Instance code block with private instance features
        {
            SubClass subInstance = new SubClass();
            subInstance.subClassMethod(targetInstance.instanceMethod("test"));
        }

        // Variable call, access outer protected, access instance method
        String outerProtectedAccess = this.targetInstance().instanceMethod(varargs[0]);
        String variableCall = nestedValue + outerProtectedAccess;

        return List.of(variableCall, String.valueOf(targetFieldAccess));
    }

    // Sub class for method feature requirement
    private class SubClass {
        void subClassMethod(String arg) {
            // ClassName.methodName(arguments) call
            SourceClass.StaticNestedClass.staticNestedMethod();
        }
    }
}