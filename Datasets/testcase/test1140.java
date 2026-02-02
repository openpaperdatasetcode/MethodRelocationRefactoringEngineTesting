import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Sealed interface for source_class permits feature
sealed interface SourceSealedInterface permits SourceClass.LocalInnerClass {}

// Source normal class (public modifier, same package, permits, local inner class, anonymous inner class)
public class SourceClass {
    // per_condition: source contains the field of the target
    private final TargetClass targetField = new TargetClass();

    // Anonymous inner class (source_class feature)
    private final Consumer<String> anonymousInner = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("Anonymous inner: " + s);
        }
    };

    // Method to be refactored (varargs, List<String> return, public access, position: source)
    public List<String> targetMethod(TargetClass... params) { // per_condition: target parameter
        List<String> result = new ArrayList<>();

        // Type declaration statement
        String targetVal;
        TargetClass.InnerClass innerObj;
        int num;

        // TypeDeclarationStatement (private modifier, ClassName.field, 1, pos: inner class)
        private void typeDeclBlock() {
            // Local inner class (source_class feature) + permits feature (implements sealed interface)
            class LocalInnerClass implements SourceSealedInterface { // permits SourceClass.LocalInnerClass
                // ClassName.field (target_feature)
                final String classField = TargetClass.CLASS_FIELD + "_" + 1; // 1 (target_feature)
                
                void process(TargetClass param) {
                    targetVal = param.getValue() + "_" + classField;
                    innerObj = param.new InnerClass();
                }
            }

            // Inner class position for TypeDeclarationStatement
            LocalInnerClass local = new LocalInnerClass();
            for (TargetClass param : params) {
                local.process(param);
                result.add(targetVal);
            }
        }
        typeDeclBlock();

        // Constructor invocation
        TargetClass newTarget = new TargetClass("new_target_5818");
        innerObj = newTarget.new InnerClass();

        // Variable call
        for (TargetClass param : params) {
            String val = param.getValue() + "_processed";
            param.setValue(val);
            innerObj.setInnerValue(val);
            result.add(innerObj.getInnerValue());
        }

        // Override violation (attempt to override final method)
        @Override // Compile error: Cannot override final method
        public final String toString() {
            return newTarget.getValue();
        }

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod", TargetClass[].class);
            method.setAccessible(true);
            method.invoke(this, (Object) params);
        } catch (Exception e) {
            result.add("Reflection error: " + e.getMessage());
        }

        // No new exception
        anonymousInner.accept(result.toString());
        return result;
    }

    // Final method to trigger override violation
    public final String toString() {
        return "SourceClass";
    }
}

// Target normal class (default modifier, member inner class target_feature)
class TargetClass {
    // ClassName.field for TypeDeclarationStatement feature
    public static final String CLASS_FIELD = "target_class_field_5818";
    private String value = "target_value_5818";

    // Member inner class (target_feature)
    public class InnerClass {
        private String innerValue;

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Constructor for invocation
    public TargetClass() {}
    public TargetClass(String value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}