// Source enum package
package com.refactoring.source;

import com.refactoring.target.TargetEnum;

// Source class: enum class, protected modifier (via nested in protected class), different package with target, type parameter, static nested class, member inner class
protected class SourceEnumContainer<T extends CharSequence> { // Type parameter feature
    // Enum class with protected modifier (nested to achieve protected enum, direct enum can't be protected)
    protected enum SourceEnum {
        INSTANCE;

        // per_condition: source contains target enum field
        private TargetEnum targetField = TargetEnum.TARGET_INSTANCE;
        // Static field for depends_on_static_field feature
        private static String staticSourceField = "staticInitValue";

        // Static nested class (source feature)
        public static class StaticNestedSource<U> { // Type parameter in static nested class
            U nestedField;
        }

        // Member inner recursive class (source_inner_rec - method position)
        public class InnerRecursiveSource<V> { // Type parameter feature
            // Method: instance, return TargetEnum Type, private access, source_inner_rec position
            private TargetEnum moveableInstanceMethod(TargetEnum targetParam) { // per_condition: contains target parameter
                // Variable call feature
                String localVar = targetParam.getTargetField();
                localVar = staticSourceField; // Access static field

                // depends_on_static_field feature
                staticSourceField = localVar + "_updatedStatic"; // Modify static field
                targetParam.setTargetField(staticSourceField); // Use static field to update target

                // Member inner class (source feature)
                class MemberInnerSource {
                    void updateTarget(TargetEnum target) {
                        target.setTargetField(localVar);
                    }
                }
                new MemberInnerSource().updateTarget(targetParam);

                // no_new_exception feature (no custom exceptions instantiated/thrown)
                return targetParam; // Return TargetClass Type (TargetEnum)
            }
        }

        // Member inner class (source feature)
        class MemberInnerSourceBase {
            String innerField = "memberInnerValue";
        }
    }
}
// Target enum package
package com.refactoring.target;

// Target class: enum class, strictfp modifier, different package with source, empty target_feature
strictfp enum TargetEnum {
    TARGET_INSTANCE;

    private String targetField = "targetInitValue";

    // Getter/Setter for variable call feature
    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }
}