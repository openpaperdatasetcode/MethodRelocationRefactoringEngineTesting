import java.lang.reflect.Method;

// Source class: normal public class, same package as target, no extra features
public class SourceClass {
    protected int outerProtectedField = 42; // For access_outer_protected

    // Inner class (first level)
    class SourceInnerClass {
        // Nested inner class (source_inner_rec) containing target method
        class SourceInnerRecClass {
            // Target method: varargs, base type return, default access, in source_inner_rec
            int processData(TargetClass.TargetInnerClass... params) {
                int result = 0;
                labeledLoop: // Labeled statement
                for (int i = 0; i < params.length; i++) { // For statement
                    if (params[i] == null) break labeledLoop;
                    
                    // Super keyword usage
                    super.toString();
                    
                    // Variable call to target parameter
                    result += params[i].targetField;
                    
                    // Access outer protected field (access_outer_protected)
                    result += SourceClass.this.outerProtectedField;
                    
                    // Constructor (private modifier) in exception throwing statements
                    try {
                        Object instance = TargetClass.TargetInnerClass.createInstance(params[i].targetField);
                        // ClassName.methodName(arguments) - constructor feature
                    } catch (IllegalAccessException e) {
                        // No new exception thrown (no_new_exception)
                        result = -1;
                    }
                    
                    // Used by reflection
                    try {
                        Method method = params[i].getClass().getMethod("getTargetField");
                        result += (int) method.invoke(params[i]);
                    } catch (Exception e) {
                        result = -2;
                    }
                }
                return result; // Base type return (int)
            }
        }
    }
}

// Target class: normal class (default modifier), has member inner class
class TargetClass {
    // Target inner class (target_inner)
    class TargetInnerClass {
        int targetField;

        // Private constructor (matches constructor modifier: private)
        private TargetInnerClass(int targetField) {
            this.targetField = targetField;
        }

        // Static method for ClassName.methodName(arguments)
        public static Object createInstance(int value) throws IllegalAccessException {
            try {
                // Exception throwing statements (constructor pos)
                return TargetInnerClass.class.getDeclaredConstructor(int.class).newInstance(value);
            } catch (Exception e) {
                throw new IllegalAccessException("Failed to create instance");
            }
        }

        // Getter for reflection access
        int getTargetField() {
            return targetField;
        }
    }

    // Field of target used in source (per_condition: source contains target's field)
    TargetInnerClass targetInnerField = new TargetInnerClass(10);
}

// Other class with call_method: synchronized, others_class type, lambda position
class OtherClass {
    // Call method: synchronized, instance, OuterClass.InnerClass.methodName() in lambda
    synchronized String invokeTargetMethod() {
        SourceClass source = new SourceClass();
        TargetClass target = new TargetClass();
        
        // Lambda expression containing OuterClass.InnerClass.methodName()
        Runnable lambda = () -> {
            SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
            SourceClass.SourceInnerClass.SourceInnerRecClass innerRec = inner.new SourceInnerRecClass();
            int val = innerRec.processData(target.targetInnerField); // Variable call to target field
            System.out.println(val);
        };
        lambda.run();
        
        return "processed"; // String return type
    }
}