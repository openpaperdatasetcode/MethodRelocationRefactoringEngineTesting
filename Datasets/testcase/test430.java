import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {}

// Sealed interface with permits (simulate permits feature for normal class)
// Note: Java permits is for sealed classes/interfaces, implemented via sealed parent
sealed interface PermitsInterface permits SourceClass, TargetClass {}

// Source class: private modifier, same package, permits feature (implements sealed interface with permits)
private class SourceClass implements PermitsInterface { // permits feature via sealed interface

    // Target method: instance, Object return, protected access, in source class
    @ProcessAnnotation // has_annotation feature
    protected Object processData(TargetClass param) {
        // Fulfill per_condition: method contains target class parameter
        if (param == null) { // if statement
            return "default_value"; // no_new_exception
        }

        // Variable call to target parameter
        String targetVar = param.getTargetValue();
        int loopCount = 0;
        Object result = null;

        // While statement
        while (loopCount < 3) {
            // Switch case
            switch (targetVar.length()) {
                case 1:
                    result = targetVar + "_case1_" + loopCount;
                    break;
                case 2:
                    result = targetVar + "_case2_" + loopCount;
                    break;
                default:
                    result = targetVar + "_default_" + loopCount;
                    break;
            }
            loopCount++;
        }

        // No new exception thrown (no_new_exception)
        return result;
    }
}

// Target class: default modifier, member inner class (target_feature)
class TargetClass implements PermitsInterface {
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