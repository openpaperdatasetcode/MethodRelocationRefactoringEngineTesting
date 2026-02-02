import java.io.IOException;
import java.util.List;

private class SourceClass<T extends List<T>> extends ArrayList<T> {
    private String outerPrivateField = "privateField";
    
    public static class StaticNestedSourceClass {
        public int innerInstanceMethod() {
            return 42;
        }
    }
    
    public int sourceMethod(TargetClass<T> targetParam) throws IOException {
        super();
        StaticNestedSourceClass nestedObj = new StaticNestedSourceClass();
        int innerVal = nestedObj.innerInstanceMethod();
        String varCall = this.outerPrivateField;
        targetParam.staticNestedTargetClass.innerTargetMethod();
        targetParam.targetInstanceMethod();
        
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println(varCall);
            }
        };
        anonymous.run();
        
        if (innerVal < 0) {
            throw new IOException("Required throws condition");
        }
        return innerVal;
    }
}

public class TargetClass<U> {
    public static class StaticNestedTargetClass {
        public static void innerTargetMethod() {}
    }
    
    public void targetInstanceMethod() {}
}