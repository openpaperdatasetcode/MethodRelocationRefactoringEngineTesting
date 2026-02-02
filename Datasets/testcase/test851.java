package com.source;

import com.target.TargetClass;
import java.util.List;
import java.util.ArrayList;

strictfp class SourceClass<T> {
    // Per condition: source contains target class field
    TargetClass<String> targetField = new TargetClass<String>() {
        @Override
        public int innerMethod(int num) {
            return num * 2;
        }
    };

    // Local inner class in source (source_class feature)
    class SourceInnerClass {
        // Method to be moved (method_position: source_inner, access_modifier: default, return_type: Object)
        Object moveMethod() {
            // Variable call feature
            int localVar = 10;
            // Access outer protected feature
            String outerProtected = SourceClass.this.protectedField;
            
            // DoStatement feature (type: DoStatement, modifier: public, target_feature: ClassName.field, 1, pos: diff_package_others)
            do {
                localVar--;
                // ClassName.field access (diff package others)
                int otherField = com.others.OtherClass.staticField;
            } while (localVar > 1);

            // Static method feature (type: static, modifier: public, return_type: List<String>, pos: property assignment)
            List<String> staticResult = SourceInnerClass.staticHelperMethod();
            targetField.nestedStaticClass.staticProperty = staticResult;

            // Constructor invocation feature
            LocalInner localInner = new LocalInner();
            // Expression statement feature
            String exprStmt = outerProtected + localInner.innerField;
            // new ClassName().method() feature (static method feature)
            int newInstanceCall = new com.others.OtherClass().instanceMethod(5);

            // Access outer protected method
            SourceClass.this.protectedMethod();
            // Override violation (method with same signature as parent but incompatible return type)
            // Intentionally violates override contract for override_violation feature
            // No new exception feature (no throw new Exception)
            return exprStmt + newInstanceCall;
        }

        // Static method for method features (type: static, modifier: public, return_type: List<String>)
        public static List<String> staticHelperMethod() {
            // Parent class feature (call parent class static method)
            ParentClass.staticParentMethod();
            // Static feature (static variable access)
            staticVar = "test";
            // new ClassName().method()
            new com.target.TargetClass.NestedStaticClass().staticMethod();
            return new ArrayList<>();
        }

        // Local inner class (source_class feature)
        class LocalInner {
            String innerField = "local";
        }

        // Anonymous inner class (source_class feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymous inner class");
            }
        };
    }

    protected String protectedField = "protected";

    protected void protectedMethod() {
        // Call method feature (inner_class, public, overriding, instanceReference.methodName(arguments), pos: expression)
        SourceInnerClass innerInstance = new SourceInnerClass();
        int callResult = innerInstance.innerMethod(5); // instanceReference.methodName(arguments)
    }

    // Override violation parent method
    public String moveMethod() {
        return "parent";
    }
}

class ParentClass {
    public static void staticParentMethod() {}
    public static String staticVar;
}

package com.target;

import java.util.List;

abstract class TargetClass<T> implements Runnable {
    // Static nested class (target_feature)
    public static class NestedStaticClass {
        public List<String> staticProperty;
        public void staticMethod() {}
    }

    @Override
    public void run() {}

    // Call method feature (inner_class, public, overriding)
    public int innerMethod(int num) {
        return num;
    }
}

package com.others;

public class OtherClass {
    public static int staticField = 1;

    public int instanceMethod(int num) {
        return num;
    }
}