import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Function;

// Source enum class (private modifier, same package, empty feature list)
private enum SourceEnum {
    INSTANCE;

    // per_condition: source contains the field of the target
    private final TargetEnum targetField = TargetEnum.VALUE1;

    // Method to be refactored (instance, base type return, default access, position: source)
    int targetMethod(TargetEnum param) { // per_condition: target parameter
        int result = 0;

        // IfStatement (private modifier, ClassName.field, 1, pos: diff_package_others)
        private void ifBlock() {
            String classNameField = TargetEnum.ClassField; // ClassName.field
            int num = 1; // target_feature: 1
            if (classNameField.contains(String.valueOf(num))) { // IfStatement
                result = num * 10;
            }
        }

        // Try statement
        try {
            ifBlock();
            
            // Expression statement
            String targetValue = param.getInnerClass().getValue(); // variable call
            targetValue = targetValue + "_processed_" + result; // expression statement
            
            // numbers:2, modifier:public, exp:TypeMethodReference
            public Function<String, String> typeRef = String::toUpperCase; // TypeMethodReference
            String processed = typeRef.apply(targetValue + "_2"); // numbers:2
            
            // this.methodName(arguments)
            this.helperMethod(processed);
            
            // IOException
            if (processed.isEmpty()) {
                throw new IOException("Empty processed value"); // IOException
            }
            
            // Used by reflection
            Method method = SourceEnum.class.getDeclaredMethod("targetMethod", TargetEnum.class);
            method.setAccessible(true);
            result = (int) method.invoke(this, param);
            
        } catch (IOException e) {
            result = -1;
        } catch (Exception e) {
            result = -2;
        }

        // Variable call
        param.getInnerClass().setValue(String.valueOf(result));
        
        // No new exception
        return result;
    }

    // Helper method for this.methodName(arguments)
    private void helperMethod(String arg) {
        targetField.getInnerClass().setValue(arg);
    }
}

// Target enum class (default modifier, member inner class target_feature)
enum TargetEnum {
    VALUE1("value1"), VALUE2("value2");

    // ClassName.field for IfStatement feature
    public static final String ClassField = "target_field_1";

    private final String value;
    private final InnerClass innerClass;

    // Member inner class (target_feature)
    public class InnerClass {
        private String innerValue;

        public String getValue() {
            return innerValue;
        }

        public void setValue(String value) {
            this.innerValue = value;
        }
    }

    TargetEnum(String value) {
        this.value = value;
        this.innerClass = new InnerClass();
        this.innerClass.setValue(value);
    }

    // Variable call: getter for inner class
    public InnerClass getInnerClass() {
        return innerClass;
    }

    public String getValue() {
        return value;
    }
}