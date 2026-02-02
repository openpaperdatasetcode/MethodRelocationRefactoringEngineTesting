import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Annotation for call_method pos: annotation attribute values
@Retention(RetentionPolicy.RUNTIME)
@interface CallMethodAnnotation {
    String value();
}

// Abstract source generic class (abstract modifier, same package as target, type parameter feature)
abstract class SourceClass<T extends CharSequence> {
    // First member inner class (source_class feature)
    public class FirstInnerClass {
        // Second member inner class (source_class feature)
        public class SecondInnerClass {
            // Call method inner class (inner_class type, default modifier)
            class CallMethodInner {
                // Overloading method 1 (call_method target_feature: overloading)
                String processData(TargetClass.InnerClass targetInner) {
                    return targetInner.getData();
                }

                // Overloading method 2 (call_method target_feature: overloading)
                String processData(TargetClass.InnerClass targetInner, int val) {
                    return targetInner.getData() + "_" + val;
                }

                // Annotation with call_method in attribute values (pos: annotation attribute values)
                @CallMethodAnnotation(value = TargetClass.InnerClass::getData) // ClassName::methodName
                public String getProcessedData(TargetClass.InnerClass targetInner) {
                    return processData(targetInner, 3);
                }
            }
        }
    }

    // Static method to be moved (protected access, base type return, source position)
    protected static int processTarget(TargetClass.InnerClass targetParam) throws IOException {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            // IOException (no_new_exception: no explicit new Exception())
            throw new IOException("Target inner class parameter cannot be null");
        }

        // Instance method with specified features (public modifier, pos: for, return List<String>)
        public List<String> instanceHelper(SourceClass<?> outerThis) {
            int val = 3; // method_feature: 3
            List<String> result = new ArrayList<>();
            
            // method_feature: sub_class + instanceReference.methodName(arguments)
            TargetSubClass subInstance = new TargetSubClass();
            result.add(subInstance.processInner(targetParam, val)); // instanceReference.methodName(arguments)

            // pos: for loop
            for (int i = 0; i < 5; i++) {
                if (i == 2) {
                    continue; // continue statement
                }
                // Access instance field of target inner class
                targetParam.data = "Loop: " + i; // access_instance_field
                // Variable call to target inner class method
                result.add(targetParam.processData());
                // uses_outer_this (reference outer class this)
                result.add("Outer this: " + outerThis.toString());
            }
            return result; // return List<String>
        }

        // Invoke instance helper method (uses_outer_this)
        SourceClass<String> outerInstance = new SourceClass<String>() {};
        List<String> helperResult = instanceHelper(outerInstance);
        
        // Variable call to target inner class
        targetParam.setCounter(helperResult.size());
        
        // Base type return (int)
        return targetParam.getCounter();
    }
}

// Target private normal class (private modifier, same package as source)
private class TargetClass {
    // Member inner class (target_feature, target_inner for method's target class)
    public class InnerClass {
        // Instance field for access_instance_field feature
        public String data;
        private int counter = 0;

        // Variable call methods
        public String processData() {
            return "Processed: " + data;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }
    }
}

// Subclass of TargetClass for sub_class method_feature
class TargetSubClass extends TargetClass {
    // Instance method for instanceReference.methodName(arguments)
    public String processInner(InnerClass inner, int val) {
        return inner.getData() + "_subclass_" + val;
    }
}