```java
import java.util.*;
import java.io.*;

public interface Source extends Serializable {
    Target targetField = new Target() {};
    static int staticField = 5;

    class InnerMemberClass {}

    default List<String> methodToMove() {
        class LocalClass {
            int getValue() {
                return 10;
            }
        }
        LocalClass lc = new LocalClass();
        int val = lc.getValue();
        
        try {
            FileReader file = new FileReader("test.txt");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Runnable r = new Runnable() {
            public void run() {
                System.out.println(staticField);
            }
        };
        r.run();
        
        return Arrays.asList("A", "B");
    }

    default List<String> methodToMove(String input) {
        return Collections.singletonList(input);
    }
}

abstract interface Target {
    void abstractMethod();
}

class CallerClass extends Source.InnerMemberClass {
    public void callMethod() {
        List<String> result = Source.targetField.methodToMove();
    }
    
    public void callMethod(int num) {
        System.out.println(num);
    }
}
```