import java.util.function.Function;

// Super class for SuperMethodInvocation feature
class SourceSuperClass {
    protected String superMethod(String arg) {
        return arg + "_super_invocation_5841";
    }
}

// Source record class (protected modifier, same package, anonymous inner class, member inner class)
protected record SourceRecord(String data) extends SourceSuperClass {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "SOURCE_PROTECTED_FIELD_5841";
    // per_condition: source contains the field of the target
    private final PublicTargetRecord targetField = new PublicTargetRecord("init_value_5841");

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner execution: " + outerProtectedField);
        }
    };

    // Member inner class (source_class feature)
    class MemberInnerClass {
        int processTarget(PublicTargetRecord param) {
            // access_outer_protected feature
            String combined = param.value() + "_" + outerProtectedField;
            return combined.length();
        }
    }

    // Call method 1 (overloading feature)
    private String callMethod(String val) {
        return val + "_overload1";
    }

    // Call method 2 (overloading feature)
    private String callMethod(int num) {
        return String.valueOf(num) + "_overload2";
    }

    // Method to be refactored (normal, base type return, protected access)
    // method types parameter is:keywords (using Java keywords as param name with backtick escape)
    protected int targetMethod(PublicTargetRecord `this`) { // per_condition: target parameter, keywords as param
        // ReturnStatement (public modifier, this.field, 3, pos: source)
        public int returnBlock() {
            `this`.customField = 3; // this.field + 3 (target_feature)
            return `this`.customField; // ReturnStatement
        }

        // numbers:1, modifier:private, exp:SuperMethodInvocation
        private String superInvocation() {
            int num = 1; // numbers:1
            return super.superMethod(`this`.value() + "_" + num); // SuperMethodInvocation
        }

        // Call method in array initialization (pos: array initialization, overloading, ClassName::methodName)
        Function<Object, String>[] funcArray = new Function[] {
            SourceRecord::callMethod, // ClassName::methodName
            (n) -> callMethod((int) n) // overloading call
        };
        String callResult = funcArray[0].apply(`this`.value()) + "_" + funcArray[1].apply(1);

        // Variable call (target static nested class)
        PublicTargetRecord.StaticNestedClass staticObj = new PublicTargetRecord.StaticNestedClass();
        String staticVal = staticObj.getStaticVal();

        // Anonymous inner class trigger
        anonymousInner.run();

        // Core business logic
        int baseResult = new MemberInnerClass().processTarget(`this`);
        baseResult += returnBlock() + superInvocation().length() + staticVal.length();

        // return statement
        return baseResult;
    }
}

// Target record class (public modifier, static nested class target_feature)
public record PublicTargetRecord(String value) {
    // this.field for ReturnStatement feature
    public int customField;

    // Static nested class (target_feature)
    public static class StaticNestedClass {
        private String staticVal = "TARGET_STATIC_VAL_5841";

        public String getStaticVal() {
            return staticVal;
        }
    }

    // Variable call: constructor with validation
    public PublicTargetRecord {
        if (value == null) {
            value = "default_target_value_5841";
        }
    }
}