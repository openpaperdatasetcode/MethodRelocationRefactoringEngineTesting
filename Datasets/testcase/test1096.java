package com.example;

import java.lang.annotation.*;

// Source annotation interface (no modifier, same package, extends, anonymous inner class, member inner class)
@interface SourceAnnotation extends CustomAnnotation {
    // Member inner class (source_class feature)
    class MemberInnerClass {
        // Call method (inner_class, protected modifier, normal, new ClassName().method(), return String)
        protected String callMethod(TargetAnnotation.InnerTargetClass param) { // pos: expression
            return new HelperClass().methodName(param); // new ClassName().method()
        }

        // Helper class for new ClassName().method()
        class HelperClass {
            String methodName(TargetAnnotation.InnerTargetClass param) {
                return param.value();
            }
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in source annotation");
        }
    };

    // Accessor method (final access, TargetAnnotation return, source position)
    final TargetAnnotation targetMethod(TargetAnnotation.InnerTargetClass param) default null; // per_condition: target parameter

    // SuperConstructorInvocation (private modifier, super.field, 1, pos: source)
    private static class SuperConstructorClass extends SuperAnnotation {
        SuperConstructorClass() {
            super(); // SuperConstructorInvocation
            String superField = super.superField; // super.field
            int num = 1; // target_feature: 1
        }
    }

    // Super constructor invocation (duplicate feature)
    @SuppressWarnings("unused")
    static class AnotherSuperClass extends SuperAnnotation {
        AnotherSuperClass() {
            super(); // super constuctor invocation
        }
    }

    // InstanceofExpression (numbers:2, default modifier, exp:InstanceofExpression)
    default boolean checkType(Object obj) {
        int num = 2; // numbers:2
        return obj instanceof TargetAnnotation.InnerTargetClass; // InstanceofExpression
    }

    // Variable call (default method for variable access)
    default String variableCall(TargetAnnotation.InnerTargetClass param) {
        String targetValue = param.value(); // variable call
        return targetValue;
    }

    // No new exception (default method with safe logic)
    default TargetAnnotation safeMethod() {
        return null; // no_new_exception
    }
}

// Super annotation for extends feature (source_class)
@interface CustomAnnotation {}

// Super class for super.field
class SuperAnnotation {
    String superField = "superFieldValue";
}

// Target annotation interface (default modifier, anonymous inner class target_feature)
@interface TargetAnnotation {
    // Inner target class (target_inner)
    class InnerTargetClass {
        String value() {
            return "targetInnerValue";
        }
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in target annotation");
        }
    };
}