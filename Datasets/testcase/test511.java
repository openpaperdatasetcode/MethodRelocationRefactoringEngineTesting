package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {}

// Source normal class (default modifier, same package, member inner + anonymous inner class)
class SourceClass {
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "outer_protected_2"; // Number 2 for feature

    // Anonymous inner class (source_class feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class: " + outerProtectedField);
        }
    };

    // Member inner class (source_class feature, method_position: source_inner)
    public class SourceInnerClass {
        // has_annotation feature
        @MethodAnnotation
        // Static method to refactor (strictfp access, returns TargetClass Type, target param - per_condition)
        public strictfp static TargetClass refactorMethod(TargetClass.TargetInner targetParam) {
            // Variable call feature
            sourceAnonymous.run();
            String varCall = targetParam.getInnerData();

            // Access_outer_protected feature
            varCall += SourceClass.this.outerProtectedField;

            // With_bounds feature (generic type with bounds)
            class BoundedClass<T extends Number & Comparable<T>> {
                T process(T val) {
                    return val.compareTo((T) Integer.valueOf(2)) > 0 ? val : (T) Integer.valueOf(2); // Number 2
                }
            }
            BoundedClass<Integer> boundedInstance = new BoundedClass<>();
            int boundedResult = boundedInstance.process(2); // Number 2
            varCall += "_bounded_" + boundedResult;

            // CreationReference (numbers=2, default modifier)
            default Supplier<TargetClass.TargetInner> creationRef = TargetClass.TargetInner::new; // CreationReference exp
            TargetClass.TargetInner newInner = creationRef.get();
            newInner.setInnerData("creation_ref_2"); // Number 2

            // Do statement feature
            int count = 0;
            do {
                varCall += "_do_" + count;
                count++;
            } while (count < 2); // Number 2

            // Enhanced for statement feature
            List<String> list = new ArrayList<>();
            list.add("enhanced_2_1"); // Number 2
            list.add("enhanced_2_2"); // Number 2
            for (String s : list) { // Enhanced for statement
                varCall += "_" + s;
            }

            // No_new_exception feature (no explicit throw new Exception)
            TargetClass target = new TargetClass();
            target.setData(varCall);
            target.setInner(newInner);
            return target;
        }
    }
}

// Target normal class (private modifier, member inner class target_feature)
private class TargetClass {
    private String data;
    private TargetInner inner;

    // Member inner class (target_feature - target_inner)
    public class TargetInner {
        private String innerData;

        public TargetInner() {
            this.innerData = "default_inner_2"; // Number 2
        }

        public TargetInner(String innerData) {
            this.innerData = innerData;
        }

        public String getInnerData() {
            return innerData;
        }

        public void setInnerData(String innerData) {
            this.innerData = innerData;
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TargetInner getInner() {
        return inner;
    }

    public void setInner(TargetInner inner) {
        this.inner = inner;
    }
}