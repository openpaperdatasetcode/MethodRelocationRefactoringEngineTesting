import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source generic class: default modifier, same package, extends/anonymous inner/member inner class
class SourceClass<T> extends BaseClass<T> { // extends feature (fulfills source_class feature)
    // Member inner class (fulfills source_class feature)
    class SourceInnerClass {
        // Overloaded method 1 (overloading feature)
        public Object processData(TargetClass<String> param) {
            return processData(param, 1); // numbers:1
        }

        // Target method: overloading, Object return, public access, in source_inner
        public Object processData(TargetClass<String> param, int flag) {
            // Expression statement
            String targetPrefix = "processed_";
            
            // Variable call to target parameter (fulfills per_condition)
            String targetVar = param.getTargetValue();
            
            // Synchronized statement
            Object lock = new Object();
            synchronized (lock) {
                targetPrefix += targetVar + "_" + flag;
            }

            // Break statement
            loop:
            for (int i = 0; i < 3; i++) {
                if (i == 2) break loop;
                targetPrefix += "_" + i;
            }

            // Overriding feature (private modifier, 1=numbers, target, new ClassName().method() in array initialization)
            // Array initialization position (pos: array initialization)
            List<String>[] overrideArr = new List[]{
                new OverrideClass().overrideMethod(param) // new ClassName().method()
            };

            // Used by reflection
            try {
                Method method = param.getClass().getMethod("getTargetValue");
                String reflectedVal = (String) method.invoke(param);
                targetPrefix += "_reflected_" + reflectedVal;
            } catch (Exception e) {
                // No new exception thrown (no_new_exception)
                targetPrefix += "_reflection_error";
            }

            return targetPrefix + overrideArr[0].get(0);
        }

        // Inner class for overriding feature
        private class OverrideClass extends TargetClass<String> {
            @Override
            public List<String> overrideMethod(TargetClass<String> param) { // overriding, return_type: List<String>
                List<String> result = new ArrayList<>();
                result.add(param.getTargetValue() + "_overridden_1"); // numbers:1, target type
                return result;
            }
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    Consumer<T> anonymousInner = new Consumer<>() {
        @Override
        public void accept(T val) {
            System.out.println("Anonymous inner class: " + val);
        }
    };
}

// Base class for extends feature
class BaseClass<T> {
    public T getBaseValue() {
        return null;
    }
}

// Subclass for call_method (type: sub_class)
class SourceSubClass extends SourceClass<String> {
    // Call method: default modifier, instance, ClassName::methodName in exception handling
    void invokeSourceMethod() {
        SourceClass<String> source = new SourceClass<>();
        SourceClass<String>.SourceInnerClass inner = source.new SourceInnerClass();
        TargetClass<String> target = new TargetClass<>("target_value");

        // Exception handling statements position (pos: exception handling statements)
        try {
            // ClassName::methodName (target_feature: instance + method reference)
            Consumer<TargetClass<String>> consumer = inner::processData; // ClassName::methodName (instance method reference)
            consumer.accept(target); // instance feature
        } catch (Exception e) {
            // Exception handling (pos: exception handling statements)
            System.out.println("Call method error: " + e.getMessage());
        }
    }
}

// Target generic class: no modifier, local inner class (target_feature)
class TargetClass<U> {
    private U targetValue;

    public TargetClass(U targetValue) {
        this.targetValue = targetValue;
    }

    // Local inner class (fulfills target_feature)
    public void outerMethod() {
        class TargetLocalInner {
            U getLocalValue() {
                return targetValue;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        this.targetValue = localInner.getLocalValue();
    }

    // Getter for variable call
    public U getTargetValue() {
        return targetValue;
    }

    // Method for overriding feature
    public List<String> overrideMethod(TargetClass<U> param) {
        List<String> result = new ArrayList<>();
        result.add(param.getTargetValue().toString());
        return result;
    }
}