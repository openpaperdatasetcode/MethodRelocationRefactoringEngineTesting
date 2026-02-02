import java.lang.reflect.Method;
import java.util.Arrays;

// Source generic class: public, same package, no extra features
public class SourceClass<T> {
    // Instance field for access_instance_field feature
    private T instanceField;

    // Base method for override_violation (incompatible return type when overridden)
    public String baseMethod() {
        return "base";
    }

    // Target method: varargs, void return, protected access, in source class
    protected <V> void processData(TargetClass<V>... params) {
        // Expression statement
        int paramCount = params.length;
        
        // Variable call to target parameter (fulfills per_condition)
        for (TargetClass<V> param : params) {
            if (param != null) {
                V targetVar = param.getTargetValue();
                // Access instance field
                this.instanceField = (T) targetVar;
                
                // Override_violation: method with same signature but incompatible return type
                // (simulated via inner class override)
                class OverrideViolation extends SourceClass<String> {
                    @Override
                    public Integer baseMethod() { // Incompatible return type (String vs Integer)
                        return 1;
                    }
                }
                
                // Used by reflection
                try {
                    Method method = param.getClass().getMethod("getTargetValue");
                    V reflectedVal = (V) method.invoke(param);
                    System.out.println("Reflected value: " + reflectedVal);
                } catch (Exception e) {
                    // No new exception thrown (no_new_exception)
                    System.out.println("Reflection error: " + e.getMessage());
                }
            }
        }
        
        // Additional expression statement
        System.out.println("Processed " + paramCount + " target parameters");
    }

    public void setInstanceField(T instanceField) {
        this.instanceField = instanceField;
    }

    public T getInstanceField() {
        return instanceField;
    }
}

// Target generic class: public, no extra target_features
public class TargetClass<U> {
    private U targetValue;

    public TargetClass(U targetValue) {
        this.targetValue = targetValue;
    }

    public U getTargetValue() {
        return targetValue;
    }

    // Inner class for call_method (type: inner_class)
    protected class TargetInnerClass<W> {
        // Call method: protected modifier, generic, ClassName.methodName(arguments) in array initialization
        public String invokeSourceMethod() {
            SourceClass<String> source = new SourceClass<>();
            // Array initialization (pos: array initialization) containing ClassName.methodName(arguments)
            TargetClass<W>[] targetArray = new TargetClass[]{
                new TargetClass<>((W) "value1"),
                new TargetClass<>((W) "value2")
            };
            // ClassName.methodName(arguments) (target_feature)
            source.processData(targetArray);
            
            return "Processed " + Arrays.toString(targetArray); // String return type
        }
    }
}