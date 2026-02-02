package refactoring.test;

// Source class: normal, default modifier, same package as target, features: member inner class, static nested class
class SourceClass {
    // Static nested class (source_class feature)
    static class SourceStaticNestedClass {
        String staticNestedField = "static-nested-data";
    }

    // Member inner class (source_class feature)
    abstract class SourceInnerClass {
        // Method to be refactored: abstract, base type return (int), private access, position source_inner
        // Per condition: method contains target class parameter
        private abstract int moveMethod(TargetClass targetParam);

        // Constructor with super constructor invocation (method feature: super constuctor invocation)
        SourceInnerClass() {
            super();
        }

        // Method causing override violation (override_violation feature)
        public String moveMethod() {
            // Variable call feature
            int localVar = 10;
            // Expression statement feature
            String exprStmt = "local-var: " + localVar;
            
            // No new exception thrown (no_new_exception feature)
            return exprStmt + new SourceStaticNestedClass().staticNestedField;
        }
    }

    // Subclass causing override violation (incompatible return type vs abstract method)
    class SourceInnerSubClass extends SourceInnerClass {
        // Override violation: abstract method is private int moveMethod(TargetClass), here Object return type
        @Override
        public Object moveMethod(TargetClass targetParam) {
            return targetParam.innerClass().innerField;
        }
    }
}

// Target class: normal, final modifier, same package, target_feature: member inner class
final class TargetClass {
    // Member inner class (target_feature)
    class TargetMemberInnerClass {
        String innerField = "target-inner-data";
    }

    public TargetMemberInnerClass innerClass() {
        return new TargetMemberInnerClass();
    }
}