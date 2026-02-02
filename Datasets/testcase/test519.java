import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source generic class (public modifier, same package as target)
public class SourceClass<S> {
    // Field of target class (satisfies per_condition)
    private TargetClass<Integer> targetField = new TargetClass<>();

    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {}

    // Member inner class (source_class feature)
    public class SourceMemberInnerClass {
        // Instance method to be moved (final, base type return, source_inner position)
        final int processTarget() {
            int result = 0;
            int count = 0;

            // Requires try-catch block
            try {
                // Access instance method of target inner recursive class
                TargetClass<Integer>.InnerClass.RecursiveInnerClass recInner = 
                    targetField.new InnerClass().new RecursiveInnerClass();
                
                // 3 protected SuperMethodReference expressions
                Consumer<String> ref1 = recInner::super.printValue; // 1st SuperMethodReference
                Consumer<String> ref2 = recInner::super.validate;  // 2nd SuperMethodReference
                Consumer<String> ref3 = recInner::super.process;   // 3rd SuperMethodReference

                // Loop with continue statement
                while (count < 5) {
                    count++;
                    if (count % 2 == 0) {
                        continue; // Continue statement
                    }
                    // Variable call
                    recInner.setCounter(count);
                    // Access instance method
                    result += recInner.getCounter();
                    
                    // Invoke super method references
                    ref1.accept(String.valueOf(result));
                    ref2.accept(String.valueOf(result));
                    ref3.accept(String.valueOf(result));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    // Target generic class (private modifier, same package as source, implements Runnable)
    private class TargetClass<T> implements Runnable {
        @Override
        public void run() {}

        // Inner class (for target_inner_rec)
        public class InnerClass {
            // Recursive inner class (target_inner_rec)
            public class RecursiveInnerClass {
                private int counter;

                protected void printValue(String val) {}
                protected void validate(String val) {}
                protected void process(String val) {}

                // Access instance method
                public int getCounter() {
                    return counter;
                }

                public void setCounter(int counter) {
                    this.counter = counter;
                }
            }

            // Local inner class (target_feature)
            public void methodWithLocalInner() {
                class LocalInnerClass {
                    void printTargetData() {
                        System.out.println(TargetClass.this.toString());
                    }
                }
                new LocalInnerClass().printTargetData();
            }
        }

        // Call method (inner_class type, public, normal, obj.m1().m2().m3(), pos: exception throwing, return List<String>)
        public List<String> callProcessTarget() {
            List<String> results = new ArrayList<>();
            try {
                // obj.m1().m2().m3() feature
                String value = targetField.new InnerClass() // m1()
                                         .new RecursiveInnerClass() // m2()
                                         .toString(); // m3()
                results.add(value);
                
                // Call the method to be moved (source_inner)
                SourceMemberInnerClass inner = new SourceMemberInnerClass();
                results.add(String.valueOf(inner.processTarget()));
            } catch (NullPointerException e) { // pos: exception throwing statements
                throw new RuntimeException("Error processing target", e);
            }
            return results;
        }
    }
}