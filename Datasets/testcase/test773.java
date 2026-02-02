package com.refactoring.movemethod;

import java.sql.SQLException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Parent class for method_feature: parent_class
class RecordParent {
    protected Object parentMethod(int val) {
        return val * 2; // method_feature: "2"
    }
}

// Source sealed record class: sealed modifier, same package, local inner + member inner classes
sealed record SourceRecord(String sourceField) permits SourceRecordImpl, SourceRecord {
    // Member inner class (source feature)
    public class SourceInnerClass extends RecordParent {
        private String innerField = "source_inner_val";

        // Method to be refactored: varargs, base type (int) return, private access, source_inner
        @RefactorAnnotation // has_annotation feature
        private int methodToMove(TargetRecord targetParam, String... varargs) throws SQLException {
            // per_condition: method contains target class parameter
            if (targetParam == null) {
                throw new SQLException("Target parameter cannot be null"); // SQLException feature
            }

            // variable call feature
            String varCall = this.innerField;

            // access_instance_method feature
            this.innerInstanceMethod(varCall);

            // with_bounds feature (type bound check)
            if (varargs.length > 0 && varargs[0] instanceof CharSequence s && s.length() > 2) {
                varCall += "_bounded";
            }

            // while statement
            int count = 0;
            while (count < 2) { // method_feature: "2"
                varCall += count;
                count++;
            }

            // Instance method (type: instance, modifier: public, pos: array initialization, return Object)
            public Object nestedInstanceMethod() {
                // array initialization position
                String[] arr = new String[]{"val1", "val2"};
                // method_feature: super.methodName(arguments)
                Object result = super.parentMethod(arr.length); // method_feature: "2" (arr length = 2)
                // method_feature: parent_class + instance
                return result;
            }
            nestedInstanceMethod();

            // Local inner class (source feature)
            class SourceLocalInner {
                private void validate(String val) throws SQLException {
                    if (val.isEmpty()) throw new SQLException("Empty value");
                }
            }
            new SourceLocalInner().validate(varCall);

            // no_new_exception (no explicit new Exception instantiation)
            return targetParam.TargetStaticNested.getStaticVal() + varargs.length;
        }

        // Instance method for access_instance_method
        private void innerInstanceMethod(String val) {
            System.out.println("Instance method called with: " + val);
        }
    }
}

// Source record implementation for sealed permit
final record SourceRecordImpl(String sourceField) extends SourceRecord {
    public SourceRecordImpl(String sourceField) {
        super(sourceField);
    }
}

// Target record class: public modifier, static nested class (target_feature)
public record TargetRecord(String targetField) {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private static int staticVal = 2; // method_feature: "2"

        public static int getStaticVal() {
            return staticVal;
        }
    }
}