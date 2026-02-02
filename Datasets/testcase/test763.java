package com.refactoring.movemethod;

import java.util.Arrays;
import java.util.function.Function;

// Others class for method_feature: others_class
class OthersClass {
    public static int processInt(int val) {
        return val * 2; // method_feature: "2"
    }
}

// Source generic class: public modifier, same package, type parameter + two static nested classes
public class SourceClass<T extends Number> {
    // per_condition: source contains field of target
    private TargetClass<T> targetField = new TargetClassImpl<>();

    // Protected outer field for access_outer_protected feature
    protected T outerProtectedField = (T) Integer.valueOf(6122);

    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "static_6122";

    // First static nested class (source feature)
    public static class FirstStaticNested {
        private String nestedField1 = "nested1_6122";
    }

    // Second static nested class (source feature)
    public static class SecondStaticNested {
        private int nestedField2 = 2; // method_feature: "2"
    }

    // Instance method for access_instance_method feature
    private T instanceMethod(T val) {
        return (T) Integer.valueOf(val.intValue() + 2); // method_feature: "2"
    }

    // Method to be refactored: instance, Object return, default access, source position
    Object methodToMove() {
        // variable call feature
        FirstStaticNested staticNested = new FirstStaticNested();
        String varCall = staticNested.nestedField1 + STATIC_FIELD; // depends_on_static_field

        // access_outer_protected feature
        T protectedVal = this.outerProtectedField;

        // access_instance_method feature
        T instanceVal = this.instanceMethod(protectedVal);

        // override_violation feature (invalid override attempt)
        class OverrideViolationClass extends TargetClass<T> {
            // Intentionally violate override (wrong return type)
            @Override
            public String getNestedValue() { // Parent returns int, this returns String - violation
                return STATIC_FIELD;
            }
        }

        // Normal method (type: normal, modifier: public, pos: array initialization, return base type)
        public int nestedNormalMethod() {
            // array initialization position
            Integer[] arr = new Integer[]{2, 4}; // method_feature: "2"
            // method_feature: ClassName::methodName (method reference)
            Function<Integer, Integer> processor = OthersClass::processInt; // others_class
            // method_feature: normal
            return Arrays.stream(arr).map(processor).sum();
        }
        int baseVal = nestedNormalMethod(); // return_type: base type (int)

        // no_new_exception (no explicit new Exception instantiation)
        return targetField.getStaticNested().nestedVal + varCall + instanceVal + baseVal;
    }
}

// Target generic class: sealed modifier, static nested class (target_feature)
sealed class TargetClass<T> permits TargetClassImpl {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedVal = 2; // method_feature: "2"
    }

    public abstract int getNestedValue();

    public TargetStaticNested getStaticNested() {
        return new TargetStaticNested();
    }
}

// Target class implementation for sealed permit
final class TargetClassImpl<T> extends TargetClass<T> {
    @Override
    public int getNestedValue() {
        return new TargetStaticNested().nestedVal;
    }
}