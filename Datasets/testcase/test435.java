import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Function;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {}

// Sealed interface with permits (simulate permits for normal class, Java spec compliant)
sealed interface PermitsInterface permits SourceClass, TargetClass {}

// Parent class for call_method (type: parent_class)
class ParentClass {
    // Call method: public modifier, normal, this.methodName(arguments) in functional interfaces
    public int invokeSourceMethod() {
        SourceClass source = new SourceClass();
        SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
        TargetClass target = source.targetField; // Use source's target field (per_condition)
        TargetClass.TargetInnerClass targetInner = target.new TargetInnerClass();

        // Functional interfaces position (pos: functional interfaces)
        Function<TargetClass.TargetInnerClass, Integer> func = (param) -> {
            // this.methodName(arguments) (target_feature)
            return inner.thisMethodCall(param, 2); // numbers:2, this.methodName(arguments)
        };

        try {
            return func.apply(targetInner);
        } catch (IllegalStateException e) {
            return -1; // No new exception thrown
        }
    }
}

// Source class: public modifier, same package, permits/local inner/member inner class
public class SourceClass extends ParentClass implements PermitsInterface { // permits feature
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass targetField = new TargetClass("source_target_field");

    // Member inner class (fulfills source_class feature: member inner class)
    class SourceInnerClass {
        // Instance field for variable call
        private int innerField = 2; // numbers:2

        // Target method: instance, Object return, strictfp access, in source_inner
        @ProcessAnnotation // has_annotation feature
        strictfp Object processData(TargetClass.TargetInnerClass param) {
            // Empty statement
            ; // empty statement

            // Synchronized statement
            Object lock = new Object();
            synchronized (lock) {
                // Variable call to target parameter
                String targetVar = param.getInnerValue();
                if (targetVar.isEmpty()) {
                    // Throw statement
                    throw new IllegalStateException("Target value cannot be empty"); // throw statement
                }
                param.setInnerValue(targetVar + "_processed");
            }

            // Instance code blocks (pos: instance code blocks)
            {
                // Instance method (public modifier, 2/sub_class/instance/this.methodName(arguments))
                int baseVal = this.thisMethodCall(param, innerField); // numbers:2, this.methodName(arguments)
                return baseVal + "_object_result"; // No new exception thrown (no_new_exception)
            }
        }

        // Instance method for method_feature (public, 2/sub_class/instance/this.methodName(arguments))
        public int thisMethodCall(TargetClass.TargetInnerClass param, int multiplier) {
            // Local inner class (fulfills source_class feature: local inner class)
            class LocalInnerClass {
                int calculate(String val) {
                    return val.length() * multiplier; // numbers:2
                }
            }
            LocalInnerClass localInner = new LocalInnerClass();
            // Variable call to target parameter
            return localInner.calculate(param.getInnerValue());
        }
    }
}

// Target class: final modifier, member inner class (target_feature)
final class TargetClass implements PermitsInterface {
    private String targetValue;

    // Member inner class (fulfills target_feature: member inner class)
    public class TargetInnerClass {
        private String innerValue = TargetClass.this.targetValue + "_inner";

        public String getInnerValue() { return innerValue; }
        public void setInnerValue(String val) { this.innerValue = val; }
    }

    public TargetClass(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getTargetValue() { return targetValue; }
    public void setTargetValue(String targetValue) { this.targetValue = targetValue; }
}