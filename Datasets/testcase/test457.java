package refactoring.test;

// Source normal class (public modifier, same package as target, type parameter + two member inner classes)
public class SourceClass<T extends Number> { // with_bounds feature (T bounded to Number)
    // Precondition: source contains target class field
    private TargetClass targetField = new TargetClass();
    
    // Static field for depends_on_static_field feature
    private static int staticField = 2;

    // First member inner class
    class FirstMemberInnerClass {}
    
    // Second member inner class
    class SecondMemberInnerClass {}

    // Parent class for parent_class feature
    static class ParentClass {
        protected TargetClass overrideMethod() {
            return new TargetClass();
        }
    }

    // Target overriding method (protected access, returns TargetClass type)
    @Override
    protected TargetClass overrideMethod() {
        // Expression statement feature
        int exprVar = staticField * 2;
        exprVar += 5;

        // Numbers:2, private modifier, MethodInvocation feature
        private int methodInvocationVar = this.instanceMethod(2); // MethodInvocation with 2, private modifier

        // Variable call feature
        String varCall = String.valueOf(exprVar);
        System.out.println(varCall);

        // Depends_on_static_field feature
        if (staticField == 2) {
            targetField = new TargetClass();
        }

        // Instance nested method (default modifier, object initialization pos, void return)
        void instanceMethod(int num) { // method_feature: 2 (num=2), parent_class, instance, this.methodName(arguments)
            ParentClass parentObj = new ParentClass(); // parent_class feature
            this.overrideMethod(); // this.methodName(arguments) feature
            
            // Object initialization pos requirement
            TargetClass initObj = new TargetClass();
            initObj.callMethod();
        }

        // No_new_exception (reuse existing exception, no explicit new)
        try {
            if (targetField == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            // No new exception instance created
        }

        instanceMethod(2);
        return targetField;
    }

    // Call method (target type, strictfp modifier, exception handling pos, String return)
    strictfp String callMethod() {
        try {
            // ClassName.methodName(arguments) feature
            TargetClass result = SourceClass.ParentClass.class.newInstance().overrideMethod();
            
            // Instance target_feature
            result.toString();
            
            return result.toString();
        } catch (Exception e) {
            // Exception handling statements (pos requirement)
            return "error";
        }
    }
}

// Target normal class (private modifier, empty target_feature)
class TargetClass {
    // Empty target_feature as specified
}