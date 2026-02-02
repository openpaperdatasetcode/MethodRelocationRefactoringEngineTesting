package refactoring.test;

import java.util.Arrays;
import java.util.List;

// Parent generic class for source enum's extends feature
class ParentEnumClass<T extends CharSequence> {
    protected T outerProtectedField = (T) "protected_value"; // access_outer_protected
}

/**
 * Javadoc for TargetEnum (target_feature: javadoc)
 * Generic enum with type parameter and member inner class
 * @param <T> type parameter (target_feature: type parameter)
 */
public enum TargetEnum<T extends CharSequence> { // type parameter + with_bounds
    VALUE1("one"), VALUE2("two");

    private final T value;
    TargetInnerClass<T> innerClass;

    TargetEnum(T value) {
        this.value = value;
        this.innerClass = new TargetInnerClass<>();
    }

    // Member inner class (target_feature)
    public class TargetInnerClass<S extends CharSequence> { // with_bounds
        S innerValue;
        int counter = 1;
    }

    public T getValue() {
        return value;
    }
}

// Source enum: default modifier, same package, type parameter + extends + double local inner classes
enum SourceEnum<T extends CharSequence> extends ParentEnumClass<T> { // type parameter + extends + with_bounds
    INSTANCE((T) "source");

    private T data;

    SourceEnum(T data) {
        this.data = data;
    }

    // Member inner class for source_inner_rec structure
    public class SourceInnerClass {
        // Recursive inner class (method_position: source_inner_rec)
        public class SourceRecursiveInner {
            /**
             * Varargs method with none parameter type
             * @param targetParam target enum parameter (per_condition)
             * @param none varargs parameter (method types parameter is:none)
             */
            @SuppressWarnings("rawtypes") // has_annotation
            public void refactorMethod(TargetEnum targetParam, String... none) { // varargs type
                // Uses outer this
                SourceEnum outerThis = SourceEnum.this;
                // Access outer protected field
                T protectedVal = outerThis.outerProtectedField;

                // Empty statement
                ;
                // Expression statement
                targetParam.innerClass.innerValue = (T) "expr";
                // ParenthesizedExpression (numbers:1, public modifier, exp:ParenthesizedExpression)
                public int parenthesized = (1); // 1 + ParenthesizedExpression

                // Variable call (target enum field)
                targetParam.innerClass.counter = parenthesized;
                // Enhanced for statement
                for (String s : Arrays.asList(none)) {
                    targetParam.innerClass.innerValue = (T) s;
                }

                // First local inner class (source feature)
                class LocalInnerOne {
                    void updateTarget() {
                        targetParam.innerClass.counter++;
                    }
                }

                // Second local inner class (source feature - duplicate local inner class)
                class LocalInnerTwo {
                    void process() {
                        new LocalInnerOne().updateTarget();
                        // No new exception
                    }
                }

                new LocalInnerTwo().process();
                // No new exception, void return
            }
        }
    }
}