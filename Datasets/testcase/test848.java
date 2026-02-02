/**
 * Test case for Move Method refactoring engine (ID: 6060)
 * Same package for source and target classes (implicit no package declaration)
 */
// Source class: normal, strictfp modifier, same package, static nested + local inner class
strictfp class SourceClass {
    // Static nested class (source_class feature)
    static class SourceStaticNestedClass<T extends Number> {
        // With bounds feature helper
        public static int staticMethod(int num) { // For call_method feature (static, ClassName.methodName)
            return num * 2;
        }
    }

    // Method to refactor: instance, Object return, default access, source position
    Object methodToRefactor(TargetClass targetParam) { // per_condition: contains target parameter; method types parameter is:none (only targetParam)
        // Variable call feature
        String localVar = "baseValue";
        int recursionCount = 3;

        // With bounds feature (generic with bounds)
        SourceStaticNestedClass<Integer> boundedNested = new SourceStaticNestedClass<>();

        // If statement feature
        if (targetParam == null) {
            localVar = "defaultTarget";
        } else {
            localVar += targetParam.getInnerValue();
        }

        // Local inner class (source_class feature) + recursion feature setup
        class RecursiveInnerClass {
            // Recursion method: default modifier, 3, inner_class, recursion, this.methodName(arguments), object initialization pos, void return
            void recursiveMethod(int count) {
                // Object initialization position for recursion
                RecursiveInnerClass self = new RecursiveInnerClass();
                
                // Variable call + recursion logic
                if (count > 0) {
                    localVar += "_recurse_" + count;
                    this.recursiveMethod(count - 1); // this.methodName(arguments) + recursion
                }
            }
        }

        // Recursion feature invocation (object initialization position)
        RecursiveInnerClass innerRecur = new RecursiveInnerClass();
        innerRecur.recursiveMethod(recursionCount); // 3, inner_class, recursion

        // Call method: inner_class type, default modifier, static, ClassName.methodName(arguments), object initialization pos, int return
        int callResult = SourceStaticNestedClass.staticMethod(recursionCount); // ClassName.methodName(arguments)
        localVar += "_callResult_" + callResult;

        // No new exception feature (no 'new Exception()' statements)
        return localVar;
    }

    // Local inner class demonstration (source_class feature)
    public void methodWithLocalInner() {
        class LocalInnerClass {
            public void innerMethod() {
                System.out.println("Local inner class execution");
            }
        }
        // Object initialization position for call_method
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.innerMethod();
    }
}

/**
 * TargetClass - private modifier (nested to enable), javadoc + extends + static nested class target_feature
 * Extends ParentClass to satisfy "extends" target_feature
 */
class TargetWrapper {
    // Private target class (matches modifier: private)
    private class TargetClass extends ParentClass {
        private String innerValue = "targetInnerVal";

        // Static nested class (target_feature)
        static class target {
            // Placeholder for moved method
            Object methodToRefactor(TargetClass targetParam) {
                SourceClass source = new SourceClass();
                return source.methodToRefactor(targetParam);
            }
        }

        public String getInnerValue() {
            return innerValue;
        }
    }
}

// Parent class for TargetClass "extends" feature
class ParentClass {}