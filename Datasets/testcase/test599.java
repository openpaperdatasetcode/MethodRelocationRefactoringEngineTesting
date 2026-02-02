package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

// Super class for super constructor/method invocation
class SuperEnumClass {
    protected String superField = "superEnumValue";

    protected List<String> superMethod() {
        return new ArrayList<>();
    }
}

// Source class: enum, default modifier, same package as target, local inner class, member inner class
enum SourceEnum extends SuperEnumClass {
    INSTANCE;

    // Member inner class (source class feature)
    class SourceInnerClass {
        String innerValue = "innerEnumValue";
    }

    // Super constructor invocation (required for extending super class)
    SourceEnum() {
        super(); // super constructor invocation feature
    }

    // Method to refactor: varargs, List<String> return, private access, in source enum
    // Per_condition: contains target enum parameter
    private List<String> methodToRefactor(TargetEnum... targetParams) throws IOException { // requires_throws feature
        List<String> result = new ArrayList<>();
        
        // Variable call (target enum parameters)
        for (TargetEnum param : targetParams) {
            result.add(param.name());
        }

        // Uses_outer_this feature (access outer enum instance from inner context)
        class InnerContext {
            void addOuterValue() {
                result.add(SourceEnum.this.superField); // uses_outer_this
            }
        }
        new InnerContext().addOuterValue();

        // Static method feature: protected modifier, 2, source, static, super.methodName(), pos=array initialization, return List<String>
        protected static List<String> staticHelperMethod() {
            List<String> staticResult = new ArrayList<>();
            int count = 2; // method_feature: 2
            // pos: array initialization
            String[] arr = new String[]{"static1", "static2"};
            for (String s : arr) {
                staticResult.add(s);
            }
            // super.methodName() feature (call super class static method via instance)
            SuperEnumClass superObj = new SuperEnumClass();
            staticResult.addAll(superObj.superMethod());
            return staticResult;
        }
        result.addAll(staticHelperMethod());

        // Feature: numbers=3, modifier=protected, exp=PostfixExpression
        protected String postfixExpressionFeature() {
            int num = 3; // numbers=3
            num++; // PostfixExpression (num++)
            return String.valueOf(num);
        }
        result.add(postfixExpressionFeature());

        // Local inner class (source class feature)
        class LocalInnerClass {
            void processResult() {
                result.add("localInnerProcessed");
            }
        }
        new LocalInnerClass().processResult();

        // Requires_throws validation (throw IOException if empty)
        if (result.isEmpty()) {
            throw new IOException("Result list is empty");
        }

        return result;
    }

    // call_method: source type, public modifier, normal, new ClassName().method(), pos=object initialization, return int
    public int callMethod() {
        // pos: object initialization
        TargetEnum targetInstance = TargetEnum.VALUE1; // Object initialization of target enum
        List<String> methodResult;
        try {
            // new ClassName().method() feature (call refactor method via new enum instance logic)
            methodResult = SourceEnum.INSTANCE.methodToRefactor(targetInstance, TargetEnum.VALUE2);
        } catch (IOException e) {
            methodResult = new ArrayList<>();
        }
        return methodResult.size();
    }
}

// Target class: enum, public modifier, no target features, same package as source
public enum TargetEnum {
    VALUE1, VALUE2, VALUE3;
}