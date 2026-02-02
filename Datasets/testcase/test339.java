package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Private normal source class with type parameter (same package as target)
private class SourceClass<T extends CharSequence> { // type parameter + with_bounds
    // Anonymous inner class (source feature)
    private Consumer<String> anonymousInner = new Consumer<>() {
        @Override
        public void accept(String s) {
            System.out.println("Anonymous inner: " + s);
        }
    };

    // Instance method (protected access, List<String> return, target parameter)
    protected List<String> instanceMethod(TargetClass targetParam, T... args) {
        List<String> result = new ArrayList<>();
        // Variable call
        String localVar = "base_value";

        // Local inner class (source feature)
        class LocalInnerClass { // depends_on_inner_class
            void process(String val) {
                result.add(val);
            }

            // Call method (inner_class, protected, instance, this.methodName)
            @AnnotationWithAttribute(handler = LocalInnerClass::process) // pos: annotation attribute
            protected void innerInstanceMethod(String arg) {
                this.process(arg); // this.methodName(arguments)
            }
        }
        LocalInnerClass inner = new LocalInnerClass();

        // Expression statement
        localVar = localVar.concat("_modified");

        // Break statement
        loop:
        for (int i = 0; i < args.length; i++) {
            if (i == 1) break loop;
            // Depends on inner class (call inner class method)
            inner.innerInstanceMethod(args[i].toString());
        }

        // No new exception (no exception instantiation)
        return result;
    }

    // Annotation for call_method position (attribute values of annotations)
    @interface AnnotationWithAttribute {
        Consumer<String> handler();
    }
}

// Public normal target class (implements interface + static nested class)
public class TargetClass implements Runnable {
    // Static nested class (target feature)
    public static class TargetStaticNested {
        private String nestedValue;
    }

    @Override
    public void run() {
        // Implements Runnable
    }
}