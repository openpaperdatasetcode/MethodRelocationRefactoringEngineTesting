package refactoring.test;

import java.io.IOException;

// Target class: protected modifier, static nested class (target_feature), same package as source
protected class TargetClass {
    public String targetField = "targetValue";
    
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private int nestedValue = 2;
        
        public static TargetClass createTargetInstance(String value) {
            TargetClass target = new TargetClass();
            target.targetField = value;
            return target;
        }
        
        public int getNestedValue() {
            return nestedValue;
        }
    }
    
    public void processData(String... data) throws IOException {
        if (data.length == 0) {
            throw new IOException("Empty data");
        }
    }
}

// Source class: protected modifier, static nested + local inner class, same package as target
protected class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();
    
    // Protected outer field for access_outer_protected feature
    protected int outerProtectedField = 1;
    
    // Static nested class (source_class feature)
    static class SourceStaticNested {
        public static int getStaticValue() {
            return 2;
        }
    }

    // Overload method (overload_exist feature)
    void refactorMethod(String singleArg) throws IOException {
        targetField.processData(singleArg);
    }

    // Varargs method: default access, void return type (method_position: source)
    void refactorMethod(String... args) throws IOException {
        // Empty statement (method feature)
        ;
        
        int count = 0;
        // While statement (pos for instance method feature)
        while (count < args.length) {
            // Variable call (method feature)
            String varCall = targetField.targetField;
            
            // Access outer protected (access_outer_protected feature)
            int outerVal = this.outerProtectedField;
            
            // Instance method feature: public modifier, 1, inner_class, instance, ClassName.methodName(arguments)
            TargetClass.TargetStaticNested innerObj = new TargetClass.TargetStaticNested();
            TargetClass targetInstance = TargetClass.TargetStaticNested.createTargetInstance(varCall + outerVal);
            
            // CaseDefaultExpression (exp feature), numbers=2, public modifier
            int caseVal = 2;
            String caseResult = switch (caseVal) {
                case 1 -> "One";
                case 2 -> "Two";
                default -> "Default";
            };
            
            // Requires throws (method feature) - call method that throws IOException
            targetInstance.processData(caseResult, args[count]);
            
            count++;
        }

        // Local inner class (source_class feature)
        class LocalInnerClass {
            public void updateTarget() {
                // Instance feature usage in local inner class
                targetField.targetField = "updated_" + SourceStaticNested.getStaticValue();
            }
        }
        new LocalInnerClass().updateTarget();
    }
}