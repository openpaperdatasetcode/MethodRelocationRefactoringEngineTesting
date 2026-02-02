import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface InstanceMethodAnno {}

// Source class: record, protected modifier, same package, member inner + anonymous inner class
protected record SourceRecord(String sourceField) {
    // Satisfy per_condition: source contains target class field
    private final TargetRecord<String> targetField = new TargetRecord<>("targetValue");

    // Member inner class (source_class feature)
    public class SourceInnerClass {
        private int innerField; // Field for VariableDeclarationStatement (this.field)

        // Instance method to refactor: TargetClass Type return, default access, source_inner position
        @InstanceMethodAnno // has_annotation feature
        public TargetRecord<String> instanceMethod() { // method types parameter is:none
            // Variable call feature
            String localVar = "innerVar";
            int fieldValue = 1; // Matches "1" in VariableDeclarationStatement target_feature

            // Type declaration statement feature
            TargetRecord<String> targetInstance;
            SourceInnerHelper helper;

            // VariableDeclarationStatement feature: protected modifier, this.field, 1, pos: source
            protected int varDeclareStmt = 1; // this.field equivalent (1)
            this.innerField = varDeclareStmt; // this.field = 1

            // Constructor invocation feature
            helper = new SourceInnerHelper();
            targetInstance = new TargetRecord<>(localVar + "_constructed");

            // Access instance method feature
            localVar = helper.processString(localVar);
            localVar += "_" + this.innerField;

            // Throw statement feature (no new exception - reuse existing RuntimeException)
            try {
                if (localVar.isEmpty()) {
                    throw new RuntimeException("Empty variable"); // throw statement
                }
            } catch (RuntimeException e) {
                // No new exception feature (no 'new Exception()' instantiation)
                localVar = "default_" + localVar;
            }

            // Variable call + target field usage
            targetInstance = new TargetRecord<>(localVar + "_" + sourceField + "_" + targetField.value());

            // No new exception feature (no custom exception instantiation)
            return targetInstance;
        }

        // Inner helper class for access_instance_method feature
        private class SourceInnerHelper {
            public String processString(String input) {
                return input + "_processed";
            }
        }
    }

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            SourceInnerClass inner = new SourceInnerClass();
            System.out.println(inner.instanceMethod().value());
        }
    };
}

// Target class: record, public modifier, type parameter + local inner class target_feature
public record TargetRecord<T>(T value) {
    // Type parameter (target_feature)
    private final T typeParam = this.value;

    // Target inner class for method relocation
    public static class target_inner {
        // Placeholder for moved instance method
        @InstanceMethodAnno
        public TargetRecord<String> instanceMethod() {
            SourceRecord source = new SourceRecord("sourceValue");
            SourceRecord.SourceInnerClass inner = source.new SourceInnerClass();
            return inner.instanceMethod();
        }
    }

    // Method with local inner class (target_feature)
    public void methodWithLocalInner() {
        // Local inner class (target_feature)
        class LocalInnerClass<U> {
            public U process(U input) {
                return (U) (input + "_localInner");
            }
        }
        LocalInnerClass<T> localInner = new LocalInnerClass<>();
        System.out.println(localInner.process(this.value));
    }
}