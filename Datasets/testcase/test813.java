package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Function;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface Refactor6161Annotation {}

// Source generic class: default modifier, same package, local inner + member inner classes
class SourceClass<T extends Number> {
    // per_condition: source contains field of target
    private TargetClass<T> targetField = new TargetClass<>();

    // Static field for ClassName.field target_feature
    private static final int STATIC_CONST = 1; // target_feature: "1"

    // Member inner class (source feature)
    protected class SourceMemberInner {
        private String innerField = "source_member_inner_6161";
        
        // Static method for call_method target_feature: OuterClass.InnerClass.methodName()
        public static int staticInnerMethod(TargetClass.TargetInner inner) { // target_feature: static
            return inner.getData().intValue();
        }

        // Instance method for method_feature: instance
        public strictfp int instanceMethod(int val) { // modifier: strictfp, method_feature: "1" (val default 1)
            return val * 6161;
        }
    }

    // WhileStatement feature (type: WhileStatement, modifier: private, pos: source)
    private void whileStatementLogic() {
        int count = 1; // target_feature: "1"
        // target_feature: ClassName.field (access SourceClass's static field)
        while (count <= SourceClass.STATIC_CONST) { // WhileStatement
            targetField.setCounter(count);
            count++;
        }
    }

    // Method to be refactored: normal, base type (int) return, final access, source position
    @Refactor6161Annotation // has_annotation feature
    public final int methodToMove() {
        // super constuctor invocation (implicit for Object, explicit in inner class)
        class SuperConstructorClass extends SourceMemberInner {
            public SuperConstructorClass() {
                super(); // super constructor invocation
            }
        }
        new SuperConstructorClass();

        // raw_type feature (raw TargetClass usage)
        TargetClass rawTarget = new TargetClass(); // raw_type

        // variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerField;

        // synchronized statement feature
        Object lock = new Object();
        synchronized (lock) {
            whileStatementLogic();
        }

        // Instance method (type: instance, modifier: strictfp, pos: Lambda expressions, return base type)
        strictfp int instanceMethodResult = ((Function<Integer, Integer>) val -> { // pos: Lambda expressions
            // method_feature: source + instance + ClassName.methodName(arguments)
            return memberInner.instanceMethod(SourceClass.STATIC_CONST); // method_feature: "1"
        }).apply(1); // method_feature: "1"

        // assert statement feature
        assert instanceMethodResult > 0 : "Instance method result must be positive";

        // local inner class (source feature)
        class SourceLocalInner {
            public int validateTarget(TargetClass<T> target) {
                return target.getCounter() + instanceMethodResult;
            }
        }

        // requires_try_catch feature
        int result = 0;
        try {
            result = new SourceLocalInner().validateTarget(targetField);
            // Access target inner class (target_inner)
            TargetClass<T>.TargetInner targetInner = targetField.new TargetInner();
            targetInner.setData((T) Integer.valueOf(result));
        } catch (NullPointerException e) {
            // requires_try_catch (handle NPE)
            e.printStackTrace();
            result = new SourceLocalInner().validateTarget(rawTarget);
        }

        return result; // base type (int) return
    }

    // Call method: inner_class type, protected modifier, static, pos=exception throwing, returns Object
    protected Object callMethod() {
        try {
            // pos: exception throwing statements
            throw new RuntimeException("Test exception for pos requirement");
        } catch (RuntimeException e) {
            // target_feature: static + OuterClass.InnerClass.methodName()
            TargetClass<T>.TargetInner targetInner = targetField.new TargetInner();
            int innerResult = SourceMemberInner.staticInnerMethod(targetInner); // OuterClass.InnerClass.methodName()
            return innerResult + methodToMove();
        }
    }
}

// Target generic class: public modifier, type parameter + static nested class (target_features)
public class TargetClass<T extends Number> {
    // type parameter feature (T)
    private T data;
    private int counter;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static String staticField = "target_static_6161";
    }

    // Target inner class (target_inner)
    public class TargetInner {
        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}