package test;
public record SourceRecord(String data) {class MemberInner {class InnerRecursive {protected void methodToMove(TargetRecord target) {labeledBlock: {// TypeDeclarationStatement with public modifier (target_feature: this.field, 3)public class TypeDecl1 {int val = target.new Inner().this.field1;}public class TypeDecl2 {int val = target.new Inner().this.field2;}public class TypeDecl3 {int val = target.new Inner().this.field3;}
// MethodInvocation expressions (numbers: 2)target.new Inner().method1();target.new Inner().method2();
// Variable callTargetRecord.Inner inner = target.new Inner();inner.variableCall();
if (inner.field1 == 0) {break labeledBlock;}
return;}}
protected void methodToMove(TargetRecord target, int param) {}}}
Runnable anonymous1 = new Runnable() { public void run() {} };Runnable anonymous2 = new Runnable() { public void run() {} };}
record TargetRecord(int id) {class Inner {int field1;int field2;int field3;
void variableCall() {}void method1() {}void method2() {}
Runnable anonymous = new Runnable() { public void run() {} };}}