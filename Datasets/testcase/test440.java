package com.refactoring.movemethod;

// Source class: interface (type), no modifier, same package as target
// Features: local inner class, member inner class
interface SourceInterface {
    // Member inner class (source_class feature)
    class MemberInnerClass {
        String outerField = "outerFieldValue";
    }

    // Instance method (method type: instance), return base type (int), public access, position: source
    // Per_condition: contains parameter of the target (TargetInterface param)
    public int moveCandidateMethod(TargetInterface param) {
        // Variable call (method feature)
        MemberInnerClass innerObj = new MemberInnerClass();
        String varCall = innerObj.outerField;
        
        // Uses outer this (method feature)
        SourceInterface outerThis = this;
        
        // Expression statement (method feature)
        int result = 0;
        
        // For statement (method feature)
        for (int i = 0; i < 3; i++) {
            result += i;
        }
        
        // While statement (method feature)
        int count = 3;
        while (count > 0) {
            result -= 1;
            count--;
        }
        
        // LabeledStatement (type), protected modifier, pos: same_package_target
        // target_feature: ClassName.field, 1
        labeledBlock: {
            TargetInterface.TargetFieldClass.field = 1;
            if (result < 0) break labeledBlock;
            result += TargetInterface.TargetFieldClass.field;
        }
        
        // Default modifier, numbers:3, exp: ArrayInitializer (method feature)
        default int[] arrayInit() {
            return new int[]{1, 2, 3};
        }
        int[] arr = arrayInit();
        result += arr[0];
        
        // No new exception (method feature - no throw new Exception)
        try {
            param.defaultMethod();
        } catch (Exception e) {
            result = -1;
        }
        
        return result;
    }

    // Local inner class (source_class feature)
    default void localInnerClassHolder() {
        class LocalInnerClass {
            void callSourceMethod(TargetInterface param) {
                SourceInterface.this.moveCandidateMethod(param);
            }
        }
        new LocalInnerClass().callSourceMethod(new TargetInterface() {});
    }
}

// Target class: interface (type), abstract modifier, target_feature: empty
abstract interface TargetInterface {
    // Field class for LabeledStatement feature (ClassName.field)
    class TargetFieldClass {
        static int field;
    }

    // call_method: type=target, modifier=default, target_feature: static, this.methodName(arguments)
    // pos: instance code blocks, return_type: void
    default void defaultMethod() {
        // Instance code block (pos)
        {
            staticMethod();
            this.defaultMethodHelper();
        }
    }

    // Static feature for call_method
    static void staticMethod() {}

    // Helper for this.methodName(arguments)
    default void defaultMethodHelper() {}
}