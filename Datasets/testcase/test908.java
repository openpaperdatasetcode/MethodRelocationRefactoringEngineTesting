package refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

/**
 * Target generic class Javadoc (target_feature: javadoc)
 * Protected modifier, type parameter + anonymous inner class (target_feature)
 * @param <T> Generic type parameter
 */
protected class TargetClass<T> {
    public T targetField;

    // Anonymous inner class (target_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + targetField);
        }
    };

    public TargetClass(T targetField) {
        this.targetField = targetField;
    }

    public void executeAnonymous() {
        anonymousInner.run();
    }

    public T getTargetField() {
        return targetField;
    }

    // Chain methods for obj.m1().m2().m3() feature
    public TargetClass<T> m1() { return this; }
    public TargetClass<T> m2() { return this; }
    public TargetClass<T> m3() { return this; }
}

// Source generic class: final modifier, static nested + local inner class (source_feature)
public final class SourceClass<T> {
    // Source contains target field (per_condition)
    private TargetClass<T> targetField = new TargetClass<>((T) "sourceTargetData");

    // Static nested class (source_feature)
    public static class SourceStaticNested<U> {
        // call_method: inner_class type, private modifier, static + obj.m1().m2().m3(), pos=if/else, return List<String>
        private static <U> List<String> innerStaticMethod(TargetClass<U> target) {
            List<String> result = new ArrayList<>();
            // pos=if/else conditions
            if (target.getTargetField() != null) {
                // obj.m1().m2().m3() target_feature
                target.m1().m2().m3().executeAnonymous();
                result.add(target.getTargetField().toString());
            } else {
                result.add("default");
            }
            return result;
        }
    }

    // Instance method: public access, void return type, method_position=source
    public void refactorMethod() {
        // Variable call feature
        T varCall = targetField.getTargetField();

        // InfixExpression feature: numbers=2, public modifier, exp=InfixExpression
        public int infixResult = (Integer) varCall + 2; // InfixExpression (+) with 2

        // Switch case feature
        int switchVal = infixResult;
        switch (switchVal) {
            case 2:
                targetField.executeAnonymous();
                break;
            default:
                break;
        }

        // otherObject.process(this); feature
        OtherClass otherObject = new OtherClass();
        otherObject.process(this);

        // Used by reflection feature
        try {
            Method executeMethod = TargetClass.class.getMethod("executeAnonymous");
            executeMethod.invoke(targetField);
        } catch (Exception e) {
            // No new exception thrown feature
        }

        // Local inner class (source_feature)
        class LocalInnerClass {
            public void printData() {
                System.out.println(varCall + "_" + infixResult);
            }
        }
        new LocalInnerClass().printData();

        // Execute call_method
        SourceStaticNested.innerStaticMethod(targetField);

        // No new exception thrown feature
    }

    // Helper class for otherObject.process(this)
    static class OtherClass {
        public <T> void process(SourceClass<T> source) {
            System.out.println("Process source: " + source.targetField.getTargetField());
        }
    }
}