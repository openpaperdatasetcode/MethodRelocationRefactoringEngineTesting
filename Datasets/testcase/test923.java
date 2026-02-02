package com.refactoring.test;

// Target class (normal class, protected modifier, static nested class feature)
protected class TargetClass {
    static int staticField; // For IfStatement ClassName.field
    String targetField; // For per_condition (source contains this field)

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static void staticMethod(TargetInner inner) {
            inner.innerField += 2;
        }
    }

    // Target inner class (target_inner)
    public class TargetInner {
        int innerField; // For variable call/per_condition

        // Instance method for access_instance_method
        public void instanceMethod() {
            this.innerField *= 2;
        }
    }
}

// Source class (normal class, public modifier, same package, static nested + member inner class)
public class SourceClass {
    private String outerPrivateField = "outer_private_value"; // For access_outer_private

    // Static nested class (source_feature)
    static class SourceStaticNested {
        static void helperMethod(TargetClass.TargetInner inner) {
            inner.innerField += 1;
        }
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // Method to be refactored (normal, void return, private access, source_inner position)
        private void moveMethod(TargetClass.TargetInner targetParam) {
            // Per_condition: source contains the field of the target (access targetParam.innerField)
            if (targetParam == null) {
                return;
            }

            // IfStatement (static modifier, ClassName.field, 2, pos=same_package_target)
            static void ifStatement(TargetClass.TargetInner target) {
                if (TargetClass.staticField != 2) { // ClassName.field + 2 from target_feature
                    TargetClass.staticField = 2;
                    target.innerField = TargetClass.staticField;
                }
            }
            ifStatement(targetParam);

            // Variable call
            int varCall = targetParam.innerField; // Access target field (per_condition)
            targetParam.innerField = varCall + 3;

            // Access outer private field
            String privateVal = SourceClass.this.outerPrivateField;
            targetParam.innerField = targetParam.innerField + privateVal.length();

            // Access instance method
            targetParam.instanceMethod(); // Call target inner class instance method

            // Requires try-catch
            try {
                TargetClass.TargetStaticNested.staticMethod(targetParam);
                SourceStaticNested.helperMethod(targetParam);
            } catch (Exception e) {
                // Handle exception without throwing new one
            }
        }
    }
}