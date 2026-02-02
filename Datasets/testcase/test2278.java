package test;
interface ParentInterface {}
interface OtherClass {static void normalMethod() {}}
interface SourceInterface extends ParentInterface {class MemberInner {protected MemberInner(int num, String str) {}}
static class StaticNested {}
default Object overloadingMethod(TargetInterface target) {; // empty statement
TargetInterface.MemberInner[] arr = { new TargetInterface.MemberInner(2, "inner_class") };
Object ref = TargetInterface.MemberInner::new;
super.toString();variableCall = target.field;
OtherClass obj = new OtherClass() {{ OtherClass::normalMethod; }};
if (true) return null;return target;}
default Object overloadingMethod(int num) {return null;}
String variableCall = "";}
interface TargetInterface {class MemberInner {String field;public MemberInner(int num, String str) {}}}