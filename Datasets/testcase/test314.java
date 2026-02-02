package com.refactor;

import java.io.IOException;

// Same package others class for SwitchStatement pos (same_package_others)
class SamePackageOthers {
    // SwitchStatement: private modifier, obj.field + 2, pos: same_package_others
    private static void switchLogic(TargetClass target) {
        switch (target.field) {
            case "value_2":
                target.field = "switched_2"; // 2 feature
                break;
            default:
                target.field = "default_2"; // 2 feature
                break;
        }
    }
}

// Others class for call_method (others_class type)
class OthersClass {
    // call_method: protected modifier, overloading + super.methodName(arguments), pos: expression, returns int
    protected int callMethod(TargetClass target) {
        return target.field.length();
    }

    // Overloading feature for call_method
    protected int callMethod(TargetClass target, int multiplier) {
        return superCall(target) * multiplier;
    }

    // Super method call for super.methodName(arguments)
    private int superCall(TargetClass target) {
        return target.field.length() * 2; // 2 feature
    }
}

// Super class for overriding feature
class SuperSourceClass {
    protected TargetClass processTarget(TargetClass target) throws IOException {
        target.field = "super_2"; // 2 feature
        return target;
    }
}

// Source class: private normal class, same package as target, local inner + member inner class
private class SourceClass extends SuperSourceClass {
    // Static field for depends_on_static_field feature
    private static String staticField = "static_2"; // 2 feature

    // Member inner class feature
    private class MemberInnerClass {
        public void modifyTarget(TargetClass target) {
            target.field += staticField; // depends_on_static_field
        }
    }

    // Method: overriding, return TargetClass Type, private access (override violation), in source class
    @Override
    private TargetClass processTarget(TargetClass targetParam) throws IOException { // requires_throws (IOException)
        // Variable call (target parameter)
        TargetClass target = targetParam;
        
        // SwitchStatement from same_package_others (obj.field + 2)
        SamePackageOthers.switchLogic(target);
        
        // For statement
        for (int i = 0; i < 2; i++) { // 2 feature
            // Do statement
            do {
                target.field = "loop_2_" + i; // 2 feature
                // Break statement
                if (i == 1) break;
            } while (i < 1);
        }
        
        // Depends on static field
        target.field += staticField;
        
        // Member inner class usage (depends_on_inner_class)
        new MemberInnerClass().modifyTarget(target);
        
        // Overload exist feature (overloaded method)
        processTarget(target, "suffix_2");
        
        // call_method: others_class type, protected modifier, overloading + super.methodName(arguments), pos: expression, returns int
        int result = new OthersClass().callMethod(target, 2); // expression pos + 2 feature
        
        // Return statement
        return target;
    }

    // Overloaded method (overload_exist feature)
    private TargetClass processTarget(TargetClass target, String suffix) throws IOException {
        target.field += suffix;
        return target;
    }

    // Local inner class feature
    private void localInnerDemo(TargetClass target) {
        class LocalInnerClass {
            public void validate(TargetClass t) {
                if (t.field == null) throw new IOException("Null field (2)"); // 2 feature + requires_throws
            }
        }
        new LocalInnerClass().validate(target);
    }
}

/**
 * TargetClass with Javadoc (target_feature)
 * Contains static nested class and field for obj.field feature
 */
public class TargetClass {
    String field; // obj.field feature for SwitchStatement

    // Static nested class (target_feature)
    public static class StaticNestedClass {
        public static void setField(TargetClass target, String value) {
            target.field = value + "_2"; // 2 feature
        }
    }
}