package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

abstract class SourceClass permits TargetClass {
    // Method to be refactored (instance, List<String> return, protected access, position: source)
    protected List<String> targetMethod(TargetClass.InnerTargetClass param) { // per_condition: contains target parameter
        super(); // super constructor invocation
        
        // Empty statement
        ;
        
        // Pattern expression (numbers:1, public modifier, exp:Pattern)
        public Pattern pattern = Pattern.compile(".{1}");
        
        // Variable call
        String targetField = param.getField();
        
        // Constructor with parameter list containing instance method call (pos: parameter list of constructors)
        OtherClass other = new OtherClass(this.instanceMethod("arg1"));
        
        // ReturnStatement (public modifier, ClassName.field, 1, pos: source)
        public return new ArrayList<>() {{
            add(TargetClass.InnerTargetClass.STATIC_FIELD); // ClassName.field
            add("1"); // target_feature 1
        }};
    }

    // Instance method (type: instance, default modifier, return void, method_feature: 1, others_class, instance, this.methodName(arguments))
    void instanceMethod(String arg) {
        // this.methodName(arguments)
        this.helperMethod(1); // 1 in method_feature
    }

    // Helper method for this.methodName(arguments)
    private void helperMethod(int num) {}
}

class TargetClass {
    // Target inner class (target class: target_inner)
    class InnerTargetClass {
        public static final String STATIC_FIELD = "targetField";
        private String field = "value";

        public String getField() {
            return field;
        }
    }
}

// Others_class (method_feature: others_class)
class OtherClass {
    public OtherClass(void unused) {}
}