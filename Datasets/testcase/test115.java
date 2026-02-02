```java
package com.move.refactoring.test.case74687;

import java.util.ArrayList;
import java.util.List;

public class Outer {
    private class TargetClass<T> {
        public class Inner {
        }
    }

    public class SourceClass extends TargetClass<String> {
        public void methodContainingLocalClasses() {
            class LocalInnerClass1 {}
            class LocalInnerClass2 {}
        }

        @Deprecated
        public TargetClass<String> create(TargetClass<String> param) {
            Inner inner = this.new Inner();
            return this;
        }

        protected List<String> processArgs(String[] args) {
            List<String> result = new ArrayList<>();
            for (String arg : args) {
                result.add(arg);
            }
            return result;
        }

        protected List<String> exampleMethod(String... args) {
            if (args.length == 0) {
                throw new IllegalArgumentException();
            }
            return this.processArgs(args);
        }
    }
}
```