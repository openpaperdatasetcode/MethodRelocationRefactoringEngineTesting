package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Sub class of TargetClass's inner class (sub_class for method_feature)
class TargetInnerSubClass extends TargetClass.InnerTargetClass {
    // Static method for ClassName.methodName(arguments) feature
    public static void subInstanceMethod(String arg) {
        System.out.println(arg + "_1"); // method_feature "1"
    }
}

// Source class: normal class, private modifier, same package, member inner class, local inner class
private class SourceClass {
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Member inner recursive class (source_inner_rec - method position)
    public class InnerRecursiveSource {
        // Method: instance, return List<String>, public access, source_inner_rec position
        public List<String> moveableInstanceMethod(TargetClass.InnerTargetClass targetParam) { // per_condition: contains target_inner parameter
            List<String> result = new ArrayList<>();
            // Variable call feature
            String localVar = targetParam.innerField;
            localVar = "initVal1"; // method_feature "1"

            // Instance feature: default modifier, method_feature [1, sub_class, instance, ClassName.methodName()], pos=exception handling, return void
            void instanceLogic() { // default modifier (package-private)
                try {
                    if (localVar.isEmpty()) {
                        throw new IllegalArgumentException("Empty variable"); // exception handling statements pos
                    }
                    // ClassName.methodName(arguments) + sub_class + instance + "1"
                    TargetInnerSubClass.subInstanceMethod(localVar + "_1");
                } catch (IllegalArgumentException e) {
                    // exception handling, no custom exception (no_new_exception)
                    result.add("error_" + 1); // method_feature "1"
                }
            }
            instanceLogic();

            // Switch case feature
            switch (localVar.length()) {
                case 1: // method_feature "1"
                    localVar = "case1";
                    break;
                case 2:
                    localVar = "case2";
                    break;
                default:
                    localVar = "default";
            }
            result.add(localVar);

            // Local inner class (source feature)
            class LocalInnerSource {
                void updateTarget(TargetClass.InnerTargetClass target) {
                    target.innerField = localVar + "_inner1"; // method_feature "1"
                }
            }
            new LocalInnerSource().updateTarget(targetParam);
            result.add(targetParam.innerField);

            // Member inner class usage (source feature)
            MemberInnerSource innerSource = new MemberInnerSource();
            result.add(innerSource.innerField);

            // no_new_exception feature (no custom exceptions instantiated/thrown)
            return result;
        }

        // Member inner class (source feature)
        class MemberInnerSource {
            String innerField = "memberInner1"; // method_feature "1"
        }
    }
}

// Target class: normal class, private modifier, same package, target_feature: static nested class
private class TargetClass {
    // Inner target class (target_inner - method's target class)
    public class InnerTargetClass {
        String innerField = "targetInner1"; // method_feature "1"
    }

    // Static nested class (target_feature)
    public static class StaticNestedTarget {
        static String staticField = "staticNested1"; // method_feature "1"
    }
}