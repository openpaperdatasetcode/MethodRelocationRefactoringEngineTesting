package com.refactoring.movemethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Super class for overriding method
abstract class SuperSourceClass<T extends CharSequence & Comparable<T>> {
    protected abstract List<String> processTarget(TargetClass<T>.TargetInnerRec param) throws IOException;
}

// Others class for varargs method feature
class OthersClass {
    public void othersMethod(String... args) {}
}

// Source generic class (protected modifier, same package)
protected class SourceClass<T extends CharSequence & Comparable<T>> extends SuperSourceClass<T> {
    // Static nested class (source feature)
    static class SourceStaticNested<U extends Number> {}

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            TargetClass<T> target = new TargetClass<>();
            TargetClass<T>.TargetInnerRec rec = target.new TargetInnerRec();
            try {
                processTarget(rec);
            } catch (IOException e) {
                // No new exception
            }
        }
    };

    @RefactorAnnotation // has_annotation feature
    @Override // overriding method type
    protected List<String> processTarget(TargetClass<T>.TargetInnerRec param) throws IOException {
        List<String> result = new ArrayList<>();
        // Variable call (per_condition: contains target parameter)
        T targetValue = param.getValue();
        result.add(targetValue.toString());

        // with_bounds: use generic type with bounds
        SourceStaticNested<Integer> nested = new SourceStaticNested<>();

        // Lambda expressions (pos for varargs method)
        Runnable lambda = () -> {
            // Varargs method call (1, others_class, varargs, super.methodName)
            varargsMethod(param, 1, new OthersClass());
        };
        lambda.run();

        // IOException handling
        try {
            if (targetValue.length() == 0) {
                throw new IOException("Empty target value");
            }
        } catch (IOException e) {
            // no_new_exception: catch without throwing new exception
            result.add("error: " + e.getMessage());
        }

        // override_violation: incorrect override attempt
        TestInterface invalidOverride = new TestInterface() {
            @Override
            public void testMethod() {} // Correct override
            // Violation: attempt to override with different return type
            public String testMethod(int val) { return ""; }
        };

        return result;
    }

    // Varargs method (protected modifier, void return, lambda pos)
    protected void varargsMethod(TargetClass<T>.TargetInnerRec rec, int num, OthersClass... others) {
        // 1: literal value usage
        if (num == 1) {
            for (OthersClass other : others) {
                // super.methodName(arguments): call super method via anonymous subclass
                TargetClass<T>.TargetInnerRec subRec = new TargetClass<T>().new TargetInnerRec() {
                    @Override
                    public T getValue() {
                        return super.getValue();
                    }
                };
                // others_class: call method from others class
                other.othersMethod(subRec.getValue().toString());
            }
        }
    }
}

// Target generic class (public modifier)
public class TargetClass<T extends CharSequence> {
    // Static nested class (target feature)
    static class TargetStaticNested<U extends Comparable<U>> {}

    // Target inner class (target_inner_rec)
    class TargetInnerRec {
        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}

// Supporting interface for override_violation
interface TestInterface {
    void testMethod();
}