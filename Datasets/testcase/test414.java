import java.lang.reflect.Method;
import java.util.function.Consumer;

// Source enum class: default modifier, same package, member inner/anonymous inner class
enum SourceEnum {
    SOURCE_INSTANCE;

    // Target class field (fulfills per_condition: source contains target's field)
    TargetEnum<String> targetField = TargetEnum.TARGET_INSTANCE;

    // Member inner class (fulfills source_class feature)
    class SourceMemberInner {
        int innerField = 2; // For depends_on_inner_class
        void helperMethod(int val) {
            System.out.println(val);
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    Consumer<Integer> anonymousInner = new Consumer<>() {
        @Override
        public void accept(Integer val) {
            SourceEnum.this.processData(TargetEnum.TARGET_INSTANCE);
        }
    };

    // Target method: normal, void return, strictfp access, in source enum
    strictfp void processData(TargetEnum<String> param) {
        // VariableDeclarationStatement (private modifier, super.field=2, pos:diff_package_others)
        // Simulate diff_package_others via reflection to super field
        private int superFieldVal = 2; // target_feature: super.field, numbers:2
        
        // Constructor invocation
        SourceMemberInner innerInstance = new SourceMemberInner();
        
        // Continue statement
        loop:
        for (int i = 0; i < 5; i++) {
            if (i == 3) continue loop;
            // Variable call to target parameter
            String targetVar = param.getTargetValue();
            
            // Access instance method
            innerInstance.helperMethod(i);
            
            // Depends on inner class
            int innerVal = innerInstance.innerField;
            
            // This method call (this.methodName(arguments))
            this.validateValue(targetVar + innerVal);
            
            // Assert statement
            assert targetVar != null : "Target value cannot be null";
            
            // Used by reflection (duplicate feature as required)
            try {
                Method method = param.getClass().getMethod("getTargetValue");
                String reflectedVal = (String) method.invoke(param);
                System.out.println(reflectedVal);
            } catch (Exception e) {
                // No new exception thrown (no_new_exception)
                System.out.println("Reflection error: " + e.getMessage());
            }
        }
    }

    // Helper method for this.methodName(arguments)
    private void validateValue(String val) {
        if (val.isEmpty()) System.out.println("Empty value");
    }
}

// Target enum class: abstract modifier, type parameter/static nested class (target_feature)
abstract enum TargetEnum<T> {
    TARGET_INSTANCE("test");

    // Static nested class (fulfills target_feature)
    public static class TargetStaticNested<T> {}

    private final T targetValue;

    TargetEnum(T targetValue) {
        this.targetValue = targetValue;
    }

    // Getter for variable call
    public T getTargetValue() {
        return targetValue;
    }

    // Abstract method to fulfill abstract enum requirement
    public abstract void abstractMethod();
}