import java.util.function.Function;

// Base class for source class extends feature
class BaseGenericClass<T> {
    protected T baseField;

    public BaseGenericClass(T baseField) {
        this.baseField = baseField;
    }

    protected String superMethod() {
        return "super_method_result_" + baseField;
    }
}

// Source generic class: protected modifier, same package, extends feature
protected class SourceClass<T> extends BaseGenericClass<T> { // extends feature
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass<T> targetField = new TargetClass<>((T) "source_target_init");

    // First-level inner class
    class SourceInnerClass {
        // Recursive inner class (source_inner_rec) containing abstract method
        abstract class SourceInnerRecClass {
            // Instance field for access_instance_field feature
            protected T innerInstanceField;

            // Target method: abstract, base type return, public access, in source_inner_rec
            public abstract int processData(TargetClass<T>.TargetInnerClass param);

            // Call method: protected modifier, source type, recursion/super.methodName() in instance code blocks
            protected String invokeProcessData(TargetClass<T>.TargetInnerClass param, int depth) {
                // Instance code blocks position (pos: instance code blocks)
                {
                    // Recursion feature (terminate at depth 0)
                    if (depth <= 0) {
                        // Super.methodName() feature
                        String superVal = SourceClass.super.superMethod(); // super.methodName()
                        return superVal + "_recursion_end";
                    }

                    // Constructor invocation
                    this.innerInstanceField = (T) ("recursion_depth_" + depth);
                    TargetClass<T> newTarget = new TargetClass<>(innerInstanceField);

                    // Variable call to target parameter
                    String targetVar = param.getInnerValue().toString();
                    
                    // Access_instance_field
                    param.setInnerValue((T) (targetVar + "_" + this.innerInstanceField)); // access_instance_field

                    // Recursive call (recursion feature)
                    return invokeProcessData(newTarget.new TargetInnerClass(), depth - 1);
                }
            }
        }
    }

    // Concrete implementation of abstract inner class
    class ConcreteInnerRecClass extends SourceInnerClass.SourceInnerRecClass {
        @Override
        public int processData(TargetClass<T>.TargetInnerClass param) {
            // Constructor invocation
            TargetClass<T>.TargetInnerClass newInner = new TargetClass<T>((T) "default").new TargetInnerClass();

            // Variable call to target parameter
            String targetVar = param.getInnerValue().toString();
            
            // Access_instance_field
            this.innerInstanceField = (T) targetVar; // access_instance_field

            // Assert statement
            assert targetVar.length() > 0 : "Target value cannot be empty"; // assert statement

            // No new exception thrown (no_new_exception)
            return targetVar.length(); // Base type (int) return
        }
    }

    // Constructor for extends feature (super constructor invocation)
    public SourceClass(T baseField) {
        super(baseField);
    }
}

// Target generic class: protected modifier, anonymous inner class (target_feature)
protected class TargetClass<U> {
    private U targetValue;

    // Target inner class (target_inner)
    public class TargetInnerClass {
        private U innerValue = TargetClass.this.targetValue;

        public U getInnerValue() { return innerValue; }
        public void setInnerValue(U val) { this.innerValue = val; }
    }

    // Anonymous inner class (fulfills target_feature)
    private final Function<U, U> anonymousInner = new Function<>() {
        @Override
        public U apply(U u) {
            return (U) (u.toString() + "_anonymous_processed");
        }
    };

    // Constructor
    public TargetClass(U targetValue) {
        this.targetValue = anonymousInner.apply(targetValue);
    }

    public U getTargetValue() { return targetValue; }
    public void setTargetValue(U targetValue) { this.targetValue = targetValue; }
}