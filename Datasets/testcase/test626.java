package com.refactoring.movemethod;

// Source class: strictfp normal class, same package as target, static nested + anonymous inner class
strictfp class SourceClass {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outer_private_1"; // method_feature: 1
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    static class SourceStaticNested {
        public static String processString(String s) {
            return s + "_static_processed_1"; // method_feature: 1
        }
    }

    // Instance helper method: public modifier, 1, source, instance, obj.m1().m2().m3(), pos=constructor parameter list, return void
    public void instanceHelper(TargetClass.TargetInnerRec inner) {
        // pos: the parameter list of constructors (pass method chain result to constructor)
        TargetClass.TargetInnerRec newInner = new TargetClass.TargetInnerRec(inner.m1().m2().m3()); // obj.m1().m2().m3()
        newInner.setInnerValue(SourceStaticNested.processString(newInner.getInnerValue())); // method_feature: source
    }

    // Method to refactor: instance, TargetClass return, public access, no type params, in source class
    public TargetClass methodToRefactor() {
        // Variable call (target inner recursive class)
        TargetClass.TargetInnerRec innerRec = targetField.getInnerRec();
        String innerValue = innerRec.getInnerValue();

        // Access outer private field
        innerValue += SourceClass.this.outerPrivateField; // access_outer_private

        // Pass method chain result to constructor (pos: parameter list of constructors)
        instanceHelper(new TargetClass.TargetInnerRec(innerValue + "_ctor_param_1")); // method_feature: 1

        // Requires_try_catch feature
        try {
            // Throw statement
            if (innerValue.isEmpty()) {
                throw new IllegalArgumentException("Inner value is empty"); // throw statement
            }
            innerRec.setInnerValue(SourceStaticNested.processString(innerValue));
        } catch (IllegalArgumentException e) {
            // Requires_try_catch handling
            innerRec.setInnerValue("error_" + e.getMessage() + "_1"); // method_feature: 1
        }

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Processed inner value: " + innerRec.getInnerValue());
            }
        };
        anonymousRunnable.run();

        return targetField; // Return TargetClass Type
    }
}

// Target class: private normal class, static nested class feature
private class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static String getStaticValue() {
            return "static_nested_1"; // method_feature: 1
        }
    }

    private TargetInnerRec innerRec = new TargetInnerRec();

    // Target_inner_rec (target inner recursive class)
    public class TargetInnerRec {
        private String innerValue = "inner_rec_1"; // method_feature: 1

        public TargetInnerRec() {}

        // Constructor with parameter (for pos: parameter list of constructors)
        public TargetInnerRec(String value) {
            this.innerValue = value;
        }

        // Method chaining for obj.m1().m2().m3()
        public String m1() { return innerValue + "_m1"; }
        public String m2() { return this.m1() + "_m2"; }
        public String m3() { return this.m2() + "_m3_" + TargetStaticNested.getStaticValue(); }

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }

    public TargetInnerRec getInnerRec() {
        return innerRec;
    }
}