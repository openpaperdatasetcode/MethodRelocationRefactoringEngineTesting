package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Abstract source class (public modifier, same package as target)
public abstract class SourceAbstractClass {
    // Static field for depends_on_static_field feature
    protected static String staticField = "source_static_value";

    // Member inner class (source class feature)
    public class MemberInnerClass {
        public String getInnerData() {
            return "inner_class_data";
        }
    }

    // Instance method for access_instance_method feature
    public String instanceMethod() {
        return "instance_method_result";
    }

    // Varargs method to refactor (default access, returns base type (int), has target parameter)
    int moveTargetMethod(TargetAbstractClass... targetParams) {
        // Override_violation: method with same signature as hypothetical supertype but incompatible return type
        // Simulate override violation (no actual superclass, intentional signature conflict)
        @SuppressWarnings("unused")
        String invalidOverride = "";

        // Type declaration statement feature
        MemberInnerClass innerObj = new MemberInnerClass();
        
        // Depends_on_static_field feature
        String staticVal = SourceAbstractClass.staticField;

        // Access_instance_method feature
        String instanceMethodResult = this.instanceMethod();

        int count = 0;
        int total = 0;

        // Do statement feature
        do {
            // Empty statement feature
            ;

            if (count >= targetParams.length) {
                count++;
                continue;
            }

            // Variable call feature
            TargetAbstractClass targetParam = targetParams[count];
            if (targetParam != null) {
                // Access target's static nested class
                TargetAbstractClass.TargetStaticNested nestedObj = new TargetAbstractClass.TargetStaticNested();
                total += nestedObj.getNestedValue();
            }

            // Call subclass method (call_method feature)
            SubClass subClass = new SubClass();
            List<String> subResult = subClass.protectedDoWhileMethod();
            total += subResult.size();

            count++;
        } while (count < 10);

        // Return base type (int)
        return total;
    }

    // Method with local inner class (source class feature)
    public void methodWithLocalInner() {
        // Local inner class
        class LocalInnerClass {
            int localValue = 5;
        }
        LocalInnerClass localObj = new LocalInnerClass();
        System.out.println(localObj.localValue);
    }
}

// Abstract target class (abstract modifier, same package as source)
abstract class TargetAbstractClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public int getNestedValue() {
            return 10;
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}

// Subclass for call_method feature (type: sub_class)
class SubClass extends SourceAbstractClass {
    // Protected method (modifier: protected) in do-while (pos: do-while)
    @Override
    public void abstractMethod() {} // Implement abstract method from parent

    protected List<String> protectedDoWhileMethod() {
        List<String> result = new ArrayList<>();
        int i = 0;

        // Do-while position for call_method
        do {
            // Constructor feature (call subclass constructor)
            SubClass self = new SubClass();
            // Super.methodName(arguments) feature
            String superResult = super.instanceMethod();
            result.add(superResult + "_" + i);
            i++;
        } while (i < 3);

        return result;
    }
}