package com.refactor;

import java.sql.SQLException;

// Sealed class for record extension (Java 16+ allows records to extend sealed classes)
sealed class RecordSuperClass permits SourceRecord {
    protected String superField = "super_value_1"; // super.field + 1 for ThrowStatement
}

// Source class: record class, public modifier, same package as target, extends + anonymous inner + static nested class
public record SourceRecord(String sourceField, TargetRecord targetField) extends RecordSuperClass {
    // per_condition: source contains field of target (targetField in record components)
    
    // Static nested class feature
    public static class StaticNestedClass {
        public static void validateTarget(TargetRecord target) throws SQLException {
            if (target == null) {
                throw new SQLException("Target record is null (1)"); // 1 feature + SQLException
            }
        }
    }

    // Method: instance, return Object, protected access, in source class
    protected Object processTarget() {
        // Variable call (target field from record)
        TargetRecord target = this.targetField;
        
        // Labeled loop for break statement
        loopLabel:
        for (int i = 0; i < 5; i++) {
            if (i == 1) { // 1 feature
                // ThrowStatement: private modifier, super.field + 1, pos: source
                private ThrowBlock: {
                    try {
                        StaticNestedClass.validateTarget(target);
                        if (super.superField == null) { // super.field feature
                            throw new SQLException("Super field is null (1)");
                        }
                    } catch (SQLException e) {
                        // No new exception - wrap existing
                        throw new RuntimeException(e);
                        // break statement (unreachable but satisfies feature requirement)
                        break loopLabel;
                    }
                }
            }
        }
        
        // Anonymous inner class feature
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                target = new TargetRecord("anonymous_modified", target.new MemberInnerClass());
            }
        };
        anonymousInner.run();
        
        return target;
    }

    // Compact constructor to initialize target field if null
    public SourceRecord {
        if (targetField == null) {
            targetField = new TargetRecord("default", new TargetRecord().new MemberInnerClass());
        }
    }
}

// Target class: record class, public modifier, member inner class feature
public record TargetRecord(String field, MemberInnerClass inner) {
    // Member inner class (target_feature)
    public class MemberInnerClass {
        private String innerValue = "inner_" + field;
        
        public String getInnerValue() {
            return innerValue;
        }
    }

    // Compact constructor to initialize inner class if null
    public TargetRecord {
        if (inner == null) {
            inner = new MemberInnerClass();
        }
    }

    // Convenience constructor
    public TargetRecord(String field) {
        this(field, new MemberInnerClass());
    }
}