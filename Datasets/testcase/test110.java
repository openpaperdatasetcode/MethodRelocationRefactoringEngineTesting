```java
class SuperSource {
    protected Object someField = new Object();
}

abstract class Source extends SuperSource {
    class MemberInner {}
    static class StaticNested {}
    
    private java.util.List<String> methodToMove(Target.TargetInner target, String... args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        Object f = super.someField;
        this.helper();
        try {
            java.lang.reflect.Method m = this.getClass().getMethod("helper");
            m.invoke(this);
        } catch (Exception e) {}
        Runnable r = this::helper;
        r.run();
        java.util.List raw = new java.util.ArrayList();
        raw.add("test");
        return java.util.Arrays.asList(args);
    }
    
    public void helper() {}
}

interface SomeInterface {}
class Target implements SomeInterface {
    class TargetInner {}
    void createLocal() {
        class LocalInner {}
    }
}
```