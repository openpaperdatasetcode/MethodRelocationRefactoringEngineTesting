package com.refactoring.movemethod;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Others class for varargs method feature
class OthersClass {
    public int processVarargs(Object... args) {
        return args.length + 3;
    }
}

// Super class for super constructor invocation and super keywords
class SuperClass {
    protected String superField = "SUPER_FIELD";
    
    public SuperClass() {}
    
    public int superMethod(int val) {
        return val + 3;
    }
}

// Source generic class (public modifier, same package, static nested + member inner class)
public class SourceClass<T extends Number & Comparable<T>> {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "PRIVATE_DATA";

    // Static nested class (source feature)
    static class SourceStaticNested<U> {}

    // Member inner class (source feature)
    class SourceMemberInner extends SuperClass {
        @Override
        public int superMethod(int val) {
            // Override violation: change return type in invalid way (simulated)
            return super.superMethod(val);
        }
    }

    @RefactorAnnotation // has_annotation feature
    // Varargs method (private access, Object return, source position)
    private Object varargsMethod(TargetClass<T>.TargetInnerRec... params) throws IOException {
        // Per_condition: contains target parameter (variable call)
        if (params == null || params.length == 0) {
            // Throw statement
            throw new IOException("No target parameters provided");
        }

        // Super constructor invocation (via anonymous subclass)
        SuperClass superObj = new SuperClass() {};

        Object result = null;
        // For statement
        for (int i = 0; i < params.length; i++) {
            TargetClass<T>.TargetInnerRec param = params[i];
            // Variable call
            T targetVal = param.targetField;
            // Access instance field
            param.auxField = outerPrivateField + "_" + targetVal;
            // Super keywords
            String superData = superObj.superField;

            // Property assignment (pos for nested varargs method)
            int nestedResult = privateVarargsMethod(param, 3, new OthersClass());
            result = nestedResult;
        }

        // Override violation: invalid override attempt
        TestInterface invalidOverride = new TestInterface() {
            @Override
            public void testMethod() {}
            // Violation: attempt to override with different signature
            public String testMethod(int val) { return ""; }
        };

        return result;
    }

    // Nested varargs method (private modifier, base type return, property assignment pos)
    private int privateVarargsMethod(TargetClass<T>.TargetInnerRec rec, int num, OthersClass... others) {
        // 3: literal value usage
        if (num == 3) {
            int sum = 0;
            for (OthersClass other : others) {
                // instanceReference.methodName(arguments)
                sum += other.processVarargs(rec.targetField, 3);
            }
            return sum;
        }
        return num;
    }
}

// Target generic class (public modifier, local inner class feature)
public class TargetClass<T extends Number> {
    // Target inner record (target_inner_rec)
    class TargetInnerRec {
        T targetField;
        String auxField;

        public T getTargetField() {
            return targetField;
        }

        public void setTargetField(T targetField) {
            this.targetField = targetField;
        }
    }

    // Local inner class (target feature)
    public void targetMethod(T value) {
        class TargetLocalInner {
            void updateInnerRec(TargetInnerRec rec) {
                rec.targetField = value;
            }
        }
        new TargetLocalInner().updateInnerRec(new TargetInnerRec());
    }
}

// Supporting interface for override_violation
interface TestInterface {
    void testMethod();
}