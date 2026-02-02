// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.ArrayList;
import java.util.List;

// Source class: protected normal class, different package from target, static nested + member inner class
protected class SourceClass {
    // Per_condition: method contains target parameter (enforced in signature)

    // Static nested class (source feature)
    static class SourceStaticNested<T> {
        public static <U extends Number & Comparable<U>> U processValue(U val) {
            return (U) Integer.valueOf(val.intValue() * 2);
        }
    }

    // Member inner class (source feature)
    class SourceInnerClass {
        public <T> T adjustValue(T val) {
            return val;
        }
    }

    // Method to refactor: instance, base type (int) return, default access, in source class
    int methodToRefactor(TargetClass targetParam) {
        int result = 0;
        
        // Variable call (target class and its static nested class)
        String targetValue = targetParam.getValue();
        int staticNestedVal = TargetClass.TargetStaticNested.getStaticInt();
        result = staticNestedVal;

        // Expression statement
        targetValue += "_processed_source"; // expression statement
        targetParam.setValue(targetValue); // expression statement
        result += targetValue.length();

        // With_bounds feature (generic with multiple bounds)
        class BoundedProcessor<U extends Number & Comparable<U>> {
            U process(U input) {
                return SourceStaticNested.processValue(input);
            }
        }
        BoundedProcessor<Integer> processor = new BoundedProcessor<>();
        result = processor.process(result);

        // Use member inner class
        SourceInnerClass inner = new SourceInnerClass();
        result = (Integer) inner.adjustValue(result);

        // No_new_exception feature
        try {
            Integer.parseInt(targetValue);
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            result = Math.abs(result);
        }

        return result; // Base type (int) return
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Target class: no modifier, static nested class feature
class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static int getStaticInt() {
            return 42;
        }
    }

    private String value = "target_default_value";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}