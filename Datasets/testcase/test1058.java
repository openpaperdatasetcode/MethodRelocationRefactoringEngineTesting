package com.refactoring.test;

import java.util.function.Function;

public class SourceClass extends SuperClass {
    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}
    
    // Member inner class (source_class feature)
    class MemberInnerSourceClass {
        public Object innerMethod() {
            return new Object();
        }
    }

    // Method to be refactored (instance, TargetClass Type return, protected access, position: source)
    @SuppressWarnings("unchecked")
    protected TargetClass targetMethod(TargetClass param) { // per_condition: contains target parameter
        super(); // super constructor invocation
        
        // Assert statement
        assert param != null : "Target parameter cannot be null";
        
        // Do statement
        int count = 0;
        do {
            // Variable call
            String targetValue = param.getStaticNestedValue();
            count++;
        } while (count < 2); // numbers:2 reference
        
        // TypeMethodReference (numbers:2, public modifier, exp:TypeMethodReference)
        public Function<String, TargetClass.StaticNestedTargetClass> ref = TargetClass.StaticNestedTargetClass::new;
        
        // Switch statement with constructor (pos: switch)
        switch (param.getStaticNestedValue()) {
            case "test":
                // Private constructor, inner_class, new ClassName().method(), 1 in method_feature
                MemberInnerSourceClass inner = new MemberInnerSourceClass(); // inner_class
                Object constructorResult = new SourceClass(1).inner.innerMethod(); // private constructor, new ClassName().method(), 1
                break;
            default:
                break;
        }
        
        return param; // no_new_exception
    }

    // Private constructor (constructor type, private modifier, return_type Object via inner method)
    private SourceClass(int num) { // 1 in method_feature
        super();
    }
}

// Super class for extends feature (source_class)
class SuperClass {}

// Target class (non-sealed modifier, static nested class target_feature)
non-sealed class TargetClass {
    // Static nested class (target_feature)
    static class StaticNestedTargetClass {
        private String value = "targetValue";
        
        public StaticNestedTargetClass(String val) {
            this.value = val;
        }
    }

    private StaticNestedTargetClass staticNested = new StaticNestedTargetClass("default");

    public String getStaticNestedValue() {
        return staticNested.value;
    }
}