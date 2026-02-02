package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Different package class for ThrowStatement pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetClass;

public class DiffPackageOthers {
    // ThrowStatement (private modifier, ClassName.field, 2, pos=diff_package_others)
    private void throwStatement(TargetClass.TargetInner target) {
        if (target.innerField != 2) {
            TargetClass.staticField = 2; // ClassName.field + 2 from target_feature
            throw new IllegalArgumentException("Field value not 2: " + target.innerField);
        }
    }
}

// Main package
package com.refactoring.test;
import com.refactoring.others.DiffPackageOthers;
import java.util.function.Consumer;

// Interface for target class implements feature
interface TargetInterface {
    default String getFieldValue() { return "default"; }
}

// Target class (normal class, default modifier, implements + local inner class)
class TargetClass implements TargetInterface {
    static int staticField; // For ThrowStatement ClassName.field
    // Target inner class (target_inner)
    public class TargetInner {
        int innerField; // For variable call/per_condition

        // Local inner class (target_feature)
        class TargetLocalInner {
            void updateField(int value) {
                innerField = value;
            }
        }
    }
}

// Subclass for call_method (sub_class type)
class TargetSubClass extends TargetClass {
    // Instance code block for call_method pos=instance code blocks
    {
        // call_method (public modifier, static + this.methodName(arguments))
        public Object callMethod(TargetClass.TargetInner param) {
            // static feature
            TargetClass.staticField = 3;
            // this.methodName(arguments)
            return this.processField(param, TargetClass.staticField);
        }
    }

    private Object processField(TargetClass.TargetInner param, int val) {
        param.innerField = val;
        return param.innerField;
    }
}

// Source class (normal class, public modifier, same package, two static nested classes)
public class SourceClass {
    // First static nested class (source_feature)
    static class SourceStaticNestedOne {}
    // Second static nested class (source_feature)
    static class SourceStaticNestedTwo {}

    // Overloading method 1 (type:overloading)
    private List<String> moveMethod(TargetClass.TargetInner targetParam) {
        return moveMethod(targetParam, 3);
    }

    // Overloading method 2 (type:overloading, main refactoring method)
    private List<String> moveMethod(TargetClass.TargetInner targetParam, int num) {
        // Per_condition: contains target parameter (targetParam)
        if (targetParam == null) {
            return new ArrayList<>();
        }

        // ThrowStatement (pos=diff_package_others)
        new DiffPackageOthers().throwStatement(targetParam);

        // Constructor invocation
        TargetClass targetInstance = new TargetClass();
        TargetClass.TargetInner newInner = targetInstance.new TargetInner();
        TargetClass.TargetInner.TargetLocalInner localInner = newInner.new TargetLocalInner();

        // Synchronized statement
        synchronized (this) {
            newInner.innerField = 2;
        }

        // Type declaration statement
        SourceStaticNestedOne staticNested = new SourceStaticNestedOne();
        TargetSubClass subClass = new TargetSubClass();

        // SuperMethodInvocation (numbers:3, modifier:public, exp:SuperMethodInvocation)
        public void superMethodInvocation() {
            super.toString(); // SuperMethodInvocation
            targetParam.innerField = 3; // 3 from numbers
        }
        superMethodInvocation();

        // Variable call
        int varCall = targetParam.innerField;
        targetParam.innerField = varCall + 3;

        // Instance code block call_method execution
        Object callResult = subClass.callMethod(targetParam);

        // Local inner class usage
        newInner.new TargetLocalInner().updateField(targetParam.innerField);

        // Result preparation
        List<String> result = new ArrayList<>();
        result.add(String.valueOf(targetParam.innerField));
        result.add(String.valueOf(callResult));

        // No new exception
        return result;
    }
}