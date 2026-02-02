package com.refactor;

import com.other.DiffPackageClass;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Source class: abstract, default modifier, same package as target, two static nested classes
abstract class SourceClass {
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();
    
    // First static nested class
    static class SourceStaticNested1 {}
    // Second static nested class
    static class SourceStaticNested2 {}
    
    // Source inner class (method_position: source_inner)
    class SourceInner {
        // Method to refactor: instance, returns TargetClass, public, in source_inner
        @CustomAnnotation // has_annotation
        public TargetClass methodToMove() {
            // SynchronizedStatement (public modifier, this.field, pos: diff_package_others, value 1)
            public synchronized (DiffPackageClass.sharedLock) {
                this.field = 1;
            }
            
            // Type declaration statement
            class LocalTypeDeclaration {}
            LocalTypeDeclaration localInstance = new LocalTypeDeclaration();
            
            // NullPointerException (explicitly triggerable, no new exception thrown)
            String nullStr = null;
            try {
                // Requires try-catch
                nullStr.length();
            } catch (NullPointerException e) {
                // Handle NPE without throwing new exception
            }
            
            // Variable call (target field access)
            int localVar = targetField.targetStaticField;
            localVar += 1;
            
            return new TargetClass();
        }
        
        // Field for SynchronizedStatement (this.field)
        public int field;
    }
}

// Target class: abstract, default modifier, static nested class
abstract class TargetClass {
    // Target static field (referenced in source)
    public static int targetStaticField = 1;
    
    // Static nested class (target_feature)
    static class TargetStaticNested {}
}

// Diff package class for SynchronizedStatement pos: diff_package_others
package com.other;
public class DiffPackageClass {
    public static final Object sharedLock = new Object();
}

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {}