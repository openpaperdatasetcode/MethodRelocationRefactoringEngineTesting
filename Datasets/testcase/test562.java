package com.refactor;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: normal, default modifier, same package as target, static nested + member inner class
class SourceClass {
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "protectedValue";

    // Static nested class (source feature)
    static class SourceStaticNested {}

    // Member inner class (source feature)
    class SourceMemberInner {}

    // Annotation for has_annotation feature
    @Retention(RetentionPolicy.RUNTIME)
    @interface MethodAnnotation {}

    /**
     * Method to refactor: static, void return, private, contains target parameter (per_condition)
     */
    @MethodAnnotation // has_annotation
    private static void methodToMove(TargetClass... targetParams) throws IOException {
        // IOException (declared, no new exception thrown)
        if (targetParams == null) {
            throw new IOException("Target parameters cannot be null");
        }

        // Enhanced for statement
        List<TargetClass> targetList = new ArrayList<>();
        for (TargetClass target : targetParams) {
            targetList.add(target);
        }

        // Variable call (target parameter access)
        for (TargetClass target : targetList) {
            String targetVar = target.staticNestedField;
        }

        // Access outer protected field (via instance for static method context)
        SourceClass instance = new SourceClass();
        String protectedAccess = instance.outerProtectedField;

        // Call method: source type, protected modifier, accessor + superTypeReference in Lambda
        java.util.function.Supplier<TargetClass> lambda = () -> SourceClass.getTargetInstance("arg");

        // No new exception thrown
    }

    // Call method: source type, protected modifier, accessor, superTypeReference.methodName(arguments)
    protected static TargetClass getTargetInstance(String arg) {
        return SuperType.createTargetClass(arg);
    }
}

// Super type for superTypeReference feature
class SuperType {
    public static TargetClass createTargetClass(String arg) {
        return new TargetClass();
    }
}

// Target class: normal, default modifier (empty modifier), static nested class (target_feature)
class TargetClass {
    // Target field (used in variable call)
    public String staticNestedField = "targetValue";

    // Static nested class (target_feature)
    static class TargetStaticNested {}
}