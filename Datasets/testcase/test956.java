package com.refactoring.movemethod;

class SourceClass {
    // Two static nested classes (source feature)
    static class SourceStaticNested1 {}
    static class SourceStaticNested2 {}

    // Source inner class (method_position: source_inner)
    class SourceInnerClass {
        // Target field reference (per_condition: source contains target field)
        private TargetClass.TargetMemberInner targetFieldRef;

        /**
         * Overloading method to test Move Method refactoring
         * @param targetParam Target class instance
         * @return Base type (int) result
         */
        int processTarget(TargetClass targetParam) {
            return processTarget(targetParam, 0);
        }

        /**
         * Overloaded method (overloading type)
         * @param targetParam Target class instance
         * @param defaultValue Default integer value
         * @return Base type (int) result
         */
        int processTarget(TargetClass targetParam, int defaultValue) {
            int result = defaultValue;
            
            // Variable call (target field access)
            targetFieldRef = targetParam.new TargetMemberInner();
            // Access instance field of target
            targetFieldRef.instanceField = 1;
            
            // Super constructor invocation (via anonymous subclass)
            SuperClass superObj = new SuperClass() {};

            // NullPointerException handling
            if (targetParam == null) {
                throw new NullPointerException("Target parameter is null");
            }

            // Expression statement
            result = targetFieldRef.instanceField + 1;

            // Property assignment (pos for instance method)
            targetFieldRef.instanceField = instanceMethod(targetParam);

            // No new exception (empty try-catch)
            try {
                // No new exception thrown
                result += targetFieldRef.instanceField;
            } catch (Exception e) {
                result = -1;
            }

            return result;
        }

        // Instance method (default modifier, TargetClass Type return, property assignment pos)
        TargetClass.TargetMemberInner instanceMethod(TargetClass target) {
            // 1: literal value usage
            int literal = 1;
            // outerInstance.new InnerClass().methodName()
            TargetClass.TargetMemberInner inner = target.new TargetMemberInner();
            inner.setInstanceField(literal);
            return inner;
        }

        // call_method: inner_class, protected modifier, static feature, instanceReference.methodName, static code blocks pos
        protected static void callMethod(TargetClass.TargetMemberInner inner) {
            // Static code blocks (pos)
            static {
                // instanceReference.methodName(arguments)
                inner.setInstanceField(inner.getInstanceField() + 1);
            }
        }
    }

    // Static code block to trigger call_method
    static {
        SourceClass source = new SourceClass();
        SourceInnerClass inner = source.new SourceInnerClass();
        TargetClass target = new TargetClass();
        TargetClass.TargetMemberInner targetInner = target.new TargetMemberInner();
        SourceInnerClass.callMethod(targetInner);
    }
}

class TargetClass {
    // Member inner class (target feature)
    class TargetMemberInner {
        // Instance field for access
        int instanceField;

        public int getInstanceField() {
            return instanceField;
        }

        public void setInstanceField(int instanceField) {
            this.instanceField = instanceField;
        }
    }
}

// Super class for super constructor invocation
class SuperClass {
    public SuperClass() {}
}