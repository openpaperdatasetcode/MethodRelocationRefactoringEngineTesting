package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Interface for source_class implements feature
interface SourceInterface {
    <T> int processTarget(T target);
}

/**
 * Target class (normal class, protected modifier, javadoc + member inner class features)
 * This class contains inner class used as target_inner
 */
protected class TargetClass {
    int targetField = 1; // For AssertStatement this.field + 1

    // Member inner class (target_feature)
    public class TargetInner {
        String innerField; // For per_condition and variable call

        // Instance method for access_instance_method feature
        public String getProcessedField(String suffix) {
            return this.innerField + "_processed_" + suffix + "_" + 1;
        }
    }
}

// Source class (normal class, default modifier, same package, implements + anonymous inner + static nested class)
class SourceClass implements SourceInterface {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        static <T> void helperMethod(T target) {
            System.out.println("Static nested processing: " + target);
        }
    }

    // Member inner class (source_inner for method_position)
    class SourceInnerClass {
        // AssertStatement (public modifier, this.field, 1, pos=source)
        public <T> void assertStatement(TargetClass.TargetInner target) {
            assert target.innerField != null : "Inner field cannot be null (1)"; // 1 from target_feature
            assert target.innerField.length() > 0 : "Inner field length > 0 (1)";
            TargetClass.this.targetField = 1; // this.field + 1 from target_feature
        }

        // Method to be refactored (generic, base type return, default access, source_inner position)
        @RefactorAnnotation // has_annotation feature
        <T> int moveMethod(TargetClass.TargetInner targetParam) {
            // Per_condition: contains target parameter (target_inner)
            if (targetParam == null) {
                return 0;
            }

            // Type declaration statement
            T typeParam = (T) targetParam.innerField;
            SourceStaticNested staticNestedInstance = new SourceStaticNested();

            // AssertStatement invocation (pos=source)
            assertStatement(targetParam);

            // Variable call (access target inner field - per_condition)
            String varCall = targetParam.innerField;
            targetParam.innerField = (varCall == null ? "default_1" : varCall + "_var_modified"); // 1 from target_feature

            // Expression statement
            targetParam.innerField = targetParam.innerField.toUpperCase() + "_exp_" + 1;

            // Access instance method
            String instanceMethodResult = targetParam.getProcessedField("instance"); // access_instance_method

            // Anonymous inner class (source_feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    SourceStaticNested.helperMethod(targetParam.innerField);
                }
            };
            anonymousRunnable.run();

            // No new exception
            return instanceMethodResult.length() + 1; // base type return (int)
        }
    }

    // Implements interface method (required for implements feature)
    @Override
    public <T> int processTarget(T target) {
        return new SourceInnerClass().moveMethod((TargetClass.TargetInner) target);
    }
}