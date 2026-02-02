package refactoring.test;

// Source class: normal, private modifier, same package as target, features: permits, static nested class, member inner class
private sealed class SourceClass permits SourceClass.NestedStaticClass, SourceClass.MemberInnerClass {
    // Per condition: source contains target class field
    TargetClass targetField = new TargetClass();

    // Member inner class (source_class feature)
    class MemberInnerClass {
        // Method to be moved: instance, return TargetClass type, protected, position source_inner
        protected TargetClass moveMethod() throws Exception {
            // Variable call feature
            int index = 0;
            // Depends on inner class feature
            NestedHelper nestedHelper = new NestedHelper();
            
            // For statement feature
            for (; index < 5; index++) {
                // Constructor invocation feature
                NestedHelper helper = new NestedHelper(index);
                // Array initialization with inner class method call (nested method pos)
                Object[] array = { helper.protectedInstanceMethod() };
            }

            // Requires throws feature (declares throws, no new exception but requires declaration)
            if (nestedHelper.getValue() < 0) {
                throw new Exception("Invalid value");
            }

            return targetField.getInnerRecClass();
        }

        // Nested instance method (type: instance, modifier: protected, method_feature: 1, inner_class, instance, super.methodName())
        protected Object protectedInstanceMethod() {
            // Super method call feature
            super.toString();
            // Inner class instance usage
            NestedHelper helper = new NestedHelper(1); // "1" feature
            // Instance method call
            return helper.getValue();
        }

        // Local inner class for depends_on_inner_class
        class NestedHelper {
            private int value;

            public NestedHelper() {
                this(0);
            }

            public NestedHelper(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }
    }

    // Static nested class (source_class feature)
    static class NestedStaticClass {}
}

// Target class: normal, public modifier, same package, target_feature: member inner class
public class TargetClass {
    // Member inner class (target_feature)
    class TargetInnerRecClass {
        private String data;

        public TargetInnerRecClass() {
            this.data = "target-inner-data";
        }
    }

    public TargetClass getInnerRecClass() {
        return new TargetClass();
    }
}