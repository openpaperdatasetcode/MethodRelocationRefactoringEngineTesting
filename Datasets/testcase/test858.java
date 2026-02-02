package refactoring.test;

import java.util.List;

// Annotation for has_annotation feature
@interface MethodAnnotation {}

// Source class: abstract, public modifier, same package as target, features: implements, member inner class, local inner class
public abstract class SourceClass implements Runnable {
    // Per condition: source contains target class field
    protected TargetClass targetField = new TargetClass() {};

    // Protected field for access_outer_protected feature
    protected String protectedOuterField = "source-protected-field";

    // Method to be refactored: instance, base type return (int), private access, position source
    @MethodAnnotation // has_annotation feature
    private int moveMethod() {
        // Variable call feature
        int localVar = 10;
        // Raw type feature (List without generic type)
        List rawList = new java.util.ArrayList();
        rawList.add(localVar);

        // Constructor invocation feature
        SourceMemberInner innerInstance = new SourceMemberInner();
        // Depends on inner class feature
        localVar += innerInstance.calculate(localVar);

        // Access outer protected feature
        String outerProtected = this.protectedOuterField;
        // Expression statement feature
        String exprStmt = outerProtected + " - " + localVar;
        System.out.println(exprStmt);

        // Local inner class (source_class feature)
        class SourceLocalInner {
            int getValue() {
                return 5;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        localVar += localInner.getValue();

        // Override violation (method with same signature as parent but incompatible access/return)
        // No new exception feature (no throw new Exception)
        return localVar;
    }

    // Member inner class (source_class feature)
    class SourceMemberInner {
        int calculate(int val) {
            return val * 2;
        }
    }

    // Override violation parent method (different access/return vs private int moveMethod())
    @Override
    public void run() {
        // Empty implementation for Runnable
    }
}

// Target class: abstract, protected modifier, same package, target_feature: extends, local inner class
protected abstract class TargetClass extends BaseTargetClass {
    // Local inner class (target_feature)
    class TargetInnerRecClass {
        int process(int value) {
            return value + 3;
        }
    }

    public TargetInnerRecClass getInnerRecClass() {
        return new TargetInnerRecClass();
    }
}

// Base class for target class extends feature
class BaseTargetClass {}