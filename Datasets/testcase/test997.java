import java.util.ArrayList;
import java.util.List;

// Source class: abstract, default modifier, same package, type parameter + two static nested classes
abstract class SourceClass<T> {
    private int thisField = 1; // Matches "this.field" and "1" in AssertStatement feature

    // First static nested class
    static class StaticNestedClass1 {}
    // Second static nested class
    static class StaticNestedClass2 {}

    public void outerMethod() {
        // Inner class with recursive structure (source_inner_rec)
        class SourceInnerRecursive {
            // Method: instance, List<String> return, final access, contains target parameter
            final List<String> moveCandidateMethod(FinalTargetClass<T> targetParam) {
                super(); // Super constructor invocation

                // AssertStatement (private modifier, this.field, 1, pos: source)
                privateAssertBlock: {
                    assert SourceClass.this.thisField == 1 : "this.field must be 1";
                }

                List<String> result = new ArrayList<>();
                try {
                    if (targetParam == null) {
                        throw new NullPointerException("Target parameter is null"); // NullPointerException feature
                    }

                    // Variable call (target's member inner class)
                    FinalTargetClass<T>.MemberInnerClass inner = targetParam.new MemberInnerClass();
                    String value = inner.getValue();

                    // Break statement
                    for (int i = 0; i < 5; i++) {
                        if (i == thisField) {
                            break;
                        }
                        result.add(value + i);
                    }
                } catch (NullPointerException e) {
                    // No new exception thrown
                    result.add("handled_null_exception");
                }
                return result;
            }
        }

        // Invoke refactor method with target parameter
        new SourceInnerRecursive().moveCandidateMethod(new FinalTargetClass<>());
    }

    // Abstract method required for abstract class
    public abstract T abstractMethod();
}

// Target class: abstract, final modifier, extends + member inner class
final abstract class FinalTargetClass<U> extends BaseClass {
    // Member inner class (target_feature)
    class MemberInnerClass {
        U getValue() {
            return (U) "target_value";
        }
    }

    // Abstract method required for abstract class
    public abstract void targetAbstractMethod();
}

// Base class for "extends" target_feature
class BaseClass {
    protected String baseField = "base_value";

    public static String baseStaticMethod(int num) {
        return String.valueOf(num);
    }
}

// Others class for call_method
class OtherClass {
    // Call method: protected, others_class type, static, return TargetClass Type, Lambda pos
    protected static FinalTargetClass<String> callMethod() {
        // Lambda expressions position + superTypeReference.methodName(arguments)
        return ((param) -> {
            String superTypeVal = BaseClass.baseStaticMethod(1); // Super type reference
            FinalTargetClass<String> target = new FinalTargetClass<String>() {
                @Override
                public void targetAbstractMethod() {}

                @Override
                public String abstractMethod() {
                    return superTypeVal;
                }
            };
            return target;
        }).apply(1);
    }
}