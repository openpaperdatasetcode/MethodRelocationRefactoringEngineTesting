import java.lang.reflect.Method;

// Source class: record, public modifier, same package, static nested + member inner class
public record SourceClass(String sourceField, TargetClass targetField) { // Satisfy per_condition: source contains target field
    // Member inner class (source_class feature)
    public class SourceInnerClass {
        private int innerField = 2; // Field for this.field = 2 feature

        // Method to refactor: varargs, void return, public access, source_inner position
        public void methodToRefactor(Object... args) {
            // Variable call feature
            String localVar = "localVar";
            localVar = args.length > 0 ? args[0].toString() : localVar;

            // Expression statement feature
            int exprResult = localVar.length() + innerField;

            // SynchronizedStatement: private modifier, this.field, 2, pos: source
            privateSynchronizedStatement();

            // Try statement feature
            try {
                // Used by reflection feature
                Method refMethod = SourceInnerClass.class.getMethod("methodToRefactor", Object[].class);
                refMethod.invoke(this, (Object) args);
            } catch (ReflectiveOperationException e) {
                // No new exception feature (rethrow existing, no custom new Exception())
                e.printStackTrace();
            }

            // No new exception feature (no 'new Exception()' statements)
        }

        // Private SynchronizedStatement implementation (pos: source)
        private void privateSynchronizedStatement() {
            synchronized (this) {
                this.innerField = 2; // this.field, 2
            }
        }
    }

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public static void staticMethod() {}
    }
}

// Target class: record, non-sealed modifier, extends + member inner class target_feature
public non-sealed record TargetClass(String targetData) implements TargetInterface { // extends (via interface implementation)
    // Member inner class (target_feature)
    public class TargetInnerClass {
        public void innerMethod() {}
    }

    // Target class for method relocation
    public static class target {
        // Placeholder for moved method
        public void methodToRefactor(Object... args) {
            SourceClass source = new SourceClass("sourceVal", new TargetClass("targetVal"));
            SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
            inner.methodToRefactor(args);
        }
    }
}

// Interface for TargetClass "extends" feature (records implement interfaces)
interface TargetInterface {}