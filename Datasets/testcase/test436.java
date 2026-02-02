package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

private class SourceClass {
    // Source contains the field of the target (per_condition)
    private TargetClass targetField = new TargetClass();

    // Local inner class (source_class feature)
    class LocalInnerClass {
        // Varargs method (method type) with public access modifier, return List<String>
        // Method position: source_inner (inside local inner class of source)
        public List<String> moveCandidateMethod(String... args) {
            List<String> result = new ArrayList<>();
            // Try statement (method feature)
            try {
                // Variable call (method feature)
                String arg0 = args.length > 0 ? args[0] : "";
                result.add(arg0);
                
                // Raw type (method feature)
                Vector rawVector = new Vector();
                rawVector.add("raw_type_element");
                result.addAll(rawVector);
                
                // Constructor with protected modifier (method feature)
                // Constructor parameter list contains new ClassName().method()
                ProtectedConstructorExample constructorExample = new ProtectedConstructorExample(new TargetClass().localInnerMethod());
                
                // No new exception (method feature - no throw new Exception)
            } catch (Exception e) {
                result.add(e.getMessage());
            }
            return result;
        }

        // Protected constructor (method feature: constructor, protected modifier)
        protected class ProtectedConstructorExample {
            public ProtectedConstructorExample(Object param) {
                // Constructor with parameter list containing new ClassName().method()
            }
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            LocalInnerClass inner = new LocalInnerClass();
            inner.moveCandidateMethod("anonymous_call");
        }
    };
}

public class TargetClass {
    // Local inner class (target_class target_feature)
    class LocalInnerClass {
        public String localInnerMethod() {
            return "target_local_inner_method";
        }
    }
}