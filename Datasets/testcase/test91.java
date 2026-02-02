package com.refactoring.movemethod;

import com.refactoring.diff.DiffPackageOthers;

import java.util.Objects;

// Diff package class for TypeDeclarationStatement position
package com.refactoring.diff;
public class DiffPackageOthers {
    public int sharedField = 1;
}

// Superclass for source class extension
sealed class SuperSourceClass permits SourceClass {
    protected String superField = "super_value";
}

// Source normal class (public modifier, same package, extends + permits + anonymous inner + local inner class)
public final class SourceClass extends SuperSourceClass {
    // Anonymous inner class (source feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner execution");
        }
    };

    // Instance method to be moved (protected, base type return, source position)
    protected int moveableMethod(TargetClass<String> targetParam) {
        // TypeDeclarationStatement (static modifier, diff_package_others pos, obj.field + 1)
        static DiffPackageOthers typeDeclObj = new DiffPackageOthers();
        int typeDeclVar = targetParam.innerClass.innerField + typeDeclObj.sharedField; // obj.field + 1

        // Local inner class (source feature)
        class SourceLocalInner {
            int localField = 1;
            
            // Constructor feature (public modifier, while pos, 1 + inner_class + constructor + this.methodName(arguments))
            public TargetClass<String> createTargetInstance() {
                int count = 0;
                while (count < 1) { // Number 1 feature
                    TargetClass<String> target = new TargetClass<>("init_value"); // inner_class + constructor
                    this.updateTargetField(target, count); // this.methodName(arguments)
                    count++;
                    return target;
                }
                return null;
            }

            private void updateTargetField(TargetClass<String> target, int val) {
                target.innerClass.innerField = val;
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();

        // Additional type declaration statement feature
        class TypeDeclClass {
            String typeField = super.superField; // super keywords feature
        }
        TypeDeclClass typeDecl = new TypeDeclClass();

        // Variable call feature
        int varCall = typeDeclVar 
                + localInner.localField 
                + targetParam.innerClass.innerField 
                + typeDecl.typeField.length();

        // Super keywords usage (access superclass field)
        varCall += super.superField.length();

        // Execute anonymous inner class (source feature)
        anonymousInner.run();

        // No new exception instantiation (no_new_exception feature)
        return varCall;
    }
}

/**
 * Target normal class with javadoc, type parameter and member inner class
 * @param <T> Generic type parameter (type parameter target feature)
 */
class TargetClass<T> {
    private final T value;

    // Member inner class (target feature)
    public class TargetMemberInner {
        int innerField = 1;
    }

    // Instance of member inner class
    public final TargetMemberInner innerClass = new TargetMemberInner();

    // Constructor
    public TargetClass(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public T getValue() {
        return value;
    }
}