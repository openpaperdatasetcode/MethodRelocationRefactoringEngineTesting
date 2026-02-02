package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Superclass for super constructor invocation feature
class SuperClassForInvocation {
    protected String superField;

    public SuperClassForInvocation(String superField) {
        this.superField = superField;
    }
}

// Source normal class (final modifier, same package, anonymous inner + member inner class)
final class SourceClass {
    // Anonymous inner class (source feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Member inner class (source feature)
    public class SourceMemberInner {
        String innerField = "source_inner_field";
    }

    // Instance method to be moved (private, returns List<String>, no type parameter, source position)
    private List<String> moveableMethod(TargetClass targetParam) {
        List<String> result = new ArrayList<>();

        // Super constructor invocation feature (via anonymous subclass)
        SuperClassForInvocation superObj = new SuperClassForInvocation("super_value") {
            {
                // Initializer block with super constructor invocation
                System.out.println(super.superField);
            }
        };

        // Synchronized statement feature
        Object lock = new Object();
        synchronized (lock) {
            // Variable call feature (target inner class field + source inner field + super field)
            String varCall = targetParam.innerClass.innerField 
                    + new SourceMemberInner().innerField 
                    + superObj.superField;
            result.add(varCall);
        }

        // Execute anonymous inner class (source feature usage)
        anonymousInner.run();

        // No new exception instantiation (no_new_exception feature)
        return result;
    }
}

// Target normal class (private modifier, member inner class target feature)
private class TargetClass {
    // Member inner class (target_inner reference)
    public class TargetMemberInner {
        String innerField = "target_inner_field_7255";
    }

    // Instance of member inner class
    public final TargetMemberInner innerClass = new TargetMemberInner();
}