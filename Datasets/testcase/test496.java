package com.refactor.movemethod;

import java.util.function.Runnable;

// Interface for source/target class implements feature
interface RefactorInterface {
    void interfaceMethod();
}

// Source normal class (private modifier, same package, implements + anonymous inner + local inner class)
private class SourceClass implements RefactorInterface {
    // Per_condition: source contains target class field
    private final TargetClass targetField = new TargetClass();
    
    // Private field for access_outer_private feature
    private String outerPrivateField = "private_outer_value";

    // Anonymous inner class (source_class feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Implement interface method
    @Override
    public void interfaceMethod() {
        sourceAnonymous.run();
    }

    // Instance method to refactor (protected access, returns Object, source position)
    protected Object refactorMethod() throws IllegalArgumentException { // requires_throws feature
        // Variable call feature
        sourceAnonymous.run();
        String varCall = outerPrivateField;

        // Local inner class (source_class feature) - depends_on_inner_class feature
        class SourceLocalInner {
            String processPrivateField() {
                // Access_outer_private feature (access outer class private field)
                return SourceClass.this.outerPrivateField + "_processed";
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        varCall = localInner.processPrivateField(); // depends_on_inner_class

        // Do statement feature
        int count = 0;
        do {
            varCall += count;
            count++;
        } while (count < 3);

        // Return statement feature (return Object type)
        if (targetField == null) {
            throw new IllegalArgumentException("Target field is null"); // requires_throws
        }
        return varCall + targetField.getStaticNestedValue();
    }

    // Inner class for call_method (inner_class type)
    private class SourceInnerClass {
        // Static code block for call_method pos
        static {
            new SourceInnerClass().callMethod();
        }

        // Call method (private modifier, inner_class type, static code blocks pos, void return)
        private void callMethod() {
            SourceClass outerInstance = new SourceClass();
            // Instance feature (call instance method)
            try {
                // superTypeReference.methodName(arguments) target_feature
                RefactorInterface superType = outerInstance;
                superType.interfaceMethod(); // Super type reference method call
                outerInstance.refactorMethod();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}

// Target normal class (private modifier, javadoc + implements + static nested class target_feature)
/**
 * TargetClass - Javadoc target_feature
 * Implements RefactorInterface to fulfill 'implements' target_feature
 * Contains static nested class as required target_feature
 */
private class TargetClass implements RefactorInterface {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private String nestedValue = "static_nested_value";
        public String getValue() { return nestedValue; }
    }

    // Implement interface method
    @Override
    public void interfaceMethod() {
        new TargetStaticNested().getValue();
    }

    // Method to access static nested class
    public String getStaticNestedValue() {
        return new TargetStaticNested().getValue();
    }
}