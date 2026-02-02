/**
 * Target private normal class (implements Runnable, member inner class feature)
 * @author Generator
 * @version 1.0
 */
private class TargetClass implements Runnable {
    // Member inner class (target_feature: member inner class, target_inner for method's target class)
    public class InnerClass {
        private String data;
        private int counter = 0;

        // Instance methods for access_instance_method feature
        public String processData(String input) {
            this.data = input + "_processed";
            return this.data;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

        public String getData() {
            return data;
        }
    }

    @Override
    public void run() {
        System.out.println("Target class implements Runnable");
    }
}

// Source normal class (default modifier, same package as target)
class SourceClass {
    // Field of target class (satisfies per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass<T extends Number & Comparable<T>> {
        // With bounds (generic type bounds feature)
        public T calculate(T value) {
            return value;
        }
    }

    // Member inner class (source_inner position for method)
    public class SourceInnerClass {
        // Instance method to be moved (private, base type return, source_inner position)
        private int processTarget() {
            // Super constructor invocation (inner class context)
            super();

            // Constructor invocation (target inner class)
            TargetClass.InnerClass targetInner = targetField.new InnerClass();
            
            // With bounds usage
            SourceStaticNestedClass<Integer> boundedClass = new SourceStaticNestedClass<>();
            Integer boundedVal = boundedClass.calculate(5);

            // If statement
            if (targetInner.getCounter() < boundedVal) {
                // Super keywords (outer class super context)
                String outerSuper = SourceClass.super.toString();
                // Access instance method of target inner class
                String processed = targetInner.processData(outerSuper);
                // Variable call
                targetInner.setCounter(boundedVal);
            }

            // Uses outer this (reference outer SourceClass this)
            targetInner.processData("Outer this: " + SourceClass.this.toString());

            // Base type return (int)
            return targetInner.getCounter();
        }
    }

    // Local inner class (source_class feature)
    public void methodWithLocalInner() {
        class LocalInnerClass {
            void printTargetData() {
                SourceInnerClass inner = new SourceInnerClass();
                System.out.println("Target counter: " + inner.processTarget());
            }
        }
        new LocalInnerClass().printTargetData();
    }
}