import java.util.function.Consumer;

// Super class for target_class extends feature
class TargetSuperClass<U> {
    protected U superField;

    public TargetSuperClass(U superField) {
        this.superField = superField;
    }
}

// Source abstract normal class (abstract modifier, same package, two local inner classes)
abstract class SourceClass {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "source_protected_5831";
    // per_condition: source contains the field of the target
    private final StrictfpTargetClass<String> targetField = new StrictfpTargetClass<>("init_value_5831", "super_val_5831");

    // Parent method for overriding
    protected int baseMethod(StrictfpTargetClass<String> param) {
        return param.getValue().length();
    }

    // Method to be refactored (overriding, base type return, public access, position: source)
    @Override
    public int baseMethod(StrictfpTargetClass<String> param) { // overriding type
        // Access outer protected field
        String protectedVal = this.outerProtectedField; // access_outer_protected

        // First local inner class (source_class feature)
        class LocalInnerClass1 {
            String process(String val) {
                return val + "_" + protectedVal;
            }
        }

        // Second local inner class (source_class feature)
        class LocalInnerClass2 {
            int calculateLength(String val) {
                return new LocalInnerClass1().process(val).length();
            }
        }

        // Variable call (target class + static nested class)
        param.setValue(new LocalInnerClass2().calculateLength(param.getValue()));
        StrictfpTargetClass.StaticNestedClass staticObj = new StrictfpTargetClass.StaticNestedClass();
        param.getSuperField(); // variable call to super field from extends

        // Call method in constructor parameter list (pos: the parameter list of constructors)
        Consumer<String> func = StrictfpTargetClass::callMethod; // ClassName::methodName
        StrictfpTargetClass<String> newTarget = new StrictfpTargetClass<>(func.accept(param.getValue()), "new_super_val");

        // No new exception
        return param.getValue() + staticObj.getStaticVal().length();
    }

    // Abstract method (required for abstract source class)
    public abstract void abstractSourceMethod();
}

// Target normal class (strictfp modifier, type parameter, extends, static nested class target_feature)
strictfp class StrictfpTargetClass<T> extends TargetSuperClass<T> { // extends feature (target_feature)
    // Type parameter feature (target_feature)
    private T value;

    // Static nested class (target_feature)
    public static class StaticNestedClass {
        private String staticVal = "static_nested_val_5831";

        public String getStaticVal() {
            return staticVal;
        }
    }

    // Constructor
    public StrictfpTargetClass(T value, T superField) {
        super(superField); // super constructor for extends
        this.value = value;
    }

    // Call method (target type, private modifier, normal, ClassName::methodName, return void)
    private static void callMethod(String val) {
        System.out.println("Call method: " + val);
    }

    // Variable call: getter/setter
    public T getValue() {
        return value;
    }

    public void setValue(int length) {
        this.value = (T) String.valueOf(length);
    }

    public T getSuperField() {
        return super.superField; // variable call to super field from extends
    }
}