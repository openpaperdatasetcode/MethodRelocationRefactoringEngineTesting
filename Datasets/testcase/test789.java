import java.sql.SQLException;
import java.util.List;

// Source class: normal, protected modifier, same package, permits (twice) + static nested class (twice)
protected sealed class SourceClass permits SourceSubClass1, SourceSubClass2 {
    // Satisfy per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";

    // Static nested class 1 (source_class feature)
    static class StaticNestedClass1 {
        public static void staticMethod1() {}
    }

    // Static nested class 2 (source_class feature)
    static class StaticNestedClass2 {
        public static void staticMethod2() {}
    }

    // Method to refactor: instance, Object return, protected access, source position
    protected Object methodToRefactor() throws SQLException {
        // Variable call feature
        String localVar = "localVar";
        localVar = targetField.innerField + localVar;

        // Access outer private field (access_outer_private feature)
        localVar = SourceClass.this.outerPrivateField + localVar;

        // Type declaration statement feature
        List rawList; // raw_type feature (no generic type)

        // Super keywords feature
        super.toString();

        // Super constructor invocation feature
        class SuperConstructorClass extends SourceClass {
            public SuperConstructorClass() {
                super(); // super constructor invocation
            }
        }
        new SuperConstructorClass();

        // ConstructorInvocation: private modifier, obj.field, 1, pos: source
        privateConstructorInvocation();

        // Instance code blocks with private instance method feature
        {
            // 1, others_class, instance, ClassName::methodName
            Object instanceResult = privateInstanceMethod(1, new OthersClass()); // instance code blocks pos
        }

        // Return statement feature
        if (localVar == null) {
            throw new SQLException("SQL error"); // SQLException feature (no new exception instantiation outside standard)
        }
        // No new exception feature (no custom new Exception() statements)
        return localVar; // return statement
    }

    // Private ConstructorInvocation implementation (pos: source)
    private void privateConstructorInvocation() {
        targetField.innerField = 1; // obj.field, 1
    }

    // Private instance method (matches type/modifier/method_feature/pos/return_type)
    private Object privateInstanceMethod(int num, OthersClass others) { // 1, others_class, instance
        // ClassName::methodName
        return others::instanceMethod;
    }
}

// Permits subclass 1 (source_class feature: permits)
final class SourceSubClass1 extends SourceClass {}

// Permits subclass 2 (source_class feature: permits)
final class SourceSubClass2 extends SourceClass {}

// Others class for instance method feature
class OthersClass {
    public Object instanceMethod() {
        return "othersInstanceMethod";
    }
}

// Parent class for call_method feature
abstract class ParentClass {
    // Call method: parent_class type, protected modifier, normal, this.methodName(arguments), static code blocks pos, return String
    protected String callMethod(String arg) {
        return arg;
    }

    // Static code blocks position for call_method
    static {
        ParentClass parent = new ParentClass() {};
        String result = parent.callMethod("staticBlockArg"); // this.methodName(arguments), Static code blocks pos
    }
}

// Target class: normal, private modifier (nested to enable private), local inner class target_feature
class WrapperClass {
    // Private target class (matches modifier: private)
    private class TargetClass {
        int innerField;

        // Local inner class (target_feature)
        void targetMethod() {
            class LocalInnerClass {
                public void innerMethod() {}
            }
            new LocalInnerClass();
        }

        // Target inner class for method relocation
        class target_inner {
            // Placeholder for moved method
            protected Object methodToRefactor() throws SQLException {
                SourceClass source = new SourceSubClass1();
                return source.methodToRefactor();
            }
        }
    }
}