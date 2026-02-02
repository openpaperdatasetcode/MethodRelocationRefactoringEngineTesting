// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.other.AccessorHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Functional interface for implements feature
interface Processable {
    TargetClass.TargetInnerRec process(TargetClass.TargetInnerRec... params);
}

// Source normal class (protected, implements + member inner + static nested, different package)
protected class SourceClass implements Processable {
    private int instanceField = 1; // access_instance_field feature

    // Static nested class (source class feature)
    public static class StaticNestedClass {
        // Overload exist feature (overloaded methods)
        public int compute(int a) { return a * 3; } // method_feature: 3
        public int compute(int a, int b) { return a + b; }
    }

    // Member inner class (source_inner position for refactor method)
    public class SourceInnerClass {
        private int innerField = 1; // target_feature: this.field, 1

        // Varargs method to refactor (public, return TargetClass type, source_inner)
        public TargetClass.TargetInnerRec refactorMethod(TargetClass.TargetInnerRec... targetParams) {
            // Variable call feature
            StaticNestedClass staticNested = new StaticNestedClass();
            int localVar = staticNested.compute(3); // overload_exist, method_feature: 3

            // Access instance field feature
            localVar += SourceClass.this.instanceField;

            // IfStatement (private modifier, same_package_target pos, this.field + 1)
            if (this.innerField == 1) { // this.field, target_feature 1 (private modifier IfStatement)
                localVar += 10;
            }

            // ExpressionMethodReference (private modifier, numbers=2)
            Function<TargetClass.TargetInnerRec, String> ref1 = TargetClass.TargetInnerRec::getData; // 1st method ref
            Function<TargetClass.TargetInnerRec, Integer> ref2 = TargetClass.TargetInnerRec::getValue; // 2nd method ref

            // Accessor method (protected, pos: collection operations, method_feature: 3/others_class/accessor)
            List<TargetClass.TargetInnerRec> collection = new ArrayList<>();
            for (TargetClass.TargetInnerRec param : targetParams) {
                collection.add(this.accessorMethod(param)); // this.methodName(arguments)
            }

            // No new exception feature (no throw new Exception)
            if (targetParams.length == 0) {
                return new TargetClass().new TargetInnerRec("default", 1);
            }

            // Process target parameter (per_condition: method has target parameter)
            TargetClass.TargetInnerRec result = collection.get(0);
            result.setData(ref1.apply(result) + "_processed");
            result.setValue(ref2.apply(result) + localVar);
            return result;
        }

        // Accessor method (protected, return TargetClass type, method_feature: accessor/others_class/3)
        protected TargetClass.TargetInnerRec accessorMethod(TargetClass.TargetInnerRec param) {
            AccessorHelper helper = new AccessorHelper(); // others_class
            param.setData(helper.processData(param.getData(), 3)); // method_feature: 3
            return param;
        }
    }

    // Implement Processable interface (implements feature)
    @Override
    public TargetClass.TargetInnerRec process(TargetClass.TargetInnerRec... params) {
        SourceInnerClass inner = new SourceInnerClass();
        return inner.refactorMethod(params);
    }
}

// Others class for accessor method (method_feature: others_class)
package com.refactoring.other;

public class AccessorHelper {
    public String processData(String data, int num) {
        return data + "_" + num;
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Target normal class (protected, local inner class feature)
protected class TargetClass {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        private String data;
        private int value;

        public TargetInnerRec(String data, int value) {
            this.data = data;
            this.value = value;
        }

        // Local inner class (target_feature)
        public void processLocalInner() {
            class LocalInnerClass {
                public void update(TargetInnerRec rec) {
                    rec.data = "local_" + rec.data;
                }
            }
            new LocalInnerClass().update(this);
        }

        // Getters/Setters (accessor feature)
        public String getData() { return data; }
        public void setData(String data) { this.data = data; }
        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }

    // IfStatement helper (same_package_target pos)
    public class IfHelper {
        public int field = 1; // target_feature: this.field, 1
    }
}