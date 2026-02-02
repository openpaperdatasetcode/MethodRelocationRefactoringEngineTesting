// Different package for ThrowStatement pos=diff_package_others
package com.refactoring.others;
import com.refactoring.test.TargetClass;

public class DiffPackageOthers {
    // ThrowStatement (private modifier, obj.field, 1, pos=diff_package_others)
    private static void throwStatement(TargetClass.TargetInner target) {
        if (target.innerField == null) {
            // obj.field + 1 from target_feature
            throw new IllegalArgumentException("Inner field null: " + target.innerField + "_1");
        }
    }
}

// Main package
package com.refactoring.test;

import com.refactoring.others.DiffPackageOthers;
import java.util.List;
import java.util.ArrayList;

// Others class for varargs method_feature "others_class"
class OthersClass {
    // Varargs helper method for method_feature
    public Object varargsHelper(Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg).append("_1"); // 1 from method_feature
        }
        return sb.toString();
    }
}

// Target abstract class (abstract modifier, static nested class feature)
abstract class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        static void validateField(String field) {
            if (field == null) throw new IllegalArgumentException("Field null_1");
        }
    }

    // Target inner class (target_inner)
    public abstract class TargetInner {
        String innerField; // For per_condition (source contains this field)

        public abstract void updateField(String value);
    }
}

// Source abstract class (normal class, abstract modifier, same package, member inner + static nested class)
abstract class SourceClass {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        // Varargs method (default modifier, method_feature:1/others_class/varargs/this.methodName(arguments), pos=exception throwing, return Object)
        Object varargsMethod(TargetClass.TargetInner target, Object... args) {
            try {
                // Exception throwing statements position
                DiffPackageOthers.throwStatement(target);
                OthersClass others = new OthersClass();
                // this.methodName(arguments) + varargs + others_class + 1
                Object result = others.varargsHelper(args);
                target.innerField = result.toString();
                return result;
            } catch (IllegalArgumentException e) {
                return "varargs_error_1";
            }
        }
    }

    // Member inner class (source_feature)
    class SourceMemberInner {
        // call_method (inner_class type, default modifier, normal + outerInstance.new InnerClass().methodName(), pos=exception throwing, return Object)
        Object callMethod(TargetClass outerTarget) {
            try {
                // Exception throwing statements position
                TargetClass.TargetInner inner = outerTarget.new TargetInner() {
                    @Override
                    public void updateField(String value) {
                        this.innerField = value + "_inner_call_1";
                    }
                };
                // outerInstance.new InnerClass().methodName()
                inner.updateField("call_method_arg");
                return inner.innerField;
            } catch (Exception e) {
                return "call_method_error_1";
            }
        }
    }

    // Method to be refactored (varargs, List<String> return, protected access, source position)
    protected List<String> moveMethod(TargetClass.TargetInner... targetParams) {
        List<String> result = new ArrayList<>();
        // Per_condition: contains target parameter (target_inner)
        if (targetParams == null || targetParams.length == 0) {
            result.add("no_target_params_1");
            return result;
        }

        // Super constructor invocation (Object superclass)
        Object superObj = new Object();

        // Super keywords (call Object superclass method)
        super.toString();

        // Process each varargs parameter
        for (TargetClass.TargetInner target : targetParams) {
            try {
                // Variable call (access target inner field - per_condition)
                String varCall = target.innerField;
                if (varCall == null) varCall = "default_1";

                // Varargs method call (pos=exception throwing statements)
                SourceStaticNested staticNested = new SourceStaticNested();
                Object varargsResult = staticNested.varargsMethod(target, varCall, "arg2", "arg3");

                // call_method invocation (pos=exception throwing statements)
                TargetClass outerTarget = new TargetClass() {};
                SourceMemberInner inner = new SourceMemberInner();
                Object callResult = inner.callMethod(outerTarget);

                // Collect results
                result.add(varCall);
                result.add(varargsResult.toString());
                result.add(callResult.toString());

                // Update target field
                target.innerField = varCall + "_processed_1";
            } catch (Exception e) {
                result.add("processing_error_1: " + e.getMessage());
            }
        }

        // No new exception
        return result;
    }

    // Abstract method required for abstract class modifier
    public abstract void abstractSourceMethod();
}