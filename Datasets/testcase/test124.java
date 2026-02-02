```java
package p;

public enum TargetEnum {
    /** TargetEnum javadoc */
    VALUE1, VALUE2;

    class MemberInnerClass {}
}

non-sealed enum SourceEnum permits ENUM1, ENUM2 {
    ENUM1, ENUM2;
    
    TargetEnum targetField;
    
    static class StaticNested {
        public static final TargetEnum methodToMove() {
            Object obj = TargetEnum.VALUE1;
            boolean flag = Boolean.parseBoolean("false");
            if (flag) {
                class LocalHelper {
                    void overloadedMethod(Object o) {
                        o.toString();
                    }
                    void overloadedMethod(String s) {}
                }
                LocalHelper helper = new LocalHelper();
                helper.overloadedMethod(null);
            }
            return (TargetEnum) obj;
        }
    }
}
```