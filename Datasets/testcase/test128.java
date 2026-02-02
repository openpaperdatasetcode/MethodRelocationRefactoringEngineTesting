```java
package p;

interface SourceBase {}
interface TargetBase {}

class Helper {
    public Helper() {}
}

public interface Source extends SourceBase {
    static class StaticNested {}
    class MemberInner { private int field = 3; }
    static int staticField = 5;

    private target_inner_rec<Integer> methodToMove(target_inner_rec<Integer> target, String... args) {
        int a = 1;
        int b = new MemberInner().field;
        Helper helper = new Helper();
        do { a++; } while (a < 3);
        class LocalClass {}
        while (b < 5) { b++; }
        class WithSuper extends Object { void m() { super.toString(); } }
        String s = helper.toString();
        int c = staticField;
        try { throw new Exception(); } catch (Exception e) {}
        return new target_inner_rec<Integer>() {};
    }

    class CallerInner {
        void invoke() {
            int x = 0;
            switch(x) {
                case 0: 
                    new CallerInner().invoke();
                    break;
            }
        }
    }
}

interface target_inner_rec<T> extends TargetBase {}
```