package com.refactoring.movemethod;

import diffpackage.DiffPackageClass;
import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature
@interface EnumMethodAnnotation {}

// Different package class for ThrowStatement pos=diff_package_others
package diffpackage;
public class DiffPackageClass {
    public static int throwFlag = 1;
}

package com.refactoring.movemethod;

// Source class: enum class, private modifier, same package, implements, local inner class, anonymous inner class
private enum SourceEnum implements Runnable {
    INSTANCE;

    private String sourceField = "sourceFieldValue";

    // Member inner recursive class (source_inner_rec - method position)
    class InnerRecursiveClass {
        // Method: instance, return TargetEnum Type, private access, source_inner_rec position
        // per_condition: contains target enum parameter (TargetEnum)
        @EnumMethodAnnotation // has_annotation feature
        private TargetEnum moveableInstanceMethod(TargetEnum targetParam) {
            // Variable call feature
            String localVar = sourceField;
            localVar = targetParam.targetField;

            // Raw_type feature
            List rawList = new ArrayList(); // raw type usage
            rawList.add(localVar);

            // Type declaration statement feature
            class LocalTypeDeclaration {
                String typeField = "typeDeclValue";
            }
            LocalTypeDeclaration localType = new LocalTypeDeclaration();

            // ThrowStatement: private modifier, this.field, 1, pos=diff_package_others
            private void throwLogic() {
                if (DiffPackageClass.throwFlag == 1) { // target_feature "1" (diff_package_others pos)
                    if (SourceEnum.this.sourceField == null) { // target_feature "this.field"
                        throw new NullPointerException(); // NullPointerException feature
                    }
                }
            }
            throwLogic();

            // Anonymous inner class (source feature)
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    localVar = localType.typeField;
                }
            };
            anonymousRunnable.run();

            // Local inner class (source feature)
            class LocalInnerSource {
                void localMethod() {
                    localVar += "localInner";
                }
            }
            LocalInnerSource localInner = new LocalInnerSource();
            localInner.localMethod();

            // Call_method: others_class, private, abstract, lambda, pos=functional interfaces, return TargetEnum Type
            FunctionalInterfaceExample funcInterface = (param) -> { // (parameters) -> methodBody
                return param;
            };
            TargetEnum callResult = funcInterface.process(targetParam);

            // no_new_exception feature (no custom exceptions instantiated)
            return callResult;
        }
    }

    @Override
    public void run() {} // implements Runnable (source feature)
}

// Target class: enum class, private modifier, target_feature: static nested class
private enum TargetEnum {
    TARGET_INSTANCE;

    String targetField = "targetFieldValue";

    // Static nested class (target_feature)
    static class StaticNestedTarget {
        void nestedMethod() {}
    }
}

// Call_method: others_class (abstract functional interface)
@FunctionalInterface
abstract class FunctionalInterfaceExample {
    // Call_method: private modifier, return TargetEnum Type
    private TargetEnum process(TargetEnum param) {
        return param;
    }
}