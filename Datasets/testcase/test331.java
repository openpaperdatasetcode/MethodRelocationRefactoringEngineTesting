package com.refactoring.movemethod;

// Abstract normal source class (same package as target)
abstract class SourceClass extends BaseClass {
    // Field for this.field reference
    private int sourceField = 1;

    // Member inner class (source feature)
    public class MemberInner {}

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class");
        }
    };

    // Varargs method (private access, returns TargetClass type, contains target parameter)
    private TargetClass methodToMove(TargetClass targetParam, String... args) {
        // Type declaration statement
        int localVar;
        TargetClass nestedTarget;

        // Enhanced for statement
        for (String arg : args) {
            // Variable call
            localVar = arg.length();
        }

        // While statement
        int count = 0;
        while (count < 2) {
            count++;
        }

        // ParenthesizedExpression with number 2 and public modifier (logical grouping)
        int parenthesizedVal = (2 * (sourceField + 1));

        // This.methodName(arguments) call
        this.callMethod(targetParam);

        // ReturnStatement (public modifier, this.field + 1)
        return new TargetClass() {
            @Override
            public TargetClass publicReturnMethod() {
                return targetParam;
            }
        }.publicReturnMethod();
    }

    // Call method (source type, public modifier, returns TargetClass, in do-while)
    public TargetClass callMethod(TargetClass param) {
        do {
            // Lambda expression: (parameters) -> methodBody
            Runnable lambda = () -> {
                System.out.println("Lambda body: " + param.toString());
            };
            lambda.run();
        } while (false);

        // No new exception (no exception instantiation)
        return param;
    }

    // Public return method for ReturnStatement feature
    public TargetClass publicReturnMethod() {
        return new TargetClass();
    }
}

// Base class for SourceClass extension
class BaseClass {}

// Abstract normal target class
abstract class TargetClass {
    // Static nested class (target feature)
    public static class StaticNested {}

    // Public method for ReturnStatement reference
    public TargetClass publicReturnMethod() {
        return this;
    }
}