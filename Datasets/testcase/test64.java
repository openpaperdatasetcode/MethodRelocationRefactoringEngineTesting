package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Others class for call_method feature
class OthersClass {
    // Call_method: private modifier, static, OuterClass.InnerClass.methodName(), return List<String>
    private static class InnerOthersClass {
        static List<String> staticMethod(String param) {
            List<String> result = new ArrayList<>();
            result.add(param + "_2"); // method_feature "2"
            return result;
        }
    }
}

// Super class for super constructor invocation & super.methodName()
class SuperRecordClass {
    protected Object superAccessorMethod() {
        return "superValue";
    }
}

// Source class: record class, default modifier, same package, type parameter, static nested class, local inner class
record SourceRecord<T>(T data, TargetRecord<String> targetField) extends SuperRecordClass { // per_condition: contains target field
    // Static nested class (source feature)
    static class StaticNestedSource {
        static final int STATIC_FIELD = 1; // target_feature "1" for EnhancedForStatement
    }

    // Member inner class (method_position: source_inner)
    public class InnerSourceClass {
        /**
         * Method javadoc feature
         * Varargs method processing target record with enhanced for and accessor features
         * @param args variable arguments list
         */
        public void moveableVarargsMethod(String... args) {
            // Variable call feature
            String localVar = targetField().targetData();
            localVar = SourceRecord.this.data.toString();

            // EnhancedForStatement: private modifier, ClassName.field, 1, pos=source
            private void enhancedForLogic() {
                for (String arg : args) { // EnhancedForStatement
                    if (arg.equals(String.valueOf(StaticNestedSource.STATIC_FIELD))) { // ClassName.field + "1"
                        localVar += arg;
                    }
                }
            }
            enhancedForLogic();

            // Accessor feature: protected modifier, method_feature [2, inner_class, accessor, super.methodName()], pos=expression, return Object
            protected Object accessorLogic() {
                Object accessorResult = super.superAccessorMethod(); // super.methodName() + pos=expression
                InnerAccessorClass innerAccessor = new InnerAccessorClass();
                accessorResult = innerAccessor.getInnerValue("2"); // method_feature "2", "inner_class", "accessor"
                return accessorResult;
            }
            Object accessorObj = accessorLogic(); // expression position

            // Super constructor invocation feature
            new SuperRecordClass() {};

            // Call_method: others_class, pos=ternary operators, return List<String>
            List<String> callResult = (localVar.isEmpty()) 
                ? OthersClass.InnerOthersClass.staticMethod(localVar) // OuterClass.InnerClass.methodName()
                : OthersClass.InnerOthersClass.staticMethod(accessorObj.toString());

            // Local inner class (source feature)
            class LocalInnerSource {
                void processResult() {
                    localVar = callResult.get(0);
                }
            }
            new LocalInnerSource().processResult();

            // no_new_exception feature (no custom exceptions instantiated/thrown)
        }

        // Inner class for accessor feature (inner_class)
        private class InnerAccessorClass {
            Object getInnerValue(String val) {
                return val + "_inner";
            }
        }
    }
}

// Target class: record class, public modifier, same package, target_feature: implements, anonymous inner class
public record TargetRecord<U>(U targetData) implements Runnable { // implements feature
    @Override
    public void run() {
        // Anonymous inner class (target_feature)
        Consumer<String> anonymousConsumer = new Consumer<>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        anonymousConsumer.accept(targetData.toString());
    }
}