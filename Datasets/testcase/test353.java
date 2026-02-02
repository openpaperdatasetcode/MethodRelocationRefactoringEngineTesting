package com.refactoring.movemethod;

// Sealed base class for permits feature (required for permits usage)
sealed class SealedBaseClass permits SourceClass, OtherPermittedClass {}

// Protected normal source class (permits, static nested, anonymous inner; same package as target)
non-sealed class SourceClass extends SealedBaseClass {
    // Source contains target class field (satisfies per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    public static class SourceStaticNested {}

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in SourceClass");
        }
    };

    // Inner class containing the target method (source_inner position)
    public class SourceInnerClass {
        // Property for method_feature pos (property assignment)
        private int assignedProperty;

        // Instance method (private access, Object return, target parameter)
        private Object instanceMethod(TargetClass targetParam) {
            // Variable call
            String localVar = "base_value";
            int numVar = 0;

            // Constructor invocation
            SourceInnerClass innerInstance = new SourceInnerClass();

            // Super constructor invocation
            super();

            // ConstructorInvocation (private modifier, obj.field + 3, same_package_others pos)
            private: {
                OtherSamePackageClass other = new OtherSamePackageClass(targetParam.instanceField + 3);
                localVar += other.fieldValue;
            }

            // Method_feature (protected modifier, 1, parent_class, instance, instanceReference::methodName)
            // Property assignment pos
            ParentClass parentInstance = new ParentClass();
            assignedProperty = parentInstance::instanceHelperMethod; // instanceReference::methodName
            assignedProperty = parentInstance.instanceHelperMethod(1); // 1 from method_feature

            // Overload exist (overload method)
            instanceMethod(localVar);

            // No new exception (no exception instantiation)
            return targetParam;
        }

        // Overload method (overload_exist feature)
        private Object instanceMethod(String arg) {
            return arg + targetField.instanceField;
        }
    }
}

// Other permitted class for permits feature
non-sealed class OtherPermittedClass extends SealedBaseClass {}

// Parent class for method_feature (parent_class)
class ParentClass {
    // Instance helper method (base type return)
    protected int instanceHelperMethod(int num) {
        return num * 2;
    }
}

// Same package others class for ConstructorInvocation pos
class OtherSamePackageClass {
    public int fieldValue;

    public OtherSamePackageClass(int value) {
        this.fieldValue = value;
    }
}

// Protected normal target class (implements, local inner class)
protected class TargetClass implements Runnable {
    // Instance field for obj.field feature
    public int instanceField = 3;

    // Local inner class (target_feature)
    public void targetMethod() {
        class LocalInnerClass {
            private String innerField = "local_inner_value";
        }
        LocalInnerClass localInner = new LocalInnerClass();
    }

    @Override
    public void run() {
        // Implements Runnable interface
    }
}