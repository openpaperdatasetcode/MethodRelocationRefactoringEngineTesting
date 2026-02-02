```java
package pkg;

public class Outer {
    protected class Source {
        class MemberInner {
            Object anonymous = new Object() {
                void innerMethod() {}
            };

            private Target moveMethod(Target target, int... varargs) {
                String result = (varargs.length > 0) ? new String("Valid") : new String("Invalid");
                target.callMethod(result);
                return target;
            }
        }
    }

    private class Target {
        class TargetInner {}

        final void callMethod(String... args) {
            java.util.List<String> list = new java.util.ArrayList<>();
            java.util.Arrays.stream(args).forEach(s -> list.add(s));
        }

        final void callMethod(int num, String... args) {
            this.callMethod(args);
        }
    }
}
```