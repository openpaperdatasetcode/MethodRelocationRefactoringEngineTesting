```java
package p;

public enum TargetEnum {
    VALUE1, VALUE2
}

private enum SourceEnum {
    ENUM_CONSTANT;
    TargetEnum targetField;

    public static class Inner {
        Inner innerField;

        strictfp void methodToMove(String... args) {
            for (String arg : args) {
                System.out.println(arg);
            }

            class LocalClass {
                void display() {
                    System.out.println("Local class method");
                }
            }

            LocalClass lc = new LocalClass();
            lc.display();
        }
    }
}
```