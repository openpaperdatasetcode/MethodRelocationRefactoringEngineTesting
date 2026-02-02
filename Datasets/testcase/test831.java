package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Super class for super constructor invocation
class SuperSourceClass {
    protected int superField = 1;
    public SuperSourceClass(int value) {
        this.superField = value;
    }
}

// Target class: protected modifier, static nested class (target_feature), same package as source
protected class TargetClass {
    public int targetField = 1; // obj.field + 1 for AssertStatement feature
    
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public String nestedData = "targetNested";
    }

    public TargetClass() {}
    
    public int getTargetField() {
        return targetField;
    }
}

// Source class: private modifier, extends SuperSourceClass (source_feature), same package as target
class OuterSourceContainer {
    private class SourceClass extends SuperSourceClass {
        public SourceClass() {
            super(1); // Super constructor invocation in source class
        }

        // Inner recursive class (source_inner_rec - method_position)
        class SourceInnerRecursive {
            // AssertStatement feature: private modifier, obj.field, 1, pos=same_package_target
            private void assertFeature(TargetClass obj) {
                assert obj.targetField == 1 : "Target field must be 1";
            }

            // Varargs method: final access, List<String> return type, target parameter (per_condition)
            final List<String> refactorMethod(TargetClass targetParam, String... args) {
                // Super constructor invocation (method feature)
                TargetClass newTarget = new TargetClass();
                
                // Variable call (method feature)
                int varCall = targetParam.getTargetField();
                List<String> result = new ArrayList<>();

                // With_bounds (method feature)
                int bound = args.length;
                for (int i = 0; i < bound; i++) { // Bound check
                    if (i >= bound - 1) {
                        result.add(args[i] + "_bound");
                        continue;
                    }
                    result.add(args[i]);
                }

                // Execute AssertStatement feature
                assertFeature(targetParam);

                // Return this (method feature) - return current inner class instance
                result.add(this.toString());

                // No new exception thrown (method feature)
                return result;
            }
        }
    }
}