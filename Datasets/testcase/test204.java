```java
import java.sql.SQLException;
import java.util.concurrent.Callable;

abstract class Target<T> {
}

class Source<T> {
    protected int outerField = 10;

    public static class StaticNested {
    }

    public void demoMethod() {
        class LocalInner<U extends Number> {
            private int innerField;

            @Deprecated
            private synchronized Object methodToMove(Target<String> target) throws SQLException {
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        break;
                    }
                }
                this.methodToMove("test");
                outerField = 20;
                return new Object();
            }

            private void methodToMove(String s) {
            }
        }
    }
}

class SubTarget extends Target<String> {
    Callable<Integer> callable = () -> {
        return 0;
    };
}
```