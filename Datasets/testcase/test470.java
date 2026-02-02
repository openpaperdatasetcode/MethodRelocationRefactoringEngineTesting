package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source final normal class (same package as target, static nested + local inner class)
final class SourceClass {
    // Static nested class feature (source class feature)
    public static class StaticNestedClass {
        private String nestedField = "staticNestedValue";
    }

    // Target accessor method (public access, Object return, in source class)
    // Precondition: method contains target class parameter
    public Object targetMethod(TargetClass targetParam) {
        // Variable call feature
        String varCall = targetParam.staticNested.targetField;
        List<String> varList = new ArrayList<>();
        varList.add(varCall);

        // Numbers:3, public modifier, InfixExpression feature
        public int infixVar = 3 + 3; // InfixExpression with 3, public modifier

        // Break statement feature
        breakLabel:
        for (int i = 0; i < 10; i++) {
            if (i == infixVar) {
                break breakLabel;
            }
            varList.add(String.valueOf(i));
        }

        // Try statement feature
        try {
            varList.add(targetParam.toString());
        } catch (Exception e) {
            varList.add("try_catch: " + e.getMessage());
        } finally {
            varList.add("try_finally");
        }

        // ThrowStatement (protected modifier, obj.field + 1, same_package_target pos)
        protected int throwField = targetParam.staticNested.targetField + 1; // obj.field + 1
        if (throwField < 0) {
            throw new IllegalArgumentException(); // no_new_exception (standard exception, no custom new)
        }

        // Accessor feature (getter for target param field)
        return targetParam.getStaticNestedField();
    }

    // Local inner class feature (source class feature)
    public void sourceLocalInnerClass() {
        class LocalInnerClass {
            void invokeTargetMethod(TargetClass targetParam) {
                SourceClass.this.targetMethod(targetParam);
            }
        }
        new LocalInnerClass().invokeTargetMethod(new TargetClass());
    }

    // Subclass for call_method (sub_class type)
    private static class SubClassCaller extends SourceClass {
        // Static code block (pos requirement for call_method)
        static {
            System.out.println("Static code block for call_method pos");
        }

        // Call method (private modifier, static code blocks pos, String return)
        private String callMethod(TargetClass targetParam) {
            // this.methodName(arguments) target_feature
            Object result = this.targetMethod(targetParam);
            
            // Normal target_feature (basic string operation)
            return "call_result: " + result.toString();
        }
    }
}

// Same package target helper class for ThrowStatement pos (same_package_target)
class SamePackageTargetHelper {
    public void useThrowStatement(TargetClass target) {
        SourceClass source = new SourceClass();
        source.targetMethod(target);
    }
}

// Target normal class (protected modifier, implements + static nested class target_feature)
protected class TargetClass implements Runnable {
    // Static nested class target_feature
    public static class StaticNestedTargetClass {
        int targetField = 5; // obj.field for ThrowStatement feature
    }

    // Instance of static nested class
    public StaticNestedTargetClass staticNested = new StaticNestedTargetClass();

    // Accessor method (getter) for accessor feature
    public Object getStaticNestedField() {
        return staticNested.targetField;
    }

    // Implements Runnable (required for implements feature)
    @Override
    public void run() {
        System.out.println("TargetClass run method");
    }
}