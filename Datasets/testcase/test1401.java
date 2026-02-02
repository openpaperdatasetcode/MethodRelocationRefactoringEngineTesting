package test;
abstract class SourceClass<T> {class SourceInner { // source_innerprotected Object methodToMove(TargetClass<String> targetParam) { // contains target parameter (per_condition)// method types parameter is:none// type declaration statementclass LocalType {}LocalType local = new LocalType();
// variable callTargetClass.MemberInner targetInner = targetParam.new MemberInner();targetInner.doAction();
return new Object();}}}
protected class TargetClass {
// target_feature: member inner class
class MemberInner {
void doAction() {}
}
}