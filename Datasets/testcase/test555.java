package com.refactor;

import com.other.DiffPackageClass;
import java.lang.reflect.Method;

// Source class: normal, public, same package as target, two member inner classes
public class SourceClass extends ParentClass {
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();
    
    // Protected field for access_outer_protected feature
    protected String outerProtectedField = "protectedValue";

    // First member inner class (source feature)
    class SourceInner1 {}
    // Second member inner class (source feature)
    class SourceInner2 {}

    // Method to refactor: overriding, void return, protected, override violation (weaker access than parent)
    @Override
    protected void methodToMove() {
        // ConstructorInvocation (protected modifier, ClassName.field, 2, pos: diff_package_others)
        protected DiffPackageClass diffPackageInstance = new DiffPackageClass(TargetClass.STATIC_FIELD, 2);

        // Instance feature (default modifier, 2, source, instance, new ClassName().method() in property assignment)
        TargetClass instanceProp = new TargetClass();
        instanceProp.nestedInstance = new TargetClass.StaticNested().targetMethod(2);

        // If statement
        if (targetField != null) {
            variableCall();
        }

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("methodToMove");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception e) {
            // No new exception thrown
        }

        // Variable call (target field access)
        String targetVar = targetField.instanceField;

        // Access outer protected field
        String protectedAccess = SourceClass.this.outerProtectedField;

        // No new exception thrown
    }

    // Variable call helper method
    private void variableCall() {}
}

// Parent class for overriding feature (override violation: parent has public method, child uses protected)
class ParentClass {
    public void methodToMove() {}
}

// Target class: normal, public, static nested class (target_feature)
public class TargetClass {
    // Target static field (used in ConstructorInvocation)
    public static int STATIC_FIELD = 2;
    // Target instance field (used in variable call)
    public String instanceField = "targetValue";
    
    // Property for instance feature (property assignment)
    public TargetClass nestedInstance;

    // Static nested class (target_feature)
    public static class StaticNested {
        public TargetClass targetMethod(int param) {
            return new TargetClass();
        }
    }
}

// Diff package class for ConstructorInvocation pos: diff_package_others
package com.other;
import com.refactor.TargetClass;
public class DiffPackageClass {
    public DiffPackageClass(int field, int num) {}
}