import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Parent class for source_class extends and call_method features
strictfp abstract class ParentClass {
    // Super field for WhileStatement target_feature (super.field = 2)
    protected int superField;

    // Call method: parent_class type, strictfp modifier, abstract, ClassName::methodName, Lambda pos, void return
    protected abstract void callMethod(String arg);

    // Lambda expressions position for call_method
    Consumer<String> lambda = ParentClass::callMethod; // ClassName::methodName
}

// Source class: normal, private modifier (nested to enable private), same package, extends + two local inner classes
class WrapperClass {
    // Private source class (matches modifier: private)
    private class SourceClass extends ParentClass {
        // Satisfy per_condition: source contains target class field
        private TargetClass targetField = new TargetClass();
        // Protected outer field for access_outer_protected feature
        protected String outerProtectedField = "protectedValue";

        // Source inner recursive structure (method_position: source_inner_rec)
        public class SourceInnerRec {
            // Method to refactor: varargs, List<String> return, public access, source_inner_rec position
            public List<String> methodToRefactor(Integer... args) {
                // Variable call feature
                String localVar = "localVar";
                int count = args.length > 0 ? args[0] : 0;

                // Access outer protected field (access_outer_protected feature)
                localVar += SourceClass.this.outerProtectedField;

                // With bounds feature (generic with bounds)
                class BoundedClass<T extends CharSequence> {
                    T boundedField;
                }
                BoundedClass<String> boundedInstance = new BoundedClass<>();
                boundedInstance.boundedField = localVar;

                // WhileStatement: public modifier, super.field, 2, pos: same_package_others
                publicWhileStatement();

                // Instance method feature (protected modifier, 1, target, instance, (parameters) -> methodBody, for pos, void return)
                for (int i = 0; i < 1; i++) { // pos: for
                    protectedInstanceMethod(1, targetField, (num) -> System.out.println(num)); // (parameters) -> methodBody
                }

                // No new exception feature (no 'new Exception()' statements)
                List<String> result = new ArrayList<>();
                result.add(localVar);
                return result;
            }

            // Public WhileStatement implementation (pos: same_package_others)
            public void publicWhileStatement() {
                int i = 0;
                while (i < 2) {
                    SourceClass.this.superField = 2; // super.field, 2
                    i++;
                }
            }

            // Protected instance method (matches type/modifier/method_feature/pos/return_type)
            protected void protectedInstanceMethod(int num, TargetClass target, Consumer<Integer> lambda) { // 1, target, instance
                lambda.accept(num); // (parameters) -> methodBody
            }
        }

        // First local inner class (source_class feature)
        public void methodWithLocalInner1() {
            class LocalInnerClass1 {
                public void innerMethod() {}
            }
            new LocalInnerClass1();
        }

        // Second local inner class (source_class feature)
        public void methodWithLocalInner2() {
            class LocalInnerClass2 {
                public void innerMethod() {}
            }
            new LocalInnerClass2();
        }
    }
}

// Target class: normal, protected modifier, member inner class target_feature
protected class TargetClass {
    // Member inner class (target_feature)
    public class TargetInnerClass {
        public void innerMethod() {}
    }

    // Target class for method relocation
    public static class target {
        // Placeholder for moved method
        public List<String> methodToRefactor(Integer... args) {
            WrapperClass wrapper = new WrapperClass();
            WrapperClass.SourceClass source = wrapper.new SourceClass();
            WrapperClass.SourceClass.SourceInnerRec innerRec = source.new SourceInnerRec();
            return innerRec.methodToRefactor(args);
        }
    }
}