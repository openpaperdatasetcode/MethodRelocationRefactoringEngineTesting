import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Super class for source_class extends feature
class SourceSuperClass<U> {
    protected String superMethod(String arg, int num) {
        return arg + "_super_" + num;
    }
}

// Others class for call_method (others_class type)
class OthersClass {
    // Call method (default modifier, static, new ClassName().method(), return int)
    public static int callMethod(String val) {
        return new OthersClass().helper(val);
    }

    private int helper(String val) {
        return val.length();
    }
}

// Source generic class (strictfp modifier, same package, extends feature)
strictfp class SourceClass<T extends CharSequence> extends SourceSuperClass<T> {
    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String data) {
        // Instance code block for call_method position
        {
            // Call method in instance code blocks (pos: instance code blocks, others_class, static, new ClassName().method())
            int len = OthersClass.callMethod(data); // new ClassName().method() via static wrapper
        }

        // Recursion method (protected modifier, 1, source, recursion, superTypeReference.methodName(arguments))
        protected List<String> recursiveMethod(int count, TargetClass<T> param) {
            List<String> result = new ArrayList<>();
            // superTypeReference.methodName(arguments)
            String superVal = new SourceSuperClass<T>().superMethod(param.getValue().toString(), count);
            
            if (count <= 0) {
                result.add(superVal);
                return result;
            }
            // Recursion call
            result.addAll(this.recursiveMethod(count - 1, param));
            result.add(superVal + "_recur_" + count);
            return result;
        }

        // Method to be refactored (normal, TargetClass return, public access)
        public <U> TargetClass<U> targetMethod(TargetClass<U> param) { // per_condition: target parameter
            // Type declaration statement
            List<String> recursionResult;
            int switchKey;
            String targetVal;

            // Switch statement (pos: switch for recursion method call)
            switchKey = param.getValue() == null ? 0 : param.getValue().toString().length();
            switch (switchKey) {
                case 1:
                    // Recursion method call in switch (1, source, recursion, superTypeReference.methodName(arguments))
                    recursionResult = recursiveMethod(1, (TargetClass<T>) param); // 1 (method_feature)
                    break;
                default:
                    recursionResult = recursiveMethod(0, (TargetClass<T>) param);
                    break;
            }

            // Variable call
            targetVal = param.getValue() + "_processed_" + recursionResult.size();
            param.setValue((U) targetVal);

            // Depends on inner class
            HelperInnerClass<U> helper = new HelperInnerClass<>();
            param = helper.updateTarget(param);

            // Used by reflection
            try {
                Method method = InnerSourceRecord.class.getDeclaredMethod("targetMethod", TargetClass.class);
                method.setAccessible(true);
                param = (TargetClass<U>) method.invoke(this, param);
            } catch (Exception e) {
                param.setValue((U) ("reflection_error_" + e.getMessage()));
            }

            // No new exception
            return param;
        }

        // Helper inner class for depends_on_inner_class feature
        class HelperInnerClass<V> {
            TargetClass<V> updateTarget(TargetClass<V> target) {
                target.setValue((V) (target.getValue() + "_inner_helper"));
                return target;
            }
        }
    }
}

// Target generic class (default modifier, empty target_feature list)
class TargetClass<T> {
    private T value;

    // Constructor
    public TargetClass() {}
    public TargetClass(T value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
}