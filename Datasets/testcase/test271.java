package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface OverrideCandidate {}

// Protected abstract source class (same package as target)
protected abstract class SourceAbstractClass {
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "outer_protected_value";

    // Member inner class (source class feature)
    protected class MemberInnerClass {
        String innerField;

        public MemberInnerClass(String innerField) {
            this.innerField = innerField;
        }
    }

    // DoStatement (protected modifier, pos: source, target_feature: this.field, 2)
    protected class DoStatement {
        // this.field feature
        private int field;

        public DoStatement() {
            this.field = 2; // Feature "2" (numeric literal)
        }

        public void execute() {
            do {
                this.field++; // Use this.field
            } while (this.field < 10);
        }
    }

    // Parent method for overriding
    protected Object parentMethod(List<?> listParam) {
        return new ArrayList<>();
    }

    // Overriding method to refactor (private access, returns Object, has List parameter)
    @OverrideCandidate // has_annotation feature
    @Override
    private Object parentMethod(List listParam) { // raw_type feature (List without generic)
        // Type declaration statement feature
        DoStatement doStmt = new DoStatement();
        MemberInnerClass innerObj = new MemberInnerClass(outerProtectedField);

        // Access_outer_protected feature
        String outerVal = SourceAbstractClass.this.outerProtectedField;

        // PrefixExpression with number 2 (modifier: protected)
        protected int prefixExpr = ++doStmt.field; // Prefix increment (PrefixExpression), uses 2

        // Expression statement feature
        doStmt.execute();

        // Instance method in collection operations (pos: collection operations)
        protected List<String> instanceMethod(Collection<String> coll) {
            // Method feature: 1 (numeric literal)
            int limit = 1;
            // Method feature: source (reference source class)
            SourceAbstractClass sourceRef = SourceAbstractClass.this;
            // Method feature: instance (instance reference)
            // Method feature: (parameters) -> methodBody (lambda)
            return coll.stream()
                    .limit(limit)
                    .map(s -> s + sourceRef.outerProtectedField)
                    .toList();
        }

        // Variable call feature
        List<String> stringList = new ArrayList<>();
        stringList.add(outerVal);
        stringList.add(String.valueOf(prefixExpr));
        List<String> processedList = instanceMethod(stringList); // Collection operations call

        // Assert statement feature
        assert processedList.size() > 0 : "Processed list is empty";

        // Target field in source (per_condition: source contains target's field)
        private TargetAbstractClass.target_inner targetField = new TargetAbstractClass.target_inner();

        // Variable call to target field
        targetField.setData(processedList.get(0));

        // Local inner class (source class feature)
        class LocalInnerClass {
            int localValue = doStmt.field;
        }
        LocalInnerClass localInner = new LocalInnerClass();

        // Subclass method call (pos: object initialization)
        SubClass subClass = new SubClass(); // Object initialization
        subClass.callMethod(); // new ClassName().method() feature

        return localInner.localValue;
    }
}

// Private abstract target class (same package as source, empty target_feature)
private abstract class TargetAbstractClass {
    // Target inner class (target_inner)
    public class target_inner {
        private String data;

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    // Abstract method (required for abstract class)
    protected abstract void targetAbstractMethod();
}

// Subclass for call_method (type: sub_class, modifier: default)
class SubClass extends SourceAbstractClass {
    // Call method (pos: object initialization, return_type: void, target_feature: normal, new ClassName().method())
    void callMethod() {
        // new ClassName().method() feature
        new TargetAbstractClass.target_inner() {
            @Override
            public String getData() {
                return super.getData() + "_subclass";
            }
        }.setData("call_method_data");
        
        // Target_feature: normal (standard method execution)
        this.targetAbstractMethod();
    }

    @Override
    protected void targetAbstractMethod() {}

    @Override
    protected Object parentMethod(List<?> listParam) {
        return null;
    }
}