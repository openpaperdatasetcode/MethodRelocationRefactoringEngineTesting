package com.refactoring.movemethod;

// Source class: enum, protected, same package as target, no extra features
protected enum SourceEnum {
    INSTANCE;

    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "sourceProtected6435";

    // Inner class (source_inner - method position)
    class SourceInnerClass {
        // Method to refactor: varargs, void return, default access, in source_inner
        // Per_condition: contains target enum parameter
        void methodToRefactor(TargetEnum.TargetInnerRec... targetParams) {
            // Variable call (target inner recursive class)
            for (TargetEnum.TargetInnerRec param : targetParams) {
                String targetValue = param.getInnerValue();
                
                // Access outer protected field
                targetValue += SourceEnum.this.outerProtectedField;

                // Instance method feature: private modifier, 1, inner_class, instance, obj.m1().m2().m3(), pos=exception throwing, return TargetClass Type
                private TargetEnum instanceHelperMethod(TargetEnum.TargetInnerRec innerParam) {
                    try {
                        // pos: exception throwing statements
                        if (innerParam == null) {
                            throw new IllegalArgumentException("Inner param is null");
                        }
                        // obj.m1().m2().m3() feature (method chaining)
                        String chain = innerParam.m1().m2().m3();
                        // method_feature: 1 (numeric literal)
                        chain += "_1";
                        // method_feature: inner_class (use target inner class)
                        innerParam.setInnerValue(chain);
                        // method_feature: instance (return target enum instance)
                        return TargetEnum.VALUE1;
                    } catch (IllegalArgumentException e) {
                        // Requires_try_catch feature
                        return TargetEnum.VALUE2;
                    }
                }
                TargetEnum instanceResult = instanceHelperMethod(param);

                // Override violation (attempt to override final method from Enum)
                @Override
                public final String name() { // Final method override violation
                    return targetValue;
                }

                // Requires_try_catch feature
                try {
                    Integer.parseInt(targetValue);
                } catch (NumberFormatException e) {
                    // Handle exception (required try-catch)
                    System.out.println("Parse error: " + e.getMessage());
                }
            }
        }
    }
}

// Target class: enum, public, same package as source, static nested class feature
public enum TargetEnum {
    VALUE1, VALUE2;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static String staticValue = "targetStaticValue";
    }

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private String innerValue = "targetInnerRecValue";

        public String getInnerValue() { return innerValue; }
        public void setInnerValue(String value) { this.innerValue = value; }

        // Method chaining for obj.m1().m2().m3()
        public String m1() { return "m1_"; }
        public String m2() { return "m2_"; }
        public String m3() { return "m3_" + TargetStaticNested.staticValue; }
    }
}