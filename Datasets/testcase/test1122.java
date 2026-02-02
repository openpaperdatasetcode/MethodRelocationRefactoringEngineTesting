import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Annotation for has_annotation feature
@interface MethodAnnotation {}

// Super interface for source_class implements feature
interface RecordInterface {
    List<String> process(TargetRecord param);
}

// Source record class (public modifier, same package, implements)
public record SourceRecord(String data) implements RecordInterface {
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "source_static_5810";

    // Inner class for constructor method_feature: inner_class
    class InnerHelper {
        List<String> buildList(String val, int num) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                list.add(val + "_" + i);
            }
            return list;
        }
    }

    /**
     * Instance method with strictfp access, returns List<String>
     * @param param Target record parameter
     * @return Processed list of strings
     */
    @MethodAnnotation // has_annotation feature
    @Override
    public strictfp List<String> process(TargetRecord param) { // per_condition: target parameter
        // Variable call (target record access)
        String targetValue = param.value();
        // Access instance method
        String processedVal = param.instanceMethod(targetValue);

        // numbers:3, modifier:default, exp:TypeMethodReference
        Function<String, String> typeRef = String::toUpperCase; // TypeMethodReference
        String refResult = typeRef.apply(processedVal + "_3"); // numbers:3

        // Constructor call in for loop (pos: for, 3, inner_class, constructor, ClassName::methodName)
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) { // method_feature: 3
            InnerHelper helper = new InnerHelper(); // constructor
            result.addAll(helper.buildList(refResult + STATIC_FIELD, i)); // depends_on_static_field
        }

        // ClassName::methodName reference
        Function<TargetRecord, String> targetRef = TargetRecord::instanceMethod;
        result.add(targetRef.apply(param));

        // No new exception
        return result;
    }
}

// Target record class (public modifier, local inner class target_feature)
public record TargetRecord(String value) {
    // Local inner class (target_feature) - defined in instance method
    public String instanceMethod(String input) {
        class TargetLocalInnerClass { // local inner class
            String format(String s) {
                return s + "_formatted_" + TargetRecord.this.value();
            }
        }
        return new TargetLocalInnerClass().format(input);
    }
}