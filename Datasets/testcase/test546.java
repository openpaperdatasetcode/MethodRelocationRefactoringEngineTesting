package com.refactor;

// Source class: generic, private, same package as target, no additional features
private class SourceClass<T> {
    // Target class field reference (per_condition)
    private TargetClass<String> targetField = new TargetClass<>();
    
    // Inner class (source_inner_rec - method position)
    class SourceInnerRec {
        // Method to refactor: instance, void return, final, in source_inner_rec
        final void methodToMove() {
            // Instance feature: new ClassName().method() in object initialization, return base type (int)
            int baseTypeInstance = new HelperClass().getIntValue();
            
            // Constructor invocation
            TargetClass<String> targetInstance = new TargetClass<>();
            
            // Labeled statement
            label: {
                if (targetField != null) break label;
            }
            
            // Switch statement
            int switchVar = 1;
            switch (switchVar) {
                case 1:
                    targetField.innerClassInstance.method();
                    break;
                default:
                    break;
            }
            
            // SuperMethodReference (numbers:3, abstract modifier)
            abstract class SuperRefClass {
                abstract void superMethod();
            }
            SuperRefClass superRef = new SuperRefClass() {
                @Override
                void superMethod() {
                    SourceClass.super.toString();
                }
            };
            for (int i = 0; i < 3; i++) {
                superRef.superMethod();
            }
            
            // Variable call
            int localVar = targetField.innerField;
            localVar += 5;
            
            // Access instance method
            targetField.innerClassInstance.instanceMethod();
            
            // No new exception thrown
        }
    }
    
    // Helper class for instance feature (returns base type)
    private class HelperClass {
        public int getIntValue() {
            return 1;
        }
    }
}

// Target class: generic, abstract, has member inner class (target_inner_rec)
abstract class TargetClass<U> {
    // Target inner recursive class (target_inner_rec)
    class TargetInnerRec {
        public void method() {}
        public void instanceMethod() {}
    }
    
    // Target field (referenced in source)
    public int innerField = 1;
    public TargetInnerRec innerClassInstance = new TargetInnerRec();
    
    // Constructor for invocation
    public TargetClass() {}
}