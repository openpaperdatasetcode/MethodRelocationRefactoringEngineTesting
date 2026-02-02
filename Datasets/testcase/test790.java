/**
 * Test case for Move Method refactoring engine (ID: derived from specs)
 * Same package for source and target classes (implicit no package declaration)
 */
// Source class: normal, public modifier, same package, permits + static nested + member inner class
public sealed class SourceClass permits SourceSubClass {
    // Member inner class (source_class feature)
    public class SourceInnerClass {
        // Method to refactor: instance, base type (int) return, private access, source_inner position
        /**
         * Method to refactor - processes TargetClass parameter
         * @param targetParam TargetClass parameter (satisfies per_condition)
         * @return int (base type) result
         * @throws NullPointerException if targetParam is null
         */
        private int methodToRefactor(TargetClass<String> targetParam) {
            // Variable call feature
            int localVar = 0;
            String innerVar = "test";

            // Depends on inner class feature (uses member inner class)
            SourceInnerHelper innerHelper = new SourceInnerHelper();
            localVar = innerHelper.getHelperValue();

            // Constructor invocation feature
            SourceInnerHelper anotherHelper = new SourceInnerHelper();

            // For statement feature
            for (int i = 0; i < 5; i++) {
                localVar += i;
                innerVar += i;
            }

            // Super constructor invocation feature
            class SuperConstructorClass extends SourceInnerClass {
                public SuperConstructorClass() {
                    super(); // super constructor invocation
                }
            }
            new SuperConstructorClass();

            // NullPointerException feature (no new exception - reuses standard NPE)
            if (targetParam == null) {
                throw new NullPointerException("Target parameter cannot be null");
            }

            // No new exception feature (no custom exception instantiation)
            return localVar;
        }

        // Inner helper class for depends_on_inner_class feature
        private class SourceInnerHelper {
            public int getHelperValue() {
                return 10;
            }
        }
    }

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public static void staticHelper() {}
    }
}

// Permits subclass (source_class feature: permits)
final class SourceSubClass extends SourceClass {}

// Target class: normal, abstract modifier, type parameter + member inner class target_feature
public abstract class TargetClass<T> {
    // Type parameter (target_feature)
    private T typeParam;

    // Member inner class (target_feature)
    public class TargetInnerClass {
        public T getTypeParam() {
            return typeParam;
        }
    }

    // Target class for method relocation
    public static class target {
        // Placeholder for moved method (matches signature)
        private int methodToRefactor(TargetClass<String> targetParam) {
            SourceClass source = new SourceSubClass();
            SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
            return inner.methodToRefactor(targetParam);
        }
    }
}