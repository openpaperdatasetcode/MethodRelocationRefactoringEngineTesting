package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface FinalClassMethodAnnotation {}

// Source final normal class (same package as target, type parameter + anonymous inner + static nested class)
final class SourceClass<T extends String> {
    // Field for ContinueStatement (this.field + 2)
    private int continueField = 2;
    // Instance variable for this.var = var feature
    private String instanceVar;

    // Static nested class feature (source class feature)
    private static class StaticNestedClass {
        // Method with override violation (final method to trigger violation)
        public final void nestedMethod() {}
    }

    /**
     * Method Javadoc feature
     * Target instance method returning List<String>, takes TargetClass as parameter
     * @param targetParam Target class parameter (precondition)
     * @return List<String> result
     */
    @FinalClassMethodAnnotation // has_annotation feature
    private List<String> targetMethod(TargetClass targetParam) {
        // Variable call feature
        String varCall = targetParam.anonymousField;
        List<String> resultList = new ArrayList<>();
        resultList.add(varCall);

        // this.var = var feature
        this.instanceVar = varCall + "_updated";
        resultList.add(this.instanceVar);

        // Empty statement feature
        ;

        // ContinueStatement (private modifier, this.field + 2, source pos)
        private int continueVar = this.continueField + 2;
        for (int i = 0; i < 5; i++) {
            if (i == continueVar) {
                continue; // ContinueStatement feature
            }
            resultList.add(String.valueOf(i));
        }

        // Override_violation feature (attempt to override final method)
        class LocalInnerForOverride extends StaticNestedClass {
            @Override
            public void nestedMethod() {} // Compile error (override violation)
        }

        // Anonymous inner class feature (source class feature, used in method)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                resultList.add("anonymous_inner");
            }
        };
        anonymousRunnable.run();

        // No_new_exception (implicit NPE if targetParam is null, no explicit new exception)
        if (targetParam == null) {
            throw new NullPointerException(); // Reuse standard exception, no new custom exception
        }

        return resultList;
    }

    // Anonymous inner class feature (source class level)
    public void sourceAnonymousInner() {
        Runnable outerAnonymous = new Runnable() {
            @Override
            public void run() {
                targetMethod(new TargetClass());
            }
        };
        outerAnonymous.run();
    }
}

// Target normal class (private modifier adjusted to package-private per Java spec, anonymous inner class feature)
class TargetClass {
    // Field accessed in source method
    String anonymousField = "target_anonymous_field";

    // Anonymous inner class target_feature
    public TargetClass() {
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                anonymousField = "updated_by_anonymous";
            }
        };
        anonymousInner.run();
    }
}