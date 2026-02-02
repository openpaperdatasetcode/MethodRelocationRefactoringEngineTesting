package com.refactoring.movemethod;

// Source class: generic, public, same package as target, type parameter, anonymous inner, local inner class
public class SourceClass<T> {
    // Per_condition: source contains target class field
    private static TargetClass<T> targetField = new TargetClass<>((T) "initialTargetValue");

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        // Method to refactor: static, Object return, strictfp access, in source_inner
        strictfp static <U> Object methodToRefactor(TargetClass<U>.TargetInnerRec... targetParams) {
            Object result = null;
            
            // Empty statement feature
            ; // empty statement

            // For statement
            for (int i = 0; i < targetParams.length; i++) {
                // Variable call (target inner recursive class)
                TargetClass<U>.TargetInnerRec param = targetParams[i];
                U targetValue = param.getValue();
                result = targetValue + "_processed_" + i;

                // No new exception feature
                try {
                    Integer.parseInt(targetValue.toString());
                } catch (NumberFormatException e) {
                    // No throw new exception, only handle
                    result = "formatted_" + targetValue;
                }
            }

            // Local inner class (source class feature)
            class LocalInnerProcessor<U> {
                U process(U input) {
                    return input;
                }
            }
            LocalInnerProcessor<T> processor = new LocalInnerProcessor<>();
            result = processor.process((T) result);

            // Anonymous inner class (source class feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Anonymous inner: " + result);
                }
            };
            anonymousRunnable.run();

            // Variable call for targetField (per_condition)
            result = targetField.getInnerRec().getValue() + "_field_" + result;

            return result;
        }
    }

    // Local inner class (source class feature)
    public void sourceLocalInnerFeature() {
        class SourceLocalInner<U> {
            void printTarget(TargetClass<U> target) {
                System.out.println(target.getInnerRec().getValue());
            }
        }
        new SourceLocalInner<T>().printTarget(targetField);
    }

    // Anonymous inner class (source class feature)
    public void sourceAnonymousFeature() {
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anonymous: " + targetField.getInnerRec().getValue());
            }
        };
        anonymous.run();
    }
}

// Target class: generic, protected, same package as source, anonymous inner class feature
protected class TargetClass<T> {
    private T value;
    private TargetInnerRec innerRec;

    public TargetClass(T initialValue) {
        this.value = initialValue;
        this.innerRec = new TargetInnerRec(initialValue);
    }

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private T innerValue;

        public TargetInnerRec(T value) {
            this.innerValue = value;
            
            // Anonymous inner class (target_feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Target anonymous: " + innerValue);
                }
            };
            anonymous.run();
        }

        public T getValue() {
            return innerValue;
        }

        public void setValue(T value) {
            this.innerValue = value;
        }
    }

    public TargetInnerRec getInnerRec() {
        return innerRec;
    }
}