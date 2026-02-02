package com.refactoring.movemethod;

// Source normal class (public, same package, type parameter + member inner + local inner)
public class SourceClass<T extends Number> {
    private T instanceVar;

    // Member inner class (source class feature)
    public class SourceMemberInner {
        public int innerField = 10;

        public int getInnerField() {
            return innerField;
        }
    }

    // Abstract method to refactor (instance type, return TargetClass, default access, source position)
    abstract TargetClass.TargetInnerRec refactorMethod(TargetClass.TargetInnerRec targetParam);

    // Concrete implementation to demonstrate method features (for compilation)
    @Override
    public TargetClass.TargetInnerRec refactorMethod(TargetClass.TargetInnerRec targetParam) {
        // Variable call feature
        T localVar = this.instanceVar;
        SourceMemberInner innerObj = new SourceMemberInner();
        int varValue = innerObj.getInnerField(); // Variable call

        // Access instance method feature
        int instanceMethodResult = this.processValue(varValue);

        // Local inner class (source class feature)
        class LocalInnerClass {
            public TargetClass.TargetInnerRec createInnerRec(TargetClass.TargetInnerRec param) {
                // Super constructor invocation (via target class hierarchy)
                TargetClass.TargetInnerRec innerRec = new TargetClass.TargetInnerRec(param.getValue() + instanceMethodResult) {
                    @Override
                    public int getValue() {
                        return super.getValue() + 5; // Super method call (related to super constructor)
                    }
                };
                // Constructor invocation feature
                TargetClass outerTarget = new TargetClass("test");
                return outerTarget.new TargetInnerRec(innerRec.getValue());
            }
        }

        LocalInnerClass localInner = new LocalInnerClass();
        TargetClass.TargetInnerRec result = localInner.createInnerRec(targetParam);

        // No new exception feature (no throw new Exception)
        if (targetParam == null) {
            return new TargetClass.TargetInnerRec(0);
        }

        return result;
    }

    // Instance method for access_instance_method feature
    public int processValue(int value) {
        return value * 2;
    }

    // Constructor for source class
    public SourceClass(T instanceVar) {
        this.instanceVar = instanceVar;
    }
}

// Target normal class (public, extends + member inner)
public class TargetClass extends BaseTargetClass {
    private String targetField;

    // Member inner class (target_inner_rec, target feature)
    public class TargetInnerRec {
        private int value;

        // Constructor for inner rec class
        public TargetInnerRec(int value) {
            this.value = value;
        }

        // Instance method for access
        public int getValue() {
            return value;
        }
    }

    // Super constructor invocation (target class extends BaseTargetClass)
    public TargetClass(String targetField) {
        super(targetField);
        this.targetField = targetField;
    }
}

// Super class for target class (extends feature)
class BaseTargetClass {
    protected String baseField;

    public BaseTargetClass(String baseField) {
        this.baseField = baseField;
    }
}