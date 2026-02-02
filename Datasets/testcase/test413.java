import java.util.function.Consumer;

// Source generic class: public modifier, same package, anonymous inner/local inner class
public class SourceClass<T> {
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass<String> targetField = new TargetClass<>("source_target_field");

    // Base method for override_violation
    public String baseMethod() {
        return "base_value";
    }

    // Inner class (source_inner) containing target method
    class SourceInnerClass {
        // Target method: instance, void return, default access, in source_inner
        void processData(TargetClass<String> param) {
            // ContinueStatement (private modifier, obj.field=1, pos:diff_package_others)
            // Simulate diff_package_others via cross-class field reference
            private void continueLoop() {
                int objFieldVal = 1; // target_feature: obj.field, numbers:1
                loop:
                for (int i = 0; i < 5; i++) {
                    if (i % 2 == 0) continue loop; // ContinueStatement
                    System.out.println("Loop iteration: " + i + ", obj.field: " + objFieldVal);
                }
            }
            continueLoop();

            // If statement
            if (param.getTargetValue() == null) {
                // No new exception thrown (no_new_exception)
                param.setTargetValue("default_value");
            }

            // Variable call to target parameter
            String targetVar = param.getTargetValue();
            
            // Uses_outer_this (access outer class instance via this)
            SourceClass<T> outerThis = SourceClass.this; // uses_outer_this
            outerThis.targetField.setTargetValue(targetVar + "_outer_this");

            // Override_violation: inner class with incompatible return type override
            class OverrideViolation extends SourceInnerClass {
                @Override
                public Integer baseMethod() { // Incompatible return type (String vs Integer)
                    return 1;
                }
            }

            // Anonymous inner class (fulfills source_class feature)
            Consumer<String> anonymousInner = new Consumer<>() {
                @Override
                public void accept(String s) {
                    System.out.println("Anonymous inner class: " + s);
                }
            };
            anonymousInner.accept(targetVar);

            // Local inner class (fulfills source_class feature)
            class LocalInnerClass {
                void printValue(String val) {
                    System.out.println("Local inner class: " + val);
                }
            }
            LocalInnerClass localInner = new LocalInnerClass();
            localInner.printValue(targetVar);
        }
    }
}

// Target generic class: protected modifier, static nested class (target_feature)
protected class TargetClass<U> {
    private U targetValue;

    // Static nested class (fulfills target_feature)
    public static class TargetStaticNested<V> {
        public V processValue(V val) {
            return val;
        }
    }

    public TargetClass(U targetValue) {
        this.targetValue = targetValue;
    }

    // Getter/Setter for variable call
    public U getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(U targetValue) {
        this.targetValue = targetValue;
    }
}