```java
package pkg77819;

import java.util.List;
import java.util.ArrayList;

enum Source {
    ;

    private Target targetField;

    static class Inner1<T> {}

    static class Inner2<U> {
        static class Inner3 {
            abstract class MethodHolder {
                protected abstract <T extends Number> List<String> methodToMove();
            }

            class ConcreteMethodHolder extends MethodHolder {
                protected <T extends Number> List<String> methodToMove() {
                    List<String> result = new ArrayList<>();
                    outer:
                    for (int i = 0; i < 10; i++) {
                        class LocalClass {
                            void display() {
                                System.out.println(super.toString());
                            }
                        }
                        new LocalClass().display();
                        if (i == 5) break outer;
                    }
                    return result;
                }
            }
        }
    }
}

class Container {
    protected enum Target {}
}
```