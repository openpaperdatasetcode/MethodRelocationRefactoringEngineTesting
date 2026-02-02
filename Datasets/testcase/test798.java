import java.util.List;

// Annotation for has_annotation feature
@interface OverloadAnnotation {}

// Source class: generic, public modifier, same package, static nested + local inner class
public class SourceClass<T extends Number> {
    // Satisfy per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>("targetValue");

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass<U> {
        public U processValue(U value) {
            return value;
        }
    }

    // Overloading method 1 (type: overloading), void return, private access, source position
    @OverloadAnnotation // has_annotation feature
    private void overloadedMethod(TargetClass<String> targetParam) {
        // Variable call feature
        T localVar = (T) Integer.valueOf(10);
        String var = "overload1";

        // Super keywords feature
        super.toString();

        // Raw type feature
        TargetClass rawTarget; // Raw type declaration (no generic)
        rawTarget = new TargetClass<>("rawValue");

        // No new exception feature (no 'new Exception()' statements)
        System.out.println(var + ": " + localVar + ", " + targetParam.getValue());
    }

    // Overloading method 2 (overload variant)
    @OverloadAnnotation
    private void overloadedMethod(TargetClass<String> targetParam, T num) {
        // Variable call feature
        var = "overload2"; // Variable reuse/call
        super.hashCode(); // Super keywords feature

        // Raw type usage
        SourceStaticNestedClass rawNested = new SourceStaticNestedClass(); // Raw type for static nested class

        // No new exception feature
        System.out.println(var + ": " + num + ", " + targetParam.getValue());
    }

    // Method with local inner class (source_class feature)
    public void methodWithLocalInner() {
        // Local inner class
        class LocalInnerClass<V> {
            public void innerMethod(TargetClass<V> target) {
                System.out.println(target.getValue());
            }
        }
        LocalInnerClass<String> inner = new LocalInnerClass<>();
        inner.innerMethod(targetField);
    }
}

// Target class: generic, default modifier, no additional features
class TargetClass<V> {
    private V value;

    public TargetClass(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    // Target class for method relocation
    public static class target {
        // Placeholder for overloaded methods
        private void overloadedMethod(TargetClass<String> targetParam) {
            SourceClass<Integer> source = new SourceClass<>();
            source.overloadedMethod(targetParam);
        }

        private void overloadedMethod(TargetClass<String> targetParam, Integer num) {
            SourceClass<Integer> source = new SourceClass<>();
            source.overloadedMethod(targetParam, num);
        }
    }
}