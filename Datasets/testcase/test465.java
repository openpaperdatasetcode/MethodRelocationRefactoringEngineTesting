package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorTestAnnotation {}

// Source normal class (public modifier, same package as target, local + anonymous inner class)
public class SourceClass {
    // Precondition: source contains target class field
    private TargetClass targetField = new TargetClass();
    
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "outerProtectedValue";

    // Member inner class (source_inner for method position)
    public class MemberInnerClass<T extends CharSequence> { // with_bounds feature (T bounded to CharSequence)
        /**
         * Method Javadoc feature
         * This is the target instance method returning TargetClass type
         * @param targetParam Nested inner class parameter of target
         * @return TargetClass instance
         */
        @RefactorTestAnnotation // has_annotation feature
        public strictfp TargetClass targetMethod(TargetClass.NestedInnerInAnonymous targetParam) {
            // Variable call feature
            String varCall = targetParam.innerField;
            T boundedVar = (T) varCall; // Use bounded type (with_bounds)
            System.out.println(boundedVar.length());

            // Access_outer_protected feature (access outer class's protected field)
            String accessedProtected = SourceClass.this.outerProtectedField;
            targetParam.innerField = accessedProtected;

            // No_new_exception (implicit NPE if targetParam is null, no explicit new exception)
            if (targetParam == null) { exception)
            if (targetParam == null) {
                throw new NullPointerException(); // No new custom exception, reuse standard NPE
            }

            return targetField; // Return TargetClass type
        }
    }

    // Local inner class feature (source class feature)
    public void sourceLocalInnerClass() {
        class LocalInnerClass {
            void invokeTargetMethod() {
                MemberInnerClass<String> inner = new MemberInnerClass<>();
                inner.targetMethod(targetField.new NestedInnerInAnonymous());
            }
        }
        new LocalInnerClass().invokeTargetMethod();
    }

    // Anonymous inner class feature (source class feature)
    public void sourceAnonymousInnerClass() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                MemberInnerClass<String> inner = new MemberInnerClass<>();
                inner.targetMethod(targetField.new NestedInnerInAnonymous());
            }
        };
        anonymousRunnable.run();
    }
}

// Target normal class (public modifier, anonymous inner class target_feature)
public class TargetClass {
    // Nested inner class for target_inner_rec context
    public class NestedInnerInAnonymous {
        String innerField = "targetInnerField";
    }

    // Anonymous inner class target_feature
    public TargetClass() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                new NestedInnerInAnonymous().innerField = "anonymousInnerUpdate";
            }
        };
        anonymousRunnable.run();
    }
}