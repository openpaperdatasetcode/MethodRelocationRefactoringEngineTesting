// Different package for TryStatement pos=diff_package_target
package com.refactoring.diff;
import com.refactoring.test.TargetClass;
import java.util.List;
import java.util.ArrayList;

public class DiffPackageClass {
    // TryStatement (private modifier, obj.field, 3, pos=diff_package_target)
    private <T> List<String> tryStatement(TargetClass<T> target) {
        List<String> result = new ArrayList<>();
        try {
            target.targetField = (T) ("try_value_" + 3); // obj.field + 3 from target_feature
            result.add(target.targetField.toString());
        } catch (Exception e) {
            result.add("try_catch_" + 3);
        }
        return result;
    }
}

// Main package
package com.refactoring.test;
import com.refactoring.diff.DiffPackageClass;
import java.util.List;
import java.util.ArrayList;

// Target class (normal class, private modifier, type parameter feature)
private class TargetClass<T> {
    T targetField; // For per_condition (source contains this field)

    // Inner class for call_method type=inner_class
    public class TargetInnerClass {
        // Final method for call_method modifier=final
        public final String innerMethod() {
            return targetField == null ? "null_" + 1 : targetField.toString();
        }
    }
}

// Source class (normal class, private modifier, same package, anonymous inner + member inner class)
private class SourceClass {
    private String outerField = "outer_this_value"; // For uses_outer_this

    // Member inner class (source_feature)
    class SourceMemberInner {
        // Inner recursive class (source_inner_rec)
        class SourceInnerRecursive {
            // Generic method (private modifier, method_feature:1/source/generic/super.methodName(arguments), pos=for, return List<String>)
            private <T> List<String> genericMethod(TargetClass<T> target) {
                List<String> result = new ArrayList<>();
                // pos=for
                for (int i = 0; i < 1; i++) { // 1 from method_feature
                    // super.methodName(arguments) (call Object superclass method)
                    String superResult = super.toString() + "_" + 1;
                    result.add(superResult);
                    result.add(target.targetField.toString());
                }
                return result;
            }

            // Method to be refactored (instance, List<String> return, default access, source_inner_rec)
            List<String> moveMethod(TargetClass<String> targetParam) {
                // Per_condition: source contains the field of the target
                if (targetParam == null) {
                    return new ArrayList<>();
                }

                // TryStatement invocation (pos=diff_package_target)
                DiffPackageClass diffClass = new DiffPackageClass();
                List<String> tryResult = diffClass.tryStatement(targetParam);

                // Generic method call (pos=for)
                List<String> genericResult = this.genericMethod(targetParam);

                // Variable call (access target field - per_condition)
                String varCall = targetParam.targetField;
                targetParam.targetField = varCall + "_var_modified_" + 1;

                // Uses outer this
                String outerThisVal = SourceClass.this.outerField; // uses_outer_this feature
                tryResult.add(outerThisVal + "_outer_this");

                // call_method (type=inner_class, final modifier, constructor + new ClassName().method(), pos=if/else conditions, return String)
                String callResult;
                if (targetParam.targetField.isEmpty()) { // pos=if/else conditions
                    TargetClass<TargetInnerClass> tempTarget = new TargetClass<>(); // constructor feature
                    callResult = new targetParam.new TargetInnerClass().innerMethod(); // new ClassName().method()
                } else {
                    callResult = new targetParam.new TargetInnerClass().innerMethod(); // new ClassName().method()
                }
                tryResult.add(callResult);

                // Anonymous inner class (source_feature)
                Runnable anonymous = new Runnable() {
                    @Override
                    public void run() {
                        genericResult.add(targetParam.targetField + "_anonymous");
                    }
                };
                anonymous.run();

                // Combine results
                tryResult.addAll(genericResult);

                // No new exception
                return tryResult;
            }
        }
    }
}