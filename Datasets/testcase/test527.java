// Parent class for super.field access
class SuperClass {
    protected int superField = 1; // Matches "1" in target_feature
}

// Source normal class (protected modifier, same package as target, extends SuperClass for super.field)
protected class SourceClass extends SuperClass {
    // Field of target class (satisfies per_condition)
    private TargetClass targetField = new TargetClass();

    // First anonymous inner class (source_class feature)
    private Runnable firstAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("First anonymous inner class in source");
        }
    };

    // Second anonymous inner class (source_class feature)
    private java.util.function.Function<String, Integer> secondAnonymous = new java.util.function.Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return s.length();
        }
    };

    // Instance method to be moved (default access, Object return, source position)
    Object processTarget() {
        // VariableDeclarationStatement (private modifier, pos: source, target_feature: super.field, 1)
        private int localVar = super.superField; // target_feature: super.field + 1 (superField is 1)
        
        // Constructor invocation
        TargetClass.InnerClass.RecursiveInnerClass recInner = 
            new TargetClass().new InnerClass().new RecursiveInnerClass();
        
        // Variable call to target inner recursive class
        recInner.setData("Processed data: " + localVar);
        recInner.setCounter(localVar);
        
        // Additional variable call
        recInner.updateData(secondAnonymous.apply("Test"));
        
        return recInner; // Return target inner recursive type as Object
    }
}

// Target normal class (default modifier, same package as source, implements Runnable)
class TargetClass implements Runnable {
    @Override
    public void run() {}

    // Inner class (for target_inner_rec)
    public class InnerClass {
        // Recursive inner class (target_inner_rec)
        public class RecursiveInnerClass {
            private String data;
            private int counter;

            // Variable call methods
            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public int getCounter() {
                return counter;
            }

            public void setCounter(int counter) {
                this.counter = counter;
            }

            public void updateData(int value) {
                this.data += "_" + value;
            }
        }
    }

    // Local inner class (target_feature)
    public void methodWithLocalInner() {
        class LocalInnerClass {
            void printTargetInfo() {
                System.out.println(TargetClass.this.toString());
            }
        }
        new LocalInnerClass().printTargetInfo();
    }
}