package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    String value() default "processed";
}

// Base class for override_violation feature
abstract class BaseMethodClass {
    // Base method with protected access and Object return type (for override_violation)
    protected Object processTarget(Object target) /* no throws */;
}

// Source class: private normal class, same package as target, anonymous inner + member inner class
private class SourceClass extends BaseMethodClass {
    // Member inner class (source_inner - method_position)
    public class SourceInnerClass {
        /**
         * Method to refactor: instance, TargetClass return, public access, in source_inner
         * @param targetParam Target abstract class instance
         * @return Processed TargetClass instance
         */
        @ProcessAnnotation("source_method") // has_annotation feature
        public TargetClass methodToRefactor(TargetClass targetParam) {
            // Per_condition: method contains target parameter
            if (targetParam == null) {
                targetParam = new TargetClassImpl(); // Fallback constructor invocation
            }

            // Variable call (target class and inner class)
            TargetClass.TargetInner inner = targetParam.createInner();
            inner.setInnerValue("source_processed");
            String innerValue = inner.getInnerValue();

            // override_violation feature (base method: protected Object, override: public TargetClass)
            @Override
            public TargetClass processTarget(Object target) { // Access modifier + return type violation
                return (TargetClass) target;
            }

            // No_new_exception feature
            try {
                // Simulate potential exception
                Integer.parseInt(innerValue);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                inner.setInnerValue("default_value");
            }

            // Anonymous inner class (source feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    // Variable call in anonymous inner class
                    System.out.println("Anonymous inner: " + inner.getInnerValue());
                }
            };
            anonymous.run();

            // Return TargetClass Type
            return targetParam;
        }
    }

    // Default implementation of base method (override_violation fallback)
    @Override
    protected Object processTarget(Object target) {
        return target;
    }
}

// Target class: abstract normal class, member inner class feature
abstract class TargetClass {
    // Member inner class (target_feature)
    public class TargetInner {
        private String innerValue;

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Abstract method for inner class creation
    public abstract TargetInner createInner();
}

// Concrete implementation of target abstract class (for instantiation)
class TargetClassImpl extends TargetClass {
    @Override
    public TargetInner createInner() {
        return new TargetInner();
    }
}