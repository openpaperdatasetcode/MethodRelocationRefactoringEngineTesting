package com.refactoring.movemethod;

// Source normal class (default modifier, same package as target)
class SourceClass {
    // Field of target class (satisfies per_condition: source contains target field)
    private TargetClass targetField;

    // Static nested class (source class feature)
    static class StaticNestedClass {
        private int nestedValue = 10;

        public int getNestedValue() {
            return nestedValue;
        }
    }

    // Member inner class (source_inner position for the method to refactor)
    class SourceInnerClass {
        private int innerVar = 5;

        /**
         * Javadoc for the method to be refactored (method javadoc feature)
         * This method processes target class instances and demonstrates required features
         * @param targetParam parameter of target inner class type
         */
        strictfp void refactorMethod(TargetClass.TargetInnerClass targetParam) {
            // Variable call feature
            int localVar = this.innerVar;
            StaticNestedClass staticNested = new StaticNestedClass();
            localVar += staticNested.getNestedValue(); // Depends on inner class (static nested)

            // Switch statement feature
            switch (localVar) {
                case 15:
                    // OtherObject.process(this) feature
                    AnonymousProcessor processor = new AnonymousProcessor();
                    processor.process(this);
                    break;
                case 20:
                    targetField = new TargetClass();
                    break;
                default:
                    localVar = 0;
                    break;
            }

            // Requires try-catch feature
            try {
                int processedValue = targetParam.calculateValue(localVar);
                System.out.println("Processed value: " + processedValue);
            } catch (NullPointerException e) {
                // No new exception thrown, just handle existing
                System.out.println("Null target parameter: " + e.getMessage());
            }
        }
    }

    // Anonymous inner class (source class feature)
    private interface Processor {
        void process(SourceInnerClass innerInstance);
    }

    private class AnonymousProcessor implements Processor {
        @Override
        public void process(SourceInnerClass innerInstance) {
            // Anonymous inner class implementation
            System.out.println("Processing inner instance: " + innerInstance.innerVar);
        }
    }

    // Constructor to initialize target field
    public SourceClass() {
        this.targetField = new TargetClass();
    }
}

// Target normal class (public modifier)
public class TargetClass {
    // Member inner class (target_feature: member inner class)
    public class TargetInnerClass {
        private int value;

        public int calculateValue(int input) {
            this.value = input * 2;
            return this.value;
        }
    }

    // Constructor to initialize inner class context
    public TargetClass() {
        TargetInnerClass inner = new TargetInnerClass();
        inner.calculateValue(0);
    }
}