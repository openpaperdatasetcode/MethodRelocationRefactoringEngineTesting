package com.refactoring.movemethod;

import com.refactoring.others.OtherPackageClass;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: normal class, public modifier, same package as target
// Features: static nested class, member inner class
public class SourceClass {
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "outerProtectedValue";
    // Instance field for access_instance_field feature
    private int instanceField = 100;

    // Static nested class (source_class feature)
    public static class StaticNestedClass {
        public static String staticField = "staticNestedField";
    }

    // Member inner class (source_class feature)
    public class MemberInnerClass {
        public String innerMethod() {
            return SourceClass.this.outerProtectedField;
        }
    }

    // Custom annotation for has_annotation feature
    @Retention(RetentionPolicy.RUNTIME)
    private @interface MethodAnnotation {}

    // Varargs method (type: varargs), return base type (int), private access, position: source
    // Per_condition: contains parameter of the target (TargetClass... targetParams)
    @MethodAnnotation // has_annotation feature
    private int moveCandidateMethod(TargetClass... targetParams) {
        // Super constructor invocation (method feature)
        super();

        // Variable call (method feature)
        MemberInnerClass innerObj = new MemberInnerClass();
        String varCall = innerObj.innerMethod();
        int result = varCall.length();

        // Access outer protected (method feature)
        result += this.outerProtectedField.length();

        // Access instance field (method feature)
        result += this.instanceField;

        // Type declaration statement (method feature)
        TargetClass targetInstance;
        targetInstance = targetParams.length > 0 ? targetParams[0] : new TargetClass();

        // TypeDeclarationStatement (type), private modifier, pos: diff_package_others
        // target_feature: ClassName.field, 1
        OtherPackageClass typeDeclObj = new OtherPackageClass();
        typeDeclObj.field = 1;
        result += typeDeclObj.field;

        // Instance method call in ternary operators (pos: ternary operators)
        // method_feature: ["1", "source", "instance", "new ClassName().method()"]
        TargetClass instanceResult = (targetInstance != null) 
            ? new SourceClass().instanceMethod(targetInstance) 
            : new TargetClass();
        result += instanceResult.getAnonymousValue().length();

        // Call others_class method (call_method) in property assignment (pos: property assignment)
        CallerClass caller = new CallerClass();
        Object callResult = caller.callMethod(result);
        result += ((Number) callResult).intValue();

        // No new exception (method feature - no throw new Exception)
        try {
            for (TargetClass target : targetParams) {
                target.process();
            }
        } catch (Exception e) {
            result -= e.getMessage().length();
        }

        return result;
    }

    // Instance method for method_feature (new ClassName().method())
    public TargetClass instanceMethod(TargetClass param) {
        return param;
    }
}

// Target class: normal class, default modifier, target_feature: anonymous inner class
class TargetClass {
    public String getAnonymousValue() {
        // Anonymous inner class (target_feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("targetAnonymousInner");
            }
        };
        anonymous.run();
        return "targetValue";
    }

    public void process() {}
}

// Others class for call_method: type=others_class, modifier=private, target_feature: recursion, super.methodName(arguments)
// pos: property assignment, return_type: Object
class CallerClass extends CallerParent {
    private Object callMethod(int value) {
        // Property assignment (pos)
        Object result = recursiveCall(value);
        return result;
    }

    // Recursion feature (target_feature)
    private int recursiveCall(int count) {
        if (count <= 0) {
            // super.methodName(arguments) (target_feature)
            return super.parentHelper(count);
        }
        return count + recursiveCall(count - 1);
    }
}

// Parent class for CallerClass (super.methodName() feature)
class CallerParent {
    protected int parentHelper(int value) {
        return value * 2;
    }
}

// Different package class for TypeDeclarationStatement (diff_package_others pos)
package com.refactoring.others;
public class OtherPackageClass {
    public int field;
}