// Source class: normal, final modifier, same package, no additional features
final class SourceClass {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Source inner class (method_position: source_inner)
    class SourceInnerClass {
        // Method to refactor: varargs, Object return, private access, source_inner position
        private Object methodToRefactor(Object... args) {
            // Variable call feature
            Object localVar = "localVariable";
            localVar = args.length > 0 ? args[0] : localVar;

            // Raw type feature
            TargetClass rawTarget; // Raw type declaration (no generic)
            rawTarget = new TargetClass();

            // Return statement feature
            if (localVar == null) {
                return null; // Return statement (null case)
            }

            // No new exception feature (no 'new Exception()' statements)
            return localVar + "_" + targetField.getFieldValue(); // Return statement (value case)
        }
    }
}

// Target class: normal, protected modifier, static nested class target_feature
protected class TargetClass {
    private String fieldValue = "targetValue";

    public String getFieldValue() {
        return fieldValue;
    }

    // Static nested class (target_feature)
    protected static class target {
        // Placeholder for moved method
        private Object methodToRefactor(Object... args) {
            SourceClass source = new SourceClass();
            SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
            return inner.methodToRefactor(args);
        }
    }
}