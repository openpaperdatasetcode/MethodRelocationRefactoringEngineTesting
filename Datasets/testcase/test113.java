package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source class: protected modifier, same package, type parameter, member inner, static nested
protected class SourceClass<T extends Number> {
    private String sourceField = "sourceValue";
    
    // Static nested class (source feature)
    static class StaticNestedSource {
        String staticNestedMethod() {
            return "staticNested";
        }
    }
    
    // Member inner class (source feature) - method position: source_inner
    class MemberInnerSource {
        // Method: varargs, return Object, protected access, source_inner position
        protected Object moveableVarargsMethod(TargetClass.MemberInnerTarget... params) { // per_condition: contains target parameter
            // Variable call feature
            String localVar = sourceField;
            localVar = StaticNestedSource.staticNestedMethod();
            
            // Raw_type feature
            List rawList = new ArrayList();
            rawList.add(localVar);
            
            // Access_instance_method feature
            TargetClass parentInstance = new TargetClass();
            String instanceVal = parentInstance.parentInstanceMethod();
            
            // Requires_try_catch feature
            try {
                int num = Integer.parseInt(instanceVal);
                rawList.add(num);
            } catch (NumberFormatException e) {
                rawList.add("error");
            }
            
            // Return Object (varargs method return type)
            return rawList;
        }
    }
    
    // Property assignment with call_method (parent_class, protected, instance, outerInstance.new InnerClass().methodName())
    private String assignedProperty;
    
    public SourceClass() {
        TargetClass outerInstance = new TargetClass();
        // Call_method: pos = property assignment, return_type String
        assignedProperty = outerInstance.new MemberInnerTarget().innerMethod();
    }
}

// Target class: public modifier, extends, static nested (target_feature)
public class TargetClass extends ParentClass {
    // Static nested class (target_feature)
    static class StaticNestedTarget {
        int nestedInt = 10;
    }
    
    // Member inner class (target_inner - method's target class)
    class MemberInnerTarget {
        String innerMethod() {
            // Call_method: parent_class, protected, instance
            return TargetClass.this.protectedParentMethod();
        }
    }
    
    // Parent class method (call_method target_feature)
    protected String protectedParentMethod() {
        return "parentValue";
    }
    
    // Instance method (access_instance_method feature)
    String parentInstanceMethod() {
        return "instanceValue";
    }
}

// Parent class for target_class (extends feature)
class ParentClass {
    protected String parentMethod() {
        return "parentBase";
    }
}