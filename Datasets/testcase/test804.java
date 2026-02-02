package com.refactoring.movemethod;

// Others class for method_feature: others_class
class OthersClass {
    public static void varargsMethod(String... args) { // method_feature: varargs
        int count = 1; // method_feature: "1"
        for (String arg : args) {
            System.out.println("Arg " + count + ": " + arg);
        }
    }
}

// Parent class for super.methodName() feature
class SourceParent {
    protected void parentMethod() {
        System.out.println("Parent method called (ID:6142)");
    }
}

// Source class: normal, private modifier, same package, static nested + local inner classes
private class SourceClass extends SourceParent {
    // per_condition: source contains field of target
    private TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_6142";
    }

    // Recursive inner class (method_position: source_inner_rec)
    public class SourceInnerRecursive {
        // Static code block (for varargs method pos: Static code blocks)
        static {
            // Varargs method (type: varargs, modifier: public, pos: Static code blocks, return void)
            public void nestedVarargsMethod(String... args) {
                // method_feature: super.methodName()
                new SourceClass().parentMethod(); // method_feature: "1" context (single call)
                // method_feature: others_class + varargs
                OthersClass.varargsMethod(args);
            }
            
            // Invoke varargs method in static block
            nestedVarargsMethod("init_arg_1");
        }

        // Method to be refactored: instance, TargetClass Type return, public access, source_inner_rec
        public TargetClass methodToMove() {
            // constructor invocation (target class constructor)
            TargetClass newTarget = new TargetClass();

            // raw_type feature (raw TargetClass usage)
            TargetClass rawTarget = new TargetClass(); // raw_type

            // variable call feature
            SourceStaticNested staticNested = new SourceStaticNested();
            String varCall = staticNested.nestedField;

            // local inner class (source feature)
            class SourceLocalInner {
                private void processTarget(TargetClass target) {
                    target.setData(varCall + "_processed");
                }
            }

            // requires_try_catch feature
            try {
                new SourceLocalInner().processTarget(newTarget);
                // Call varargs method
                nestedVarargsMethod(varCall, targetField.getData());
            } catch (NullPointerException e) {
                // requires_try_catch (handle NPE)
                e.printStackTrace();
                newTarget = rawTarget;
            }

            // Update source's target field and return
            targetField = newTarget;
            return targetField; // TargetClass Type return
        }

        // Recursive method (source_inner_rec context)
        public int recursiveCall(int val) {
            if (val <= 0) return 0;
            methodToMove();
            return val + recursiveCall(val - 1);
        }
    }
}

// Target class: normal, public modifier, local inner class (target_feature)
public class TargetClass {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        // local inner class (target_feature)
        class TargetLocalInner {
            private String validateData(String val) {
                return val == null ? "default_6142" : val;
            }
        }
        this.data = new TargetLocalInner().validateData(data);
    }
}