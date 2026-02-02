import java.lang.reflect.Method;
import java.util.ArrayList;

public class SourceClass<T> extends ParentClass<T> {
    class InnerSourceClass {
        protected int sourceInnerMethod(TargetClass<T> targetParam) throws NullPointerException {
            if (targetParam == null) {
                throw new NullPointerException("Target parameter is null");
            }
            
            // Constructor invocation & raw type
            ArrayList rawList = new ArrayList();
            String varCall = SourceClass.this.toString();
            
            // Access instance method of target
            targetParam.instanceMethod();
            
            // Used by reflection
            try {
                Method method = TargetClass.class.getMethod("instanceMethod");
                method.invoke(targetParam);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
            // Overload exist - call overloaded method
            sourceInnerMethod(targetParam, 0);
            
            // Call parent class private method in for loop
            for (int i = 0; i < 1; i++) {
                TargetClass<T> innerTarget = SourceClass.this.new ParentInnerClass().parentPrivateMethod(targetParam);
                rawList.add(innerTarget);
            }
            
            return rawList.size();
        }
        
        // Overloaded method (overload_exist feature)
        protected int sourceInnerMethod(TargetClass<T> targetParam, int num) {
            return num;
        }
    }
}

class ParentClass<T> {
    private TargetClass<T> parentPrivateMethod(TargetClass<T> targetParam) {
        return targetParam;
    }
    
    class ParentInnerClass {
        TargetClass<T> parentPrivateMethod(TargetClass<T> targetParam) {
            return targetParam;
        }
    }
}

public class TargetClass<U> {
    public static class StaticNestedTargetClass {}
    
    public void instanceMethod() {
        new StaticNestedTargetClass();
    }
}