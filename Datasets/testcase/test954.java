// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;

// Abstract normal class (source_class: abstract modifier, normal type)
public abstract class SourceClass extends BaseSuperClass {
    // Static nested classes (two as required)
    static class SourceStaticNested1 {}
    static class SourceStaticNested2 {}

    // Static field for depends_on_static_field feature
    public static String staticField = "STATIC_DATA";
    // Instance variable for this.var = var
    private String instanceVar;

    // Normal method (public, void return, source position)
    public void processTarget(TargetClass targetParam) {
        // Per_condition: contains target parameter (variable call)
        String targetField = targetParam.getTargetField();
        
        // Constructor invocation of target class
        TargetClass newTarget = new TargetClass();
        
        // this.var = var feature
        this.instanceVar = targetField;
        
        // Access instance method of target
        targetParam.setTargetField(this.instanceVar + "_processed");
        
        // Depends_on_static_field: use static field
        String combined = staticField + targetParam.getTargetField();
        
        // No_new_exception (empty try-catch)
        try {
            // No exception thrown - just use combined value
            newTarget.setTargetField(combined);
        } catch (Exception e) {
            // Do not throw new exception, only handle
            this.instanceVar = "error";
        }
    }
}

// Super class for source_class extends feature
class BaseSuperClass {}

// Target class package (different from source)
package com.refactoring.target;

// Private normal class (target_class: private modifier, normal type)
private class TargetClass {
    // Static nested class (target_feature)
    static class TargetStaticNested {}

    // Instance field for variable call/access
    private String targetField;

    // Instance methods for access
    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }
}