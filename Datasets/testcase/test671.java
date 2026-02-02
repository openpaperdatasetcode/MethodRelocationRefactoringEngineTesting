package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Abstract base class for abstract method feature (sub_class)
abstract class AbstractMethodClass {
    // Abstract method: public modifier, 1, sub_class, abstract, obj.m1().m2().m3(), pos=switch, return List<String>
    public abstract List<String> abstractProcess(TargetClass.TargetInner inner);
}

// Subclass for method_feature: sub_class
class SubClassImpl extends AbstractMethodClass {
    @Override
    public List<String> abstractProcess(TargetClass.TargetInner inner) {
        List<String> result = new ArrayList<>();
        // pos: switch statement
        switch (inner.getInnerValue().length()) {
            case 1: // method_feature: 1
                // obj.m1().m2().m3() feature
                String chain = inner.m1().m2().m3(); // method_feature: sub_class
                result.add(chain + "_1"); // method_feature: 1
                break;
            default:
                result.add("default_1"); // method_feature: 1
                break;
        }
        return result;
    }
}

// Source class: private normal class, same package as target, two static nested classes
private class SourceClass {
    // Per_condition: source contains target class field
    private final TargetClass targetField = new TargetClass();

    // First static nested class (source feature)
    static class FirstStaticNested {
        public static int processInt(int val) {
            return val * 1; // method_feature: 1
        }
    }

    // Second static nested class (source feature)
    static class SecondStaticNested {
        public static int adjustInt(int val) {
            return val + 1; // method_feature: 1
        }
    }

    /**
     * Method to refactor: varargs, base type (int) return, private access, method javadoc
     * <p>Features: abstract method call, expression statement, variable call, no_new_exception</p>
     * @param innerParams Varargs of target inner class instances
     * @return Processed integer result
     */
    private int methodToRefactor(TargetClass.TargetInner... innerParams) {
        int result = 0;
        AbstractMethodClass abstractInstance = new SubClassImpl(); // method_feature: sub_class

        // Process varargs parameters
        for (TargetClass.TargetInner param : innerParams) {
            // Variable call (target inner class)
            String innerValue = param.getInnerValue();
            result += innerValue.length();

            // Abstract method feature call (pos=switch)
            List<String> abstractResult = abstractInstance.abstractProcess(param);
            result += abstractResult.size() * 1; // method_feature: 1

            // Expression statement
            innerValue = FirstStaticNested.processInt(innerValue.length()) + "_expr_1"; // method_feature: 1
            param.setInnerValue(innerValue); // expression statement
            result = SecondStaticNested.adjustInt(result); // expression statement

            // No_new_exception feature
            try {
                Integer.parseInt(innerValue);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                param.setInnerValue("formatted_" + innerValue);
                result += 1; // method_feature: 1
            }
        }

        // Variable call for targetField (per_condition)
        result += targetField.getInner().getInnerValue().length();

        return result; // Base type (int) return
    }

    // Helper method to invoke refactored method
    public int invokeVarargsMethod(TargetClass.TargetInner... inners) {
        return methodToRefactor(inners);
    }
}

// Target class: no modifier, local inner class feature
class TargetClass {
    private final TargetInner inner = new TargetInner();

    // Target_inner (target inner class)
    public class TargetInner {
        private String innerValue = "target_1"; // method_feature: 1

        // Method chaining for obj.m1().m2().m3()
        public String m1() { return innerValue + "_m1"; }
        public String m2() { return this.m1() + "_m2"; }
        public String m3() { return this.m2() + "_m3_1"; } // method_feature: 1

        // Local inner class (target_feature)
        public String processValue(String input) {
            class LocalInnerProcessor {
                String process(String s) {
                    return s + "_local_1"; // method_feature: 1
                }
            }
            return new LocalInnerProcessor().process(input);
        }

        // Variable call getters/setters
        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String value) {
            this.innerValue = value;
        }
    }

    // Getter for inner class (per_condition variable call)
    public TargetInner getInner() {
        return inner;
    }
}