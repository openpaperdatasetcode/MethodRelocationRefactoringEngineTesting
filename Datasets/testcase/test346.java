package com.refactoring.movemethod;

// Public enum source class (same package as target)
public enum SourceEnum {
    INSTANCE;

    // Static field for depends_on_static_field feature
    private static volatile int staticField = 1;

    // Member inner class (source feature)
    public class SourceMemberInner {
        // Overloaded methods for call_method feature
        public PrivateTargetEnum innerMethod(PrivateTargetEnum target) {
            return target;
        }

        public PrivateTargetEnum innerMethod(int num) {
            return PrivateTargetEnum.VALUE;
        }
    }

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            ; // Empty statement
        }
    };

    // Annotation for call_method position (attribute values of annotations)
    @interface CallMethodAnnotation {
        SourceMemberInner innerMethod() default SourceMemberInner::innerMethod; // ClassName::methodName
    }

    // Instance method (protected access, base type return, target parameter)
    @CallMethodAnnotation
    protected int instanceMethod(PrivateTargetEnum targetParam) {
        // Variable call
        int localVar = 0;
        // Empty statement
        ;

        // Volatile InfixExpression with number 1
        volatile int infixVal = localVar + 1; // InfixExpression (numbers:1, modifier:volatile)

        // Depends on static field
        localVar = staticField + infixVal;

        // Exception handling statements with constructor feature
        try {
            // Constructor feature (default modifier, 1, others_class, constructor, this.methodName)
            OtherClass other = new OtherClass(1);
            localVar = this.constructorHelperMethod(other);
        } catch (Exception e) {
            // No new exception (no instantiation of new Exception)
            localVar = -1;
        }

        // Call inner class overloaded method (ClassName::methodName in annotation attribute)
        SourceMemberInner inner = new SourceMemberInner();
        PrivateTargetEnum callResult = inner.innerMethod(targetParam); // Overloading

        return localVar; // Base type return (int)
    }

    // Constructor helper method (exception handling pos, base type return)
    private int constructorHelperMethod(OtherClass other) {
        return other.getValue();
    }
}

// Private enum target class (same package as source)
private enum PrivateTargetEnum {
    VALUE;

    // Member inner class (target feature)
    public class TargetMemberInner {
        private int innerField = 1;
    }
}

// Others class for constructor feature
class OtherClass {
    private int value;

    // Constructor (others_class, constructor feature)
    OtherClass(int num) {
        this.value = num;
    }

    public int getValue() {
        return this.value;
    }
}