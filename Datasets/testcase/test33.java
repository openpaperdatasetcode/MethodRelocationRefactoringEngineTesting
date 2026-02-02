```java
package pkg;

abstract class SuperClass {
    abstract void methodToOverride();
}

sealed abstract class Source extends SuperClass permits SourceSubclass {
    Target targetField;
    private int privateField = 1;
    public int instanceField = 2;

    class MemberInnerClass {
        void innerMethod() {
            System.out.println("Member inner class method");
        }
    }

    @Override
    void methodToOverride() {
        int localVar = 5;
        System.out.println(localVar);
        System.out.println(privateField);
        System.out.println(instanceField);
        MemberInnerClass inner = new MemberInnerClass();
        inner.innerMethod();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class in Source");
            }
        };
        r.run();
    }
}

non-sealed class SourceSubclass extends Source {}

strictfp abstract class Target extends SuperClass {
    {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class in Target");
            }
        };
        r.run();
    }
}
```