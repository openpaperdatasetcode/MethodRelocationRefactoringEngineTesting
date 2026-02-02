package com.refactoring.movemethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Supporting annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {}

// Diff package class for IfStatement pos (simulated same package structure for minimal code)
class DiffPackageOthers {
    static int processIfCondition(int val) {
        return val;
    }
}

protected class SourceClass {
    // First member inner class
    class MemberInnerClass1 {}
    // Second member inner class
    class MemberInnerClass2 {}

    // Private field for access_outer_private
    private String outerPrivateField = "test";

    @TestAnnotation // First has_annotation
    protected List<String> getTargetValues(AbstractTargetClass.TargetInnerRec param) {
        List<String> result = new ArrayList<>();
        Object lock = new Object();

        // Synchronized statement
        synchronized (lock) {
            // Type declaration statement
            String localVar = param.getValue();
            // Variable call
            result.add(localVar);

            // IfStatement with private modifier, super.field, 1, pos: diff_package_others
            privateIfStatement(param);

            try {
                // IOException
                if (localVar.isEmpty()) {
                    throw new IOException("Empty value");
                }
            } catch (IOException e) {
                // no_new_exception
                result.add("error");
            }

            // Access_outer_private
            result.add(this.outerPrivateField);
        }
        return result;
    }

    @TestAnnotation // Second has_annotation (duplicate feature)
    private void privateIfStatement(AbstractTargetClass.TargetInnerRec param) {
        // Diff_package_others position
        int val = DiffPackageOthers.processIfCondition(1);
        // IfStatement with super.field and 1
        if (param.getSuperField() == val) {
            param.setValue("matched");
        }
    }
}

abstract class AbstractTargetClass {
    // Static nested class in target class
    static class TargetStaticNestedClass {}

    class TargetInnerRec extends SuperClass {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // Access super.field
        public int getSuperField() {
            return super.superField;
        }
    }
}

// Super class for super.field feature
class SuperClass {
    protected int superField = 1;
}