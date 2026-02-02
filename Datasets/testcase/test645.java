import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {}

public abstract class SourceClass {
    private String instanceField = "sourceInstanceField";

    Object sourceMethod(TargetClass targetParam, Object... varargs) {
        @MethodAnnotation
        String varCall = this.instanceField;
        
        // Variable call to target parameter's instance field
        String targetFieldVal = targetParam.targetInstanceField;
        
        // Overload exist - call overloaded method
        sourceMethod(targetParam);
        
        // Anonymous inner class usage
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println(varCall + targetFieldVal);
            }
        };
        anonymous.run();
        
        // Local inner class
        class LocalInnerClass {
            String getCombinedValue() {
                return varCall + targetFieldVal;
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        
        try {
            return localInner.getCombinedValue();
        } catch (Exception e) {
            return null;
        }
    }

    // Overloaded method (overload_exist feature)
    Object sourceMethod(TargetClass targetParam) {
        return targetParam.targetInstanceField;
    }
}

public abstract class TargetClass {
    String targetInstanceField = "targetInstanceField";

    public TargetClass() {
        // Anonymous inner class in target class
        new Runnable() {
            @Override
            public void run() {
                System.out.println(targetInstanceField);
            }
        }.run();
    }
}