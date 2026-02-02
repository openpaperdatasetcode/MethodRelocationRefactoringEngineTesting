package com.refactoring.movemethod;

// Super class for source class (extends feature)
class SourceSuperClass {
    protected int superValue = 5;
}

// Source normal class (protected modifier, same package, extends + member inner + static nested class)
protected class SourceClass extends SourceSuperClass {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Static nested class (source class feature)
    public static class SourceStaticNested {
        public int nestedField = 10;

        public int getNestedValue() {
            return nestedField;
        }
    }

    // Member inner class (source class feature)
    public class SourceMemberInner {
        public int innerField = 3;

        public int getInnerValue() {
            return innerField;
        }
    }

    // Instance method to refactor (public, return base type (int), source position)
    public int refactorMethod(TargetClass targetParam) {
        // Variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        SourceMemberInner memberInner = new SourceMemberInner();
        int localVar = staticNested.getNestedValue() + memberInner.getInnerValue() + superValue;

        // No new exception feature (no throw new Exception)
        if (targetParam == null) {
            return localVar;
        }

        // Process target parameter (per_condition: method has target parameter)
        this.targetField = targetParam;
        localVar += targetParam.getTargetValue();
        localVar += TargetClass.TargetStaticNested.staticField; // Access target's static nested class

        return localVar;
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass();
    }
}

// Target normal class (protected modifier, static nested class feature)
protected class TargetClass {
    private int targetValue = 8;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static int staticField = 2;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }
}