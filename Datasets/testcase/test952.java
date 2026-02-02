package com.refactoring.movemethod;

protected class SourceClass {
    // Static nested class (source feature)
    static class SourceStaticNested {}

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetClass target = new TargetClass();
            TargetClass.TargetInnerRec rec = target.new TargetInnerRec();
            processTarget(rec);
        }
    };

    private int localVar; // Field for this.var = var feature

    /**
     * Instance method to test Move Method refactoring
     * @param param Target inner record parameter (per_condition)
     * @return Base type (int) result
     */
    int processTarget(TargetClass.TargetInnerRec param) {
        // Variable call (per_condition: contains target parameter)
        int targetFieldVal = param.instanceField;
        
        // Type declaration statement
        String typeDecl = "processed_" + targetFieldVal;
        
        // this.var = var feature
        this.localVar = targetFieldVal;
        
        // Access instance field of target
        param.instanceField = this.localVar + 1;
        
        // Super constructor invocation (via anonymous subclass)
        SuperClass superObj = new SuperClass() {};
        
        // If/else conditions (pos for accessor method)
        if (targetFieldVal > 0) {
            publicAccessorMethod(param, 1);
        } else {
            publicAccessorMethod(param, 0);
        }

        // No new exception (empty try-catch)
        try {
            // No exception thrown
        } catch (Exception e) {
            targetFieldVal = -1;
        }

        return targetFieldVal;
    }

    /**
     * Public accessor method with required features
     * @param rec Target inner record instance
     * @param val Literal value 1
     */
    public void publicAccessorMethod(TargetClass.TargetInnerRec rec, int val) {
        // 1: literal value usage
        if (val == 1) {
            // instanceReference.methodName(arguments)
            rec.setInstanceField(rec.getInstanceField() + 1);
        }
    }
}

public class TargetClass implements TestInterface {
    // Anonymous inner class (target feature)
    Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetInnerRec rec = new TargetInnerRec();
            rec.setInstanceField(0);
        }
    };

    // Target inner class (target_inner_rec)
    class TargetInnerRec {
        // Instance field for access
        int instanceField;

        // Accessor methods
        public int getInstanceField() {
            return instanceField;
        }

        public void setInstanceField(int instanceField) {
            this.instanceField = instanceField;
        }
    }
}

// Supporting interface for target_class implements feature
interface TestInterface {}

// Super class for super constructor invocation
class SuperClass {
    public SuperClass() {}
}