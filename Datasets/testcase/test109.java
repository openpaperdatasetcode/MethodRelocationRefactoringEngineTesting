package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;

// Source class: default modifier, same package, type parameter, static nested, member inner
class SourceClass<T extends CharSequence> {
    private String sourceField = "sourceField";
    
    // Static nested class (source feature)
    static class StaticNestedSource {
        final List<String> helperMethod() {
            return new ArrayList<>();
        }
    }
    
    // Member inner class (source feature)
    class MemberInnerSource {
        void sampleMethod() {}
    }
    
    // Super class for overriding method
    abstract class SuperClass {
        public abstract void overrideMethod(TargetClass.MemberInnerTarget param);
    }
    
    // Method: overriding, void return, final access, source position
    final class OverridingClass extends SuperClass {
        @Override
        public final void overrideMethod(TargetClass.MemberInnerTarget param) { // per_condition: contains target parameter
            // TypeDeclarationStatement: protected, this.field, 2 (source pos)
            protected class LocalTypeDeclaration {
                private int num = 2;
                public void useField() {
                    System.out.println(SourceClass.this.sourceField); // uses_outer_this & this.field
                }
            }
            
            // Type declaration statement feature
            LocalTypeDeclaration localType = new LocalTypeDeclaration();
            localType.useField();
            
            // Variable call feature
            String localVar = sourceField;
            int counter = 0;
            
            // Continue statement feature
            for (int i = 0; i < 5; i++) {
                if (i == 2) {
                    counter++;
                    continue;
                }
                System.out.println(localVar);
            }
            
            // Object initialization with call_method (others_class, final, abstract, new ClassName().method())
            List<String> callResult = new OtherAbstractClass() {
                @Override
                public final List<String> abstractMethod() {
                    return new StaticNestedSource().helperMethod();
                }
            }.abstractMethod();
        }
    }
}

// Target class: protected modifier, empty target_feature
protected class TargetClass {
    // Member inner class (target_inner for method's target class)
    class MemberInnerTarget {
        String targetField = "targetField";
    }
}

// Call_method: others_class, final modifier, abstract, new ClassName().method()
abstract class OtherAbstractClass {
    public abstract List<String> abstractMethod();
}