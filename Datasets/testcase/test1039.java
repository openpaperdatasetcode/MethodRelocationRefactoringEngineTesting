package com.refactoring.test;

// Source class (default modifier, same package, local inner + static nested class)
class SourceClass {
    private String outerPrivateField = "privateOuterValue";

    // Static nested class (source feature)
    static class StaticNestedSource {
        // Inner class for method position (source_inner)
        class InnerSourceClass {
            // Method to be refactored (instance, TargetClass return, public access)
            public TargetClass moveMethod(TargetClass targetParam) {
                // Access outer private field (access_outer_private)
                String privateVal = SourceClass.this.outerPrivateField;
                // Source contains target's field (per_condition)
                targetParam.targetField = privateVal;
                
                // Variable call
                String varCall = targetParam.targetField;
                // Expression statement
                targetParam.setTargetField(varCall + "_modified");
                
                // Local inner class (source feature)
                class LocalInnerClass {
                    void processTarget(TargetClass target) {
                        target.targetField = "processed";
                    }
                }
                new LocalInnerClass().processTarget(targetParam);
                
                // If/else condition with call_method (pos: if/else conditions)
                if (targetParam != null) {
                    Object result = targetParam.<String>callMethod("param1");
                } else {
                    Object result = new TargetClass().<Integer>callMethod(123);
                }
                
                // No new exception
                return targetParam;
            }
        }
    }
}

// Target class (protected modifier, type parameter + static nested class)
protected class TargetClass<T> {
    String targetField;

    // Static nested class (target feature)
    static class TargetStaticNested<U> {
        U nestedField;
        
        U getNestedField() {
            return nestedField;
        }
    }

    void setTargetField(String value) {
        this.targetField = value;
    }

    // call_method (target type, strictfp modifier, generic + new ClassName().method())
    strictfp <V> Object callMethod(V param) {
        TargetStaticNested<V> nested = new TargetStaticNested<>();
        nested.nestedField = param;
        return nested.getNestedField();
    }
}