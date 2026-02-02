package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source class: enum, protected, static nested class, anonymous inner class, same package as target
protected enum SourceEnum {
    INSTANCE;

    // Per_condition: source contains target enum field
    private TargetEnum targetField = TargetEnum.VALUE1;
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "enumPrivateField";

    // Static nested class (source enum feature)
    static class SourceStaticNestedClass {
        int nestedValue = 1;
    }

    // Method to refactor: varargs, void return, protected, in source enum
    protected void methodToRefactor(String... args) {
        // Variable call (target enum field and its inner class)
        String targetInnerValue = targetField.targetInnerClass.innerValue;
        
        // Access outer private field (access_outer_private feature)
        targetInnerValue += SourceEnum.this.outerPrivateField;
        
        // IfStatement feature: static modifier, super.field, 1, pos=source
        static void ifLogic() {
            SuperEnum superObj = new SuperEnum();
            int count = 1; // target_feature: 1
            if (superObj.superField == count) { // super.field
                targetInnerValue += "_ifMatch";
            }
        }
        ifLogic();

        // Do statement
        int doCount = 0;
        do {
            targetInnerValue += args[doCount % args.length];
            doCount++;
        } while (doCount < 3);

        // Switch statement
        switch (targetField) {
            case VALUE1:
                targetInnerValue += "_value1";
                break;
            case VALUE2:
                targetInnerValue += "_value2";
                break;
            default:
                targetInnerValue += "_default";
        }

        // Override violation (attempt to override final method from Enum)
        @Override
        public final String name() { // final method cannot be overridden (violation)
            return targetInnerValue;
        }

        // Anonymous inner class (source enum feature)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetInnerValue);
            }
        };
        runnable.run();

        // No new exception feature
        try {
            Integer.parseInt(targetInnerValue);
        } catch (NumberFormatException e) {
            // No throw new exception
            targetInnerValue = "parseError";
        }
    }

    // call_method: source type, public modifier, normal, obj.m1().m2().m3(), pos=while, return List<String>
    public List<String> callMethod() {
        List<String> result = new ArrayList<>();
        int whileCount = 0;
        
        // Position: while loop
        while (whileCount < 2) {
            // obj.m1().m2().m3() feature
            SourceEnum.INSTANCE.methodToRefactor("arg1"); // m1
            String chain = targetField.targetInnerClass.getInnerValue().toUpperCase().substring(0, 5); // m2().m3()
            result.add(chain);
            whileCount++;
        }
        
        return result;
    }
}

// Super enum for IfStatement's super.field
class SuperEnum {
    int superField = 1;
}

// Target class: enum, abstract, member inner class feature, same package as source
abstract enum TargetEnum {
    VALUE1, VALUE2;

    // Member inner class (target enum feature)
    public class TargetInnerClass {
        String innerValue = "targetInnerValue";

        public String getInnerValue() {
            return innerValue;
        }
    }

    // Instance of member inner class (used in source enum variable call)
    TargetInnerClass targetInnerClass = new TargetInnerClass();
}