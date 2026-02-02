// Different package for source class
package com.source;

import com.target.TargetClass;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

protected class SourceClass<T> {
    public static class StaticNestedSourceClass {
        public int getStaticValue() {
            return 5;
        }
    }

    int sourceMethod(TargetClass<String> targetParam) throws IllegalArgumentException {
        @RefactorAnnotation
        int localVar = 0;
        String varCall = String.valueOf(localVar);
        
        // Type declaration statement
        StaticNestedSourceClass nestedObj = new StaticNestedSourceClass();
        
        // Local inner class in source method
        class LocalInnerSourceClass {
            int processTarget(TargetClass<String> param) {
                return param.getTargetValue();
            }
        }
        
        loopLabel:
        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                continue loopLabel;
            }
            if (i == 7) {
                break loopLabel;
            }
            localVar += nestedObj.getStaticValue();
            
            if (targetParam == null) {
                throw new IllegalArgumentException("Target parameter is null: " + varCall);
            }
        }
        
        LocalInnerSourceClass localInner = new LocalInnerSourceClass();
        return localInner.processTarget(targetParam) + localVar;
    }
}

// Different package for target class
package com.target;

private class TargetClass<U> {
    private U targetField;

    int getTargetValue() {
        // Local inner class in target class
        class LocalInnerTargetClass {
            int calculate() {
                return 10;
            }
        }
        LocalInnerTargetClass localInner = new LocalInnerTargetClass();
        return localInner.calculate();
    }
}