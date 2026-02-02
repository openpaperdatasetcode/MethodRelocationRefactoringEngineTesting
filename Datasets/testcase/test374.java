package com.refactoring.movemethod;

// Super class for target class (extends feature)
class TargetSuperClass<T> {
    protected T superData;

    public TargetSuperClass(T superData) {
        this.superData = superData;
    }
}

// Others class for recursion method feature
class RecursionHelper {
    // Recursion method (method_feature: recursion/others_class/2/ClassName.methodName)
    public static <T> TargetClass<T>.TargetInnerRec recursiveMethod(TargetClass<T>.TargetInnerRec inner, int count) {
        if (count <= 0) return inner; // base case for recursion
        inner.setData(inner.getData() + "_rec" + count);
        return recursiveMethod(inner, count - 1); // recursion call (method_feature: 2)
    }
}

// Source normal class (protected, same package, type parameter + static nested + member inner)
protected class SourceClass<T extends Number> {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass<String> targetField;

    // Static nested class (source class feature)
    public static class SourceStaticNested {
        public int nestedValue = 10;

        public int getNestedValue() {
            return nestedValue;
        }
    }

    // Member inner recursive class (source_inner_rec position for refactor method)
    public class SourceInnerRec {
        private int innerVar = 5;

        // Generic method to refactor (protected, return Object, source_inner_rec)
        protected <V> Object refactorMethod(TargetClass<V>.TargetInnerRec targetParam) {
            // Uses outer this feature
            SourceClass<T> outerThis = SourceClass.this;
            
            // Variable call feature
            SourceStaticNested staticNested = new SourceStaticNested();
            int localVar = staticNested.getNestedValue() + this.innerVar;
            
            // This.var = var feature
            this.innerVar = localVar;
            outerThis.targetField = (TargetClass<String>) targetParam.getOuterTarget();
            
            // Super keywords feature (via outer class hierarchy)
            super.getClass();
            
            // Recursion method in do-while (pos: do-while, method_feature: 2/others_class/recursion)
            int doCount = 2; // method_feature: 2
            TargetClass<V>.TargetInnerRec recursiveResult = targetParam;
            do {
                // ClassName.methodName(arguments) feature
                recursiveResult = RecursionHelper.recursiveMethod(recursiveResult, doCount);
                doCount--;
            } while (doCount > 0);

            // No new exception feature (no throw new Exception)
            if (targetParam == null) {
                return "default";
            }

            // Process target parameter (per_condition: method has target parameter)
            recursiveResult.setData(recursiveResult.getData() + "_processed");
            return recursiveResult;
        }
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass<>("init");
        SourceInnerRec innerRec = new SourceInnerRec();
    }
}

// Target normal class (public, type parameter + extends feature)
public class TargetClass<V> extends TargetSuperClass<V> {
    // Inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        private V data;

        public TargetInnerRec(V data) {
            this.data = data;
        }

        // Getter/Setter for data
        public V getData() {
            return data;
        }

        public void setData(V data) {
            this.data = data;
        }

        // Get outer target class instance
        public TargetClass<V> getOuterTarget() {
            return TargetClass.this;
        }
    }

    // Constructor (super constructor invocation for extends feature)
    public TargetClass(V data) {
        super(data);
    }
}