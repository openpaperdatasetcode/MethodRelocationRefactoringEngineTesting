import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Consumer;

// Source class: generic, public modifier, same package, extends + static nested + local inner
public class SourceClass<T extends Number> extends BaseClass {
    // Per condition: source contains the field of the target (target_inner_rec)
    private TargetClass<T>.StaticNestedClass targetInnerField;

    // Static nested class (source feature)
    static class SourceStaticNested {
        static void helper() {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface RefactorAnnotation {}

    // Method: varargs, base type return (int), default access, source position
    @RefactorAnnotation // has_annotation
    int moveCandidateMethod(TargetClass<T>... targetParams) {
        // Local inner class (source feature)
        class SourceLocalInner {
            // Instance method feature (private, 2, others_class, instanceReference::methodName, property assignment pos)
            private void instanceHelper(Consumer<Integer> consumer) {
                consumer.accept(2); // Matches "2" in method_feature
            }
        }

        SourceLocalInner localInner = new SourceLocalInner();
        // Property assignment position + instanceReference::methodName
        targetInnerField = new TargetClass<T>.StaticNestedClass();
        targetInnerField.consumer = localInner::instanceHelper; // others_class关联的方法引用

        int total = 0;
        // Try statement
        try {
            for (TargetClass<T> target : targetParams) {
                // Variable call
                targetInnerField = target.new StaticNestedClass();
                total += targetInnerField.calculate(2);
                // Return statement (early return for demonstration)
                if (total > 10) return total;
            }
        } catch (NullPointerException e) {
            // No new exception thrown
            total = 0;
        }
        return total; // Base type return
    }
}

// Base class for "extends" source feature
class BaseClass {
    protected String baseField = "base";
}

// Target class: generic, protected modifier, static nested class feature (target_inner_rec)
protected class TargetClass<U> {
    // Static nested class (target_feature) - target_inner_rec
    static class StaticNestedClass {
        int value;
        Consumer<Integer> consumer;

        int calculate(int num) {
            consumer.accept(num);
            return value + num * 2;
        }
    }
}

// Others class for method_feature "others_class"
class OtherClass {
    public static <U> void process(TargetClass<U>.StaticNestedClass inner) {
        inner.consumer.accept(2);
    }
}