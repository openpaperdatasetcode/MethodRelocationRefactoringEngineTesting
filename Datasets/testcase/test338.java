package com.refactoring.movemethod;

import java.lang.reflect.Method;

// Private record source class (same package as target)
private record SourceRecord(String data) {
    // Source contains target class field (satisfies per_condition)
    private final TargetRecord targetField = new TargetRecord("target_data");

    // Static nested class (source feature)
    public static class StaticNestedClass {}

    // Member inner class (source feature)
    public class MemberInnerClass {
        protected String outerProtectedField = "protected_value";
    }

    // Abstract method holder interface (record can't have abstract methods directly)
    protected interface AbstractMethodHolder {
        // Abstract method (protected access, returns TargetRecord, source position)
        @CustomAnnotation // Annotation with number 1, public modifier
        TargetRecord abstractMethod(TargetRecord targetParam);
    }

    // Implementation of abstract method with all features
    protected TargetRecord methodImplementation(TargetRecord targetParam) {
        // Variable call
        String localVar = this.data();
        MemberInnerClass inner = new MemberInnerClass();

        // Labeled statement
        label: {
            if (localVar.isEmpty()) {
                break label;
            }
            localVar += targetParam.data();
        }

        // Expression statement
        localVar = localVar.toUpperCase();

        // Access outer protected field
        String protectedVal = inner.outerProtectedField;

        // Super keywords
        super.toString();

        // NullPointerException (no new exception - check null)
        if (targetParam == null) {
            throw new NullPointerException("Target parameter is null");
        }

        // Used by reflection
        try {
            Method method = TargetRecord.class.getDeclaredMethod("data");
            method.setAccessible(true);
            String reflectedData = (String) method.invoke(targetField);
            localVar += reflectedData;
        } catch (Exception e) {
            // No new exception (no instantiation of new Exception)
            localVar = "reflection_error";
        }

        return targetField;
    }

    // Public annotation with number 1 (matches exp: Annotation, numbers:1, modifier:public)
    @interface CustomAnnotation {
        int value() default 1;
    }
}

// Final record target class (same package as source)
final record TargetRecord(String data) {
    // Member inner class (target feature)
    public class MemberInnerClass {
        private String innerData = data;
    }
}