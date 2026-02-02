// Source normal class (default modifier, same package, static nested class, local inner class)
class SourceClass {
    // per_condition: source contains the field of the target
    private final AbstractTargetClass targetField = new ConcreteTargetClass();
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "source_protected_5808";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {
        protected abstract void abstractHelperMethod(); // abstract method for feature
    }

    // Inner class (method_position: source_inner)
    class InnerSourceClass {
        private String innerVar;

        /**
         * Method Javadoc: Overloading method with void return, default access
         * @param param Target class parameter
         */
        // Overloading method 1 (overloading type, void return, default access)
        void targetMethod(AbstractTargetClass param) {
            // Empty statement
            ;

            // this.var = var feature
            this.innerVar = param.getValue();
            this.innerVar = this.innerVar + "_updated"; // this.var = var

            // Access outer protected field
            String protectedVal = SourceClass.this.outerProtectedField; // access_outer_protected

            // Variable call
            param.setValue(this.innerVar + "_" + protectedVal);

            // Local inner class (source_class feature)
            class LocalInnerClass {
                // Abstract method call in constructor parameter list (pos: the parameter list of constructors)
                LocalInnerClass() {
                    new StaticNestedSourceClass() {
                        @Override
                        protected void abstractHelperMethod() {
                            // OuterClass.InnerClass.methodName() (3, source, abstract)
                            SourceClass.InnerSourceClass.this.targetMethod(3, param); 
                        }
                    }.abstractHelperMethod();
                }
            }
            new LocalInnerClass();

            // No new exception
            System.out.println(param.getValue());
        }

        /**
         * Method Javadoc: Overloading method (int + target param)
         * @param num Numeric parameter (3 for method_feature)
         * @param param Target class parameter
         */
        // Overloading method 2 (overloading feature)
        void targetMethod(int num, AbstractTargetClass param) {
            param.setValue(param.getValue() + "_num_" + num);
        }
    }
}

// Target abstract class (abstract modifier, local inner class target_feature)
abstract class AbstractTargetClass {
    private String value = "target_value_5808";

    // Local inner class (target_feature) - defined in abstract method implementation
    @Override
    public String toString() {
        class TargetLocalInnerClass { // local inner class feature
            String getFormattedValue() {
                return value.toUpperCase();
            }
        }
        return new TargetLocalInnerClass().getFormattedValue();
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Abstract method (required for abstract target class)
    public abstract void abstractTargetMethod();
}

// Concrete implementation of target abstract class (for compilation)
class ConcreteTargetClass extends AbstractTargetClass {
    @Override
    public void abstractTargetMethod() {}
}