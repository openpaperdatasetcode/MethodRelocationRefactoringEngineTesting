// Target class package (different from source)
package com.refactoring.target;

// Target class (normal class, default modifier, local inner class feature)
class TargetClass {
    String targetField; // For per_condition and variable call

    // Method with local inner class (target_feature)
    public void targetMethod() {
        // Local inner class
        class TargetLocalInner {
            void updateField(TargetClass target, String value) {
                target.targetField = value;
            }
        }
        new TargetLocalInner().updateField(this, "target_value");
    }
}

// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Source class (normal class, public modifier, different package, static nested + local inner class)
public class SourceClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        static void processTarget(TargetClass target) {
            target.targetMethod();
        }
    }

    // Method to be refactored (instance, void return, protected access, source position)
    @RefactorAnnotation // has_annotation feature
    protected void moveMethod(TargetClass targetParam) {
        // Per_condition: contains target parameter
        if (targetParam == null) {
            return; // return statement feature
        }

        // Variable call (access target field - per_condition)
        String varCall = targetParam.targetField;
        if (varCall == null) {
            varCall = "default_source_value";
        }

        // Local inner class (source_feature)
        class SourceLocalInner {
            void updateTarget(TargetClass target, String value) {
                target.targetField = value + "_modified";
            }
        }
        new SourceLocalInner().updateTarget(targetParam, varCall);

        // Static nested class usage
        SourceStaticNested.processTarget(targetParam);

        // Return statement (void return)
        return;

        // No new exception
    }
}