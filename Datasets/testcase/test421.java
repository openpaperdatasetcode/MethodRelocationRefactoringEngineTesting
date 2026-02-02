import java.util.ArrayList;
import java.util.List;

// Source record class: default modifier, same package, static nested/anonymous inner class
record SourceRecord(String sourceField) {
    // Static nested class (fulfills source_class feature)
    static class SourceStaticNested {
        String getStaticValue() {
            return "static_nested_value";
        }
    }

    // Anonymous inner class (fulfills source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(sourceField);
        }
    };

    // Member inner class (source_inner) containing target method
    class SourceInnerClass {
        // Target class field (fulfills per_condition: source contains target's field)
        TargetRecord targetField = new TargetRecord("source_target_field");

        // Target method: instance, List<String> return, private access, in source_inner
        private List<String> processData(TargetRecord.TargetInnerClass.TargetInnerRecClass param) {
            // VariableDeclarationStatement (private modifier, obj.field=1, pos:inner class)
            private int objFieldVal = 1; // target_feature: obj.field, numbers:1 (pos:inner class)
            
            // Variable call to target parameter
            String targetVar = param.getRecValue();
            
            // Switch statement
            int switchResult = 0;
            switch (targetVar.length()) {
                case 1:
                    switchResult = objFieldVal;
                    break;
                case 2:
                    switchResult = objFieldVal * 2;
                    break;
                default:
                    switchResult = objFieldVal * 3;
                    break;
            }
            
            // Access instance method
            String instanceMethodVal = this.getInstanceMethodValue(targetVar);
            
            // Prepare return value
            List<String> result = new ArrayList<>();
            result.add(targetVar);
            result.add(instanceMethodVal);
            result.add(String.valueOf(switchResult));
            
            // No new exception thrown (no_new_exception)
            return result;
        }

        // Instance method for access_instance_method feature
        private String getInstanceMethodValue(String val) {
            return val + "_processed_" + objFieldVal;
        }
    }
}

// Target record class: public modifier, member inner class (target_feature)
public record TargetRecord(String targetField) {
    // Member inner class (first level)
    public class TargetInnerClass {
        // Target inner recursive class (target_inner_rec)
        public class TargetInnerRecClass {
            private String recValue = targetField + "_rec";

            public String getRecValue() {
                return recValue;
            }
        }
    }
}