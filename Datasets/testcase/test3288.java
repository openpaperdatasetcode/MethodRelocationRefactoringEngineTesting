package test;
import java.io.IOException;
strictfp class SourceClass {static class FirstStaticNested {}static class SecondStaticNested {}
record SourceInnerRec<T extends CharSequence>(T data) { // With boundsfinal TargetClass varargsMethod(TargetClass... targetParams) throws IOException {class LocalDeclaredType {}LocalDeclaredType localType = new LocalDeclaredType();
// VariableDeclarationExpression (2 variables)TargetClass target1 = targetParams[0];TargetClass target2 = targetParams[1];
target1.doAction(); // Variable calltarget2.doAction(); // Variable callSystem.out.println(this.data); // Expression statement
OtherSamePackageClass.staticExpressionStatement(target1, target2, this); // Uses outer this
return target1;}}}
class TargetClass {private String field1 = "field1";private String field2 = "field2";private String field3 = "field3";
void doAction() {class TargetLocalInner {}new TargetLocalInner();}
String getField1() { return field1; }String getField2() { return field2; }String getField3() { return field3; }}
class OtherSamePackageClass {static <T extends CharSequence> void staticExpressionStatement(TargetClass t1, TargetClass t2, SourceClass.SourceInnerRec<T> rec) {// This.field (3 targets: t1.field1, t2.field2, t2.field3)System.out.println(t1.getField1() + t2.getField2() + t2.getField3());}}
class OtherClass {public static String callMethod(SourceClass.SourceInnerRec<String> rec, TargetClass target) throws IOException {if (target != null) {return rec.varargsMethod(target, new TargetClass()).getField1().toUpperCase(); // obj.m1().m2().m3()} else {return "Default";}}}