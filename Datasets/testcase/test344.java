package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Default modifier enum source class (same package as target)
enum SourceEnum implements MethodOverrideInterface {
    INSTANCE;

    // First member inner class (source feature)
    public class MemberInnerOne {}
    // Second member inner class (source feature)
    public class MemberInnerTwo {}

    // Overriding method (public access, List<String> return, target inner param)
    @CustomAnnotation // has_annotation feature
    @Override
    public List<String> overrideMethod(FinalTargetEnum.TargetInner targetInnerParam, String... args) {
        List<String> result = new ArrayList<>();
        // Variable call
        String localVar = "base_value";

        // Expression statement
        localVar = localVar.toUpperCase();
        result.add(localVar);

        // Overload exist (overload method)
        overrideMethod(localVar);

        // Static code block with call_method (others_class, private static, new ClassName().method())
        static {
            int callResult = OtherClass.privateStaticMethod(); // new ClassName().method() in static block
            result.add(String.valueOf(callResult));
        }

        // No new exception (no exception instantiation)
        return result;
    }

    // Overload method (overload_exist feature)
    public List<String> overrideMethod(String arg) {
        return new ArrayList<>();
    }
}

// Annotation for has_annotation feature
@interface CustomAnnotation {}

// Interface for overriding feature
interface MethodOverrideInterface {
    List<String> overrideMethod(FinalTargetEnum.TargetInner targetInnerParam, String... args);
}

// Final modifier enum target class (member inner class feature)
final enum FinalTargetEnum {
    VALUE;

    // Member inner class (target_inner)
    public class TargetInner {
        private String innerField = "target_inner_value";
    }
}

// Others class for call_method (private static, new ClassName().method(), int return)
class OtherClass {
    // Private static method (call_method: others_class, private, static)
    private static int privateStaticMethod() {
        // new ClassName().method() feature
        return new FinalTargetEnum.TargetInner().hashCode();
    }
}