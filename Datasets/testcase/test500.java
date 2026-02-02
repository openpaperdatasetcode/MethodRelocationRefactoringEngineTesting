package com.refactor.movemethod;

import java.sql.SQLException;
import java.util.function.Function;

// Parent class for source_class extends feature
class SourceParentClass {
    protected String parentMethod() {
        return "parent_value_3"; // Number 3 for method_feature
    }
}

// Source normal class (strictfp modifier, same package, extends + local inner + static nested class)
strictfp class SourceClass extends SourceParentClass {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "static_value_3"; // Number 3 for method_feature

    // Static nested class (source_class feature)
    static class SourceStaticNested {
        String nestedData = "static_nested";
        public String getNestedData() { return nestedData; }
    }

    // Instance method to refactor (default access, returns Object, source position)
    Object refactorMethod() {
        // Variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        String varCall = staticNested.getNestedData();

        // Local inner class (source_class feature)
        class SourceLocalInner {
            String processValue(String val) {
                // Uses_outer_this feature
                return SourceClass.this.varCall + "_processed_" + 3; // Number 3 for method_feature
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        varCall = localInner.processValue(varCall);

        // Depends_on_static_field feature
        varCall += STATIC_FIELD;

        // Instance method (public modifier, 3 + target + instance + super.methodName(), expression pos)
        public TargetClass instanceMethod() {
            // Super.methodName() target_feature + Number 3
            String superVal = super.parentMethod();
            // Expression position + target feature
            targetField.setData(superVal + "_" + 3);
            // Return TargetClass Type
            return targetField;
        }

        // SQLException feature (no_new_exception - no explicit throw new)
        try {
            if (varCall.length() < 3) { // Number 3 for method_feature
                throw new SQLException("Invalid length (expected >=3): " + varCall);
            }
        } catch (SQLException e) {
            varCall = e.getMessage() + "_3"; // Number 3 for method_feature
        }

        // Uses_outer_this feature
        SourceClass outerThis = SourceClass.this;
        varCall += outerThis.STATIC_FIELD;

        // No_new_exception feature (no explicit throw new Exception)
        return instanceMethod(); // Return TargetClass Type via instance method
    }

    // Inner class for call_method (inner_class type)
    private class SourceInnerClass {
        // Instance code block for call_method pos
        {
            callMethod(); // Instance code blocks position
        }

        // Call method (private modifier, inner_class type, normal + lambda, instance code blocks pos)
        private TargetClass callMethod() {
            // (parameters) -> methodBody target_feature
            Function<SourceClass, TargetClass> lambda = (source) -> {
                // Normal feature (standard instance method call)
                return (TargetClass) source.refactorMethod();
            };
            // Execute lambda and return TargetClass Type
            return lambda.apply(SourceClass.this);
        }
    }
}

// Target normal class (default modifier, anonymous inner class target_feature)
class TargetClass {
    private String data;

    public TargetClass() {
        // Anonymous inner class (target_feature)
        Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                data = "target_anonymous_3"; // Number 3 for method_feature
            }
        };
        targetAnonymous.run();
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}