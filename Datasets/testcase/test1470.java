package test.refactoring;
import java.sql.SQLException;
// Source class: normal, protected, same package, has static nested/member inner classprotected class SourceClass {private static String sourceStaticVar = "source_static";
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (parent of source_inner_rec)class SourceInnerClass {// Member inner class (source_inner_rec: method's original position)class SourceInnerRecClass {// Target method: static, Object, final, source_inner_rec position// per_condition: contains target parameter (TargetClass)public static final Object moveTargetMethod(TargetClass targetParam, int choice) throws SQLException {// VariableDeclarationStatement (public, pos: diff_package_others, target_feature: ClassName.field x1)public String declaredVar = TargetClass.TargetStaticNested.targetStaticField; // ClassName.field
// Variable callString var = sourceStaticVar + declaredVar;Object result = var;
// Expression statementtargetParam.targetField = var;
// Switch statementswitch (choice) {case 1:result = targetParam.targetField;break;case 2:// SQLException featurethrow new SQLException("Switch case 2 triggered exception");default:result = choice;}
// No new checked exception (only declares SQLException as required)return result;}
// Override violation: target has same-signature method with incompatible access/returnpublic static Object moveTargetMethod(TargetClass targetParam, String choice) {return null;}}}}
// Target class: normal, private, has static nested class (target_feature)private class TargetClass {// Target instance fieldString targetField = "target_field";
// Static nested class (target_feature)public static class TargetStaticNested {// Target static field (ClassName.field - referenced in VariableDeclarationStatement)public static String targetStaticField = "target_static_field";}
// Override violation: same method signature with non-final access (conflicts with source's final)public static Object moveTargetMethod(TargetClass targetParam, int choice) {return new Object();}}