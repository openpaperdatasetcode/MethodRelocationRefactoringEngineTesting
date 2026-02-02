package refactoring.test;

import java.util.function.Consumer;

// Source class: normal, default modifier, same package as target, features: implements, local inner class, static nested class
class SourceClass implements Consumer<String> {
    // Static nested class (source_class feature)
    static class SourceStaticNested {
        int nestedValue = 0;

        int getProcessedValue(int input) {
            return input * 2;
        }
    }

    // Method to be refactored: instance, return void, default access, position source
    // Per condition: method contains target class parameter
    void moveMethod(TargetClass targetParam) {
        // Variable call feature
        int localVar = 10;
        SourceStaticNested staticNested = new SourceStaticNested();

        // Local inner class (source_class feature)
        class SourceLocalInner {
            int combineValues(int val1, int val2) {
                return val1 + val2;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();

        // Depends on inner class feature (use local inner class and static nested class)
        localVar = localInner.combineValues(localVar, staticNested.getProcessedValue(localVar));
        
        // Assert statement feature
        assert localVar > 0 : "Local variable must be greater than 0";

        // Use target parameter's local inner class
        targetParam.processData(localVar);

        // No new exception thrown (no_new_exception feature)
        System.out.println("Processed value: " + localVar);
    }

    // Implement Consumer interface method (source_class feature: implements)
    @Override
    public void accept(String s) {
        System.out.println("Accepted: " + s);
    }
}

// Target class: normal, protected modifier, same package, target_feature: local inner class
protected class TargetClass {
    public void processData(int value) {
        // Local inner class (target_feature)
        class TargetLocalInner {
            int validate(int val) {
                return val > 0 ? val : 0;
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        int validated = localInner.validate(value);
        System.out.println("Target processed value: " + validated);
    }
}