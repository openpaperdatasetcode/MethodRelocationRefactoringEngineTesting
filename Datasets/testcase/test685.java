package com.refactoring.movemethod;

// Parent class for source class extends feature
class ParentSourceClass {
    protected String parentField;

    public ParentSourceClass(String value) {
        this.parentField = value; // super constructor invocation base
    }

    public String parentMethod() {
        return "parent_1"; // method_feature: 1
    }
}

// Interface for source class implements feature
interface SourceInterface {
    void interfaceMethod();
}

// Subclass for method_feature: sub_class
class TargetSubClass extends TargetClass {
    public TargetSubClass(String value) {
        super(value);
    }

    @Override
    public TargetInnerRec new TargetInnerRec() {
        return new TargetInnerRec("sub_inner_1"); // method_feature: 1
    }
}

// Source class: protected normal class, same package as target, extends + implements + local inner + static nested class
protected class SourceClass extends ParentSourceClass implements SourceInterface {
    // Instance field for access_instance_field feature
    private String instanceField = "instance_1"; // method_feature: 1

    // Per_condition: source contains target class field
    private final TargetClass targetField = new TargetClass("initial_target_1");

    // Static nested class (source feature)
    static class SourceStaticNested {
        public static String staticMethod() {
            return "static_nested_1"; // method_feature: 1
        }
    }

    // Inner class for call_method feature
    public class SourceInnerClass {
        // call_method: inner_class type, final modifier, static, OuterClass.InnerClass.methodName(), pos=if/else conditions, return String
        public final String callMethod(TargetClass.TargetInnerRec inner) {
            String result = "";
            // pos: if/else conditions
            if (inner.getInnerValue() != null) {
                // OuterClass.InnerClass.methodName() + static feature
                result = SourceClass.SourceStaticNested.staticMethod(); // static feature
            } else {
                result = "else_1"; // method_feature: 1
            }
            return result;
        }
    }

    // Super constructor invocation (source class constructor)
    public SourceClass() {
        super("super_ctor_1"); // super constructor invocation, method_feature: 1
    }

    // Constructor feature: public modifier, 1, sub_class, constructor, new ClassName().method(), pos=exception throwing statements, return TargetClass Type
    public TargetClass constructorFeatureMethod() throws IllegalArgumentException {
        try {
            // pos: exception throwing statements
            if (instanceField == null) {
                throw new IllegalArgumentException("Instance field is null"); // throw statement
            }
            // new ClassName().method() + sub_class + constructor
            TargetClass target = new TargetSubClass("sub_target_1"); // sub_class + constructor
            target.new TargetInnerRec().innerMethod(); // new ClassName().method()
            return target; // return TargetClass Type
        } catch (IllegalArgumentException e) {
            throw e; // throw statement
        }
    }

    // Method to refactor: varargs, void return, strictfp access, in source
    public strictfp void methodToRefactor(TargetClass.TargetInnerRec... innerParams) {
        // Per_condition: method contains target parameter (varargs)
        if (innerParams == null || innerParams.length == 0) {
            return;
        }

        // Type declaration statement
        class ProcessedType {
            private String value;
            ProcessedType(String val) { this.value = val; }
            String getVal() { return value; }
        }

        // Local inner class (source feature)
        class LocalInnerProcessor {
            void process(TargetClass.TargetInnerRec inner) {
                // Access_instance_field feature
                inner.setInnerValue(inner.getInnerValue() + "_" + SourceClass.this.instanceField);
            }
        }

        for (TargetClass.TargetInnerRec inner : innerParams) {
            // Assert statement
            assert inner.getInnerValue() != null : "Inner value cannot be null"; // method_feature: 1

            // Constructor feature call (exception throwing statements)
            try {
                TargetClass newTarget = constructorFeatureMethod();
            } catch (IllegalArgumentException e) {
                // No_new_exception feature (no throw new exception, handle only)
                inner.setInnerValue("error_handling_1"); // method_feature: 1
            }

            // Variable call (target inner recursive class)
            String innerValue = inner.getInnerValue();
            inner.setInnerValue(innerValue + "_processed_1"); // method_feature: 1

            // Access_instance_field feature
            inner.setInnerValue(inner.getInnerValue() + "_" + this.instanceField);

            // Local inner class usage
            new LocalInnerProcessor().process(inner);

            // Call call_method (inner_class type)
            String callResult = new SourceInnerClass().callMethod(inner);
            inner.setInnerValue(inner.getInnerValue() + "_" + callResult);
        }
    }

    // Implement interface method
    @Override
    public void interfaceMethod() {
        // Empty implementation for interface
    }
}

// Target class: private normal class, local inner class feature
private class TargetClass {
    private String value;

    public TargetClass(String initialValue) {
        this.value = initialValue;
    }

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private String innerValue;

        public TargetInnerRec(String value) {
            this.innerValue = value;
        }

        // Local inner class (target_feature)
        public void innerMethod() {
            class TargetLocalInner {
                String process(String input) {
                    return input + "_local_inner_1"; // method_feature: 1
                }
            }
            this.innerValue = new TargetLocalInner().process(this.innerValue);
        }

        // Variable call getters/setters
        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Getter for target field (per_condition variable call)
    public TargetInnerRec new TargetInnerRec() {
        return new TargetInnerRec(this.value);
    }

    public String getValue() {
        return value;
    }
}