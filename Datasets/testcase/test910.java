package refactoring.test;

// Target record class: final modifier, member inner class (target_feature)
public final record TargetClass<T>(T instanceField) {
    // Member inner class (target_feature)
    public class TargetInner {
        private T innerData;

        public TargetInner(T innerData) {
            this.innerData = innerData;
        }

        public T getInnerData() {
            return innerData;
        }
    }

    // Additional constructor for constructor invocation feature
    public TargetClass(T instanceField, T dummy) {
        this(instanceField);
    }
}

// Source record class: public modifier, type parameter, two anonymous inner classes (source_feature)
public record SourceClass<T>(T sourceField) {
    // First anonymous inner class (source_feature)
    private final Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            // uses_outer_this feature
            System.out.println("Source outer this: " + SourceClass.this.sourceField);
        }
    };

    // Second anonymous inner class (source_feature)
    private final Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target field access: " + sourceField);
        }
    };

    // Instance method: final access, TargetInner return type (TargetClas Type), target parameter (per_condition)
    public final TargetClass<T>.TargetInner refactorMethod(TargetClass<T> targetParam) {
        // Constructor invocation feature
        TargetClass<T> newTarget = new TargetClass<>(targetParam.instanceField(), (T) "dummy");

        // Variable call feature
        T varCall = targetParam.instanceField();

        // Access instance field feature
        T instanceFieldVal = newTarget.instanceField();

        // uses_outer_this feature
        T outerThisVal = SourceClass.this.sourceField;

        // Execute anonymous inner classes
        anonymousInner1.run();
        anonymousInner2.run();

        // Create and return TargetInner (TargetClas Type)
        TargetClass<T>.TargetInner targetInner = newTarget.new TargetInner(varCall);

        // No new exception thrown feature
        return targetInner;
    }
}