package refactoring.test;

// Interface for source class implements feature
interface SourceInterface<T> {
    int process(T data);
}

// Source class: generic, public modifier, same package, type parameter + implements + local inner + static nested classes
public class SourceClass<T> implements SourceInterface<T> {
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>("initial");

    // Type parameter (source feature)
    private T sourceData;

    // Static nested class (source feature)
    public static class SourceStaticNested {
        int nestedValue = 1;
    }

    // Member inner class for source_inner_rec structure
    public class SourceInnerClass {
        // Recursive inner class (method_position: source_inner_rec)
        public class SourceRecursiveInner {
            // Method to be refactored: static, Object return, public access, source_inner_rec
            public static Object refactorMethod() {
                // Variable call (target inner class field)
                targetField.innerClass.value = "refactor";
                // Super keywords usage
                SourceRecursiveInner.super.getClass();

                // Constructor invocation (target class)
                TargetClass<String> targetInstance = new TargetClass<>("newInstance");
                // Depends on inner class
                TargetClass<T>.TargetInnerClass innerInstance = targetInstance.new TargetInnerClass();

                // Local inner class (source feature)
                class LocalInnerClass extends SourceStaticNested {
                    // Overriding method: synchronized, pos=exception handling, return base type
                    @Override
                    public synchronized int process() { // overriding feature
                        try {
                            // super.methodName() (method_feature)
                            super.toString();
                            return 1; // 1 (method_feature)
                        } catch (Exception e) {
                            // No new exception
                            return 0;
                        }
                    }
                }

                // Exception handling statements with overriding method call
                try {
                    LocalInnerClass localInner = new LocalInnerClass();
                    int baseResult = localInner.process(); // source (method_feature)
                } catch (Exception e) {
                    // No new exception
                }

                // Anonymous inner class usage (target_feature)
                targetField.runnable = new Runnable() {
                    @Override
                    public void run() {
                        innerInstance.counter++;
                    }
                };

                // No new exception, return Object
                return innerInstance;
            }
        }
    }

    // Implement interface method (required for implements feature)
    @Override
    public int process(T data) {
        this.sourceData = data;
        return 1;
    }
}

// Target class: generic, default modifier, anonymous inner class feature
class TargetClass<T> {
    private T data;
    TargetInnerClass innerClass;
    Runnable runnable;

    public TargetClass(T data) {
        this.data = data;
        this.innerClass = new TargetInnerClass();
    }

    // Target inner class (target_inner - target for method)
    public class TargetInnerClass {
        T value;
        int counter = 1;
    }

    // Anonymous inner class (target_feature)
    {
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                innerClass.counter = 1;
            }
        };
        anonymous.run();
    }
}