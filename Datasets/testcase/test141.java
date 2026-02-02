```java
package p;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

enum SourceEnum {
    ;

    @Deprecated
    public static List<String> methodToMove(TargetEnum target) {
        abstract class LocalClass<T extends Number> {
            abstract void process();
        }

        new Runnable() {
            public void run() {
                System.out.println("Anonymous inner");
            }
        }.run();

        assert true : "Assertion message";

        ;

        label:
        for (int i = 0; i < 1; i++) {
            if (i == 0) break label;
        }

        int val = 0;
        switch (val) {
            case 0:
                break;
            default:
                break;
        }

        int num = 1;

        Pattern p = Pattern.compile(".*");
        p.matcher("test").matches();

        String s = target.name();
        return new ArrayList<>();
    }
}

strictfp enum TargetEnum {
    CONSTANT;

    {
        new Object() {
            void execute() {}
        };
    }
}
```