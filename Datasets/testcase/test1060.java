package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

public abstract class SourceClass {
    // Source contains target class field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // Local inner class (source_class feature)
            class LocalInnerClass {
                String getValue() {
                    return targetField.getInnerValue();
                }
            }
            LocalInnerClass local = new LocalInnerClass();
            local.getValue();
        }
    };

    // Method to be refactored (varargs, List<String> return, public access, position: source)
    public List<String> targetMethod(String... args) throws Exception { // requires_throws
        List<String> result = new ArrayList<>();
        
        // Try statement
        try {
            // For statement
            for (int i = 0; i < args.length; i++) {
                // Variable call
                String innerValue = targetField.innerClass.getValue;
                result.add(args[i] + "-" + innerValue);
            }
        } catch (NullPointerException e) {
            throw new Exception("Error processing varargs", e); // requires_throws
        }
        
        return result;
    }
}

public abstract class TargetClass {
    // Member inner class (target_feature)
    class InnerTargetClass {
        String getValue() {
            return "targetValue";
        }
    }

    final InnerTargetClass innerClass = new InnerTargetClass();

    String getInnerValue() {
        return innerClass.getValue();
    }
}