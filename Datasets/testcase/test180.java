```java
// File: pkg/MySealed.java
package pkg;

public sealed interface MySealed permits SourceEnum {}

// File: pkg/SourceEnum.java
package pkg;

import java.util.*;
import java.lang.reflect.*;

public non-sealed enum SourceEnum implements MySealed {
    CONSTANT;

    private Container.TargetEnum targetField;

    public class MemberInnerClass {}

    public static class StaticNestedClass {}

    @Deprecated
    @Override
    protected void finalize() throws Throwable {
        assert true : "Assertion message";
        super.finalize();
        try {
            String s = "