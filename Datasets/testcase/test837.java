package refactoring.test;

import java.lang.reflect.Method;

// Super class for super constructor invocation
class SuperTargetClass {
    public SuperTargetClass() {}
    public int superMethod(int value) {
        return value * 1; // method_feature: 1
    }
}

// Target class: public modifier, local inner class (target_feature), same package as source
public class TargetClass extends SuperTargetClass {
    public String targetField = "targetData";
    private int thisField = 1; // this.field + 1 for WhileStatement feature

    // Local inner class (target_feature)
    public Object processVarargs(Object... args) {
        class LocalInnerTarget {
            Object combine(Object... data) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : data) {
                    sb.append(obj).append(",");
                }
                return sb.toString();
            }
        }
        return new LocalInnerTarget().combine(args);
    }

    public int getThisField() {
        return thisField;
    }
}

// Source class: default modifier, empty features, same package as target
class SourceClass {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";
    // Static field for depends_on_static_field feature
    private static int staticField = 1; // method_feature: 1

    // WhileStatement feature: private modifier, this.field, 1, pos=source
    private void whileFeature(TargetClass target) {
        int count = 0;
        while (count < target.getThisField()) { // this.field + 1
            count++;
            if (count == 1) break; // target_feature: 1
        }
    }

    // Instance method feature: private modifier, 1, source, instance, lambda, pos=for, return_type=Object
    private Object instanceHelperMethod(SourceClass source, Object... args) {
        // Lambda: (parameters) -> methodBody
        java.util.function.Function<Object[], Object> lambda = (params) -> {
            StringBuilder sb = new StringBuilder();
            // pos=for
            for (int i = 0; i < 1; i++) { // method_feature: 1
                sb.append(source.outerPrivateField).append(params[i]);
            }
            return sb.toString();
        };
        return lambda.apply(args);
    }

    // Inner class for call_method feature
    class SourceInnerClass {
        // Base method for overriding
        protected int baseMethod(int value) {
            return value;
        }
    }

    // Overriding inner class for call_method
    class OverridingInner extends SourceInnerClass {
        @Override // overriding feature
        protected int baseMethod(int value) {
            // superTypeReference.methodName(arguments)
            return super.superMethod(value); // Call super class method
        }
    }

    // Varargs method: public access, Object return type, target parameter (per_condition)
    public Object refactorMethod(TargetClass targetParam, Object... args) {
        // Super constructor invocation (method feature)
        TargetClass newTarget = new TargetClass();

        // Variable call (method feature)
        String varCall = targetParam.targetField;

        // Access outer private (access_outer_private feature)
        String outerPrivate = this.outerPrivateField;

        // Depends on static field (depends_on_static_field feature)
        int staticVal = SourceClass.staticField;

        // Execute WhileStatement feature
        whileFeature(targetParam);

        // Execute instance helper method (lambda + for pos)
        Object lambdaResult = instanceHelperMethod(this, args);

        // call_method: inner_class type, protected modifier, overriding + superTypeReference, pos=exception handling, return_type=int
        int callResult = 0;
        try {
            SourceInnerClass inner = new OverridingInner();
            callResult = inner.baseMethod(staticVal); // pos=exception handling
        } catch (Exception e) {
            // No new exception thrown (method feature)
            callResult = 0;
        }

        // Used by reflection (used_by_reflection feature)
        try {
            Method processMethod = TargetClass.class.getMethod("processVarargs", Object[].class);
            Object reflectResult = processMethod.invoke(targetParam, (Object) args);
        } catch (Exception e) {
            // No new exception thrown (method feature)
        }

        // No new exception thrown (method feature)
        return varCall + "_" + outerPrivate + "_" + staticVal + "_" + lambdaResult + "_" + callResult;
    }
}