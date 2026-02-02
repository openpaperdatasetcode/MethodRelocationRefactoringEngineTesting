```java
package pkg;

import java.util.List;
import java.util.ArrayList;

@interface SourceAnnotation {
    public static class Inner1 {
        public class Inner2 {
            List<String> methodToMove(TargetAnnotation target) throws Exception {
                class LocalClass extends ArrayList<String> {
                    LocalClass() {
                        super();
                    }
                }
                if (target == null) {
                    throw new Exception("Exception message");
                }
                int x = 10;
                System.out.println(x);
                return new LocalClass();
            }
        }

        private int callMethod(TargetAnnotation target) {
            Inner2 inner2 = this.new Inner2();
            try {
                List<String> result = inner2.methodToMove(target);
                return result.size();
            } catch (Exception e) {
                return -1;
            }
        }
    }
}

public @interface TargetAnnotation {
    public static class TargetInner {
        public class TargetInnerMost implements Runnable {
            @Override
            public void run() {
            }
        }
    }
}
```