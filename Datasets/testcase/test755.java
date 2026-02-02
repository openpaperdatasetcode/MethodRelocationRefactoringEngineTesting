import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Source class: record, default modifier, same package, two static nested classes
record SourceRecord(String sourceField) {
    // Satisfy per_condition: source contains target class field
    private final TargetRecord targetField = new TargetRecord("targetValue");

    // First static nested class (source_class feature)
    static class FirstStaticNested {
        public String processString(String input) {
            return input.toUpperCase();
        }
    }

    // Second static nested class (source_class feature)
    static class SecondStaticNested {
        public List<String> createList(int count) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                list.add("item_" + i);
            }
            return list;
        }
    }

    // Source inner class (method_position: source_inner)
    class SourceInnerClass {
        // Instance method to refactor: base type (int) return, default access
        public int instanceMethod() {
            // Variable call feature
            String localVar = "innerVar";
            int loopCount = 3; // Matches "3" in instance method_feature

            // Uses outer this feature
            SourceRecord outerThis = SourceRecord.this;
            localVar += "_outerThis_" + outerThis.sourceField;

            // Super constructor invocation feature (nested class)
            class NestedClass extends SourceInnerClass {
                public NestedClass() {
                    super(); // super constructor invocation
                }
            }
            new NestedClass();

            // While statement feature
            int count = 0;
            while (count < loopCount) {
                localVar += "_loop_" + count;
                count++;
            }

            // Instance method feature: protected modifier, 3, source, instance, instanceReference::methodName, array initialization pos, List<String> return
            FirstStaticNested instanceRef = new FirstStaticNested(); // instanceReference
            Function<String, String> methodRef = instanceRef::processString; // instanceReference::methodName
            // Array initialization position for instance method feature
            List<String>[] listArray = new List[]{this.protectedInstanceMethod(loopCount, methodRef)};

            // Variable call + target field usage
            int result = listArray[0].size() + outerThis.targetField.value().length();

            // No new exception feature (no 'new Exception()' statements)
            return result;
        }

        // Protected instance method (matches instance method_feature specs)
        protected List<String> protectedInstanceMethod(int num, Function<String, String> func) { // 3, source, instance
            SecondStaticNested nested = new SecondStaticNested();
            List<String> list = nested.createList(num); // num = 3
            list.replaceAll(func);
            return list;
        }
    }
}

// Target class: record, public modifier, anonymous inner class target_feature
public record TargetRecord(String value) {
    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + value);
        }
    };

    // Target class for method relocation
    public static class target {
        // Placeholder for moved instance method
        public int instanceMethod() {
            SourceRecord source = new SourceRecord("sourceValue");
            SourceRecord.SourceInnerClass inner = source.new SourceInnerClass();
            return inner.instanceMethod();
        }
    }
}