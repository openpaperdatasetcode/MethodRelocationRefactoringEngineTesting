import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature
@interface AbstractMethodAnno {}

// Parent class for super.field feature
class ParentClass {
    protected int superField = 1; // Matches "1" in DoStatement target_feature
}

// Interface for target_class implements feature
interface TargetInterface {
    int processValue(int value);
}

// Source class: generic, public modifier, same package, two member inner classes
public class SourceClass<T extends Number> extends ParentClass {
    // Instance field for access_instance_field feature
    private T instanceField;

    // First member inner class (source_class feature)
    public class FirstMemberInnerClass<U extends CharSequence> {
        public U processString(U input) {
            return input;
        }
    }

    // Second member inner class (source_class feature)
    public class SecondMemberInnerClass<V extends List<T>> {
        // Abstract method to refactor: base type (int) return, public access, source_inner position
        @AbstractMethodAnno // has_annotation feature
        public abstract int abstractMethod(TargetClass<T> targetParam); // per_condition: contains target parameter

        // DoStatement feature: static modifier, super.field, 1, pos: diff_package_target
        public static int staticDoStatement() {
            int count = 0;
            do {
                ParentClass parent = new ParentClass();
                parent.superField = 1; // super.field = 1
                count += parent.superField;
                if (count >= 3) break; // break statement feature
            } while (count < 3);
            return count;
        }

        // Overload exist feature (overloaded methods)
        public int overloadMethod(int num) {
            return num * 2;
        }

        public int overloadMethod(int num, T value) { // overload variant
            return num + value.intValue();
        }

        // Concrete implementation for abstract method (refactoring target)
        public int concreteImpl(TargetClass<T> targetParam) {
            // Variable call feature
            int localVar = 1;
            T value = (T) Integer.valueOf(5);

            // Access instance field (access_instance_field feature)
            SourceClass.this.instanceField = value;
            localVar += SourceClass.this.instanceField.intValue();

            // With bounds feature (generic with bounds)
            class BoundedClass<W extends T & Comparable<T>> {
                W boundedField = (W) value;
            }
            BoundedClass<T> boundedInstance = new BoundedClass<>();
            localVar += boundedInstance.boundedField.intValue();

            // Enhanced for statement (enhganced for statement)
            List<T> numList = new ArrayList<>();
            numList.add((T) Integer.valueOf(1));
            numList.add((T) Integer.valueOf(2));
            for (T num : numList) {
                localVar += num.intValue();
            }

            // DoStatement feature invocation (diff_package_target pos)
            localVar += staticDoStatement();

            // Overload exist feature usage
            localVar += this.overloadMethod(localVar);
            localVar += this.overloadMethod(localVar, value);

            // No new exception feature (no 'new Exception()' statements)
            return localVar + targetParam.getInnerValue();
        }
    }
}

/**
 * TargetClass - generic, protected modifier, javadoc + implements target_feature
 * Implements TargetInterface to satisfy "implements" target_feature
 * @param <T> Generic type parameter (extends Number for consistency with source)
 */
protected class TargetClass<T extends Number> implements TargetInterface {
    private T innerValue;

    public TargetClass(T value) {
        this.innerValue = value;
    }

    // Implements interface method
    @Override
    public int processValue(int value) {
        return value + innerValue.intValue();
    }

    // Target inner recursive structure for method relocation
    public static class target_inner_rec {
        // Placeholder for moved abstract method (concrete impl)
        @AbstractMethodAnno
        public int abstractMethod(TargetClass<Integer> targetParam) {
            SourceClass<Integer> source = new SourceClass<>();
            SourceClass<Integer>.SecondMemberInnerClass<List<Integer>> inner = source.new SecondMemberInnerClass<>();
            return inner.concreteImpl(targetParam);
        }
    }

    public int getInnerValue() {
        return innerValue.intValue();
    }
}