// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;

// Source normal class (private modifier, different package, two static nested classes)
private class SourceClass {
    // Static nested class (duplicated feature)
    private static class StaticNestedClass1 {
        String nestedField1 = "static_nested_1";
    }

    // Static nested class (duplicated feature)
    private static class StaticNestedClass2 {
        String nestedField2 = "static_nested_2";
    }

    /**
     * Abstract method to be moved (final, returns TargetClass type, source position)
     * @param targetParam Target class parameter (satisfies pre-condition)
     * @return TargetClass instance
     */
    public abstract TargetClass moveableMethod(TargetClass targetParam);

    // Concrete implementation to demonstrate feature usage (for variable call)
    private class SourceConcrete extends SourceClass {
        @Override
        public final TargetClass moveableMethod(TargetClass targetParam) {
            // Variable call feature
            String varCall = StaticNestedClass1.nestedField1 + targetParam.targetField;
            
            // No new exception instantiation (no_new_exception feature)
            System.out.println(varCall);
            
            return targetParam;
        }
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Superclass for target class extension
class TargetSuperClass {}

// Target normal class (private modifier, extends + anonymous inner class target features)
private class TargetClass extends TargetSuperClass {
    String targetField = "target_field";

    // Anonymous inner class (target feature)
    {
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                targetField = "updated_by_anonymous";
            }
        };
        anonymousInner.run();
    }
}