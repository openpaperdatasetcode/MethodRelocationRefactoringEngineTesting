import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Source class: normal, default modifier, same package, two member inner classes
class SourceClass {
    // Per condition: source contains the field of the target (target_inner)
    private TargetClass.StaticNestedClass targetInnerField;

    // First member inner class
    class MemberInnerClass1 {
        int processValue(int val) {
            return val * 2;
        }
    }

    // Second member inner class
    class MemberInnerClass2 {
        void updateTarget(TargetClass.StaticNestedClass inner) {
            inner.value = 10;
        }
    }

    // Method: instance, base type return (int), final access, no type parameters
    final int moveCandidateMethod() {
        super(); // Super constructor invocation
        // Constructor invocation (target inner class)
        targetInnerField = new TargetClass.StaticNestedClass();
        // this.var = var
        this.targetInnerField.value = 5;
        ; // Empty statement

        // With bounds (type parameter bounds for inner class)
        class BoundedInner<T extends Number> {
            int calculate(T num) {
                return num.intValue() + targetInnerField.value;
            }
        }

        // Variable call + depends_on_inner_class
        MemberInnerClass1 inner1 = new MemberInnerClass1();
        MemberInnerClass2 inner2 = new MemberInnerClass2();
        int varCall = inner1.processValue(targetInnerField.value);
        inner2.updateTarget(targetInnerField); // Depends on inner class

        // Used by reflection
        try {
            Field valueField = TargetClass.StaticNestedClass.class.getDeclaredField("value");
            valueField.setAccessible(true);
            valueField.set(targetInnerField, varCall);

            Method calcMethod = TargetClass.StaticNestedClass.class.getDeclaredMethod("calculate");
            calcMethod.setAccessible(true);
            varCall = (int) calcMethod.invoke(targetInnerField);
        } catch (Exception e) {
            // No new exception thrown
            varCall = 0;
        }

        return varCall; // Base type return
    }
}

// Target class: normal, abstract modifier, static nested class feature
abstract class TargetClass {
    // Static nested class (target_feature) - target_inner
    static class StaticNestedClass {
        int value;

        int calculate() {
            return value * 3;
        }
    }

    // Abstract method required for abstract class
    public abstract void abstractMethod();
}