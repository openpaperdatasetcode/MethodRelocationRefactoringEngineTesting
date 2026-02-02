import java.lang.reflect.Method;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Parent class for accessor method_feature: parent_class
class ParentAccessorClass<T> {
    public List<String> accessorMethod(T val, int num) {
        List<String> list = new ArrayList<>();
        list.add(val.toString() + "_parent_" + num);
        return list;
    }

    // Overloading method for overload_exist feature
    public List<String> accessorMethod(T val) {
        return accessorMethod(val, 0);
    }
}

// Source generic class (protected modifier, same package, type parameter, extends, two local inner classes)
protected class SourceClass<T extends CharSequence> extends ParentAccessorClass<T> {
    // per_condition: source contains the field of the target
    private final StrictfpTargetClass<T> targetField = new StrictfpTargetClass<>("init_value_5830");

    // Method to be refactored (varargs, TargetClass return, default access, position: source)
    StrictfpTargetClass<T> targetMethod(StrictfpTargetClass<T>... params) throws IOException { // requires_throws
        // Super constructor invocation (inherited from parent class)
        super();

        StrictfpTargetClass<T> resultTarget = new StrictfpTargetClass<>("result_5830"); // constructor invocation

        // SwitchStatement (private modifier, ClassName.field, 1, pos: diff_package_others)
        private void switchBlock() {
            String classField = StrictfpTargetClass.CLASS_FIELD; // ClassName.field
            int num = 1; // target_feature: 1
            
            for (StrictfpTargetClass<T> param : params) {
                switch (param.getValue().length()) {
                    case 1:
                        param.setValue((T) (classField + "_" + num));
                        break;
                    default:
                        param.setValue((T) (classField + "_default"));
                        break;
                }
            }
        }
        switchBlock();

        // Accessor method call in if/else conditions (pos: if/else conditions, 1, parent_class, accessor, new ClassName().method())
        for (StrictfpTargetClass<T> param : params) {
            List<String> accessorResult;
            if (param.getValue().isEmpty()) {
                // new ClassName().method() (accessor feature)
                accessorResult = new ParentAccessorClass<T>().accessorMethod(param.getValue(), 1); // 1 (method_feature)
            } else {
                // Overload exist feature (call overloaded accessor method)
                accessorResult = this.accessorMethod(param.getValue()); // overload_exist
            }

            // Variable call (target member inner class)
            param.getInnerClass().setInnerValue(accessorResult.toString());
            resultTarget.setValue((T) (resultTarget.getValue() + "_" + accessorResult.get(0)));
        }

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod", StrictfpTargetClass[].class);
            method.setAccessible(true);
            resultTarget = (StrictfpTargetClass<T>) method.invoke(this, (Object) params);
        } catch (Exception e) {
            throw new IOException("Reflection error", e); // requires_throws
        }

        // First local inner class (source_class feature)
        class LocalInnerClass1 {
            void updateTarget(StrictfpTargetClass<T> target) {
                target.setValue((T) (target.getValue() + "_local1"));
            }
        }
        new LocalInnerClass1().updateTarget(resultTarget);

        // Second local inner class (source_class feature)
        class LocalInnerClass2 {
            void validate(StrictfpTargetClass<T> target) throws IOException {
                if (target.getValue().isEmpty()) {
                    throw new IOException("Empty target value");
                }
            }
        }
        new LocalInnerClass2().validate(resultTarget);

        return resultTarget;
    }
}

// Target generic class (strictfp modifier, member inner class target_feature)
strictfp class StrictfpTargetClass<T> {
    // ClassName.field for SwitchStatement feature
    public static final String CLASS_FIELD = "target_class_field_5830";
    private T value;

    // Member inner class (target_feature)
    public class InnerClass {
        private String innerValue;

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Inner class instance for variable call
    private final InnerClass innerClass = new InnerClass();

    // Constructor for invocation
    public StrictfpTargetClass(T value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }
}