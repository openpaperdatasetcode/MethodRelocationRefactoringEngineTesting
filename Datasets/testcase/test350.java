// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.others.OtherClass;
import java.util.List;
import java.util.ArrayList;

// Strictfp normal source class (different package from target, local/anonymous inner classes)
strictfp class SourceClass {
    // Source contains target class field (satisfies per_condition)
    private TargetClass targetField = new TargetClass();

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in SourceClass");
        }
    };

    // Static code block with varargs method feature (protected modifier, 3, others_class, varargs, this.methodName)
    static {
        SourceClass sourceInstance = new SourceClass();
        List<String> varargsResult = sourceInstance.protectedVarargsMethod(3, "arg1", "arg2");
    }

    // Normal method (private access, void return, target parameter)
    private void targetMethod(TargetClass targetParam) {
        // Variable call
        String localVar = "var_call";

        // ExpressionStatement (private modifier, ClassName.field + 1, diff_package_target pos)
        private: {
            // ClassName.field from target class (diff_package_target)
            localVar = TargetClass.staticField + 1; // ClassName.field + 1
        }

        // Constructor invocation
        SourceClass sourceInstance = new SourceClass();

        // Switch statement
        switch (localVar.length()) {
            case 1:
                localVar += targetParam.instanceField;
                break;
            default:
                localVar += "default";
        }

        // Local inner class (source feature)
        class LocalInnerClass {
            void process() {
                // Lambda expression with call_method (target, protected, abstract, instanceReference.methodName)
                Runnable lambda = () -> targetParam.protectedAbstractMethod(localVar);
                lambda.run();
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.process();

        // No new exception (no exception instantiation)
    }

    // Varargs method (protected modifier, List<String> return, others_class reference)
    protected List<String> protectedVarargsMethod(int num, String... args) {
        List<String> result = new ArrayList<>();
        // others_class reference + this.methodName
        result.add(OtherClass.helperMethod(num) + this.toString());
        for (String arg : args) {
            result.add(arg);
        }
        return result;
    }
}

// Others class for varargs method feature
package com.refactoring.others;

public class OtherClass {
    public static String helperMethod(int num) {
        return String.valueOf(num);
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Default modifier target class (local inner class feature)
class TargetClass {
    // Static field for ExpressionStatement (ClassName.field)
    public static String staticField = "target_static";
    // Instance field for variable call
    public String instanceField = "target_instance";

    // Local inner class (target_feature)
    public void targetMethod() {
        class LocalInnerClass {
            private String innerField = "local_inner";
        }
        LocalInnerClass localInner = new LocalInnerClass();
    }

    // Call method (target type, protected, abstract, instanceReference.methodName, void return)
    protected abstract void protectedAbstractMethod(String arg);
}