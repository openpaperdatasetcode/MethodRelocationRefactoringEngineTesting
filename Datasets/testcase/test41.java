```java
public class Container {
    private static class TargetClass {
        public static class NestedClass {}
    }

    static class SourceClass<T> {
        TargetClass methodToMove(TargetClass target, T data) {
            int code = 5;
            switch (code) {
                case 5:
                    System.out.println("Case five");
                    break;
                default:
                    System.out.println("Default case");
            }

            class LocalType {
                void display() {
                    System.out.println("Local type method");
                }
            }
            new LocalType().display();

            java.util.ArrayList rawList = new java.util.ArrayList();
            rawList.add("Raw type element");

            try {
                java.io.File.createTempFile("test", ".tmp");
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }

            return target;
        }
    }
}
```