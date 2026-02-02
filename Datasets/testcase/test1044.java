package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

public class SourceClass {
    public static String staticField = "staticValue";
    private MemberInnerClass memberInner = new MemberInnerClass();

    public List<String> moveMethod(TargetClass.TargetInnerRec targetParam) {
        // LabeledStatement feature (static modifier, obj.field, 1)
        label: {
            TargetClass.TargetInnerRec obj = targetParam;
            obj.field = 1;
            break label;
        }

        // Type declaration statement
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.localField = "local";

        // Variable call & depends_on_static_field
        String varCall = staticField;
        List<String> result = new ArrayList<>();
        result.add(varCall);

        // Constructor with overloading method in parameter list
        AnotherClass another = new AnotherClass(() -> overloadingMethod(3, new OthersClass()));

        // If/else condition with callMethod
        if (targetParam != null) {
            String callResult = callMethod(targetParam);
            result.add(callResult);
        } else {
            String callResult = callMethod(new TargetClass.TargetInnerRec());
            result.add(callResult);
        }

        return result;
    }

    public String callMethod(TargetClass.TargetInnerRec param) {
        return param != null ? (() -> param.field + param.toString()).get() : "default";
    }

    // Overloading method (private, 3, others_class, overloading, super.methodName())
    private void overloadingMethod(int num, OthersClass others) {
        super.toString();
    }

    private void overloadingMethod() {
        // Overloading implementation
    }

    // Member inner class feature
    public class MemberInnerClass {
        String innerField;
    }

    // Local inner class feature (defined within moveMethod scope)
    class LocalInnerClass {
        String localField;
    }
}

public class TargetClass {
    public static class TargetInnerRec {
        int field;
    }
}

class OthersClass {
    public OthersClass() {
        super();
    }
}