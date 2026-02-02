package com.refactoring.movemethod;

// Source enum class (public modifier, same package as target, type parameter feature)
public enum SourceEnum<T extends CharSequence> {
    INSTANCE1, INSTANCE2;

    // Private outer field for access_outer_private feature
    private String outerPrivateField = "source_outer_private";

    // Overloading method 1 (base for overloading feature)
    private TargetEnum overloadedMethod(String param) {
        return TargetEnum.TARGET1;
    }

    /**
     * Method Javadoc feature - Overloading method to refactor
     * Processes target enum parameters and returns TargetEnum type
     * @param targetParams TargetEnum varargs parameter (per_condition fulfillment)
     * @return TargetEnum instance
     */
    // Overloading method 2 (overloading feature, private access, returns TargetEnum type)
    private TargetEnum overloadedMethod(TargetEnum... targetParams) {
        // Override_violation: simulate incompatible override (no actual super, intentional signature conflict)
        @SuppressWarnings("unused")
        String invalidOverride = "";

        // Uses_outer_this feature
        SourceEnum<T> outerThis = SourceEnum.this;

        // Access_outer_private feature
        String privateVal = outerThis.outerPrivateField;

        // Enhanced for statement feature
        for (TargetEnum targetParam : targetParams) {
            // NullPointerException feature
            if (targetParam == null) {
                throw new NullPointerException("Target parameter is null");
            }

            // Variable call feature
            TargetEnum.target_inner innerObj = targetParam.new target_inner();
            innerObj.setData(privateVal + "_" + targetParam.name());

            // Continue statement feature (conditional continue)
            if (targetParam == TargetEnum.TARGET2) {
                continue;
            }

            innerObj.processData();
        }

        // Return TargetClass Type (TargetEnum)
        return TargetEnum.TARGET1;
    }
}

// Target enum class (default modifier, same package as source)
/**
 * Javadoc feature for target class
 * Contains anonymous inner class and target_inner nested class
 */
enum TargetEnum {
    TARGET1 {
        // Anonymous inner class feature (target_feature)
        @Override
        public target_inner createInner() {
            return new target_inner() {
                @Override
                public void processData() {
                    System.out.println("Anonymous inner processing for TARGET1");
                }
            };
        }
    },
    TARGET2 {
        @Override
        public target_inner createInner() {
            return new target_inner();
        }
    };

    // Abstract method for anonymous inner class implementation
    public abstract target_inner createInner();

    // Target inner class (target_inner - target class for method)
    public class target_inner {
        private String data;

        public void setData(String data) {
            this.data = data;
        }

        public void processData() {
            System.out.println("Processing data: " + this.data);
        }
    }
}