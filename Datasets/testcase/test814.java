package com.refactoring.movemethod;

import java.io.IOException;

// Source private generic class: same package, static nested + local inner classes
private class SourceClass<T extends String> {
    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_6163";
    }

    // Recursive inner class (method_position: source_inner_rec)
    public class SourceInnerRecursive {
        // Method to be refactored: instance, base type (int) return, protected access, source_inner_rec
        protected int methodToMove(TargetClass<T> targetParam) throws IOException { // requires_throws feature
            // per_condition: method contains target class parameter
            if (targetParam == null) {
                throw new IOException("TargetClass parameter cannot be null (ID:6163)");
            }

            // variable call feature
            SourceStaticNested staticNested = new SourceStaticNested();
            String varCall = staticNested.nestedField;

            // Local inner class (source feature)
            class SourceLocalInner {
                public int processData(T data) {
                    return data.length() + varCall.length();
                }
            }

            // Process target data with local inner class
            T targetData = targetParam.getData();
            int processedVal = new SourceLocalInner().processData(targetData);

            // Recursive method call (source_inner_rec context)
            return recursiveCalculate(processedVal);
        }

        // Recursive method (source_inner_rec context)
        private int recursiveCalculate(int val) {
            if (val <= 0) return 0;
            return val + recursiveCalculate(val - 1);
        }
    }
}

// Target generic class: default modifier, local inner class (target_feature)
class TargetClass<T> {
    private T data;

    public T getData() {
        // local inner class (target_feature)
        class TargetLocalInner {
            private T validateData(T val) {
                return val == null ? (T) "default_6163" : val;
            }
        }
        return new TargetLocalInner().validateData(this.data);
    }

    public void setData(T data) {
        this.data = data;
    }
}