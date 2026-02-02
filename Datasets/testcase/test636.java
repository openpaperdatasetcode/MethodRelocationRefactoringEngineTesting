package com.refactoring.movemethod;

// Source class: public normal class, same package as target, two static nested classes
public class SourceClass {
    // First static nested class (source feature)
    static class FirstStaticNested {
        public static int processInt(int val) {
            return val * 2; // method_feature: 2
        }
    }

    // Second static nested class (source feature)
    static class SecondStaticNested {
        public static int adjustInt(int val) {
            return val + 2; // method_feature: 2
        }
    }

    // Instance helper method: private modifier, 2, inner_class, instance, obj.m1().m2().m3(), pos=property assignment, return TargetClass Type
    private TargetClass instanceHelper(TargetClass.TargetInner inner) {
        TargetClass target = new TargetClass(); // constructor invocation
        // pos: property assignment
        String chainResult = inner.m1().m2().m3(); // obj.m1().m2().m3(), method_feature: inner_class
        target.setInnerValue(chainResult); // property assignment
        target.setCounter(FirstStaticNested.processInt(2)); // method_feature: 2
        return target; // method_feature: instance
    }

    // Method to refactor: accessor (getter-like), base type (int) return, private access, in source class
    private int getTargetValue(TargetClass... targetParams) {
        // Constructor invocation (target class)
        TargetClass defaultTarget = new TargetClass();
        
        int result = 0;
        // Enhanced for statement
        for (TargetClass target : targetParams) {
            // Variable call (target class and inner class)
            TargetClass.TargetInner inner = target.getInner();
            String innerValue = inner.getInnerValue();
            
            try {
                // Requires_try_catch feature
                result += Integer.parseInt(innerValue);
                // Instance helper method call (property assignment)
                TargetClass processedTarget = instanceHelper(inner);
                result += processedTarget.getCounter();
                
                // Use static nested classes (source feature)
                result = FirstStaticNested.processInt(result);
                result = SecondStaticNested.adjustInt(result);
            } catch (NumberFormatException e) {
                // Requires_try_catch handling
                result += 2; // method_feature: 2
                inner.setInnerValue("default_2"); // method_feature: 2
            }
        }

        // return this; (return current instance in nested context, simulated for accessor)
        class AccessorInner {
            SourceClass getThis() {
                return SourceClass.this; // return this; feature
            }
        }
        new AccessorInner().getThis();

        // Return statement (base type)
        return result;
    }

    // Accessor method (compliant with "accessor" type)
    public int getProcessedTargetValue(TargetClass target) {
        // Per_condition: method contains target parameter
        return getTargetValue(target);
    }
}

// Target class: no modifier, member inner class feature
class TargetClass {
    private String innerValue;
    private int counter;
    private final TargetInner inner = new TargetInner();

    // Member inner class (target_feature)
    public class TargetInner {
        private String innerField = "inner_2"; // method_feature: 2

        // Method chaining for obj.m1().m2().m3()
        public String m1() { return innerField + "_m1"; }
        public String m2() { return this.m1() + "_m2"; }
        public String m3() { return this.m2() + "_m3_2"; } // method_feature: 2

        public String getInnerValue() {
            return innerField;
        }

        public void setInnerValue(String value) {
            this.innerField = value;
        }
    }

    // Constructor invocation (used in helper method)
    public TargetClass() {}

    // Variable call getters/setters
    public TargetInner getInner() {
        return inner;
    }

    public String getInnerValue() {
        return innerValue;
    }

    public void setInnerValue(String value) {
        this.innerValue = value;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}