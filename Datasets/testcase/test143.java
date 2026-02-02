```java
package pkg;

import java.sql.SQLException;

public @interface SourceAnnotation {
    Object anon1 = new Object() {
        class Inner1 {}
    };
    Object anon2 = new Object() {
        public class ClassName {
            public int field;
            public ClassName() {}
        }
    };

    class SourceNested {
        final int methodToMove(Wrapper.TargetAnnotation.target_inner param) throws SQLException {
            pkg.SourceAnnotation.anon2.ClassName obj = new pkg.SourceAnnotation.anon2.ClassName();
            obj.field = 2;

            for (int i = 0; i < 5; i++) {
                if (i == 2) continue;
            }

            String str = "test";
            str