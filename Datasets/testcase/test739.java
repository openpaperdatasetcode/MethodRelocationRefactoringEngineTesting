import java.sql.SQLException;
import java.util.List;

// Source class: enum, protected modifier, same package, type parameter + member inner + static nested class
protected enum SourceClass<T extends CharSequence> {
    INSTANCE;

    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = TargetClass.VALUE1;

    // Type parameter (source_class feature)
    private T typeParam;

    // Member inner class (source_class feature)
    class MemberInnerClass {
        public void innerMethod() {}
    }

    // Static nested class (source_class feature)
    static class StaticNestedClass {
        public static void staticMethod() {}
    }

    // Method to refactor: instance, base type return (int), default access, source position
    int methodToRefactor() throws SQLException { // SQLException feature
        // Variable call feature
        int localVar = 10;
        localVar = targetField.ordinal();

        // Override violation feature
        @SuppressWarnings("unused")
        class OverrideViolation extends ParentClass {
            // Intentionally violates override (parent method is private, cannot override)
            @Override
            public List<String> abstractMethod() { // override_violation
                return null;
            }
        }

        // No new exception feature (no 'new Exception()' statements)
        if (localVar < 0) {
            throw new SQLException("SQL error"); // Only throws existing SQLException, no new exception instantiation
        }

        return localVar; // Base type return (int)
    }
}

// Parent class for call_method feature
abstract class ParentClass {
    // Call method: parent_class type, private modifier, abstract + instanceReference::methodName
    private abstract List<String> abstractMethod(); // abstract feature

    // Property assignment position for call_method
    private List<String> property = this::abstractMethod; // instanceReference::methodName, property assignment
}

// Target class: enum, public modifier, static nested class target_feature
public enum TargetClass {
    VALUE1, VALUE2;

    // Static nested class (target_feature)
    public static class target {
        // Placeholder for moved method (matches signature)
        int methodToRefactor() throws SQLException {
            return SourceClass.INSTANCE.methodToRefactor();
        }
    }
}