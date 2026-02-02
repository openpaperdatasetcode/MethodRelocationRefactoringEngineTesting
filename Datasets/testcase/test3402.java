package test;
import otherpkg.OtherPackageClass;
@RecordAnnotationrecord SourceRecord(String data) extends RecordParent {// Two static nested classes (source feature)public static class FirstStaticNested {}public static class SecondStaticNested {}
// Varargs method (default access modifier, void return type)void varargsMethod(PrivateTargetRecord... targetParams) {// Access outer private (record component is implicitly private)String privateVal = this.data;
int count = 0;// While statementwhile (count < 2) {for (PrivateTargetRecord target : targetParams) {// Expression statement + variable calltarget.memberInner.variableCall(privateVal);
// Public NullLiteral (numbers=2)public Object null1 = null;public String null2 = null;
// EmptyStatement (static, target_feature: obj.field x1, pos: diff_package_others)OtherPackageClass other = new OtherPackageClass();if (other.field == null) {;} // Empty statement}count++;}
// Override violation: target's member inner class method without @OverridePrivateTargetRecord.TargetMemberInner overrideInner = new PrivateTargetRecord("test").new TargetMemberInner() {public void variableCall(String data) {}};}}
// Parent class for source record's extends featureabstract class RecordParent {}
// Target record class (private modifier, with member inner class)private record PrivateTargetRecord(String value) {// Member inner class (target_feature)public class TargetMemberInner {public void variableCall(String data) {}}
public TargetMemberInner memberInner = new TargetMemberInner();}
// Diff package class (separate package)package otherpkg;
public class OtherPackageClass {public String field = "other_field";}
// Annotation for has_annotation feature@interface RecordAnnotation {}