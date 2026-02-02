// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.function.Supplier;

// Source normal class (default modifier, member inner + static nested class, different package)
class SourceClass {
    // Static nested class (source class feature)
    static class StaticNestedClass {
        private int nestedValue = 1; // numbers:1 for CreationReference

        public int getNestedValue() {
            return nestedValue;
        }
    }

    // Member inner class (source_inner position for refactor method)
    class SourceInnerClass {
        private int innerVar = 10;

        // Annotation for has_annotation feature (duplicated as per spec)
        @CustomAnnotation
        @CustomAnnotation
        // Method to refactor (instance, return Object, private access, source_inner)
        private Object refactorMethod(TargetClass targetParam) {
            // Variable call feature
            StaticNestedClass staticNested = new StaticNestedClass();
            int localVar = staticNested.getNestedValue(); // depends_on_inner_class

            // Expression statement feature
            localVar += this.innerVar;

            // CreationReference (private modifier, numbers:1)
            Supplier<TargetClass> creationRef = TargetClass::new; // private modifier CreationReference

            // Switch statement feature
            Object result;
            switch (localVar) {
                case 1:
                    result = creationRef.get(); // use CreationReference
                    break;
                case 11:
                    result = targetParam.getInnerData();
                    break;
                default:
                    result = "default";
                    break;
            }

            // No new exception feature (no throw new Exception)
            if (targetParam == null) {
                return result;
            }

            // Process target parameter (per_condition: method has target parameter)
            result = targetParam.process(1); // numbers:1
            return result;
        }
    }

    // Constructor to initialize inner class
    public SourceClass() {
        SourceInnerClass inner = new SourceInnerClass();
    }
}

// Custom annotation for has_annotation feature
@interface CustomAnnotation {}

// Target class package (different from source)
package com.refactoring.target;

// Target normal class (protected modifier, member inner class feature)
protected class TargetClass {
    private String innerData = "target_data_1";

    // Member inner class (target_feature)
    public class TargetInnerClass {
        public String getData() {
            return innerData;
        }
    }

    // Method for target parameter processing
    public Object process(int num) {
        return this.innerData + "_" + num;
    }

    // Getter for inner data
    public String getInnerData() {
        return innerData;
    }

    // Constructor for CreationReference
    public TargetClass() {}
}