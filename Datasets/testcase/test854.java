package refactoring.test;

// Source class: normal, protected modifier, same package as target, features: member inner class, local inner class
protected class SourceClass {
    // Per condition: source contains target class field
    TargetClass targetField = new TargetClass();

    // Member inner class (source_class feature)
    class SourceInnerClass {
        // Method to be moved: instance, return Object, default access, position source_inner
        Object moveMethod(TargetClass param) { // method types parameter is:Target class
            // Variable call feature
            String localVar = "source-local-var";
            // Constructor invocation feature
            LocalInner localInner = new LocalInner(localVar);
            
            // No new exception thrown (no_new_exception feature)
            String combined = localVar + param.getLocalInnerData();
            return combined;
        }

        // Local inner class (source_class feature)
        class LocalInner {
            private String data;

            public LocalInner(String data) {
                this.data = data;
            }

            public String getData() {
                return data;
            }
        }

        // Final method (call_method: type source, modifier final, return Object)
        final Object finalSourceMethod(String input) {
            return input.toUpperCase();
        }

        // Lambda expression (call_method pos: Lambda expressions, target_feature: ClassName::methodName)
        void lambdaUsage() {
            java.util.function.Function<String, Object> func = SourceInnerClass.this::finalSourceMethod;
            Object result = func.apply("test");
        }
    }
}

// Target class: normal, public modifier, same package, target_feature: local inner class
public class TargetClass {
    public String getLocalInnerData() {
        // Local inner class (target_feature)
        class TargetLocalInner {
            String getTargetData() {
                return "target-local-data";
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        return localInner.getTargetData();
    }
}