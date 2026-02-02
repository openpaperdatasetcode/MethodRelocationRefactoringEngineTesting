package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Default modifier enum source class (same package as target)
enum SourceEnum {
    INSTANCE;

    // Source contains target enum field (satisfies per_condition)
    private TargetEnum targetField = TargetEnum.VALUE;

    // Static nested class (source feature)
    public static class SourceStaticNested {}

    // Recursive inner class (source_inner_rec)
    public class SourceInner {
        // Private outer field for access_outer_private
        private String outerPrivateField = "outer_private";

        // Recursive inner class containing the target method
        public class RecursiveInner {
            /**
             * Javadoc for the instance method to be refactored
             * @param targetParam parameter of target enum type
             * @return List<String> result
             */
            protected List<String> instanceMethod(TargetEnum targetParam) {
                // Type declaration statement
                String localVar;
                List<String> result;

                // Variable call
                localVar = outerPrivateField;
                result = new ArrayList<>();

                // Access outer private field
                String privateVal = SourceInner.this.outerPrivateField;

                // Raw type usage
                List rawList = result;

                // EnhancedForStatement (private modifier, obj.field + 3, source pos)
                private: {
                    int[] nums = {3};
                    for (int num : nums) {
                        rawList.add(targetParam.nestedField + num);
                    }
                }

                // Constructor invocation
                SourceStaticNested staticNested = new SourceStaticNested();

                // If statement
                if (rawList.size() > 0) {
                    localVar += "_modified";
                }

                // Super constructor invocation
                super();

                // Instance code block with call_method (sub_class, synchronized, instanceReference)
                {
                    TargetSubEnum subInstance = TargetSubEnum.SUB_VALUE;
                    int callResult = subInstance.synchronizedMethod(localVar); // instanceReference.methodName
                    rawList.add(String.valueOf(callResult));
                }

                // No new exception (no exception instantiation)
                return result;
            }
        }
    }

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in SourceEnum");
        }
    };
}

// Default modifier enum target class (static nested class feature)
enum TargetEnum {
    VALUE;

    // Field for obj.field reference
    public String nestedField = "target_field";

    // Static nested class (target feature)
    public static class TargetStaticNested {}
}

// Sub-class (enum constant with method) for call_method
enum TargetSubEnum {
    SUB_VALUE;

    // Synchronized method (sub_class, synchronized modifier, normal, int return)
    public synchronized int synchronizedMethod(String arg) {
        return arg.length() + TargetEnum.VALUE.nestedField.length();
    }
}