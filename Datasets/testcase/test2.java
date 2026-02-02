```java
package pkg;

interface DummyInterface {}

public record TargetRecord(int value) implements DummyInterface {
    public class TargetInner {
    }
}

record SourceRecord(String data) {
    public class SourceInner {
        strictfp TargetRecord recursiveMethod(TargetRecord param, int depth) {
            int counter = 0;
            process: while (counter < 1) {
                System.out.println(param.toString());
                if (depth <= 0) {
                    return param;
                }
                counter++;
                break process;
            }
            return recursiveMethod(param, depth - 1);
        }
    }

    public static class StaticNestedClass {
    }
}
```