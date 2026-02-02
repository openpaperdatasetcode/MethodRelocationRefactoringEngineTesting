import java.io.IOException;

// Source generic class: private modifier, same package, permits/anonymous inner/static nested class
private sealed class SourceClass<T extends CharSequence> permits SourceClass.SourceStaticNestedClass {
    // Static nested class (fulfills static nested class feature)
    static final class SourceStaticNestedClass extends SourceClass<String> {
        SourceStaticNestedClass() {
            super(); // Super constructor invocation
        }
    }

    // Target method: static, void return, protected, in source class
    protected static <U extends Number & Comparable<U>> void processData(TargetClass<U> param) {
        // With bounds (U extends Number & Comparable<U>)
        if (param != null) { // If statement
            try {
                // Requires try-catch (simulated IOException risk)
                if (param.getTargetField().intValue() > 10) {
                    throw new IOException();
                }
                // Variable call to target parameter
                U value = param.getTargetField();
                // Uses outer this (via anonymous inner class)
                SourceClass<String> instance = new SourceClass<>() {
                    @Override
                    void dummyMethod() {
                        SourceClass.this.toString(); // Outer this usage
                    }
                };
                System.out.println(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Dummy method for anonymous inner class implementation
    void dummyMethod() {}

    // Super constructor invocation (default constructor)
    SourceClass() {
        super();
    }
}

// Target generic class: private modifier, extends/static nested class
private class TargetClass<V extends Number> extends SourceClass<String> {
    // Static nested class (fulfills target_feature)
    static class TargetStaticNestedClass {}

    private V targetField;

    // Super constructor invocation (inherited from SourceClass)
    TargetClass() {
        super();
    }

    // Variable call accessor
    V getTargetField() {
        return targetField;
    }

    void setTargetField(V targetField) {
        this.targetField = targetField;
    }
}