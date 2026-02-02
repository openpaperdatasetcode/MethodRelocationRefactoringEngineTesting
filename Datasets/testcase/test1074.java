package com.refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Source annotation interface (type: @interface, modifier: empty, same package, implements, member inner class, static nested class)
@interface SourceAnnotation implements CustomAnnotationInterface {
    // Protected outer field for access_outer_protected
    String outerProtectedField = "outerProtectedValue";

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {
        String innerField = "innerClassValue";
    }

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass<T extends CharSequence> {}

    // Method to be refactored (instance, TargetAnnotation return, protected access, position: source)
    @Override
    default TargetAnnotation targetMethod() throws Exception { // requires_throws
        // Source contains target field (per_condition)
        TargetAnnotation targetField = new TargetAnnotation() {};
        
        // Type declaration statement
        List<? extends CharSequence> boundedList = new ArrayList<>(); // with_bounds
        MemberInnerSourceClass innerInstance = new MemberInnerSourceClass(); // depends_on_inner_class
        
        // Try statement
        try {
            // FieldAccess (numbers:2, default modifier, exp:FieldAccess)
            default int num = 2;
            String fieldAccess = targetField.localInnerField + num; // FieldAccess
            
            // Access outer protected field
            String outerProtected = SourceAnnotation.outerProtectedField; // access_outer_protected
            
            // Variable call
            String targetValue = targetField.value();
            boundedList.add(targetValue + outerProtected);
            
        } catch (NullPointerException e) {
            throw new Exception("Field access error", e); // requires_throws
        }
        
        return targetField;
    }
}

// Interface for implements feature (source_class)
interface CustomAnnotationInterface {
    TargetAnnotation targetMethod() throws Exception;
}

// Target annotation interface (type: @interface, modifier: default, local inner class target_feature)
@interface TargetAnnotation {
    String value() default "targetValue";
    String localInnerField = "localInnerValue";

    // Local inner class (target_feature)
    default void initLocalInner() {
        class LocalInnerClass {
            String localField = "targetLocalInner";
        }
        LocalInnerClass local = new LocalInnerClass();
        // Field access to local inner class (depends_on_inner_class)
        System.out.println(local.localField);
    }
}