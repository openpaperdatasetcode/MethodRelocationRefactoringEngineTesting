package refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Parent class for call_method (parent_class type)
class ParentClass {
    // Public instance method with outerInstance.new InnerClass().methodName() (call_method features)
    public String callMethod(AbstractTargetClass target) {
        try {
            // Exception handling statements pos
            if (target == null) throw new NullPointerException();
            // outerInstance.new InnerClass().methodName()
            AbstractTargetClass.TargetInner inner = target.new TargetInner();
            return inner.processData();
        } catch (NullPointerException e) {
            return "default"; // No new exception
        }
    }
}

// Source abstract class: public modifier, same package, type parameter + double static nested classes
public abstract class AbstractSourceClass<T extends CharSequence> extends ParentClass {
    // Source contains target field (per_condition)
    private AbstractTargetClass targetField = new AbstractTargetClass() {};

    // Type parameter (source feature)
    private T sourceData;

    // First static nested class (source feature)
    public static class SourceStaticNestedOne {
        int value = 1;
    }

    // Second static nested class (source feature - duplicate)
    public static class SourceStaticNestedTwo {
        List<String> list = new ArrayList<>();
    }

    // Member inner class for source_inner_rec structure
    public class SourceInnerClass {
        // Recursive inner class (method_position: source_inner_rec)
        public class SourceRecursiveInner {
            // Normal method to be refactored (all specified features)
            public List<String> refactorMethod() {
                List<String> result = new ArrayList<>();
                
                // Raw type usage
                ArrayList rawList = new ArrayList();
                rawList.add("raw_type");

                // Variable call (target class field)
                targetField.counter = 1;
                // Expression statement
                targetField.data = "refactor";
                // Access instance method
                String instanceResult = targetField.process();
                result.add(instanceResult);

                // TypeMethodReference (numbers:1, public modifier, exp:TypeMethodReference)
                public Function<AbstractTargetClass, String> methodRef = AbstractTargetClass::getFieldValue; // TypeMethodReference
                result.add(methodRef.apply(targetField) + 1); // 1 (numbers feature)

                // Override violation (invalid override attempt)
                class InvalidOverride extends AbstractTargetClass {
                    @Override
                    public final String getFieldValue() { // Compile error: final method override
                        return "violation"; // No new exception
                    }
                }

                // Call_method invocation (parent_class, public, instance, outerInstance.new InnerClass().methodName())
                String callResult = AbstractSourceClass.super.callMethod(targetField);
                result.add(callResult);

                // Add raw type data to result
                result.addAll(rawList);

                // No new exception, return List<String>
                return result;
            }
        }
    }

    // Abstract method (required for abstract class)
    public abstract T getSourceData();
}

// Target abstract class: final modifier, anonymous inner class feature
public final abstract class AbstractTargetClass {
    int counter = 1;
    String data;

    // Member inner class for call_method
    public class TargetInner {
        public String processData() {
            return data + "_" + counter;
        }
    }

    // Instance method for access_instance_method feature
    public String process() {
        // Anonymous inner class (target_feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                counter++; // No new exception
            }
        };
        anonymous.run();
        return data;
    }

    // Final method for override_violation feature
    public final String getFieldValue() {
        return data;
    }
}