package com.refactoring.movemethod;

import java.sql.SQLException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorTestAnnotation {}

// Abstract super class to host abstract method (source_class is normal class, method is abstract)
abstract class AbstractSuperClass {
    @RefactorTestAnnotation
    protected abstract Object processTarget(TargetClass.TargetInner targetParam) throws SQLException;
    
    // Overload method for overload_exist feature
    protected Object processTarget(TargetClass.TargetInner targetParam, int flag) throws SQLException {
        return targetParam;
    }
}

// Source normal class (private modifier, same package)
private class SourceClass extends AbstractSuperClass {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "PRIVATE_DATA";

    // Local inner class (source feature)
    public void sourceMethodWithLocalInner() {
        class LocalInnerClass {
            void invokeAbstractMethod(TargetClass.TargetInner inner) throws SQLException {
                SourceClass.this.processTarget(inner);
            }
        }
        new LocalInnerClass().invokeAbstractMethod(new TargetClass().new TargetInner());
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            try {
                processTarget(new TargetClass().new TargetInner());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    // Abstract method implementation (method is abstract in super, implemented here to comply)
    @Override
    @RefactorTestAnnotation // has_annotation feature
    protected Object processTarget(TargetClass.TargetInner targetParam) throws SQLException {
        // Per_condition: contains target parameter (variable call)
        int targetField = targetParam.targetField;
        
        // Constructor invocation of target inner class
        TargetClass.TargetInner newInner = new TargetClass().new TargetInner();
        
        // Switch case with CaseDefaultExpression (numbers=2, private modifier)
        switch (targetField) {
            case 1:
                newInner.targetField = 1;
                break;
            case 2: // numbers=2 feature
                newInner.targetField = 2;
                break;
            default: // CaseDefaultExpression feature
                newInner.targetField = -1;
                break;
        }

        // Access outer private field
        newInner.auxField = outerPrivateField;

        // Overload_exist: call overloaded method
        this.processTarget(targetParam, 2);

        // SQLException & requires_throws: declare and throw checked exception
        if (targetField < 0) {
            throw new SQLException("Invalid target field value: " + targetField);
        }

        return newInner;
    }
}

// Target normal class (protected modifier)
protected class TargetClass {
    // Anonymous inner class (target feature)
    Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetInner inner = new TargetInner();
            inner.targetField = 0;
        }
    };

    // Target inner class (target_inner)
    class TargetInner {
        int targetField;
        String auxField;

        public int getTargetField() {
            return targetField;
        }

        public void setTargetField(int targetField) {
            this.targetField = targetField;
        }
    }
}