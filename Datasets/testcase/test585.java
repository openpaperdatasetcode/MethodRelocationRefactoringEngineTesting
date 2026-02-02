package refactoring.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// Source interface for implements feature
interface SourceInterface {
    List<String> process(String... args);
}

// Parent class for call_method (parent_class type)
class ParentClass {
    // Private generic method with ClassName::methodName feature (call_method)
    private <T> Object genericMethod(Supplier<T> supplier) {
        return supplier.get();
    }
}

// Source class: normal, default modifier, same package, implements + double local inner class
class SourceClass extends ParentClass implements SourceInterface {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class for source_inner_rec method position
    class SourceInnerClass {
        // Recursive inner class (source_inner_rec)
        class SourceRecursiveInner {
            /**
             * Method javadoc - specified method feature
             * @param targetParam target class parameter
             * @param args varargs parameter
             * @return List<String> result
             */
            public final List<String> process(TargetClass targetParam, String... args) {
                // Variable call (target class field)
                targetParam.dataList.add("varargs:" + args.length);
                targetField.counter++;

                // First local inner class (source feature)
                class LocalInnerOne {
                    void updateTarget() {
                        targetParam.value = "local1";
                    }
                }

                // Second local inner class (source feature - duplicate local inner class)
                class LocalInnerTwo {
                    void doWhileLogic() {
                        int i = 0;
                        // do-while statement with call_method (parent_class, private, generic, ClassName::methodName)
                        do {
                            Object callResult = ParentClass.this.genericMethod(LocalInnerTwo::new);
                            i++;
                        } while (i < targetField.counter);
                    }
                }

                // Instantiate local inner classes
                new LocalInnerOne().updateTarget();
                new LocalInnerTwo().doWhileLogic();

                // Used by reflection (method feature)
                try {
                    Method targetMethod = TargetClass.class.getMethod("getList");
                    List<String> reflectedList = (List<String>) targetMethod.invoke(targetParam);
                    targetParam.dataList.addAll(reflectedList);
                } catch (Exception e) {
                    // No new exception (method feature)
                    e.printStackTrace();
                }

                // Return List<String> (return_type)
                return new ArrayList<>(targetParam.dataList);
            }
        }
    }

    // Implement interface method (required for implements feature)
    @Override
    public List<String> process(String... args) {
        return new SourceInnerClass().new SourceRecursiveInner().process(targetField, args);
    }
}

// Target class: normal, public modifier, implements + anonymous inner class features
public class TargetClass implements TargetInterface {
    String value;
    int counter = 1;
    List<String> dataList = new ArrayList<>();

    // Implement interface method
    @Override
    public void interfaceMethod() {
        // Anonymous inner class (target_feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                dataList.add("anonymous");
            }
        };
        anonymousRunnable.run();
    }

    // Helper method for reflection
    public List<String> getList() {
        return new ArrayList<>(dataList);
    }
}

// Target interface for implements feature
interface TargetInterface {
    void interfaceMethod();
}