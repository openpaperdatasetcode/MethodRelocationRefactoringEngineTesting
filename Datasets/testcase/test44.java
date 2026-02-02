package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Others class for call_method feature
class OthersClass {
    // Call_method: public modifier, instance, OuterClass.InnerClass.methodName(), return int
    public static class InnerOthersClass {
        public int innerMethod(String param) {
            return param.length() + 3;
        }
    }
}

// Source class: normal class, protected modifier, same package, static nested class, local inner class
protected class SourceClass {
    private String sourcePrivateField = "sourcePrivateValue";

    // Static nested class (source feature)
    static class StaticNestedSource {
        String nestedField = "staticNestedValue";
    }

    // Method: instance, void return, private access, source position
    // per_condition: contains target class parameter (TargetClass)
    private void moveableInstanceMethod(TargetClass targetParam) {
        // Variable call feature
        String localVar = targetParam.targetField;
        localVar = sourcePrivateField;

        // ForStatement: public modifier, obj.field, 3, pos=inner class
        class ForStatementInnerClass {
            public void forLogic(TargetClass obj) {
                for (int i = 0; i < 3; i++) { // target_feature "3"
                    obj.targetField = String.valueOf(i); // target_feature "obj.field"
                    localVar = obj.targetField;
                }
            }
        }
        new ForStatementInnerClass().forLogic(targetParam);

        // Generic feature: protected modifier, method_feature [3, source, generic, instanceReference.methodName()], pos=object initialization, return void
        protected <T extends SourceClass> void genericMethod(T instance) {
            instance.moveableInstanceMethod(targetParam); // instanceReference.methodName(arguments)
            String genericVal = String.valueOf(3); // method_feature "3"
            localVar = genericVal + "source"; // method_feature "source"
        }
        // Object initialization position for generic feature
        SourceClass sourceInstance = new SourceClass();
        genericMethod(sourceInstance);

        // Switch statement & switch case feature
        switch (localVar.length()) {
            case 3: // switch case
                localVar = "case3";
                break;
            default:
                localVar = "default";
        }

        // While statement feature
        int counter = 0;
        while (counter < 3) {
            localVar += counter++;
        }

        // OuterClass.this.x feature
        String outerThisVal = SourceClass.this.sourcePrivateField;

        // Access_outer_private feature
        localVar = this.sourcePrivateField;

        // Call_method: others_class, pos=collection operations, return int
        List<String> collection = new ArrayList<>();
        collection.add(localVar);
        int callResult = collection.stream()
                .mapToInt(s -> OthersClass.InnerOthersClass.innerMethod(s)) // OuterClass.InnerClass.methodName()
                .sum();

        // Local inner class (source feature)
        class LocalInnerSource {
            void localMethod() {
                localVar += callResult;
            }
        }
        new LocalInnerSource().localMethod();

        // No_new_exception feature (no custom exceptions instantiated/thrown)
    }
}

// Target class: normal class, private modifier, target_feature: member inner class
private class TargetClass {
    String targetField = "targetValue";

    // Member inner class (target_feature)
    class MemberInnerTarget {
        void innerMethod() {
            targetField = "innerUpdated";
        }
    }
}