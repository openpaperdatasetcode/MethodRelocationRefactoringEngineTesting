package com.refactoring.movemethod;

import java.io.IOException;

// Source class: normal, protected modifier, same package, member inner + static nested classes
protected class SourceClass {
    // per_condition: source contains field of target
    private TargetClass.TargetInnerRecursive targetField = new TargetClass().new TargetInnerRecursive();

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String staticNestedField = "static_nested_6114";
    }

    // Member inner class (source feature)
    private class SourceMemberInner {
        private void instanceHelperMethod(String val) {
            System.out.println("Helper method called with: " + val);
        }
    }

    // Instance method for access_instance_method feature
    private void sourceInstanceMethod() throws IOException {
        if (targetField == null) {
            throw new IOException("Target inner recursive field is null");
        }
    }

    // Method to be refactored: instance, base type return (int), public access, source position
    // method types parameter is:none
    public int methodToMove() throws IOException {
        // IOException feature
        try {
            this.sourceInstanceMethod(); // access_instance_method feature
        } catch (IOException e) {
            // no_new_exception (no explicit new Exception instantiation)
            e.printStackTrace();
            return -1;
        }

        // variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        String varCall = staticNested.staticNestedField;
        targetField.innerData = varCall;

        // Return base type (int)
        return targetField.innerValue;
    }

    // Call method: source type, private modifier, normal, instanceReference.methodName(), pos=ternary, returns Object
    private Object callMethod(boolean condition) {
        SourceClass sourceInstance = new SourceClass();
        // pos: ternary operators, target_feature: instanceReference.methodName(arguments)
        return condition 
            ? sourceInstance.methodToMove() 
            : new SourceClass().methodToMove();
    }
}

// Target class: normal, public modifier, anonymous inner class (target_feature)
public class TargetClass {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        int innerValue = 6114;
        String innerData;

        // Anonymous inner class (target_feature)
        private Runnable anonymousProcessor = new Runnable() {
            @Override
            public void run() {
                innerValue *= 2;
            }
        };

        public void process() {
            anonymousProcessor.run();
        }
    }
}