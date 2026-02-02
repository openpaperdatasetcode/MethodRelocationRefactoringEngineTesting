```java
public class TestCase29647 {

    interface SuperInterface {
        Object methodToOverride();
    }

    static class SuperClass implements SuperInterface {
        @Override
        public Object methodToOverride() {
            return null;
        }
    }

    protected static class SourceClass extends SuperClass {
        TargetClass targetField;

        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
            }
        };

        class MemberInner implements InnerInterface {
            @Override
            public Object innerMethod() {
                Object outerData = SourceClass.this.toString();
                innerMethod(1);
                assert outerData != null : "outerData is null";
                Runnable r = () -> System.out.println("Lambda");
                r.run();
                TargetClass.staticMethod();
                outerLoop:
                while (true) {
                    String s = outerData.toString();
                    if (s.isEmpty()) {
                        break outerLoop;
                    } else {
                        throw new NullPointerException("NPE");
                    }
                }
                return null;
            }

            public void innerMethod(int x) {
            }
        }
    }

    interface InnerInterface {
        Object innerMethod();
    }

    static class TargetClass implements SomeInterfaceForTarget {
        /**
         * Javadoc for TargetClass.
         */
        public static class StaticNested {
        }

        public static void staticMethod() {
        }
    }

    interface SomeInterfaceForTarget {
    }

    static class CallerClass {
        protected Object callMethod(SourceClass.MemberInner inner) {
            Object result = null;
            boolean flag = true;
            while (flag) {
                flag = false;
                TargetClass.staticMethod();
                result = inner.innerMethod();
            }
            return result;
        }
    }
}
```