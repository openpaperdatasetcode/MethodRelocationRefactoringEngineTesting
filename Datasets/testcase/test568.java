package com.refactor;

import com.other.DiffPackageClass;
import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Function;

// Source class: normal, default modifier, same package as target, no additional features
class SourceClass {
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();

    // Annotation for has_annotation feature
    @Retention(RetentionPolicy.RUNTIME)
    @interface MethodAnnotation {}

    /**
     * Method to refactor: instance, List<String> return, public access modifier, in source class
     */
    @MethodAnnotation // has_annotation
    public List<String> methodToMove() {
        // Super constructor invocation (implicit super() for Object constructor)
        super();

        // ContinueStatement (private modifier, this.field, 2, pos: diff_package_others)
        private DiffPackageClass diffPackageInstance = new DiffPackageClass();
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                diffPackageInstance.field = 2;
                continue; // Continue statement
            }
        }

        // Type declaration statement
        class LocalTypeDeclaration {}
        LocalTypeDeclaration localType = new LocalTypeDeclaration();

        // Expression statement
        int exprVar = 2;
        exprVar += 1;

        // Accessor feature (public modifier, 1, target, accessor, lambda (parameters) -> methodBody in switch)
        int switchVar = 1;
        TargetClass.TargetInner innerInstance = null;
        switch (switchVar) {
            case 1:
                Function<String, TargetClass> accessorLambda = (param) -> targetField.getInnerInstance().getTargetInstance();
                innerInstance = targetField.new TargetInner();
                break;
            default:
                break;
        }

        // Call method: target type, default modifier, static + super.methodName() in switch
        Object callResult;
        switch (switchVar) {
            case 1:
                callResult = TargetClass.staticMethod();
                break;
            default:
                callResult = new Object();
                break;
        }

        // Variable call (target field access)
        String targetVar = innerInstance.innerField;

        // No new exception thrown
        List<String> result = new ArrayList<>();
        result.add(targetVar);
        result.add(callResult.toString());
        return result;
    }
}

// Target class: normal, default modifier, anonymous inner class (target_feature)
class TargetClass {
    // Target inner class (target_inner)
    class TargetInner {
        String innerField = "targetInnerFieldValue";

        // Accessor method for accessor feature
        public TargetClass getTargetInstance() {
            return TargetClass.this;
        }
    }

    // Target field (referenced in source)
    private TargetInner innerInstance = new TargetInner();

    // Accessor method for source variable call
    public TargetInner getInnerInstance() {
        return this.innerInstance;
    }

    // Static method for call_method feature (super.methodName() context)
    static Object staticMethod() {
        return new SuperTargetClass().superMethod();
    }

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(innerInstance.innerField);
        }
    };
}

// Super class for call_method super.methodName() feature
class SuperTargetClass {
    Object superMethod() {
        return new Object();
    }
}

// Diff package class for ContinueStatement pos: diff_package_others
package com.other;
public class DiffPackageClass {
    public int field;
}