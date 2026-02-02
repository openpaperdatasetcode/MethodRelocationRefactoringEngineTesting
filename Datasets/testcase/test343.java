package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.Collection;

// Private normal source class (same package as target)
private class SourceClass extends ParentClass {
    // Field for LabeledStatement (this.field + 3)
    private int sourceField = 3;

    // Member inner class (source feature)
    public class MemberInnerClass {}

    // Static nested class (source feature)
    public static class StaticNestedClass {}

    // Varargs method (private access, returns TargetClass, contains target parameter)
    private TargetClass targetMethod(TargetClass targetParam, String... args) {
        // Variable call
        String localVar = "var_call";
        int recursionResult = 0;

        // LabeledStatement (private modifier, this.field + 3, source pos)
        privateLabel: {
            if (this.sourceField == 3) {
                localVar += this.sourceField + 3;
                break privateLabel;
            }
        }

        // Super constructor invocation
        super();

        // Recursion feature (default modifier, exception throwing statements pos, base type return)
        try {
            recursionResult = recursiveMethod(1); // 1 from method_feature
        } catch (IllegalArgumentException e) {
            // No new exception (no instantiation of new Exception)
            recursionResult = -1;
        }

        // Collection operations with call_method (target, protected, static, ClassName.methodName)
        Collection<String> collection = new ArrayList<>();
        collection.add(String.valueOf(TargetClass.staticMethod(recursionResult))); // ClassName.methodName

        return targetParam;
    }

    // Recursive method (recursion feature, parent_class reference, method reference ClassName::methodName)
    int recursiveMethod(int num) {
        if (num > 5) {
            // Exception throwing statements pos
            throw new IllegalArgumentException("Recursion limit exceeded");
        }
        // Recursion + method reference (ClassName::methodName)
        return num + ParentClass::helperMethod; // Parent class reference + method reference
    }
}

// Parent class for recursion method_feature (parent_class)
class ParentClass {
    // Helper method for method reference
    static int helperMethod(int num) {
        return num * 2;
    }
}

// Default modifier target class (member inner class feature)
class TargetClass {
    // Member inner class (target feature)
    public class MemberInnerClass {
        private int innerField = 0;
    }

    // Call method (target type, protected static, int return, ClassName.methodName)
    protected static int staticMethod(int arg) {
        return arg * 3;
    }
}