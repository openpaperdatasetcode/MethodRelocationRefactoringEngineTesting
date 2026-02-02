package com.refactor.movemethod;

import com.refactor.other.DiffPackageClass; // Diff package for VariableDeclarationStatement pos

// Source abstract generic class (abstract modifier, same package, static nested + anonymous inner class)
abstract class SourceGenericClass<T extends Number> {
    // Per_condition: source contains target class field
    protected TargetGenericClass<String> targetField = new TargetGenericClass<>("target_field_1");
    
    // Private field for access_outer_private feature
    private String outerPrivateField = "outer_private_1";
    
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "static_field_1"; // Value 1 for target_feature

    // Static nested class (source_class feature)
    public static class SourceStaticNested<U> {
        U nestedValue;
        public SourceStaticNested(U value) { this.nestedValue = value; }
        public U getNestedValue() { return nestedValue; }
    }

    // Anonymous inner class (source_class feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class: " + STATIC_FIELD);
        }
    };

    // Overloading method (public access, void return, source position)
    public void refactorMethod(TargetGenericClass<T>.TargetInner targetParam) throws IllegalArgumentException { // requires_throws
        // Variable call feature
        sourceAnonymous.run();
        SourceStaticNested<Integer> staticNested = new SourceStaticNested<>(1); // Value 1
        String varCall = staticNested.getNestedValue().toString();

        // Access_outer_private feature
        varCall += SourceGenericClass.this.outerPrivateField;

        // Depends_on_static_field feature
        varCall += STATIC_FIELD;

        // Type declaration statement feature
        class LocalTypeDeclaration<U> {
            U process(U val) { return val; }
        }
        LocalTypeDeclaration<String> localType = new LocalTypeDeclaration<>();
        varCall = localType.process(varCall);

        // Constructor invocation feature
        TargetGenericClass<T>.TargetInner newInner = targetParam.new TargetInner((T) Integer.valueOf(1));

        // VariableDeclarationStatement (private modifier, this.field=1, pos: diff_package_others)
        DiffPackageClass diffObj = new DiffPackageClass();
        private {
            this.targetField = new TargetGenericClass<>("updated_1"); // this.field target_feature (value 1)
            diffObj.field = this.targetField.getValue(); // Diff package position
        }

        // No_new_exception (implicit - only standard exception in requires_throws)
        if (targetParam == null) {
            throw new IllegalArgumentException("Target param is null (code: 1)"); // requires_throws
        }
    }

    // Overload method (overload_exist feature)
    public void refactorMethod(TargetGenericClass<T>.TargetInner targetParam, String extra) throws IllegalArgumentException {
        refactorMethod(targetParam);
        System.out.println(extra + "_" + STATIC_FIELD);
    }
}

// Target strictfp generic class (strictfp modifier, member inner class target_feature)
strictfp class TargetGenericClass<U> {
    private U value;

    public TargetGenericClass(U value) {
        this.value = value;
    }

    // Member inner class (target_feature - target_inner)
    public class TargetInner {
        private U innerValue;

        public TargetInner(U innerValue) {
            this.innerValue = innerValue;
        }

        public U getInnerValue() { return innerValue; }
    }

    public U getValue() { return value; }
}

// Diff package class (for VariableDeclarationStatement pos: diff_package_others)
package com.refactor.other;
import com.refactor.movemethod.TargetGenericClass;

public class DiffPackageClass {
    public String field;

    public <T> void process(TargetGenericClass<T>.TargetInner inner) {
        this.field = inner.getInnerValue().toString();
    }
}