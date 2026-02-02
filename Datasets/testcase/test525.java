import java.util.ArrayList;
import java.util.List;

// Source normal class (public modifier, same package as target)
public class SourceClass {
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
    private List<String> secondAnonymous = new ArrayList<String>() {{
        add("Second anonymous inner class in source");
    }};

    // Member inner class (source_inner position for method)
    public class SourceInnerClass {
        // Instance method to be moved (public, base type return, source_inner position)
        public int processTarget() {
            // Access target inner recursive class instance
            TargetClass.InnerClass.RecursiveInnerClass recInner = 
                targetField.new InnerClass().new RecursiveInnerClass();
            
            // Variable call (target inner recursive class field)
            recInner.setCounter(0);
            
            // Access instance method of target inner recursive class
            int result = 0;
            for (int i = 0; i < 5; i++) {
                recInner.incrementCounter();
                // Variable call (get counter value)
                result += recInner.getCounter();
            }
            
            // Additional access to target instance method
            recInner.resetCounter();
            return result; // Base type return (int)
        }
    }
}

// Target normal class (default modifier, same package as source)
class TargetClass {
    // Anonymous inner class (target_feature)
    private Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in target");
        }
    };

    // Inner class (for target_inner_rec)
    public class InnerClass {
        // Recursive inner class (target_inner_rec)
        public class RecursiveInnerClass {
            private int counter;

            // Instance methods for variable call/access
            public int getCounter() {
                return counter;
            }

            public void setCounter(int counter) {
                this.counter = counter;
            }

            public void incrementCounter() {
                this.counter++;
            }

            public void resetCounter() {
                this.counter = 0;
            }
        }
    }
}