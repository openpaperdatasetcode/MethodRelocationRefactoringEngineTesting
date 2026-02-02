package refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {}

// Source enum class (public modifier, same package as target, local + member inner class)
public enum SourceEnum {
    INSTANCE;

    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";

    // Member inner class (for source_inner_rec method position)
    public class MemberInnerClass {
        // Nested member inner class (source_inner_rec)
        public class NestedMemberInnerClass {
            // Target varargs method (default access, List<String> return, source_inner_rec)
            // Precondition: method contains target enum parameter
            @TestAnnotation // has_annotation feature
            List<String> targetMethod(TargetEnum targetParam, String... varargs) {
                // Variable call feature
                List<String> varCallList = new ArrayList<>();
                for (String arg : varargs) {
                    varCallList.add(arg);
                }
                String varCall = varCallList.get(0);

                // Access_outer_private feature (access outer enum's private field)
                String outerPrivate = SourceEnum.this.outerPrivateField;

                // Type declaration statement feature
                class TypeDeclClass {
                    String field = outerPrivate + varCall;
                }
                TypeDeclClass typeDeclObj = new TypeDeclClass();

                // Constructor invocation feature
                TargetEnum.MemberInnerInTarget innerTarget = targetParam.new MemberInnerInTarget();

                // ThrowStatement (protected modifier, obj.field + 2, same_package_target pos)
                protected int throwField = innerTarget.targetField + 2; // obj.field + 2
                if (throwField < 0) {
                    throw new IllegalArgumentException(); // throw statement + no_new_exception (standard exception)
                }

                // No_new_exception (reuse existing exception, no explicit new custom exception)
                try {
                    if (targetParam == null) {
                        throw new NullPointerException(); // throw statement feature
                    }
                } catch (NullPointerException e) {
                    varCallList.add(e.getMessage());
                }

                varCallList.add(typeDeclObj.field);
                return varCallList;
            }
        }
    }

    // Local inner class feature (within enum method)
    public void sourceLocalInner() {
        class LocalInnerInSource {
            void innerMethod() {
                MemberInnerClass inner = new MemberInnerClass();
                NestedMemberInnerClass nested = inner.new NestedMemberInnerClass();
                nested.targetMethod(TargetEnum.VALUE, "test1", "test2");
            }
        }
        new LocalInnerInSource().innerMethod();
    }
}

// Same package target helper class for ThrowStatement pos (same_package_target)
class SamePackageTargetHelper {
    public void useThrowStatement(TargetEnum target) {
        SourceEnum.INSTANCE.new MemberInnerClass().new NestedMemberInnerClass()
            .targetMethod(target, "samePackageArg");
    }
}

// Target enum class (public modifier, member inner class target_feature)
public enum TargetEnum {
    VALUE;

    // Member inner class target_feature
    public class MemberInnerInTarget {
        int targetField = 10; // obj.field for ThrowStatement feature
    }
}