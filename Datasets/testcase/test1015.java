package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Custom annotation for has_annotation feature
@interface EnumMethodAnnotation {}

// Others class for call_method feature
class OtherClass {
    // call_method: static method, return_type List<String>
    protected static List<String> staticMethod() {
        return new ArrayList<>() {{ add("other-static-data"); }};
    }
}

// Parent class for super.field and super.methodName() feature
class EnumParentClass {
    protected int superField = 3; // target_feature: super.field, 3

    protected String superMethod() {
        return "parent-super-method";
    }
}

// Source class: enum, public modifier, same package as target, features: static nested class, member inner class
public enum SourceEnum extends EnumParentClass {
    INSTANCE;

    // Static nested class (source_class feature)
    public static class SourceStaticNested {
        public SourceStaticNested() {
            super(); // Implicit super constructor invocation
        }

        int process(int val) {
            return val * 2;
        }
    }

    // Member inner class (source_class feature)
    public class SourceMemberInner {
        // Abstract method (type: abstract, modifier: private, method_feature: 1, inner_class, abstract, super.methodName(arguments))
        private abstract Object abstractInnerMethod();

        // Concrete implementation of abstract inner class method
        public Object concreteMethod() {
            return new abstractInnerMethod() {};
        }
    }

    /**
     * Method to be refactored: overloading, return Object, public access, position source
     * Per condition: contains target class parameter
     */
    @EnumMethodAnnotation // has_annotation feature
    public Object moveMethod(TargetEnum targetParam) {
        // Variable call feature
        int localVar = 1; // method_feature: 1
        SourceStaticNested staticNested = new SourceStaticNested();
        SourceMemberInner memberInner = new SourceMemberInner();

        // ConstructorInvocation feature (type: ConstructorInvocation, modifier: public, target_feature: super.field, 3, pos: source)
        SourceStaticNested invocationObj = new SourceStaticNested();
        localVar += super.superField; // super.field, 3

        // If statement feature
        if (localVar > 3) {
            localVar = 3;
        }

        // Ternary operator with abstract method (pos: ternary operators)
        Object ternaryResult = (localVar == 3) ? memberInner.concreteMethod() : "default";

        // Super keywords feature
        String superStr = super.superMethod();
        localVar += superStr.length();

        // Throw statement feature (standard exception, no new custom exception)
        if (targetParam == null) {
            throw new IllegalArgumentException("Target parameter cannot be null");
        }

        // Exception throwing statements with call_method (pos: exception throwing statements)
        List<String> callResult = null;
        try {
            // call_method target_feature: static, super.methodName()
            callResult = OtherClass.staticMethod();
            if (callResult.isEmpty()) {
                throw new RuntimeException("Call method result is empty");
            }
        } catch (Exception e) {
            // No new exception thrown (no_new_exception feature)
            callResult = new ArrayList<>();
            callResult.add("fallback-data");
        }

        // Use target parameter's local inner class
        Object targetData = targetParam.getLocalInnerData(localVar);

        // Combine results
        return List.of(ternaryResult, superStr, targetData, callResult);
    }

    // Overload method (overload_exist feature)
    public Object moveMethod(TargetEnum targetParam, String extra) {
        return List.of(targetParam.getLocalInnerData(extra.length()), extra);
    }
}

// Target class: enum, default modifier, same package, target_feature: local inner class
enum TargetEnum {
    VALUE_1, VALUE_2;

    public Object getLocalInnerData(int value) {
        // Local inner class (target_feature)
        class TargetLocalInner {
            String combine(int val) {
                return "target-local-data-" + val;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        return localInner.combine(value);
    }
}