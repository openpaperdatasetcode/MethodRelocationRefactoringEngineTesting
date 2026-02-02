import java.util.ArrayList;
import java.util.List;

// Source class: default modifier, same package, type parameter/static nested/anonymous inner class
class SourceClass<T> {
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass targetField = new TargetClass();

    // Static nested class (fulfills source_class feature)
    static class SourceStaticNested<T> {
        T getNestedValue(T val) {
            return val;
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class execution");
        }
    };

    // Overloaded method 1 (overload_exist feature)
    strictfp List<String> processData(TargetClass param) {
        return processData(param, "default");
    }

    // Target method: overloading, List<String> return, strictfp access, in source class
    strictfp List<String> processData(TargetClass param, String suffix) {
        // Labeled statement
        labeledLoop:
        for (int i = 0; i < 3; i++) {
            if (i == 2) break labeledLoop;
            
            // Variable call to target parameter
            String targetVar = param.targetValue + "_" + suffix + "_" + i;
            
            // No new exception thrown (no_new_exception)
            System.out.println(targetVar);
        }

        // Prepare return value
        List<String> result = new ArrayList<>();
        result.add(param.targetValue);
        result.add(suffix);
        return result;
    }

    // Additional overloaded method to confirm overload_exist
    strictfp List<String> processData(TargetClass param, int count) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(param.targetValue + "_" + i);
        }
        return result;
    }
}

// Target class: default modifier, static nested class (target_feature)
class TargetClass {
    String targetValue = "target_data";

    // Static nested class (fulfills target_feature)
    static class TargetStaticNested<T> {
        T processValue(T val) {
            return val;
        }
    }
}