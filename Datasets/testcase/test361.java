package com.refactoring.movemethod;

// Source normal class (public modifier, same package, static nested + member inner class)
public class SourceClass {
    // Static nested class (source feature)
    public static class StaticNestedClass<T extends CharSequence> {
        String nestedField = "static_nested";
    }

    // Member inner class (source feature)
    public class MemberInnerClass {
        int innerField = 2;
    }

    // Inner record class containing the method to be moved (source_inner_rec position)
    public record SourceInnerRec(TargetClass targetParam) {
        // Instance method to be moved (private, returns Object, target param in pre-condition)
        private <V extends Number & Comparable<V>> Object moveableMethod() {
            // ConstructorInvocation (public, same_package_target pos, this.field + 2 features)
            TargetClass.MemberInnerClass targetInner = new TargetClass.MemberInnerClass(this.targetParam, 2);
            
            // Expression statement feature
            String exprStmt = targetInner.innerField + new StaticNestedClass<String>().nestedField;
            
            // Variable call feature
            int varCall = this.targetParam().targetField + 2;
            
            // With bounds feature (generic type with bounds)
            V boundedVar = (V) Integer.valueOf(varCall);
            int compareResult = boundedVar.compareTo((V) Integer.valueOf(2));
            
            // Ternary operator with sub_class call_method (instance + ClassName::methodName)
            String ternaryResult = (compareResult > 0) ? 
                new TargetSubClass().instanceMethod() : 
                TargetSubClass::staticMethod;

            // No new exception instantiation (no_new_exception feature)
            return ternaryResult + exprStmt;
        }
    }
}

// Target normal class (default modifier, member inner class target feature)
class TargetClass {
    int targetField = 2;

    // Member inner class (target feature)
    public class MemberInnerClass {
        int innerField;

        // Constructor for ConstructorInvocation feature
        public MemberInnerClass(TargetClass outer, int num) {
            this.innerField = outer.targetField + num; // this.field (outer.targetField) + 2
        }
    }
}

// Subclass for call_method feature
class TargetSubClass extends TargetClass {
    // Public instance method (call_method: instance feature)
    public String instanceMethod() {
        return "subclass_instance";
    }

    // Static method for method reference (ClassName::methodName)
    public static String staticMethod() {
        return "subclass_static";
    }
}