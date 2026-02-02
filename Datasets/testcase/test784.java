package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;

// Source class: normal, protected modifier, same package, member inner + static nested classes
protected class SourceClass {
    // per_condition: source contains field of target
    private TargetClass<String> targetField = new TargetClass<>();

    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "static_6136";

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_val";
    }

    // Member inner class (source feature)
    protected class SourceMemberInner {
        private String innerField = "member_inner_val";
    }

    // Abstract method for method_feature: abstract
    public abstract int abstractMethod(int val); // method_feature: abstract, return base type (int)

    // Method to be refactored: instance, List<String> return, default access, source position
    List<String> methodToMove() {
        // type declaration statement feature
        SourceStaticNested staticNested = new SourceStaticNested();
        SourceMemberInner memberInner = new SourceMemberInner();
        TargetClass<String>.TargetInner targetInner = targetField.new TargetInner();

        // variable call feature
        String varCall = staticNested.nestedField + memberInner.innerField + STATIC_FIELD; // depends_on_static_field

        // numbers: "1", modifier: abstract, exp: Assignment
        abstract int assignmentExp() {
            int num = 1; // numbers: "1"
            targetInner.innerField = String.valueOf(num); // Assignment expression
            return num;
        }

        // ExpressionStatement feature (type: ExpressionStatement, modifier: public, pos: source)
        public void expressionStatementLogic() {
            int count = 2; // target_feature: "2"
            // target_feature: obj.field (targetInner's field)
            targetInner.innerField = varCall + "_" + count; // ExpressionStatement
        }
        expressionStatementLogic();

        // do statement feature
        int doCount = 0;
        do {
            varCall += "_do_" + doCount;
            doCount++;
        } while (doCount < 2); // target_feature: "2"

        // Abstract method implementation (type: abstract, modifier: public, pos: for, return base type)
        @Override
        public int abstractMethod(int val) {
            int result = 0;
            // pos: for statement
            for (int i = 0; i < 3; i++) { // method_feature: "3"
                // method_feature: target + new ClassName().method()
                result += new TargetClass<Integer>().new TargetInner().processInt(3); // method_feature: abstract context
            }
            return result; // return base type (int)
        }
        int abstractResult = abstractMethod(3);

        // no_new_exception (no explicit new Exception instantiation)
        List<String> result = new ArrayList<>();
        result.add(varCall);
        result.add(targetInner.innerField);
        result.add(String.valueOf(abstractResult));
        return result;
    }
}

// Target class: normal, protected modifier, type parameter (target_feature)
protected class TargetClass<T> {
    // Target inner class (target_inner)
    public class TargetInner {
        public String innerField;

        public int processInt(int val) {
            return val * 3; // method_feature: "3"
        }

        public T processGeneric(T val) {
            return val;
        }
    }
}

// Concrete implementation of abstract method (for compilation)
class SourceConcrete extends SourceClass {
    @Override
    public int abstractMethod(int val) {
        return val * 3;
    }
}