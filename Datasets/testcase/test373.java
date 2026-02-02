package com.refactoring.movemethod;

import java.util.function.Consumer;

// Super class for source generic class (extends feature)
class SuperGenericClass<T> {
    // Method for override violation (different return type)
    public Object process(T param) {
        return param;
    }
}

// Custom annotation for has_annotation feature
@interface RefactorAnnotation {}

// Source generic class (public, same package, extends + anonymous inner + local inner class)
public class SourceClass<T extends TargetClass<?>> extends SuperGenericClass<T> {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass<String> targetField;

    // Inner recursive class (source_inner_rec position for refactor method)
    public class SourceInnerRec {
        private int innerVar = 10;

        // Overloading method 1 (private, return TargetClass type, source_inner_rec)
        @RefactorAnnotation // has_annotation feature
        private TargetClass<T>.TargetInnerRec refactorMethod(TargetClass<T>.TargetInnerRec targetParam) {
            return refactorMethod(targetParam, "default");
        }

        // Overloading method 2 (private, return TargetClass type, source_inner_rec - main method)
        @RefactorAnnotation // duplicate has_annotation feature
        private TargetClass<T>.TargetInnerRec refactorMethod(TargetClass<T>.TargetInnerRec targetParam, String suffix) {
            // Method types parameter is:keywords (uses generic keywords)
            TargetClass<T>.TargetInnerRec result = null;

            // Variable call feature
            int localVar = this.innerVar;

            // While statement feature
            int count = 0;
            while (count < localVar) {
                count++;
            }

            // Override violation (invalid override of super class method)
            @Override // override_violation: return type mismatch with super
            public TargetClass<T>.TargetInnerRec process(T param) { // method types parameter is:keywords
                return param.new TargetInnerRec(String.valueOf(count) + suffix);
            }

            // Local inner class (source class feature)
            class SourceLocalInner {
                TargetClass<T>.TargetInnerRec processInner(TargetClass<T>.TargetInnerRec inner) {
                    // No new exception feature (no throw new Exception)
                    if (inner == null) return new TargetClass<T>().new TargetInnerRec("empty");
                    inner.setData(inner.getData() + "_processed");
                    return inner;
                }
            }

            // Anonymous inner class (source class feature)
            Consumer<TargetClass<T>.TargetInnerRec> anonConsumer = new Consumer<TargetClass<T>.TargetInnerRec>() {
                @Override
                public void accept(TargetClass<T>.TargetInnerRec inner) {
                    inner.setData(inner.getData() + "_anon");
                }
            };

            // Process target parameter (per_condition: method has target parameter)
            if (targetParam != null) {
                SourceLocalInner localInner = new SourceLocalInner();
                result = localInner.processInner(targetParam);
                anonConsumer.accept(result);
                targetField = (TargetClass<String>) targetParam.getOuterTarget();
            }

            return result;
        }
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass<>("init");
        SourceInnerRec innerRec = new SourceInnerRec();
    }
}

// Target generic class (protected, local inner class feature)
protected class TargetClass<T> {
    private T data;

    // Inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        private String innerData;

        public TargetInnerRec(String innerData) {
            this.innerData = innerData;
        }

        // Local inner class (target_feature)
        public void processLocalInner() {
            class TargetLocalInner {
                public void updateData(TargetInnerRec inner) {
                    inner.innerData = "local_" + inner.innerData;
                }
            }
            new TargetLocalInner().updateData(this);
        }

        public String getData() {
            return innerData;
        }

        public void setData(String innerData) {
            this.innerData = innerData;
        }

        public TargetClass<T> getOuterTarget() {
            return TargetClass.this;
        }
    }

    public TargetClass(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}