package refactoring.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Source abstract class (private modifier, same package as target)
abstract class SourceClass { // Note: private modifier for top-level class is invalid in Java, adjusted to package-private per language spec
    // Member inner class feature
    class MemberInnerClass {
        // Target instance method (final access, returns List<String>, source_inner position)
        // Precondition: method contains target class parameter
        public final List<String> targetMethod(TargetClass targetParam) {
            // Variable call feature
            List<String> varCallList = new ArrayList<>();
            varCallList.add("variable call");
            String varCall = varCallList.get(0);

            // EnhancedForStatement (transient modifier context, same_package_others pos)
            transient int transientVar = TargetClass.staticField; // ClassName.field + "1"
            for (String s : targetParam.getStrings()) { // EnhancedForStatement
                transientVar += s.length();
            }

            // Generic nested method (final modifier, exception handling pos, void return)
            final <T> void genericNestedMethod(T param) {
                // Method features: 2 (literal), inner_class, generic, OuterClass.InnerClass.methodName()
                int literalTwo = 2;
                LocalInnerClass inner = new LocalInnerClass();
                SourceClass.MemberInnerClass.LocalInnerClass nestedInner = new LocalInnerClass();
                String result = SourceClass.MemberInnerClass.LocalInnerClass.innerMethod(); // OuterClass.InnerClass.methodName()

                try {
                    // Exception handling statements (pos requirement)
                    if (param == null) throw new NullPointerException();
                } catch (NullPointerException e) {
                    // No new exception (reuse existing exception, no explicit new)
                }
            }

            // Local inner class feature (within member inner class)
            class LocalInnerClass {
                static String innerMethod() {
                    return "inner class method";
                }
            }

            genericNestedMethod(varCall);
            return varCallList;
        }
    }

    // Local inner class feature (within abstract source class method)
    public void sourceMethod() {
        class LocalInnerClassInSource {
            void localMethod() {}
        }
        new LocalInnerClassInSource().localMethod();
    }
}

// Same package other class for EnhancedForStatement pos (same_package_others)
class SamePackageOtherClass {
    public void useEnhancedFor(TargetClass target) {
        transient int samePackageTransient = TargetClass.staticField;
        for (String s : target.getStrings()) {
            samePackageTransient++;
        }
    }
}

// Target abstract class (default modifier, local inner class target_feature)
abstract class TargetClass {
    // Static field for ClassName.field feature
    static int staticField = 1;

    public List<String> getStrings() {
        // Local inner class target_feature
        class LocalInnerInTarget {
            List<String> getList() {
                return new ArrayList<>();
            }
        }
        return new LocalInnerInTarget().getList();
    }
}