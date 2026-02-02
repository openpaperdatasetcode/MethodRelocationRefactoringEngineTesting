package com.refactor;

import java.util.List;
import java.util.ArrayList;

// Source class: normal, protected modifier, same package as target, permits + static nested + member inner class
sealed protected class SourceClass permits SourceSubClass {
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "protectedValue";
    
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    static class SourceStaticNested {}

    // Member inner class (source feature)
    class SourceMemberInner {}

    // Method to refactor: instance, List<String> return, default access modifier, in source class
    List<String> methodToMove() {
        // ReturnStatement (static modifier, this.field, 1, pos: source)
        static class ReturnHelper {
            private int field = 1;
            public int getField() {
                return this.field; // Return statement with this.field = 1
            }
        }
        ReturnHelper returnHelper = new ReturnHelper();
        int staticFieldVal = returnHelper.getField();

        // Super constructor invocation (implicit super() for Object constructor)
        super();

        // Constructor invocation (target inner class)
        TargetClass.TargetInner innerInstance = targetField.new TargetInner();

        // Variable call (target field access)
        String targetVar = innerInstance.innerField;

        // Access outer protected field
        String protectedAccess = SourceClass.this.outerProtectedField;

        // No new exception thrown
        List<String> result = new ArrayList<>();
        result.add(targetVar);
        result.add(protectedAccess);
        result.add(String.valueOf(staticFieldVal));
        return result;
    }
}

// Permits subclass for source class feature
non-sealed class SourceSubClass extends SourceClass {}

// Target class: normal, public modifier, anonymous inner class (target_feature)
public class TargetClass {
    // Target inner class (target_inner)
    class TargetInner {
        String innerField = "targetInnerFieldValue";
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(new TargetInner().innerField);
        }
    };
}