// Different package for LabeledStatement diff_package_target pos
package diffpkg;
import refactoring.test.TargetAbstractClass;

public class LabelHelperClass {
    // For LabeledStatement target_feature: ClassName.field + 1
    public static int staticField = 1;

    // LabeledStatement logic (diff_package_target pos)
    public static <T> void labeledLogic(TargetAbstractClass<T> target) {
        // LabeledStatement: static modifier, ClassName.field + 1
        label: {
            TargetAbstractClass<T>.TargetInner inner = target.new TargetInner();
            inner.value = (T) "labeled_" + LabelHelperClass.staticField; // ClassName.field + 1
            break label;
        }
    }
}

// Back to test package
package refactoring.test;

import diffpkg.LabelHelperClass;
import java.util.Arrays;

// Sealed interface for non-sealed abstract class
sealed interface SourceSealedInterface permits SourceAbstractClass {}

// Source abstract class: non-sealed modifier, same package, member inner + static nested classes
non-sealed abstract class SourceAbstractClass<T extends CharSequence> implements SourceSealedInterface {
    // Source contains target field (per_condition)
    private TargetAbstractClass<String> targetField = new TargetAbstractClass<String>() {
        @Override
        public String getDefaultValue() {
            return "source_init";
        }
    };

    // Static nested class (source feature)
    public static class SourceStaticNested {
        int nestedValue = 2; // For generic method's 2 (method_feature)
    }

    // Member inner class (method_position: source_inner)
    public class SourceInnerClass {
        // Accessor method to be refactored (all specified features)
        public Object refactorMethod() {
            // Variable call (target class field)
            targetField.value = "refactor_value";
            // Access target inner class
            TargetAbstractClass<String>.TargetInner inner = targetField.new TargetInner();
            inner.value = "inner_refactor";

            // LabeledStatement: diff_package_target pos (call diff pkg helper)
            LabelHelperClass.labeledLogic(targetField);

            // Super keywords usage
            SourceInnerClass.super.getClass();

            // Array initialization with generic method call (pos: array initialization)
            String[] arr = new String[2];
            int genericResult = genericMethod(arr, targetField); // 2/source/generic/obj.m1().m2().m3()

            // Object initialization with call_method invocation (pos: object initialization)
            TargetAbstractClass<String> callResult = targetField.callMethod(inner);

            // No new exception, return Object
            return callResult;
        }

        // Generic method (type:generic, private modifier, method_feature:2/source/generic/obj.m1().m2().m3())
        private <U> int genericMethod(U[] arr, TargetAbstractClass<U> target) { // generic feature
            // obj.m1().m2().m3() chain + source + 2
            int result = target.getInner() // m1()
                              .getValueLength() // m2()
                              .intValue() + SourceStaticNested.nestedValue; // m3() + 2 (method_feature)
            Arrays.fill(arr, (U) ("generic_" + result));
            return result;
        }
    }

    // Abstract method (required for abstract class)
    public abstract T getSourceData();
}

// Target abstract class: abstract modifier, member inner class feature
abstract class TargetAbstractClass<T> {
    T value;

    // Member inner class (target_feature)
    public class TargetInner {
        T value;

        // For obj.m1().m2().m3() chain
        public Integer getValueLength() {
            return value.toString().length();
        }

        public T getValue() {
            return value;
        }
    }

    // Accessor for inner class (obj.m1() in generic method)
    public TargetInner getInner() {
        return new TargetInner();
    }

    // Abstract method (required for abstract class)
    public abstract T getDefaultValue();

    // Call_method: target type, public modifier, instance type, super.methodName(), object initialization pos, TargetClass return
    public TargetAbstractClass<T> callMethod(TargetInner inner) {
        // super.methodName(arguments) (call super class method)
        super.toString();
        // Instance feature
        this.value = inner.value;
        // Object initialization pos return
        return this;
    }
}