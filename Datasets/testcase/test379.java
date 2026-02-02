package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source class: normal, private modifier, same package, local inner + anonymous inner classes
private class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Abstract method to be refactored (abstract type, List<String> return, public access)
    @SuppressWarnings("unused") // has_annotation
    public abstract List<String> refactorMethod();

    // Concrete implementation holder for abstract method logic
    public List<String> refactorMethodImpl() {
        List<String> result = new ArrayList<>();
        
        // Variable call (target inner recursive class field)
        targetField.innerRecursive.recursiveInner.value = "test";
        // ExpressionStatement: transient modifier, same_package_target pos, obj.field + 1
        transient int exprField = 1;
        targetField.innerRecursive.counter = exprField; // obj.field (target_feature)
        ; // ExpressionStatement with 1 (target_feature)

        // Local inner class (source feature)
        class LocalInnerClass {
            // Call method: inner_class, private modifier, accessor, superTypeReference.methodName()
            private int callMethod(List<String> list) { // collection operations pos
                // Accessor feature (getter)
                String value = targetField.innerRecursive.getRecursiveValue();
                // superTypeReference.methodName(arguments)
                Object superCall = Object.class.getMethod("toString").invoke(value);
                list.add(value);
                return list.size();
            }
        }

        // Collection operations with call_method invocation
        result.add("initial");
        int callResult = new LocalInnerClass().callMethod(result);

        // Anonymous inner class (source feature)
        Runnable sourceAnonymous = new Runnable() {
            @Override
            public void run() {
                targetField.innerRecursive.counter = 1;
                // No new exception
            }
        };
        sourceAnonymous.run();

        // Anonymous inner class usage (target_feature)
        targetField.anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                result.add("anonymous");
            }
        };
        targetField.anonymousRunnable.run();

        // No new exception, return List<String>
        return result;
    }
}

// Target class: normal, private modifier, anonymous inner class feature
private class TargetClass {
    TargetInnerRecursive innerRecursive = new TargetInnerRecursive();
    Runnable anonymousRunnable;

    // Target inner recursive class (target_inner_rec)
    class TargetInnerRecursive {
        int counter = 1;
        TargetRecursiveInner recursiveInner = new TargetRecursiveInner();

        // Accessor feature (getter)
        public String getRecursiveValue() {
            return recursiveInner.value;
        }

        // Recursive inner structure
        class TargetRecursiveInner {
            String value;
        }
    }

    // Anonymous inner class (target_feature)
    {
        anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                innerRecursive.counter = 1;
                // No new exception
            }
        };
    }
}