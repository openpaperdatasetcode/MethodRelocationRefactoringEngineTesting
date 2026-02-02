package com.example;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

// Source record class (public modifier, same package, member inner class, static nested class)
public record SourceRecord(String data) {
    // Member inner class (source_class feature)
    class MemberInnerClass {}

    // Static nested class (source_class feature)
    static class StaticNestedSourceClass {}

    // Method to be refactored (instance, List<String> return, default access, position: source)
    List<String> targetMethod(TargetRecord.InnerTargetRecord param) { // per_condition: target parameter
        // Super constructor invocation (record's implicit super)
        super();

        // Constructor invocation
        TargetRecord targetInstance = new TargetRecord("targetData");
        TargetRecord.InnerTargetRecord innerRecord = new TargetRecord.InnerTargetRecord("innerData");

        // Property assignment with instance method call (pos: property assignment)
        List<String> propList;
        propList = instanceMethod(1, param); // 1, parent_class, instance, instanceReference::methodName

        // Variable call
        String targetValue = param.value();
        propList.add(targetValue);

        // While loop with call_method (pos: while)
        int count = 0;
        while (count < 1) {
            int callResult = targetInstance.callMethod(); // target, public, normal, obj.m1().m2().m3()
            propList.add(String.valueOf(callResult));
            count++;
        }

        // No new exception
        return propList;
    }

    // Instance method (default modifier, List<String> return, 1, parent_class, instance)
    List<String> instanceMethod(int num, TargetRecord.InnerTargetRecord param) {
        // instanceReference::methodName (method_feature)
        Function<String, String> methodRef = param::value; // parent_class feature via record inheritance
        List<String> list = new ArrayList<>();
        list.add(methodRef.apply("test_" + num));
        return list;
    }
}

// Target record class (public modifier, anonymous inner class target_feature)
public record TargetRecord(String data) {
    // Inner target record (target_inner_rec)
    record InnerTargetRecord(String value) {}

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in target record");
        }
    };

    // Call method (target, public modifier, normal, obj.m1().m2().m3(), return int)
    public int callMethod() {
        // obj.m1().m2().m3() chain call
        return new ChainHelper().m1().m2().m3(data.length());
    }

    // Helper class for obj.m1().m2().m3()
    static class ChainHelper {
        ChainHelper m1() { return this; }
        ChainHelper m2() { return this; }
        int m3(int num) { return num * 2; }
    }
}