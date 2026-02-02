package com.refactoring.movemethod;

import java.util.function.Consumer;

// Different package for SynchronizedStatement pos (diff_package_target)
package com.refactoring.target.other;
public class SyncHelper {
    public transient int syncField = 1; // target_feature: 1, this.field (transient modifier)
    public synchronized void syncLogic() {
        synchronized (this) { // SynchronizedStatement (diff_package_target pos)
            this.syncField++;
        }
    }
}

// Back to main package
package com.refactoring.movemethod;
import com.refactoring.target.other.SyncHelper;

// Source normal class (public, same package, implements + static nested + anonymous inner)
public class SourceClass implements Runnable {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Static nested class (source class feature)
    public static class StaticNestedClass {
        private int nestedValue = 10;

        public int getNestedValue() {
            return nestedValue;
        }
    }

    // Instance method to refactor (protected, void return, source position)
    protected void refactorMethod(TargetClass.TargetInnerRec targetParam) {
        // Variable call feature
        StaticNestedClass staticNested = new StaticNestedClass();
        int localVar = staticNested.getNestedValue(); // depends_on_inner_class (static nested)

        // Super keywords feature (via Runnable super interface)
        super.run();

        // Lambda: () -> System.out.println(this.value)
        Runnable lambda = () -> System.out.println(this.targetField.getValue());

        // Super constructor invocation (anonymous inner class)
        Consumer<TargetClass.TargetInnerRec> anonInner = new Consumer<TargetClass.TargetInnerRec>() {
            @Override
            public void accept(TargetClass.TargetInnerRec rec) {
                super.getClass(); // Super constructor invocation
                System.out.println(rec.innerValue);
            }
        };

        // SynchronizedStatement (transient modifier, diff_package_target pos, this.field + 1)
        SyncHelper syncHelper = new SyncHelper();
        synchronized (syncHelper) {
            syncHelper.syncField = 1; // this.field, target_feature 1
            syncHelper.syncLogic();
        }

        // Anonymous inner class (source class feature)
        Runnable anonRunnable = new Runnable() {
            @Override
            public void run() {
                // No new exception feature (no throw new Exception)
                if (targetParam == null) return;
                System.out.println(targetParam.innerValue);
            }
        };
        anonRunnable.run();

        // Call method (target, private, pos: object initialization, lambda (parameters) -> methodBody)
        TargetClass targetObj = new TargetClass(5) {
            @Override
            private void targetCallMethod() { // private modifier, instance target_feature
                // (parameters) -> methodBody feature
                Consumer<Integer> callLambda = (param) -> {
                    this.innerValue = param;
                    System.out.println("Processed: " + this.innerValue);
                };
                callLambda.accept(localVar);
            }
        };
        targetObj.targetCallMethod();

        anonInner.accept(targetParam);
        lambda.run();
    }

    // Implement Runnable interface (implements feature)
    @Override
    public void run() {
        // Empty implementation for super keywords
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass(0);
    }
}

// Target normal class (default modifier, anonymous inner class feature)
class TargetClass {
    private int value;
    protected int innerValue;

    // Member inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        public int innerValue;

        public TargetInnerRec(int value) {
            this.innerValue = value;
        }
    }

    // Call method (private, instance, pos: object initialization, void return)
    private void targetCallMethod() {}

    // Anonymous inner class (target_feature)
    public Consumer<Integer> getAnonymousConsumer() {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                TargetClass.this.value = integer;
            }
        };
    }

    public int getValue() {
        return this.value;
    }

    public TargetClass(int value) {
        this.value = value;
    }
}