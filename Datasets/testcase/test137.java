```java
public class TestMoveMethod28245 {
    public class Container {
        protected record Target() {
            public class InnerTarget {
                public void existingMethod() {}
                public void existingMethod(int i) {}
            }
        }
    }

    public record Source() {
        public class InnerSource {}

        /**
         * Method to be moved.
         * @param target Target instance
         */
        public static void methodToMove(Container.Target target) {
            class LocalClass {}
            int val = 3;
            System.out.println("Value: " + val);
            new Object();
            target.new InnerTarget().existingMethod();
        }
    }
}
```