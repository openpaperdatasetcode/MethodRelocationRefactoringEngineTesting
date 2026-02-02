package com.refactoring.movemethod;

// Others class for call_method feature
class OthersClass {
    private static class ChainClass {
        // Chain methods for obj.m1().m2().m3() feature
        public ChainClass m1() { return this; }
        public ChainClass m2() { return this; }
        public String m3() { return "chainResult1"; } // numbers "1"
    }

    // Static code block for call_method pos=Static code blocks
    static {
        // call_method: private modifier, static, obj.m1().m2().m3(), return String
        private static String callMethodLogic() {
            return new ChainClass().m1().m2().m3(); // obj.m1().m2().m3()
        }
        String staticBlockResult = callMethodLogic();
    }
}

// Super class for super.field feature
class SuperSourceClass {
    protected String superField = "superVal1"; // target_feature "super.field" + "1"
}

// Source class: normal class, default modifier, same package, member inner class, anonymous inner class
class SourceClass extends SuperSourceClass {
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Member inner class (source feature)
    class MemberInnerSource {
        String innerField = "innerVal1"; // numbers "1"
        void updateTarget(TargetClass.InnerTargetClass targetInner) {
            targetInner.innerField = innerField;
        }
    }

    // Method: instance, return void, default access, source position
    void moveableInstanceMethod(TargetClass.InnerTargetClass targetParam) { // per_condition: contains target_inner parameter
        // Variable call feature
        String localVar = targetParam.innerField;
        localVar = super.superField; // super.field access

        // BreakStatement: private modifier, super.field, 1, pos=same_package_others
        private void breakLogic() {
            outerLoop:
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 1) { // target_feature "1"
                        localVar = super.superField; // target_feature "super.field"
                        break outerLoop; // BreakStatement, pos=same_package_others
                    }
                }
            }
        }
        breakLogic();

        // Switch statement feature
        switch (localVar.length()) {
            case 1: // numbers "1"
                localVar = "case1";
                break;
            default:
                localVar = "default";
        }

        // Access_instance_field feature
        targetParam.innerField = localVar; // access target inner instance field
        this.targetField.publicField = localVar; // access target instance field

        // Overload_exist feature (overloaded methods)
        overloadMethod();
        overloadMethod(localVar);

        // Depends_on_inner_class feature
        MemberInnerSource innerSource = new MemberInnerSource();
        innerSource.updateTarget(targetParam); // depend on inner class method

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                localVar += "_anonymous1"; // numbers "1"
            }
        };
        anonymousRunnable.run();

        // Requires_throws feature (declare and throw checked exception)
        try {
            if (localVar.isEmpty()) throw new Exception("Empty variable");
        } catch (Exception e) {
            // Handle exception (no custom exception thrown)
        }
    }

    // Overload method 1 (overload_exist)
    void overloadMethod() {}

    // Overload method 2 (overload_exist)
    void overloadMethod(String param) {}
}

// Target class: normal class, public modifier, same package, target_feature: anonymous inner class
public class TargetClass {
    public String publicField = "targetVal1"; // numbers "1"

    // Inner target class (target_inner - method's target class)
    class InnerTargetClass {
        String innerField = "innerTarget1"; // numbers "1"
    }

    // Anonymous inner class (target_feature)
    void useAnonymousClass() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                publicField = "anonymousUpdated1"; // numbers "1"
            }
        };
        anonymousRunnable.run();
    }
}