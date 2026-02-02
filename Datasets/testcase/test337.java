// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.Objects;

// Final normal source class with type parameter (different package from target)
final class SourceClass<T> {
    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in Source");
        }
    };

    // Inner class containing the target method (source_inner position)
    public class SourceInnerClass {
        // Varargs method (protected access, returns TargetClass, contains target parameter)
        @CustomAnnotation // has_annotation (first occurrence)
        @AnotherAnnotation // has_annotation (second occurrence)
        protected TargetClass targetMethod(TargetClass targetParam, String... args) {
            // Variable call
            String localVar = "labeled_statement_demo";
            int switchVar = 1;
            int whileCount = 0;

            // Labeled statement
            label: {
                if (args.length > 0) {
                    break label;
                }
                localVar += args.length;
            }

            // Switch statement
            switch (switchVar) {
                case 1:
                    localVar = "case_1";
                    break;
                default:
                    localVar = "default";
            }

            // While statement
            while (whileCount < 5) {
                localVar += whileCount;
                whileCount++;
            }

            // Constructor invocation
            SourceInnerClass innerInstance = new SourceInnerClass();

            // This.methodName(arguments) call
            this.helperMethod(localVar);

            // NullPointerException (no new exception - use Objects.requireNonNull)
            TargetClass nonNullTarget = Objects.requireNonNull(targetParam, "Target parameter cannot be null");

            // No new exception (no instantiation of new Exception)
            return nonNullTarget;
        }

        // Helper method for this.methodName call
        private void helperMethod(String arg) {
            // Local inner class (source feature)
            class LocalInner {
                String innerField = arg;
            }
            LocalInner localInner = new LocalInner();
        }
    }
}

// Custom annotations for has_annotation feature
@interface CustomAnnotation {}
@interface AnotherAnnotation {}

// Target class package (different from source)
package com.refactoring.target;

// Protected normal target class (empty target_feature)
protected class TargetClass {}