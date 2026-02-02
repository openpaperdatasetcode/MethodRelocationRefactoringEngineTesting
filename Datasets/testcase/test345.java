package com.refactoring.movemethod;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ArrayList;

// Default modifier record source class (same package as target, no features)
record SourceRecord(String data) {
    // Varargs method (public access, returns TargetRecord, List type parameter, target param)
    public TargetRecord<String> targetMethod(List<String> listParam, TargetRecord<String> targetParam, String... args) {
        // Variable call
        String localVar = "var_call";
        List<String> resultList = new ArrayList<>();

        // Constructor invocation
        SourceRecord sourceInstance = new SourceRecord(localVar);

        // Super keywords
        super.toString();

        // Used by reflection (access target record constructor)
        try {
            Constructor<TargetRecord<String>> constructor = TargetRecord.class.getConstructor(String.class);
            TargetRecord<String> reflectedTarget = constructor.newInstance("reflection_data");
            resultList.add(reflectedTarget.data());
        } catch (Exception e) {
            // No new exception (no instantiation of new Exception)
            resultList.add("reflection_error");
        }

        // Switch statement with constructor feature (protected modifier, 1, target, constructor, instanceReference)
        switch (args.length) {
            case 1:
                TargetRecord.TargetStaticNested staticNested = new TargetRecord.TargetStaticNested();
                resultList = staticNested.protectedConstructorMethod(targetParam, 1); // instanceReference.methodName
                break;
            default:
                resultList.add(localVar);
        }

        return targetParam;
    }
}

// Default modifier record target class (type parameter + static nested class)
record TargetRecord<T>(T data) {
    // Type parameter (target_feature)
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        // Protected constructor feature method (switch pos, List<String> return)
        protected List<String> protectedConstructorMethod(TargetRecord<String> target, int num) {
            // Constructor feature: 1, target, constructor, instanceReference.methodName
            TargetRecord<String> targetInstance = new TargetRecord<>(String.valueOf(num));
            List<String> list = new ArrayList<>();
            list.add(targetInstance.data());
            list.add(target.data());
            return list;
        }
    }
}