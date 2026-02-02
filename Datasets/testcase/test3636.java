package test;
public class SourceClass {private String outerPrivateField = "privateData";
class MemberInner {
void overloadMethod (AbstractTargetClass target) {}
void overloadMethod (AbstractTargetClass target, String arg) {}}
abstract AbstractTargetClass abstractMethod(AbstractTargetClass target);
class ConcreteInner extends SourceClass {@OverrideAbstractTargetClass abstractMethod (AbstractTargetClass target) {try {MemberInner inner = new MemberInner ();
inner.overloadMethod (target);inner.overloadMethod (target, "test");
System.out.println (SourceClass.this.outerPrivateField);
variableCall (target.memberInner);} catch (Exception e) {e.printStackTrace ();}return target;}
private void variableCall(AbstractTargetClass.MemberInner inner) {inner.innerMethod();}}}
abstract class AbstractTargetClass {class MemberInner {void innerMethod() {}}
MemberInner memberInner = new MemberInner();}