package com.refactor;

// Same package others class for ReturnStatement pos (same_package_others)
class SamePackageOthers {
    // ReturnStatement: private modifier, super.field + 2, pos: same_package_others
    private static int returnLogic(SourceClass source, TargetClass.TargetInner inner) {
        inner.setValue(source.superField + "_2"); // super.field + 2 feature
        return 2;
    }
}

// Super class for super.field feature
class SuperSourceClass {
    protected String superField = "super_value";
}

// Source class: public normal class, same package as target, member inner + static nested class
public class SourceClass extends SuperSourceClass {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "private_1"; // numbers=1 related
    // Static nested class feature
    public static class StaticNestedClass {
        public void helperMethod(TargetClass.TargetInner inner) {
            inner.setValue(outerPrivateField);
        }
    }

    // Member inner class feature
    public class MemberInnerClass {
        public void processInner(TargetClass.TargetInner inner) {
            inner.setValue(SourceClass.this.outerPrivateField);
        }
    }

    // Method: normal, base type (int) return, private access, in source class
    private int processTarget(TargetClass.TargetInner targetParam) {
        // Type declaration statement
        MemberInnerClass innerClass;
        int result = 0;
        
        // Variable call (target parameter)
        TargetClass.TargetInner inner = targetParam;
        
        // Access outer private field
        inner.setValue(SourceClass.this.outerPrivateField);
        
        // Try statement + no_new_exception
        try {
            // Loop with continue statement
            for (int i = 0; i < 5; i++) {
                // PostfixExpression: numbers=1, public modifier, exp=PostfixExpression
                public int postfix = i++; // Postfix increment (PostfixExpression) + 1 feature
                if (postfix == 1) {
                    continue; // continue statement
                }
                result += postfix;
            }
            
            // ReturnStatement from same_package_others (super.field + 2)
            return SamePackageOthers.returnLogic(this, inner);
        } catch (Exception e) {
            // No new exception - wrap existing
            throw new RuntimeException(e);
        }
    }

    // Instance method to trigger processing
    public void triggerProcessing() {
        TargetClass.TargetInner inner = new TargetClass().new TargetInner();
        processTarget(inner);
        new MemberInnerClass().processInner(inner);
        StaticNestedClass.helperMethod(inner);
    }
}

// Target class: public normal class, static nested class feature
public class TargetClass {
    // Target inner class (target_inner)
    public class TargetInner {
        private String value;
        
        public String getValue() {
            return value;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
    }

    // Static nested class (target_feature)
    public static class StaticNestedClass {
        public static void nestedMethod(TargetInner inner) {
            inner.setValue("static_" + inner.getValue());
        }
    }
}