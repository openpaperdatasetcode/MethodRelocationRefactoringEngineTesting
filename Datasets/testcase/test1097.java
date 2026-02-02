package com.example;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}

// Source class (private modifier, normal class, same package, local inner class, member inner class)
private class SourceClass {
    // per_condition: source contains the field of the target
    private TargetClass targetField = new TargetClass();

    // Member inner class (source_class feature)
    class MemberInnerClass {}

    // Method to be refactored (instance, List<String> return, default access, position: source)
    @CustomAnnotation // has_annotation feature
    List<String> targetMethod() throws Exception { // requires_throws
        // Local inner class (source_class feature)
        class LocalInnerClass {
            String process(String val) {
                return val.trim();
            }
        }
        LocalInnerClass local = new LocalInnerClass();

        // Variable call (target_inner)
        TargetClass.InnerTargetClass innerTarget = targetField.new InnerTargetClass();
        String targetValue = innerTarget.getValue();

        // With_bounds (generic bounds)
        List<? extends CharSequence> boundedList = new ArrayList<>();
        boundedList.add(local.process(targetValue));

        // If/else conditions with call_method (pos: if/else conditions)
        Object callResult;
        if (targetValue.length() > 0) {
            OthersClass others = new OthersClass();
            callResult = others.callMethod(1); // others_class, private, instance, this.methodName(arguments)
        } else {
            OthersClass others = new OthersClass();
            callResult = others.callMethod("empty");
        }
        boundedList.add(callResult.toString());

        return new ArrayList<>(boundedList);
    }
}

// Target class (public modifier, normal class, member inner class target_feature)
public class TargetClass {
    // Inner target class (target_inner)
    public class InnerTargetClass {
        private String value = "targetInnerValue";

        public String getValue() {
            return value;
        }
    }
}

// Others class for call_method (others_class, private modifier)
class OthersClass {
    // Call method (instance, this.methodName(arguments), return Object)
    private Object callMethod(Object arg) {
        return this.processArg(arg); // this.methodName(arguments)
    }

    // Helper method for this.methodName(arguments)
    private Object processArg(Object arg) {
        return "processed_" + arg;
    }
}