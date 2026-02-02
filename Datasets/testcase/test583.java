package refactoring.test;

import java.lang.reflect.Method;

// Source class: generic, default modifier, same package as target, member + local inner classes
class SourceClass<T> {
    // Member inner class (source feature)
    class SourceInnerClass {
        // Method to be refactored: public instance, returns TargetClass type, source_inner position
        public TargetClass<String> refactorMethod(TargetClass<String> targetParam) {
            // Type declaration statement
            TargetClass<String> targetInstance;
            // Variable call
            targetParam.value = "refactor";
            // Labeled statement
            label: {
                try {
                    // NullPointerException handling
                    if (targetParam == null) throw new NullPointerException();
                    // Constructor invocation
                    targetInstance = new TargetClass<>("initial");
                } catch (NullPointerException e) {
                    // No new exception instantiation
                    targetInstance = new TargetClass<>("fallback");
                    break label;
                }
            }

            // Local inner class (source feature)
            class LocalInnerClass {
                // Final recursive method in ternary operator (method feature)
                final TargetClass<String> recursiveMethod(int depth) {
                    // Recursion + super.methodName(arguments)
                    return (depth <= 1) ? (TargetClass<String>) super.toString() : recursiveMethod(depth - 1);
                }
            }

            // Ternary operator with recursive method call
            TargetClass<String> result = (targetParam.value.isEmpty()) ? 
                new LocalInnerClass().recursiveMethod(1) : targetInstance;

            // Override violation (invalid attempt to override final method)
            class InvalidOverride extends TargetClass<String> {
                @Override
                public final String getValue() { // Compile error: final method cannot be overridden
                    return "violation";
                }
            }

            // Used by reflection
            try {
                Method method = TargetClass.class.getMethod("setValue", String.class);
                method.invoke(result, "reflected");
            } catch (Exception e) {
                // No new exception
            }

            // Anonymous inner class (target feature usage)
            targetParam.callback = new Runnable() {
                @Override
                public void run() {
                    targetParam.value = "anonymous";
                }
            };

            return result;
        }
    }
}

// Target class: generic, default modifier, anonymous inner class feature
class TargetClass<T> {
    T value;
    Runnable callback;

    public TargetClass(T value) {
        this.value = value;
    }

    public final T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}