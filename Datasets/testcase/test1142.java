import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Source enum class (public modifier, same package, two anonymous inner classes)
public enum SourceEnum {
    INSTANCE;

    // per_condition: source contains the field of the target
    private final PrivateTargetEnum targetField = PrivateTargetEnum.VALUE1;

    // First anonymous inner class (source_class feature)
    private final Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            System.out.println("First anonymous inner in SourceEnum: " + targetField.getInnerRecord().value());
        }
    };

    // Second anonymous inner class (source_class feature)
    private final Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Second anonymous inner in SourceEnum: " + targetField.value);
        }
    };

    // Method to be refactored (instance, Object return, public access, position: source)
    public Object targetMethod(PrivateTargetEnum.InnerRecord param) { // per_condition: target parameter (target_inner_rec)
        // Empty statement
        ;

        // Super constructor invocation (enum implicit super)
        super();

        // Raw type
        List rawList = new ArrayList(); // raw_type

        // Labeled statement
        outerLabel:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1) {
                    break outerLabel; // labeled statement usage
                }
                // Access instance field
                String targetInstanceField = targetField.value; // access_instance_field
                // Variable call (target inner record)
                String innerRecVal = param.value() + "_" + targetInstanceField;
                rawList.add(innerRecVal);
            }
        }

        // Used by reflection
        Object result = null;
        try {
            Method method = SourceEnum.class.getDeclaredMethod("targetMethod", PrivateTargetEnum.InnerRecord.class);
            method.setAccessible(true);
            result = method.invoke(this, param);
        } catch (Exception e) {
            rawList.add("Reflection error: " + e.getMessage());
            result = rawList;
        }

        // Trigger anonymous inner classes
        anonymousInner1.run();
        anonymousInner2.run();

        // No new exception
        return result;
    }
}

// Target enum class (private modifier, anonymous inner class target_feature)
private enum PrivateTargetEnum {
    VALUE1("value1"), VALUE2("value2");

    // Instance field for access_instance_field
    public final String value;
    private final InnerRecord innerRecord;

    // Anonymous inner class (target_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in PrivateTargetEnum: " + value);
        }
    };

    // Inner record (target_inner_rec)
    public record InnerRecord(String value) {}

    PrivateTargetEnum(String value) {
        this.value = value;
        this.innerRecord = new InnerRecord(value + "_inner_rec");
        this.anonymousInner.run(); // Trigger anonymous inner class
    }

    // Variable call: getter for inner record
    public InnerRecord getInnerRecord() {
        return innerRecord;
    }
}