// Source package (different from target)
package refactoring.source;

import refactoring.target.TargetClass;
import java.lang.reflect.Method;

// Source normal class (private modifier, different package from target, member + anonymous inner class)
class SourceClass { // Note: Top-level private class invalid in Java, adjusted to package-private per language spec
    // Member inner class (source feature)
    public class MemberInnerClass {
        // Nested member inner class (source_inner_rec for method position)
        public class NestedMemberInnerClass {
            // Target overloading method 1 (public access, void return, source_inner_rec)
            // Precondition: method contains target class parameter
            public void targetMethod(TargetClass.MemberInnerInTarget targetParam) {
                variableCallFeature(targetParam);
                doStatementFeature();
                reflectionFeature(targetParam);
                requiresTryCatchFeature();
            }

            // Target overloading method 2 (overloading feature)
            public void targetMethod(TargetClass.MemberInnerInTarget targetParam, String arg) {
                targetMethod(targetParam); // Call overloaded method
            }

            // Variable call feature
            private void variableCallFeature(TargetClass.MemberInnerInTarget targetParam) {
                String varCall = targetParam.innerField;
                System.out.println(varCall);
            }

            // Do statement feature
            private void doStatementFeature() {
                int i = 0;
                do {
                    i++;
                } while (i < 5);
            }

            // Used by reflection feature
            private void reflectionFeature(TargetClass.MemberInnerInTarget targetParam) {
                try {
                    Method method = NestedMemberInnerClass.class.getDeclaredMethod(
                        "targetMethod", TargetClass.MemberInnerInTarget.class);
                    method.invoke(this, targetParam);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            // Requires try-catch feature
            private void requiresTryCatchFeature() {
                try {
                    Class.forName("refactoring.target.TargetClass");
                } catch (ClassNotFoundException e) { // Mandatory try-catch for Class.forName
                    e.printStackTrace();
                }
            }
        }
    }

    // Anonymous inner class feature (source feature)
    public void anonymousInnerFeature() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                MemberInnerClass inner = new MemberInnerClass();
                NestedMemberInnerClass nested = inner.new NestedMemberInnerClass();
                nested.targetMethod(new TargetClass().new MemberInnerInTarget());
            }
        };
        anonymousRunnable.run();
    }
}

// Target package (different from source)
package refactoring.target;

import refactoring.source.SourceClass;

// Target normal class (private modifier, member inner class target_feature)
class TargetClass { // Note: Top-level private class invalid in Java, adjusted to package-private per language spec
    // Member inner class target_feature (target_inner context)
    public class MemberInnerInTarget {
        String innerField = "targetInnerField";

        // Call method (target type, public modifier, array initialization pos, Object return)
        public Object callMethod() {
            // Array initialization pos requirement
            String[] arr = new String[]{"arg1", "arg2"};
            
            // Overloading target_feature
            SourceClass.MemberInnerClass.NestedMemberInnerClass sourceInner = 
                new SourceClass().new MemberInnerClass().new NestedMemberInnerClass();
            
            // ClassName.methodName(arguments) feature
            sourceInner.targetMethod(this); // Overload 1
            sourceInner.targetMethod(this, arr[0]); // Overload 2
            
            return arr;
        }
    }
}