import java.lang.reflect.Method;

// Parent class for super.field access
class SuperClass {
    protected int superField = 1; // Matches "1" in EmptyStatement target_feature
}

// Source normal class (protected modifier, same package as target, extends SuperClass for super.field)
protected class SourceClass extends SuperClass {
    // Field of target class (satisfies per_condition)
    private TargetClass targetField = new TargetClass();

    // Anonymous inner class (source_class feature)
    private Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public static String formatData(Object data) {
            return data.toString();
        }
    }

    // Instance method to be moved (final, Object return, source position)
    final Object processTarget() {
        // Inner class for EmptyStatement pos: inner class
        class InnerHelperClass {
            // EmptyStatement (private modifier, target_feature: super.field, 1)
            private void emptyStatementDemo() {
                ; // Empty statement
                int val = SourceClass.super.superField; // target_feature: super.field (value 1)
                targetField.setData("Value: " + val);
            }
        }

        // Invoke inner class with EmptyStatement
        new InnerHelperClass().emptyStatementDemo();

        // Expression statement
        targetField.getStaticNested().setCounter(1);
        // Variable call
        targetField.getStaticNested().incrementCounter();
        
        // Used by reflection feature
        try {
            Method method = TargetClass.class.getDeclaredMethod("getCounter");
            int counter = (int) method.invoke(targetField.getStaticNested());
            targetField.setData("Reflected counter: " + counter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return targetField; // Return target class instance as Object
    }

    // Inner class for call_method (inner_class type)
    class SourceInnerClass {
        // Overloading method 1 (call_method target_feature: overloading)
        String processTarget(TargetClass target) {
            return SourceStaticNestedClass.formatData(target.getData());
        }

        // Overloading method 2 (call_method target_feature: overloading)
        String processTarget(TargetClass.StaticNestedClass nested) {
            return SourceStaticNestedClass.formatData(nested.getCounter());
        }

        // Constructor with call_method in parameter list (pos: constructor parameter list)
        SourceInnerClass() {
            // call_method target_feature: ClassName::methodName (method reference)
            this(processTarget(SourceClass::processTarget));
        }

        private SourceInnerClass(String initValue) {
            System.out.println(initValue);
        }
    }
}

// Target normal class (protected modifier, same package as source)
protected class TargetClass {
    // Static nested class (target_feature)
    public static class StaticNestedClass {
        private int counter;

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

        public void incrementCounter() {
            this.counter++;
        }
    }

    private String data;
    private final StaticNestedClass staticNested = new StaticNestedClass();

    // Variable call methods
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public StaticNestedClass getStaticNested() {
        return staticNested;
    }

    public int getCounter() {
        return staticNested.getCounter();
    }
}