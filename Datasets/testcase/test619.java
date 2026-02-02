package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

// Super class for source class extends feature
class SuperGenericClass<T> {
    protected List<String> processData(T data) {
        List<String> list = new ArrayList<>();
        list.add(data.toString() + "_super_3"); // method_feature: 3
        return list;
    }
}

// Source class: generic, strictfp modifier, same package as target, extends, two local inner classes
strictfp class SourceClass<T extends CharSequence> extends SuperGenericClass<T> {
    // Per_condition: method contains target parameter (enforced in signature)

    /**
     * Method to refactor for Move Method refactoring engine test
     * <p>Features covered: method javadoc, overriding, type declaration, variable call, requires_throws</p>
     * @param targetParam Target generic class instance
     * @return TargetClass<T> instance with processed data
     * @throws IOException if target parameter is null
     */
    // Method to refactor: instance, TargetClass return, protected access, in source class
    protected TargetClass<T> methodToRefactor(TargetClass<T> targetParam) throws IOException {
        // Requires_throws validation
        if (targetParam == null) {
            throw new IOException("Target parameter cannot be null");
        }

        // Type declaration statement
        class ProcessedData {
            private T value;
            ProcessedData(T val) { this.value = val; }
            T getValue() { return this.value; }
        }

        // Variable call (target class and its static nested class)
        T targetValue = targetParam.getValue();
        String staticNestedVal = TargetClass.StaticNested.getStaticValue(3); // method_feature: 3
        targetParam.setValue((T) (targetValue + "_" + staticNestedVal));

        // First local inner class (source feature)
        class LocalInnerProcessor1 {
            T process(T input) {
                return (T) (input + "_local1_3"); // method_feature: 3
            }
        }
        LocalInnerProcessor1 processor1 = new LocalInnerProcessor1();
        targetParam.setValue(processor1.process(targetParam.getValue()));

        // Second local inner class (source feature)
        class LocalInnerProcessor2 {
            List<String> convertToList(T input) {
                List<String> list = new ArrayList<>();
                list.add(input.toString() + "_local2_3"); // method_feature: 3
                return list;
            }
        }

        // Overriding method feature: protected modifier, 3, source, overriding, OuterClass.InnerClass.methodName(), pos=property assignment, return List<String>
        @Override
        protected List<String> processData(T data) {
            List<String> superList = super.processData(data);
            // pos: property assignment
            LocalInnerProcessor2 processor2 = new LocalInnerProcessor2();
            List<String> sourceList = processor2.convertToList(data); // method_feature: source
            // OuterClass.InnerClass.methodName()
            superList.addAll(new SourceClass.InnerHelper().helperMethod(data));
            return superList;
        }

        // Call overriding method and assign to target property (pos: property assignment)
        targetParam.setProcessedList(processData(targetValue)); // method_feature: overriding

        return targetParam; // Return TargetClass Type
    }

    // Inner helper class for OuterClass.InnerClass.methodName()
    class InnerHelper {
        List<String> helperMethod(T data) {
            List<String> list = new ArrayList<>();
            list.add(data.toString() + "_inner_helper_3"); // method_feature: 3
            return list;
        }
    }
}

// Target class: generic, abstract, same package as source, static nested class feature
abstract class TargetClass<T> {
    // Static nested class (target_feature)
    public static class StaticNested {
        public static String getStaticValue(int num) {
            return "static_nested_" + num;
        }
    }

    private T value;
    private List<String> processedList;

    public abstract T getValue();

    public abstract void setValue(T value);

    public List<String> getProcessedList() {
        return processedList;
    }

    public void setProcessedList(List<String> list) {
        this.processedList = list;
    }
}