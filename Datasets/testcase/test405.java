import java.util.List;

// Source class: default modifier, abstract, same package as target, two member inner classes
abstract class SourceClass {
    static int staticField = 42; // Static field for method dependency

    // First member inner class
    class FirstMemberInner {
        public Object getValue() { // Public accessor with Object return type
            return staticField;
        }
    }

    // Second member inner class (fulfills "member inner class" twice)
    class SecondMemberInner {}

    // Target method: varargs, void return, protected, in source class
    protected void processData(TargetClass.InnerLocalClass... params) {
        // Override violation: method with same signature as super but incompatible (if inherited)
        // Variable call to target parameter
        Object value = (params.length > 0) ? 
            new SourceClass().new FirstMemberInner().getValue() : // Ternary operator with outerInstance.new InnerClass().methodName()
            null;
        
        // Access static field (depends_on_static_field)
        int data = staticField + (params[0] != null ? params[0].innerField : 0);
        
        // No new exception thrown (fulfills no_new_exception)
        System.out.println(value + " : " + data);
    }
}

// Target class: abstract, private modifier, implements interface, has local inner class
private abstract class TargetClass implements Runnable {
    int innerField = 10;

    @Override
    public void run() {} // Implements Runnable method

    public void outerMethod() {
        // Local inner class (fulfills target_feature "local inner class")
        class InnerLocalClass {
            int innerField = 5;
        }
    }

    // Target inner class for method move (target_inner)
    class InnerLocalClass {
        int innerField = 5;
    }
}