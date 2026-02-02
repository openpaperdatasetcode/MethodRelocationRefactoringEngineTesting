```java
//===== FILE: sourcepkg/SourceClass.java =====
package sourcepkg;

import targetpkg.TargetClass;
import java.sql.SQLException;
import java.util.ArrayList;

class SourceClass {
    private int value = 10;

    class InnerClass extends BaseClass {
        InnerClass() {
            super();
        }
    }

    @CustomAnnotation
    private TargetClass recursiveMethod(TargetClass param, int count) {
        if (count <= 0) {
            return param;
        }

        try {
            ArrayList rawList = new ArrayList();
            rawList.add(1);

            int[] numbers = {1, 2, 3};
            for (int num : numbers) {
                System.out.println(num);
            }

            switch (count) {
                case 1:
                    System.out.println("Case 1");
                    break;
                default:
                    System.out.println("Default case");
            }

            Runnable r = () -> System.out.println(this.value);
            r.run();

            String str = "test";
            int length = str.length();

            InnerClass inner = new InnerClass();
            return recursiveMethod(param, count - 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class BaseClass {}
class SQLHelper {
    static void sqlOperation() throws SQLException {
        throw new SQLException("DB error");
    }
}
@interface CustomAnnotation {}

//===== FILE: targetpkg/TargetClass.java =====
package targetpkg;

public class TargetClass extends SuperClass {
    private int value = 20;
}

class SuperClass {}
```