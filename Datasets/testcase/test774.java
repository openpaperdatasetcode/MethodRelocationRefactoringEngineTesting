package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

// Functional interface for target class implements feature
interface DataProcessor {
    int process(int val);
}

// Source class: normal, private modifier, same package, member inner + local inner classes
private class SourceClass {
    // per_condition: source contains field of target
    private TargetClass targetField = new TargetClass();

    // Instance field for access_instance_field feature
    private int instanceField = 6138;

    // Member inner class (source feature)
    private class SourceMemberInner {
        private String innerField = "source_member_inner_6138";
    }

    // Overloaded method 1 (method type: overloading)
    public int methodToMove(TargetClass targetParam) {
        return methodToMove(targetParam, 0);
    }

    // Overloaded method 2 (method to be refactored: overloading, base type return, public access)
    public int methodToMove(TargetClass targetParam, int offset) {
        // per_condition: method contains target class parameter (via overload)
        if (targetParam == null) {
            targetParam = targetField;
        }

        // access_instance_field feature
        this.instanceField += offset;
        int instanceVal = this.instanceField;

        // variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerField;

        // do statement feature
        int doCount = 0;
        do {
            instanceVal += doCount;
            doCount++;
        } while (doCount < 3);

        // super keywords feature (inner class context)
        class SuperKeywordClass extends SourceClass {
            public String getSuperStr() {
                return super.toString(); // super keywords usage
            }
        }
        String superStr = new SuperKeywordClass().getSuperStr();

        // used_by_reflection feature
        try {
            Method method = TargetClass.class.getDeclaredMethod("processData", int.class);
            method.invoke(targetParam, instanceVal);
        } catch (ReflectiveOperationException e) {
            // throw statement feature (checked exception wrapped in runtime)
            throw new RuntimeException("Reflection error", e);
        }

        // Local inner class (source feature)
        class SourceLocalInner {
            private int validate(int val) {
                return val > 0 ? val : 0;
            }
        }
        int validatedVal = new SourceLocalInner().validate(instanceVal);

        // no_new_exception (no explicit new Exception instantiation beyond throw statement requirement)
        return validatedVal + varCall.length() + superStr.length();
    }
}

// Target class: normal, public modifier, implements + member inner class (target_features)
public class TargetClass implements DataProcessor {
    // Member inner class (target_feature)
    public class TargetInner {
        private String innerField = "target_inner_6138";

        public String getProcessed(String val) {
            return val + "_processed";
        }
    }

    @Override
    public int process(int val) {
        return val * 2;
    }

    public int processData(int val) {
        return process(val);
    }

    // Call method: target type, strictfp modifier, instance, lambda, pos=collection operations, returns List<String>
    public strictfp List<String> callMethod() {
        SourceClass source = new SourceClass();
        TargetInner targetInner = new TargetInner();
        
        // pos: collection operations, target_feature: (parameters) -> methodBody (lambda)
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        
        // target_feature: instance + lambda expression
        List<String> result = intList.stream()
                .map(num -> { // (parameters) -> methodBody
                    int sourceResult = source.methodToMove(this, num); // instance method call
                    return targetInner.getProcessed(String.valueOf(sourceResult));
                })
                .collect(Collectors.toList());
        
        return result;
    }
}