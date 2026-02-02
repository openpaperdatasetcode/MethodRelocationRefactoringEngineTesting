```java
package pkg;

import java.util.List;
import java.util.ArrayList;

public interface Target<T> {
    class Nested {}
    
    default void anonymousClassMethod() {
        Runnable r = new Runnable() {
            @Override
            public void run() {}
        };
    }
}

sealed interface Source permits PermittedClass {
    class