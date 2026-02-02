package refactoring.test;

// Source class - private modifier, same package as target, contains member inner and static nested classes
private class SourceClass {
    // Member inner class
    class MemberInnerClass {
        int value;

        MemberInnerClass(int value) {
            this.value = value;
        }
    }

    // Static nested class
    static class StaticNestedClass {
        String text;

        StaticNestedClass(String text) {
            this.text = text;
        }
    }

    // Method to be refactored - meets all specified method features
    public TargetClass refactorMethod(TargetClass targetParam) {
        TargetClass result = null;
        try {
            // Constructor invocation
            TargetClass.MemberInnerClass inner = new TargetClass.MemberInnerClass();
            // Variable call
            inner.counter = 0;
            // For statement
            for (int i = 0; i < 5; i++) {
                inner.counter += i;
                targetParam.updateValue(inner.counter);
            }
            result = targetParam;
        } catch (NullPointerException e) {
            // Requires try-catch block
            result = new TargetClass();
        }
        return result;
    }
}

// Target class - default modifier, contains member inner class
class TargetClass {
    // Member inner class (target feature)
    class MemberInnerClass {
        int counter;
    }

    private int value;

    void updateValue(int newValue) {
        this.value = newValue;
    }

    int getValue() {
        return value;
    }
}