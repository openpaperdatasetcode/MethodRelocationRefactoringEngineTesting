package test;
interface MyInterface {}
protected class SourceClass implements MyInterface {private int outerPrivateField = 3;protected TargetClass targetField = new TargetClass();
class MemberInner1 {@Overrideprotected void moveMethod() {do {if (targetField.field == 3) {break;}} while (true);
GenericClass<String> genericObj = new GenericClass<>(super::moveMethod);variableCall();System.out.println(SourceClass.this.outerPrivateField);new TargetClass() {{super();}};MemberInner2 inner2 = new MemberInner2();inner2.helper();}}
class MemberInner2 {void helper() {}}
private void variableCall() {targetField.new AnonymousInner().action();}}
class TargetClass {int field = 3;
class TargetInnerRec {protected void moveMethod() {}}
void createAnonymous() {new Runnable() {@Overridepublic void run() {}};}
class AnonymousInner {void action() {}}}
class GenericClass<T> {GenericClass(Runnable runnable) {runnable.run();}}
class OthersClass {{SourceClass source = new SourceClass();SourceClass.MemberInner1 inner = source.new MemberInner1();inner.moveMethod();}
public int callMethod() {return new SourceClass().targetField.field;}}