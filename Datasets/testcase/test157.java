package com.refactoring.movemethod;

import java.lang.reflect.Method;

// Different package class for SynchronizedStatement pos=diff_package_others
package diffpackage;
public class DiffPackageClass {
    public static final Object lock = new Object();
}

package com.refactoring.movemethod;
import diffpackage.DiffPackageClass;

// Source class: normal class, abstract modifier, same package, local inner class, member inner class
abstract class SourceClass extends SuperClass {
    // Static field for depends_on_static_field feature
    private static String staticSourceField = "staticValue";
    
    // Member inner class (source feature)
    class MemberInnerSource {
        void innerInstanceMethod() {
            System.out.println("Member inner class method");
        }
    }

    // Method: static, void return, strictfp access, source position
    // per_condition: contains target class parameter (TargetClass<T>)
    strictfp static <T> void moveableStaticMethod(TargetClass<T> targetParam) {
        // Variable call feature
        String localVar = staticSourceField;
        localVar = targetParam.targetField;

        // Access_instance_method feature
        MemberInnerSource innerInstance = new SourceClass().new MemberInnerSource();
        innerInstance.innerInstanceMethod();
        targetParam.targetInstanceMethod();

        // Depends_on_static_field feature
        staticSourceField = localVar + "_updated";

        // SynchronizedStatement: static modifier, super.field, 2, pos=diff_package_others
        synchronized (DiffPackageClass.lock) { // pos=diff_package_others
            int num = 2; // target_feature "2"
            String superFieldVal = SuperClass.superStaticField; // target_feature "super.field"
            staticSourceField = superFieldVal + num;
        }

        // Used_by_reflection feature
        try {
            Method staticMethod = SourceClass.class.getDeclaredMethod("moveableStaticMethod", TargetClass.class);
            staticMethod.setAccessible(true);
            staticMethod.invoke(null, targetParam);
        } catch (Exception e) {
            // no_new_exception feature (no custom exceptions instantiated)
            return;
        }

        // Local inner class (source feature)
        class LocalInnerSource {
            void localMethod() {
                localVar = "localInnerValue";
            }
        }
        LocalInnerSource localInner = new LocalInnerSource();
        localInner.localMethod();

        // no_new_exception feature (no new exceptions thrown)
    }
}

// Super class for super.field feature
class SuperClass {
    protected static String superStaticField = "superFieldValue";
}

// Target class: normal class, public modifier, target_feature: type parameter
public class TargetClass<T> {
    String targetField = "targetValue";

    void targetInstanceMethod() {
        System.out.println("Target instance method");
    }
}