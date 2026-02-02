```java
package refactoring.move;

import java.sql.SQLException;

public enum SourceEnum {
    CONSTANT;

    private int privateField = 42;

    public static class NestedStaticClass {}

    {
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        }.run();
    }

    protected int methodToMove(TargetEnum.InnerRec targetParam) throws SQLException {
        int value = privateField;
        Helper obj = new Helper();
        String str = super.toString();
        if (targetParam == null) {
            throw new SQLException("Exception thrown");
        }
        return value;
    }

    public void methodToMove() {}
}

enum TargetEnum {
    VALUE;
    
    public static final class InnerRec {
        InnerRec self;
        
        public InnerRec(InnerRec rec) {
            this.self = rec;
        }
    }
}

class Helper {}
```