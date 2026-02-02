package com.refactoring.test;

// Parent class for target class extends feature
class TargetParentClass {
    protected int parentField;
    public TargetParentClass(int val) {
        this.parentField = val;
    }
}

// Target class (normal class, public modifier, extends + anonymous inner class feature)
public class TargetClass extends TargetParentClass {
    // Target inner class (target_inner)
    public class TargetInner {
        int innerField; // For per_condition (source contains this field)
        int value = 10; // For lambda expression () -> System.out.println(this.value)
    }

    // Super constructor invocation (required for extends)
    public TargetClass() {
        super(0);
    }

    // Anonymous inner class (target_feature)
    Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class");
        }
    };
}

// Source class (normal class, default modifier, same package, type parameter + member inner + static nested class)
class SourceClass<T extends Number> {
    private T typeParam; // Type parameter feature

    // Static nested class (source_feature)
    static class SourceStaticNested {
        int nestedVal = 5;
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // Method to be overridden for overriding feature
        protected int moveMethod(TargetClass.TargetInner targetParam) {
            return 0;
        }
    }

    // Overriding class
    class SourceOverridingClass extends SourceMemberInner {
        // Method to be refactored (overriding, base type return, protected access, source position)
        @Override
        protected int moveMethod(TargetClass.TargetInner targetParam) {
            // Per_condition: source contains the field of the target (access targetParam.innerField)
            if (targetParam == null) {
                return 0;
            }

            // Constructor invocation
            TargetClass targetInstance = new TargetClass();
            TargetClass.TargetInner newInner = targetInstance.new TargetInner();

            // Do statement
            int count = 0;
            do {
                targetParam.innerField += count;
                count++;
            } while (count < 3);

            // Super constructor invocation (Object superclass)
            Object superObj = new Object();

            // Lambda expression: () -> System.out.println(this.value)
            Runnable lambda = () -> System.out.println(targetParam.value);
            lambda.run();

            // Variable call
            int varCall = targetParam.innerField; // Access target field (per_condition)
            targetParam.innerField = varCall + new SourceStaticNested().nestedVal;

            // No new exception
            return targetParam.innerField; // Base type return (int)
        }
    }
}