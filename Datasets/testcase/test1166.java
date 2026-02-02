import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Others class for call_method feature
class OthersClass {
    protected List<String> baseMethod(String val) {
        List<String> list = new ArrayList<>();
        list.add(val + "_others_base");
        return list;
    }
}

// Subclass for call_method overriding feature
class SubOthersClass extends OthersClass {
    @Override
    protected List<String> baseMethod(String val) {
        // (parameters) -> methodBody (lambda in overriding method)
        Function<String, List<String>> lambda = (s) -> {
            List<String> list = new ArrayList<>();
            list.add(s + "_lambda_body_5860");
            return list;
        };
        return lambda.apply(val);
    }
}

// Source public normal class (same package, local inner class, static nested class)
public abstract class SourceClass {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "SOURCE_PROTECTED_FIELD_5860";
    // per_condition: source contains the field of the target
    private final PublicTargetClass<String> targetField = new PublicTargetClass<>("init_value_5860");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        static String format(String s) {
            return s.toUpperCase() + "_static_source";
        }
    }

    // ExpressionStatement (protected modifier, obj.field, 3, pos: same_package_target)
    protected void expressionBlock(PublicTargetClass<String> param) {
        String objField = param.value; // obj.field
        int num = 3; // target_feature: 3
        
        // Expression statement
        param.value = objField + "_expr_statement_" + num; // expression statement
    }

    // Accessor method (default modifier, 2, inner_class, accessor, new ClassName().method())
    List<String> accessorMethod(PublicTargetClass<String> param) {
        // Local inner class (inner_class feature)
        class AccessorInnerClass {
            List<String> getValues(PublicTargetClass<String> target) {
                int num = 2; // method_feature: 2
                List<String> list = new ArrayList<>();
                list.add(target.getValue() + "_accessor_" + num);
                return list;
            }
        }
        // new ClassName().method() (accessor feature)
        return new AccessorInnerClass().getValues(param);
    }

    // numbers:2, modifier:public, exp:ParenthesizedExpression
    public String parenthesizedExpr(PublicTargetClass<String> param) {
        int num = 2; // numbers:2
        return (param.getValue() + "_parenthesized_" + num); // ParenthesizedExpression
    }

    // Abstract method to be refactored (abstract, List<String> return, public access)
    public abstract List<String> processTarget(PublicTargetClass<String> param); // per_condition: target parameter

    // Concrete implementation (for compilation)
    public static SourceClass getInstance() {
        return new SourceClass() {
            @Override
            public List<String> processTarget(PublicTargetClass<String> param) {
                List<String> result = new ArrayList<>();

                // Requires try-catch feature
                try {
                    // ExpressionStatement execution (same_package_target position)
                    expressionBlock(param);
                    result.add(param.getValue());

                    // Accessor method call in if/else conditions (pos: if/else conditions)
                    if (param.getValue().length() > 0) {
                        result.addAll(accessorMethod(param)); // accessor feature
                    } else {
                        result.addAll(new AccessorInnerClass().getValues(param));
                    }

                    // ParenthesizedExpression execution
                    String parenthVal = parenthesizedExpr(param);
                    result.add(parenthVal);

                    // Call method in if/else conditions (pos: if/else conditions, others_class, overriding, (parameters)->methodBody)
                    OthersClass callObj = new SubOthersClass();
                    if (param.getValue().contains("3")) {
                        result.addAll(callObj.baseMethod(param.getValue())); // overriding + lambda body
                    } else {
                        result.addAll(new OthersClass().baseMethod(param.getValue()));
                    }

                    // Variable call (target type parameter + anonymous inner class)
                    param.setProcessor(new PublicTargetClass.Processor<String>() {
                        @Override
                        public String process(String val) {
                            // uses_outer_this feature
                            return SourceClass.this.outerProtectedField + "_" + val; // uses_outer_this
                        }
                    });
                    result.add(param.processValue());

                    // access_outer_protected feature
                    result.add(outerProtectedField + "_accessed");

                    // Used by reflection
                    Method method = SourceClass.class.getDeclaredMethod("accessorMethod", PublicTargetClass.class);
                    method.setAccessible(true);
                    result.addAll((List<String>) method.invoke(this, param));

                } catch (Exception e) {
                    result.add("Try-catch handled: " + e.getMessage());
                }

                // No new exception (core logic safe)
                return result;
            }

            // Local inner class for accessor (source_class feature)
            class AccessorInnerClass {
                List<String> getValues(PublicTargetClass<String> target) {
                    return new ArrayList<>(List.of(target.getValue() + "_local_inner"));
                }
            }
        };
    }
}

// Target public normal class (type parameter, anonymous inner class target_feature)
public class PublicTargetClass<T> {
    // obj.field for ExpressionStatement feature
    public T value;

    // Functional interface for anonymous inner class
    public interface Processor<U> {
        U process(U val);
    }

    private Processor<T> processor;

    // Type parameter feature (target_feature)
    public PublicTargetClass(T value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public T getValue() {
        return value;
    }

    public void setProcessor(Processor<T> processor) {
        this.processor = processor; // anonymous inner class assignment
    }

    public T processValue() {
        return processor.process(value);
    }

    public void setValue(T value) {
        this.value = value;
    }
}