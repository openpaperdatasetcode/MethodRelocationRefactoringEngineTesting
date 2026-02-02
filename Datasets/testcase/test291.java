package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorMethod {}

// Private generic source class (same package as target, anonymous/local inner class features)
private class SourceClass<T> {
    // Member inner class for method_position: source_inner
    public class SourceInner {
        // Field referencing target class (per_condition: source contains target's field)
        private TargetClass<String> targetField;

        // EnhancedForStatement (private modifier, pos: source, target_feature: super.field, 2)
        private class EnhancedForStatement {
            // Super.field feature (simulated super field access)
            protected String superField = "super_field_" + 2; // Feature "2" (numeric literal)

            public <E> void process(List<E> list) {
                // Enhanced for loop implementation
                for (E element : list) {
                    System.out.println(superField + ": " + element);
                }
            }
        }

        // Overload method 1 (for overload_exist feature)
        protected Integer overloadMethod(int num) {
            return num * 3;
        }

        // Overload method 2 (for overload_exist feature)
        protected String overloadMethod(String str) {
            return str + "_processed";
        }

        // Instance method to refactor (protected access, returns TargetClass type, source_inner position)
        @RefactorMethod // has_annotation feature
        protected TargetClass<String> moveTargetMethod(TargetClass<String> targetParam) {
            // Type declaration statement feature
            EnhancedForStatement enhancedFor = new EnhancedForStatement();
            
            // CreationReference with number 3 (public modifier, exp: CreationReference)
            public List<String> refList = new ArrayList<>() {{
                add("ref_" + 3); // Feature "3" (numeric literal)
            }};

            // Variable call feature (EnhancedForStatement usage)
            enhancedFor.process(refList);
            
            // Overload_exist feature (call overloaded methods)
            Integer numResult = overloadMethod(3);
            String strResult = overloadMethod("target_data");

            // Set target field (per_condition fulfillment)
            this.targetField = targetParam;
            // Variable call to target's member inner class
            TargetClass<String>.TargetInner innerObj = targetField.new TargetInner();
            innerObj.setInnerData(strResult + "_" + numResult);

            // Local inner class (source class feature)
            class LocalInnerClass {
                private String localData = enhancedFor.superField;
                
                public String getLocalData() {
                    return localData;
                }
            }
            LocalInnerClass localInner = new LocalInnerClass();
            innerObj.setInnerData(innerObj.getInnerData() + "_" + localInner.getLocalData());

            // Anonymous inner class (source class feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Anonymous inner processing: " + innerObj.getInnerData());
                }
            };
            anonymousRunnable.run();

            return targetField;
        }
    }
}

// Generic target class (default modifier, same package as source, type parameter/member inner class features)
class TargetClass<T> {
    // Type parameter feature (T)
    private T data;

    public TargetClass(T data) {
        this.data = data;
    }

    // Member inner class (target_feature)
    public class TargetInner {
        private String innerData;

        public void setInnerData(String innerData) {
            this.innerData = innerData;
        }

        public String getInnerData() {
            return innerData;
        }
    }

    public T getData() {
        return data;
    }
}