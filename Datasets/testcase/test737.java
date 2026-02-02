import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: abstract, default modifier, same package, two static nested classes
abstract class SourceClass {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Private field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";

    // Static nested class 1 (source_class feature)
    static class StaticNestedClass1 {
        public void helperMethod() {}
    }

    // Static nested class 2 (source_class feature)
    static class StaticNestedClass2 {
        public void anotherHelper() {}
    }

    // Instance method to refactor: public, void return, source position, all method features
    @CustomAnnotation // has_annotation feature
    public void methodToRefactor(TargetClass targetParam) { // method types parameter is Target class
        // Variable call feature
        String localVar = "localVar";
        localVar = outerPrivateField; // access_outer_private feature

        // Synchronized statement feature
        synchronized (this) {
            localVar += "_synced";
        }

        // Expression statement feature
        int exprResult = localVar.length() + targetParam.innerField;

        // Access instance method feature
        targetParam.new MemberInnerClass().instanceMethod();

        // Override violation (method not properly overriding despite signature match)
        // Intentionally violates override rules (e.g., broader access than parent)
        @SuppressWarnings("unused")
        class OverrideViolation extends TargetClass {
            @Override
            public void instanceMethod() {} // Violation if parent is private (override_violation)
        }

        // No new exception (no throw statements, satisfies no_new_exception)
        // Empty block to confirm no exception instantiation
    }

    // Instance method for access_instance_method reference
    private void instanceMethodReference() {}
}

// Target class: abstract, default modifier, member inner class target_feature
abstract class TargetClass {
    int innerField = 10;

    // Member inner class (target_feature)
    class MemberInnerClass {
        public void instanceMethod() {}
    }

    // Target class for method relocation
    public class target {
        // Placeholder for moved method (matches signature)
        @CustomAnnotation
        public void methodToRefactor(TargetClass targetParam) {
            SourceClass source = new SourceClass() {}; // Instantiate abstract source
            source.methodToRefactor(targetParam);
        }
    }
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}