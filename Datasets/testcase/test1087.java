package com.example;

import java.lang.reflect.Method;
import java.util.List;

// Source class (default modifier, normal class, same package, member inner class, anonymous inner class)
class SourceClass {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "outerProtectedValue";

    // Member inner class (source_class feature)
    class MemberInnerClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in source");
        }
    };

    // Method to be refactored (varargs, void return, final access, position: source)
    final void targetMethod(AbstractTargetClass param, String... args) { // per_condition: target parameter
        // Super constructor invocation
        super();

        // Super keywords usage
        super.toString();

        // ConditionalExpression (numbers:3, public modifier, exp:ConditionalExpression)
        public int conditionalExpr() {
            int num = 3; // numbers:3
            return num > 0 ? 1 : 0; // ConditionalExpression
        }
        int condResult = conditionalExpr();

        // Variable call
        String targetValue = param.getValue();

        // Access outer protected field
        String protectedValue = SourceClass.this.outerProtectedField;

        // Raw type usage
        List rawList = new java.util.ArrayList(); // raw_type
        rawList.add(targetValue + protectedValue);

        // Depends on inner class (anonymous inner class logic)
        anonymousInner.run();

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod", AbstractTargetClass.class, String[].class);
            method.setAccessible(true);
            method.invoke(this, param, (Object) args);
        } catch (Exception e) {
            // No new exception
            System.err.println("Reflection error: " + e.getMessage());
        }

        // No new exception
    }
}

// Target class (abstract modifier, normal class, empty target_feature)
abstract class AbstractTargetClass {
    private String value = "targetValue";

    public String getValue() {
        return value;
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}