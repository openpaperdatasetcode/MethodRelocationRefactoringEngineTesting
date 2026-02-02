package com.refactor.movemethod;

import java.sql.SQLException;

// Source normal class (strictfp modifier, same package, two anonymous inner classes)
strictfp class SourceClass {
    // Instance field for access_instance_field feature
    private String instanceField = "source_instance_field";

    // First anonymous inner class (source_class feature)
    private final Runnable firstAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("First anonymous inner class");
        }
    };

    // Second anonymous inner class (source_class feature - duplicate as specified)
    private final Runnable secondAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous inner class");
        }
    };

    // Member inner class (method_position: source_inner)
    class SourceInnerClass {
        // Varargs method to refactor (protected access, base type return, target param - per_condition)
        protected int refactorMethod(TargetClass... targetParams) {
            // Variable call feature
            firstAnonymous.run();
            secondAnonymous.run();
            String varCall = SourceClass.this.instanceField;

            // Access_instance_field feature
            varCall += SourceClass.this.instanceField;
            int result = varCall.length();

            // Synchronized statement feature
            Object lock = new Object();
            synchronized (lock) {
                result += targetParams.length;
            }

            // If statement feature
            if (targetParams == null || targetParams.length == 0) {
                result = 0;
            } else {
                // SQLException feature (requires try-catch, no_new_exception - no explicit throw new)
                try {
                    if (targetParams[0].getLocalInnerValue() == null) {
                        throw new SQLException("Local inner value is null");
                    }
                } catch (SQLException e) {
                    result = e.getMessage().length();
                }
            }

            // No_new_exception feature (no explicit throw new Exception)
            return result; // Base type (int) return
        }
    }
}

// Target normal class (public modifier, local inner class target_feature)
public class TargetClass {
    private String data;

    public TargetClass(String data) {
        this.data = data;
        // Local inner class (target_feature)
        class TargetLocalInner {
            String getValue() {
                return data;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        this.data = localInner.getValue();
    }

    public String getLocalInnerValue() {
        return this.data;
    }
}

// Sub class for call_method (sub_class type, default modifier, static + ClassName.methodName(), if/else pos)
class SubClass extends SourceClass {
    // Call method (default modifier, sub_class type, if/else pos, returns TargetClass Type)
    TargetClass callMethod() {
        SourceInnerClass inner = new SourceInnerClass();
        TargetClass result = null;

        // If/else conditions position
        if (inner != null) {
            // Static feature + ClassName.methodName(arguments) target_feature
            int methodResult = inner.refactorMethod(new TargetClass("test"));
            result = new TargetClass(String.valueOf(methodResult));
        } else {
            result = new TargetClass("default");
        }
        return result;
    }

    // Static method for ClassName.methodName() feature
    public static TargetClass staticCallMethod() {
        SubClass sub = new SubClass();
        return sub.callMethod();
    }
}