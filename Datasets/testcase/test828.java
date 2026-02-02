package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Parent class for source class 'extends' feature
class SourceParent {
    protected String parentField = "parentValue";
}

/**
 * Target class Javadoc (target_feature: javadoc)
 * This is the target class with public modifier and member inner class
 */
public class TargetClass {
    public String targetField = "targetData";

    // Member inner class (target_feature)
    public class TargetInnerClass {
        public String getInnerValue() {
            return "targetInnerValue";
        }
    }

    public String getTargetField() {
        return targetField;
    }
}

// Source class: protected modifier, extends, local inner + member inner class (source_feature), same package as target
protected class SourceClass extends SourceParent {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class (source_feature)
    public class SourceInnerClass {
        /**
         * Method Javadoc (method feature)
         * Instance method with List<String> return type, public access
         * @return List<String> result data
         */
        public List<String> refactorMethod() {
            List<String> result = new ArrayList<>();

            // Variable call (method feature)
            String targetVar = targetField.getTargetField();
            result.add(targetVar);

            // Local inner class (source_feature)
            class LocalInnerClass {
                // call_method: inner_class type, public modifier, abstract + lambda, pos=if/else, return_type=String
                public String innerCallMethod() {
                    // Abstract feature (functional interface)
                    @FunctionalInterface
                    interface AbstractFunctional {
                        String process(String input);
                    }

                    String input = targetVar;
                    String lambdaResult;

                    // pos: if/else conditions
                    if (input != null && !input.isEmpty()) {
                        // (parameters) -> methodBody (lambda feature)
                        AbstractFunctional lambda = (param) -> param + "_processed";
                        lambdaResult = lambda.process(input);
                    } else {
                        AbstractFunctional lambda = (param) -> "default_" + param;
                        lambdaResult = lambda.process("empty");
                    }
                    return lambdaResult;
                }
            }

            // Invoke call_method
            LocalInnerClass localInner = new LocalInnerClass();
            result.add(localInner.innerCallMethod());

            // No new exception thrown (method feature)
            return result;
        }
    }
}