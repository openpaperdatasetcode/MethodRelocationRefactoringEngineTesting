// Source package (different from target package)
package sourcepkg;

import targetpkg.TargetClass;

// Source class: normal, protected modifier, different package, double static nested classes
protected class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // First static nested class (source feature)
    private static class SourceStaticNestedOne {
        int value = 1;
    }

    // Second static nested class (source feature - duplicate)
    private static class SourceStaticNestedTwo {
        String data;
    }

    // Instance method to be refactored (all specified features)
    protected void refactorMethod() {
        // Variable call (target class field)
        targetField.counter = 1;
        targetField.data = "source_data";

        // Annotation expression (numbers:1, private modifier, exp:Annotation)
        @SuppressWarnings("unused") // Annotation feature
        private int annotatedField = 1; // 1 (numbers feature)

        // Throw statement (no_new_exception - rethrow existing)
        try {
            if (targetField.counter != 1) {
                throw new IllegalArgumentException("Invalid counter value"); // Throw statement
            }
        } catch (IllegalArgumentException e) {
            // No new exception instantiation
            throw e;
        }
    }
}

// Target package (different from source package)
package targetpkg;

// Target class: normal, default modifier, static nested class feature
class TargetClass {
    int counter;
    String data;

    // Static nested class (target_feature)
    static class TargetStaticNested {
        int nestedValue = 1;
    }
}