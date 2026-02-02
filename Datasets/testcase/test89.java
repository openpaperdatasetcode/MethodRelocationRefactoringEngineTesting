```java
package pkg;

class SuperSource {
    protected int field;
    protected List<String> someMethod() {
        return new ArrayList<>();
    }
}

class Source extends SuperSource {
    private target_inner_rec targetField;

    static class StaticNested {
        StaticNested() {
            new Object();
        }
    }

    class Inner {}

    @Deprecated
    synchronized Object methodToMove() {
        super.field = 2;

        new StaticNested();

        label: for (int i = 0; i < 3; i++) {
            if (i == 1) break label;
        }

        class LocalType {}

        try {
            List<String> lst = null;
            lst.size();
        } catch (NullPointerException e) {
        }

        switch (1) {
            case 1:
                List<String> result = (List<String>) methodToMove();
        }

        return null;
    }

    Object methodToMove(int arg) {
        return null;
    }
}

class OuterTarget {
    protected static class target_inner_rec {
        static class NestedTarget {}
    }
}
```