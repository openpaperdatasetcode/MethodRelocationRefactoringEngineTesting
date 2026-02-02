// Source enum package (different from target)
package com.source;

import com.target.TargetEnum;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

// Source enum class (strictfp modifier, enum, different package, static nested class, anonymous inner class)
strictfp enum SourceEnum {
    VALUE1, VALUE2;

    // per_condition: source contains the field of the target
    private final TargetEnum targetField = TargetEnum.VALUE_A;

    // Private outer field for access_outer_private
    private String outerPrivateField = "outerPrivateValue";

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in source enum");
        }
    };

    // Method to be refactored (instance, TargetEnum return, strictfp access, position: source)
    strictfp TargetEnum targetMethod() throws SQLException, Exception { // SQLException, requires_throws
        // Super constructor invocation (enum's implicit super)
        super();

        // Overloading method call in property assignment (pos: property assignment)
        String prop = "";
        prop = overloadingMethod(1).toString(); // 1, target, overloading, outerInstance.new InnerClass().methodName()

        // Variable call
        String targetValue = targetField.getValue();

        // Access outer private field
        String privateValue = this.outerPrivateField;

        // Depends on static field
        String staticFieldValue = TargetEnum.STATIC_FIELD;

        // Overload exist (call overloading method with different param)
        overloadingMethod("suffix");

        // Requires throws (simulate SQL exception)
        if (targetValue.isEmpty()) {
            throw new SQLException("Empty target value");
        }

        return targetField;
    }

    // Overloading method 1 (private modifier, List<String> return, 1, target, overloading)
    private List<String> overloadingMethod(int num) { // outerInstance.new InnerClass().methodName()
        TargetEnum.InnerClass inner = targetField.new InnerClass();
        return inner.methodName(num);
    }

    // Overloading method 2 (overload_exist feature)
    private List<String> overloadingMethod(String suffix) {
        List<String> list = new ArrayList<>();
        list.add(suffix);
        return list;
    }
}

// Target enum package (different from source)
package com.target;

import java.util.List;
import java.util.ArrayList;

// Target enum class (default modifier, enum, local inner class target_feature)
enum TargetEnum {
    VALUE_A, VALUE_B;

    // Static field for depends_on_static_field
    public static final String STATIC_FIELD = "TargetStaticField";

    // Local inner class (target_feature)
    public class InnerClass {
        public List<String> methodName(int num) {
            List<String> list = new ArrayList<>();
            list.add("target_" + num);
            return list;
        }
    }

    public String getValue() {
        // Local inner class usage (target_feature)
        class LocalInnerClass {
            String format(String val) {
                return val + "_formatted";
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        return local.format(this.name());
    }
}