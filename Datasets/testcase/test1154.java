import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source generic class (public modifier, same package, static nested class, anonymous inner class)
public class SourceClass<T extends CharSequence> {
    // per_condition: source contains the field of the target
    private final SealedTargetClass<T> targetField = new ConcreteTargetClass<>("init_value_5838");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        static String format(String s) {
            return s.toUpperCase() + "_static";
        }
    }

    // Anonymous inner class (source_class feature)
    private final Consumer<String> anonymousInner = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("Anonymous inner process: " + s);
        }
    };

    // Inner class (method_position: source_inner)
    public class InnerSourceClass {
        // Method to be refactored (instance, List<String> return, private access)
        private List<String> process(SealedTargetClass<T>.InnerClass param) { // per_condition: target parameter (target_inner)
            // Type declaration statement
            List<String> result = new ArrayList<>();
            String targetVal;
            int switchKey;

            // SwitchStatement (private modifier, ClassName.field, 1, pos: source)
            private void switchBlock() {
                String classField = SealedTargetClass.CLASS_FIELD; // ClassName.field
                int num = 1; // target_feature: 1

                // Access instance field
                targetVal = param.outerTarget.value + "_" + classField + "_" + num; // access_instance_field

                switchKey = targetVal.length() % 3;
                switch (switchKey) {
                    case 0:
                        param.setValue((T) (targetVal + "_case0"));
                        break;
                    case 1:
                        param.setValue((T) (targetVal + "_case1"));
                        break;
                    default:
                        param.setValue((T) (targetVal + "_default"));
                        break;
                }
            }

            // Requires try-catch feature
            try {
                switchBlock();
                // Variable call
                targetVal = param.getValue().toString();
                result.add(targetVal);
                result.add(StaticNestedSourceClass.format(targetVal));
                
                // Trigger anonymous inner class
                anonymousInner.accept(targetVal);
            } catch (NullPointerException e) {
                result.add("Error: " + e.getMessage());
            }

            return result;
        }
    }
}

// Target generic class (sealed modifier, member inner class target_feature)
sealed class SealedTargetClass<T> permits ConcreteTargetClass<T> {
    // ClassName.field for SwitchStatement feature
    public static final String CLASS_FIELD = "TARGET_CLASS_FIELD_5838";
    protected T value;
    protected final InnerClass innerClass;

    // Member inner class (target_feature)
    public class InnerClass {
        private T innerValue;
        protected final SealedTargetClass<T> outerTarget;

        public InnerClass(SealedTargetClass<T> outerTarget) {
            this.outerTarget = outerTarget;
            this.innerValue = outerTarget.value;
        }

        // Variable call: getter/setter
        public T getValue() {
            return innerValue;
        }

        public void setValue(T value) {
            this.innerValue = value;
        }
    }

    public SealedTargetClass(T value) {
        this.value = value;
        this.innerClass = new InnerClass(this);
    }
}

// Concrete implementation of sealed target class
final class ConcreteTargetClass<T> extends SealedTargetClass<T> {
    public ConcreteTargetClass(T value) {
        super(value);
    }
}