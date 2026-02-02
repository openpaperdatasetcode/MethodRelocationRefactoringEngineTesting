```java
package pkg;

public class OuterForTarget {
    /**
     * Target record class with Javadoc.
     */
    protected static record TargetRecord() {
        static class StaticNested {
        }

        class TargetInner {
        }
    }
}

record SourceRecord(OuterForTarget.TargetRecord targetRecord) {
    static class StaticNested {
    }

    class MemberInner {
    }

    @Deprecated
    protected Object methodToMove(Object... args) {
        String s = "test";
        s.length();

        int x = 10;
        switch (x) {
            case 10:
                System.out.println("ten");
                break;
            case 20:
                System.out.println("twenty");
                break;
        }

        class DummyBase {
            DummyBase(int y) {
            }
        }
        DummyBase dummy = new DummyBase(x) {
        };

        if (targetRecord != null) {
            System.out.println(targetRecord);
        }

        return null;
    }
}
```