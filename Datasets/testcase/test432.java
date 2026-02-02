import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;

// Custom annotation for "the attribute values of annotations" position
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ProcessAnnotation {
    // Annotation attribute to hold method reference (pos: the attribute values of annotations)
    String methodRef() default "processHelper";
    String constructorRef() default "TargetClass";
}

// Source abstract class (note: abstract is modifier, type is normal class)
abstract class SourceClass {
    // Member inner class (fulfills source_class feature)
    class SourceMemberInner {
        public String getInnerValue() {
            return "inner_value";
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Target method: instance, TargetClass return, default access, in source class
    @ProcessAnnotation(
        methodRef = "processHelper", // pos: the attribute values of annotations (normal method)
        constructorRef = "TargetClass" // pos: the attribute values of annotations (call method constructor)
    )
    TargetClass processData(TargetClass.TargetInnerClass param) {
        // Fulfill per_condition: method contains target inner class parameter
        if (param == null) {
            throw new IllegalArgumentException("Target parameter cannot be null");
        }

        // ForStatement (private modifier, obj.field=1, pos:source)
        private void forLoop() {
            int objFieldVal = 1; // target_feature: obj.field, numbers:1
            for (int i = 0; i < objFieldVal; i++) { // ForStatement
                param.setInnerValue(param.getInnerValue() + "_loop_" + i);
            }
        }
        forLoop();

        // Constructor invocation
        TargetClass newTargetInstance = new TargetClass("default_value");

        // Type declaration statement
        class BoundedType<T extends String & Comparable<T>> { // with_bounds feature
            T process(T val) {
                return (T) val.toUpperCase();
            }
        }
        BoundedType<String> boundedType = new BoundedType<>();

        // Variable call to target parameter
        String targetVar = param.getInnerValue();
        String processedVar = boundedType.process(targetVar);
        param.setInnerValue(processedVar);

        // Requires_try_catch feature
        TargetClass result = null;
        try {
            // Normal method (final modifier, 1/target/normal/this.methodName(arguments))
            result = this.processHelper(param, 1); // numbers:1, target type, this.methodName(arguments)
        } catch (NullPointerException e) {
            // Handle exception (requires_try_catch)
            result = newTargetInstance;
        }

        return result; // TargetClass Type return
    }

    // Final normal method (method_feature: 1/target/normal/this.methodName(arguments))
    private final TargetClass processHelper(TargetClass.TargetInnerClass param, int flag) {
        // numbers:1, target type, normal method
        TargetClass outerInstance = param.getTargetOuterInstance();
        outerInstance.setTargetValue(param.getInnerValue() + "_helper_" + flag);
        return outerInstance;
    }

    // Abstract method (required for abstract class modifier)
    public abstract void abstractMethod();
}

// Target class: default modifier, anonymous inner class (target_feature)
class TargetClass {
    private String targetValue;

    // Target inner class (target_inner)
    public class TargetInnerClass {
        private String innerValue = targetValue + "_inner";

        public String getInnerValue() { return innerValue; }
        public void setInnerValue(String val) { this.innerValue = val; }

        // Get outer instance for return type
        public TargetClass getTargetOuterInstance() {
            return TargetClass.this;
        }
    }

    // Anonymous inner class (fulfills target_feature)
    private final Function<String, String> anonymousInner = new Function<>() {
        @Override
        public String apply(String s) {
            return s + "_anonymous_processed";
        }
    };

    // Constructor
    public TargetClass(String targetValue) {
        this.targetValue = anonymousInner.apply(targetValue);
    }

    // Getter/Setter
    public String getTargetValue() { return targetValue; }
    public void setTargetValue(String targetValue) { this.targetValue = targetValue; }
}

// Others class for call_method (type: others_class)
class CallerClass {
    // Call method: public modifier, constructor/ClassName.methodName(arguments) in annotation attributes
    @ProcessAnnotation(constructorRef = "new TargetClass(\"caller_default\")") // pos: annotation attributes
    public Object invokeSourceMethod() {
        SourceClass source = new SourceClass() {
            @Override
            public void abstractMethod() {} // Implement abstract method
        };
        TargetClass target = new TargetClass("caller_init");
        TargetClass.TargetInnerClass targetInner = target.new TargetInnerClass();

        // Constructor feature (new ClassName()) + ClassName.methodName(arguments)
        TargetClass result = source.processData(targetInner); // ClassName.methodName(arguments)
        return result; // Object return type
    }
}