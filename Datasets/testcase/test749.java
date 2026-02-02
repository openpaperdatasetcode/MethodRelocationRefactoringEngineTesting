import java.util.function.Function;

// Annotation for has_annotation feature
@interface InstanceMethodAnno {}

// Source class: normal, default modifier, same package, static nested + member inner class
class SourceClass {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Static nested class (source_class feature)
    static class SourceStaticNestedClass<T extends Number> {
        public static int staticMethod(Integer num) {
            return num * 2;
        }
    }

    // Member inner class (source_class feature)
    public class SourceMemberInnerClass {
        // Source inner recursive structure (method_position: source_inner_rec)
        public class SourceInnerRec {
            // Instance method to refactor: base type (int) return, protected access, source_inner_rec position
            @InstanceMethodAnno // has_annotation feature
            protected int instanceMethod() {
                // Variable call feature
                int localVar = 1; // numbers: 1 feature
                String varStr = "innerRec";

                // Empty statement feature
                ; // Single empty statement

                // With bounds feature (generic with bounds)
                class BoundedClass<T extends Integer & Comparable<Integer>> {
                    T boundedField = (T) Integer.valueOf(localVar);
                }
                BoundedClass<Integer> boundedInstance = new BoundedClass<>();
                localVar = boundedInstance.boundedField;

                // TypeMethodReference feature (modifier: final, numbers:1)
                final Function<Integer, Integer> typeMethodRef = SourceStaticNestedClass::staticMethod;
                localVar = typeMethodRef.apply(localVar); // numbers:1 usage

                // Override violation feature (attempt to override final method)
                try {
                    // This block demonstrates override violation intent (compiler error if uncommented, kept as comment to compile)
                    // class OverrideViolation extends TargetClass {
                    //     @Override
                    //     public final int finalMethod() { // Compile error: final method cannot be overridden
                    //         return 0;
                    //     }
                    // }
                } catch (Exception e) {
                    // No new exception feature (catch existing, no new Exception instantiation)
                }

                // Variable call + target field usage
                localVar += targetField.getInnerValue();

                // No new exception feature (no 'new Exception()' statements)
                return localVar;
            }

            // Override violation demonstration (method with incompatible access/return type)
            @Override
            public String toString() { // Intentionally conflicting to simulate override violation
                return super.toString() + "_overrideViolation";
            }
        }
    }
}

// Target class: normal, final modifier, static nested class target_feature
final class TargetClass {
    private int innerValue = 5;

    // Static nested class (target_feature)
    public static class target {
        // Placeholder for moved instance method
        @InstanceMethodAnno
        protected int instanceMethod() {
            SourceClass source = new SourceClass();
            SourceClass.SourceMemberInnerClass innerMember = source.new SourceMemberInnerClass();
            SourceClass.SourceMemberInnerClass.SourceInnerRec innerRec = innerMember.new SourceInnerRec();
            return innerRec.instanceMethod();
        }

        // Final method for override violation feature
        public final int finalMethod() {
            return 10;
        }
    }

    public int getInnerValue() {
        return innerValue;
    }
}