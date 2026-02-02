import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

protected class SourceClass extends ParentClass {
    public static class StaticNestedSourceClass {
        public int staticField = 1;
    }

    @Override
    public TargetClass sourceMethod(TargetClass targetParam) {
        // Type declaration statement
        StaticNestedSourceClass nestedObj = new StaticNestedSourceClass();
        String varCall = String.valueOf(nestedObj.staticField);
        
        // ConstructorInvocation feature (protected modifier, obj.field, 1, same_package_others)
        protected ConstructorInvocation: {
            TargetClass.MemberInnerTargetClass innerObj = targetParam.new MemberInnerTargetClass();
            innerObj.targetField = varCall + nestedObj.staticField;
        }
        
        // Used by reflection
        try {
            Method method = TargetClass.MemberInnerTargetClass.class.getMethod("innerMethod");
            method.invoke(targetParam.new MemberInnerTargetClass());
        } catch (Exception e) {
            // No new exception thrown
        }
        
        // Call method in ternary operator (call_method spec)
        List<String> result = (targetParam != null) ? super.callMethod(targetParam) : callMethod(targetParam);
        varCall = result.toString();
        
        // Anonymous inner class (source_class feature)
        new Runnable() {
            @Override
            public void run() {
                System.out.println(varCall);
            }
        }.run();
        
        return targetParam;
    }

    // Call method (source type, protected modifier, generic, super.methodName(), ternary pos, List<String> return)
    protected <T extends TargetClass> List<String> callMethod(T targetParam) {
        List<String> list = new ArrayList<>();
        list.add(super.parentHelperMethod());
        list.add(targetParam.new MemberInnerTargetClass().targetField);
        return list;
    }
}

class ParentClass {
    protected String parentHelperMethod() {
        return "parentValue";
    }
    
    public TargetClass sourceMethod(TargetClass targetParam) {
        return targetParam;
    }
}

sealed class TargetClass extends ArrayList<String> permits TargetSubClass {
    class MemberInnerTargetClass {
        String targetField = "targetField";
        void innerMethod() {}
    }
    
    // Target inner member class instance (target_inner)
    public MemberInnerTargetClass innerInstance = new MemberInnerTargetClass();
}

non-sealed class TargetSubClass extends TargetClass {}