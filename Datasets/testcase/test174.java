```java
package pkg;

import java.io.IOException;

interface BaseInterface {}

public record SourceRecord(TargetRecord targetField) implements BaseInterface {
    class Inner1 {}
    class Inner2 {}

    protected int protectedField = 2;

    public static final void methodToMove() {
        try {
            synchronized (new Object()) {
                for (int i = 0; i < 5; i++) {
                    if (i == 2) continue;
                }
                SourceRecord instance = new SourceRecord(new TargetRecord());
                TargetRecord tr = instance.targetField();
                int val = tr.x;
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        instance.instanceMethod();
                        int fieldVal = instance.protectedField;
                        this.toString();
                    }
                };
                r.run();
                throw new IOException();
            }
        } catch (Exception e) {
        }
    }

    private void instanceMethod() {}
}

record TargetRecord(int x) {
    Runnable anon = new Runnable() {
        @Override
        public void run() {}
    };
}
```