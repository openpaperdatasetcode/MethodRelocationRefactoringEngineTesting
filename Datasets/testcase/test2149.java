package test;
abstract class SourceClass {class MemberInner {}
Runnable anonymous = new Runnable() {public void run() {}};
int methodToMove(TargetClass target) {TargetClass.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();
// Access instance fieldint value = innerRec.counter;
// Prefix expressions++innerRec.counter;--innerRec.otherField;
// Constructor invocation in inner classclass LocalWrapper {TargetClass.MemberInner.InnerRecursive wrapped;
LocalWrapper() {this.wrapped = new TargetClass.MemberInner.InnerRecursive(innerRec.super.superField1,innerRec.super.superField2);}}
// Variable callinnerRec.variableCall();
// Throw statementif (value < 0) {throw new IllegalArgumentException();}
return value;}}
class SuperTarget {int superField1;String superField2;}
class TargetClass {class MemberInner extends SuperTarget {class InnerRecursive {int counter;int otherField;
InnerRecursive(int f1, String f2) {super.superField1 = f1;super.superField2 = f2;}
void variableCall() {}}}}