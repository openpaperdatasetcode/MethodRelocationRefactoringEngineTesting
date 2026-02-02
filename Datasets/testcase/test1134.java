import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Annotation for has_annotation and call_method position
@interface MethodAnno {
    String value();
}

// Sealed interface for source_class permits feature
sealed interface SourceSealedInterface permits SourceClass.MemberInnerClass1, SourceClass.MemberInnerClass2 {}

// Super class for target_class extends feature
class TargetSuperClass {
    protected int superField = 5828;
}

// Source normal class (protected modifier, same package, permits, two member inner classes)
protected class SourceClass {
    // per_condition: source contains the field of the target
    private final FinalTargetClass targetField = new FinalTargetClass();

    // First member inner class (source_class feature, permits)
    class MemberInnerClass1 implements SourceSealedInterface {
        int helper(int val) {
            return val * 2;
        }
    }

    // Second member inner class (source_class feature, permits)
    class MemberInnerClass2 implements SourceSealedInterface {
        int helper(int val) {
            return val + 10;
        }
    }

    /**
     * Method with has_annotation feature
     * @param params Target class varargs parameter
     * @return int (base type)
     */
    @MethodAnno(value = String.valueOf(new FinalTargetClass().callMethod(1, (s) -> s.length()))) // call_method in annotation attribute values
    // Method to be refactored (varargs, base type return, default access, position: source)
    int targetMethod(FinalTargetClass... params) { // per_condition: target parameter
        // Type declaration statement
        int result = 0;
        String targetFieldVal;
        List<? extends FinalTargetClass> boundedList; // with_bounds

        // with_bounds feature
        boundedList = new ArrayList<>(List.of(params));

        // BreakStatement (private modifier, obj.field, 1, pos: source)
        private void breakBlock() {
            for (FinalTargetClass param : params) {
                targetFieldVal = param.value; // obj.field
                int num = 1; // target_feature: 1
                if (param.getValue() == num) {
                    result = num;
                    break; // BreakStatement
                }
                result += param.getSuperField() + num;
            }
        }
        breakBlock();

        // If statement
        if (boundedList.isEmpty()) {
            result = targetField.getSuperField();
        } else {
            // Expression statement
            targetFieldVal = targetField.value + "_processed"; // expression statement
            targetField.setValue(targetFieldVal);

            // Variable call
            for (FinalTargetClass param : boundedList) {
                param.setValue(param.getValue() + "_" + result);
                result += new MemberInnerClass1().helper(param.getSuperField());
            }
        }

        // Return statement
        return result;
    }
}

// Target normal class (final modifier, extends, local inner class target_feature)
final class FinalTargetClass extends TargetSuperClass { // target_feature: extends
    // obj.field for BreakStatement feature
    public String value = "target_value_5828";
    private int intValue;

    // Local inner class (target_feature)
    public int getSuperField() {
        class TargetLocalInnerClass { // local inner class
            int getSuperVal() {
                return superField; // super class field from extends
            }
        }
        return new TargetLocalInnerClass().getSuperVal();
    }

    // Call method (target type, protected modifier, overloading, (parameters) -> methodBody, return int)
    protected int callMethod(int num, Function<String, Integer> func) {
        return func.apply(this.value + "_" + num);
    }

    // Overloading method (overloading feature)
    protected int callMethod(int num) {
        return num * superField;
    }

    // Variable call: getter/setter
    public int getValue() {
        return intValue;
    }

    public void setValue(String value) {
        this.value = value;
        this.intValue = value.length();
    }

    // No new exception
    public FinalTargetClass() {}
}