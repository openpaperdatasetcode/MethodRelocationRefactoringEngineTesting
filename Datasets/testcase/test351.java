package com.refactoring.movemethod;

import com.refactoring.others.OtherProcessingClass;
import java.util.List;

// Private enum source class (same package as target, static nested + member inner classes)
private enum SourceEnum implements OverrideInterface {
    INSTANCE;

    // Static nested class (source feature)
    public static class SourceStaticNested {}

    // Member inner class (source feature)
    public class SourceMemberInner {
        // Strictfp method for call_method (source type, strictfp, normal, instanceReference::methodName)
        strictfp String processMethod(String arg) {
            return arg.toUpperCase();
        }
    }

    // Overriding method (default access, base type return, target parameter)
    @Override
    public int overrideMethod(FinalTargetEnum targetParam) {
        // Variable call
        String localVar = "base_value";
        int result = 0;

        // Raw type usage
        List rawList = java.util.Arrays.asList(localVar);

        // ContinueStatement (protected modifier, ClassName.field + 1, diff_package_others pos)
        protected: {
            for (int i = 0; i < 5; i++) {
                if (i == OtherProcessingClass.staticField + 1) { // ClassName.field (diff_package_others)
                    continue;
                }
                result += i;
            }
        }

        // otherObject.process(this); feature
        OtherProcessingClass processor = new OtherProcessingClass();
        processor.process(this);

        // Do-while statement with call_method (instanceReference::methodName)
        SourceMemberInner innerInstance = new SourceMemberInner();
        do {
            localVar = innerInstance::processMethod; // instanceReference::methodName
            localVar = innerInstance.processMethod(localVar);
        } while (result < 10);

        // Requires try-catch block
        try {
            if (targetParam == null) {
                throw new IllegalArgumentException("Target parameter is null");
            }
            result += targetParam.ordinal();
        } catch (IllegalArgumentException e) {
            result = -1;
        }

        return result; // Base type return (int)
    }
}

// Interface for overriding feature
interface OverrideInterface {
    int overrideMethod(FinalTargetEnum targetParam);
}

// Final enum target class (member inner class feature)
final enum FinalTargetEnum {
    VALUE;

    // Member inner class (target_feature)
    public class TargetMemberInner {
        private String innerField = "target_inner";
    }
}

// Different package class for ContinueStatement (diff_package_others)
package com.refactoring.others;

import com.refactoring.movemethod.SourceEnum;

public class OtherProcessingClass {
    // Static field for ClassName.field feature
    public static int staticField = 1;

    // Process method for otherObject.process(this)
    public void process(SourceEnum source) {
        // Processing logic
    }
}