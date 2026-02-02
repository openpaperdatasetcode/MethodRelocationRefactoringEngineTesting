package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Functional interface for source class implementation
interface DataHandler<T extends CharSequence> {
    void handle(T data);
}

// Source normal class (private modifier, same package, implements + member inner + local inner class)
private class SourceClass implements DataHandler<String> {
    // Private field for access_outer_private feature
    private int outerPrivateField = 2;

    // Member inner class (source feature)
    private class SourceMemberInner {
        // Method to be moved (normal, void return, default access, source_inner position)
        void moveableMethod(TargetClass targetParam) {
            // LabeledStatement (private modifier, source pos, this.field + 2 features)
            private int labeledVar = this.outerPrivateField + 2; // this.field (outerPrivateField) + 2
            label:
            while (labeledVar > 0) { // While statement feature
                labeledVar--;
                if (labeledVar == 0) break label;
                
                // Expression statement feature
                String exprStmt = targetParam.staticNested.targetField + labeledVar;
                
                // Variable call feature
                String varCall = exprStmt + new SourceLocalInner().localField;
                
                // Access_outer_private feature
                int outerAccess = SourceClass.this.outerPrivateField;
                
                // With bounds feature (generic with bounds)
                DataHandler<? extends String> boundedHandler = data -> System.out.println(data);
                
                // No new exception instantiation (no_new_exception feature)
                System.out.println(varCall + outerAccess);
            }

            // Overload_exist feature (overloaded method)
            moveableMethod(targetParam, "extra");
            
            // Call_method feature (source, protected, overriding + outerInstance.new InnerClass().methodName() in object initialization)
            List<String> callResult = new SourceClass().new SourceMemberInner() {
                @Override
                protected List<String> callMethod() { // Overriding feature
                    return new ArrayList<>();
                }
            }.callMethod();
        }

        // Overloaded method for overload_exist feature
        void moveableMethod(TargetClass targetParam, String extra) {
            System.out.println(extra + targetParam.staticNested.targetField);
        }

        // Local inner class (source feature)
        private class SourceLocalInner {
            int localField = 10;
        }

        // Call_method base method (protected, returns List<String>)
        protected List<String> callMethod() {
            return new ArrayList<>();
        }

        // Implementation of DataHandler interface
        @Override
        public void handle(String data) {
            System.out.println(data);
        }
    }
}

// Target normal class (private modifier, static nested class target feature)
private class TargetClass {
    // Static nested class (target feature)
    static class TargetStaticNested {
        String targetField = "target_static_field";
    }

    // Instance of static nested class
    TargetStaticNested staticNested = new TargetStaticNested();
}