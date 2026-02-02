```java
class TargetClass {
    static class TargetNested {
    }
}

class Container {
    private static class SourceClass {
        static class StaticNested {
        }

        protected Object methodToMove(TargetClass target) {
            class LocalInner {
            }

            java.util.List<? extends Number> list = java.util.Arrays.asList(1, 2, 3);
            Object result = null;
            for (Number num : list) {
                result = target.toString();
            }
            return result;
        }
    }
}
```