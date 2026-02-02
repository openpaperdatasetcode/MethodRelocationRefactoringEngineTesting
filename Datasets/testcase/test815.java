package refactoring.test;

protected class SourceClass {
    // Source contains the field of the target (per_condition)
    private TargetClass targetField;

    // First member inner class (feature requirement)
    class InnerClassOne {
        // Second member inner class (recursive inner - method_position: source_inner_rec)
        class InnerClassTwo {
            // Varargs method, final access modifier, return type TargetClass
            final TargetClass testMethod(int... args) {
                int value = 10;
                do {
                    // Expression statement
                    value += args.length > 0 ? args[0] : 0;
                    // Variable call
                    if (targetField != null) {
                        // Lambda: () -> System.out.println(this.value)
                        Runnable runnable = () -> System.out.println(this.value);
                        runnable.run();
                        break; // Break statement
                    }
                    // With bounds (loop bound check)
                } while (value < 100);
                
                // No new exception thrown, return TargetClass type
                return new TargetClass();
            }
            
            private int value; // Field for lambda access
        }
    }
}

public class TargetClass {
    // Member inner class (target_feature requirement)
    public class TargetInnerClass {
        private String data;
        
        public String getData() {
            return data;
        }
    }
}