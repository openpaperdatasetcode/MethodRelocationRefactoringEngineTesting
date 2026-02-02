package refactoring.test;

// Target class: private modifier, member inner class (target_feature), same package as source
class OuterContainer {
    private class TargetClass {
        int targetField = 10;
        
        // Member inner class (target_feature)
        class TargetInnerClass {
            String innerData = "targetInner";
        }
    }
}

// Source class: protected modifier, static nested + local inner class, same package as target
protected class SourceClass {
    // Static nested class (source_class feature)
    static class StaticNestedSource {
        int nestedValue = 5;
        
        // Non-overridable static method for override_violation
        public static void staticMethod() {}
    }

    // Inner class (method_position: source_inner)
    class SourceInnerClass {
        // Instance method: default access, base return type (int), target parameter (per_condition)
        int refactorMethod(OuterContainer.TargetClass targetParam) {
            // Variable call feature
            int varCall = targetParam.targetField;
            
            // Expression statement feature
            varCall += SourceClass.StaticNestedSource.nestedValue;
            
            // Assert statement feature
            assert varCall > 0 : "Value must be positive";
            
            // Override_violation feature (attempt to override static method)
            try {
                StaticNestedSource invalidOverride = new StaticNestedSource() {
                    // Compile-time override violation (static method cannot be overridden)
                    public void staticMethod() {}
                };
            } catch (Exception e) {
                // No new exception thrown feature (no custom exception instantiation)
            }

            // Local inner class (source_class feature)
            class LocalInnerClass {
                int modifyValue(int val) {
                    return val * 2;
                }
            }
            varCall = new LocalInnerClass().modifyValue(varCall);

            // No new exception thrown feature (no 'new Exception()' statements)
            return varCall;
        }
    }
}