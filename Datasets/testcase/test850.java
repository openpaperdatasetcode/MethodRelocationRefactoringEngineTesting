import java.util.List;

// Wrapper class to enable private modifier for generic classes (top-level can't be private)
class PackageWrapper {
    // Source class: generic, private modifier, same package, anonymous inner + member inner class
    private class SourceClass<T extends CharSequence> {
        // Satisfy per_condition: source contains target class field
        private TargetClass<String> targetField = new TargetClass<>("targetValue");
        // Instance field for access_instance_field feature
        private T instanceField;
        // Static field for depends_on_static_field feature
        private static int staticField = 42;

        // Member inner class (source_class feature)
        public class SourceMemberInnerClass<U extends Number> {
            // Abstract method to refactor (method type: abstract), void return, public access, source_inner position
            public abstract void abstractMethod();

            // Concrete implementation (to satisfy abstract method in non-abstract inner class)
            public void concreteImpl() {
                // Variable call feature
                T localVar = (T) "sourceVar";
                U numVar = (U) Integer.valueOf(10);

                // Type declaration statement feature
                List<T> stringList;
                TargetClass<U> rawTarget; // With bounds + type declaration

                // Access instance field (access_instance_field feature)
                SourceClass.this.instanceField = localVar;
                localVar = SourceClass.this.instanceField;

                // Depends on static field (depends_on_static_field feature)
                int staticVal = SourceClass.staticField;
                localVar = (T) (localVar + "_" + staticVal);

                // With bounds feature (generic with bounds)
                class BoundedClass<V extends T & Comparable<T>> {
                    V boundedField;
                }
                BoundedClass<T> boundedInstance = new BoundedClass<>();
                boundedInstance.boundedField = localVar;

                // Uses outer this feature
                SourceClass<T> outerThis = SourceClass.this;
                localVar = (T) (localVar + "_" + outerThis.targetField.getValue());

                // Constructor invocation feature
                SourceMemberInnerClass<U> innerInstance = new SourceMemberInnerClass<>();

                // Return statement feature
                if (localVar.isEmpty()) {
                    return;
                }

                // No new exception feature (no 'new Exception()' statements)
                System.out.println(localVar + "_" + numVar + "_" + staticVal);
            }
        }

        // Anonymous inner class (source_class feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                SourceMemberInnerClass<Integer> inner = new SourceMemberInnerClass<>();
                inner.concreteImpl();
            }
        };
    }

    // Target class: generic, private modifier, static nested class target_feature
    private class TargetClass<V> {
        private V value;

        public TargetClass(V value) {
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        // Static nested class (target_feature)
        public static class target_inner {
            // Placeholder for moved abstract method (concrete impl for refactoring target)
            public void abstractMethod() {
                PackageWrapper wrapper = new PackageWrapper();
                PackageWrapper.SourceClass<String> source = wrapper.new SourceClass<>();
                PackageWrapper.SourceClass<String>.SourceMemberInnerClass<Integer> inner = source.new SourceMemberInnerClass<>();
                inner.concreteImpl();
            }
        }
    }
}