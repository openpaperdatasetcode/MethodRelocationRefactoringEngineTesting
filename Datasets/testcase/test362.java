package com.refactoring.movemethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface MoveMethodAnnotation {}

// Permitted subclass for source class permits feature
class SourceSubClass extends SourceClass {}

// Source normal class (default modifier, same package, permits + two member inner classes)
class SourceClass permits SourceSubClass {
    // Static field for depends_on_static_field feature
    public static int staticField = 5;

    // First member inner class (duplicated feature)
    class MemberInnerClass1 {
        int innerField1 = 10;
    }

    // Second member inner class (duplicated feature)
    class MemberInnerClass2 {
        int innerField2 = 20;
    }

    /**
     * Instance method to be moved (strictfp, returns Object, base type parameter, source position)
     * @param targetParam Target class parameter (satisfies pre-condition)
     * @param baseParam Base type parameter (int)
     * @return Object result
     */
    @MoveMethodAnnotation
    strictfp Object moveableMethod(TargetClass targetParam, int baseParam) {
        // Requires_try_catch feature
        try {
            // Continue statement within loop
            for (int i = 0; i < 10; i++) {
                if (i == staticField) { // Depends_on_static_field feature
                    continue; // Continue statement feature
                }
                // Variable call feature
                int varCall = targetParam.innerClass.innerField + baseParam + staticField;
                System.out.println(varCall);
            }
        } catch (Exception e) {
            // Empty catch block (requires_try_catch satisfied, no_new_exception implicit)
        }

        // Variable call with target class member inner class field
        String varCallResult = targetParam.innerClass.innerField + "_" + staticField;
        
        // Return Object type
        return varCallResult;
    }
}

// Target normal class (public modifier, member inner class target feature)
public class TargetClass {
    // Member inner class (target feature)
    public class TargetMemberInnerClass {
        int innerField = 30;
    }

    // Instance of member inner class
    TargetMemberInnerClass innerClass = new TargetMemberInnerClass();
}