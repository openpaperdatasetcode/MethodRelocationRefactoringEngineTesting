package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorMethodAnn {}

// Abstract source class: no features, same package as target
public abstract class SourceClass {
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "sourceProtected6432";

    // Inner class (source_inner - method position)
    class SourceInnerClass {
        private int innerField = 3; // For VariableDeclarationStatement target_feature: 3 (this.field)

        // Method to refactor: varargs, void return, public access, no type parameters, in source_inner
        @RefactorMethodAnn // has_annotation feature
        public void methodToRefactor(TargetClass.TargetInner... targetParams) {
            // Type declaration statement
            List<String> processedValues = new ArrayList<>();
            
            // VariableDeclarationStatement feature: private modifier, this.field, 3, pos=inner class
            private class VarDeclInnerClass { // pos: inner class
                // this.field usage (innerField = 3)
                private int localVar = SourceInnerClass.this.innerField; // target_feature: this.field, 3
                String processTarget(TargetClass.TargetInner param) {
                    return param.getValue() + "_" + localVar;
                }
            }
            VarDeclInnerClass varDeclObj = new VarDeclInnerClass();

            // Variable call (target parameters) + access_outer_protected
            for (TargetClass.TargetInner param : targetParams) {
                String targetValue = param.getValue();
                // Access outer protected field
                targetValue += SourceClass.this.outerProtectedField;
                processedValues.add(targetValue);
            }

            // While statement
            int count = 0;
            while (count < 3) { // target_feature: 3 alignment
                processedValues.add("loop_" + count);
                count++;
            }

            // No new exception feature
            try {
                Integer.parseInt(processedValues.get(0));
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                processedValues.set(0, "formatted_" + processedValues.get(0));
            }
        }
    }
}

// Private target class: no features, same package as source
private class TargetClass {
    // Target_inner (target inner class)
    public class TargetInner {
        private String value = "targetInnerValue";

        public String getValue() { return value; }
        public String m1() { return "m1_"; }
        public String m2() { return "m2_"; }
        public String m3() { return "m3"; }
    }

    // call_method: target type, strictfp modifier, instance, obj.m1().m2().m3(), pos=switch, return List<String>
    strictfp List<String> callMethod(SourceClass.SourceInnerClass innerObj, TargetInner... targetParams) {
        List<String> result = new ArrayList<>();
        int switchCase = 3; // align with target_feature: 3

        // pos: switch statement
        switch (switchCase) {
            case 1:
                innerObj.methodToRefactor(targetParams);
                break;
            case 3:
                // obj.m1().m2().m3() feature (method chaining)
                for (TargetInner param : targetParams) {
                    String chain = param.m1() + param.m2() + param.m3();
                    result.add(chain);
                }
                innerObj.methodToRefactor(targetParams); // instance method call
                break;
            default:
                result.add("default");
        }
        return result;
    }
}