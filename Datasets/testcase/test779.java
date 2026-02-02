package com.refactoring.movemethod;

// Source generic class: public modifier, same package, two static nested classes
public class SourceClass<T> {
    // First static nested class (source feature)
    private static class FirstStaticNested {
        private String nestedField1 = "nested1_6129";
    }

    // Second static nested class (source feature)
    private static class SecondStaticNested {
        private int nestedField2 = 6129;
    }

    // Inner class for call_method type: inner_class
    protected class SourceInnerClass {
        // Call method: inner_class type, protected modifier, static, obj.m1().m2().m3(), pos=exception handling, returns void
        protected static void callMethod(SourceClass<T> source, TargetClass<T> target) {
            try {
                // pos: exception handling statements
                int result = source.methodToMove(target);
                // target_feature: obj.m1().m2().m3() (chained method calls)
                target.getStaticNested().m1().m2().m3(result);
            } catch (NullPointerException e) {
                // Exception handling context (pos requirement)
                e.printStackTrace();
            }
        }
    }

    // Method to be refactored: instance, base type (int) return, default access, source position
    int methodToMove(TargetClass<T> targetParam) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            // constructor invocation (target class constructor)
            targetParam = new TargetClass<>();
            return 0;
        }

        // type declaration statement feature
        FirstStaticNested staticNested = new FirstStaticNested();
        SecondStaticNested secondStatic = new SecondStaticNested();
        int baseVal = secondStatic.nestedField2;

        // variable call feature
        String varCall = staticNested.nestedField1;

        // expression statement feature
        targetParam.getStaticNested().nestedVal = varCall + "_processed";
        baseVal += varCall.length();

        // no_new_exception (no explicit new Exception instantiation)
        return baseVal;
    }
}

// Target generic class: protected modifier, static nested class (target_feature)
protected class TargetClass<T> {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public String nestedVal;

        // Chained methods for obj.m1().m2().m3() feature
        public TargetStaticNested m1() { return this; }
        public TargetStaticNested m2() { return this; }
        public void m3(int val) { this.nestedVal += "_" + val; }
    }

    public TargetStaticNested getStaticNested() {
        return new TargetStaticNested();
    }
}