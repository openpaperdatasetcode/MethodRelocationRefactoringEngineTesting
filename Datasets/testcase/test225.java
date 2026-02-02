```java
package refactoring.test83505;

public class Test83505 {

    public record SourceRecord() {
        public static record StaticNestedRecord() {
            /**
             * Method to be refactored.
             * @param target Target record instance
             */
            protected void methodToMove(TargetRecord target) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Iteration: " + i);
                }

                class InnerDependency {
                    InnerDependency() {
                        System.out.println("Inner dependency created");
                    }
                }

                try {
                    InnerDependency dep = new InnerDependency();
                    switch (dep.hashCode() % 3) {
                        case 0:
                            System.out.println("Case 0");
                            break;
                        case 1:
                            System.out.println("Case 1");
                            break;
                        default:
                            System.out.println("Default case");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void outerMethod() {
            class LocalInnerClass {
                public LocalInnerClass() {
                    System.out.println("Local inner in source");
                }
            }
            new LocalInnerClass();
        }
    }

    public record TargetRecord() {
        public void targetMethod() {
            class TargetLocalInner {
                public TargetLocalInner() {
                    System.out.println("Target local inner");
                }
            }
            new TargetLocalInner();
        }
    }
}
```