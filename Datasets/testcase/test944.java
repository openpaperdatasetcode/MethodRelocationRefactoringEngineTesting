package com.refactoring.movemethod;

import java.util.List;

private class SourceClass<T extends Number & Comparable<T>> implements TestInterface {
    // Member inner class
    class SourceMemberInner {
        void innerMethod() {}
    }

    // Anonymous inner class
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            SourceClass.this.varargsMethod(new TargetClass<>().new TargetMemberInner());
        }
    };

    /**
     * Javadoc for varargs method
     * @param params target class inner instances
     */
    void varargsMethod(TargetClass<?>.TargetMemberInner... params) {
        // Labeled statement
        loopLabel:
        while (true) {
            // Type declaration statement
            String typeDecl = "test";
            // Variable call
            for (TargetClass<?>.TargetMemberInner param : params) {
                param.targetMethod();
                // Super constructor invocation (via anonymous subclass)
                SuperClass superObj = new SuperClass() {};
                // SuperFieldAccess with number 2 and public modifier
                int superField = superObj.publicSuperField + 2;
                
                // Normal method with public modifier, array initialization pos, 1/target/normal/instanceReference.methodName
                publicNormalMethod(new int[]{1}, param);
                
                // Uses outer this
                SourceClass.this.new SourceMemberInner().innerMethod();
                break loopLabel;
            }
        }
    }

    /**
     * Normal public method with required features
     * @param arr array with 1
     * @param target target instance
     */
    public void publicNormalMethod(int[] arr, TargetClass<?>.TargetMemberInner target) {
        // instanceReference.methodName(arguments)
        target.targetMethod(arr[0]);
    }
}

class TargetClass<T extends CharSequence> implements TestTargetInterface {
    // Member inner class
    class TargetMemberInner {
        void targetMethod() {}
        void targetMethod(int arg) {}
    }
}

// Supporting interfaces/classes
interface TestInterface {}
interface TestTargetInterface {}

class SuperClass {
    public int publicSuperField = 0;
    public SuperClass() {}
}