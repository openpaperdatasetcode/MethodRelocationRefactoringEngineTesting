```java
// File: SuperInterface.java
public interface SuperInterface {
    Target<String> methodToMove();
    default void helper() {
        System.out.println("SuperInterface helper");
    }
}

// File: Target.java
public record Target<T>(T value) {
    public static void staticMethod() {
        System.out.println("Target static method");
    }

    public static class Nested {
        public void nestedMethod() {
            System.out.println("Nested method");
        }
    }
}

// File: Source.java
record Source(Target<String> targetField) implements SuperInterface {
    class InnerMember {
        public void process() {
            System.out.println("InnerMember process");
        }
    }

    @Override
    public final Target<String> methodToMove() {
        // Anonymous inner class
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        }).start();

        // Member inner class
        InnerMember inner = new InnerMember();
        inner.process();

        // Super type reference call
        SuperInterface.super.helper();

        // Enhanced for statement
        java.util.List<String> items = java.util.List.of("A", "B");
        for (String item : items) {
            System.out.println(item);
        }

        // While statement
        int count = 0;
        while (count < 2) {
            System.out.println("While iteration: " + count);
            count++;
        }

        // Variable call
        int size = items.size();
        System.out.println("List size: " + size);

        // Access instance method
        targetField.value().length();

        return targetField;
    }
}

// File: Parent.java
class Parent {
    void initCaller(Source src) {
        // Static method call
        Target.staticMethod();
        
        // Instance method reference
        java.util.function.Supp