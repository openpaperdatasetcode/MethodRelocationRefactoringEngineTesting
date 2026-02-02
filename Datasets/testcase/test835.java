package refactoring.test;

// Super class for super constructor invocation and super.field/super.methodName()
class SuperTargetClass {
    protected int superField = 2; // super.field + 2 for WhileStatement feature

    protected void superMethod() {
        System.out.println("Super method called");
    }
}

// Target class: default modifier, local inner class (target_feature), same package as source
class TargetClass extends SuperTargetClass {
    public String targetField = "targetValue";

    public TargetClass() {
        super(); // Super constructor invocation in target class
    }

    // Static method for call_method feature (static + super.methodName())
    public static void staticTargetMethod(TargetClass instance) {
        int count = 1; // NumberLiteral: 1
        while (count <= 2) { // pos=while for call_method
            instance.superMethod(); // super.methodName()
            count++;
        }
    }

    // Local inner class (target_feature)
    public Object processData() {
        class LocalInnerTarget {
            String innerData = "localInnerData";

            public String getInnerData() {
                return innerData;
            }
        }
        return new LocalInnerTarget().getInnerData();
    }

    public String getTargetField() {
        return targetField;
    }
}

// Source class container (for private modifier of SourceClass)
class SourceContainer {
    // Source class: private modifier, two static nested classes (source_feature), same package as target
    private class SourceClass {
        // First static nested class (source_feature)
        static class FirstStaticNested {
            public static int nestedValue = 1; // NumberLiteral: 1
        }

        // Second static nested class (source_feature)
        static class SecondStaticNested {
            public static String getNestedData() {
                return "secondNestedData";
            }
        }

        // WhileStatement feature: public modifier, super.field, 2, pos=inner class
        public void whileFeature(TargetClass target) {
            class InnerWhileClass { // pos=inner class
                void execute() {
                    int count = 0;
                    while (count < target.superField) { // super.field + 2
                        count++;
                        if (count == 2) break; // 2 for target_feature
                    }
                }
            }
            new InnerWhileClass().execute();
        }

        // Instance method: protected access, Object return type, target parameter (per_condition)
        protected Object refactorMethod(TargetClass targetParam) {
            // Super constructor invocation (method feature)
            TargetClass newTarget = new TargetClass();

            // Type declaration statement (method feature)
            String localVarType;
            localVarType = "StringType";

            // NumberLiteral feature: numbers=1, public modifier, exp=NumberLiteral
            public int numberLiteral = 1;

            // Variable call (method feature)
            String varCall = targetParam.getTargetField();

            // Uses_outer_this (method feature)
            SourceContainer outerThis = SourceContainer.this;
            String outerRef = outerThis.toString();

            // Execute WhileStatement feature
            whileFeature(targetParam);

            // call_method: target type, public modifier, static + super.methodName(), pos=while, return_type=void
            TargetClass.staticTargetMethod(targetParam);

            // Use local inner class from target
            Object processed = targetParam.processData();

            // No new exception thrown (method feature)
            return varCall + "_" + outerRef + "_" + processed;
        }
    }
}