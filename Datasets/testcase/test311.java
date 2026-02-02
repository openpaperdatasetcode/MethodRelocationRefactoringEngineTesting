package com.refactor;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.function.Supplier;

// Source class: public normal class, same package as target, type parameter + two static nested classes
public class SourceClass<T extends TargetClass.TargetInner> {
    // per_condition: source contains field of target
    private TargetClass.TargetInner targetField = new TargetClass().new TargetInner();

    // First static nested class feature
    public static class FirstStaticNested<T> {
        public static <T> T createInstance(Supplier<T> supplier) {
            return supplier.get();
        }
    }

    // Second static nested class feature (duplicate as per spec)
    public static class SecondStaticNested<T> {
        public static void processInner(TargetClass.TargetInner inner) throws IOException {
            if (inner.getValue() == null) {
                throw new IOException("Value is null (2)"); // IOException + 2 feature
            }
        }
    }

    // Inner class for source_inner method position
    public class InnerClass {
        // Method: varargs, base type (int) return, protected access, source_inner position
        protected int processTarget(T... targetParams) {
            // Variable call (target parameter/field)
            T inner = targetParams.length > 0 ? targetParams[0] : (T) targetField;
            
            // Expression statement
            inner.setValue("processed_2"); // 2 feature
            
            // CreationReference: numbers=2, protected modifier, exp=CreationReference
            protected Supplier<TargetClass.TargetInner> creator = TargetClass.TargetInner::new; // CreationReference
            TargetClass.TargetInner newInner = FirstStaticNested.createInstance(creator);
            newInner.setValue("creation_2"); // 2 feature
            
            // IOException handling
            try {
                SecondStaticNested.processInner(inner);
                
                // Used by reflection
                Constructor<TargetClass.TargetInner> constructor = TargetClass.TargetInner.class.getConstructor();
                Method setValueMethod = TargetClass.TargetInner.class.getMethod("setValue", String.class);
                setValueMethod.invoke(constructor.newInstance(), "reflection_2");
                
                // call_method: source type, default modifier, static + superTypeReference.methodName(arguments), pos: exception handling statements, returns TargetClass Type
                TargetClass.TargetInner callResult = null;
                try {
                    callResult = SourceClass.staticCallMethod(inner); // superTypeReference.methodName(arguments)
                } catch (Exception e) {
                    // Exception handling statements position for call_method
                    throw new RuntimeException(e);
                }
                
                return callResult.getValue() != null ? 2 : 0; // 2 feature
            } catch (IOException e) {
                // No new exception - wrap existing
                throw new RuntimeException(e);
            } catch (Exception e) {
                // No new exception - wrap existing
                throw new RuntimeException(e);
            }
        }
    }

    // call_method: static feature, default modifier, pos: exception handling statements, returns TargetClass Type
    static TargetClass.TargetInner staticCallMethod(TargetClass.TargetInner inner) {
        inner.setValue("static_call_2"); // 2 feature
        return inner;
    }

    // Instance method to trigger inner class method
    public void triggerProcessing() {
        InnerClass innerClass = new InnerClass();
        innerClass.processTarget((T) targetField);
    }
}

// Target class: private modifier, anonymous inner class feature
private class TargetClass {
    // Target inner class (target_inner)
    public class TargetInner {
        private String value;
        
        public String getValue() {
            return value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetInner inner = new TargetInner();
            inner.setValue("anonymous_2"); // 2 feature
        }
    };
}