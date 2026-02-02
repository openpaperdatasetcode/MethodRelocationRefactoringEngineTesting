// Source package: com.source
package com.source;

import com.target.TargetEnum;
import java.util.List;
import java.util.ArrayList;

/**
 * Source Enum Class (public modifier, different package from target)
 * Features: local inner class, static nested class
 */
public enum SourceEnum {
    VALUE1, VALUE2;

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        public static String helper(String s) {
            return s.toUpperCase();
        }
    }

    // Inner class (method_position: source_inner)
    public class InnerSourceClass {
        // Method to be refactored (instance, List<String> return, public access)
        // per_condition: contains target enum parameter
        public List<String> targetMethod(TargetEnum param) {
            // Local inner class (source_class feature)
            class LocalInnerClass {
                String processValue(TargetEnum enumVal) {
                    return enumVal.getValue() + "_processed";
                }
            }
            LocalInnerClass local = new LocalInnerClass();

            // Variable call (target class access)
            String targetValue = param.getValue();
            String processed = local.processValue(param);

            // Raw type (feature: raw_type)
            List rawList = new ArrayList(); // Unparameterized List (raw type)
            rawList.add(targetValue);
            rawList.add(processed);
            rawList.add(StaticNestedSourceClass.helper(targetValue));

            // No new exception (feature: no_new_exception)
            // Convert raw list to generic List<String> for return
            List<String> resultList = new ArrayList<>(rawList.size());
            for (Object obj : rawList) {
                resultList.add((String) obj);
            }

            return resultList;
        }
    }
}
// Target package: com.target
package com.target;

/**
 * Target Enum Class (protected modifier, javadoc feature)
 * Features: javadoc, anonymous inner class
 */
protected enum TargetEnum {
    TARGET1("target1_value"),
    TARGET2("target2_value");

    private final String value;

    // Anonymous inner class (target_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in TargetEnum: " + value);
        }
    };

    // Constructor
    TargetEnum(String value) {
        this.value = value;
        this.anonymousInner.run(); // Trigger anonymous inner class logic
    }

    // Variable call: getter for target value
    public String getValue() {
        return this.value;
    }
}