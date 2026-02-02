package refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Super class for enum extends (note: enum implicitly extends Enum, use wrapper for extends feature)
class EnumSuperWrapper {
    protected String superField = "enumSuperValue";
    protected List<String> superMethod(String... args) {
        List<String> list = new ArrayList<>();
        for (String arg : args) {
            list.add("super_" + arg);
        }
        return list;
    }
}

// Functional interface for abstract feature in call_method
@FunctionalInterface
abstract interface EnumProcessable {
    List<String> process(String... args);
}

// Target enum class: default modifier, implements interface, local inner class (target_feature)
enum TargetEnum implements EnumProcessable {
    TARGET_1("value1"), TARGET_2("value2");

    // Target inner record (target_inner_rec - target class of method)
    public record TargetInnerRec(String id, String data) {}

    private final String enumField;
    private TargetInnerRec innerRec;

    // Super constructor invocation (implicit for Enum, explicit for custom logic)
    TargetEnum(String enumField) {
        this.enumField = enumField; // Implicit super(enumField) for Enum
        this.innerRec = new TargetInnerRec(this.name(), enumField);
    }

    // Local inner class (target_feature)
    @Override
    public List<String> process(String... args) {
        class LocalInnerEnum {
            List<String> combine(TargetInnerRec rec, String... data) {
                List<String> list = new ArrayList<>();
                list.add(rec.id());
                list.add(rec.data());
                for (String d : data) list.add(d);
                return list;
            }
        }
        return new LocalInnerEnum().combine(this.innerRec, args);
    }

    // Access instance field
    public String getEnumField() {
        return enumField;
    }

    public TargetInnerRec getInnerRec() {
        return innerRec;
    }
}

// Subclass for call_method (sub_class type)
class TargetEnumSubClass extends EnumSuperWrapper {
    // Private modifier, abstract + super.methodName(arguments), pos=Lambda, return_type=List<String>
    private List<String> subClassMethod(String... args) {
        // Lambda expressions (pos) + abstract (functional interface)
        Function<String[], List<String>> lambda = (params) -> super.superMethod(params); // super.methodName(arguments)
        return lambda.apply(args);
    }
}

// Source enum class: protected modifier, extends wrapper, member inner + static nested class (source_feature)
protected enum SourceEnum extends EnumSuperWrapper {
    SOURCE_1("source1"), SOURCE_2("source2");

    private final String sourceField;
    // Source contains target field (per_condition)
    private TargetEnum.TargetInnerRec targetInnerRecField;

    // Super constructor invocation
    SourceEnum(String sourceField) {
        super(); // Explicit super constructor invocation
        this.sourceField = sourceField;
        this.targetInnerRecField = new TargetEnum.TargetInnerRec(this.name(), sourceField);
    }

    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static TargetEnum.TargetInnerRec createInnerRec(String id, String data) {
            return new TargetEnum.TargetInnerRec(id, data);
        }
    }

    // Member inner class (source_feature)
    public class SourceMemberInner {
        public String getCombinedData(TargetEnum.TargetInnerRec rec) {
            return rec.id() + "_" + rec.data();
        }
    }

    // Varargs method: public access, TargetInnerRec return type (TargetClas Type), method_position=source
    public TargetEnum.TargetInnerRec refactorMethod(TargetEnum targetParam, String... args) {
        // Type declaration statement (method feature)
        TargetEnum.TargetInnerRec newInnerRec;

        // Super constructor invocation (method feature)
        newInnerRec = new TargetEnum.TargetInnerRec(targetParam.name(), targetParam.getEnumField());

        // Variable call (method feature)
        String varCall = targetParam.getInnerRec().data();

        // Access instance field (method feature)
        String instanceField = targetParam.getEnumField();

        // Depends on inner class (method feature)
        SourceMemberInner memberInner = new SourceMemberInner();
        String innerData = memberInner.getCombinedData(newInnerRec);

        // Used by reflection (method feature)
        try {
            Method processMethod = TargetEnum.class.getMethod("process", String[].class);
            List<String> reflectResult = (List<String>) processMethod.invoke(targetParam, (Object) args);
        } catch (Exception e) {
            // No new exception thrown (method feature)
        }

        // call_method: sub_class type, private modifier, abstract + super.methodName(), pos=Lambda, return_type=List<String>
        TargetEnumSubClass subClass = new TargetEnumSubClass();
        List<String> callResult = subClass.subClassMethod(args);

        // No new exception thrown (method feature)
        return SourceStaticNested.createInnerRec(innerData, varCall + "_" + instanceField);
    }
}