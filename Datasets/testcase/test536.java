import java.io.IOException;
import java.util.function.Consumer;

/**
 * Target generic class (protected modifier, same package as source)
 * @param <T> Generic type parameter
 */
protected class TargetClass<T> {
    // Static nested class (target_feature)
    public static class TargetStaticNestedClass {
        private int value;

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void processValue(String data) {
            this.value += data.length();
        }
    }

    private T data;
    private final TargetStaticNestedClass staticNested = new TargetStaticNestedClass();

    // Variable call methods
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TargetStaticNestedClass getStaticNested() {
        return staticNested;
    }
}

// Source generic class (protected modifier, same package as target)
protected class SourceClass<S> {
    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public static <T> void validate(T data) throws IOException {
            if (data == null) {
                throw new IOException("Data cannot be null");
            }
        }
    }

    // Anonymous inner class (source_class feature)
    private Consumer<String> anonymousConsumer = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("Anonymous inner class: " + s);
        }
    };

    // Instance method to be moved (private, base type return, source position)
    private int processTarget(TargetClass<S> targetParam) throws IOException {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            // IOException (no_new_exception: no explicit new Exception())
            throw new IOException("Target parameter cannot be null");
        }

        // Instance method with specified features (private modifier, pos: array initialization, void return)
        private void helperMethod() {
            int val = 2; // method_feature: 2
            // method_feature: target + ClassName::methodName (method reference)
            Consumer<String> processor = TargetClass.TargetStaticNestedClass::processValue;
            
            // pos: array initialization
            String[] dataArray = {
                String.valueOf(val),
                targetParam.getData().toString(),
                String.valueOf(targetParam.getStaticNested().getValue())
            };
            
            // method_feature: instance (instance method call)
            for (String data : dataArray) {
                processor.accept(data);
                anonymousConsumer.accept(data);
            }
        }

        // Invoke helper method (array initialization context)
        helperMethod();

        // Expression statement
        targetParam.getStaticNested().setValue(10);
        // Variable call
        targetParam.getStaticNested().processValue(targetParam.getData().toString());
        
        // Validate via static nested class (expression statement)
        SourceStaticNestedClass.validate(targetParam.getData());

        // Base type return (int)
        return targetParam.getStaticNested().getValue();
    }
}