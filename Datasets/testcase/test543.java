// Source normal class (default modifier, same package as target)
class SourceClass {
    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        public static void logData(String data) {
            System.out.println("Static nested log: " + data);
        }
    }

    // Member inner class (source_class feature, source_inner position for method)
    public class SourceInnerClass {
        // Varargs method to be moved (public access, void return, source_inner position)
        public void processTarget(TargetClass targetParam, String... values) {
            // Per_condition: method contains target parameter
            if (targetParam == null) {
                return; // Return statement (early return)
            }

            // Type declaration statement
            TargetClass.InnerClass targetInner = targetParam.new InnerClass();
            int counter = 0;

            // Variable call to target inner class (loop through varargs)
            for (String val : values) {
                // Variable call: set data to target inner class
                targetInner.setData(val);
                // Variable call: get data from target inner class and log
                SourceStaticNestedClass.logData(targetInner.getData());
                counter++;
            }

            // Variable call: update target inner class counter
            targetInner.setCounter(counter);
            
            // Return statement (explicit void return, optional but satisfies feature)
            return;
        }
    }
}

// Target normal class (public modifier, same package as source)
public class TargetClass {
    // Member inner class (target_feature)
    public class InnerClass {
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
    }
}