package com.refactor;

import java.lang.reflect.Method;
import java.util.List;

// Sealed interface for permits feature
sealed interface SealedInterface permits SourceClass {}

// Functional interface for implements feature
interface Processable<T> {
    T process(T... params);
}

// Source class: default modifier, same package as target, implements + permits + static nested + anonymous inner class
final class SourceClass implements SealedInterface, Processable<TargetClass.TargetInner> {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "private_value";
    // Static field for depends_on_static_field feature
    private static String staticField = "static_value";

    // Static nested class feature
    public static class StaticNestedClass {
        public static void helperMethod(TargetClass.TargetInner inner) {
            inner.setValue(staticField);
        }
    }

    // Member inner class (source_inner_rec hierarchy)
    public class MemberInnerClass {
        // Inner recursive class for source_inner_rec method position
        public class InnerRecursiveClass {
            // Method: varargs, return TargetClass Type (TargetInner), protected access, source_inner_rec position
            protected TargetClass.TargetInner processTarget(TargetClass.TargetInner... targetParams) {
                // Variable call (target parameter)
                TargetClass.TargetInner inner = targetParams.length > 0 ? targetParams[0] : new TargetClass().new TargetInner();
                
                // Access outer private field
                inner.setValue(SourceClass.this.outerPrivateField);
                
                // Depends on static field
                inner.setValue(staticField + "_" + inner.getValue());
                
                // Overload exist feature (overloaded method)
                processTarget(inner, staticField);
                
                // Used by reflection
                try {
                    Method setValueMethod = TargetClass.TargetInner.class.getMethod("setValue", String.class);
                    setValueMethod.invoke(inner, "reflection_value");
                } catch (Exception e) {
                    // No new exception - wrap existing
                    throw new RuntimeException(e);
                }
                
                return inner;
            }

            // Overloaded method (overload_exist feature)
            protected TargetClass.TargetInner processTarget(TargetClass.TargetInner inner, String suffix) {
                inner.setValue(inner.getValue() + suffix);
                return inner;
            }
        }
        
        void callInnerRecursive(TargetClass.TargetInner inner) {
            InnerRecursiveClass innerRec = new InnerRecursiveClass();
            innerRec.processTarget(inner);
        }
    }

    // Anonymous inner class feature
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetClass.TargetInner inner = new TargetClass().new TargetInner();
            MemberInnerClass memberInner = new MemberInnerClass();
            memberInner.callInnerRecursive(inner);
        }
    };

    // Implements feature (Processable interface)
    @Override
    public TargetClass.TargetInner process(TargetClass.TargetInner... params) {
        return new MemberInnerClass().new InnerRecursiveClass().processTarget(params);
    }
}

// Target class: public modifier, local inner class feature
public class TargetClass {
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

    // Local inner class (target_feature)
    public void targetMethod() {
        class LocalInnerClass {
            public void processInner(TargetInner inner) {
                inner.setValue("local_" + inner.getValue());
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        local.processInner(new TargetInner());
    }
}