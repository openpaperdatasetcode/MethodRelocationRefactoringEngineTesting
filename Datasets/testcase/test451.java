package refactoring.test;

import java.lang.reflect.Method;
import java.util.Arrays;

// Source generic class (final modifier, same package as target)
final class SourceClass<T> {
    // Precondition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>();

    // First static nested class
    static class StaticNestedClass1<V> {}

    // Second static nested class
    static class StaticNestedClass2<W> {}

    // Target varargs method (default access, void return, in source class)
    void targetMethod(Object... varargs) {
        // VariableDeclarationStatement (private modifier, diff_package_others pos)
        private int localVar = TargetClass.staticField; // ClassName.field + "1" (int literal)

        // Variable call feature
        String varCall = Arrays.toString(varargs);
        System.out.println(varCall);

        // Super constructor invocation (in context of anonymous subclass)
        Runnable anonymousSub = new Runnable() {
            {
                super(); // Super constructor invocation
            }
            @Override
            public void run() {}
        };

        // Used by reflection (duplicate feature as required)
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod", Object[].class);
            method.invoke(new SourceClass<>(), (Object) new String[]{"reflection"});
        } catch (Exception e) {
            // No new exception (reuse reflection exception, no explicit new)
            throw new RuntimeException(e);
        }

        // Used by reflection (second occurrence)
        try {
            Method innerMethod = TargetClass.StaticNestedTargetClass.class.getDeclaredMethod("innerMethod");
            innerMethod.invoke(new TargetClass.StaticNestedTargetClass());
        } catch (Exception e) {
            throw new RuntimeException(e); // No new exception
        }
    }

    // Call method (private modifier, others_class type, exception throwing pos, int return)
    private int callMethod() {
        try {
            // ClassName::methodName feature
            Runnable r = SourceClass::targetMethod;
            r.run();
            
            // Exception throwing statements (pos requirement)
            if (targetField == null) {
                throw new IllegalArgumentException("Target field is null");
            }
            return 1;
        } catch (IllegalArgumentException e) {
            return -1; // Normal feature (basic return logic)
        }
    }
}

// Target generic class (public modifier)
public class TargetClass<U> {
    // Static field for ClassName.field feature
    public static int staticField = 1;

    // Static nested class (target_feature)
    static class StaticNestedTargetClass {
        void innerMethod() {}
    }
}

// Diff package class for VariableDeclarationStatement pos (diff_package_others)
package refactoring.other;
import refactoring.test.TargetClass;
public class DiffPackageClass {
    public void accessTargetField() {
        int field = TargetClass.staticField;
    }
}