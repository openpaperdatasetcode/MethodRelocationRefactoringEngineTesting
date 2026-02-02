package com.refactoring.test;

import java.util.function.Function;

// Interface for source class implements feature
interface SourceInterface {
    <T> Object process(T param);
}

// Parent class for target class extends feature
class TargetParentClass {
    protected String parentField = "parentValue";
}

// Source class (final modifier, same package, implements + local inner + anonymous inner class)
final class SourceClass implements SourceInterface {
    private String outerPrivateField = "privateOuterValue"; // For access_outer_private
    private int instanceField = 100;

    // Instance method for access_instance_method feature
    private int getInstanceValue() {
        return this.instanceField;
    }

    @Override
    public <T> Object process(T param) {
        return param;
    }

    // Method to be refactored (generic, Object return, private access, source position)
    private <T> Object moveMethod(TargetClass targetParam) {
        // Per_condition: source contains target's field
        String targetField = targetParam.targetField;

        // Constructor invocation
        TargetClass targetInstance = new TargetClass("newTargetValue");
        TargetParentClass parentInstance = new TargetParentClass();

        // this(arguments) feature
        SourceInnerClass innerInstance = new SourceInnerClass(this.getInstanceValue());

        // Access outer private field
        String privateVal = SourceClass.this.outerPrivateField;

        // Access instance method
        int instanceVal = this.getInstanceValue();

        // Variable call
        targetParam.targetField = targetField + "_" + privateVal + "_" + instanceVal;

        // Local inner class (source feature)
        class LocalInnerClass {
            String modifyTarget(String input) {
                return input + "_localModified";
            }
        }
        String modifiedVal = new LocalInnerClass().modifyTarget(targetParam.targetField);
        targetParam.targetField = modifiedVal;

        // Anonymous inner class (source feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetParam.targetField);
            }
        };
        anonymousInner.run();

        // Array initialization with call_method (pos:array initialization)
        String[] resultArray = {new InnerCallClass().callMethod(targetParam)};

        // No new exception
        return resultArray[0];
    }

    // Inner class for this(arguments)
    class SourceInnerClass {
        private int innerVal;

        public SourceInnerClass(int val) {
            this.innerVal = val;
        }
    }

    // Inner class for call_method (inner_class type)
    class InnerCallClass {
        // call_method (private modifier, accessor + instanceReference::methodName)
        private String callMethod(TargetClass param) {
            // Accessor feature (getter) + instanceReference::methodName
            Function<TargetClass, String> accessor = TargetClass::getTargetField;
            return accessor.apply(param) + "_accessed";
        }
    }
}

// Target class (protected modifier, extends + local inner class)
protected class TargetClass extends TargetParentClass {
    String targetField; // For variable call/access (per_condition)

    public TargetClass(String targetField) {
        this.targetField = targetField;
    }

    // Accessor method (getter) for call_method accessor feature
    public String getTargetField() {
        // Local inner class (target_feature)
        class TargetLocalInner {
            String formatField(String field) {
                return "formatted_" + field;
            }
        }
        return new TargetLocalInner().formatField(this.targetField);
    }

    // Instance method for access_instance_method feature
    public void setTargetField(String value) {
        this.targetField = value;
    }
}