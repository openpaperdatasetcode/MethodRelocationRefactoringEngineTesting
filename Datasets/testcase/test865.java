package com.refactoring.test;

// Source enum class (public modifier, same package, member inner + local inner class)
public enum SourceEnum {
    INSTANCE;

    private String outerPrivateField = "privateValue"; // For access_outer_private

    // Member inner class (source feature)
    public class SourceMemberInner {
        public Object innerMethod() {
            return outerPrivateField;
        }
    }

    // Method to be refactored (instance, base type return, default access, source position)
    int moveMethod(TargetEnum.TargetInner targetParam) {
        // Per_condition: contains target parameter
        if (targetParam == null) {
            throw new IllegalArgumentException("Target parameter is null"); // Exception throwing statements
        }

        // Nested normal method (type:normal, modifier:public, method_feature:1/source/normal/this.methodName(arguments), pos:exception throwing statements, return base type)
        public int nestedMethod(int num) {
            return this.moveMethodHelper(num, targetParam); // this.methodName(arguments)
        }

        // Variable call + access_outer_private
        String privateVal = this.outerPrivateField;
        int varCall = targetParam.innerField;
        targetParam.innerField = varCall + 1; // 1 from method_feature

        // Local inner class (source feature)
        class LocalInnerClass {
            int processTarget(TargetEnum.TargetInner target) {
                return target.innerField * 2;
            }
        }
        int processedVal = new LocalInnerClass().processTarget(targetParam);

        // Constructor with call_method in parameter list (pos: parameter list of constructors)
        InnerCallClass callInstance = new InnerCallClass(this.callMethod(targetParam));

        // No new exception, return base type
        return processedVal + nestedMethod(1);
    }

    // Helper method for nestedMethod
    private int moveMethodHelper(int num, TargetEnum.TargetInner target) {
        return num + target.innerField;
    }

    // Inner class for call_method (inner_class type)
    private class InnerCallClass {
        public InnerCallClass(Object param) {}

        // call_method (modifier:private, target_feature:normal/outerInstance.new InnerClass().methodName(), return Object)
        private Object callMethod(TargetEnum.TargetInner param) {
            // outerInstance.new InnerClass().methodName()
            SourceMemberInner inner = SourceEnum.INSTANCE.new SourceMemberInner();
            return inner.innerMethod() + param.innerField;
        }
    }
}

// Target enum class (default modifier, target_feature:member inner class)
enum TargetEnum {
    VALUE1, VALUE2;

    // Target inner class (target_inner)
    public class TargetInner {
        int innerField; // For variable call
    }
}