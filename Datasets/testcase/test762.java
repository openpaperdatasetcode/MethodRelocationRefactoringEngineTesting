package com.refactoring.movemethod;

// Diff package class for ContinueStatement pos: diff_package_others
package com.refactoring.others;
public class ContinueHelper {
    protected int count = 1; // target_feature: "1"
    protected SuperClass superFieldHolder = new SuperClass(); // target_feature: super.field
}

package com.refactoring.movemethod;
import com.refactoring.others.ContinueHelper;

// Super class for super.field feature
class SuperClass {
    protected String superField = "super_value_6102";
}

// Source class: abstract normal class, same package, two member inner classes
abstract class SourceClass extends SuperClass {
    // First member inner class (source feature)
    private class FirstInnerClass {
        private String innerField1 = "inner1";
    }

    // Second member inner class (source feature)
    private class SecondInnerClass {
        private String innerField2 = "inner2";
    }

    // Method to be refactored: instance, Object return, private access, source position
    private Object methodToMove(PrivateTargetClass targetParam) {
        // per_condition: method contains target class parameter
        if (targetParam == null) {
            return null;
        }

        // requires_try_catch feature
        try {
            // ContinueStatement feature (type: ContinueStatement, modifier: protected, pos: diff_package_others)
            ContinueHelper helper = new ContinueHelper();
            for (int i = 0; i < 5; i++) {
                if (i == helper.count) { // target_feature: "1"
                    helper.superFieldHolder.superField = "updated"; // target_feature: super.field
                    continue; // ContinueStatement
                }
            }

            // expression statement feature
            String exprStmt = targetParam.targetField + "_processed";

            // variable call feature
            FirstInnerClass innerObj = new FirstInnerClass();
            String varCall = innerObj.innerField1;

            // otherObject.process(this); feature
            targetParam.anonymousProcessor.process(this);

            return exprStmt + varCall;
        } catch (Exception e) {
            // requires_try_catch (handles generic exception, no_new_exception implied)
            return e.getMessage();
        }
    }
}

// Target class: private normal class, anonymous inner class (target feature)
private class PrivateTargetClass {
    String targetField = "target_6102";
    Processor anonymousProcessor;

    public PrivateTargetClass() {
        // Anonymous inner class (target feature)
        this.anonymousProcessor = new Processor() {
            @Override
            public void process(Object obj) {
                System.out.println("Processing: " + obj.toString());
            }
        };
    }

    interface Processor {
        void process(Object obj);
    }
}