package com.refactor.movemethod;

import java.util.function.Consumer;

// Parent class for varargs method_feature "parent_class"
class ParentClass {
    protected void varargsMethod(String... args) {}
}

// Others class for call_method (final modifier, overloading + method reference, switch pos)
class OthersClass extends ParentClass {
    // Final call method (others_class type, switch pos, returns TargetEnum)
    final TargetEnum callMethod() {
        SourceEnum source = SourceEnum.VALUE1;
        TargetEnum result = TargetEnum.TARGET1;

        // Switch position for call_method
        switch (source) {
            case VALUE1:
                // InstanceReference::methodName target_feature
                Consumer<TargetEnum> consumer = TargetEnum::toString;
                consumer.accept(result);
                // Overloading target_feature (call overloaded method)
                result = callMethodOverload(result, "arg1");
                break;
            case VALUE2:
                result = callMethodOverload(result);
                break;
        }
        return result;
    }

    // Overloaded method 1 (overloading feature)
    final TargetEnum callMethodOverload(TargetEnum param) {
        return param;
    }

    // Overloaded method 2 (overloading feature)
    final TargetEnum callMethodOverload(TargetEnum param, String... varargs) {
        return param;
    }
}

// Source enum class (private modifier, same package, two static nested classes)
private enum SourceEnum {
    VALUE1, VALUE2;

    // First static nested class (source_class feature)
    static class SourceStaticNested1 {
        String data1 = "nested1";
        public String getData() { return data1; }
    }

    // Second static nested class (source_class feature - duplicate as specified)
    static class SourceStaticNested2 {
        String data2 = "nested2";
        public String getData() { return data2; }
    }

    // Annotation for has_annotation feature
    @SuppressWarnings("unchecked")
    // Abstract method (default access, returns Object, has target param - per_condition)
    abstract Object refactorMethod(TargetEnum targetParam);

    // Concrete implementation (fulfills all method features)
    @Override
    Object refactorMethod(TargetEnum targetParam) {
        // Expression statement feature
        String localVar = "init";
        SourceStaticNested1 nested1 = new SourceStaticNested1();

        // Variable call feature
        localVar = nested1.getData();

        // Type declaration statement feature
        class LocalTypeDeclaration {
            String process(String val) { return val + "_processed"; }
        }

        // Depends_on_inner_class feature (uses local type declaration)
        LocalTypeDeclaration localType = new LocalTypeDeclaration();
        localVar = localType.process(localVar);

        // Varargs method (protected modifier, 3 + parent_class + varargs + lambda, exception throwing pos)
        protected void varargsMethod(String... args) {
            // (parameters) -> methodBody target_feature
            Consumer<String[]> lambda = (params) -> {
                if (params.length != 3) { // Number 3 feature
                    // Exception throwing statements position
                    throw new IllegalArgumentException("Expected 3 args");
                }
                localVar += params[0];
            };
            lambda.accept(args);
        }

        // No_new_exception feature (no explicit throw new Exception - catch only)
        try {
            varargsMethod("arg1", "arg2", "arg3"); // 3 args for number feature
        } catch (IllegalArgumentException e) {
            localVar = e.getMessage();
        }

        return localVar;
    }
}

// Target enum class (default modifier, empty target_feature)
enum TargetEnum {
    TARGET1, TARGET2;
}