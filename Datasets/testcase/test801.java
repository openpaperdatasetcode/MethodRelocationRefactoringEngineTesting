package com.refactoring.movemethod;

import java.util.function.Function;

// Source abstract generic class: abstract modifier, same package, static nested + anonymous inner classes
abstract class SourceClass<T extends Number> { // with_bounds feature (T extends Number)
    // per_condition: source contains field of target
    private TargetClass<T> targetField = new TargetClass<>();

    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "static_6156";

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_6156";
    }

    // Method to be refactored: normal, TargetClass Type return, private access, source position
    // method types parameter is:none (no additional method parameters beyond generic class type)
    private TargetClass<T> methodToMove() {
        // variable call feature
        SourceStaticNested staticNested = new SourceStaticNested();
        String varCall = staticNested.nestedField + STATIC_FIELD; // depends_on_static_field

        // labeled statement + break statement feature
        outerLoop: // labeled statement
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (i + j == 3) {
                    break outerLoop; // break statement with label
                }
                varCall += "_" + (i + j);
            }
        }

        // with_bounds feature (type bound constraint)
        T numData = (T) Integer.valueOf(6156);
        targetField.setData(numData);

        // Anonymous inner class (source feature)
        Runnable anonymousProcessor = new Runnable() {
            @Override
            public void run() {
                targetField.appendData(varCall);
            }
        };
        anonymousProcessor.run();

        // no_new_exception (no explicit new Exception instantiation)
        return targetField;
    }

    // Inner class for call_method type: inner_class
    public class SourceInnerClass {
        // Call method: inner_class type, public modifier, instance, lambda, pos=do-while, returns int
        public int callMethod() {
            int count = 0;
            // pos: do-while statement
            do {
                // target_feature: instance + (parameters) -> methodBody (lambda)
                Function<TargetClass<T>, Integer> processor = (target) -> { // lambda
                    T data = target.getData();
                    return data.intValue() + count; // instance method call context
                };

                // Call refactored method and process result
                TargetClass<T> targetResult = methodToMove();
                count = processor.apply(targetResult);
                count++;
            } while (count < 3);

            return count;
        }
    }
}

// Target generic class: public modifier, local inner class (target_feature)
public class TargetClass<T extends Number> { // with_bounds alignment
    private T data;
    private String appendData;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void appendData(String append) {
        // local inner class (target_feature)
        class TargetLocalInner {
            private String processAppend(String base, String add) {
                return base == null ? add : base + "_" + add;
            }
        }
        this.appendData = new TargetLocalInner().processAppend(this.appendData, append);
    }

    public String getAppendData() {
        return appendData;
    }
}