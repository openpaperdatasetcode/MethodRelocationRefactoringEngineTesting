import java.util.List;

// Source class: public normal class, same package, no extra features
public class SourceClass {
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass targetField = new TargetClass();

    // First-level inner class
    class SourceInnerClass {
        // Recursive inner class (source_inner_rec) containing target method
        class SourceInnerRecClass {
            // Instance method for access_instance_method feature
            private <T extends Number & Comparable<T>> int boundedMethod(T val) {
                return val.intValue() * 2;
            }

            // Target method: normal, Object return, private access, in source_inner_rec
            private Object processData(TargetClass.TargetInnerClass param) {
                // Expression statement
                String targetIdentifier = "target_inner_data";
                
                // Variable call to target parameter
                String targetVar = param.getInnerValue();
                
                // With_bounds feature (generic type with multiple bounds)
                class BoundedGeneric<T extends List<String> & Comparable<T>> {
                    T processBoundedValue(T val) {
                        return val;
                    }
                }
                BoundedGeneric<List<String>> boundedInstance = new BoundedGeneric<>();
                
                // Requires_try_catch feature
                Object result = null;
                try {
                    // Access instance method (with_bounds + access_instance_method)
                    int instanceVal = this.boundedMethod(10); // access_instance_method
                    result = targetVar + "_processed_" + instanceVal;
                    
                    // Additional expression statement
                    boundedInstance.processBoundedValue(List.of(result.toString()));
                } catch (ClassCastException e) {
                    // Handle exception (requires_try_catch)
                    result = "default_value";
                }
                
                return result; // Object return type, no new exception thrown
            }
        }
    }
}

// Target class: protected modifier, static nested class (target_feature)
protected class TargetClass {
    // Static nested class (fulfills target_feature)
    public static class TargetStaticNested {
        public String getStaticValue() {
            return "static_nested_value";
        }
    }

    // Target inner class (target_inner)
    public class TargetInnerClass {
        private String innerValue = "target_inner_value";

        // Instance method for variable call
        public String getInnerValue() {
            return innerValue;
        }
    }
}