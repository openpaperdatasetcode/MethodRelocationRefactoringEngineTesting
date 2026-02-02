package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

// Source class: record class, public modifier, same package, extends, implements, member inner, static nested
public record SourceRecord(String sourceField, TargetRecord targetField) implements Runnable { // per_condition: source contains target field
    // Extends via sealed hierarchy (record implicit extends Record, explicit via sealed)
    public sealed interface BaseInterface permits SourceRecord {}
    
    // Static nested class (source feature)
    public static class StaticNestedSource {
        static List<String> staticHelper() {
            return new ArrayList<>();
        }
    }
    
    // Member inner class (source feature)
    public class MemberInnerSource {
        void innerMethod() {}
    }

    // Method: varargs, return Object, default access, source position
    Object moveableVarargsMethod(String... args) {
        // Type declaration statement feature
        class LocalTypeDeclaration {
            int value = 2;
        }
        LocalTypeDeclaration localType = new LocalTypeDeclaration();

        // Variable call feature
        String localVar = sourceField;
        localVar = targetField.innerField();

        // Overload_exist feature (overloaded methods)
        overloadMethod();
        overloadMethod(1);

        // Requires_try_catch feature
        try {
            int num = Integer.parseInt(args.length > 0 ? args[0] : "0");
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }

        // Static feature: public modifier, method_feature [2, inner_class, static, this.methodName(arguments)], pos=do-while, return List<String>
        do {
            List<String> staticResult = StaticNestedSource.staticHelper();
            staticResult = SourceRecord.staticMethod(2, new MemberInnerSource()); // this.methodName(arguments)
        } while (localType.value == 2);

        // Call_method: others_class, final, static, lambda, pos=collection operations, return int
        List<String> collection = new ArrayList<>();
        int callResult = collection.stream()
                .mapToInt(s -> OtherFinalClass.staticLambdaMethod(s)) // (parameters) -> methodBody
                .sum();

        return callResult;
    }

    // Overload_exist: overloaded method 1
    void overloadMethod() {}

    // Overload_exist: overloaded method 2
    void overloadMethod(int num) {}

    // Static method for static feature
    public static List<String> staticMethod(int num, MemberInnerSource inner) { // method_feature 2, inner_class, static
        if (num == 2) {
            inner.innerMethod();
            return SourceRecord.staticMethod(2, inner); // this.methodName(arguments) (static context)
        }
        return new ArrayList<>();
    }

    @Override
    public void run() {} // Implements Runnable
}

// Target class: record class, default modifier, target_feature: member inner class
record TargetRecord(String innerField) {
    // Member inner class (target_inner_rec - method's target class)
    public class TargetInnerRecursive {
        void targetMethod() {}
    }
}

// Call_method: others_class, final modifier, static, lambda support
final class OtherFinalClass {
    public static int staticLambdaMethod(String s) { // return int
        return s == null ? 0 : s.length();
    }
}