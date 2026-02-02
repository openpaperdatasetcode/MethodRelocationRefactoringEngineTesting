import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Function;

// Super class for SuperFieldAccess feature
class SourceSuperClass {
    protected String superField = "SOURCE_SUPER_FIELD_5849";
    public void superTypeMethod(String arg) {
        System.out.println("SuperTypeReference method: " + arg);
    }
}

// Annotation for generic method position (attribute values of annotations)
@Retention(RetentionPolicy.RUNTIME)
@interface GenericAnno {
    String value();
}

// Source private normal class (same package, anonymous inner class, member inner class)
private class SourceClass extends SourceSuperClass {
    // Private outer field for access_outer_private
    private String outerPrivateField = "SOURCE_PRIVATE_FIELD_5849";
    // per_condition: source contains the field of the target
    private final SealedTargetClass targetField = new ConcreteTargetClass("init_value_5849");

    // Member inner class (source_class feature)
    class MemberInnerClass<T> {
        // Generic method (public modifier, 1, inner_class, generic, (parameters) -> methodBody)
        public int genericMethod(T val) {
            // (parameters) -> methodBody (lambda expression)
            Function<T, Integer> lambda = (v) -> v.toString().length() + 1; // 1 (method_feature)
            return lambda.apply(val);
        }
    }

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // access_outer_private feature
            System.out.println("Anonymous inner access private field: " + outerPrivateField);
        }
    };

    // Call method (source type, synchronized modifier, instance, superTypeReference.methodName(arguments))
    private synchronized void callMethod(String val) { // synchronized modifier
        // superTypeReference.methodName(arguments)
        new SourceSuperClass().superTypeMethod(val + "_superTypeCall");
    }

    // Parent method for overriding
    int processTarget(SealedTargetClass.InnerRecord param) {
        return param.value().length();
    }

    /**
     * Overriding method with strictfp access modifier
     * @param param Target inner record parameter
     * @return base type (int)
     */
    @Override
    strictfp int processTarget(SealedTargetClass.InnerRecord param) { // overriding type, strictfp access
        // numbers:1, modifier:default, exp:SuperFieldAccess
        default String superFieldAccess() {
            int num = 1; // numbers:1
            return super.superField + "_" + num; // SuperFieldAccess expression
        }

        // Generic method call in annotation attribute values (pos: the attribute values of annotations)
        @GenericAnno(value = String.valueOf(new MemberInnerClass<String>().genericMethod(param.value())))
        class AnnotationWrapper {}

        // Call method in object initialization (pos: object initialization)
        Runnable callRunnable = new Runnable() {
            @Override
            public void run() {
                callMethod(param.value()); // instance method call
            }
        };
        callRunnable.run();

        // Trigger anonymous inner class
        anonymousInner.run();

        // Variable call (target inner record)
        String innerRecVal = param.value() + "_" + superFieldAccess();
        int baseResult = new MemberInnerClass<String>().genericMethod(innerRecVal); // 1 (method_feature)

        // access_outer_private feature
        baseResult += outerPrivateField.length();

        // No new exception
        return baseResult;
    }
}

// Target sealed normal class (empty target_feature list)
sealed class SealedTargetClass permits ConcreteTargetClass {
    // Inner record (target_inner_rec)
    public record InnerRecord(String value) {}

    private String value;

    public SealedTargetClass(String value) {
        this.value = value;
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public InnerRecord getInnerRecord() {
        return new InnerRecord(this.value);
    }
}

// Concrete implementation of sealed target class
final class ConcreteTargetClass extends SealedTargetClass {
    public ConcreteTargetClass(String value) {
        super(value);
    }
}