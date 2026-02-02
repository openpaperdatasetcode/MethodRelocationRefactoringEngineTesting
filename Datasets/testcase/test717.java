package com.refactoring.movemethod;

// Permitted classes for source class permits feature
final class PermittedClass1 extends SourceClass {}
final class PermittedClass2 extends SourceClass {}

// Source class: public, permits, two member inner classes, same package as target
public sealed class SourceClass permits PermittedClass1, PermittedClass2 {
    // First member inner class (source class feature)
    class FirstInnerClass {
        int innerField1 = 10;
    }

    // Second member inner class (source class feature)
    class SecondInnerClass {
        int innerField2 = 20;
    }

    // Super class for super constructor/keywords usage
    static class SuperSourceClass {
        protected int superField = 30;

        public SuperSourceClass() {}
    }

    // Inherit super class to enable super constructor/keywords
    private final SuperSourceClass superInstance = new SuperSourceClass();

    // Method to refactor: instance, base type return, protected, in source class
    // Per_condition: contains target class parameter
    protected int methodToRefactor(TargetClass targetParam) {
        // Super constructor invocation
        SuperSourceClass superObj = new SuperSourceClass();
        
        // Super keywords (access super class member)
        int value = superObj.superField;
        
        // Variable call (target parameter's inner class field)
        value += targetParam.TargetInnerClass.getInnerValue();
        
        // Expression statement
        value += new FirstInnerClass().innerField1 + new SecondInnerClass().innerField2;
        
        // No new exception feature
        try {
            if (value < 0) throw new ArithmeticException();
        } catch (ArithmeticException e) {
            // No throw new exception, only handle
            value = 0;
        }
        
        return value; // Base type return (int)
    }

    // Overload_exist feature: overloaded version of the method to refactor
    protected int methodToRefactor(TargetClass targetParam, int extra) {
        return methodToRefactor(targetParam) + extra;
    }
}

// Target class: public, implements, member inner class, same package as source
public class TargetClass implements Runnable {
    // Member inner class (target class feature)
    public class TargetInnerClass {
        private static final int INNER_VALUE = 5;

        public static int getInnerValue() {
            return INNER_VALUE;
        }
    }

    // Implement Runnable (implements feature)
    @Override
    public void run() {
        new TargetInnerClass();
    }
}

// Sub class for call_method (type: sub_class)
class SourceSubClass extends SourceClass {
    // call_method: final modifier, overriding, instanceReference.methodName(), Lambda pos, void return
    @Override
    public final void callMethod() {
        // Lambda expressions position
        Runnable lambda = () -> {
            // InstanceReference.methodName(arguments) feature
            TargetClass targetInstance = new TargetClass();
            SourceClass sourceInstance = new PermittedClass1();
            sourceInstance.methodToRefactor(targetInstance);
        };
        lambda.run();
    }
}