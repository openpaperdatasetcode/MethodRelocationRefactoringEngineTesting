// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.lang.reflect.Field;
import java.util.List;

// Public abstract source class implementing interface
public abstract class SourceClass implements Runnable {
    // Field of target class (satisfies per_condition)
    private TargetClass.InnerClass targetField = new TargetClass().new InnerClass();

    // Member inner class (source feature)
    public class MemberInner {
        int x = 10;
    }

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in Source");
        }
    };

    // Overloading method 1 (void return, public access)
    public void overloadedMethod(List<String> listParam, TargetClass.InnerClass targetInnerParam) {
        // Variable call
        String localVar = "test";
        // Raw type usage
        List rawList = listParam;

        // OuterClass.this.x reference
        int outerX = SourceClass.this.new MemberInner().x;

        // Constructor invocation
        MemberInner inner = new MemberInner();

        // NullPointerException (no new exception - just check null)
        if (listParam == null) {
            throw new NullPointerException();
        }

        // Used by reflection (access target inner class field)
        try {
            Field field = TargetClass.InnerClass.class.getDeclaredField("innerValue");
            field.setAccessible(true);
            field.get(targetInnerParam);
        } catch (Exception e) {
            // No new exception instantiation
            return;
        }

        // Ternary operator with call to target final method
        String result = (targetInnerParam != null) ? targetInnerParam.finalTargetMethod(outerX) : "default";
    }

    // Overloading method 2 (overload of above)
    public void overloadedMethod(List<Integer> listParam) {
        // Overload implementation
    }

    @Override
    public void run() {
        // Implements Runnable
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Default abstract target class extending another class
abstract class TargetClass extends BaseClass {
    // Anonymous inner class (target feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in Target");
        }
    };

    // Target inner class (target_inner)
    public class InnerClass {
        private String innerValue = "targetInner";

        // Final target method (call_method: final modifier, String return)
        public final String finalTargetMethod(int arg) {
            // super.methodName(arguments)
            return super.toString() + " : " + (arg > 0 ? "valid" : "invalid");
        }
    }
}

// Base class for TargetClass extension
class BaseClass {
    @Override
    public String toString() {
        return "BaseClass";
    }
}