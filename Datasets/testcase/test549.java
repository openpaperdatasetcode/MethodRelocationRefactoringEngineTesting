package com.refactor;

import java.util.List;
import java.util.ArrayList;

// Source class: normal, private, same package as target, has member inner + static nested class
private class SourceClass {
    // Target class field reference (per_condition)
    private TargetClass targetField = new TargetClass();
    
    // Private field for access_outer_private feature
    private String outerPrivateField = "outerPrivate";
    
    // Member inner class (source feature)
    class SourceMemberInner {}
    
    // Static nested class (source feature)
    static class SourceStaticNested {}

    /**
     * Method javadoc - generic method to refactor
     * @param <T> generic type parameter
     * @param param input parameter
     * @return List<String> result
     */
    private <T> List<String> methodToMove(T param) {
        // Super constructor invocation (implicit super() for Object constructor)
        super();
        
        // Variable call (target field access)
        String targetVar = targetField.targetInner.innerField;
        
        // Access outer private field (access_outer_private)
        String privateAccess = SourceClass.this.outerPrivateField;
        
        // Call method (others_class, private, superTypeReference in object initialization)
        OtherClass instance = new OtherClass(SuperType.superMethod("arg"));
        
        // No new exception thrown
        List<String> result = new ArrayList<>();
        result.add(targetVar);
        result.add(privateAccess);
        return result;
    }
}

// Target class: normal, default modifier, extends SuperType, has member inner class
class TargetClass extends SuperType {
    // Member inner class (target_inner - target class for method)
    class TargetInner {
        String innerField = "targetInnerField";
    }
    
    // Target field (referenced in source)
    TargetInner targetInner = new TargetInner();
}

// Super type for target class extends feature
class SuperType {
    // SuperTypeReference.methodName for call_method feature
    public static void superMethod(String arg) {}
}

// Others class for call_method (type: others_class)
class OtherClass {
    // Private call method (return type void, normal feature)
    private OtherClass(void param) {}
}