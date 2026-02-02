```java
package pkg81508;

sealed interface EnumInterface81508 permits SourceEnum81508 {}

non-sealed enum SourceEnum81508 implements EnumInterface81508 {
    ENUM_CONSTANT;

    TargetEnum81508 targetField;

    class MemberInner extends BaseInner81508 {
        int innerField;

        @Override
        public void recursiveMethod(int n) {
            if (n <= 0) {
                throw new RuntimeException("Base case");
            }
            this.innerField = n;
            int current = this.innerField;
            super.log("Processing: " + n);
            String str = "Hello";
            int length = str.length();
            recursiveMethod(n - 1);
        }
    }

    static class StaticNested {}
}

strictfp enum TargetEnum81508 {
    CONSTANT;

    Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {}
    };
}

interface BaseInterface81508 {
    void recursiveMethod(int n);
}

class BaseInner81508 implements BaseInterface81508 {
    public void log(String message) {}

    @Override
    public void recursiveMethod(int n) {}
}
```