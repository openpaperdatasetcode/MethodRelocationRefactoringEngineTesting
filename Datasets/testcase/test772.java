package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

// Parent class for source extends feature and call_method parent_class type
protected class ParentGenericClass<T> {
    // Private method for call_method: parent_class, modifier: private, normal, super.methodName()
    private String parentCallMethod(int switchCase, SourceGenericClass<T> source, TargetGenericClass<T> target) {
        switch (switchCase) { // pos: switch
            case 1:
                // target_feature: super.methodName()
                List<String> result = source.new SourceInnerClass().methodToMove(target, "arg1", "arg2");
                return result.toString();
            case 2:
                List<String> result2 = source.new SourceInnerClass().methodToMove(target, "arg3");
                return result2.toString();
            default:
                return super.toString(); // super.methodName()
        }
    }
}

// Source generic class: protected modifier, same package, extends + anonymous inner + static nested classes
protected class SourceGenericClass<T> extends ParentGenericClass<T> {
    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String staticNestedField = "static_nested_val";
    }

    // Member inner class (method_position: source_inner)
    public class SourceInnerClass {
        // Method to be refactored: varargs, List<String> return, public access, source_inner
        public List<String> methodToMove(TargetGenericClass<T> targetParam, T... varargs) {
            // per_condition: method contains target class parameter
            if (targetParam == null) {
                return new ArrayList<>();
            }

            // super constructor invocation
            super();

            // variable call feature
            SourceStaticNested staticNested = new SourceStaticNested();
            String varCall = staticNested.staticNestedField;

            // requires_try_catch feature
            List<String> result = new ArrayList<>();
            try {
                for (T arg : varargs) {
                    result.add(varCall + "_" + arg.toString() + "_" + targetParam.getTargetField());
                }
            } catch (NullPointerException e) {
                // requires_try_catch (handle NPE, no_new_exception implied)
                result.add("error: " + e.getMessage());
            }

            return result;
        }
    }

    // Anonymous inner class (source feature)
    private Consumer<T> anonymousInner = new Consumer<T>() {
        @Override
        public void accept(T t) {
            new SourceInnerClass().methodToMove(new TargetGenericClass<>(t), t);
        }
    };
}

// Target generic class: public modifier, anonymous inner class (target_feature)
public class TargetGenericClass<T> {
    private T targetField;

    public TargetGenericClass(T targetField) {
        this.targetField = targetField;
    }

    public T getTargetField() {
        return targetField;
    }

    // Anonymous inner class (target_feature)
    private Runnable anonymousProcessor = new Runnable() {
        @Override
        public void run() {
            targetField = (T) (targetField.toString() + "_processed");
        }
    };

    public void processTarget() {
        anonymousProcessor.run();
    }
}