package refactoring.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Javadoc for TargetEnum (target_feature: javadoc)
 * Enum with local inner class feature
 */
// Target enum: default modifier, javadoc + local inner class features
enum TargetEnum {
    VALUE1("one"), VALUE2("two"), VALUE3("three");

    private final String value;
    int counter = 3; // For obj.field target_feature (3)

    TargetEnum(String value) {
        this.value = value;
    }

    // Local inner class (target_feature)
    public List<String> processData() {
        class TargetLocalInner {
            int process(TargetEnum enumVal) {
                return enumVal.counter * 1; // 1 for numeric feature
            }
        }
        return new ArrayList<>(List.of(this.value));
    }

    public String getValue() {
        return value;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

// Others class for call_method
class OthersClass {
    // Private instance method with super.methodName(arguments) (call_method features)
    private String callMethod(TargetEnum target) {
        try {
            // Exception throwing statements pos
            if (target == null) throw new IllegalArgumentException();
            // super.methodName(arguments)
            return super.toString() + target.getValue();
        } catch (IllegalArgumentException e) {
            return "default";
        }
    }
}

// Source enum: strictfp modifier, same package, local inner + anonymous inner classes
strictfp enum SourceEnum {
    INSTANCE;

    protected int outerProtectedField = 1; // For access_outer_protected

    // Member inner class (method_position: source_inner)
    public class SourceInnerClass {
        // Instance method to be refactored (all specified features)
        @SuppressWarnings("unchecked") // has_annotation
        public List<String> refactorMethod(TargetEnum targetParam) { // per_condition: target parameter
            // Uses outer this
            SourceEnum outerThis = SourceEnum.this;
            // Access outer protected field
            int protectedVal = outerThis.outerProtectedField;

            // Type declaration statement
            List<String> result;
            // Raw type usage
            ArrayList rawList = new ArrayList();
            result = new ArrayList<>();

            // This(arguments)
            class ThisConstructorClass {
                ThisConstructorClass(int val) {
                    this(val, "default"); // this(arguments)
                }
                ThisConstructorClass(int val, String str) {
                    rawList.add(str + val);
                }
            }
            new ThisConstructorClass(1);

            // Do statement
            int i = 0;
            do {
                // Expression statement
                targetParam.setCounter(i + 3);
                i++;
            } while (i < 3);

            // Switch case
            switch (targetParam.getValue()) {
                case "one":
                    // EnhancedForStatement: private, same_package_target pos, obj.field + 3
                    private void enhancedForLogic(TargetEnum target) {
                        for (String s : target.processData()) {
                            target.counter = 3; // obj.field + 3 (target_feature)
                            result.add(s);
                        }
                    }
                    enhancedForLogic(targetParam);
                    break; // break statement
                default:
                    result.add(targetParam.getValue());
                    break;
            }

            // TypeMethodReference (numbers:1, protected modifier, exp:TypeMethodReference)
            protected Function<TargetEnum, String> methodRef = TargetEnum::getValue; // TypeMethodReference
            result.add(methodRef.apply(targetParam) + 1); // 1 (numbers feature)

            // Generic method: private, array initialization pos, 1/source/generic/ClassName.methodName
            private <T> int genericMethod(T[] arr) {
                arr[0] = (T) targetParam; // source feature
                return 1; // 1 (method_feature)
            }
            // Array initialization pos for generic method call
            TargetEnum[] enumArr = new TargetEnum[1];
            int genericResult = SourceEnum.INSTANCE.genericMethod(enumArr); // ClassName.methodName(arguments)

            // Requires try-catch
            try {
                // Used by reflection
                Method targetMethod = TargetEnum.class.getMethod("processData");
                result.addAll((List<String>) targetMethod.invoke(targetParam));
                
                // Call_method invocation (others_class, private, instance, super.methodName)
                OthersClass others = new OthersClass();
                Method callMethod = OthersClass.class.getDeclaredMethod("callMethod", TargetEnum.class);
                callMethod.setAccessible(true);
                String callResult = (String) callMethod.invoke(others, targetParam);
                result.add(callResult);
            } catch (Exception e) {
                // Requires try-catch (no new exception)
                result.add("error:" + e.getMessage());
            }

            // Anonymous inner class (source feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    result.add("anonymous:" + protectedVal);
                }
            };
            anonymous.run();

            // Local inner class (source feature)
            class SourceLocalInner {
                void updateResult() {
                    result.addAll(rawList);
                }
            }
            new SourceLocalInner().updateResult();

            return result;
        }
    }
}