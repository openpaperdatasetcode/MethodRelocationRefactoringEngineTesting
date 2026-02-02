package refactoring.test;

// Source class: record, public modifier, same package as target, features: type parameter, anonymous inner class, static nested class
public record SourceRecord<T extends CharSequence>(T data, int id) {
    // Static nested class (source_class feature)
    public static class SourceStaticNested {
        static int staticField = 42; // For depends_on_static_field feature
    }

    /**
     * Javadoc for overloading method (method javadoc feature)
     * @param targetParam target record parameter (per_condition)
     * @return int base type result
     */
    // Method to be refactored: overloading, return base type (int), private access, position source
    // Per condition: method contains target class parameter
    private int moveMethod(TargetRecord targetParam) {
        // Variable call feature
        int counter = 0;
        // Depends on static field feature
        int staticVal = SourceStaticNested.staticField;

        // For statement feature
        for (; counter < 5; counter++) {
            // Super constructor invocation (in anonymous inner class)
            Runnable runnable = new Runnable() {
                {
                    super(); // Super constructor invocation feature
                }

                @Override
                public void run() {
                    // Super keywords feature
                    super.toString();
                    // Access instance method feature
                    String processed = SourceRecord.this.processData(data);
                    System.out.println(processed + "-" + counter);
                }
            };
            runnable.run();
        }

        // No new exception thrown (no_new_exception feature)
        return (counter * staticVal) + targetParam.nestedClass().calculate(id);
    }

    // Overload method (overload_exist feature)
    private int moveMethod() {
        // method types parameter is:none feature
        return SourceStaticNested.staticField;
    }

    // Instance method for access_instance_method feature
    private String processData(T data) {
        return data.toString().toUpperCase();
    }
}

// Target class: record, private modifier, same package, target_feature: static nested class
private record TargetRecord(String value, long timestamp) {
    // Static nested class (target_feature)
    static class TargetInnerClass {
        int calculate(int num) {
            return num * 3;
        }
    }

    public TargetInnerClass nestedClass() {
        return new TargetInnerClass();
    }
}