package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation and call_method pos feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {
    String value() default "6596_1"; // numbers=1, id=6596
    int callMethodParam() default 1; // target_feature for call_method pos
}

// Parent class for super constructor invocation
class ParentSourceClass {
    protected String parentField;

    public ParentSourceClass(String value) {
        this.parentField = value; // super constructor invocation base
    }

    protected String superMethod() {
        return "super_method_1"; // numbers=1
    }
}

// Source class: protected normal class, same package as target, static nested + local inner class
protected class SourceClass extends ParentSourceClass {
    // Outer private field for access_outer_private feature
    private final String outerPrivateField = "outer_private_1"; // numbers=1, id=6596

    // Per_condition: source contains target class field
    private final TargetClass targetField = new TargetClass("initial_target_1"); // numbers=1

    // Static nested class (source feature)
    public static class SourceStaticNested {
        public static String staticMethod() {
            return "static_nested_1"; // numbers=1
        }
    }

    // Static code blocks for recursion pos feature
    static {
        SourceClass source = new SourceClass();
        SourceInnerClass inner = source.new SourceInnerClass();
        // pos: Static code blocks, instanceReference.methodName()
        Object recursionResult = inner.recursiveMethod(source.targetField.getInnerRec(), 1); // numbers=1
    }

    // Inner class for recursion and call_method feature
    public class SourceInnerClass {
        // recursion feature: public modifier, 1, inner_class, recursion, instanceReference.methodName(), pos=Static code blocks, return Object
        public Object recursiveMethod(TargetClass.TargetInnerRec inner, int depth) {
            // Recursion base case
            if (depth <= 0) {
                return inner.getInnerValue();
            }
            // instanceReference.methodName() + recursion
            return recursiveMethod(inner, depth - 1); // numbers=1
        }

        // call_method: inner_class type, private modifier, constructor, superTypeReference.methodName(), pos=annotation attribute, return int
        @RefactorAnnotation(callMethodParam = 1) // pos: the attribute values of annotations (numbers=1)
        private int callMethod(TargetClass.TargetInnerRec inner) {
            // constructor feature (target inner class constructor)
            TargetClass.TargetInnerRec newInner = new TargetClass("call_target_1").new TargetInnerRec(); // numbers=1
            // superTypeReference.methodName() feature
            String superValue = ParentSourceClass.super.superMethod();
            return newInner.getInnerValue().length() + superValue.length() + 1; // numbers=1
        }
    }

    // Super constructor invocation (source class constructor)
    public SourceClass() {
        super("super_ctor_1"); // numbers=1, super constructor invocation
    }

    // numbers=1, modifier=volatile, exp=VariableDeclarationExpression
    private volatile int volatileVar = 1; // numbers=1, volatile modifier, VariableDeclarationExpression

    // has_annotation feature
    @RefactorAnnotation("method_6596_1") // numbers=1, id=6596
    // Method to refactor: varargs, List<String> return, private access, in source
    private List<String> methodToRefactor(TargetClass.TargetInnerRec... innerParams) throws Exception { // requires_throws
        // Per_condition: method contains target parameter (varargs)
        if (innerParams == null || innerParams.length == 0) {
            throw new IllegalArgumentException("No target parameters provided"); // requires_throws
        }

        List<String> result = new ArrayList<>();
        int count = 0;

        // Do statement
        do {
            // Local inner class (source feature)
            class LocalInnerProcessor {
                String process(String input) {
                    return input + "_local_inner_1"; // numbers=1
                }
            }

            for (TargetClass.TargetInnerRec inner : innerParams) {
                // assert statement
                assert inner.getInnerValue() != null : "Inner value cannot be null (6596)"; // id=6596

                // Variable call (target inner recursive class)
                String innerValue = inner.getInnerValue();
                
                // access_outer_private feature (access outer private field)
                innerValue += "_" + SourceClass.this.outerPrivateField; // numbers=1

                // raw_type feature (use raw TargetClass)
                TargetClass rawTarget = new TargetClass("raw_type_1"); // numbers=1

                // Local inner class usage
                LocalInnerProcessor processor = new LocalInnerProcessor();
                innerValue = processor.process(innerValue);

                // Volatile variable usage (VariableDeclarationExpression)
                innerValue += "_volatile_" + SourceClass.this.volatileVar; // numbers=1

                // Recursion feature call (inner_class)
                SourceInnerClass innerHelper = new SourceInnerClass();
                Object recursionResult = innerHelper.recursiveMethod(inner, 1); // numbers=1
                result.add(recursionResult.toString());

                // Call overloaded method (overload_exist)
                result.addAll(methodToRefactor(innerParams, "extra_1")); // numbers=1

                // Call call_method (inner_class type)
                int callResult = innerHelper.callMethod(inner);
                result.add("call_result_" + callResult);

                // requires_try_catch (implicit via requires_throws)
                try {
                    Integer.parseInt(innerValue);
                } catch (NumberFormatException e) {
                    result.add("format_error_1"); // numbers=1
                }

                result.add(innerValue);
                count++;
            }
        } while (count < 1); // numbers=1

        // Variable call for targetField (per_condition)
        result.add(targetField.getInnerRec().getInnerValue() + "_field_6596_1"); // numbers=1, id=6596

        return result;
    }

    // Overloaded method (overload_exist feature)
    private List<String> methodToRefactor(TargetClass.TargetInnerRec[] innerParams, String extra) {
        List<String> result = new ArrayList<>();
        for (TargetClass.TargetInnerRec inner : innerParams) {
            result.add(inner.getInnerValue() + "_overload_" + extra);
        }
        return result;
    }
}

/**
 * Target class: default modifier, javadoc + local inner class features (id=6596)
 * Javadoc feature (target_feature)
 */
class TargetClass {
    private String value;

    public TargetClass(String initialValue) {
        this.value = initialValue;
    }

    /**
     * Target inner recursive class (javadoc feature)
     * Local inner class feature is implemented in processInner method
     */
    public class TargetInnerRec {
        private String innerValue;

        public TargetInnerRec() {
            this.innerValue = "inner_rec_1"; // numbers=1
        }

        /**
         * Process inner value with local inner class (target_feature: local inner class)
         */
        public String processInner() {
            // Local inner class (target_feature)
            class TargetLocalInner {
                String process(String input) {
                    return input + "_target_local_1"; // numbers=1
                }
            }
            return new TargetLocalInner().process(this.innerValue);
        }

        // Variable call getters/setters
        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Getter for inner recursive class (per_condition variable call)
    public TargetInnerRec getInnerRec() {
        return new TargetInnerRec();
    }

    public String getValue() {
        return value;
    }
}