package com.refactoring.movemethod;

import java.util.Arrays;
import java.util.List;

// Functional interface for source class implementation
interface Operation {
    void execute();
}

// Source normal class (public modifier, implements interface, same package)
public class SourceClass implements Operation {
    // Target class field to satisfy pre-condition
    TargetClass targetField = new TargetClass();

    // Static nested class (duplicated as per feature requirement)
    public static class StaticNestedClass1 {
        static int staticField1 = 2;
    }

    // Static nested class (duplicated as per feature requirement)
    private static class StaticNestedClass2 {
        static String staticField2 = "nested2";
    }

    // Inner class containing the overloading methods (method_position: source_inner)
    public class SourceInnerClass {
        // First overloaded method (public, void return type)
        public void moveableMethod() {
            // TypeDeclarationStatement (private modifier, inner class pos, obj.field + 2 features)
            private class TypeDeclClass {
                int field = targetField.anonymousField + 2;
            }
            TypeDeclClass typeDeclObj = new TypeDeclClass();

            // Enhanced for statement
            List<String> items = Arrays.asList("a", "b", "c");
            for (String item : items) {
                System.out.println(item);
            }

            // Super constructor invocation
            SuperClass superObj = new SuperClass(2) {};

            // Switch case statement
            int switchVar = typeDeclObj.field;
            switch (switchVar) {
                case 2:
                    System.out.println("Case 2");
                    break;
                default:
                    System.out.println("Default case");
            }

            // Variable call feature
            String varCall = StaticNestedClass2.staticField2 + typeDeclObj.field;
            System.out.println(varCall);
        }

        // Second overloaded method (overloading feature, public, void return type)
        public void moveableMethod(String param) {
            // TypeDeclarationStatement (private modifier, inner class pos, obj.field + 2 features)
            private class AnotherTypeDeclClass {
                String field = targetField.anonymousField + "_" + 2;
            }
            AnotherTypeDeclClass anotherTypeDeclObj = new AnotherTypeDeclClass();

            // Enhanced for statement
            int[] nums = {1, 2, 3};
            for (int num : nums) {
                System.out.println(num + anotherTypeDeclObj.field);
            }

            // Super constructor invocation
            SuperClass superObj = new SuperClass(Integer.parseInt(param)) {};

            // Switch case statement
            String switchStr = anotherTypeDeclObj.field;
            switch (switchStr) {
                case "anon_2":
                    System.out.println("Case anon_2");
                    break;
                default:
                    System.out.println("Default string case");
            }

            // Variable call feature
            String varCall = param + StaticNestedClass1.staticField1;
            System.out.println(varCall);
        }

        // Super class for super constructor invocation
        private class SuperClass {
            public SuperClass(int num) {
                // Constructor body
            }
        }

        @Override
        public void execute() {
            // Implementation of Operation interface
        }
    }
}

// Target normal class (private modifier, same package)
private class TargetClass {
    String anonymousField = "anon";

    // Anonymous inner class as target feature
    {
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                anonymousField = "anonymous_updated";
            }
        };
        anonymousInner.run();
    }
}