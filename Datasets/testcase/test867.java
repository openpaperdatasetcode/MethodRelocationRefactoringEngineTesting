package com.refactoring.test;

import java.sql.SQLException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Source record class (default modifier, same package, local inner + static nested class)
record SourceRecord(String privateField) {
    // Static field for depends_on_static_field feature
    private static int staticField = 100;

    // Static nested class (source feature)
    static class SourceStaticNested {
        // Inner class for method position (source_inner)
        class SourceInnerClass {
            // Method to be refactored (varargs, base type return, public access)
            @RefactorAnnotation // has_annotation feature
            public int moveMethod(TargetRecord.TargetInner... targetParams) {
                // Access outer private field (access_outer_private)
                String outerPrivate = SourceRecord.this.privateField;
                int result = 0;

                // Empty statement feature
                ;

                // Super constructor invocation (Object superclass)
                Object superObj = new Object();

                // With bounds (generic with bounds)
                class BoundedGeneric<T extends Number & Comparable<T>> {
                    T process(T val) {
                        return val;
                    }
                }
                BoundedGeneric<Integer> bounded = new BoundedGeneric<>();

                // Switch case feature
                switch (targetParams.length) {
                    case 0:
                        throw new IllegalArgumentException("No target parameters"); // throw statement
                    case 1:
                        // Variable call + depends_on_static_field
                        int varCall = staticField;
                        result = targetParams[0].innerField() + varCall + bounded.process(1);
                        break;
                    default:
                        result = targetParams[0].innerField() * targetParams.length;
                        break;
                }

                // SQLException feature + no_new_exception (declared only, no new instance)
                try {
                    if (result < 0) {
                        throw new SQLException("Invalid result value");
                    }
                } catch (SQLException e) {
                    // Lambda expression with call_method (pos: Lambda expressions)
                    TargetSubClass subClass = new TargetSubClass();
                    String lambdaResult = ((int param) -> subClass.callMethod(param, targetParams[0])).apply(result);
                    result = lambdaResult.length();
                }

                // Per_condition: contains target parameter (targetParams)
                return result; // base type return
            }
        }

        // Local inner class (source feature)
        class LocalInnerClass {
            void validateTarget(TargetRecord.TargetInner target) {
                if (target.innerField() < 0) throw new IllegalArgumentException();
            }
        }
    }
}

// Target record class (protected modifier, member inner class feature)
protected record TargetRecord(String data) {
    // Member inner class (target_inner)
    class TargetInner {
        private int innerField;

        public int innerField() {
            return innerField;
        }

        public void setInnerField(int innerField) {
            this.innerField = innerField;
        }
    }

    // Base method for overriding
    protected String callMethod(int param, TargetInner target) {
        return param + ":" + target.innerField();
    }
}

// Subclass for call_method (sub_class type)
class TargetSubClass extends TargetRecord {
    public TargetSubClass() {
        super("subclass_data");
    }

    // call_method (protected modifier, overriding + (parameters) -> methodBody)
    @Override
    protected String callMethod(int param, TargetInner target) {
        // Lambda in method body: (parameters) -> methodBody
        return ((Integer p) -> p * 2 + "-" + target.innerField()).apply(param);
    }
}