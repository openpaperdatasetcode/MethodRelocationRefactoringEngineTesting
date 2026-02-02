package com.refactoring.movemethod;

import java.io.IOException;
import java.util.List;

// Source abstract class (protected modifier, same package, local inner + member inner class)
protected abstract class SourceClass {
    // Member inner class (source feature)
    protected class SourceMemberInnerClass {
        String innerField = "source_inner";
    }

    // Instance method to be moved (default access, returns TargetClass type, source position)
    TargetClass moveableMethod(TargetClass targetParam) throws IOException {
        // Constructor invocation feature (target inner class constructor)
        TargetClass.TargetMemberInner targetInner = targetParam.new TargetMemberInner("param1", 2);

        // Labeled statement feature
        label:
        for (int i = 0; i < 5; i++) {
            if (i == 3) break label;
            // Variable call feature
            String varCall = targetInner.innerField + this.new SourceMemberInnerClass().innerField;
            System.out.println(varCall);
        }

        // Raw type feature
        List rawList = new java.util.ArrayList();
        rawList.add(targetInner.innerField);

        // Local inner class (source feature)
        class SourceLocalInnerClass {
            String localField = rawList.get(0).toString();
        }
        SourceLocalInnerClass localInner = new SourceLocalInnerClass();

        // Requires throws feature (declares IOException, no new exception instantiation)
        if (localInner.localField.isEmpty()) {
            throw new IOException("Empty field");
        }

        // Return TargetClass type (target_inner)
        return targetParam;
    }
}

// Target abstract class (protected modifier, same package, local inner class target feature)
protected abstract class TargetClass {
    // Member inner class for target_inner reference
    protected class TargetMemberInner {
        String innerField;

        public TargetMemberInner(String str, int num) {
            this.innerField = str + num;
        }
    }

    // Abstract method (required for abstract class)
    protected abstract void abstractMethod();

    // Method containing local inner class (target feature)
    protected void targetMethod() {
        // Local inner class (target feature)
        class TargetLocalInnerClass {
            int localInt = 10;
        }
        TargetLocalInnerClass targetLocal = new TargetLocalInnerClass();
        System.out.println(targetLocal.localInt);
    }
}