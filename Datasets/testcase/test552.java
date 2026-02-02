package com.source;

import com.target.TargetClass;

// Source class: normal, private, different package from target, two local inner classes
private class SourceClass {
    // Target class field reference (per_condition)
    private TargetClass<String> targetField = new TargetClass<>();
    
    // Private field for access_outer_private feature
    private int outerPrivateField = 42;

    // Method to refactor: varargs, base type (int) return, protected, in source class
    protected int methodToMove(Integer... args) {
        // Empty statement
        ;
        
        // Type declaration statement
        class LocalTypeDeclaration {}
        LocalTypeDeclaration localType = new LocalTypeDeclaration();
        
        // Super keywords usage
        super.toString();
        
        // Variable call (target field access)
        int targetVar = targetField.targetIntField;
        
        // Access outer private field (access_outer_private)
        int privateAccess = SourceClass.this.outerPrivateField;
        
        // With bounds (generic type with bounds)
        class BoundedType<T extends Number & Comparable<T>> {
            T value;
        }
        BoundedType<Integer> boundedInstance = new BoundedType<>();
        boundedInstance.value = 1;
        
        // First local inner class (source feature)
        class LocalInner1 {
            void method() {}
        }
        LocalInner1 local1 = new LocalInner1();
        
        // Second local inner class (source feature)
        class LocalInner2 {
            void method() {}
        }
        LocalInner2 local2 = new LocalInner2();
        
        // Call method: sub_class, default modifier, overriding, outerInstance.new InnerClass().methodName() in object initialization
        TargetSubClass subInstance = new TargetSubClass(targetField.new TargetInnerRec().innerMethod());
        
        // No new exception thrown
        return targetVar + privateAccess + (args.length > 0 ? args[0] : 0);
    }
}

package com.target;

/**
 * TargetClass Javadoc (target_feature: javadoc)
 * Generic class with type parameter, extends SuperClass, has local inner class
 * @param <T> Type parameter (target_feature: type parameter)
 */
class TargetClass<T> extends SuperClass {
    // Target field (referenced in source)
    int targetIntField = 1;
    
    // Target inner recursive class (target_inner_rec)
    class TargetInnerRec {
        void innerMethod() {}
    }
    
    // Local inner class (target_feature)
    public void targetMethod() {
        class LocalInnerInTarget {
            void method() {}
        }
    }
}

// Super class for target class extends feature
class SuperClass {}

// Sub class for call_method (type: sub_class)
class TargetSubClass extends TargetClass<String> {
    // Call method: default modifier, overriding (target_feature: overriding)
    TargetSubClass(void param) {
        super();
    }
    
    @Override
    public void targetMethod() {
        // Overriding feature implementation
        super.targetMethod();
    }
}