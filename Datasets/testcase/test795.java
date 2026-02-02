// Wrapper class to enable private modifier for generic source class (top-level can't be private)
class PackageWrapper {
    // Source class: generic, private modifier, same package, member inner + static nested class
    private class SourceClass<T extends CharSequence> {
        // Member inner class (source_class feature)
        public class SourceInnerClass {
            // Method to refactor: varargs, base type (int) return, public access, source_inner position
            public int methodToRefactor(TargetClass<String> targetParam, T... args) {
                // Satisfy per_condition: method contains target class parameter (targetParam)
                
                // Variable call feature
                int localVar = 0;
                T varArg;

                // Type declaration statement feature
                SourceClass rawSource; // raw_type feature (no generic type)
                TargetClass rawTarget; // raw_type feature

                // Super keywords feature
                super.toString();

                // Constructor invocation feature
                rawSource = new SourceClass<>();
                SourceStaticNestedClass staticNested = new SourceStaticNestedClass();

                // Enhanced varargs handling + variable call
                for (T arg : args) {
                    varArg = arg;
                    localVar += varArg.length();
                }

                // Raw type usage (raw_type feature)
                rawTarget = new TargetClass<>("rawTypeValue");
                localVar += rawTarget.getValue().length();

                // No new exception feature (no 'new Exception()' statements)
                return localVar;
            }
        }

        // Static nested class (source_class feature)
        public static class SourceStaticNestedClass {
            public static <E> E staticHelper(E value) {
                return value;
            }
        }
    }
}

// Target class: generic, public modifier, anonymous inner class target_feature
public class TargetClass<V> {
    private V value;

    public TargetClass(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class: " + value);
        }
    };

    // Target inner recursive structure for method relocation
    public static class target_inner_rec {
        // Placeholder for moved method (matches signature)
        public int methodToRefactor(TargetClass<String> targetParam, String... args) {
            PackageWrapper wrapper = new PackageWrapper();
            PackageWrapper.SourceClass<String> source = wrapper.new SourceClass<>();
            PackageWrapper.SourceClass<String>.SourceInnerClass inner = source.new SourceInnerClass();
            return inner.methodToRefactor(targetParam, args);
        }
    }
}