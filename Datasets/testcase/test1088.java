package com.refactoring.test;

import java.util.function.Function;

// Source record class (non-sealed modifier, same package, empty feature list)
non-sealed record SourceRecord(String data) {
    // Inner class (method_position: source_inner)
    class InnerSourceClass {
        // Method to be refactored (varargs, TargetRecord return, final access)
        final TargetRecord targetMethod(TargetRecord.InnerTargetRecord param, String... args) { // per_condition: target parameter
            // Constructor invocation
            TargetRecord targetInstance = new TargetRecord("targetData");
            TargetRecord.InnerTargetRecord innerRecord = new TargetRecord.InnerTargetRecord(2); // target_feature 2

            // SwitchStatement (private modifier, obj.field, 2, pos: source)
            private void switchBlock() {
                String objField = innerRecord.value(); // obj.field
                int num = 2; // target_feature 2
                switch (num) {
                    case 2:
                        objField = objField + "_processed";
                        break;
                    default:
                        objField = objField + "_default";
                        break;
                }
            }
            switchBlock();

            // Generic method call (public modifier, 1, source, obj.m1().m2().m3(), pos: for, return Object)
            for (int i = 0; i < 1; i++) { // 1 in method_feature
                Object genericResult = genericMethod(i).m1().m2().m3(); // source, generic type, chain call
            }

            // While statement
            int count = 0;
            while (count < args.length) {
                // Expression statement
                innerRecord = new TargetRecord.InnerTargetRecord(args[count]);
                count++;
            }

            // ExpressionMethodReference (numbers:1, volatile modifier, exp:ExpressionMethodReference)
            volatile Function<String, String> methodRef = String::toUpperCase; // 1 in numbers
            String refResult = methodRef.apply(param.value());

            // Variable call
            String targetValue = param.value();

            // Depends on inner class
            targetInstance.initLocalInner(); // call target's local inner class logic

            // No new exception (no_new_exception)
            return targetInstance;
        }

        // Generic method (public modifier, return Object, source, generic type)
        public <T> GenericChain genericMethod(T value) { // 1 in method_feature, generic type
            return new GenericChain();
        }

        // Helper class for obj.m1().m2().m3() chain call
        static class GenericChain {
            public GenericChain m1() { return this; }
            public GenericChain m2() { return this; }
            public GenericChain m3() { return this; }
        }
    }
}

// Target record class (final modifier, local inner class target_feature)
final record TargetRecord(String data) {
    // Inner target record (target_inner_rec)
    record InnerTargetRecord(String value) {}

    // Local inner class (target_feature)
    void initLocalInner() {
        class LocalInnerClass {
            String process(String val) {
                return val + "_localProcessed";
            }
        }
        LocalInnerClass local = new LocalInnerClass(); // depends_on_inner_class
        System.out.println(local.process(data));
    }
}