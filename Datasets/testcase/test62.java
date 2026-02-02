```java
package pkg58568;

public class Test58568 {

    private static class SourceClass {
        public static class StaticNested {}
        public class MemberInner {
            public class InnerInnerRec {
                /**
                 * Method javadoc.
                 */
                public static int moveMe(TargetClass.StaticNestedTarget.InnerTarget param, int... nums) {
                    int base = TargetClass.StaticNestedTarget.staticField;
                    int count = 0;
                    do {
                        count++;
                    } while (count < 1);
                    
                    class LocalHelper extends Object {
                        LocalHelper() {
                            super();
                        }
                    }
                    new LocalHelper();
                    
                    synchronized (TargetClass.class) {
                        base++;
                    }
                    
                    int value = base * 2;
                    
                    try {
                        java.lang.reflect.Method method = 
                            InnerInnerRec.class.getMethod("dummyReflection");
                        method.invoke(null);
                    } catch (Exception ignored) {}
                    
                    return value + nums.length;
                }
                
                public static void dummyReflection() {}
            }
        }
    }

    protected static class TargetClass {
        public static class StaticNestedTarget {
            public static int staticField = 5;
            public static class InnerTarget {}
        }
    }

    public String callMethod() {
        int result = true ? 
            SourceClass.MemberInner.InnerInnerRec.moveMe(
                new TargetClass.StaticNestedTarget.InnerTarget(), 1, 2, 3) : 0;
        return super.toString() + result;
    }
}
```