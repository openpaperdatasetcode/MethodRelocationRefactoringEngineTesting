import java.util.List;
import java.util.ArrayList;

interface ExampleInterface {}

/**
 * Target record class with local inner class
 */
strictfp record TargetClass(String data, int value) {
    // Local inner class in constructor
    public TargetClass {
        class LocalInner {
            void process() {
                System.out.println("Processing: " + data);
            }
        }
        new LocalInner().process();
    }
}

strictfp record SourceClass(int id, TargetClass target) implements ExampleInterface {
    // Member inner class
    protected class MemberInner {
        String getInfo() {
            return "MemberInner: " + id;
        }
    }

    // Anonymous inner class in initializer
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner: " + id);
        }
    };

    private String outerPrivateField = "private data";

    /**
     * Method to be moved with varargs
     * @param args variable arguments
     */
    protected void methodToMove(String... args) {
        // Uses outer this
        MemberInner inner = this.new MemberInner();
        System.out.println(inner.getInfo());
        
        // Access outer private
        synchronized (outerPrivateField) {
            // Variable call
            List<String> list = new ArrayList<>();
            list.add(outerPrivateField);
            
            // Constructor invocation with target class field access
            TargetClass newTarget = new TargetClass(TargetClass.this.data, 2);
            TargetClass anotherTarget = new TargetClass(TargetClass.this.data, 3);
            
            for (String arg : args) {
                list.add(arg);
            }
        }
        anonymousInner.run();
    }
}
