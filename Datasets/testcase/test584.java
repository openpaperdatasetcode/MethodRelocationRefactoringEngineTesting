package refactoring.test;

import diffpkg.OuterClass;

// Source class: abstract generic, same package as target, local + anonymous inner classes
abstract class SourceClass<T> {
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>();

    // Method to be refactored: public instance, base type return, source position
    public int refactorMethod() {
        // Type declaration statement
        TargetClass rawTarget; // Raw type feature
        TargetClass<String> typedTarget;
        
        // Constructor invocation
        typedTarget = new TargetClass<>("initial");
        rawTarget = new TargetClass(); // Raw type usage

        // Variable call
        targetField.value = "refactor";
        // Access instance field
        int instanceFieldVal = targetField.counter;

        // Local inner class (source feature)
        class LocalInnerClass extends OuterClass {
            // WhileStatement: private modifier, diff_package_others pos
            private void whileLogic() {
                int i = 1; // 1 (numeric feature)
                while (i < 5) {
                    // super.field (WhileStatement target_feature)
                    super.outerField = i++;
                }
            }
        }

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                new LocalInnerClass().whileLogic();
            }
        };
        anonymousRunnable.run();

        // Override violation (invalid override attempt)
        class InvalidOverride extends TargetClass<String> {
            @Override
            public final void targetMethod() { // Compile error: final method override
                // No new exception
            }
        }

        // For statement with call_method (target, synchronized, instance, obj.m1().m2().m3())
        for (int j = 0; j < 3; j++) {
            Object callResult = targetField.m1().m2().m3();
        }

        // No new exception, return base type (int)
        return targetField.counter;
    }
}

// Target class: generic, default modifier, member inner class feature
class TargetClass<T> {
    T value;
    int counter = 1;

    // Member inner class (target_feature)
    class TargetInnerClass {
        int innerCounter;
    }

    public TargetClass() {}

    public TargetClass(T value) {
        this.value = value;
    }

    public final void targetMethod() {}

    // Synchronized instance method (call_method: target, synchronized, instance)
    public synchronized TargetClass<T> m1() {
        return this;
    }

    public TargetClass<T> m2() {
        return this;
    }

    public Object m3() {
        TargetInnerClass inner = new TargetInnerClass();
        inner.innerCounter = counter;
        return inner;
    }
}

// Different package class (diff_package_others for WhileStatement pos)
package diffpkg;

public class OuterClass {
    protected int outerField;
}