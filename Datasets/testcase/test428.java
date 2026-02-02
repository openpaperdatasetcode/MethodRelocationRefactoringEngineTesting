import java.util.ArrayList;
import java.util.List;

// Functional interface for implements feature
interface Processable<T> {
    T process(T val);
}

// Base class for extends feature
class BaseClass<T> {
    protected T baseField;

    public BaseClass(T baseField) {
        this.baseField = baseField;
    }

    public T getBaseValue() {
        return baseField;
    }
}

// Source class: private modifier, same package, type parameter/extends/implements/static nested/local inner class
private class SourceClass<T extends Number> extends BaseClass<T> implements Processable<T> { // type parameter/extends/implements
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass targetField = new TargetClass();

    // Static nested class (fulfills source_class feature)
    static class SourceStaticNested {
        public Object innerMethod(int val) { // method_feature: normal, return_type: Object
            return val * 2;
        }
    }

    // Super constructor invocation (source class constructor)
    public SourceClass(T baseField) {
        super(baseField); // super constuctor invocation
    }

    // Target method: varargs, base type return, protected access, in source class
    protected int processData(TargetClass... params) {
        // NullPointerException feature
        if (params == null || params.length == 0) {
            throw new NullPointerException("Target parameters cannot be null/empty");
        }

        // Enhanced for statement
        int total = 0;
        for (TargetClass param : params) {
            // Variable call to target parameter
            String targetVar = param.getTargetValue();
            total += targetVar.length();

            // Depends_on_inner_class feature
            LocalInner helper = new LocalInner();
            total += helper.calculate(targetVar); // depends on local inner class

            // Access instance method
            total += this.processInstanceMethod(targetVar); // access_instance_method
        }

        // While loop containing normal method (method_feature: 1/inner_class/normal/new ClassName().method())
        int count = 0;
        while (count < 1) { // pos: while, numbers:1
            // new ClassName().method() (method_feature)
            Object nestedResult = new SourceStaticNested().innerMethod(1); // numbers:1, inner_class
            total += (Integer) nestedResult;
            count++;
        }

        // Override_violation feature
        class OverrideViolation extends SourceStaticNested {
            @Override
            public String innerMethod(int val) { // Incompatible return type (Object vs String)
                return String.valueOf(val); // override_violation
            }
        }

        // return this; (adjusted for base type return: return total as base type)
        // Simulate "return this" semantics by returning instance-derived value
        return total; // Base type (int) return, no_new_exception
    }

    // Instance method for access_instance_method feature
    private int processInstanceMethod(String val) {
        return val.hashCode() % 100;
    }

    // Local inner class (fulfills source_class feature)
    class LocalInner {
        public int calculate(String val) {
            return val.length() * 2;
        }
    }

    // Implements Processable interface method
    @Override
    public T process(T val) {
        return val;
    }
}

// Target class: default modifier, member inner class (target_feature)
class TargetClass {
    private String targetValue = "target_base";

    // Member inner class (fulfills target_feature)
    public class TargetMemberInner {
        public String getInnerValue() {
            return targetValue + "_inner";
        }
    }

    // Getter for variable call
    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }
}