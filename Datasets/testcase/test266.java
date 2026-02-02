```java
package pkg;

@interface SourceAnnotation {
    class SourceInner {
        {
            new Object() {};
        }

        @Deprecated
        public Object methodToMove(TargetAnnotation param) {
            if (true) {
                class LocalType {}
                LocalType var = new LocalType();
                helper(1);
            } else {
                helper(0);
            }
            super.toString();
            return null;
        }

        protected void helper(int x) {}
        protected void helper() {}
    }
}

public @interface TargetAnnotation {
    /**
     * Javadoc
     */
    class TargetInner {
        protected static int recursiveMethod(int n) {
            do {
                TargetInner.recursiveMethod(n-1);
            } while (n > 0);
            return 0;
        }
    }
}
```