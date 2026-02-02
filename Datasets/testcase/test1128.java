import java.util.function.Function;

// Super interface for source_class implements feature
interface SourceInterface<T> {
    void interfaceMethod(T param);
}

// Super class for super.field feature
class TargetSuperClass {
    protected String superField = "super_target_field_5675";
}

// Source normal class (default modifier, same package, type parameter, implements)
class SourceClass<T extends PrivateTargetClass> implements SourceInterface<T> {
    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String data) {
        // Method to be refactored (instance, void return, protected access)
        protected void targetMethod(T param) { // per_condition: contains target parameter
            // Type declaration statement
            String targetValue;
            int num;
            PrivateTargetClass.StaticNestedClass staticObj;

            // Empty statement
            ;

            // VariableDeclarationStatement (private modifier, super.field, 1, pos: diff_package_others)
            private void varDeclBlock() {
                TargetSuperClass superObj = new TargetSuperClass();
                String superField = superObj.superField; // super.field
                int count = 1; // target_feature: 1
                targetValue = superField + "_" + count;
            }

            // Requires try-catch
            try {
                varDeclBlock();
                // Constructor invocation
                staticObj = new PrivateTargetClass.StaticNestedClass();
                // Variable call
                param.setValue(targetValue + "_" + staticObj.getStaticValue());
                
                // Call method in functional interfaces (pos: functional interfaces)
                Function<PrivateTargetClass, String> func = new SubClass()::callMethod; // (parameters) -> methodBody
                String callResult = func.apply(param);
                System.out.println(callResult);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // Sub class for call_method (sub_class type, default modifier)
        class SubClass {
            // Call method (normal, (parameters) -> methodBody, return String)
            String callMethod(PrivateTargetClass param) {
                // Lambda expression: (parameters) -> methodBody
                return ((String s) -> s.toUpperCase()).apply(param.getValue());
            }
        }
    }

    @Override
    public void interfaceMethod(T param) {
        new InnerSourceRecord("data").targetMethod(param);
    }
}

// Target normal class (private modifier, static nested class target_feature)
private class PrivateTargetClass extends TargetSuperClass {
    // Static nested class (target_feature)
    public static class StaticNestedClass {
        private String staticValue = "static_nested_value";

        public String getStaticValue() {
            return staticValue;
        }
    }

    private String value = "target_value_5675";

    // Constructor for invocation
    public PrivateTargetClass() {}

    // Variable call: getter/setter
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}