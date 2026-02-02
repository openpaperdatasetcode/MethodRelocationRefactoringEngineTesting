package com.refactoring.test;

protected class SourceClass implements CustomInterface {
    // Member inner class (source_class feature)
    class MemberInnerSourceClass {
        // Inner record class (method_position: source_inner_rec)
        record InnerSourceRecord() {
            // Method to be refactored (instance, TargetClass Type return, private access)
            private TargetClass targetMethod(TargetClass param) { // per_condition: target parameter, method types parameter is Target class
                super(); // super constructor invocation
                
                // ForStatement (private modifier, ClassName.field, 3, pos: source)
                private int limit = 3; // target_feature 3
                for (int i = 0; i < limit; i++) {
                    // ClassName.field access
                    String staticField = TargetClass.STATIC_FIELD; // ClassName.field
                    
                    // Expression statement
                    param.instanceField = "updated_" + i;
                    
                    // Access instance field
                    String instanceField = param.instanceField;
                    
                    // Variable call
                    String innerValue = param.anonymousInner.getValue();
                }
                
                return param; // no_new_exception
            }
        }
    }

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}
}

// Interface for implements feature (source_class)
interface CustomInterface {}

public class TargetClass {
    // ClassName.field for ForStatement
    public static final String STATIC_FIELD = "targetStaticField"; // ClassName.field
    String instanceField = "targetInstanceField";

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {}
        
        String getValue() {
            return "anonymousInnerValue";
        }
    };
}