import java.io.IOException;

// Parent record for sealed source record extends feature
non-sealed record ParentRecord(String parentValue) {}

// Source sealed record class (sealed modifier, same package as target, extends ParentRecord)
sealed record SourceRecord(String sourceValue, TargetRecord<String> targetParam) extends ParentRecord permits SourceRecord.InnerClass {
    // Super constructor invocation (record canonical constructor)
    public SourceRecord {
        super(parentValue); // Super constructor invocation
    }

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public static int getStaticValue() {
            return 3; // Matches "3" in static method_feature
        }
    }

    // Member inner class (for source_inner_rec)
    public class InnerClass {
        // Recursive inner class (source_inner_rec position)
        public class RecursiveInnerClass {
            // Instance method to be moved (private, returns TargetRecord type, source_inner_rec)
            private TargetRecord<String> processTarget(TargetRecord<String> targetParam) throws IOException {
                // Per_condition: method contains target parameter
                if (targetParam == null) {
                    // IOException (no_new_exception: no explicit new Exception())
                    throw new IOException("Target parameter cannot be null");
                }

                // VariableDeclarationStatement (public modifier, pos: source, target_feature: obj.field, 1)
                public int localVar = 1; // target_feature: 1
                localVar = targetParam.value().length(); // target_feature: obj.field

                // Static method with specified features (pos: instance code blocks, return TargetRecord type)
                private static TargetRecord<String> staticHelper() {
                    int val = SourceStaticNestedClass.getStaticValue(); // method_feature: 3 + source
                    // method_feature: static + obj.m1().m2().m3()
                    String chainValue = new SourceStaticNestedClass()
                        .toString() // m1()
                        .toLowerCase() // m2()
                        .substring(0, val); // m3()
                    return new TargetRecord<>(chainValue);
                }

                // Instance code block (pos for static method)
                {
                    TargetRecord<String> staticResult = staticHelper();
                    targetParam = staticResult;
                }

                // For statement
                for (int i = 0; i < localVar; i++) {
                    // Expression statement
                    targetParam.TargetStaticNestedClass.updateCounter(i);
                    // Variable call
                    targetParam.setCounter(targetParam.getCounter() + 1);
                }

                // Lambda expression: () -> System.out.println(this.value)
                Runnable lambda = () -> System.out.println(this.targetParam.value());
                lambda.run();

                // Depends_on_inner_class: use inner class for variable call
                TargetRecord<String>.TargetStaticNestedClass nested = targetParam.new TargetStaticNestedClass();
                nested.setData("Processed: " + localVar);

                return targetParam;
            }
        }
    }

    // Local inner class (source_class feature)
    public void methodWithLocalInner() {
        class LocalInnerClass {
            void printSourceData() {
                System.out.println(SourceRecord.this.sourceValue);
            }
        }
        new LocalInnerClass().printSourceData();
    }
}

// Target public generic record class (type parameter, static nested class)
public record TargetRecord<T>(T value) {
    // Static nested class (target_feature)
    public static class TargetStaticNestedClass {
        private static int counter = 0;
        private String data;

        public static void updateCounter(int val) {
            counter += val;
        }

        public static int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            TargetStaticNestedClass.counter = counter;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}