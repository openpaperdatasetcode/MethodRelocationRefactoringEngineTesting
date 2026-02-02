```java
package pkg;

public @interface TargetAnnotation {
    public class TargetInner {
    }
}

@interface SourceAnnotation {
    TargetAnnotation field = null;

    static class SourceNested {
        public class SourceInner {
            @Deprecated
            strictfp public TargetAnnotation methodToMove() {
                // Uses outer 'this' of enclosing class
                String s = SourceNested.this.toString();

                // Variable call
                Object o = new Object();
                o.toString();

                // While loop
                while (s.isEmpty()) {
                    break;
                }

                // Inner class definition
                class LocalClass {
                    void innerMethod() {
                        // Call super method
                        super.toString();
                    }
                }

                // Call instance method
                this.helper();

                return field;
            }

            private void helper() {
            }
        }
    }
}
```