package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Target class: public modifier, type parameter, implements interface, anonymous inner class
interface DataProcessor<T> {
    void process(T data);
}

public class TargetClass<T> implements DataProcessor<T> {
    public static String staticField = "targetStaticField";
    private T value;

    // Anonymous inner class (target_feature)
    private DataProcessor<String> anonymousProcessor = new DataProcessor<String>() {
        @Override
        public void process(String data) {
            System.out.println("Processed: " + data);
        }
    };

    public TargetClass() {}

    // Protected constructor for ConstructorInvocation feature
    protected TargetClass(T value) {
        this.value = value;
    }

    @Override
    public void process(T data) {
        this.value = data;
    }

    public T getValue() {
        return value;
    }
}

// Source class: public modifier, local inner class, static nested class, same package as target
public class SourceClass {
    // Static nested class (source_class feature)
    public static class StaticNestedSource {
        public int nestedValue = 10;
    }

    // Method to be refactored: instance, public access, base return type (int), target parameter (per_condition)
    public int refactorMethod(TargetClass<String> targetParam) {
        // Variable call feature
        int count = 0;
        String varCall = TargetClass.staticField;

        // ConstructorInvocation feature: protected modifier, ClassName.field, 1, pos=source
        TargetClass<String> newTarget = new TargetClass<>(varCall + 1);

        // Do statement feature
        do {
            // NullPointerException feature (intentional null access scenario)
            if (targetParam == null) {
                String nullAccess = targetParam.getValue(); // Triggers NPE when targetParam is null
            }
            count++;
            // Override_violation feature (invalid attempt to override non-overridable method)
            try {
                StaticNestedSource nested = new StaticNestedSource() {
                    // Compile-time override violation (static method can't be overridden)
                    public static int nestedValue = 20;
                };
            } catch (Exception e) {
                // No new exception thrown feature (no custom exception instantiation)
            }
        } while (count < 5);

        // Local inner class (source_class feature)
        class LocalInnerClass {
            public void updateCount() {
                count += StaticNestedSource.nestedValue;
            }
        }
        new LocalInnerClass().updateCount();

        // No new exception thrown feature (no 'new Exception()' statements)
        return count;
    }
}