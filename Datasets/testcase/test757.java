package com.refactoring.movemethod;

import java.util.List;
import java.lang.reflect.Method;

// Source class - private modifier, same package as target, contains static nested and member inner classes
private class SourceClass {
    // Field referencing target class (per_condition)
    private TargetClass.TargetStaticNested targetField = new TargetClass.TargetStaticNested();

    // Static nested class in source
    private static class SourceStaticNested {
        private String staticNestedField = "staticNested";
    }

    // Member inner class in source (method_position: source_inner)
    private class SourceMemberInner {
        // Private instance method to be moved, void return type
        private void methodToMove(List<String> listParam) {
            // Constructor invocation
            SourceStaticNested staticNested = new SourceStaticNested();
            
            // Switch statement
            switch (listParam.size()) {
                case 0:
                    System.out.println("Empty list");
                    break;
                case 1:
                    System.out.println("Single element");
                    break;
                default:
                    System.out.println("Multiple elements");
            }

            // TypeLiteral expression (numbers:1, modifier:private, exp:TypeLiteral)
            private Class<String> typeLiteral = String.class;

            // Variable call
            String varCall = staticNested.staticNestedField;
            System.out.println(varCall);

            // Used by reflection
            try {
                Method method = SourceMemberInner.class.getDeclaredMethod("methodToMove", List.class);
                method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                // no_new_exception (no explicit new Exception())
                e.printStackTrace();
            }

            // Static code block with varargs method (type:varargs, modifier:private, pos:Static code blocks)
            static {
                private TargetClass.TargetStaticNested varargsMethod(String... args) {
                    // 2 (feature), others_class, varargs, new ClassName().method()
                    OthersClass others = new OthersClass();
                    others.otherMethod(args.length);
                    return new TargetClass.TargetStaticNested();
                }
                varargsMethod("arg1", "arg2");
            }
        }
    }
}

// Target class - private modifier, contains static nested class
private class TargetClass {
    // Static nested class (target_feature)
    private static class TargetStaticNested {
        private int nestedValue = 0;
    }
}

// Others class for varargs method feature
class OthersClass {
    void otherMethod(int count) {
        // Empty implementation
    }
}