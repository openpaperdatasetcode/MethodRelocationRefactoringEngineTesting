package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

// Diff package class for SwitchStatement pos: diff_package_others
package com.other;
public class DiffPackageClass {
    // For SwitchStatement's super.field feature
    public static class SuperClass {
        transient int superField = 3;
    }
}

package com.refactoring.movemethod;
import com.other.DiffPackageClass;

// Source class: private, static nested class, anonymous inner class, same package as target
private class SourceClass extends com.other.DiffPackageClass.SuperClass {
    // Static nested class (source class feature)
    static class SourceStaticNestedClass {
        int nestedField = 1;
    }

    // Abstract method to refactor: abstract, Object return, private, in source class
    // Per_condition: method contains target class parameter
    private abstract Object methodToRefactor(TargetClass targetParam);

    // Concrete implementation (to satisfy abstract method & feature requirements)
    private Object implementMethodToRefactor(TargetClass targetParam) throws Exception { // requires_throws
        // Variable call (target parameter field)
        int value = targetParam.targetField;
        
        // Constructor invocation
        SourceStaticNestedClass staticNestedObj = new SourceStaticNestedClass();
        
        // Super constructor invocation
        super();
        
        // Empty statement
        ;
        
        // Enhanced for statement
        List<Integer> list = Arrays.asList(1, 2, 3);
        for (int num : list) { // enhanced for statement
            // While loop for recursion feature pos
            int count = 1; // recursion feature: 1
            while (count > 0) {
                // Recursion feature: new ClassName().method() & source
                new SourceClass().recursiveMethod(count);
                count--;
                // Continue statement
                continue;
            }
        }

        // SwitchStatement feature: transient modifier, super.field, 3 cases, diff_package_others
        transient void switchLogic() {
            switch (super.superField) { // super.field
                case 1: value += 1; break;
                case 2: value += 2; break;
                case 3: value += 3; break; // 3 cases
                default: value = 0;
            }
        }
        switchLogic();

        // Used by reflection feature
        try {
            Method method = SourceClass.class.getDeclaredMethod("methodToRefactor", TargetClass.class);
            method.setAccessible(true);
            method.invoke(this, targetParam);
        } catch (Exception e) {
            throw e; // requires_throws
        }

        // Anonymous inner class (source class feature)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };
        runnable.run();

        return value;
    }

    // Recursion feature: private modifier, source type, recursion
    private Object recursiveMethod(int num) {
        if (num <= 0) return num;
        return recursiveMethod(num - 1); // recursion
    }

    // Inner class for call_method (type: inner_class)
    class SourceInnerClass {
        // call_method: default modifier, overloading, super.methodName(), Lambda pos, int return
        int callMethod() {
            // Lambda expressions pos
            Runnable lambda = () -> {
                // super.methodName() feature
                SourceClass.super.toString();
                // Overloading feature: call overloaded method
                callMethod(1);
            };
            lambda.run();
            return new SourceClass().implementMethodToRefactor(new TargetClass()).hashCode();
        }

        // Overloading feature for call_method
        int callMethod(int param) {
            return param;
        }
    }
}

// Target class: protected, static nested class, same package as source
protected class TargetClass {
    // Field for per_condition (method has target parameter)
    int targetField = 6394;

    // Static nested class (target class feature)
    static class TargetStaticNestedClass {
        int nestedValue = 0;
    }
}