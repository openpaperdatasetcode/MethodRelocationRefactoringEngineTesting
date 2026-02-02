```java
package p1;

import java.util.List;
import java.util.ArrayList;
import p2.TargetAnnotation;
import java.util.function.Supplier;

public @interface SourceAnnotation {
    class SourceInner {
        TargetAnnotation.TargetInner targetField;
        String fieldInSourceInner = "data";

        Runnable r = new Runnable() {
            public void run() {}
        };

        protected List<String> methodToMove() throws Exception {
            class LocalInner extends SuperClass {
                void innerMethod() {
                    String s = fieldInSourceInner;
                    super.methodName();
                }
            }
            
            LocalInner inner = new LocalInner();
            inner.innerMethod();
            
            if (fieldInSourceInner.length() > 0) {
                throw new Exception();
            }
            return new ArrayList<>();
        }

        String callMethod() {
            String result;
            if (targetField != null) {
                result = targetField.accessor();
                Supplier<String> s = targetField::accessor;
            } else {
                result = "default";
            }
            return result;
        }
    }
}

class SuperClass {
    protected void methodName() {}
}
```

```java
package p2;

public @interface TargetAnnotation {
    class TargetInner {
        Runnable r = new Runnable() {
            public void run() {}
        };

        public String accessor() {
            return "value";
        }
    }
}
```