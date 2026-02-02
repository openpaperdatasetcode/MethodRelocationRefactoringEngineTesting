package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    String value() default "feature_1";
}

// Parent class for override_violation feature
class ParentSourceClass<T> {
    // Parent method with no throws clause
    public void parentMethod(TargetClass<T> target) {
        // Empty parent method
    }
}

// Source class: public normal class, same package as target, type parameter + anonymous inner + member inner class
public class SourceClass<T> extends ParentSourceClass<T> {
    // Per_condition: source contains target class field
    private final TargetClass<T> targetField = new TargetClass<>((T) "initial_target_1");

    // Member inner class (source feature)
    public class SourceInnerClass {
        public T processInner(T input) {
            return (T) (input.toString() + "_inner_1");
        }
    }

    // has_annotation feature
    @ProcessAnnotation("method_annotation_1")
    // Method to refactor: instance, void return, final access, in source
    public final void methodToRefactor(TargetClass<T> targetParam) throws Exception { // requires_throws
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            throw new IllegalArgumentException("Target parameter cannot be null"); // requires_throws
        }

        // Empty statement
        ; // empty statement

        // Variable call (target class)
        T targetValue = targetParam.getValue();
        
        // Anonymous inner class (source feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                targetParam.setValue((T) (targetValue + "_anonymous_1"));
            }
        };
        anonymous.run();

        // Member inner class usage
        SourceInnerClass innerHelper = new SourceInnerClass();
        T processedValue = innerHelper.processInner(targetValue);
        targetParam.setValue(processedValue);

        // Override_violation feature (base method no throws, override adds throws)
        @Override
        public void parentMethod(TargetClass<T> target) throws Exception { // override_violation
            target.setValue((T) (target.getValue() + "_override_1"));
        }

        // Variable call for targetField (per_condition)
        targetParam.setValue((T) (targetParam.getValue() + "_" + targetField.getValue()));

        // requires_throws verification
        if (targetParam.getValue() == null) {
            throw new Exception("Target value is null"); // requires_throws
        }
    }
}

// Target class: public normal class, anonymous inner class feature
public class TargetClass<T> {
    private T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
        // Anonymous inner class (target_feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                value = (T) (value + "_target_anonymous_1");
            }
        };
        anonymous.run();
    }

    // Variable call getters/setters
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}