package refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Source generic class (public modifier, same package as target, two member inner classes)
public class SourceClass<T> {
    // Precondition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>();

    // First member inner class
    public class FirstMemberInnerClass<U> {
        // Second member inner class (source_inner_rec for method position)
        public class SecondMemberInnerClass<V> {
            // Target instance method (protected access, returns TargetClass type, source_inner_rec)
            protected TargetClass<T> targetMethod() {
                // Variable call feature
                String varCall = "testVar";
                int varLength = varCall.length();

                // Raw type feature
                ArrayList rawList = new ArrayList();
                rawList.add(varCall);

                // For statement feature
                for (int i = 0; i < 5; i++) {
                    rawList.add(i);
                }

                // NullPointerException (no_new_exception - no explicit new exception instance)
                Object nullObj = null;
                try {
                    nullObj.toString(); // Triggers NPE
                } catch (NullPointerException e) {
                    // No new exception (reuse existing NPE)
                }

                // Instance nested method (default modifier, functional interfaces pos, void return)
                void instanceNestedMethod(int num) {
                    // Method features: 1 (literal), source, instance, this.methodName(arguments)
                    int literalOne = 1;
                    SourceClass<T> sourceInstance = SourceClass.this;
                    this.instanceNestedMethod(literalOne); // this.methodName(arguments)

                    // Functional interfaces pos requirement
                    Consumer<String> consumer = s -> System.out.println(s);
                    consumer.accept(varCall);
                }

                instanceNestedMethod(varLength);
                return targetField; // Return TargetClass type
            }

            // Call method (inner_class type, public modifier, Lambda expressions pos, List<String> return)
            public List<String> callMethod() {
                // Lambda expressions pos requirement
                List<String> lambdaList = new ArrayList<>();
                lambdaList.forEach(s -> System.out.println(s));

                // InstanceReference.methodName(arguments) feature
                SecondMemberInnerClass<V> instanceRef = this;
                TargetClass<T> targetInstance = instanceRef.targetMethod();

                // Normal target_feature (basic list operation)
                lambdaList.add(targetInstance.toString());
                return lambdaList;
            }
        }
    }
}

// Target generic class (protected modifier, static nested class target_feature)
protected class TargetClass<U> {
    // Static nested class target_feature
    public static class StaticNestedTargetClass<W> {
        W value;
        
        StaticNestedTargetClass(W value) {
            this.value = value;
        }
    }
}