// Source package: com.source
package com.source;

import com.target.ProtectedTargetClass;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Source normal class (private modifier, different package, static nested class, local inner class)
private class SourceClass {
    // Protected outer field for access_outer_protected
    protected String outerProtectedField = "source_protected_5678";

    // Static nested class (source_class feature)
    public static class StaticNestedSourceClass {
        public static String format(String s) {
            return s.toUpperCase();
        }
    }

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String data) {
        // Method to be refactored (static, base type return, public access)
        public static int targetMethod(ProtectedTargetClass.InnerClass param) { // per_condition: target parameter (target_inner)
            // Type declaration statement
            int result = 0;
            String targetFieldVal;
            List rawList; // raw_type

            // Expression statement
            targetFieldVal = param.innerValue; // access_instance_field
            targetFieldVal = targetFieldVal + "_processed";

            // Access outer protected field
            SourceClass sourceObj = new SourceClass();
            String protectedVal = sourceObj.outerProtectedField; // access_outer_protected

            // Raw type usage
            rawList = new ArrayList(); // raw_type
            rawList.add(targetFieldVal);
            rawList.add(protectedVal);

            // Switch case
            switch (rawList.size()) {
                case 1:
                    result = 10;
                    break;
                case 2:
                    result = 20;
                    break;
                default:
                    result = -1;
                    break;
            }

            // Local inner class (source_class feature)
            class LocalInnerClass {
                int calculate() {
                    // Override violation (attempt to override final method)
                    @Override // Compile error: Cannot override final method
                    public final String toString() {
                        return param.getValue();
                    }
                    return result + rawList.size();
                }
            }
            result = new LocalInnerClass().calculate();

            // Used by reflection
            try {
                Method method = InnerSourceRecord.class.getDeclaredMethod("targetMethod", ProtectedTargetClass.InnerClass.class);
                method.setAccessible(true);
                method.invoke(null, param);
            } catch (Exception e) {
                result = -2; // No new exception, reuse existing
            }

            // Variable call
            param.setInnerValue(targetFieldVal + "_" + result);

            // No new exception
            return result;
        }

        // Final method to trigger override violation
        public final String toString() {
            return "InnerSourceRecord";
        }
    }
}

// Target package: com.target
package com.target;

// Target normal class (protected modifier, member inner class target_feature)
protected class ProtectedTargetClass {
    // Member inner class (target_inner, target_feature)
    public class InnerClass {
        // Instance field for access_instance_field
        public String innerValue = "target_inner_value_5678";

        public String getValue() {
            return innerValue;
        }

        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }
}