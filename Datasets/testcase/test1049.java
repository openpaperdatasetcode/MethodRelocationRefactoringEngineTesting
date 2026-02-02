// Source package
package com.refactoring.source;

import com.refactoring.target.TargetEnum;
import static org.junit.Assert.assertEquals;

// Source enum class (default modifier, different package from target, no features)
enum SourceEnum {
    INSTANCE;

    protected String outerProtectedField = "protectedValue";

    // Inner recursive class for method position (source_inner_rec)
    class SourceInnerRec {
        private String innerField = "innerValue";

        // Method to be refactored (instance, void return, private access, target=target)
        private void moveMethod(TargetEnum targetParam) {
            // Uses outer this
            String outerVal = SourceEnum.this.outerProtectedField;
            // Variable call
            String varCall = this.innerField;
            
            // Loop with continue statement
            for (int i = 0; i < 5; i++) {
                if (i == 2) {
                    continue;
                }
                // AssertStatement (private modifier, this.field, 1, pos=same_package_others)
                com.refactoring.target.SamePackageOthers.assertField(targetParam, this.innerField, 1);
            }
            
            // Access outer protected field
            targetParam.setField(outerVal);
            // No new exception
        }
    }
}

// Target package
package com.refactoring.target;

/**
 * Javadoc feature for TargetEnum
 */
// Target enum class (protected modifier, extends, static nested class)
protected enum TargetEnum extends BaseEnum {
    VALUE1, VALUE2;

    private String field;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static void processField(TargetEnum target) {
            target.field = "processed";
        }
    }

    public void setField(String value) {
        this.field = value;
    }

    public String getField() {
        return this.field;
    }
}

// Same package others for AssertStatement position
class SamePackageOthers {
    // AssertStatement implementation (private modifier implied via package-private, this.field, 1)
    static void assertField(TargetEnum target, String expected, int num) {
        assertEquals(num, target.getField() != null ? 1 : 0);
        assertEquals(expected, target.getField());
    }
}

// Extended class for TargetEnum (extends feature)
class BaseEnum {}