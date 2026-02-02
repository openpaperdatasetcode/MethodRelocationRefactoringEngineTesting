import java.lang.reflect.Method;
import java.util.function.Function;

// Source class: generic, public modifier, same package, static nested + local inner classes
public class SourceClass<T> extends BaseSourceClass<T> {
    // Static nested class (source feature)
    static class SourceStaticNested<U extends Number> { // with_bounds
        // Overload methods for overload_exist feature
        int process(TargetClass<U> target) {
            return target.getValue();
        }

        int process(TargetClass<U> target, int multiplier) {
            return target.getValue() * multiplier;
        }
    }

    // Static code block (pos for instance method feature)
    static {
        SourceSubClass<String> subClass = new SourceSubClass<>();
        subClass.instanceHelper(new TargetClass<>()); // super.methodName() context
    }

    @Override
    public int moveCandidateMethod(TargetClass<T> targetParam) { // overriding (extends BaseSourceClass)
        super(); // Super constructor invocation

        // Local inner class (source feature) + source_inner position
        class SourceInnerClass {
            // Instance method feature (private, 1, sub_class, super.methodName(), static code blocks pos, return TargetClass Type)
            private TargetClass<T> instanceHelper(TargetClass<T> target) {
                // super.methodName()
                super.processSuper();
                return target; // Return TargetClass Type
            }

            // Synchronized statement
            synchronized void syncProcess(TargetClass<T> target) {
                // ConditionalExpression (numbers:1, public modifier, exp:ConditionalExpression)
                publicConditionalBlock: {
                    int val = (target.getValue() == 1) ? 1 : 0; // Number 1, conditional expression
                    // Variable call
                    SourceStaticNested<T> nested = new SourceStaticNested<>();
                    int varCall = nested.process(target);
                    // Overload_exist: call overloaded method
                    int overloadedCall = nested.process(target, 1);
                    // Access instance method
                    target.processInstance(val);
                }
            }
        }

        SourceInnerClass inner = new SourceInnerClass();
        // Invoke inner class methods
        inner.syncProcess(targetParam);
        TargetClass<T> target = inner.instanceHelper(targetParam);

        // Used by reflection
        try {
            Method processMethod = SourceStaticNested.class.getDeclaredMethod("process", TargetClass.class);
            processMethod.setAccessible(true);
            processMethod.invoke(new SourceStaticNested<>(), targetParam);
        } catch (Exception e) {
            // No new exception thrown
        }

        // Return base type (int)
        return targetParam.getValue();
    }

    // Call method: inner_class type, default modifier, instance + super.methodName(), exception handling pos, void return
    class SourceCallInnerClass {
        void callMethod(TargetClass<T> target) {
            try {
                // Exception handling statements pos
                // instance + super.methodName()
                SourceClass.super.processSuper();
                moveCandidateMethod(target); // Instance method call
            } catch (Exception e) {
                // No new exception thrown
            }
        }
    }
}

// Base class for overriding and super.methodName()
abstract class BaseSourceClass<T> {
    public abstract int moveCandidateMethod(TargetClass<T> targetParam);

    protected void processSuper() {
        // Super method for super.methodName() feature
    }
}

// Subclass for sub_class feature in method_feature
class SourceSubClass<T extends CharSequence> extends SourceClass<T> { // with_bounds
    @Override
    public int moveCandidateMethod(TargetClass<T> targetParam) {
        return super.moveCandidateMethod(targetParam);
    }
}

// Target class: generic, default modifier, local inner class feature
class TargetClass<U> {
    private int value = 1;

    int getValue() {
        return value;
    }

    void processInstance(int val) {
        // Local inner class (target_feature)
        class TargetLocalInner {
            void updateValue(int newVal) {
                value = newVal;
            }
        }
        new TargetLocalInner().updateValue(val);
    }
}