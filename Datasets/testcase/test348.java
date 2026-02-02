package com.refactoring.movemethod;

// Protected normal source class (type parameter, static nested, local inner; same package as target)
protected class SourceClass<T extends String> {
    // Field for VariableDeclarationStatement (this.field + 1)
    private int sourceField = 1;
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "protected_value";

    // Static nested class (source feature)
    public static class SourceStaticNested {}

    // Static method (public access, void return, target inner param)
    public static void staticMethod(TargetClass.TargetStaticNested targetInnerParam) {
        // Variable call
        String localVar;
        
        // VariableDeclarationStatement (private modifier, this.field + 1, source pos)
        private: {
            // Use instance to access this.field in static context
            SourceClass<String> sourceInstance = new SourceClass<>();
            int privateVar = sourceInstance.sourceField + 1;
            localVar = String.valueOf(privateVar);
        }

        // Access outer protected field
        localVar = sourceInstance.outerProtectedField + localVar;

        // While statement with instance method feature (public modifier, 2, source, instance, new ClassName().method())
        int count = 2;
        while (count > 0) {
            // new ClassName().method() - instance method call
            new SourceClass<String>().instanceHelperMethod(localVar);
            count--;
        }

        // Local inner class (source feature)
        class LocalInnerClass {
            void process() {
                localVar += targetInnerParam.nestedField;
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.process();

        // No new exception (no exception instantiation)
    }

    // Instance helper method (public modifier, void return, source type)
    public void instanceHelperMethod(String arg) {
        // Instance method implementation
    }
}

// Public normal target class (static nested class feature)
public class TargetClass {
    // Static nested class (target_inner)
    public static class TargetStaticNested {
        public String nestedField = "target_nested_value";
    }
}