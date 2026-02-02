package com.source;

import com.target.TargetClass;
import java.lang.reflect.Method;

// Source class: normal, protected, different package from target, two anonymous inner classes
protected class SourceClass {
    /**
     * Method javadoc - varargs method to refactor
     * @param targetParams target class parameters (per_condition)
     * @return base type (int) result
     */
    protected int methodToMove(TargetClass... targetParams) {
        // Super constructor invocation (implicit super() for Object constructor)
        super();

        // Labeled statement
        labelBlock: {
            if (targetParams.length == 0) break labelBlock;
        }

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("methodToMove", TargetClass[].class);
            method.setAccessible(true);
            method.invoke(this, (Object) targetParams);
        } catch (Exception e) {
            // No new exception thrown
        }

        // Variable call (target parameter access)
        int targetVar = targetParams.length > 0 ? targetParams[0].staticNestedField : 0;

        // First anonymous inner class (source feature)
        Runnable anonymous1 = new Runnable() {
            @Override
            public void run() {
                SourceClass.this.methodToMove(targetParams);
            }
        };

        // Second anonymous inner class (source feature)
        java.util.function.Supplier<Integer> anonymous2 = new java.util.function.Supplier<Integer>() {
            @Override
            public Integer get() {
                return targetVar;
            }
        };

        // No new exception thrown
        return targetVar + anonymous2.get();
    }

    // Overload method (overload_exist feature)
    protected int methodToMove(int param) {
        return param;
    }
}

package com.target;

// Target class: normal, public, static nested class (target_feature)
public class TargetClass {
    // Target field (used in variable call)
    public int staticNestedField = 1;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public void nestedMethod() {}
    }
}