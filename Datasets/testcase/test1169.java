import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Super class for target_class extends feature
class TargetSuperClass {
    protected String superField = "TARGET_SUPER_FIELD_5866";
    public String superMethod(String val) {
        return val + "_super_processed";
    }
}

// Source strictfp record class (same package, static nested class, local inner class)
strictfp record SourceRecord(String data) {
    // Private outer field for access_outer_private feature
    private final String outerPrivateField = "SOURCE_PRIVATE_FIELD_5866";
    // per_condition: source contains the field of the target
    private final PublicTargetRecord<String> targetField = new PublicTargetRecord<>("init_value_5866");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass<U> {
        static String format(U val) {
            return val.toString().toUpperCase() + "_static_source";
        }
    }

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String innerData) {
        // Method to be refactored (instance, void return, private access)
        private void processTarget(PublicTargetRecord<String>.InnerClass param) { // per_condition: target parameter (target_inner)
            // type declaration statement
            List<String> result = new ArrayList<>();
            String targetVal;
            int num;

            // ExpressionStatement (private modifier, ClassName.field, 1, pos: inner class)
            private void exprStatementBlock() {
                // pos: inner class (nested inside method)
                class ExprInnerClass {
                    void execute() {
                        String classField = PublicTargetRecord.CLASS_FIELD; // ClassName.field
                        num = 1; // target_feature: 1
                        
                        // Expression statement
                        param.innerValue = param.innerValue + "_expr_" + classField + "_" + num; // expression statement
                    }
                }
                new ExprInnerClass().execute();
            }

            // Execute expression statement block
            exprStatementBlock();

            // access_outer_private feature (uses_outer_this to access outer private field)
            String privateVal = SourceRecord.this.outerPrivateField; // uses_outer_this
            param.innerValue = param.innerValue + "_private_" + privateVal;

            // raw_type feature (use raw type of target static nested class)
            PublicTargetRecord.StaticNestedTargetClass rawStaticObj = new PublicTargetRecord.StaticNestedTargetClass(); // raw type
            param.innerValue = param.innerValue + "_raw_" + rawStaticObj.getStaticVal();

            // depends_on_inner_class feature (rely on target anonymous inner class)
            param.setProcessor(new PublicTargetRecord.Processor<String>() { // anonymous inner class (target_feature)
                @Override
                public String process(String val) {
                    return val + "_anonymous_inner_processed";
                }
            });
            param.innerValue = param.processValue();

            // used_by_reflection feature (invoke target inner class method via reflection)
            try {
                Method setValueMethod = PublicTargetRecord.InnerClass.class.getDeclaredMethod("setInnerValue", String.class);
                setValueMethod.invoke(param, param.getInnerValue() + "_reflection_modified");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // local inner class (source_class feature)
            class LocalInnerClass {
                void enhanceValue(PublicTargetRecord<String>.InnerClass inner) {
                    inner.setInnerValue(StaticNestedSourceClass.format(inner.getInnerValue()));
                }
            }
            new LocalInnerClass().enhanceValue(param);

            // Variable call (target type parameter + extends feature)
            param.innerValue = param.innerValue + "_super_" + param.superMethodCall();

            // No new exception
        }
    }

    // Helper method to create inner record instance
    public InnerSourceRecord createInnerRecord() {
        return new InnerSourceRecord(this.data + "_inner_rec");
    }
}

// Target public record class (type parameter, extends, anonymous inner class target_feature)
public record PublicTargetRecord<T>(T value) extends TargetSuperClass {
    // ClassName.field for ExpressionStatement feature
    public static final String CLASS_FIELD = "TARGET_CLASS_FIELD_5866";

    // Static nested class (for raw_type feature)
    public static class StaticNestedTargetClass<U> {
        private String staticVal = "TARGET_STATIC_VAL_5866";
        public String getStaticVal() { return staticVal; }
    }

    // Functional interface for anonymous inner class
    public interface Processor<U> {
        U process(U val);
    }

    // Inner class (target_inner)
    public class InnerClass {
        public T innerValue;
        private Processor<T> processor;

        public InnerClass() {
            this.innerValue = PublicTargetRecord.this.value;
        }

        // Variable call: getter/setter
        public T getInnerValue() { return innerValue; }
        public void setInnerValue(T value) { this.innerValue = value; }

        // extends feature (call super class method)
        public String superMethodCall() {
            return super.superMethod(this.innerValue.toString());
        }

        // anonymous inner class feature (set processor)
        public void setProcessor(Processor<T> processor) {
            this.processor = processor;
        }

        public T processValue() {
            return processor.process(innerValue);
        }
    }

    // Constructor to validate value
    public PublicTargetRecord {
        if (value == null) {
            value = (T) "default_target_value_5866";
        }
    }

    // Helper method to create inner class instance
    public InnerClass createInnerClass() {
        return new InnerClass();
    }
}