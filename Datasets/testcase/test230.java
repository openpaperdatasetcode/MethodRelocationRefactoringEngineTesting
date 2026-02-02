```java
package pkg;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

class Source {
    protected Target targetField;

    private static class BaseClass {
        BaseClass() {}
    }

    /**
     * Method Javadoc for recursive method.
     * @param depth recursion depth
     * @return Target instance
     * @throws SQLException if database error occurs
     */
    public Target recursiveMethod(int depth) throws SQLException {
        if (depth <= 0) {
            return targetField;
        }

        try {
            if (depth == 1) {
                throw new SQLException("Simulated DB error");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException caught: " + ex.getMessage());
        }

        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };
        anonymous.run();

        class LocalClass extends BaseClass {
            LocalClass() {
                super();
            }
        }
        new LocalClass();

        List<String> data = Arrays.asList("item1", "item2", "item3");
        data.forEach(element -> Helper.process(element));

        return recursiveMethod(depth - 1);
    }

    protected class Target implements Operations {
        public class InnerTarget {}
    }
}

interface Operations {
    void execute();
}

class Helper {
    static void process(String... elements) {
        System.out.println("Processing " + elements.length + " elements");
    }
}
```