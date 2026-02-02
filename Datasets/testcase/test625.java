package com.refactoring.movemethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.function.Supplier;

// Source class: protected normal class, same package as target, anonymous inner + local inner class
protected class SourceClass {
    private int outerField = 1; // For OuterClass.this.x feature (numbers=1 alignment)
    // Per_condition: method contains target parameter (enforced in signature)

    // Method to refactor: instance, base type (int) return, private access, in source class
    private int methodToRefactor(FinalTargetClass targetParam) {
        int result = 0;

        // Constructor invocation (target class and inner class)
        FinalTargetClass newTarget = new FinalTargetClass("new_target_1"); // numbers=1
        FinalTargetClass.TargetInner newInner = newTarget.new TargetInner();

        // Type declaration statement
        class ProcessedInt {
            private int value;
            ProcessedInt(int val) { this.value = val; }
            int getVal() { return value; }
        }

        // While statement
        int count = 0;
        while (count < 2) { // method_feature: 2
            // Variable call (target class and inner class)
            String targetVal = targetParam.getValue();
            String innerVal = targetParam.getInner().getInnerValue();
            result += targetVal.length() + innerVal.length() + count;
            count++;
        }

        // Instance method feature: synchronized modifier, 2, inner_class, instance, this.methodName(), pos=exception handling, return base type (int)
        private synchronized int instanceHelperMethod(FinalTargetClass.TargetInner inner) {
            try {
                // pos: exception handling statements
                if (inner == null) throw new IllegalArgumentException("Inner class null");
                // method_feature: this.methodName(arguments)
                int helperResult = this.calculateInnerValue(inner);
                // method_feature: 2 (numeric literal)
                return helperResult * 2;
            } catch (IllegalArgumentException e) {
                // No new exception, handle only
                return 2; // method_feature: 2
            }
        }
        result += instanceHelperMethod(newInner);

        // numbers=1, protected modifier, exp=CreationReference (constructor reference)
        protected Supplier<FinalTargetClass.TargetInner> creationRef() {
            // CreationReference (FinalTargetClass.TargetInner::new)
            Supplier<FinalTargetClass.TargetInner> ref = newTarget::new TargetInner; // numbers=1
            return ref;
        }
        result += creationRef().get().getInnerValue().length();

        // OuterClass.this.x feature (access outer class field via qualified this)
        result += SourceClass.this.outerField; // numbers=1

        // Used by reflection feature
        try {
            Constructor<FinalTargetClass.TargetInner> innerCtor = FinalTargetClass.TargetInner.class.getDeclaredConstructor(FinalTargetClass.class, String.class);
            innerCtor.setAccessible(true);
            FinalTargetClass.TargetInner reflectInner = innerCtor.newInstance(targetParam, "reflect_1"); // numbers=1

            Method setMethod = FinalTargetClass.TargetInner.class.getDeclaredMethod("setInnerValue", String.class);
            setMethod.invoke(reflectInner, "reflection_" + result);
        } catch (Exception e) {
            // No new exception feature
            result = Math.abs(result);
        }

        // Local inner class (source feature)
        class LocalInnerProcessor {
            int process(int input) {
                return input + SourceClass.this.outerField; // OuterClass.this.x
            }
        }
        result = new LocalInnerProcessor().process(result);

        // Anonymous inner class (source feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner result: " + result);
            }
        };
        anonymous.run();

        // No new exception feature
        try {
            if (result < 0) throw new ArithmeticException("Negative result");
        } catch (ArithmeticException e) {
            // No throw new exception, handle only
            result = Math.abs(result);
        }

        return result; // Base type (int) return
    }

    // Helper method for this.methodName(arguments)
    private int calculateInnerValue(FinalTargetClass.TargetInner inner) {
        return inner.getInnerValue().length();
    }
}

// Target class: final normal class, same package as source, member inner class feature
final class FinalTargetClass {
    private String value;

    public FinalTargetClass(String value) {
        this.value = value;
    }

    // Member inner class (target_feature)
    public class TargetInner {
        private String innerValue;

        public TargetInner() {
            this.innerValue = "inner_default_1"; // numbers=1
        }

        public TargetInner(String innerValue) {
            this.innerValue = innerValue;
        }

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }

    public TargetInner getInner() {
        return new TargetInner();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}