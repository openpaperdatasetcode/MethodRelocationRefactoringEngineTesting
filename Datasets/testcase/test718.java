package com.refactoring.movemethod;

// Source class: generic, abstract, static nested class, local inner class, same package as target
public abstract class SourceClass<T extends Number> {
    // Per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>();

    // Static nested class (source class feature)
    static class SourceStaticNestedClass<U> {
        U nestedValue;
    }

    // Super class for overriding feature
    abstract static class SuperMethodClass<V> {
        abstract TargetClass<V> methodToRefactor();
    }

    // Implementing class for overriding
    class OverrideClass extends SuperMethodClass<String> {
        // Method to refactor: overriding, TargetClass return, private access, in source class
        @Override
        private TargetClass<String> methodToRefactor() {
            // Variable call (target class field and its inner class)
            String innerData = targetField.targetInnerClass.getInnerData();
            int count = 10;
            
            // Break statement
            for (int i = 0; i < count; i++) {
                if (i == 5) {
                    break; // break statement feature
                }
                innerData += i;
            }

            // Local inner class (source class feature)
            class LocalInnerClass<W> {
                W processData(W data) {
                    return data;
                }
            }
            LocalInnerClass<String> localObj = new LocalInnerClass<>();
            targetField.targetInnerClass.setInnerData(localObj.processData(innerData));

            // No new exception feature
            try {
                Integer.parseInt(innerData);
            } catch (NumberFormatException e) {
                // No throw new exception, only handle
                targetField.targetInnerClass.setInnerData("default");
            }

            return targetField; // TargetClass Type return
        }
    }
}

// Target class: generic, private, member inner class, same package as source
private class TargetClass<U> {
    // Member inner class (target class feature)
    public class TargetInnerClass {
        private U innerData;

        public U getInnerData() {
            return innerData;
        }

        public void setInnerData(U innerData) {
            this.innerData = innerData;
        }
    }

    // Instance of member inner class (used in source class variable call)
    TargetInnerClass targetInnerClass = new TargetInnerClass();
}