package com.refactor.movemethod;

import java.util.Objects;

// Parent class for target_class extends feature
class TargetParentClass {
    protected Object parentMethod(int num) {
        return "parent_" + num;
    }
}

// Source normal class (protected modifier, same package, type parameter + static nested + local inner class)
protected class SourceClass<T extends CharSequence> {
    // Static nested class (source_class feature)
    public static class SourceStaticNested<U> {
        U nestedValue;
        
        public SourceStaticNested(U value) {
            this.nestedValue = value;
        }
        
        public U getNestedValue() {
            return nestedValue;
        }
    }

    // Annotation for has_annotation feature
    @SuppressWarnings("unchecked")
    // Static method to refactor (private access, generic type param, returns TargetClass Type, has target param - per_condition)
    private static <V extends Number> TargetClass refactorMethod(TargetClass targetParam) {
        // Variable call feature
        SourceStaticNested<String> staticNested = new SourceStaticNested<>("static_nested");
        String varCall = staticNested.getNestedValue();

        // Local inner class (source_class feature) - depends_on_inner_class feature
        class SourceLocalInner {
            String processValue(String val) {
                return val + "_processed";
            }
            
            // Uses_outer_this feature (via outer class instance)
            String useOuterThis(SourceClass<T> outerInstance) {
                return outerInstance.toString() + varCall;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        varCall = localInner.processValue(varCall); // depends_on_inner_class feature

        // Constructor invocation feature
        TargetClass targetInstance = new TargetClass("constructor_arg");

        // Accessor method (protected modifier, array initialization pos, 2 + parent_class + super.methodName() + accessor)
        protected Object accessorMethod() {
            // Array initialization position
            Object[] arr = new Object[]{
                super.toString(), // super.methodName(arguments) precursor
                new TargetParentClass().parentMethod(2) // parent_class + 2 feature
            };
            return arr[1]; // accessor returns Object
        }

        // Uses_outer_this feature (create outer instance to reference this)
        SourceClass<String> outerInstance = new SourceClass<>();
        localInner.useOuterThis(outerInstance);

        // No_new_exception feature (no explicit throw new Exception)
        if (targetParam == null) {
            targetParam = targetInstance;
        }

        return targetParam;
    }
}

// Target normal class (strictfp modifier, extends + anonymous inner class target_feature)
strictfp class TargetClass extends TargetParentClass {
    private String data;

    public TargetClass(String data) {
        this.data = data;
        // Anonymous inner class (target_feature)
        Runnable targetAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class: " + data);
            }
        };
        targetAnonymous.run();
    }

    // Accessor method (fulfills accessor feature)
    public String getData() {
        return data;
    }

    // Override parent method (super.methodName() support)
    @Override
    protected Object parentMethod(int num) {
        return super.parentMethod(num) + "_overridden";
    }
}