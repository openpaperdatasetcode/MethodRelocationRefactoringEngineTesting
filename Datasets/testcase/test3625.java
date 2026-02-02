package test;
class ParentClass {protected String superField = "parentData";
synchronized void parentMethod(String arg1, String arg2, String arg3) {}}
private class SourceClass {class MemberInner {class NestedInner {int process(TargetClass target) {new SuperConstructorInvocation(target);
int expr1 = 10 + target.innerField;int expr2 = 20 * target.innerField;int expr3 = 30 - target.innerField;
variableCall(target.nestedInner);
try {throw new Exception(() -> target.parent.synchronizedMethod("a", "b", "c"));} catch (Exception e) {}
return expr1 + expr2 + expr3;}
private void variableCall(TargetClass.NestedInner inner) {inner.print();}}}
class SuperConstructorInvocation extends ParentClass {public SuperConstructorInvocation(TargetClass target) {super();this.superField = target.parent.superField;}}}
class TargetClass extends ParentClass {int innerField = 5;NestedInner nestedInner = new NestedInner();
TargetClass() {new Runnable() {public void run() {innerField = 10;}}.run();}
class NestedInner {void print() {}}
abstract class AbstractInfixHolder {abstract int computeInfix();}}
