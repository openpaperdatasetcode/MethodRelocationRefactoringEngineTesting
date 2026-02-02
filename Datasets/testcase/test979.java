package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Functional interface for source_class implements feature
interface SourceInterface {
    List<String> processTarget(TargetClass target);
}

// Target class (normal class, sealed modifier, static nested class feature)
public sealed class TargetClass permits TargetSubClass {
    String targetField; // For per_condition and variable call

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static String staticMethod(String input) {
            return input + "_static_" + 1; // 1 from numbers feature
        }
    }
}

// Permits subclass for sealed TargetClass
final class TargetSubClass extends TargetClass {}

// Source class (normal class, default modifier, same package, implements + local inner + member inner class)
class SourceClass implements SourceInterface {
    // Member inner class (source_feature)
    class SourceMemberInner {
        protected String helperMethod(TargetClass target) { // protected modifier for numbers feature
            return target.targetField + "_helper_" + 1; // 1 from numbers feature
        }
    }

    // Method to be refactored (accessor, List<String> return, default access, source position)
    @Override
    public List<String> processTarget(TargetClass targetParam) { // accessor type (implements interface method)
        // Per_condition: contains target parameter
        List<String> result = new ArrayList<>();
        if (targetParam == null) {
            return result;
        }

        // Variable call (access target field - per_condition)
        String varCall = targetParam.targetField;
        if (varCall == null) {
            varCall = "default_value_" + 1; // 1 from numbers feature
        }

        // Requires try-catch
        try {
            // MethodInvocation (numbers:1, modifier:protected, exp:MethodInvocation)
            SourceMemberInner inner = new SourceMemberInner();
            String methodInvocResult = inner.helperMethod(targetParam); // protected modifier + MethodInvocation
            result.add(methodInvocResult);
            
            // Additional MethodInvocation with static nested class
            String staticMethodResult = TargetClass.TargetStaticNested.staticMethod(varCall); // 1 from numbers feature
            result.add(staticMethodResult);
        } catch (Exception e) {
            result.add("Exception caught: " + e.getMessage());
        }

        // Local inner class (source_feature)
        class SourceLocalInner {
            void updateResult(List<String> list, String value) {
                list.add(value + "_local_inner_" + 1); // 1 from numbers feature
            }
        }
        new SourceLocalInner().updateResult(result, varCall);

        // No new exception (try-catch handles all exceptions)
        return result;
    }
}