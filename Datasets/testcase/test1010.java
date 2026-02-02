import java.util.List;
import java.util.ArrayList;

abstract class SourceClass<T> {
    private int instanceField = 10;

    private int moveCandidateMethod(TargetClass<T>... targetParams) {
        // First local inner class
        class LocalInnerClass1 {
            int getValue(TargetClass<T> target) {
                return target.getInstanceValue() + instanceField;
            }
        }

        // Second local inner class
        class LocalInnerClass2 {
            void updateValue(TargetClass<T> target, int val) {
                target.setInstanceValue(val);
            }
        }

        int total = 0;
        LocalInnerClass1 inner1 = new LocalInnerClass1();
        LocalInnerClass2 inner2 = new LocalInnerClass2();

        // Try statement
        try {
            for (TargetClass<T> target : targetParams) {
                // Variable call
                int current = inner1.getValue(target);
                // Access instance method
                inner2.updateValue(target, current);
                total += target.getInstanceValue();
            }
        } catch (NullPointerException e) {
            // No new exception thrown, handle existing exception silently
            total = 0;
        }
        return total;
    }

    private int getInstanceMethod() {
        return this.instanceField * 2;
    }
}

private class TargetClass<U> {
    private int instanceValue;

    int getInstanceValue() {
        return this.instanceValue;
    }

    void setInstanceValue(int value) {
        this.instanceValue = value;
    }
}