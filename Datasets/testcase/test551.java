package com.refactor;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: record, public, same package as target, type parameter + anonymous inner + member inner class
public record SourceRecord<T>(T data, String value) {
    // Target class field reference (per_condition)
    private final TargetRecord targetField = new TargetRecord("targetData");

    // Private field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";

    // Member inner class (source feature)
    class SourceMemberInner {
        // Source inner recursive class (source_inner_rec - method position)
        class SourceInnerRec {
            // Annotation with instance feature in attribute values (method_feature)
            @CustomAnnotation(
                value = "test",
                instanceCall = "outerInstance.new InnerClass().methodName()"
            )
            // Method to refactor: varargs, Object return, public, in source_inner_rec, requires throws
            public Object methodToMove(Object... args) throws SQLException {
                // SQLException (requires_throws)
                if (args.length == 0) {
                    throw new SQLException("Empty arguments");
                }

                // Instance feature: outerInstance.new InnerClass().methodName() in annotation attributes (return List<String>)
                List<String> instanceResult = SourceRecord.this.new SourceMemberInner().innerMethod();

                // Break statement
                loopLabel:
                for (int i = 0; i < 10; i++) {
                    if (i == 1) break loopLabel;
                }

                // Switch statement
                int switchVar = 1;
                switch (switchVar) {
                    case 1:
                        variableCall();
                        break;
                    default:
                        break;
                }

                // Synchronized statement
                synchronized (this) {
                    accessOuterPrivate();
                }

                // Variable call (target field access)
                String targetVar = targetField.data();

                // Access outer private field (access_outer_private)
                String privateAccess = SourceRecord.this.outerPrivateField;

                // Call method: target, default modifier, abstract, lambda in functional interface (pos: functional interfaces)
                FunctionalInterfaceExample func = () -> TargetRecord.targetMethod(args);

                // No new exception thrown (only declared SQLException)
                return new ArrayList<>(List.of(targetVar, privateAccess));
            }

            // Variable call helper method
            private void variableCall() {}

            // Access outer private helper method
            private void accessOuterPrivate() {}
        }

        // Inner method for instance feature (return List<String>)
        public List<String> innerMethod() {
            return new ArrayList<>(List.of("innerMethodValue"));
        }
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            SourceRecord.this.new SourceMemberInner().new SourceInnerRec().methodToMove("test");
        }
    };
}

// Target class: record, public, no additional features
public record TargetRecord(String data) {
    // Call method: target, default modifier, abstract, lambda body (parameters) -> methodBody
    static List<String> targetMethod(Object... params) {
        return new ArrayList<>(List.of(params));
    }
}

// Functional interface for call_method position (functional interfaces)
@FunctionalInterface
interface FunctionalInterfaceExample {
    List<String> getResult();
}

// Annotation for has_annotation feature (instance feature in attribute values)
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {
    String value();
    String instanceCall();
}