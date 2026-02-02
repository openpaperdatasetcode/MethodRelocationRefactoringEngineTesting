package refactoring.test;

import java.lang.reflect.Method;
import java.util.function.Function;

// Others class for call_method feature
class OtherClass {
    // call_method: normal method, return_type int
    public int normalMethod(int value) {
        return value * 3;
    }
}

// Source class: generic, public modifier, same package as target, features: implements, local inner class, static nested class
public class SourceClass<T> implements Function<T, String> {
    // Per condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>("initial-data");

    // Static nested class (source_class feature)
    public static class SourceStaticNested<U> {
        U nestedData;

        public U getNestedData() {
            return nestedData;
        }
    }

    // Member inner class (source_inner_rec - method position host)
    public class SourceInnerRecClass {
        // Method to be refactored: instance, return Object, protected access, position source_inner_rec
        protected Object moveMethod() {
            // Variable call feature
            int localVar = 0;
            OtherClass otherObj = new OtherClass();

            // Do statement feature
            int doCounter = 0;
            do {
                localVar++;
                // Exception handling statements with call_method (pos: exception handling statements)
                try {
                    // call_method target_feature: instanceReference::methodName
                    Function<Integer, Integer> func = otherObj::normalMethod;
                    localVar = func.apply(localVar);
                } catch (Exception e) {
                    // No new exception thrown (no_new_exception feature)
                    localVar = 1;
                }
                doCounter++;
            } while (doCounter < 5);

            // Local inner class (source_class feature)
            class SourceLocalInner {
                String process(int val) {
                    return "processed-" + val;
                }
            }
            SourceLocalInner localInner = new SourceLocalInner();
            String processedStr = localInner.process(localVar);

            // Used_by_reflection feature
            try {
                Method method = TargetClass.TargetInnerRecClass.class.getMethod("setData", String.class);
                TargetClass.TargetInnerRecClass innerRec = targetField.getInnerRecClass();
                method.invoke(innerRec, processedStr);
            } catch (Exception e) {
                // No new exception thrown (no_new_exception feature)
                e.printStackTrace();
            }

            // No new exception thrown (no_new_exception feature)
            return targetField.getInnerRecClass().getData();
        }
    }

    // Implement Function interface method (source_class feature: implements)
    @Override
    public String apply(T t) {
        return t.toString();
    }
}

/**
 * Javadoc for TargetClass (target_feature: javadoc)
 * Generic target class with anonymous inner class
 * @param <T> Type parameter for target data
 */
// Target class: generic, strictfp modifier, same package, target_feature: javadoc, anonymous inner class
strictfp class TargetClass<T> {
    private T data;

    // Anonymous inner class (target_feature)
    private Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + data);
        }
    };

    public TargetClass(T data) {
        this.data = data;
        anonymousRunnable.run(); // Invoke anonymous inner class
    }

    // Inner class (target_inner_rec - target class for method)
    public class TargetInnerRecClass {
        private String innerData;

        public void setData(String data) {
            this.innerData = data;
        }

        public Object getData() {
            return innerData + "-" + TargetClass.this.data;
        }
    }

    public TargetInnerRecClass getInnerRecClass() {
        return new TargetInnerRecClass();
    }
}