import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Others class for call_method (others_class type)
class OthersClass {
    protected void superMethod(String arg) {
        System.out.println("super_method_" + arg);
    }

    // Call method (final modifier, static, super.methodName(arguments), return void)
    public final static void callMethod(String val) {
        new OthersClass() {
            @Override
            protected void superMethod(String arg) {
                super.superMethod(arg); // super.methodName(arguments)
            }
        }.superMethod(val);
    }
}

// Source normal class (strictfp modifier, same package, static nested class, local inner class)
strictfp class SourceClass {
    // per_condition: source contains the field of the target
    private final PrivateTargetClass targetField = new PrivateTargetClass();
    // this.field for ContinueStatement feature
    private String thisField = "source_this_field_5816";

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        // Overloading method 1 (overload_exist feature)
        public static int accessorMethod(String s) {
            return s.length();
        }

        // Overloading method 2 (overload_exist feature)
        public static int accessorMethod(String s, int num) {
            return s.length() + num;
        }
    }

    // Method to be refactored (instance, List<String> return, strictfp access, position: source)
    public strictfp List<String> targetMethod(PrivateTargetClass param) { // per_condition: target parameter
        List<String> result = new ArrayList<>();

        // ContinueStatement (private modifier, this.field, 3, pos: diff_package_others)
        private void continueBlock() {
            int num = 3; // target_feature: 3
            for (int i = 0; i < num; i++) {
                if (i == 1) {
                    this.thisField = thisField + "_" + i; // this.field
                    continue; // ContinueStatement
                }
                result.add(thisField + "_continue_" + i);
            }
        }
        continueBlock();

        // Accessor method call in do-while (pos: do-while, 1, source, accessor, instanceReference::methodName)
        int count = 0;
        int accessorResult = 0;
        do {
            // instanceReference::methodName (accessor feature)
            Function<String, Integer> accessorRef = StaticNestedSourceClass::accessorMethod;
            accessorResult = accessorRef.apply(param.getValue() + "_1"); // numbers:1 (accessor)
            count++;
        } while (count < 1);

        // numbers:1, modifier:protected, exp:CreationReference
        protected Function<String, PrivateTargetClass> creationRef = PrivateTargetClass::new; // CreationReference
        PrivateTargetClass newTarget = creationRef.apply(param.getValue() + "_1"); // numbers:1

        // While statement
        int whileCount = 0;
        while (whileCount < 3) {
            // Variable call
            String targetVal = newTarget.getValue() + "_" + accessorResult;
            newTarget.setValue(targetVal);
            result.add(targetVal);

            // Call method in do-while (pos: do-while, others_class, final, static, super.methodName(arguments))
            int callCount = 0;
            do {
                OthersClass.callMethod(targetVal);
                callCount++;
            } while (callCount < 1);

            whileCount++;
        }

        // Overload exist (call overloaded accessor method)
        int overloadResult = StaticNestedSourceClass.accessorMethod(newTarget.getValue(), 1);
        result.add(String.valueOf(overloadResult));

        // Local inner class (source_class feature)
        class LocalInnerClass {
            void updateResult(List<String> list) {
                list.add(targetField.getValue() + "_local_inner");
            }
        }
        new LocalInnerClass().updateResult(result);

        // No new exception
        return result;
    }
}

// Target normal class (private modifier, static nested class target_feature)
private class PrivateTargetClass {
    private String value = "target_value_5816";

    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        public static String format(String s) {
            return s.toUpperCase();
        }
    }

    // Constructor for CreationReference
    public PrivateTargetClass() {}
    public PrivateTargetClass(String value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}