package com.refactoring.movemethod;

// Diff package others class for SuperConstructorInvocation pos
package com.refactoring.others;
import com.refactoring.movemethod.SourceClass;
import com.refactoring.movemethod.TargetClass;

public class DiffPackageHelper {
    public static void invokeSuperConstructor(SourceClass.InnerClass.RecursiveInnerClass innerObj, TargetClass target) {
        innerObj.superConstructorLogic(target);
    }
}

// Back to source package
package com.refactoring.movemethod;
import com.refactoring.others.DiffPackageHelper;

// Super class for SuperConstructorInvocation
class SuperSourceClass {
    protected int superField;

    public SuperSourceClass(int value) {
        this.superField = value;
    }
}

// Base class for overriding feature
class BaseMethodClass {
    public TargetClass processTarget(TargetClass target) {
        return target;
    }
}

// Source class: normal, default modifier, same package as target, local inner + member inner class
class SourceClass extends SuperSourceClass {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    public SourceClass() {
        super(3); // Super constructor with 3 (align with target_feature: 3)
    }

    // Member inner class (source class feature)
    class InnerClass {
        // Recursive inner class (source_inner_rec - method position)
        class RecursiveInnerClass extends BaseMethodClass {
            // Overriding method (method type: overriding)
            @Override
            public TargetClass processTarget(TargetClass target) { // return TargetClass Type
                // SuperConstructorInvocation feature: private modifier, ClassName.field, 3, pos=diff_package_others
                private void superConstructorLogic(TargetClass targetParam) {
                    // ClassName.field usage (TargetClass.StaticNested.staticField = 3)
                    int fieldValue = TargetClass.StaticNested.staticField; // target_feature: ClassName.field, 3
                    // SuperConstructorInvocation (call super class constructor)
                    SuperSourceClass superObj = new SuperSourceClass(fieldValue);
                    // pos: diff_package_others (call diff package helper)
                    DiffPackageHelper.invokeSuperConstructor(this, targetParam);
                }

                // Variable call (target field and parameter)
                String targetValue = targetField.getFieldValue();
                targetValue += target.getFieldValue();

                // While statement
                int count = 0;
                while (count < 3) { // align with target_feature: 3
                    targetValue += "_loop_" + count;
                    count++;
                }

                // Depends on inner class (local inner class)
                class LocalInnerProcessor { // depends_on_inner_class (source feature)
                    String processValue(String input) {
                        return input + "_processed_by_inner";
                    }
                }
                LocalInnerProcessor innerProcessor = new LocalInnerProcessor();
                target.setFieldValue(innerProcessor.processValue(targetValue));

                // No new exception feature
                try {
                    Integer.parseInt(targetValue);
                } catch (NumberFormatException e) {
                    // No throw new exception, only handle
                    target.setFieldValue("formatted_" + targetValue);
                }

                // return this; (return current instance - adjusted for TargetClass return type)
                target.setInnerInstance(this); // Pass this to target for "return this" semantic
                // Return statement (return TargetClass Type)
                return target;
            }
        }
    }

    // Sub class for call_method (sub_class type)
    class SubClass extends InnerClass.RecursiveInnerClass {
        // call_method: sub_class type, default modifier, normal, this.methodName(), pos=while, return void
        void callMethod(TargetClass targetParam) {
            int loopCount = 0;
            // pos: while statement
            while (loopCount < 2) {
                // target_feature: normal + this.methodName(arguments)
                TargetClass result = this.processTarget(targetParam); // this.methodName(arguments)
                System.out.println("Call method result: " + result.getFieldValue());
                loopCount++;
            }
        }
    }
}

// Target class: normal, default modifier, same package as source, static nested class feature
class TargetClass {
    // Static nested class (target_feature)
    public static class StaticNested {
        public static int staticField = 3; // target_feature: 3
    }

    private String fieldValue = "targetValue6436";
    private SourceClass.InnerClass.RecursiveInnerClass innerInstance;

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String value) {
        this.fieldValue = value;
    }

    public void setInnerInstance(SourceClass.InnerClass.RecursiveInnerClass instance) {
        this.innerInstance = instance;
    }

    // For "return this" semantic
    public SourceClass.InnerClass.RecursiveInnerClass getInnerInstance() {
        return innerInstance;
    }
}