package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Annotation for has_annotation feature
@interface AccessorMethodAnnotation {}

// Source class: generic abstract class, same package, empty features
abstract class SourceClass<T> {
    // Private field for access_outer_private feature
    private String outerPrivateField = "sourcePrivateVal";
    // per_condition: source contains target class field
    private TargetClass<T> targetField = new TargetClass<T>() {};

    // Method: accessor, return Object, public access, source position
    @AccessorMethodAnnotation // has_annotation feature
    public Object moveableAccessorMethod(TargetClass<T> targetParam) throws Exception { // per_condition: contains target parameter
        // Variable call feature
        Object localVar = targetParam.getTargetField();
        localVar = outerPrivateField;

        // Expression statement feature
        localVar = localVar.toString().toUpperCase(); // Expression statement
        targetParam.setTargetField((T) localVar); // Expression statement

        // Access_outer_private feature (access outer class's private field)
        localVar = SourceClass.this.outerPrivateField;

        // Call_method: inner_class, private modifier, normal, (parameters) -> methodBody, pos=for, return List<String>
        private List<String> callMethodLogic() {
            List<String> result = new ArrayList<>();
            // Lambda expression: (parameters) -> methodBody (target_feature)
            Function<T, String> lambdaFunc = (t) -> { // normal method body
                return t.toString() + "_lambda";
            };

            // pos=for (call_method position)
            for (int i = 0; i < 1; i++) {
                result.add(lambdaFunc.apply((T) localVar));
            }
            return result;
        }
        List<String> innerClassResult = callMethodLogic();
        localVar = innerClassResult;

        // Requires_throws feature (declare and throw checked exception)
        if (targetParam == null) {
            throw new Exception("Target parameter is null");
        }

        // no_new_exception (implicit: no custom exceptions instantiated)
        return localVar;
    }

    // Abstract method (required for abstract class)
    public abstract T abstractSourceMethod();
}

// Target class: generic abstract class, same package, target_feature: local inner class
abstract class TargetClass<U> {
    private U targetField;

    // Accessor methods (matches method type: accessor)
    public U getTargetField() {
        return targetField;
    }

    public void setTargetField(U targetField) {
        this.targetField = targetField;
    }

    // Local inner class (target_feature)
    public void demoLocalInnerClass() {
        class LocalInnerTarget<V> { // Local inner class
            V innerField;

            void process(U val) {
                innerField = (V) val;
            }
        }
        LocalInnerTarget<U> localInner = new LocalInnerTarget<>();
        localInner.process(targetField);
    }

    // Abstract method (required for abstract class)
    public abstract U abstractTargetMethod();
}