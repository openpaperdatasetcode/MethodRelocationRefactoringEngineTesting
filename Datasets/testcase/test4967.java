package test;

import java.util.function.Function;

private class SourceClass<T extends Number> {
    // Field of target class (per_condition)
    private TargetClass targetField;
    
    // Member inner class (source feature)
    protected class MemberInner {
        // Recursive method to be refactored (method_position: source_inner)
        @Deprecated // has_annotation
        protected TargetClass recursiveMethod(int value) {
            // Access outer protected member
            if (value < 0) {
                return accessOuterProtected();
            }
            
            // Type declaration statement
            TargetClass.InnerTarget innerTarget = targetField.new InnerTarget();
            
            // Variable call
            TargetClass current = targetField;
            
            // Switch statement
            switch (value) {
                case 1:
                    // obj.field access (target_feature)
                    if (current.field == 10) {
                        // BreakStatement (type)
                        break;
                    }
                    return recursiveMethod(value - 1); // Recursion
                case 2:
                    // Expression statement
                    innerTarget.process();
                    return recursiveMethod(value - 1); // Recursion
                default:
                    return targetField;
            }
            
            // TypeMethodReference (2 required)
            Function<String, Integer> func1 = Integer::parseInt;
            Function<TargetClass, String> func2 = TargetClass::toString;
            
            // Overloaded method call (overload_exist)
            innerTarget.overloadedMethod();
            innerTarget.overloadedMethod(100);
            
            // Access instance method
            current.instanceMethod();
            
            // Depends on inner class
            return innerTarget.getTargetClass();
        }
    }
    
    // Static nested class (source feature)
    private static class StaticNested {
        // Static nested class implementation
    }
    
    // Protected method for access_outer_protected
    protected TargetClass accessOuterProtected() {
        return targetField;
    }
}
    
package test;

/**
 * Javadoc for TargetClass (target_feature)
 * This class contains the member inner class targeted for method move
 */
class TargetClass {
    // Field accessed by source class (obj.field)
    int field = 10;
    
    // Member inner class (target_feature)
    public class InnerTarget {
        // Methods used by source class
        void process() {
            // Implementation
        }
        
        void overloadedMethod() {
            // Implementation
        }
        
        void overloadedMethod(int value) {
            // Overloaded method (overload_exist)
        }
        
        TargetClass getTargetClass() {
            return TargetClass.this;
        }
    }
    
    // Instance method accessed by source class
    void instanceMethod() {
        // Implementation
    }
}
    