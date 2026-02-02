package com.refactoring.test;

import java.util.ArrayList;
import java.util.List;

public class SourceClass {
    // Private outer field for access_outer_private
    private String outerPrivateField = "outerPrivateValue";

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {}
    };

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Method to be refactored (instance, void return, final access, position: source)
    public final void targetMethod(TargetClass param) { // per_condition: contains target parameter
        // For loop with instance method (pos: for)
        for (int i = 0; i < 1; i++) { // 1 in method_feature
            // Instance method call (private modifier, parent_class, super.methodName(), return List<String>)
            List<String> list = instanceMethod(i);
            // Variable call
            String targetValue = param.getValue();
            list.add(targetValue);
        }

        // Access outer private field
        String privateValue = this.outerPrivateField;

        // Constructor parameter list with call_method (pos: parameter list of constructors)
        OtherClass other = new OtherClass(OtherClassChild.overriddenMethod(privateValue));

        // No new exception (no_new_exception)
    }

    // Instance method (private modifier, return List<String>, parent_class, super.methodName())
    private List<String> instanceMethod(int num) { // 1 in method_feature, instance type
        super.toString(); // super.methodName() (parent_class feature)
        List<String> result = new ArrayList<>();
        result.add(String.valueOf(num));
        return result;
    }
}

protected class TargetClass {
    public String getValue() {
        return "targetValue";
    }
}

// Others_class (call_method type)
class OtherClass {
    public OtherClass(Object obj) {}

    // Private method for call_method modifier
    private static Object baseMethod(String arg) {
        return arg;
    }
}

// Child class for overriding (call_method target_feature)
class OtherClassChild extends OtherClass {
    public OtherClassChild() {
        super(null);
    }

    // Overriding method (call_method target_feature)
    @Override
    public Object overriddenMethod(String arg) {
        super.overriddenMethod(arg); // super.methodName(arguments)
        return OtherClass.baseMethod(arg); // private modifier call
    }
}