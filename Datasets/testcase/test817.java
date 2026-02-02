package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

// Target class: abstract modifier, extends + implements, same package as source
interface TargetInterface {
    List<String> processData();
}

class TargetParent {
    protected String parentField = "parentValue";
}

abstract class TargetClass extends TargetParent implements TargetInterface {
    @Override
    public abstract List<String> processData();
}

// Source class: protected modifier, static nested class + local inner class, same package as target
protected class SourceClass {
    private String outerPrivateField = "outerPrivateValue";

    // Static nested class (source class feature)
    static class StaticNestedSource {
        protected String nestedField = "nestedSourceValue";
    }

    // Base method for overriding (method type: overriding)
    protected List<String> refactorMethod(TargetClass targetParam) {
        return new ArrayList<>();
    }

    // Inner class (method_position: source_inner)
    class SourceInnerClass {
        // Overriding method: protected access, List<String> return type, target parameter (per_condition)
        @Override
        protected List<String> refactorMethod(TargetClass targetParam) {
            // Variable call (feature)
            String varCall = targetParam.parentField;
            List<String> result = new ArrayList<>();

            // If statement (feature)
            if (varCall != null && !varCall.isEmpty()) {
                // Access outer private field (access_outer_private feature)
                result.add(SourceClass.this.outerPrivateField);
                
                // Raw type (feature)
                Collection rawCollection = new ArrayList();
                rawCollection.add(varCall);
                result.addAll(rawCollection);
            }

            // Local inner class (source class feature)
            class LocalInnerClass {
                void addData() {
                    result.add("localInnerData");
                }
            }
            new LocalInnerClass().addData();

            // No new exception thrown (feature)
            return result;
        }
    }
}