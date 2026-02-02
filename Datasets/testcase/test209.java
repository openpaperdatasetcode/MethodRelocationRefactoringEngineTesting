package com.refactoring.movemethod;

import diffpackage.TargetClassDiffPackage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Different package class for ConstructorInvocation pos=diff_package_target
package diffpackage;
import com.refactoring.movemethod.TargetClass;
public class TargetClassDiffPackage {
    public static int diffPackageVal = 1;
    public static TargetClass<String> createTargetInstance(String fieldVal) {
        return new TargetClass<>(fieldVal); // ConstructorInvocation in diff package
    }
}

package com.refactoring.movemethod;

// Super class for target_class extends feature
class SuperTargetClass<T> {
    protected T superField;
    public int superStaticMethod(int val) {
        return val + 1; // numbers "1"
    }
}

// Source class: generic class, public modifier, same package, anonymous inner class, local inner class
public class SourceClass<T> {
    private String sourceField = "sourceInit"; // this.field for ConstructorInvocation

    /**
     * Method javadoc feature
     * Instance method processing generic TargetClass and returning List<String>
     * @param targetParam the target class parameter (per_condition)
     * @return processed list of strings
     */
    private List<String> moveableInstanceMethod(TargetClass<T> targetParam) {
        List<String> result = new ArrayList<>();
        // Variable call feature
        String localVar = targetParam.targetField;
        localVar = String.valueOf(1); // numbers "1"

        // ConstructorInvocation: public modifier, this.field, 1, pos=diff_package_target
        public TargetClass<T> createTargetInstance() {
            // pos=diff_package_target (uses diffpackage class)
            TargetClass<T> instance = (TargetClass<T>) diffpackage.TargetClassDiffPackage.createTargetInstance(this.sourceField);
            instance.targetField = String.valueOf(diffpackage.TargetClassDiffPackage.diffPackageVal); // target_feature "1"
            instance.targetField = this.sourceField; // target_feature "this.field"
            return instance;
        }
        TargetClass<T> newTargetInstance = createTargetInstance();

        // Numbers: 1, protected modifier, exp=Name
        protected String nameExp() {
            return "Name_" + 1; // numbers "1", exp=Name, protected modifier
        }
        localVar = nameExp();

        // Do statement feature
        int counter = 0;
        do {
            result.add(localVar + counter);
            counter++;
        } while (counter < 1); // numbers "1"

        // Switch statement feature
        switch (localVar.length()) {
            case 1: // numbers "1"
                localVar = "case1";
                break;
            default:
                localVar = "default";
        }

        // Type declaration statement feature
        class LocalTypeDeclaration<U> {
            U typeField;
            void process(T val) {
                typeField = (U) val;
            }
        }
        LocalTypeDeclaration<T> localType = new LocalTypeDeclaration<>();
        localType.process((T) localVar);

        // Expression statement feature
        sourceField = localVar.toUpperCase();
        newTargetInstance.targetField = sourceField;
        result.add(newTargetInstance.targetField);

        // Access_instance_field feature
        result.add(targetParam.targetField); // access target instance field
        result.add(this.sourceField); // access source instance field

        // Call_method: target, synchronized modifier, static, superTypeReference.methodName(), pos=collection operations, return int
        synchronized int callMethodLogic() {
            // pos=collection operations (stream)
            int callResult = result.stream()
                    .mapToInt(s -> SuperTargetClass.superStaticMethod(s.length())) // superTypeReference.methodName(arguments) + static
                    .sum();
            return callResult;
        }
        result.add(String.valueOf(callMethodLogic()));

        // Anonymous inner class (source feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                result.add("anonymousValue");
            }
        };
        anonymousRunnable.run();

        // Local inner class (source feature)
        class LocalInnerSource {
            void updateResult() {
                result.add(localType.typeField.toString());
            }
        }
        new LocalInnerSource().updateResult();

        // no_new_exception feature (no custom exceptions instantiated/thrown)
        return result;
    }
}

// Target class: generic class, public modifier, same package, target_feature: extends, local inner class
public class TargetClass<T> extends SuperTargetClass<T> {
    T targetField;

    // Constructor for ConstructorInvocation feature
    public TargetClass(T targetField) {
        this.targetField = targetField;
    }

    // Local inner class (target_feature)
    class LocalInnerTarget {
        void processField() {
            targetField = (T) ("localInner_" + 1); // numbers "1"
        }
    }
}