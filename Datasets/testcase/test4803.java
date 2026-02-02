package test.refactoring;

import java.util.function.Consumer;

// Parent generic class for SourceClass to enable "super keywords"
class ParentGeneric<T> {
    protected T parentProtectedField;

    public ParentGeneric(T parentField) {
        this.parentProtectedField = parentField;
    }

    protected String getParentInfo() {
        return "ParentGeneric[field=" + parentProtectedField + "]";
    }
}

// TargetClass: generic class, public modifier, has member inner class (target_feature)
public class TargetClass<T> {
    private T targetData;

    // Member inner class (target_feature)
    public class TargetInner<U> {
        private U innerData;

        public TargetInner(U innerData) {
            this.innerData = innerData;
        }

        public String combineData() {
            return "TargetInner[inner=" + innerData + ", outer=" + targetData + "]";
        }
    }

    public TargetClass(T targetData) {
        this.targetData = targetData;
    }

    public T getTargetData() {
        return targetData;
    }

    public void setTargetData(T targetData) {
        this.targetData = targetData;
    }
}

// SourceClass: generic class, private modifier, same package, has member inner & anonymous inner (source_feature)
private class SourceClass<T, S> extends ParentGeneric<T> {
    // Source contains target field (per_condition)
    private TargetClass<S> targetField;

    // Member inner class (source_class feature)
    public class SourceInner<U> {
        private U innerValue;

        public SourceInner(U value) {
            this.innerValue = value;
        }

        public String processWithTarget(TargetClass<S> target) {
            return "SourceInner[value=" + innerValue + ", target=" + target.getTargetData() + "]";
        }
    }

    public SourceClass(T parentField, TargetClass<S> target) {
        super(parentField);
        this.targetField = target;
    }

    // Method: generic type, return void, public access, position source
    public <U> void genericMethod(U input, Consumer<String> consumer) {
        // Per_condition: method uses source's target field (contains target parameter)
        if (targetField == null) {
            consumer.accept("Warning: targetField is null");
            return;
        }

        // Super keywords (method feature: access parent method)
        String parentInfo = super.getParentInfo();
        // Super keywords (method feature: access parent field)
        T parentFieldValue = super.parentProtectedField;

        // Access outer protected (method feature: parent's protected field via super)
        String protectedAccess = "Access outer protected: " + parentFieldValue;

        // Create target's member inner class instance
        TargetClass.TargetInner<U> targetInner = targetField.new TargetInner<>(input);

        // Create source's member inner class instance
        SourceInner<U> sourceInner = new SourceInner<>(input);

        // Variable call (method feature)
        variableCall(targetField, "Processing with input: " + input);

        // Anonymous inner class (source_class feature: use Consumer)
        Consumer<String> anonConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                String combined = s + "|AnonymousInner[parent=" + parentInfo + ", inner=" + targetInner.combineData() + "]";
                consumer.accept(combined);
            }
        };

        // Process and pass result to consumer
        anonConsumer.accept(sourceInner.processWithTarget(targetField) + "|" + protectedAccess);
    }

    // Variable call (method feature)
    private void variableCall(TargetClass<S> target, String message) {
        System.out.printf("[SourceClass] %s | Target data: %s%n",
                message, target.getTargetData());
    }
}

// Test entry
public class TestEntry {
    public static void main(String[] args) {
        // Initialize target (generic type String, public access)
        TargetClass<String> target = new TargetClass<>("target_string_data");
        
        // Initialize source (generic, private access, contains target field: per_condition)
        SourceClass<Integer, String> source = new SourceClass<>(42, target);

        // Test 1: Call generic method with String input
        System.out.println("Test 1 Result:");
        source.genericMethod("test_input_1", System.out::println);

        // Test 2: Call generic method with Integer input
        System.out.println("\nTest 2 Result:");
        source.genericMethod(123, System.out::println);

        // Test 3: Call with null target
        System.out.println("\nTest 3 Result (null target):");
        SourceClass<Double, String> sourceWithNullTarget = new SourceClass<>(3.14, null);
        sourceWithNullTarget.genericMethod("test_input_3", System.out::println);
    }
}
    