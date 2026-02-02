package com.refactoring.movemethod;

import java.util.List;

// Different package for ConstructorInvocation pos (diff_package_others)
package com.refactoring.other;
public class ConstructorHelper {
    public int field = 1; // target_feature: this.field, 1
    public ConstructorHelper() {} // public modifier ConstructorInvocation
}

// Back to main package
package com.refactoring.movemethod;
import com.refactoring.other.ConstructorHelper;

// Super class for source class (extends feature)
class SourceSuperClass {
    protected Object superMethod() { // super.methodName() for method_feature
        return "super_value_2"; // method_feature: 2
    }
}

// Source normal class (protected, same package, extends + member inner + local inner)
protected class SourceClass extends SourceSuperClass {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Member inner class (source class feature)
    public class SourceMemberInner {
        public int innerValue = 10;
    }

    // Varargs method to refactor (strictfp, return TargetClass, source position)
    strictfp TargetClass refactorMethod(TargetClass... targetParams) {
        // Variable call feature
        SourceMemberInner innerObj = new SourceMemberInner();
        int localVar = innerObj.innerValue;

        // Type declaration statement feature
        class LocalTypeDeclaration {} // type declaration statement
        LocalTypeDeclaration typeDecl = new LocalTypeDeclaration();

        // Raw type feature
        List rawList = new java.util.ArrayList(); // raw_type
        rawList.add(localVar);

        // ConstructorInvocation (public modifier, diff_package_others pos, this.field + 1)
        ConstructorHelper constructorHelper = new ConstructorHelper(); // public ConstructorInvocation
        constructorHelper.field = 1; // this.field, target_feature 1

        // Instance method in instance code blocks (pos: instance code blocks)
        { // instance code block
            Object instanceResult = this.instanceTargetMethod(); // super.methodName()
            rawList.add(instanceResult);
        }

        // Constructor invocation feature
        TargetClass newTarget = new TargetClass("init", localVar);

        // Do statement feature
        int doCount = 0;
        do {
            newTarget.setValue(doCount);
            doCount++;
        } while (doCount < localVar);

        // Local inner class (source class feature)
        class SourceLocalInner {
            void processTarget(TargetClass target) {
                // No new exception feature (no throw new Exception)
                if (target == null) return;
                rawList.add(target.getValue());
            }
        }

        // Process target parameters (per_condition: method has target parameter)
        SourceLocalInner localInner = new SourceLocalInner();
        for (TargetClass target : targetParams) {
            localInner.processTarget(target);
        }

        // Call method (target, private, pos: do-while, static + this.methodName(arguments))
        int callCount = 0;
        String callResult;
        do { // pos: do-while
            callResult = TargetClass.targetCallMethod(callCount); // static + this.methodName(arguments)
            callCount++;
        } while (callCount < 2); // method_feature: 2
        newTarget.setData(callResult);

        // Update target field
        this.targetField = newTarget;
        return newTarget;
    }

    // Instance method (public, return Object, method_feature: target/instance/2/super.methodName())
    public Object instanceTargetMethod() {
        return super.superMethod(); // super.methodName()
    }
}

// Super class for target class (extends feature)
class TargetSuperClass {
    protected int superValue = 1;
}

// Target normal class (strictfp, extends + local inner class)
strictfp class TargetClass extends TargetSuperClass {
    private String data;
    private int value;

    // Constructor for invocation
    public TargetClass(String data, int value) {
        this.data = data;
        this.value = value;
    }

    // Call method (target, private, static, this.methodName(arguments), pos: do-while, return String)
    private static String targetCallMethod(int arg) { // static target_feature
        return "call_result_" + arg; // this.methodName(arguments)
    }

    // Local inner class (target_feature)
    public void processLocalInner() {
        class TargetLocalInner { // local inner class (target_feature)
            public void updateData(TargetClass target) {
                target.data = "local_" + target.data;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        localInner.updateData(this);
    }

    // Getters/Setters
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}