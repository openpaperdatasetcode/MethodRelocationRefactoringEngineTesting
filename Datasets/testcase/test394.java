package refactoring.test;

import java.lang.reflect.Method;
import java.util.function.Consumer;

// Sealed interface for source enum's permits feature
sealed interface SourceEnumPermit permits SourceEnum {}

// Source enum: non-sealed modifier, same package, permits feature
non-sealed enum SourceEnum implements SourceEnumPermit { // permits feature
    INSTANCE;

    // Instance method to be refactored (all specified features)
    public int refactorMethod(TargetEnum.TargetInner targetParam) { // per_condition + instance type + base type return
        // Type declaration statement
        int result = 0;
        TargetEnum.TargetStaticNested staticNested;

        // Variable call (target inner class field)
        targetParam.counter = 1;
        targetParam.value = "refactor_value";

        // DoStatement: private modifier, source pos, super.field + 1
        private void doLogic() {
            int i = 0;
            do {
                targetParam.super.field = 1; // super.field + 1 (target_feature)
                result += i;
                i++;
                if (i == 1) break; // break statement
            } while (i < 3);
        }
        doLogic();

        // For statement
        for (int j = 0; j < 1; j++) { // 1 for method_feature
            targetParam.counter += j;
            result += targetParam.counter;
        }

        // Super keywords usage
        SourceEnum.super.toString();

        // Accessor method: protected, functional interfaces pos, 1/target/accessor/ClassName.methodName()
        protected void accessorMethod(TargetEnum.TargetInner inner) {
            // ClassName.methodName(arguments) + accessor + target + 1
            TargetEnum.setInnerValue(inner, "accessor_1"); // 1 (method_feature)
        }
        // Functional interfaces pos for accessor method call
        Consumer<TargetEnum.TargetInner> func = this::accessorMethod;
        func.accept(targetParam);

        // Used by reflection
        try {
            Method getter = TargetEnum.TargetInner.class.getMethod("getCounter");
            result = (int) getter.invoke(targetParam); // accessor via reflection
        } catch (Exception e) {
            // No new exception
            result = -1;
        }

        // No new exception, return base type (int)
        return result;
    }
}

// Parent class for target inner class's super.field
class TargetInnerParent {
    protected int field = 0;
}

// Target enum: protected modifier, static nested class feature
protected enum TargetEnum {
    VALUE1("one"), VALUE2("two");

    private final String data;

    TargetEnum(String data) {
        this.data = data;
    }

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedValue = 1;
    }

    // Target inner class (target_inner)
    public class TargetInner extends TargetInnerParent { // for super.field
        int counter = 1;
        String value;

        // Accessor (getter) for used_by_reflection
        public int getCounter() {
            return counter;
        }

        // Accessor (setter) for accessor method feature
        public void setValue(String value) {
            this.value = value;
        }
    }

    // Static accessor method for ClassName.methodName(arguments)
    public static void setInnerValue(TargetInner inner, String value) {
        inner.setValue(value); // accessor feature
    }
}