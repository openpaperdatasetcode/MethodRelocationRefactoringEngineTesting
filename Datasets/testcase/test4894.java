import java.util.function.Supplier;

interface ExampleInterface {}

/**
 * Target record class with javadoc, implements interface and anonymous inner class
 */
record TargetRecord(String name, int value) implements ExampleInterface {
    // Anonymous inner class
    Supplier<Object> supplier = new Supplier<>() {
        @Override
        public Object get() {
            return new HelperClass().helperMethod();
        }
    };

    // Inner class containing target for method move
    public class TargetInner {
        private String data;
    }
}

// Helper class with overriding method
class HelperClass {
    public void helperMethod() {}
}

class OtherClass extends HelperClass {
    // Overriding method
    @Override
    public final void helperMethod() {
        System.out.println("Overridden helper method");
    }
}

strictfp record SourceRecord(TargetRecord target, TargetRecord.TargetInner targetInner) {
    /**
     * Method to be moved with javadoc
     * @return base type (int) result
     */
    public int methodToMove() {
        // Type declaration statement
        OtherClass other = new OtherClass();
        
        // Object initialization with method chain: obj.m1().m2().m3()
        TargetRecord temp = new TargetRecord("temp", 0);
        Object result = temp.targetInner().toString().hashCode();
        
        // Variable call
        int val = target.value();
        
        // Functional interface usage with call to others_class method
        Supplier<Void> func = () -> {
            TargetRecord.InnerClass.methodName();
            return null;
        };
        func.get();
        
        return val + 10;
    }
}
    