package test;
class ParentGenericClass<T> {protected <V> Object genericMethod1(T t, V v) {super.toString();return t;}
protected <V> Object genericMethod2(T t, V v) {super.toString();return v;}}
class TargetClass {class TargetMemberInner {void innerMethod () {}void innerMethod (String arg) {} }
TargetMemberInner memberInner = new TargetMemberInner();}
public class SourceClass extends ParentGenericClass<String> {class SourceMemberInner {void innerMethod() {}}
public final void instanceMethod(TargetClass target) {new Runnable() {public void run() {SourceClass.this.toString();}}.run();
labeled: {boolean literal1 = true;boolean literal2 = false;
do {
Object result1 = super.genericMethod1 ("arg1", 123);Object result2 = super.genericMethod2 ("arg2", 45.6);
target.memberInner.innerMethod ();expressionStatement (target);
variableCall (target.memberInner);variableCall (target.memberInner, "overloadArg");
if (literal1 && !literal2) break labeled;} while (false);}}
private void variableCall(TargetClass.TargetMemberInner inner) {inner.innerMethod();}
private void variableCall(TargetClass.TargetMemberInner inner, String arg) {inner.innerMethod(arg);}
private void expressionStatement(TargetClass target) {target.memberInner.toString();}}