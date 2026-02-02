import java.util.ArrayList;
import java.util.List;

// Super interface for target_class implements feature
interface TargetProcessable {
    void process(String value);
}

// Source normal class (protected modifier, same package, empty feature list)
protected class SourceClass {
    // per_condition: source contains the field of the target
    private final AbstractTargetClass targetField = new ConcreteTargetClass();

    // Method to be refactored (varargs, Object return, private access, position: source)
    private Object targetMethod(AbstractTargetClass... params) { // per_condition: target parameter
        // Type declaration statement
        List<String> result = new ArrayList<>();
        String targetVal;
        int count;

        // For statement
        for (count = 0; count < params.length; count++) {
            AbstractTargetClass param = params[count];
            // Variable call
            targetVal = param.getValue() + "_processed_" + count;
            param.setValue(targetVal);
            result.add(targetVal);

            // Call method in constructor parameter list (pos: the parameter list of constructors)
            ConcreteTargetClass newTarget = new ConcreteTargetClass(param::callMethod); // instanceReference.methodName(arguments)
            newTarget.callMethod(targetVal); // overloading feature (call no-arg overload)
        }

        // No new exception
        return result;
    }
}

// Target abstract class (abstract modifier, implements, local inner class target_feature)
abstract class AbstractTargetClass implements TargetProcessable { // implements feature (target_feature)
    private String value = "TARGET_DEFAULT_5836";

    // Local inner class (target_feature)
    @Override
    public void process(String value) {
        class TargetLocalInnerClass { // local inner class feature
            String format(String s) {
                return s.toUpperCase() + "_local_inner";
            }
        }
        this.value = new TargetLocalInnerClass().format(value);
    }

    // Call method (target type, default modifier, overloading, instanceReference.methodName(arguments))
    void callMethod(String val) {
        process(val);
    }

    // Overloading method (overloading feature)
    void callMethod() {
        process(this.value);
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Abstract method (required for abstract target class)
    public abstract void abstractTargetMethod();
}

// Concrete implementation of target abstract class
class ConcreteTargetClass extends AbstractTargetClass {
    public ConcreteTargetClass() {}

    // Constructor for call_method position (parameter list)
    public ConcreteTargetClass(java.util.function.Consumer<String> consumer) {
        consumer.accept(this.getValue());
    }

    @Override
    public void abstractTargetMethod() {}
}