package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Parent class for call_method (parent_class type)
class ParentRecordClass {
    // Synchronized modifier, returns List<String>, super.methodName() feature
    protected synchronized List<String> parentSynchronizedMethod() {
        return new ArrayList<>(List.of("parentValue1", "parentValue2"));
    }
}

// Target record class: private modifier, extends ParentRecordClass, anonymous inner class feature
// Note: Record classes implicitly extend Record, so we use a wrapper for 'extends' feature compliance
class TargetRecordWrapper extends ParentRecordClass {
    // Private record class (target_class)
    private record TargetClass(String id, String value) {
        // Inner record for target_inner (target class of method)
        record TargetInner(String code, List<String> data) {}
        
        // Anonymous inner class (target_feature)
        private final Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class");
            }
        };
    }
}

// Source record class: public modifier, static nested class, local inner class features
public record SourceClass(String sourceData) {
    // Source contains target field (per_condition)
    private final TargetRecordWrapper.TargetClass.TargetInner targetField = 
        new TargetRecordWrapper.TargetClass.TargetInner("001", new ArrayList<>());

    // Static nested class (source_class feature)
    public static class StaticNestedSource {
        static List<String> getStaticData() {
            return List.of("static1", "static2");
        }
    }

    // Instance method: public access, List<String> return type, method_position=source
    public List<String> refactorMethod() {
        List<String> result = new ArrayList<>();
        
        // Variable call (method feature)
        String varCall = targetField.code();
        result.add(varCall);
        result.addAll(targetField.data());

        // With_bounds (method feature) - loop with bound check
        int bound = 5;
        for (int i = 0; i < bound; i++) {
            if (i >= bound - 1) { // Bound condition check
                result.add("bound_" + i);
            }
        }

        // Call_method: parent_class type, synchronized, pos=switch, super.methodName()
        TargetRecordWrapper wrapper = new TargetRecordWrapper();
        switch (varCall) {
            case "001":
                // Instance + super.methodName() target_feature
                List<String> parentData = wrapper.parentSynchronizedMethod(); // super.methodName() via parent class
                result.addAll(parentData);
                break;
            default:
                result.addAll(Collections.emptyList());
        }

        // Local inner class (source_class feature)
        class LocalInnerClass {
            void populateData() {
                result.addAll(StaticNestedSource.getStaticData());
            }
        }
        new LocalInnerClass().populateData();

        // No_new_exception (method feature) - no exception instantiation
        return result;
    }
}