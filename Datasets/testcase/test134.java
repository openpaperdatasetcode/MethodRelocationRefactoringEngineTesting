package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Source class: normal class, private modifier, same package, anonymous inner class, static nested class
private class SourceClass {
    // Static nested class (source feature)
    static class StaticNestedSource {
        String staticNestedMethod() {
            return "staticNestedValue";
        }
    }

    // Method: instance, return List<String>, default access, source position
    // per_condition: contains target class parameter (TargetClass.MemberInnerTarget)
    List<String> moveableInstanceMethod(TargetClass.MemberInnerTarget targetParam) {
        // Empty statement feature
        ;

        // Type declaration statement feature
        class LocalTypeDeclaration {
            String localField = "localTypeValue";
        }
        LocalTypeDeclaration localType = new LocalTypeDeclaration();

        // Variable call feature
        String localVar = localType.localField;
        localVar = targetParam.innerField;
        localVar = StaticNestedSource.staticNestedMethod();

        // Access_instance_method feature
        targetParam.innerMethod();
        StaticNestedSource staticNestedInstance = new StaticNestedSource();
        staticNestedInstance.staticNestedMethod();

        // Anonymous inner class usage (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                localVar = "anonymousValue";
            }
        };
        anonymousRunnable.run();

        // Return this; feature (cast to match return type)
        if (targetParam == null) {
            return (List<String>) this;
        }

        // No_new_exception feature (no custom exceptions instantiated)
        List<String> result = new ArrayList<>();
        result.add(localVar);
        return result;
    }
}

// Target class: normal class, strictfp modifier, target_feature: member inner class
strictfp class TargetClass {
    // Member inner class (target_inner - method's target class)
    class MemberInnerTarget {
        String innerField = "innerFieldValue";

        void innerMethod() {
            System.out.println("Member inner class method");
        }
    }
}