package refactoring.test;

import java.lang.reflect.Method;

// Source normal class (protected modifier, same package as target)
protected class SourceClass {
    // Target varargs method (protected access, base type return, in source class)
    // Precondition: method contains target class parameter
    protected int targetMethod(TargetClass targetParam, String... varargs) {
        // VariableDeclarationStatement (private modifier, same_package_others pos)
        private int localVar = TargetClass.staticField; // ClassName.field + "1" (static field value)

        // Variable call feature
        String varCall = String.join(",", varargs);
        int varLength = varCall.length();

        // Used by reflection feature
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod", TargetClass.class, String[].class);
            method.invoke(new SourceClass(), new TargetClass(), (Object) new String[]{"reflection"});
        } catch (Exception e) {
            // No new exception (reuse reflection exception, no explicit new)
            throw new RuntimeException(e);
        }

        // Overload_exist feature (overloaded method exists)
        targetMethod(new TargetClass());

        return varLength; // Base type return (int)
    }

    // Overloaded method for overload_exist feature
    protected int targetMethod(TargetClass targetParam) {
        return 0;
    }

    // Call method's sub_class (default modifier, sub_class type)
    class SubClassCaller extends SourceClass {
        // Call method (default modifier, exception handling pos, int return)
        int callMethod() {
            try {
                // ClassName.methodName(arguments) feature
                int result = SourceClass.this.targetMethod(new TargetClass(), "arg1", "arg2");
                
                // Constructor target_feature
                TargetClass targetInstance = new TargetClass();
                
                return result;
            } catch (RuntimeException e) {
                // Exception handling statements (pos requirement)
                return -1;
            }
        }
    }
}

// Same package other class for VariableDeclarationStatement pos (same_package_others)
class SamePackageOtherClass {
    public void accessTargetField() {
        int field = TargetClass.staticField;
    }
}

// Target normal class (private modifier, extends + static nested class features)
class TargetClass extends ParentClass {
    // Static field for ClassName.field feature
    static int staticField = 1;

    // Static nested class target_feature
    static class StaticNestedClass {}
}

// Parent class for TargetClass's extends feature
class ParentClass {}