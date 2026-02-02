import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Source class: normal, public modifier, same package, member inner + local inner classes
public class SourceClass {
    // Member inner class (source feature)
    class SourceMemberInner {
        int instanceField = 10; // For access_instance_field feature
    }

    // Method: instance, void return, protected access, contains target parameter
    protected void moveCandidateMethod(TargetClass targetParam) {
        super(); // Super constructor invocation

        // Local inner class (source feature)
        class SourceLocalInner {
            void processTarget(TargetClass target) {
                // Access instance field
                SourceMemberInner inner = new SourceMemberInner();
                int fieldVal = inner.instanceField;

                // Lambda expression: () -> System.out.println(this.value)
                Runnable lambda = () -> System.out.println(this.instanceField);
                lambda.run();

                // Variable call (target inner recursive context)
                target.AnonymousInterface impl = target.new AnonymousInnerClass();
                impl.setValue(fieldVal);
                int varCall = impl.getValue();

                // Used by reflection
                try {
                    Field valueField = TargetClass.AnonymousInnerClass.class.getDeclaredField("value");
                    valueField.setAccessible(true);
                    valueField.set(impl, varCall * 2);

                    Method getMethod = TargetClass.AnonymousInterface.class.getDeclaredMethod("getValue");
                    getMethod.setAccessible(true);
                    getMethod.invoke(impl);
                } catch (Exception e) {
                    // No new exception thrown
                }
            }
        }

        // Invoke local inner class method with target parameter
        new SourceLocalInner().processTarget(targetParam);
    }
}

// Target class: normal, private modifier, implements + anonymous inner class features
private class TargetClass implements TargetInterface {
    // Implements feature (target_feature)
    interface AnonymousInterface {
        void setValue(int val);
        int getValue();
    }

    // Anonymous inner class (target_feature) - target_inner_rec (recursive inner context)
    AnonymousInnerClass AnonymousInnerClass = new AnonymousInnerClass();
    class AnonymousInnerClass implements AnonymousInterface {
        int value;

        @Override
        public void setValue(int val) {
            this.value = val;
        }

        @Override
        public int getValue() {
            return this.value;
        }
    }

    // Anonymous inner class implementation (target_feature)
    TargetInterface anonymousImpl = new TargetInterface() {
        @Override
        public void execute() {
            System.out.println(AnonymousInnerClass.value);
        }
    };

    @Override
    public void execute() {
        anonymousImpl.execute();
    }
}

// Interface for target class "implements" feature
interface TargetInterface {
    void execute();
}