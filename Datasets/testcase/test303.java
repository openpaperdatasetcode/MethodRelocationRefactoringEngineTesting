package com.refactor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {
    String value() default "source_inner";
}

// Source class: generic class, default modifier, same package as target, anonymous inner + local inner class
class SourceClass<T> {
    private String outerPrivateField = "private_value"; // access_outer_private feature

    // Local inner class (source feature)
    public void sourceMethod() {
        class LocalInnerClass {
            // Abstract method: base type (int) return, default access, source_inner position
            @MethodAnnotation // has_annotation feature
            abstract int processTarget(TargetClass<String>.TargetInner targetParam);

            // Override violation (attempt to override abstract method with incompatible signature)
            @Override
            public String toString() { // Simulated override violation context
                // Type declaration statement
                TargetClass<String>.TargetInner inner;
                
                // Variable call (target parameter)
                inner = new TargetClass<String>().new TargetInner();
                inner.setValue(SourceClass.this.outerPrivateField); // access_outer_private
                
                // If statement
                if (inner.getValue() == null) {
                    try {
                        // No new exception - wrap existing
                        throw new RuntimeException("Null value");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                
                // call_method: inner_class type, public modifier, instance + OuterClass.InnerClass.methodName(), pos: switch, void return
                switch (inner.getValue()) {
                    case "private_value":
                        SourceClass.InnerClass.staticInnerMethod(inner); // OuterClass.InnerClass.methodName()
                        break;
                    default:
                        new InnerClass().instanceInnerMethod(inner);
                }
                
                return super.toString();
            }
        }

        // Anonymous inner class (source feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                LocalInnerClass local = new LocalInnerClass() {
                    // Implement abstract method (override violation if signature mismatches)
                    @Override
                    int processTarget(TargetClass<String>.TargetInner targetParam) {
                        return targetParam.getValue().length();
                    }
                };
                local.processTarget(new TargetClass<String>().new TargetInner());
            }
        };
        anonymousInner.run();
    }

    // Inner class for call_method
    public static class InnerClass {
        // Instance method for call_method feature
        public void instanceInnerMethod(TargetClass<String>.TargetInner inner) {
            inner.setValue("processed");
        }
        
        // Static method for OuterClass.InnerClass.methodName()
        public static void staticInnerMethod(TargetClass<String>.TargetInner inner) {
            inner.setValue("static_processed");
        }
    }
}

// Target class: generic class, private modifier, member inner class feature
private class TargetClass<T> {
    // Member inner class (target_inner)
    public class TargetInner {
        private T value;
        
        public T getValue() {
            return value;
        }
        
        public void setValue(T value) {
            this.value = value;
        }
    }
}