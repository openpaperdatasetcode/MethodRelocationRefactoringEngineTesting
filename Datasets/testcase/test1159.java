import java.util.ArrayList;
import java.util.List;

// Super interface for source/target implements feature
interface Processable {
    void process(String value);
    default String defaultMethod() { return "processable_default"; }
}

// Others class for overriding method_feature: others_class
class OthersClass {
    public void overrideMethod(int num) {
        System.out.println("OthersClass overrideMethod: " + num);
    }
}

// Source normal class (protected modifier, same package, implements, static nested class, anonymous inner class)
protected class SourceClass extends OthersClass implements Processable {
    // per_condition: source contains the field of the target
    private final PrivateTargetClass targetField = new PrivateTargetClass("init_value_5845");

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        static String format(String s) {
            return s.toUpperCase() + "_static_source";
        }
    }

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner execution: " + targetField.value);
        }
    };

    // Implement super interface method
    @Override
    public void process(String value) {
        targetField.setValue(value + "_processed_by_source");
    }

    // SuperConstructorInvocation (public modifier, obj.field, 1, pos: diff_package_others)
    public void superConstructorBlock() {
        // Simulate diff_package_others position (logical separation)
        class DiffPackageSimulator {
            void invokeSuper() {
                PrivateTargetClass newTarget = new PrivateTargetClass("super_invocation"); // SuperConstructorInvocation
                String objField = newTarget.value; // obj.field
                int num = 1; // target_feature: 1
                newTarget.setValue(objField + "_" + num);
            }
        }
        new DiffPackageSimulator().invokeSuper();
    }

    // Overriding method (public modifier, 1, others_class, overriding, super.methodName())
    @Override
    public void overrideMethod(int num) {
        // super.methodName() (call parent class method)
        super.overrideMethod(num);
        num = 1; // method_feature: 1

        // pos: switch
        switch (num) {
            case 1:
                targetField.process(targetField.getValue() + "_override_" + num);
                break;
            default:
                targetField.process(targetField.getValue() + "_override_default");
                break;
        }
    }

    // Method to be refactored (instance, base type return, public access)
    public int targetMethod(PrivateTargetClass.InnerRecord param) { // per_condition: target parameter (target_inner_rec)
        int result = 0;

        // Requires try-catch feature
        try {
            // SuperConstructorInvocation execution
            superConstructorBlock();

            // Synchronized statement
            synchronized (this) {
                // Variable call (target inner record + static nested class)
                String innerRecVal = param.value();
                String staticTargetVal = PrivateTargetClass.StaticNestedTargetClass.format(innerRecVal);
                targetField.setValue(staticTargetVal + "_" + StaticNestedSourceClass.format(innerRecVal));
                
                // Overriding method call in switch context
                overrideMethod(1); // 1 (method_feature)
                
                // Access target instance field
                result = targetField.getValue().length();
            }

            // Trigger anonymous inner class
            anonymousInner.run();

        } catch (NullPointerException e) {
            result = -1;
            System.out.println("Try-catch handled: " + e.getMessage());
        }

        // No new exception (core logic safe)
        return result;
    }
}

// Target normal class (private modifier, implements, static nested class target_feature)
private class PrivateTargetClass implements Processable {
    // obj.field for SuperConstructorInvocation feature
    public String value;

    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        static String format(String s) {
            return s.toLowerCase() + "_static_target";
        }
    }

    // Inner record (target_inner_rec)
    public record InnerRecord(String value) {}

    // SuperConstructorInvocation (constructor)
    public PrivateTargetClass(String value) {
        super(); // Implicit super constructor for Object
        this.value = value;
    }

    // Implement super interface method
    @Override
    public void process(String value) {
        this.value = value + "_processed_by_target";
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}