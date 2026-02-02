// Target enum package
package com.refactoring.target;

// Target class: enum class, strictfp modifier, different package with source, target_feature: anonymous inner class
strictfp enum TargetEnum {
    TARGET_INSTANCE;

    private String targetField = "targetVal3"; // target_feature "3"

    // Anonymous inner class (target_feature)
    void useAnonymousClass() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                targetField = "anonymousUpdated3"; // target_feature "3"
            }
        };
        anonymousRunnable.run();
    }

    // Getter for variable call feature
    public String getTargetField() {
        return targetField;
    }
}
// Source enum package
package com.refactoring.source;

import com.refactoring.target.TargetEnum;
import java.util.ArrayList;
import java.util.List;

// Source class: enum class, default modifier, different package with target, member inner class, local inner class
enum SourceEnum {
    INSTANCE;

    // per_condition: source contains target enum field
    private TargetEnum targetField = TargetEnum.TARGET_INSTANCE;
    private String sourcePrivateField = "sourcePrivateVal3"; // this.field with "3"

    // Member inner recursive class (source_inner_rec - method position)
    class InnerRecursiveSource<T> { // generic method type parameter
        // Method: instance, return base type (int), private access, source_inner_rec position
        private <U extends Number> int moveableInstanceMethod(T param) { // generic parameter feature
            // Variable call feature
            String localVar = sourcePrivateField;
            localVar = targetField.getTargetField();
            localVar = String.valueOf(3); // target_feature "3"

            // ReturnStatement: public modifier, this.field, 3, pos=source
            public int returnLogic() {
                localVar = SourceEnum.this.sourcePrivateField; // target_feature "this.field"
                return 3; // target_feature "3", pos=source
            }
            int baseVal = returnLogic();

            // Continue statement feature
            for (int i = 0; i < 5; i++) {
                if (i == 3) { // target_feature "3"
                    continue;
                }
                localVar += i;
            }

            // Access_outer_private feature
            localVar = SourceEnum.this.sourcePrivateField; // access outer enum's private field

            // Overload_exist feature (overloaded methods)
            int overloadMethod() { return 3; }
            int overloadMethod(String s) { return s.length() + 3; }

            // Raw_type feature
            List rawList = new ArrayList(); // raw type usage
            rawList.add(localVar);

            // Local inner class (source feature)
            class LocalInnerSource {
                void updateVar() {
                    localVar = rawList.get(0).toString();
                }
            }
            new LocalInnerSource().updateVar();

            // no_new_exception feature (no custom exceptions instantiated/thrown)
            return baseVal; // base type (int) return
        }

        // Overload method (overload_exist feature)
        private <V> long moveableInstanceMethod(V param, String suffix) { // overloaded generic method
            return 3L; // target_feature "3"
        }
    }

    // Member inner class (source feature)
    class MemberInnerSource {
        String innerField = "memberInner3"; // target_feature "3"
    }
}