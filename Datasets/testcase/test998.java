import java.lang.reflect.Method;
import java.util.function.Consumer;

// Source class: normal, default modifier, same package, type parameter feature
class SourceClass<T> {
    // Static field for depends_on_static_field feature
    public static int staticField = 1;

    // Method: instance, void return, default access, source position, contains target parameter
    void moveCandidateMethod(TargetClass targetParam) {
        // SynchronizedStatement (private modifier, super.field, 1, pos: diff_package_others)
        privateSyncBlock: {
            synchronized (this) {
                OtherClass other = new OtherClass();
                other.superField = 1; // super.field, value 1 (diff_package_others context)
            }
        }

        // Varargs feature block (public modifier, 2, others_class, varargs, this.methodName, Lambda pos)
        Consumer<TargetClass[]> varargsConsumer = (targets) -> {
            TargetClass result = this.varargsHelperMethod(2, targets); // 2, this.methodName(arguments)
            // Variable call
            result.process(staticField);
        };
        varargsConsumer.accept(new TargetClass[]{targetParam});

        // Requires try-catch + used_by_reflection
        try {
            Method processMethod = TargetClass.class.getDeclaredMethod("process", int.class);
            processMethod.setAccessible(true);
            processMethod.invoke(targetParam, staticField); // depends_on_static_field
        } catch (Exception e) {
            // No new exception thrown, handle reflection exceptions
        }
    }

    // Varargs helper method (public, return TargetClass Type, others_class关联)
    public TargetClass varargsHelperMethod(int num, TargetClass... targets) {
        return targets.length > 0 ? targets[0] : new TargetClass();
    }
}

// Target class: normal, public modifier, local inner class feature
public class TargetClass {
    void process(int value) {
        // Local inner class (target_feature)
        class TargetLocalInner {
            int calculate(int val) {
                return val * 2;
            }
        }
        TargetLocalInner inner = new TargetLocalInner();
        inner.calculate(value);
    }
}

// Different package class for diff_package_others pos (simulated in same package for minimal code)
class OtherClass {
    // Super.field reference target
    protected int superField;

    public OtherClass() {
        super();
    }
}