package refactoring.test;

// Parent generic class for target's extends feature
class TargetParentClass<T> {
    protected T parentField;
}

// Source generic class: public modifier, same package, type parameter + member inner + anonymous inner classes
public class SourceClass<S extends CharSequence> { // type parameter (source feature)
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>("initial");

    // Member inner class (source feature)
    public class SourceInnerClass {
        S innerData;

        public SourceInnerClass(S data) {
            this.innerData = data;
        }
    }

    // Anonymous inner class (source feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            // Uses outer this
            SourceClass<S> outerThis = SourceClass.this;
            outerThis.targetField.innerClass.innerValue = "anonymous_updated";
        }
    };

    /**
     * First overloading method (overloading type)
     * @param <T> generic type for raw_type feature
     */
    @SuppressWarnings("rawtypes") // has_annotation
    protected <T> void refactorMethod() {
        // Raw type usage
        TargetClass rawTarget = targetField;
        rawTarget.innerClass.innerValue = "raw_type_value";

        // Variable call (target inner class field)
        targetField.innerClass.innerValue = "overload_1";
        // Depends on inner class
        TargetClass<String>.TargetInnerClass innerInstance = targetField.new TargetInnerClass();
        innerInstance.innerValue = "depends_on_inner";

        // Uses outer this
        SourceClass.this.sourceAnonymous.run();
        // No new exception
    }

    /**
     * Second overloading method (overloading type)
     * @param param additional parameter for overloading
     */
    @Deprecated // has_annotation (duplicate feature)
    protected <T> void refactorMethod(T param) {
        // Variable call (target inner class field with parameter)
        targetField.innerClass.innerValue = param.toString();
        // Depends on inner class (target static nested class)
        TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
        targetField.innerClass.counter = staticNested.nestedValue;

        // Uses outer this
        SourceClass.this.sourceAnonymous.run();
        // No new exception
    }
}

// Target generic class: default modifier, extends + static nested class features
class TargetClass<T extends CharSequence> extends TargetParentClass<T> { // extends (target_feature)
    T value;
    TargetInnerClass innerClass;

    public TargetClass(T value) {
        this.value = value;
        this.parentField = value; // Use parent class field
        this.innerClass = new TargetInnerClass();
    }

    // Target inner class (target_inner - target for method)
    public class TargetInnerClass {
        String innerValue;
        int counter = 0;
    }

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        int nestedValue = 1;
    }
}