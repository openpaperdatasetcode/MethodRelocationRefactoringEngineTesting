import java.util.function.Function;

// Wrapper class to enable private modifier for source class (top-level classes can't be private)
class PackageWrapper {
    // Source class: normal, private modifier, same package, type parameter + static nested + local inner class
    private class SourceClass<T extends CharSequence> {
        // Satisfy per_condition: source contains target class field
        private TargetClass targetField = new TargetClass();
        // Protected outer field for access_outer_protected feature
        protected String outerProtectedField = "outerProtectedValue";

        // Type parameter (source_class feature)
        private T typeParam;

        // Static nested class (source_class feature)
        private static class SourceStaticNestedClass {
            public static String staticMethod(String input) {
                return input.toUpperCase();
            }
        }

        // Overloading method 1 (method type: overloading), Object return, public access, source position
        public Object overloadedMethod(TargetClass target, T param) {
            // Variable call feature
            String localVar = "base";
            localVar += param.toString();

            // Type declaration statement feature
            SourceInnerHelper innerHelper;
            Object[] objArray;

            // Access outer protected field (access_outer_protected feature)
            localVar += "_" + this.outerProtectedField;

            // Uses outer this feature
            SourceClass<T> outerThis = SourceClass.this;
            localVar += "_outerThis_" + outerThis.outerProtectedField;

            // Constructor invocation feature
            innerHelper = new SourceInnerHelper();
            SourceStaticNestedClass staticNested = new SourceStaticNestedClass();

            // Depends on inner class feature
            localVar = innerHelper.processString(localVar);

            // Overriding feature: protected modifier, 2, inner_class, overriding, ClassName::methodName, array initialization pos, Object return
            objArray = new Object[]{this.overriddenMethod(2, innerHelper, localVar)}; // array initialization pos

            // No new exception feature (no 'new Exception()' statements)
            return objArray[0];
        }

        // Overloading method 2 (overload variant)
        public Object overloadedMethod(TargetClass target) {
            return this.overloadedMethod(target, (T) "defaultParam");
        }

        // Overriding method (matches type/modifier/method_feature/pos/return_type)
        protected Object overriddenMethod(int num, SourceInnerHelper inner, String str) { // 2, inner_class, overriding
            // ClassName::methodName (method reference)
            Function<String, String> func = SourceStaticNestedClass::staticMethod;
            return inner.overrideHelper(str, num, func);
        }

        // Inner helper class for depends_on_inner_class feature
        private class SourceInnerHelper {
            public String processString(String input) {
                return input + "_processed";
            }

            // Overridden helper method (overriding feature)
            public Object overrideHelper(String str, int num, Function<String, String> func) {
                return func.apply(str) + "_" + num;
            }
        }

        // Method with local inner class (source_class feature)
        public void methodWithLocalInner() {
            class LocalInnerClass<U> {
                public U process(U input) {
                    return input;
                }
            }
            LocalInnerClass<String> localInner = new LocalInnerClass<>();
            localInner.process("localInnerValue");
        }
    }

    /**
     * TargetClass - default modifier, javadoc + member inner class target_feature
     * This class is the target for Move Method refactoring
     */
    class TargetClass {
        private String fieldValue = "targetValue";

        // Member inner class (target_feature)
        public class TargetInnerClass {
            public String getProcessedValue(String input) {
                return input + "_targetInner";
            }
        }

        // Target class for method relocation
        public static class target {
            // Placeholder for overloaded methods
            public Object overloadedMethod(TargetClass target) {
                PackageWrapper wrapper = new PackageWrapper();
                PackageWrapper.SourceClass<String> source = wrapper.new SourceClass<>();
                return source.overloadedMethod(target);
            }

            public Object overloadedMethod(TargetClass target, String param) {
                PackageWrapper wrapper = new PackageWrapper();
                PackageWrapper.SourceClass<String> source = wrapper.new SourceClass<>();
                return source.overloadedMethod(target, param);
            }
        }

        public String getFieldValue() {
            return fieldValue;
        }
    }
}