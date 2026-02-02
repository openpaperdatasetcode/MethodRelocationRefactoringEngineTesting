import java.lang.reflect.Method;
import java.util.function.Function;

// Source abstract class: public modifier, same package, two member inner classes
public abstract class SourceAbstractClass {
    // First member inner class (fulfills source_class feature)
    class FirstMemberInner {}
    // Second member inner class (fulfills source_class feature twice)
    class SecondMemberInner {
        // Recursive inner class (source_inner_rec) containing target method
        class SourceInnerRecClass {
            // Target method: varargs, TargetAbstractClass return, protected access, in source_inner_rec
            protected TargetAbstractClass processData(TargetAbstractClass... params) {
                // Super keywords (abstract class implicit super)
                super.toString();

                // NullPointerException feature
                if (params == null || params.length == 0) {
                    throw new NullPointerException("Target parameters cannot be null/empty");
                }

                // Synchronized statement
                Object lock = new Object();
                synchronized (lock) {
                    // Variable call to target parameter (fulfills per_condition)
                    for (TargetAbstractClass param : params) {
                        String targetVar = param.getTargetValue();
                        param.setTargetValue(targetVar + "_processed");
                    }
                }

                // LambdaExpression (numbers:3, modifier:private)
                private Function<TargetAbstractClass, Integer> lambdaFunc = (param) -> {
                    return param.getTargetValue().length() * 3; // numbers:3
                };

                // Used by reflection
                try {
                    Method method = params[0].getClass().getMethod("getTargetValue");
                    String reflectedVal = (String) method.invoke(params[0]);
                    System.out.println("Reflected value: " + reflectedVal);
                } catch (Exception e) {
                    // No new exception thrown (no_new_exception)
                    System.out.println("Reflection error: " + e.getMessage());
                }

                // Execute lambda expression (numbers:3)
                lambdaFunc.apply(params[0]);

                // Return TargetClass Type (first parameter as result)
                return params[0];
            }
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}

// Target abstract class: protected modifier, static nested class (target_feature)
protected abstract class TargetAbstractClass {
    private String targetValue = "target_base";

    // Static nested class (fulfills target_feature)
    public static class TargetStaticNested {
        public String processStatic(String val) {
            return val.toUpperCase();
        }
    }

    // Getter/Setter for variable call
    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    // Abstract method (required for abstract class)
    public abstract void targetAbstractMethod();
}