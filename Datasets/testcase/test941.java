package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Interface for Source record implements feature
interface RecordProcessable {
    void process(String data);
}

// Abstract target record class: abstract modifier, member inner class (target_feature)
public abstract sealed class TargetClass<T> permits ConcreteTargetClass {
    public abstract T getValue();

    // Member inner class (target_inner - target class of method)
    public class TargetInner {
        private T innerValue;

        public TargetInner(T innerValue) {
            this.innerValue = innerValue;
        }

        // Instance method for access_instance_method feature
        public T getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(T innerValue) {
            this.innerValue = innerValue;
        }
    }
}

// Concrete implementation of abstract target record
public final class ConcreteTargetClass<T> extends TargetClass<T> {
    private final T value;

    public ConcreteTargetClass(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}

// Sealed source record class: sealed modifier, implements interface, static nested + member inner class (source_feature)
public sealed record SourceClass<String>(String data) implements RecordProcessable permits SourceSubClass {
    private int x = 2; // For OuterClass.this.x feature

    // Static nested class (source_feature)
    public static class SourceStaticNested<U> {
        public static U staticHelper(U data) {
            return data;
        }
    }

    // Member inner class (source_inner - method_position)
    public class SourceInner {
        // Instance method: protected access, void return type, target parameter (per_condition)
        protected void refactorMethod(TargetClass<String> targetParam) {
            // Super constructor invocation (method feature) - simulate via target inner class constructor
            TargetClass<String>.TargetInner targetInner = targetParam.new TargetInner(targetParam.getValue());

            // Variable call feature
            String varCall = targetParam.getValue();

            // PrefixExpression feature: numbers=2, private modifier, exp=PrefixExpression
            private int prefixResult = ++SourceClass.this.x; // PrefixExpression (++) with 2 (initial x=2 → 3)

            // OuterClass.this.x feature
            int outerX = SourceClass.this.x;

            // Raw type feature
            List rawList; // Raw type declaration
            rawList = new ArrayList(); // Raw type initialization
            rawList.add(varCall);

            // Expression statement feature
            targetInner.setInnerValue(SourceStaticNested.staticHelper(varCall + "_modified"));

            // Empty statement feature
            ; // Empty statement

            // Break statement feature
            int count = 0;
            while (true) {
                count++;
                rawList.add("iteration_" + count);
                if (count == prefixResult) {
                    break; // Break statement
                }
            }

            // Access instance method feature
            String innerVal = targetInner.getInnerValue();

            // No new exception thrown feature
            System.out.printf("Outer X: %d, Prefix Result: %d, Inner Value: %s, Raw List Size: %d%n",
                    outerX, prefixResult, innerVal, rawList.size());
        }
    }

    @Override
    public void process(String data) {
        System.out.println("Processed: " + data);
    }
}

// Permitted subclass of sealed SourceClass
public final class SourceSubClass extends SourceClass {
    public SourceSubClass(String data) {
        super(data);
    }
}