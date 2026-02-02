package com.refactoring.movemethod;

// Public normal source class (same package as target)
public abstract class SourceClass { // Abstract to contain abstract method
    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            // Local inner class (source feature)
            class LocalInner {
                int innerField = 1;
            }
            LocalInner local = new LocalInner();
        }
    };

    // Abstract method (protected access, void return, contains target inner param)
    protected abstract void abstractMethod(TargetClass.MemberInner targetInnerParam);

    // Concrete method implementing feature logic (backing abstract method contract)
    protected void methodImplementation(TargetClass.MemberInner targetInnerParam) {
        // Type declaration statement
        int localVar;
        String strVar;

        // Variable call
        localVar = 0;
        strVar = "variableCall";

        // Access instance field (target inner class field)
        localVar = targetInnerParam.innerField;

        // EnhancedForStatement (private modifier, obj.field + 1)
        private: { // Private scope marker for modifier
            int[] nums = {1};
            for (int num : nums) {
                localVar = targetInnerParam.innerField + num;
            }
        }

        // InfixExpression with number 1, protected modifier
        protected: { // Protected scope marker for modifier
            localVar = localVar + 1; // Infix expression (+)
        }

        // Requires try-catch (IO operation example)
        try {
            OtherClass other = new OtherClass();
            // Switch statement with call_method (others_class, public, instance, lambda)
            switch (localVar) {
                case 1:
                    int result = other.publicInstanceMethod(targetInnerParam, () -> localVar * 2);
                    break;
                default:
                    result = 0;
            }
        } catch (Exception e) {
            // Required try-catch block
        }
    }
}

// Public normal target class (same package as source)
public class TargetClass implements Runnable { // Implements (target feature)
    // Member inner class (target_inner_rec)
    public class MemberInner {
        int innerField = 1; // Field for obj.field reference
    }

    @Override
    public void run() {
        // Implements Runnable
    }
}

// Others class for call_method feature
class OtherClass {
    // Public instance method (others_class, public, instance, lambda param, int return)
    public int publicInstanceMethod(TargetClass.MemberInner inner, java.util.function.IntSupplier lambda) {
        return lambda.getAsInt() + inner.innerField;
    }
}