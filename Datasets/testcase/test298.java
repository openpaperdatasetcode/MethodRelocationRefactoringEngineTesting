package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Target enum class with javadoc and member inner class feature
 */
enum TargetEnum {
    // Call_method feature: instance + obj.m1().m2().m3() in constructor parameter list
    TARGET_INSTANCE(new TargetEnum.TargetMemberInner().m1().m2().m3());

    private final String value;

    TargetEnum(String value) {
        this.value = value;
    }

    // Member inner class (target feature)
    public class TargetMemberInner {
        public TargetMemberInner m1() { return this; }
        public TargetMemberInner m2() { return this; }
        public String m3() { return "chained_method_result"; }
    }

    // Instance method for access_instance_method feature
    protected String targetInstanceMethod() {
        return this.value;
    }
}

// Source enum class (protected modifier, same package, anonymous inner + static nested class)
protected enum SourceEnum {
    SOURCE_INSTANCE;

    // Static nested class (source feature)
    private static class SourceStaticNested {
        int nestedField = 2;
    }

    // Anonymous inner class (source feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("source_anonymous_inner");
        }
    };

    // Instance method to be moved (protected, returns List<String>, source position)
    protected List<String> moveableMethod(TargetEnum targetParam) {
        List<String> result = new ArrayList<>();

        // Abstract method feature (public modifier, object initialization pos, 2 + source + abstract + outerInstance.new InnerClass().methodName())
        public abstract class AbstractFeatureClass {
            public abstract void abstractMethod();
            
            {
                // Object initialization position
                int num = new SourceStaticNested().nestedField; // Number 2 feature
                if (num == 2) {
                    // outerInstance.new InnerClass().methodName() call (source feature)
                    SourceEnum.this.new SourceInnerHelper().helperMethod();
                }
            }
        }

        // Constructor invocation feature
        SourceStaticNested staticNestedObj = new SourceStaticNested();

        // Break statement feature
        loop:
        for (int i = 0; i < 5; i++) {
            if (i == staticNestedObj.nestedField) {
                break loop;
            }
            // Access instance method feature (target enum instance method)
            result.add(targetParam.targetInstanceMethod());
        }

        // Variable call feature
        String varCall = targetParam.value + staticNestedObj.nestedField + anonymousInner.toString();
        result.add(varCall);

        // No new exception instantiation (no_new_exception feature)
        return result;
    }

    // Inner helper class for abstract method feature
    private class SourceInnerHelper {
        void helperMethod() {}
    }
}