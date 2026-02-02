package com.refactor;

import java.util.Objects;

// Target class: public normal class, static nested class feature
public class TargetClass {
    private int value;

    // Static nested class (target_feature)
    public static class StaticNestedClass {
        public static int processValue(int val) {
            return val * 2;
        }

        public static void validateTarget(TargetClass target) {
            if (target == null) throw new IllegalArgumentException("Target cannot be null");
        }
    }

    // Instance method for access_instance_method feature
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

// Parent class for sub_class call_method feature
class ParentSourceClass {
    protected TargetClass targetField;

    public ParentSourceClass(TargetClass target) {
        this.targetField = target;
    }

    // Method to be overridden in sub_class
    protected int baseProcess(TargetClass target) {
        return target.getValue();
    }
}

// Sub class for call_method (sub_class type)
class SubSourceClass extends ParentSourceClass {
    // call_method: public modifier, constructor + ClassName.methodName(arguments), pos: parameter list of constructors, returns int
    public SubSourceClass() {
        // Parameter list of constructors pos: ClassName.methodName(arguments)
        super(new TargetClass() {{
            setValue(TargetClass.StaticNestedClass.processValue(5));
        }});
    }

    // Overridden method with call_method logic
    @Override
    public int baseProcess(TargetClass target) {
        // Constructor feature: create new TargetClass instance
        TargetClass newTarget = new TargetClass();
        newTarget.setValue(target.getValue() + 3);
        // ClassName.methodName(arguments)
        return TargetClass.StaticNestedClass.processValue(newTarget.getValue());
    }
}

// Source class: default modifier, same package as target, anonymous inner + local inner class
class SourceClass {
    // Local inner class (source_inner_rec hierarchy)
    public void outerMethod() {
        class FirstLocalInner {
            // Inner recursive class for source_inner_rec method position
            class InnerRecursiveClass {
                // Method: instance, base type (int) return, private access, source_inner_rec position, method types parameter is:none
                private int processTarget(TargetClass targetParam) {
                    // Type declaration statement
                    TargetClass target;
                    int result = 0;

                    // Variable call (target parameter)
                    target = targetParam;

                    // Validate target (access_instance_method)
                    TargetClass.StaticNestedClass.validateTarget(target);

                    // Loop with continue statement
                    for (int i = 0; i < 10; i++) {
                        if (i % 2 == 0) {
                            continue; // continue statement
                        }
                        // Access instance method of target class
                        target.setValue(target.getValue() + i);
                        result = target.getValue();
                    }

                    // Anonymous inner class feature
                    Runnable anonymousInner = new Runnable() {
                        @Override
                        public void run() {
                            target.setValue(result * 2);
                        }
                    };
                    anonymousInner.run();

                    // Call sub_class method (call_method integration)
                    SubSourceClass subClass = new SubSourceClass();
                    result = subClass.baseProcess(target);

                    // No new exception - wrap existing if any
                    try {
                        return result;
                    } catch (Exception e) {
                        throw new RuntimeException(e); // No new exception instantiation
                    }
                }
            }

            // Call inner recursive class method
            int callInnerRecursive(TargetClass target) {
                InnerRecursiveClass innerRec = new InnerRecursiveClass();
                return innerRec.processTarget(target);
            }
        }

        // Local inner class usage
        FirstLocalInner localInner = new FirstLocalInner();
        TargetClass target = new TargetClass();
        target.setValue(10);
        // per_condition: method contains parameter of target
        localInner.callInnerRecursive(target);
    }

    // Instance method to trigger processing
    public int triggerProcessing(TargetClass target) {
        outerMethod();
        // Call sub_class call_method
        SubSourceClass subClass = new SubSourceClass();
        return subClass.baseProcess(target);
    }
}