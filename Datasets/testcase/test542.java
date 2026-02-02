import java.io.IOException;

// Abstract source generic class (public modifier, type parameter feature)
public abstract class SourceClass<T extends CharSequence> {
    // Protected field for access_outer_protected feature
    protected String protectedOuterField = "ProtectedOuterData";

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public static <U> U process(U data) {
            return data;
        }
    }

    // Anonymous inner class (source_class feature)
    private Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Instance method to be moved (private, void return, source position)
    private void processTarget(AbstractTargetClass targetParam) throws IOException {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            throw new IOException("Target parameter cannot be null"); // requires_throws
        }

        // Access outer protected field (access_outer_protected)
        String outerProtected = SourceClass.this.protectedOuterField;
        // Access instance field of target class
        targetParam.instanceField = outerProtected; // access_instance_field

        // Depends on inner class (target's anonymous inner class)
        targetParam.targetAnonymous.run(); // depends_on_inner_class

        // Variable call to target class methods
        targetParam.setData("Processed: " + targetParam.instanceField);
        targetParam.processData();

        // Use static nested class to process target data (additional variable call)
        String processed = SourceStaticNestedClass.process(targetParam.getData());
        targetParam.instanceField = processed;
    }

    // Abstract method (required for abstract class)
    public abstract T getGenericData();
}

// Abstract target class (abstract modifier, same package as source)
abstract class AbstractTargetClass {
    // Instance field for access_instance_field feature
    public String instanceField;
    private String data;

    // Anonymous inner class (target_feature)
    public Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + instanceField);
        }
    };

    // Variable call methods
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void processData() {
        this.data = this.data + "_processed_by_target";
    }

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}