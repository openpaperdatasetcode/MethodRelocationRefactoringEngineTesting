import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Annotation for has_annotation feature
@interface RecordMethodAnno {}

// Others class for generic method_feature: others_class
class OthersClass<T> {
    protected T superMethod(T param, int num) {
        return (T) (param.toString() + "_super_" + num);
    }

    // Overloading method for overload_exist feature
    protected T superMethod(T param) {
        return superMethod(param, 0);
    }
}

// Source record class (public modifier, same package, type parameter, local inner class, member inner class)
public record SourceRecord<T extends CharSequence>(T data) {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "source_protected_5832";

    // Member inner class (source_class feature)
    public class MemberInnerClass<U> extends OthersClass<U> {
        // Generic method (default modifier, 1, others_class, generic, super.methodName())
        public <V> TargetRecord<V> genericMethod(V param, int num) {
            V superResult = super.superMethod(param, num); // super.methodName()
            return new TargetRecord<>(new TargetRecord.InnerRecord<>(superResult));
        }

        // Overloading method for overload_exist feature
        public <V> TargetRecord<V> genericMethod(V param) {
            return genericMethod(param, 0);
        }
    }

    /**
     * Abstract method with has_annotation feature
     * @param param Target inner record parameter
     * @return List<String>
     */
    @RecordMethodAnno // has_annotation feature
    public abstract List<String> process(TargetRecord.InnerRecord<T> param); // abstract type, target_inner_rec

    // Concrete implementation of abstract method (for compilation)
    public static <T extends CharSequence> SourceRecord<T> of(T data) {
        return new SourceRecord<T>(data) {
            @Override
            public List<String> process(TargetRecord.InnerRecord<T> param) { // per_condition: target parameter
                List<String> result = new ArrayList<>();

                // Labeled statement
                outerLabel:
                for (int i = 0; i < 3; i++) { // for statement
                    // Expression statement
                    String processed = param.value().toString() + "_" + i; // expression statement

                    // If statement
                    if (i == 1) {
                        // Access outer protected field
                        processed += "_" + outerProtectedField; // access_outer_protected
                    }

                    // Switch statement
                    switch (i) {
                        case 0:
                            result.add(processed + "_case0");
                            break;
                        case 1:
                            result.add(processed + "_case1");
                            break;
                        default:
                            result.add(processed + "_default");
                            break outerLabel; // labeled statement + break
                    }
                }

                // Synchronized statement
                synchronized (this) {
                    // Variable call + access instance method
                    MemberInnerClass<T> innerObj = new MemberInnerClass<>();
                    TargetRecord<T> target = innerObj.genericMethod(param.value(), 1); // 1 (method_feature)
                    result.add(target.innerRecord().value().toString());

                    // Overload exist (call overloaded generic method)
                    TargetRecord<T> overloadedTarget = innerObj.genericMethod(param.value()); // overload_exist
                    result.add(overloadedTarget.innerRecord().value().toString());
                }

                // Call method in object initialization (pos: object initialization, source, protected, static, ClassName::methodName)
                Function<T, List<String>> callFunc = SourceRecord::callMethod; // ClassName::methodName
                List<String> callResult = new ArrayList<>(callFunc.apply(param.value()));
                result.addAll(callResult);

                // Local inner class (source_class feature)
                class LocalInnerClass {
                    void updateList(List<String> list) {
                        list.add("local_inner_" + param.value());
                    }
                }
                new LocalInnerClass().updateList(result);

                // No new exception
                return result;
            }
        };
    }

    // Call method (source type, protected modifier, static, ClassName::methodName, return List<String>)
    protected static <T extends CharSequence> List<String> callMethod(T param) {
        List<String> list = new ArrayList<>();
        list.add("call_method_" + param);
        return list;
    }
}

// Target record class (public modifier, empty target_feature list)
public record TargetRecord<T>(InnerRecord<T> innerRecord) {
    // Inner record (target_inner_rec)
    public record InnerRecord<U>(U value) {}

    // Constructor for object initialization
    public TargetRecord(U value) {
        this(new InnerRecord<>(value));
    }
}