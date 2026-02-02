package com.example;

// Super interface for source_class implements feature
interface SourceInterface {
    default int interfaceMethod() { return 0; }
}

// Source class (private modifier, normal class, same package, implements, anonymous inner class, static nested class)
private class SourceClass implements SourceInterface {
    // per_condition: source contains the field of the target
    private final AbstractTargetClass targetField = new ConcreteTargetClass();
    // this.field for IfStatement
    private String field = "sourcePrivateField";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in source class");
        }
    };

    // Method to be refactored (instance, AbstractTargetClass return, public access, position: source)
    public AbstractTargetClass targetMethod() {
        // IfStatement (private modifier, this.field, 3, pos: diff_package_others)
        private void ifStatementBlock() {
            this.field = "updated_" + 3; // this.field, 3 in target_feature
            if (this.field.contains("3")) { // IfStatement
                System.out.println(this.field);
            }
        }
        ifStatementBlock();

        // Super constructor invocation
        super();

        // Constructor invocation
        AbstractTargetClass newTarget = new ConcreteTargetClass();

        // Variable call
        String targetValue = newTarget.getValue();

        // Switch statement
        int switchResult = 0;
        switch (targetValue.length()) {
            case 3:
                switchResult = 3;
                break;
            default:
                switchResult = -1;
                break;
        }

        // Call method in expression (pos: expression)
        int callResult = new SourceClass().finalCallMethod(switchResult); // source, final, normal, new ClassName().method()

        // No new exception
        newTarget.setValue(targetValue + "_" + callResult);
        return newTarget;
    }

    // Call method (source, final modifier, normal, new ClassName().method(), return int)
    final int finalCallMethod(int arg) {
        return new HelperClass().helperMethod(arg);
    }

    // Helper class for new ClassName().method()
    static class HelperClass {
        int helperMethod(int arg) {
            return arg * 2;
        }
    }
}

// Target abstract class (abstract modifier, normal class, anonymous inner class target_feature)
abstract class AbstractTargetClass {
    protected String value = "targetValue";

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in target class");
        }
    };

    public abstract String getValue();
    public abstract void setValue(String value);
}

// Concrete implementation of target abstract class
class ConcreteTargetClass extends AbstractTargetClass {
    @Override
    public String getValue() {
        return super.value;
    }

    @Override
    public void setValue(String value) {
        super.value = value;
    }
}