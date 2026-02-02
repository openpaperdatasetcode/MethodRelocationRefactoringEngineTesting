package refactoring.test;

import java.util.function.Function;

// Different package class for ExpressionStatement pos (diff_package_others)
package refactoring.other;
import refactoring.test.TargetClass;
public class DiffPackageOtherClass {
    public void accessTargetField(TargetClass<String> target) {
        private int exprField = target.memberInner.targetField + 3; // obj.field + 3 (ExpressionStatement pos)
        System.out.println(exprField);
    }
}

// Back to source package
package refactoring.test;

// Parent class for overriding and call_method (parent_class type)
protected class ParentClass {
    // Method to be overridden
    public Object overrideMethod(TargetClass<?> targetParam) {
        return targetParam;
    }

    // Call method (parent_class type, protected modifier, ternary operators pos, String return)
    protected String callMethod(TargetClass<String> targetParam) {
        // Ternary operators pos requirement
        String ternaryResult = (targetParam != null) ? "valid" : "invalid";
        
        // Constructor target_feature
        TargetClass<String> newTarget = new TargetClass<>("constructorArg");
        TargetClass.MemberInnerClass<String> inner = newTarget.new MemberInnerClass<>();
        
        // instanceReference::methodName target_feature
        Function<TargetClass<String>, Object> func = this::overrideMethod;
        Object result = func.apply(targetParam);
        
        return ternaryResult + result.toString();
    }
}

// Source normal class (protected modifier, same package as target, local inner + static nested class)
protected class SourceClass extends ParentClass {
    // Static nested class feature (source class feature)
    protected static class StaticNestedClass<T extends Number> { // with_bounds feature (T bounded to Number)
        T nestedField;
    }

    // Overriding method (default access, Object return, in source class)
    // Precondition: method contains target class parameter
    @Override
    public Object overrideMethod(TargetClass<?> targetParam) {
        // Variable call feature
        StaticNestedClass<Integer> staticNested = new StaticNestedClass<>();
        staticNested.nestedField = 5;
        String varCall = staticNested.nestedField.toString();

        // Numbers:2, default modifier, ArrayInitializer expression
        default int[] intArray = {2, 2}; // ArrayInitializer with 2, default modifier

        // Constructor invocation feature
        TargetClass.MemberInnerClass<?> innerInstance = targetParam.new MemberInnerClass<>();
        
        // ExpressionStatement (private modifier, obj.field + 3, diff_package_others pos)
        private int exprStmtVar = innerInstance.targetField + 3; // obj.field + 3
        System.out.println(exprStmtVar); // ExpressionStatement feature

        // with_bounds feature (reuse static nested class's bounded type)
        StaticNestedClass<Double> boundedInstance = new StaticNestedClass<>();
        boundedInstance.nestedField = 3.14;

        // No_new_exception (implicit NPE if targetParam is null, no explicit new exception)
        return varCall + exprStmtVar + boundedInstance.nestedField;
    }

    // Local inner class feature (source class feature)
    public void sourceLocalInnerClass() {
        class LocalInnerClass {
            void innerMethod(TargetClass<String> targetParam) {
                overrideMethod(targetParam);
            }
        }
        new LocalInnerClass().innerMethod(new TargetClass<>("localInnerArg"));
    }
}

// Target normal class (protected modifier, type parameter + member inner class target_feature)
protected class TargetClass<T> {
    private T value;

    // Constructor for constructor invocation feature
    public TargetClass(T value) {
        this.value = value;
    }

    // Member inner class target_feature (with type parameter)
    public class MemberInnerClass<U> {
        int targetField = 10; // obj.field for ExpressionStatement feature
        U innerValue;
    }

    // Instance of member inner class for field access
    public MemberInnerClass<T> memberInner = new MemberInnerClass<>();
}