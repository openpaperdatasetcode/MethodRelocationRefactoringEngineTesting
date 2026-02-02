package com.refactoring.movemethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

protected class SourceClass {
    // Static nested class
    static class StaticNestedClass {}

    // Anonymous inner class
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetClass.TargetInnerRec rec = new TargetClass.TargetInnerRec();
            SourceClass.this.varargsMethod(rec);
        }
    };

    protected int varargsMethod(TargetClass.TargetInnerRec... params) {
        int result = 0;
        // Instance code blocks for nested instance method
        {
            publicInstanceMethod(params);
        }

        for (TargetClass.TargetInnerRec param : params) {
            // Constructor invocation
            TargetClass.TargetInnerRec newRec = new TargetClass.TargetInnerRec();
            // Variable call
            result = param.getValue();
            // Access instance field
            param.instanceField = result + 3;

            // Used by reflection
            try {
                Class<?> clazz = TargetClass.TargetInnerRec.class;
                Constructor<?> constructor = clazz.getConstructor();
                Object obj = constructor.newInstance();
                Method method = clazz.getMethod("getValue");
                result = (int) method.invoke(obj);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                // No new exception thrown
                result = -1;
            }
        }
        return result;
    }

    // Public instance method with required features
    public void publicInstanceMethod(TargetClass.TargetInnerRec... params) {
        for (TargetClass.TargetInnerRec param : params) {
            // super.methodName(arguments) (via anonymous subclass of TargetInnerRec)
            TargetClass.TargetInnerRec subRec = new TargetClass.TargetInnerRec() {
                @Override
                public int getValue() {
                    return super.getValue() + 3;
                }
            };
            // 3, others_class, instance features
            subRec.instanceField = 3;
        }
    }
}

private class TargetClass {
    // Anonymous inner class in target class
    Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetInnerRec rec = new TargetInnerRec();
            rec.setValue(0);
        }
    };

    class TargetInnerRec {
        // Instance field for access
        int instanceField;
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}