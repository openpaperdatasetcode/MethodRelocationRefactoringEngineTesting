```java
package pkg;

import java.util.ArrayList;
import java.util.List;

public enum SourceEnum {
    CONST;

    private TargetEnum field = TargetEnum.TARGET_CONST;

    class Inner1 {}
    class Inner2 {}

    private static TargetEnum staticField = TargetEnum.TARGET_CONST;

    protected enum TargetEnum {
        TARGET_CONST {
            @Override
            void display() {
                System.out.println("Anonymous inner class");
            }
        };

        abstract void display();
    }

    public static TargetEnum methodToMove() throws Exception {
        if (staticField == null) {
            throw new Exception("Depends on static field");
        }
        List rawList = new ArrayList();
        rawList.add("Raw type usage");
        methodToMove("Overload call");
        class Local extends Object {
            Local() {
                super();
            }
        }
        new Local();
        return staticField;
    }

    private static void methodToMove(String s) {
        System.out.println(s);
    }

    List<String> callMethod() {
        field.display();
        try {
            methodToMove();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
```