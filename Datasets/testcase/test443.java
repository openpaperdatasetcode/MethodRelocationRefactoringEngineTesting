package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Source class: normal class, public modifier, same package as target
// Features: extends, anonymous inner class, static nested class
public class SourceClass extends ParentSuperClass {
    // Static nested class (source_class feature)
    public static class StaticNestedClass {
        // Overloading method 1 (functional interfaces pos)
        public List<String> overloadedMethod(Function<TargetClass, String> func, TargetClass... args) {
            List<String> result = new ArrayList<>();
            for (TargetClass arg : args) {
                // superTypeReference.methodName(arguments) (method_feature)
                result.add(ParentSuperClass.superHelper(arg.localInnerClassMethod()));
            }
            return result;
        }

        // Overloading method 2 (overloading feature)
        public List<String> overloadedMethod(String prefix, TargetClass... args) {
            List<String> result = new ArrayList<>();
            for (TargetClass arg : args) {
                result.add(prefix + arg.localInnerClassMethod());
            }
            return result;
        }

        // Overloading method 3 (method_feature: "3")
        public List<String> overloadedMethod(int count, TargetClass... args) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < count && i < args.length; i++) {
                result.add(args[i].localInnerClassMethod());
            }
            return result;
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            InnerClass inner = new InnerClass();
            inner.moveCandidateMethod(new TargetClass());
        }
    };

    // Inner class for method_position: source_inner
    public class InnerClass {
        String innerVar = "sourceInnerVar";

        // Varargs method (type: varargs), void return, protected access, position: source_inner
        // Per_condition: contains parameter of the target (TargetClass... targetParams)
        protected void moveCandidateMethod(TargetClass... targetParams) {
            // Variable call (method feature)
            String varCall = this.innerVar;
            
            // Functional interfaces (overloading pos)
            Function<TargetClass, String> func = target -> varCall + target.localInnerClassMethod();
            
            // Overloading method call (inner_class association, method_feature)
            StaticNestedClass nested = new StaticNestedClass();
            nested.overloadedMethod(func, targetParams);
            nested.overloadedMethod("prefix_", targetParams);
            nested.overloadedMethod(3, targetParams);
            
            // No new exception (method feature - no throw new Exception)
            try {
                for (TargetClass target : targetParams) {
                    // Target_inner_rec access
                    target.new RecursiveInnerClass().innerMethod();
                }
            } catch (Exception e) {
                // No new exception instantiation
                System.err.println(e.getMessage());
            }
        }
    }
}

// Parent class for source_class extends feature
class ParentSuperClass {
    public static String superHelper(String input) {
        return "super_" + input;
    }
}

// Target class: normal class, no modifier, target_feature: local inner class
class TargetClass {
    public String localInnerClassMethod() {
        // Local inner class (target_feature)
        class LocalInnerClass {
            String getValue() {
                return "targetLocalInnerValue";
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        
        // Recursive inner class (target_inner_rec)
        class RecursiveInnerClass {
            void innerMethod() {
                new RecursiveInnerClass() {}.innerMethodHelper();
            }
            
            void innerMethodHelper() {}
        }
        
        return local.getValue();
    }

    // Recursive inner class for target_inner_rec
    public class RecursiveInnerClass {
        void innerMethod() {}
    }
}