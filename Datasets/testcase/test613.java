package com.refactoring.movemethod;

import java.lang.reflect.Method;

// Same package others class for call_method (others_class type)
class OthersClass {
    // call_method: overloading (method 1)
    Object callMethod(SourceClass sourceObj, TargetClass targetParam) {
        return sourceObj.methodToRefactor(targetParam);
    }

    // call_method: overloading (method 2)
    Object callMethod(SourceClass sourceObj, TargetClass targetParam, String extra) {
        sourceObj.methodToRefactor(targetParam, extra);
        return targetParam;
    }
}

// Source class: public, same package as target, anonymous inner class, static nested class
public class SourceClass {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivate2"; // align with target_feature: 2
    // Instance method for access_instance_method feature
    private String instanceMethod() {
        return "instanceMethodResult2";
    }

    // Static nested class (source class feature)
    static class SourceStaticNested {
        public static int nestedField = 2; // align with target_feature: 2
    }

    // Method to refactor: varargs, void return, default access, in source class
    void methodToRefactor(TargetClass... targetParams) {
        // VariableDeclarationStatement feature: private modifier, obj.field, 2, pos=same_package_target
        private void varDeclLogic(TargetClass target) {
            // pos: same_package_target (operate on target object field)
            int fieldValue = 2; // target_feature: 2
            target.localField = fieldValue; // obj.field feature
            VariableDeclarationStatement:
            String localVar = target.localField + "_varDecl2"; // target_feature: 2
            System.out.println(localVar);
        }

        // Variable call (target field and varargs parameters)
        for (TargetClass param : targetParams) {
            varDeclLogic(param);
            String targetValue = param.getLocalInnerValue();
            // Access outer private field
            targetValue += SourceClass.this.outerPrivateField; // access_outer_private
            // Uses_outer_this (explicit outer this reference)
            targetValue += SourceClass.this.instanceMethod(); // uses_outer_this + access_instance_method
            
            // Used by reflection feature
            try {
                Method getMethod = TargetClass.class.getDeclaredMethod("getLocalInnerValue");
                getMethod.setAccessible(true);
                String reflectValue = (String) getMethod.invoke(param);
                targetValue += "_reflection_" + reflectValue;
            } catch (Exception e) {
                // No new exception feature (no throw new exception)
                targetValue += "_reflection_error";
            }

            // Anonymous inner class (source class feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(targetValue);
                }
            };
            anonymousRunnable.run();

            // No new exception feature (additional catch block)
            try {
                Integer.parseInt(targetValue);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                targetValue = "formatted_" + targetValue;
            }
        }

        // Call varDeclLogic for targetField (per_condition)
        varDeclLogic(targetField);
    }

    // call_method usage: ClassName::methodName, pos=if/else conditions
    public void invokeCallMethod() {
        OthersClass others = new OthersClass();
        boolean flag = true;
        
        // pos: if/else conditions
        if (flag) {
            // ClassName::methodName (method reference)
            java.util.function.BiFunction<SourceClass, TargetClass, Object> methodRef = OthersClass::callMethod;
            methodRef.apply(this, targetField);
        } else {
            others.callMethod(this, targetField, "extra2");
        }
    }
}

// Target class: protected, same package as source, local inner class feature
protected class TargetClass {
    int localField; // obj.field feature

    public String getLocalInnerValue() {
        // Local inner class (target_feature)
        class TargetLocalInner {
            String innerValue = "localInner2"; // align with target_feature: 2
            String getValue() {
                return innerValue + "_" + localField;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        return localInner.getValue();
    }
}