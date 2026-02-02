import java.util.Objects;

sealed class SourceClass<T> permits SourceClass.ChildClass {
    // Per condition: source contains the field of the target
    protected TargetClass<T> targetField;
    protected String outerProtectedField = "protectedValue";

    // First member inner class
    class MemberInnerClass1 {
        int calculate(int num) {
            return num * 3; // Matches "3" in method_feature
        }
    }

    // Second member inner class
    class MemberInnerClass2 {
        @Override
        public String toString() {
            return SourceClass.this.outerProtectedField;
        }
    }

    /**
     * Method javadoc for moveCandidateMethod
     * Returns TargetClass type, final access modifier, instance method
     * @return TargetClass<T> instance
     */
    final TargetClass<T> moveCandidateMethod() {
        super(); // Super constructor invocation
        this.targetField = new TargetClass<>(); // this.var = var

        // Overriding feature block (public modifier, exception handling pos, base type return)
        try {
            int result = new MemberInnerClass1().calculate(3); // new ClassName().method()
            // Variable call
            this.targetField.setInnerValue(result);
            // Expression statement
            String expr = this.outerProtectedField + result;
            
            // Continue statement
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) continue;
                this.targetField.updateLocalInner(i);
            }
        } catch (NullPointerException e) {
            // No new exception thrown, exception handling statements
        }

        return this.targetField; // Return TargetClass Type
    }

    // Call method: source type, default modifier, void return, ternary operators pos
    void callMethod(boolean flag) {
        // Ternary operator position
        SourceClass<T> instance = flag ? new SourceClass.ChildClass<>() : new SourceClass<>();
        // Constructor target_feature
        TargetClass<T> target = new TargetClass<>(instance.outerProtectedField);
        // superTypeReference.methodName(arguments)
        Objects.requireNonNull(target).processValue(3);
    }

    // Permitted subclass for sealed class
    static final class ChildClass<T> extends SourceClass<T> {}
}

private class TargetClass<U> {
    private int innerValue;

    public TargetClass() {
        super();
    }

    public TargetClass(U initial) {
        this.innerValue = initial.toString().length();
    }

    void setInnerValue(int val) {
        this.innerValue = val;
    }

    void updateLocalInner(int num) {
        // Local inner class (target_feature)
        class LocalInnerClass {
            int process(int input) {
                return input + innerValue;
            }
        }
        LocalInnerClass inner = new LocalInnerClass();
        this.innerValue = inner.process(num);
    }

    int processValue(int num) {
        return this.innerValue * num;
    }
}