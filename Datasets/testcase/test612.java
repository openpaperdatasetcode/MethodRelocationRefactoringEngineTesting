package com.refactoring.movemethod;

// Source class: normal, protected modifier, same package as target, member inner + static nested class
protected class SourceClass {
    // Static field for depends_on_static_field feature
    private static int staticField = 1; // align with numbers=1

    // Per_condition: source contains target class field
    private TargetClass targetField = TargetClass.createInstance();

    // Static nested class (source class feature)
    static class SourceStaticNested {
        public static String processTarget(TargetClass target) {
            return target.getStaticNestedValue() + "_" + staticField;
        }
    }

    // Member inner class (source class feature)
    class SourceInnerClass {
        public String innerField = "source_inner_field_" + staticField;
    }

    // Overloading method 1 (method type: overloading)
    protected TargetClass methodToRefactor(TargetClass targetParam) {
        // Variable call (target parameter and field)
        String targetValue = targetParam.getStaticNestedValue();
        targetValue += targetField.getStaticNestedValue();

        // Feature: numbers=1, modifier=public, exp=PrefixExpression
        public String prefixExpressionFeature() {
            int num = 1; // numbers=1
            ++num; // PrefixExpression (++num) - exp=PrefixExpression
            return "prefix_" + num + "_" + staticField;
        }
        targetValue += prefixExpressionFeature();

        // Depends on static field (use source static field)
        targetParam.setTempValue(targetValue + "_static_" + SourceClass.staticField);

        // No new exception feature
        try {
            Integer.parseInt(targetValue);
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            targetParam.setTempValue("formatted_" + targetValue);
        }

        return targetParam; // Return TargetClass Type
    }

    // Overloading method 2 (overload_exist feature)
    protected TargetClass methodToRefactor(TargetClass targetParam, String extra) {
        TargetClass baseResult = methodToRefactor(targetParam);
        baseResult.setTempValue(baseResult.getTempValue() + extra);
        return baseResult;
    }
}

// Target class: normal, abstract modifier, same package as source, static nested class feature
abstract class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static final int NESTED_STATIC_FIELD = 1; // align with numbers=1
        public static String getValue() {
            return "target_static_nested_" + NESTED_STATIC_FIELD;
        }
    }

    private String tempValue;

    // Factory method for instance creation (abstract class can't be instantiated directly)
    public static TargetClass createInstance() {
        return new TargetClass() {
            @Override
            public String getStaticNestedValue() {
                return TargetStaticNested.getValue();
            }
        };
    }

    // Abstract method (required for abstract class)
    public abstract String getStaticNestedValue();

    // Getter/Setter for temp value
    public String getTempValue() {
        return tempValue;
    }

    public void setTempValue(String value) {
        this.tempValue = value;
    }
}