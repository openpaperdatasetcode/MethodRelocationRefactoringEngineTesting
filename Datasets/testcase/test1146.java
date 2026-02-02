import java.util.ArrayList;
import java.util.List;

// Abstract class for TypeLiteral expression (numbers:1, modifier:abstract)
abstract class TypeLiteralClass<T> {
    // TypeLiteral expression (numbers:1)
    protected final Class<T> type = (Class<T>) new ArrayList<String>().getClass();
    public abstract String process(T val, int num); // abstract modifier
}

// Source normal class (public modifier, same package, anonymous inner class, static nested class)
public class SourceClass {
    // per_condition: source contains the field of the target
    private final FinalTargetClass<String> targetField = new FinalTargetClass<>("init_value_5842");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        // Overload method 1 (overload_exist feature)
        public static String format(String s) {
            return s.toUpperCase() + "_static1";
        }

        // Overload method 2 (overload_exist feature)
        public static String format(String s, int num) {
            return s.toLowerCase() + "_static" + num;
        }
    }

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner process: " + targetField.value); // access_instance_field
        }
    };

    // Method to be refactored (instance, void return, default access)
    void targetMethod(FinalTargetClass<String>.InnerRecord param) { // per_condition: target parameter (target_inner_rec)
        // BreakStatement (private modifier, obj.field, 2, pos: diff_package_others)
        private void breakBlock() {
            FinalTargetClass<String> targetObj = new FinalTargetClass<>("break_target");
            String objField = targetObj.value; // obj.field
            int num = 2; // target_feature: 2

            for (int i = 0; i < 5; i++) {
                if (i == num) {
                    break; // BreakStatement
                }
                // Continue statement
                if (i == 1) {
                    continue; // continue statement
                }
                targetObj.innerClass.updateValue(objField + "_loop_" + i);
            }
        }

        // numbers:1, modifier:abstract, exp:TypeLiteral
        TypeLiteralClass<List<String>> typeLiteral = new TypeLiteralClass<>() {
            @Override
            public String process(List<String> val, int num) { // abstract modifier
                num = 1; // numbers:1
                return val.toString() + "_typeLiteral_" + num;
            }
        };

        // Execute break block
        breakBlock();

        // Variable call (target member inner class + inner record)
        String innerRecVal = param.value();
        targetField.innerClass.updateValue(innerRecVal + "_processed");
        String updatedVal = targetField.innerClass.getValue();

        // overload_exist (call overloaded static method)
        String overload1 = StaticNestedSourceClass.format(updatedVal);
        String overload2 = StaticNestedSourceClass.format(updatedVal, 1); // numbers:1

        // Access instance field
        targetField.value = overload1 + "_" + overload2; // access_instance_field

        // Trigger anonymous inner class
        anonymousInner.run();

        // No new exception
    }
}

// Target normal class (final modifier, type parameter, member inner class target_feature)
final class FinalTargetClass<T> {
    // obj.field for BreakStatement feature
    public T value;

    // Member inner class (target_feature)
    public class InnerClass {
        private T innerValue;

        public void updateValue(T value) {
            this.innerValue = value;
        }

        public T getValue() {
            return innerValue;
        }
    }

    // Inner record (target_inner_rec)
    public record InnerRecord(T value) {}

    // Type parameter feature (target_feature)
    public final InnerClass innerClass;

    // Constructor
    public FinalTargetClass(T value) {
        this.value = value;
        this.innerClass = new InnerClass();
        this.innerClass.updateValue(value);
    }

    // Variable call: helper method for inner record
    public InnerRecord getInnerRecord() {
        return new InnerRecord(this.value);
    }
}