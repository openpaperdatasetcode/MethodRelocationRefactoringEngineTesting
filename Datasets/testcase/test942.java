package com.refactoring.test;

protected class SourceClass extends BaseClass permits DerivedClass1, DerivedClass2 {
    private static final int CONSTANT = 10;
    static class StaticNestedClass1 {}
    static class StaticNestedClass2 {}

    private TargetClass targetInstance = new TargetClass();

    @Override
    public final void moveCandidateMethod(TargetClass.TargetInnerRec param) {
        try {
            // Constructor invocation
            TargetClass.TargetInnerRec innerRec = new TargetClass.TargetInnerRec();
            // Enhanced for statement
            for (int num : new int[]{1, 2, 3}) {
                // Variable call
                int value = param.getValue();
                // Access instance method
                targetInstance.instanceMethod(value);
            }
            // Used by reflection
            Class<?> clazz = Class.forName("com.refactoring.test.TargetClass$TargetInnerRec");
            Object obj = clazz.getConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 java.lang.NoSuchMethodException | java.lang.reflect.InvocationTargetException e) {
            // Requires try-catch
            e.printStackTrace();
        }
    }

    private <T extends Number> int callMethod() {
        // outerInstance.new InnerClass().methodName()
        TargetClass outerInstance = new TargetClass();
        TargetClass.LocalInnerClass innerClass = outerInstance.new LocalInnerClass();
        return innerClass.innerMethod();
    }
}

private class TargetClass implements TestInterface {
    class LocalInnerClass {
        int innerMethod() {
            return 0;
        }
    }

    class TargetInnerRec {
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    void instanceMethod(int val) {
        // Instance method for access
    }
}

// Supporting classes/interfaces for structure compliance
class BaseClass {
    public void moveCandidateMethod(TargetClass.TargetInnerRec param) {}
}

interface TestInterface {}

sealed class DerivedClass1 extends SourceClass permits DerivedClass2 {}
final class DerivedClass2 extends SourceClass {}