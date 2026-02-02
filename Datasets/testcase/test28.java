```java
package pkg;

class Source {
    static class Nested1 {}
    static class Nested2 {}

    static class InnerBase {
        public Target methodToMove(Target t) throws Exception {
            return t;
        }
    }

    static class Inner extends InnerBase {
        public void methodToMove() {} // Overloaded method

        @Override
        public Target methodToMove(Target t) throws Exception {
            super.methodToMove(t); // Call to super method

            java.util.List<String> list = new java.util.ArrayList<>();
            list.add("item1");
            list.add("item2");

            int code = 2;
            switch (code) {
                case 1:
                    list.size();
                    break;
                case 2:
                    throw new Exception("Error");
                default:
                    list.clear();
            }

            return t;
        }
    }

    public final String callMethod() {
        java.util.List<String> data = new java.util.ArrayList<>();
        data.add("test");

        data.forEach(System.out::println);

        Target target = new Target();
        Inner inner = new Inner();
        Target result = true ? inner.methodToMove(target) : null;

        return "completed";
    }
}

class Target {}
```