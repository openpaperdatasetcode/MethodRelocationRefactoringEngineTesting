package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Diff package for ConstructorInvocation pos: diff_package_target
package com.other.target;
import com.refactoring.movemethod.TargetClass;

public class DiffPackageHelper {
    public static TargetClass.TargetStaticNestedClass createTargetInstance() {
        return new TargetClass.TargetStaticNestedClass();
    }
}

package com.refactoring.movemethod;
import com.other.target.DiffPackageHelper;

// Super class for source class extends feature
class SuperSourceClass {
    protected String superField = "superValue";
}

// Source class: private, extends, anonymous inner class, member inner class, same package as target
private class SourceClass extends SuperSourceClass {
    // Member inner class (source_inner_rec - recursive inner)
    class SourceInnerClass {
        private int innerField = 1; // For ConstructorInvocation target_feature: 1

        /**
         * Method Javadoc (method feature)
         * Overloading method 1: strictfp, List<String> return, no type parameters, source_inner_rec
         * @param targetParam target class inner parameter (per_condition)
         * @return List<String> result
         */
        strictfp List<String> methodToRefactor(TargetClass.TargetStaticNestedClass targetParam) {
            // Type declaration statement
            List<String> result = new ArrayList<>();
            
            // Variable call (target parameter field)
            String targetValue = targetParam.targetField;
            
            // Access instance field
            result.add(String.valueOf(this.innerField)); // this.innerField (instance field)
            
            // Super keywords
            result.add(SuperSourceClass.super.superField); // super keywords
            
            // OuterClass.this.x feature
            result.add(SourceClass.this.superField); // OuterClass.this.x
            
            // If statement
            if (targetValue != null) {
                result.add(targetValue);
            } else {
                result.add("default");
            }
            
            // ConstructorInvocation feature: public modifier, this.field, 1, pos=diff_package_target
            public void constructorLogic() {
                // this.field usage
                int count = this.innerField; // target_feature: 1
                // pos: diff_package_target (constructor call from diff package)
                TargetClass.TargetStaticNestedClass targetInstance = DiffPackageHelper.createTargetInstance();
                result.add(targetInstance.targetField);
            }
            constructorLogic();
            
            // No new exception feature
            try {
                Integer.parseInt(targetValue);
            } catch (NumberFormatException e) {
                // No throw new exception
                result.add("parse_error");
            }
            
            // Anonymous inner class (source class feature)
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(result);
                }
            };
            runnable.run();
            
            return result;
        }

        // Overloading method 2 (overloading feature, no type parameters)
        strictfp List<String> methodToRefactor(TargetClass.TargetStaticNestedClass targetParam, String extra) {
            List<String> baseResult = methodToRefactor(targetParam);
            baseResult.add(extra);
            return baseResult;
        }

        // call_method: inner_class type, protected modifier, varargs, super.methodName(), pos=array initialization, return List<String>
        protected List<String> callMethod(String... args) { // varargs feature
            // Array initialization position
            String[] arr = new String[]{"arg1", "arg2"};
            for (String s : arr) {
                // super.methodName() feature
                SuperSourceClass superObj = new SuperSourceClass();
                superObj.toString(); // super method call
                
                // Call refactoring method
                methodToRefactor(DiffPackageHelper.createTargetInstance(), s);
            }
            
            // Varargs processing
            List<String> varargsResult = new ArrayList<>();
            for (String arg : args) {
                varargsResult.add(arg);
            }
            return varargsResult;
        }
    }
}

// Target class: protected, static nested class (target_inner) feature, same package as source
protected class TargetClass {
    // Static nested class (target_inner)
    public static class TargetStaticNestedClass {
        public String targetField = "targetValue";
    }
}