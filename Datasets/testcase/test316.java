package com.refactor;

import com.target.TargetGenericClass;
import java.io.IOException;

// Different package target helper for ConstructorInvocation pos (diff_package_target)
package com.target;

// Target generic class: protected modifier, static nested class feature
protected class TargetGenericClass<T> {
    // Target inner class (target_inner)
    public class TargetInner {
        T field; // obj.field feature for ConstructorInvocation
        public TargetInner(T field) {
            this.field = field;
        }
        public TargetInner() {
            this((T) "default_1"); // 1 feature
        }
    }

    // Static nested class (target_feature)
    public static class StaticNestedClass<T> {
        // ConstructorInvocation: private modifier, obj.field + 1, pos: diff_package_target
        private static <T> TargetInner createInner(T value) {
            return new TargetGenericClass<T>().new TargetInner((T) (value + "_1")); // obj.field + 1
        }
    }
}

// Back to refactor package
package com.refactor;

import com.target.TargetGenericClass;
import java.io.IOException;

// Super class for overriding feature
class SuperSourceClass<T> {
    public Object processTarget(TargetGenericClass<T>.TargetInner target) throws IOException {
        return target;
    }
}

// Source generic class: default modifier, same package as target, anonymous inner + member inner class
class SourceGenericClass<T> extends SuperSourceClass<T> {
    // per_condition: source contains field of target
    private TargetGenericClass<String>.TargetInner targetField = new TargetGenericClass<String>().new TargetInner("field_1");

    // Member inner class feature
    public class MemberInnerClass {
        public void validateInner(TargetGenericClass<String>.TargetInner inner) throws IOException {
            if (inner.field == null) {
                throw new IOException("Field is null (1)"); // 1 feature + requires_throws
            }
        }
    }

    // Method: overriding, return Object, public access, in source class, requires_throws (IOException)
    @Override
    public Object processTarget(TargetGenericClass<T>.TargetInner targetParam) throws IOException {
        // Type declaration statement
        TargetGenericClass<T>.TargetInner inner;
        MemberInnerClass memberInner;
        
        // Variable call (target parameter/field)
        inner = targetParam != null ? targetParam : (TargetGenericClass<T>.TargetInner) targetField;
        
        // ConstructorInvocation from diff_package_target (obj.field + 1)
        inner = (TargetGenericClass<T>.TargetInner) com.target.TargetGenericClass.StaticNestedClass.createInner("invoked_1");
        
        // Super constructor invocation (via anonymous inner class)
        TargetGenericClass<T> targetInstance = new TargetGenericClass<T>() {};
        
        // Constructor invocation
        inner = targetInstance.new TargetInner((T) "constructor_1"); // 1 feature
        
        // If statement
        if (inner.field.equals("constructor_1")) { // 1 feature
            memberInner = new MemberInnerClass();
            memberInner.validateInner((TargetGenericClass<String>.TargetInner) inner);
        }
        
        // Anonymous inner class feature
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                inner.field = (T) ("anonymous_1"); // 1 feature
            }
        };
        anonymousInner.run();
        
        return inner;
    }
}