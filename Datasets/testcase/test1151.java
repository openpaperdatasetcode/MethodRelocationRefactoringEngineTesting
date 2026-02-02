// Source package: com.source
package com.source;

import com.target.TargetClass;
import java.io.IOException;

// Source normal class (public modifier, different package, local inner class, member inner class)
public class SourceClass {
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "SOURCE_STATIC_5834";

    // Member inner class (source_class feature)
    public class MemberInnerClass {
        int processValue(String val) {
            return val.length() + STATIC_FIELD.length();
        }
    }

    // Method to be refactored (instance, base type return, protected access, position: source)
    protected int targetMethod(TargetClass param) throws IOException { // requires_throws, per_condition: target parameter
        // Super constructor invocation (implicit for Object superclass)
        super();

        int result = 0;

        // Try statement
        try {
            // Constructor invocation (target static nested class)
            TargetClass.StaticNestedClass staticObj = new TargetClass.StaticNestedClass();
            
            // Do statement
            int count = 0;
            do {
                // Variable call + depends_on_static_field
                String targetVal = param.getValue() + "_" + STATIC_FIELD + "_" + count;
                param.setValue(targetVal);
                
                // Local inner class (source_class feature)
                class LocalInnerClass {
                    int calculate(TargetClass target) {
                        return new MemberInnerClass().processValue(target.getValue());
                    }
                }
                
                result += new LocalInnerClass().calculate(param) + staticObj.getStaticVal();
                count++;
            } while (count < 3);

        } catch (NullPointerException e) {
            throw new IOException("Processing error", e); // requires_throws
        }

        // Return statement
        return result;
    }
}

// Target package: com.target
package com.target;

// Target normal class (default modifier, static nested class target_feature)
class TargetClass {
    private String value = "TARGET_DEFAULT_5834";

    // Static nested class (target_feature)
    public static class StaticNestedClass {
        private final int staticVal = 5834;

        public int getStaticVal() {
            return staticVal;
        }
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}