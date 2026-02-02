package com.refactor;

import com.target.TargetGenericClass;

// Different package target helper class for VariableDeclarationStatement pos (diff_package_target)
package com.target;

/**
 * Super class for TargetGenericClass extends feature (target_feature: extends)
 */
public class TargetSuperClass<T> {
    protected T superField = (T) "super_value_1"; // super.field + 1 feature
}

/**
 * TargetGenericClass with Javadoc (target_feature: javadoc)
 * Extends TargetSuperClass (target_feature: extends)
 * Generic class with inner recursive component
 */
public class TargetGenericClass<T> extends TargetSuperClass<T> {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        private T value;
        
        public T getValue() {
            return value;
        }
        
        public void setValue(T value) {
            this.value = value;
        }
    }

    // VariableDeclarationStatement helper (diff_package_target pos)
    public static <T> void varDeclLogic(TargetGenericClass<T> target) {
        // VariableDeclarationStatement: private modifier, super.field + 1, pos: diff_package_target
        private T varDecl = target.superField; // super.field feature
        target.new TargetInnerRec().setValue((T) (varDecl + "_1")); // 1 feature
    }
}

// Back to refactor package
package com.refactor;

import com.target.TargetGenericClass;

// Source class: generic class, public modifier, same package as target, member inner + static nested class
public class SourceGenericClass<T> {
    // per_condition: source contains field of target
    private TargetGenericClass<String>.TargetInnerRec targetField = new TargetGenericClass<String>().new TargetInnerRec();

    // Static nested class feature
    public static class StaticNestedClass<T> {
        public static void processInner(TargetGenericClass<T>.TargetInnerRec inner) {
            inner.setValue((T) ("static_1")); // 1 feature (NumberLiteral related)
        }
    }

    // Member inner class feature
    public class MemberInnerClass {
        public void modifyTarget(TargetGenericClass<String>.TargetInnerRec inner) {
            inner.setValue(inner.getValue() + "_member_1"); // 1 feature
        }
    }

    // Method: instance, return TargetClass Type (TargetInnerRec), protected access, in source class
    protected TargetGenericClass<String>.TargetInnerRec processTarget(TargetGenericClass<String>.TargetInnerRec targetParam) {
        // Variable call (target parameter/field)
        TargetGenericClass<String>.TargetInnerRec innerRec = targetParam != null ? targetParam : targetField;
        
        // VariableDeclarationStatement from diff_package_target (super.field + 1)
        TargetGenericClass.varDeclLogic(new TargetGenericClass<String>());
        
        // NumberLiteral: numbers=1, public modifier, exp=NumberLiteral
        public int numberLiteral = 1; // 1 feature (NumberLiteral)
        
        // Do statement
        int count = 1;
        do {
            innerRec.setValue("do_loop_1_" + count); // 1 feature
            count++;
        } while (count <= 1); // 1 feature
        
        // NullPointerException feature
        try {
            if (innerRec == null) {
                throw new NullPointerException("Target inner rec is null (1)"); // 1 feature
            }
            
            // Use static nested class
            StaticNestedClass.processInner(innerRec);
            
            // Use member inner class
            new MemberInnerClass().modifyTarget(innerRec);
            
            // No new exception - wrap existing if any
            return innerRec;
        } catch (Exception e) {
            throw new RuntimeException(e); // No new exception instantiation
        }
    }
}