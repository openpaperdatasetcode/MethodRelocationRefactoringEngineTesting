import java.util.function.Consumer;

// Functional interface for source_class implements feature
interface GenericProcessable<T> {
    void process(T value);
}

// Source protected generic class (same package, implements, static nested class, local inner class)
protected class SourceClass<T extends CharSequence> implements GenericProcessable<T> {
    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass<U> {
        // Call method (source type, default modifier, instance, ClassName.methodName(arguments))
        static int staticCallMethod(U val) { // instance feature (static method as instance-like call)
            return val.toString().length();
        }
    }

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord<T>(T data) {
        // Accessor method to be refactored (accessor, void return, default access)
        void processTarget(AbstractTargetClass<T> param) { // per_condition: target parameter
            // type declaration statement
            T targetVal;
            int count = 0;
            transient boolean flag = true; // transient modifier for ConstructorInvocation

            // ConstructorInvocation (transient modifier, obj.field, 2, pos: diff_package_others)
            transient void constructorBlock() { // transient modifier
                // diff_package_others position (logical separation)
                class DiffPackageSimulator {
                    void invokeConstructor() {
                        ConcreteTargetClass<T> newTarget = new ConcreteTargetClass<>((T) "constructor_init"); // ConstructorInvocation
                        T objField = newTarget.value; // obj.field
                        int num = 2; // target_feature: 2

                        newTarget.setValue((T) (objField + "_constructor_" + num));
                        param.setValue(newTarget.getValue());
                    }
                }
                new DiffPackageSimulator().invokeConstructor();
            }

            // Execute constructor block
            constructorBlock();

            // while statement
            while (count < 3) {
                // synchronized statement
                synchronized (param) {
                    targetVal = (T) (param.getValue() + "_while_loop_" + count);
                    param.setValue(targetVal);
                    count++;
                }
            }

            // Call method in if/else conditions (pos: if/else conditions, instance, ClassName.methodName(arguments))
            int callResult;
            if (param.getValue().length() > 0) {
                callResult = SourceClass.StaticNestedSourceClass.staticCallMethod(param.getValue()); // ClassName.methodName(arguments)
            } else {
                callResult = SourceClass.StaticNestedSourceClass.staticCallMethod((T) "default_val");
            }

            // throw statement (controlled exception)
            if (callResult <= 0) {
                throw new IllegalArgumentException("Invalid call method result: " + callResult);
            }

            // local inner class (source_class feature)
            class LocalInnerProcessor {
                void enhanceValue(AbstractTargetClass<T> target) {
                    target.setValue((T) (target.getValue() + "_local_inner_processed_" + callResult));
                }
            }
            new LocalInnerProcessor().enhanceValue(param);

            // Variable call (target generic class getter/setter)
            param.setValue((T) (param.getValue() + "_variable_call"));

            // No new exception
        }
    }

    // Implement super interface method
    @Override
    public void process(T value) {
        System.out.println("Processed value: " + value);
    }

    // Helper method to create inner record instance
    public InnerSourceRecord<T> createInnerRecord(T data) {
        return new InnerSourceRecord<>(data);
    }
}

// Target abstract generic class (default modifier, empty target_feature list)
abstract class AbstractTargetClass<T> {
    // obj.field for ConstructorInvocation feature
    protected T value;

    // Constructor
    public AbstractTargetClass(T value) {
        this.value = value;
    }

    // Variable call: getter/setter (accessor feature)
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    // Abstract method (required for abstract target class)
    public abstract void abstractTargetMethod();
}

// Concrete implementation of target abstract class
class ConcreteTargetClass<T> extends AbstractTargetClass<T> {
    public ConcreteTargetClass(T value) {
        super(value);
    }

    @Override
    public void abstractTargetMethod() {
        // Empty implementation for completeness
    }
}