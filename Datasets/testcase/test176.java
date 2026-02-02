```java
package pkg;

public final class SourceClass {
    private volatile int volatileField;
    private target_inner_rec targetField;

    private static class target_inner_rec {
        public void methodInTarget() {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Target anonymous");
                }
            };
            r.run();
        }
    }

    public static class StaticNested {
    }

    public final void methodToMove() {
    }

    public final <V> Object methodToMove(String s) {
        return null;
    }

    public final <T, U> target_inner_rec methodToMove(int x) {
        while (volatileField == 1) {
            break;
        }

        if (this.<String>methodToMove("arg") != null) {
            new Integer(5);
        }

        java.util.List<String> list;
        list = new java.util.ArrayList<>();
        list.size();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Source anonymous");
            }
        };
        r.run();

        return new target_inner_rec();
    }
}
```