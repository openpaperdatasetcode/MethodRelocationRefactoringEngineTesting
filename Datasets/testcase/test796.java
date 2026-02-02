// Source class: record, default modifier, same package, no additional features
record SourceClass(String sourceField, TargetClass<String> targetField) { // Satisfy per_condition: source contains target field
    // Overriding method (method type: overriding), Object return, public access, source position
    @Override
    public Object toString() {
        // Variable call feature
        String localVar = "sourceRecord";
        localVar += this.sourceField;

        // Labeled statement feature
        label:
        for (int i = 0; i < 3; i++) {
            if (i == 2) break label;
            localVar += i;
        }

        // Super constructor invocation feature
        class NestedClass extends SourceClass {
            public NestedClass() {
                super("nestedSource", new TargetClass<>("nestedTarget")); // super constructor invocation
            }
        }
        new NestedClass();

        // Uses outer this feature
        SourceClass outerThis = SourceClass.this;
        localVar += outerThis.targetField().value();

        // No new exception feature (no 'new Exception()' statements)
        return localVar;
    }
}

// Target class: record, protected modifier, type parameter + static nested class target_feature
protected record TargetClass<T>(T value) {
    // Type parameter (target_feature)
    private T typeParam = this.value;

    // Static nested class (target_feature)
    protected static class target_inner_rec {
        // Placeholder for moved overriding method
        @Override
        public String toString() {
            SourceClass source = new SourceClass("sourceVal", new TargetClass<>("targetVal"));
            return (String) source.toString();
        }
    }
}