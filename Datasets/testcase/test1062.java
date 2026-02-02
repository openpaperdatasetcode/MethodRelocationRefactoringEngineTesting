package com.refactoring.test;

protected enum SourceEnum {
    INSTANCE;

    // Inner class (method_position: source_inner)
    class InnerSourceClass {
        // Method to be refactored (normal, void return, private access)
        @SuppressWarnings("unused")
        private void targetMethod(TargetEnum.InnerTargetEnum param) { // per_condition: contains target parameter
            // Variable call
            String targetValue = param.getValue();
            
            // ClassInstanceCreation (numbers:1, public modifier, exp:ClassInstanceCreation)
            public TargetEnum.InnerTargetEnum instance = new TargetEnum.InnerTargetEnum(); // 1
            
            // Property assignment with instance method (pos: property assignment)
            TargetEnum.InnerTargetEnum assigned = instanceMethod(1); // 1 in method_feature
            
            // ThrowStatement (protected modifier, super.field, 3, pos: same_package_target)
            try {
                if (param == null) {
                    protected String superField = super.toString(); // super.field
                    throw new IllegalArgumentException(superField + 3); // 3
                }
            } catch (Exception e) {
                // No new exception (no_new_exception)
                return; // return statement
            }
            
            // Override violation (attempt to override final method)
            @Override // Compile error (override violation)
            public final String toString() {
                return super.toString();
            }
        }

        // Instance method (private modifier, return TargetClass Type, parent_class, super.methodName())
        private TargetEnum.InnerTargetEnum instanceMethod(int num) { // 1 in method_feature
            super.toString(); // super.methodName() (parent_class feature)
            return TargetEnum.InnerTargetEnum.INSTANCE;
        }
    }
}

// Target enum (abstract modifier, anonymous inner class target_feature)
abstract enum TargetEnum {
    // Anonymous inner class (target_feature)
    INSTANCE {
        @Override
        public String getValue() {
            return "target";
        }
    };

    // Inner target enum (target class: target_inner)
    enum InnerTargetEnum {
        INSTANCE;

        public String getValue() {
            return "innerTarget";
        }
    }

    public abstract String getValue();
}