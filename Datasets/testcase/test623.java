package com.refactoring.movemethod;

import java.io.IOException;

// Super class for super constructor invocation
class SuperSourceClass {
    protected int superField;

    public SuperSourceClass(int value) {
        this.superField = value;
    }
}

// Base class for overriding feature in call_method
class BaseInnerClass {
    public String processTarget(TargetClass.TargetInnerRec inner) {
        return inner.getInnerValue() + "_base";
    }
}

// Source class: abstract normal class, same package as target, two local inner classes
abstract class SourceClass extends SuperSourceClass {
    // Instance field for access_instance_field feature
    private int instanceField = 2; // align with numbers=2
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    public SourceClass() {
        // Super constructor invocation
        super(2); // numbers=2 alignment
    }

    // Overloading method 1 (method type: overloading)
    public int methodToRefactor(TargetClass.TargetInnerRec innerParam) throws IOException {
        // Type declaration statement
        class ProcessedInt {
            private int value;
            ProcessedInt(int val) { this.value = val; }
            int getValue() { return this.value; }
        }

        // numbers=2, final modifier, exp=VariableDeclarationExpression
        final int localVar = 2; // VariableDeclarationExpression, numbers=2, final modifier

        int result = 0;
        // Try statement
        try {
            // For loop with continue statement
            for (int i = 0; i < 5; i++) {
                if (i == 2) { // numbers=2 alignment
                    continue; // continue statement
                }
                // Variable call (target inner recursive class)
                String innerValue = innerParam.getInnerValue();
                // Access instance field
                result += instanceField + i;
            }

            // Requires_throws validation
            if (result < 0) {
                throw new IOException("Negative result"); // requires_throws
            }

            result = new ProcessedInt(result * localVar).getValue();
        } catch (IOException e) {
            throw e; // requires_throws
        }

        // First local inner class (source feature)
        class LocalInnerProcessor1 {
            int process(int input) {
                return input + localVar; // numbers=2
            }
        }
        result = new LocalInnerProcessor1().process(result);

        // Second local inner class (source feature)
        class LocalInnerProcessor2 {
            int adjust(int input) {
                return input * instanceField; // access_instance_field, numbers=2
            }
        }
        result = new LocalInnerProcessor2().adjust(result);

        return result; // base type (int) return
    }

    // Overloading method 2 (overloading feature)
    public int methodToRefactor(TargetClass.TargetInnerRec innerParam, int multiplier) throws IOException {
        int baseResult = methodToRefactor(innerParam);
        return baseResult * multiplier * 2; // numbers=2 alignment
    }

    // Inner class for call_method (inner_class type)
    public class SourceInnerClass extends BaseInnerClass {
        // call_method: public modifier, overriding, obj.m1().m2().m3(), pos=exception handling, return String
        @Override
        public String processTarget(TargetClass.TargetInnerRec inner) {
            try {
                // pos: exception handling statements
                int methodResult = methodToRefactor(inner);
                // obj.m1().m2().m3() feature (method chaining)
                String chain = inner.m1().m2().m3(); // target_feature: obj.m1().m2().m3()
                return chain + "_processed_" + methodResult;
            } catch (IOException e) {
                // exception handling statements
                return "error_" + e.getMessage();
            }
        }
    }
}

// Target class: public normal class, anonymous inner class feature
public class TargetClass {
    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private String innerValue = "target_inner_2"; // numbers=2 alignment

        public String getInnerValue() {
            // Anonymous inner class (target_feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Target anonymous: " + innerValue);
                }
            };
            anonymousRunnable.run();
            return innerValue;
        }

        // Method chaining for obj.m1().m2().m3()
        public String m1() { return "m1_"; }
        public String m2() { return "m2_"; }
        public String m3() { return "m3_" + innerValue; }
    }
}