import java.lang.reflect.Method;
import java.util.List;

final class SourceClass<T> permits SubSourceClass {
    public void outerMethod() {
        class LocalInnerSourceClass {
            strictfp void sourceInnerMethod(TargetClass<String>... targetParams) {
                TargetClass<String> targetInstance = targetParams[0];
                targetInstance.targetInstanceMethod();
                try {
                    Method method = TargetClass.class.getMethod("targetInstanceMethod");
                    method.invoke(targetInstance);
                } catch (Exception e) {
                    // No new exception thrown
                }
                
                new Runnable() {
                    @Override
                    public void run() {
                        targetInstance.targetInstanceMethod();
                    }
                }.run();
                
                class LocalInnerInMethod {
                    void innerMethod() {
                        targetInstance.targetInstanceMethod();
                    }
                }
                new LocalInnerInMethod().innerMethod();
            }
        }
        new LocalInnerSourceClass().sourceInnerMethod(new TargetClass<>());
    }
}

class SubSourceClass extends SourceClass<String> {}

/**
 * Generic target class with javadoc, extends and local inner class
 * @param <E> Type parameter
 */
public class TargetClass<E> extends ArrayList<E> {
    public void targetInstanceMethod() {
        class LocalInnerTargetClass {
            void innerMethod() {
                System.out.println("Local inner class in target");
            }
        }
        new LocalInnerTargetClass().innerMethod();
    }
}