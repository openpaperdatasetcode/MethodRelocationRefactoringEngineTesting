// Target class package (different from source)
package com.refactoring.target;

// Target class (normal class, protected modifier, empty target_feature)
protected class TargetClass {
    String targetField; // For per_condition (source contains this field)

    // Inner class for method_feature "inner_class"
    public class TargetInner {
        public String innerMethod(String input) {
            return input + "_inner_" + 1; // 1 from method_feature
        }
    }
}

// Others class for call_method type=others_class
package com.refactoring.others;
import com.refactoring.target.TargetClass;

public class OthersClass {
    // call_method (private modifier, constructor + ClassName.methodName(arguments), pos=switch, return String)
    private static String othersMethod(TargetClass target) {
        TargetClass newTarget = new TargetClass(); // constructor feature
        return newTarget.targetField + "_others_" + 1;
    }
}

// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.others.OthersClass;
import java.util.List;
import java.util.ArrayList;

// Source class (normal class, abstract modifier, different package, static nested + anonymous inner class)
public abstract class SourceClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        static void helperMethod(TargetClass target) {
            target.targetField += "_static_nested";
        }
    }

    // Member inner class (source_inner for method_position)
    class SourceInnerClass {
        // Generic method (public modifier, method_feature:1/inner_class/generic/outerInstance.new InnerClass().methodName(), pos=exception handling, return List<String>)
        public <T> List<String> genericMethod(TargetClass target) {
            List<String> result = new ArrayList<>();
            try { // pos=exception handling statements
                // outerInstance.new InnerClass().methodName()
                TargetClass.TargetInner inner = target.new TargetInner();
                String innerResult = inner.innerMethod(target.targetField);
                result.add(innerResult + "_generic_" + 1); // 1 from method_feature
            } catch (Exception e) {
                result.add("generic_error_" + 1);
            }
            return result;
        }

        // Method to be refactored (instance, TargetClass return, default access, source_inner position)
        TargetClass moveMethod(TargetClass targetParam) {
            // Per_condition: source contains the field of the target
            if (targetParam == null) {
                // Constructor invocation
                targetParam = new TargetClass();
                targetParam.targetField = "default_" + 1;
            }

            // Type declaration statement
            TargetClass.TargetInner targetInner = targetParam.new TargetInner();
            List rawList = new ArrayList(); // raw_type feature

            // Super keywords (call Object superclass method)
            super.toString();

            // Variable call (access target field - per_condition)
            String varCall = targetParam.targetField;
            targetParam.targetField = varCall + "_var_modified_" + 1;

            // Generic method call (pos=exception handling statements)
            List<String> genericResult;
            try {
                genericResult = this.genericMethod(targetParam);
            } catch (Exception e) {
                genericResult = new ArrayList<>();
                genericResult.add("generic_call_error");
            }

            // call_method (pos=switch, constructor + ClassName.methodName(arguments), return String)
            String callResult;
            switch (targetParam.targetField.length()) {
                case 1: // 1 from method_feature
                    callResult = OthersClass.othersMethod(targetParam); // ClassName.methodName(arguments)
                    break;
                default:
                    callResult = OthersClass.othersMethod(new TargetClass()); // constructor feature
                    break;
            }
            rawList.add(callResult);

            // Requires try-catch
            try {
                SourceStaticNested.helperMethod(targetParam);
                // Anonymous inner class (source_feature)
                Runnable anonymous = new Runnable() {
                    @Override
                    public void run() {
                        targetParam.targetField += "_anonymous";
                    }
                };
                anonymous.run();
            } catch (Exception e) {
                targetParam.targetField += "_try_catch_error";
            }

            // Return TargetClass type
            return targetParam;
        }
    }

    // Abstract method (required for abstract class)
    public abstract void abstractSourceMethod();
}