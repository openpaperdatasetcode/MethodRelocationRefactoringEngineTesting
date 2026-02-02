import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Source class: public normal class, same package, member inner/anonymous inner class
public class SourceClass {
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass targetField = new TargetClass();

    // Member inner class (fulfills source_class feature)
    class SourceMemberInner {
        String getInnerValue() {
            return "inner_value";
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Target method: instance, List<String> return, default access, in source class
    List<String> processData(TargetClass param) {
        // Constructor invocation
        TargetClass newTargetInstance = new TargetClass();
        
        // Variable call to target parameter
        String targetVar = param.targetField;
        
        // Used by reflection
        try {
            Method method = param.getClass().getMethod("getTargetField");
            String reflectedVal = (String) method.invoke(param);
        } catch (Exception e) {
            // No new exception thrown (no_new_exception)
            System.out.println("Reflection error: " + e.getMessage());
        }

        // Prepare return value
        List<String> result = new ArrayList<>();
        result.add(targetVar);
        result.add(newTargetInstance.targetField);
        return result;
    }
}

// Target class: non-sealed modifier, anonymous inner class (target_feature)
non-sealed class TargetClass {
    String targetField = "target_value";

    // Anonymous inner class (fulfills target_feature)
    Function<String, String> targetAnonymousInner = new Function<>() {
        @Override
        public String apply(String s) {
            return s.toUpperCase();
        }
    };

    // Getter for reflection access
    public String getTargetField() {
        return targetField;
    }

    // Call method: final modifier, instance, outerInstance.new InnerClass().methodName() in functional interface
    final String invokeSourceMethod() {
        SourceClass source = new SourceClass();
        // Functional interface position (pos: functional interfaces)
        Function<SourceClass, List<String>> func = (src) -> {
            // outerInstance.new InnerClass().methodName() (target_feature)
            SourceClass.SourceMemberInner inner = src.new SourceMemberInner();
            String innerVal = inner.getInnerValue();
            
            // Call target method
            List<String> result = src.processData(source.targetField);
            result.add(innerVal);
            return result;
        };

        List<String> resultList = func.apply(source);
        return String.join(",", resultList); // String return type
    }
}