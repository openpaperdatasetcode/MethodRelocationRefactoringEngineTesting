// Source package: com.source
package com.source;

import com.target.PublicTargetClass;
import com.target.PublicTargetClass.InnerRecord;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Parent class for method_feature: parent_class
class ParentSourceClass {
    protected PublicTargetClass instanceMethod(PublicTargetClass target, int num) {
        target.setValue(target.getValue() + "_parent_" + num);
        return target;
    }
}

// Source normal class (private modifier, different package, member inner class, static nested class)
private class SourceClass extends ParentSourceClass {
    // per_condition: source contains the field of the target
    private final PublicTargetClass targetField = new PublicTargetClass("init_value_5812");
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "source_protected_5812";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {
        static int calculateLength(String s) {
            return s.length();
        }
    }

    // Member inner class (source_class feature)
    class MemberInnerClass {
        String process(InnerRecord record) {
            return SourceClass.this.outerProtectedField + "_" + record.value(); // uses_outer_this
        }
    }

    // Method to be refactored (normal, Object return, strictfp access, position: source)
    // method types parameter is:List
    public strictfp Object targetMethod(List<InnerRecord> paramList) {
        // Super constructor invocation
        super();

        // Constructor invocation
        PublicTargetClass newTarget = new PublicTargetClass("new_target_5812");

        // Instance method call in for loop (pos: for, 1, parent_class, instance, instanceReference.methodName(arguments))
        for (int i = 0; i < 1; i++) { // method_feature: 1
            newTarget = this.instanceMethod(newTarget, i); // instanceReference.methodName(arguments) (parent_class, instance)
        }

        // numbers:2, modifier:protected, exp:MethodInvocation
        protected int methodInvocation() {
            return StaticNestedSourceClass.calculateLength(newTarget.getValue() + "_2"); // numbers:2 (MethodInvocation)
        }
        int calcResult = methodInvocation();

        // With_bounds (generic bounds)
        List<? extends InnerRecord> boundedList = paramList; // with_bounds

        // Access outer protected field + uses_outer_this
        String protectedVal = SourceClass.this.outerProtectedField; // access_outer_protected + uses_outer_this

        // Variable call (target_inner_rec access)
        StringBuilder sb = new StringBuilder();
        for (InnerRecord record : boundedList) {
            sb.append(new MemberInnerClass().process(record)).append("_");
            newTarget.setValue(record.value() + "_" + protectedVal + "_" + calcResult);
        }

        // Override violation (attempt to override final method)
        @Override // Compile error: Cannot override final method
        public final String toString() {
            return newTarget.getValue();
        }

        // Call method in array initialization (pos: array initialization, sub_class, private, normal, ClassName::methodName)
        Function<String, Integer> func = StaticNestedSourceClass::calculateLength; // ClassName::methodName
        int[] arr = new int[] {new SubClass().callMethod(func, newTarget.getValue())}; // array initialization

        // No new exception
        return sb.toString() + "_" + arr[0] + "_" + newTarget.getValue();
    }

    // Final method to trigger override violation
    public final String toString() {
        return "SourceClass";
    }

    // Sub class for call_method (sub_class type, private modifier)
    private class SubClass {
        // Call method (normal, ClassName::methodName, return int)
        int callMethod(Function<String, Integer> func, String str) {
            return func.apply(str + "_sub") * 2;
        }
    }
}

// Target package: com.target
package com.target;

import java.util.function.Consumer;

// Target normal class (public modifier, anonymous inner class target_feature)
public class PublicTargetClass {
    private String value;

    // Anonymous inner class (target_feature)
    private final Consumer<String> anonymousInner = new Consumer<String>() {
        @Override
        public void accept(String s) {
            PublicTargetClass.this.value = s.toUpperCase();
        }
    };

    // Inner record (target_inner_rec)
    public record InnerRecord(String value) {}

    // Constructor
    public PublicTargetClass(String value) {
        this.value = value;
        this.anonymousInner.accept(value); // Trigger anonymous inner class
    }

    // Variable call: getter/setter
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}