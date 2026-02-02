import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.io.IOException;

// Others class for call_method feature
class OthersClass {
    protected static List<String> staticMethod(String arg) {
        // superTypeReference.methodName(arguments)
        return new SuperOthersClass().superMethod(arg);
    }

    // Super class for superTypeReference
    static class SuperOthersClass {
        List<String> superMethod(String arg) {
            List<String> list = new ArrayList<>();
            list.add(arg + "_super_others");
            return list;
        }
    }
}

// Source non-sealed abstract class (same package, static nested class, local inner class)
non-sealed abstract class SourceClass {
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "SOURCE_STATIC_5850";
    // per_condition: source contains the field of the target
    private final PublicTargetClass targetField = new ConcreteTargetClass();

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        static String format(String s) {
            return s.toUpperCase() + "_" + STATIC_FIELD;
        }
    }

    // Instance method (public modifier, 1, inner_class, instance, super.methodName(arguments))
    public Object instanceMethod(String val, int num) {
        // super.methodName(arguments) (call Object super method)
        super.toString();
        num = 1; // method_feature: 1

        // Local inner class (source_class feature)
        class LocalInnerClass {
            String process(String s) {
                return s + "_local_inner_" + num;
            }
        }
        return new LocalInnerClass().process(val);
    }

    // Overloading method 1 (base overload)
    void processTarget(PublicTargetClass param) throws IOException { // requires_throws
        processTarget(param, STATIC_FIELD); // overload_exist (call overloaded method)
    }

    // Method to be refactored (overloading, void return, default access)
    void processTarget(PublicTargetClass param, String suffix) throws IOException { // requires_throws, per_condition: target parameter
        // this.var = var feature
        String localVar = param.getValue() + "_" + suffix;
        this.targetField.setValue(localVar); // this.var = var

        // Instance method call in functional interfaces (pos: functional interfaces)
        Function<String, Object> func = this::instanceMethod; // functional interface
        Object instanceResult = func.apply(localVar + "_1"); // 1 (method_feature)

        // Call method in switch (pos: switch, others_class, protected, static, superTypeReference.methodName(arguments))
        switch (localVar.length() % 3) {
            case 1:
                List<String> callResult = OthersClass.staticMethod(localVar); // static + superTypeReference
                param.setStaticNestedVal(callResult.get(0));
                break;
            default:
                param.setStaticNestedVal(StaticNestedSourceClass.format(localVar));
                break;
        }

        // Variable call (target static nested class)
        PublicTargetClass.StaticNestedTargetClass staticObj = new PublicTargetClass.StaticNestedTargetClass();
        String staticVal = staticObj.getStaticVal();
        
        // depends_on_static_field feature
        param.setValue(param.getValue() + "_" + staticVal + "_" + STATIC_FIELD);

        // Validate and throw if needed (requires_throws)
        if (param.getValue().isEmpty()) {
            throw new IOException("Empty target value");
        }
    }

    // Abstract method (required for abstract source class)
    public abstract void abstractSourceMethod();
}

// Target public abstract class (static nested class target_feature)
public abstract class PublicTargetClass {
    private String value;
    private String staticNestedVal;

    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        public static final String STATIC_VAL = "TARGET_STATIC_CONST_5850";

        public String getStaticVal() {
            return STATIC_VAL;
        }
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStaticNestedVal() {
        return staticNestedVal;
    }

    public void setStaticNestedVal(String staticNestedVal) {
        this.staticNestedVal = staticNestedVal;
    }

    // Abstract method (required for abstract target class)
    public abstract void abstractTargetMethod();
}

// Concrete implementation of target abstract class
class ConcreteTargetClass extends PublicTargetClass {
    public ConcreteTargetClass() {
        this.setValue("default_target_value_5850");
    }

    @Override
    public void abstractTargetMethod() {}
}