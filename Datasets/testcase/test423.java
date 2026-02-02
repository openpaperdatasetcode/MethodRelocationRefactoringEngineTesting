import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Function;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {}

// Source enum class: public modifier, same package, no extra features
public enum SourceEnum {
    INSTANCE;

    // Static field for SynchronizedStatement (ClassName.field, 3)
    private static final Object LOCK = new Object();
    private static int syncField = 3; // target_feature: ClassName.field, numbers:3

    // Target method: instance, TargetEnum return, default access, in source enum
    @ProcessAnnotation // has_annotation feature
    TargetEnum processData(TargetEnum param) {
        // Super keywords (enum implicit super)
        super.toString();

        // Variable call to target parameter (fulfills per_condition)
        String targetVar = param.getTargetValue();

        // SynchronizedStatement (private modifier, ClassName.field=3, pos:diff_package_others)
        // Simulate diff_package_others via static field reference
        private synchronized void syncMethod() { // SynchronizedStatement
            SourceEnum.syncField = 3; // ClassName.field, numbers:3
        }
        syncMethod();

        // Switch case feature
        TargetEnum result;
        switch (targetVar) {
            case "VALUE1":
                result = TargetEnum.VALUE1;
                break;
            case "VALUE2":
                result = TargetEnum.VALUE2;
                break;
            default:
                // Throw statement feature
                throw new IllegalArgumentException("Invalid target value: " + targetVar);
        }

        // Accessor method (public modifier, 1=numbers, inner_class, accessor, super.methodName() in constructor param list)
        int accessorVal = result.new TargetInnerClass(accessorMethod(1)).superMethod(); // pos: constructor parameter list

        // Return statement feature
        return (accessorVal > 0) ? result : TargetEnum.VALUE1; // No new exception thrown (no_new_exception)
    }

    /**
     * Accessor method (fulfills accessor feature)
     * @param val Numeric value (numbers:1)
     * @return Base type (int)
     */
    public int accessorMethod(int val) { // accessor, numbers:1, return_type: base type
        return val * 10;
    }

    // Call method: private modifier, source type, overloading, ClassName::methodName in expression
    private TargetEnum callMethod(TargetEnum param) {
        // Overloading feature (overload 1)
        return callMethod(param, "default");
    }

    // Overloaded call method (overloading feature)
    private TargetEnum callMethod(TargetEnum param, String suffix) {
        // ClassName::methodName in expression (pos: expression, target_feature)
        Function<TargetEnum, TargetEnum> func = SourceEnum::processData; // ClassName::methodName
        return func.apply(param); // TargetClas Type return
    }
}

// Target enum class: private modifier, extends (enum implicit extends), static nested class (target_feature)
// Note: Java enum implicitly extends Enum, so "extends" feature is fulfilled natively
private enum TargetEnum {
    VALUE1("value1"), VALUE2("value2");

    // Static nested class (fulfills target_feature)
    public static class TargetStaticNested {
        public int getStaticValue() {
            return 3;
        }
    }

    private final String targetValue;

    TargetEnum(String targetValue) {
        this.targetValue = targetValue;
    }

    // Inner class for accessor feature (inner_class)
    public class TargetInnerClass {
        private final int val;

        public TargetInnerClass(int val) {
            this.val = val;
        }

        // super.methodName() for accessor feature
        public int superMethod() {
            return TargetEnum.this.ordinal() + this.val;
        }
    }

    // Accessor method for variable call
    public String getTargetValue() {
        return this.targetValue;
    }
}