package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Abstract super class for source class (to support abstract method)
abstract class AbstractSourceBase {
    // Abstract method template (matches refactor method specs)
    public abstract strictfp List<String> refactorMethod() throws Exception;
}

// Source normal class (private modifier, same package, two local inner classes feature)
private class SourceClass extends AbstractSourceBase {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Abstract method to refactor (strictfp, return List<String>, source position, requires_throws)
    @Override
    public strictfp List<String> refactorMethod() throws Exception { // requires_throws
        // Method types parameter is:none (no parameters)
        List<String> result = new ArrayList<>();
        
        // First local inner class (source class feature - duplicate as per spec)
        class LocalInnerOne {
            public int getValue() {
                return 10;
            }
        }

        // Second local inner class (source class feature - duplicate)
        class LocalInnerTwo {
            public String formatValue(int val) {
                return "formatted_" + val;
            }
        }

        // Variable call feature
        LocalInnerOne innerOne = new LocalInnerOne();
        LocalInnerTwo innerTwo = new LocalInnerTwo();
        int localVar = innerOne.getValue();
        result.add(innerTwo.formatValue(localVar));

        // Raw type feature
        List rawList = new ArrayList(); // raw_type (no generic parameter)
        rawList.add(localVar);
        result.addAll(rawList); // add raw list elements to typed list

        // Super constructor invocation (via anonymous inner class)
        Runnable anonRunnable = new Runnable() {
            @Override
            public void run() {
                super.getClass(); // Super constructor invocation
            }
        };
        anonRunnable.run();

        // No new exception but requires_throws (declare throws without throwing new)
        if (targetField == null) {
            throw new NullPointerException("Target field is null"); // requires_throws
        }

        // Process target field (per_condition: source contains target field)
        result.add(targetField.getTargetData());
        result.addAll(targetField.getInnerClassData());

        return result;
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass();
    }
}

// Target normal class (public modifier, member inner class feature)
public class TargetClass {
    private String targetData = "target_main_data";

    // Member inner class (target_feature)
    public class TargetMemberInner {
        public List<String> getInnerData() {
            List<String> innerList = new ArrayList<>();
            innerList.add("inner_data_1");
            innerList.add("inner_data_2");
            return innerList;
        }
    }

    public String getTargetData() {
        return targetData;
    }

    public List<String> getInnerClassData() {
        TargetMemberInner inner = new TargetMemberInner();
        return inner.getInnerData();
    }
}