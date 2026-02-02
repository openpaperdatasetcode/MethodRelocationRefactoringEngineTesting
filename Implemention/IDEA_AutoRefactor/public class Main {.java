import java.util.ArrayList;

import javax.print.attribute.standard.Destination;

import static java.util.stream.Collectors.toList;

public class Main {
    void method(A a) {
    }
}

class RenameTest {
    static void foo1(Number n) {
        System.out.println("1");
    }

    public static void main(String[] args) {
        long n = 0;
        foo1(n);// look here
    }
}

class A {
    RenameTest test;

    // move 'foo1' to class 'RenameTest'
    void foo1(Long i) {
        System.out.println("2");
    }
}

interface A {

}

class B implements A {
    public void m(B b) {
    }
}

class TargetClass {
}

class SubClass extends TargetClass {
    static public void methodToBeMoved() {
    }
}

class SourceClass {
    TargetClass t;

    void methodToBeMoved() {
    }
}