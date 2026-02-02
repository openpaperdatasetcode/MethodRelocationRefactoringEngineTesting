package com.refactoring.movemethod;

import java.util.function.Function;

/**
 * Target enum Javadoc (target_feature: javadoc)
 */
public enum TargetClass {
    INSTANCE_1(1), INSTANCE_2(2);

    private final int value;

    TargetClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Object processInstance() {
        return this.name() + "_processed_" + value;
    }
}

// Others class for call_method
class OthersClass<T extends TargetClass> {
    // Private modifier, generic target_feature, return TargetClass type
    private T callMethod(T target) {
        // superTypeReference.methodName(arguments)
        Object superRef = target.getClass().getSuperclass();
        System.out.println(superRef.toString() + target.getValue());
        return target;
    }
}

// Source enum class (public, same package, local inner + anonymous inner class)
public enum SourceClass {
    SOURCE_INSTANCE;

    private int instanceField = 1; // access_instance_field feature

    // Instance method to refactor (private, return TargetClass, source position)
    private TargetClass refactorMethod(TargetClass targetParam) throws Exception { // requires_throws
        // Variable call feature
        int localVar = this.instanceField;

        // Synchronized statement feature
        Object lock = new Object();
        synchronized (lock) {
            localVar += targetParam.getValue();
        }

        // While statement feature
        int count = 0;
        while (count < localVar) {
            count++;
        }

        // Local inner class (source class feature)
        class LocalInnerClass {
            int processValue(int val) {
                return val * 2;
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        localVar = localInner.processValue(localVar); // depends_on_inner_class

        // Anonymous inner class (source class feature)
        Runnable anonRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetParam.name());
            }
        };
        anonRunnable.run();

        // Instance method in switch (pos: switch, method_feature: 1/target/instance/instanceReference::methodName)
        TargetClass result;
        switch (targetParam) {
            case INSTANCE_1:
                Function<TargetClass, Object> func = TargetClass::processInstance; // instanceReference::methodName
                func.apply(targetParam); // method_feature: 1
                result = TargetClass.INSTANCE_1;
                break;
            default:
                result = TargetClass.INSTANCE_2;
                break;
        }

        // Call method (others_class, private, pos: do-while, generic + superTypeReference.methodName)
        OthersClass<TargetClass> others = new OthersClass<>();
        int doCount = 0;
        do { // pos: do-while
            result = others.callMethod(result); // superTypeReference feature
            doCount++;
        } while (doCount < 1); // method_feature: 1

        // Access instance field
        this.instanceField = localVar;

        // Process target parameter (per_condition: method has target parameter)
        return result;
    }

    // Protected instance method (method_feature: protected/instance)
    protected Object instanceMethod(TargetClass target) { // return Object, method_feature
        return target.processInstance();
    }
}