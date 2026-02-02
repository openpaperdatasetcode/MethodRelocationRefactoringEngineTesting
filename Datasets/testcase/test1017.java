package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Sealed parent class for permits feature
sealed class SealedParent permits SourceClass.SourceMemberInner, SourceClass.LocalInnerPermit {}

// Source class: normal, public modifier, same package as target, features: type parameter, extends, permits, member inner class, local inner class
public class SourceClass<T extends CharSequence> extends SealedParent {
    // Per condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Private outer field for access_outer_private feature
    private int outerPrivateField = 1;
    // Static field for depends_on_static_field feature
    public static int staticField = 1;

    // Member inner class (source_inner - method position host, permits feature)
    public class SourceMemberInner extends SealedParent {
        // Abstract method to be refactored: abstract, return List<String>, default access, position source_inner
        abstract List<String> moveMethod();

        // Concrete implementation for abstract method features
        public class SourceConcreteInner extends SourceMemberInner {
            @Override
            List<String> moveMethod() {
                // Variable call feature
                int localVar = SourceClass.this.outerPrivateField; // access_outer_private feature
                List<String> resultList = new ArrayList<>();

                // TypeDeclarationStatement feature (type: TypeDeclarationStatement, modifier: public, target_feature: ClassName.field, 1, pos: source)
                public class TypeDeclClass {
                    int field = SourceClass.staticField; // ClassName.field, 1
                }
                TypeDeclClass typeDeclObj = new TypeDeclClass();
                localVar += typeDeclObj.field;

                // TypeLiteral feature (numbers: 1, modifier: abstract, exp: TypeLiteral)
                abstract class TypeLiteralClass {
                    List<String> literalField = new ArrayList<>() {{ add("1"); }};
                }
                TypeLiteralClass typeLiteralObj = new TypeLiteralClass() {};
                resultList.addAll(typeLiteralObj.literalField);

                // Expression statement feature
                String exprStmt = "Processed value: " + localVar;
                resultList.add(exprStmt);

                // this.methodName(arguments) feature
                this.helperMethod(resultList, localVar);

                // Depends on static field feature
                resultList.add("Static field value: " + SourceClass.staticField);

                // Use target field's static nested class
                resultList.addAll(TargetClass.TargetStaticNested.processData(targetField, localVar));

                // No new exception thrown (no_new_exception feature)
                return resultList;
            }

            // Helper method for this.methodName(arguments)
            private void helperMethod(List<String> list, int val) {
                list.add("Helper processed: " + val);
            }
        }
    }

    // Local inner class (source_class feature, permits feature)
    class LocalInnerPermit extends SealedParent {}
}

// Target class: normal, public modifier, same package, target_feature: static nested class
public class TargetClass {
    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static List<String> processData(TargetClass target, int value) {
            List<String> list = new ArrayList<>();
            list.add("Target static nested - value: " + value);
            return list;
        }
    }
}