package test;
class ParentClass {protected String superField1 = "super1";protected String superField2 = "super2";protected String superField3 = "super3";}
protected class TargetClass extends ParentClass {public void instanceMethod() {}
TargetClass() {new Runnable() {public void run() {superField1 = "updatedSuper1";}}.run();}}
class SourceClass {private TargetClass targetField;
public final void normalMethod(TargetClass target) {this.targetField = target;
class FirstLocalInner {void process() {target.instanceMethod();}}new FirstLocalInner().process();
class SecondLocalInner {void accessSuper() {private String var1 = target.superField1;private String var2 = target.superField2;private String var3 = target.superField3;}}new SecondLocalInner().accessSuper();
final String access1 = target.superField1;final String access2 = target.superField2;final String access3 = target.superField3;
int i = 0;while (i < 3) {expressionStatement(target);variableCall(target);this.normalMethodHelper();i++;}}
private void normalMethodHelper() {}
private void variableCall(TargetClass target) {target.instanceMethod();}
private void expressionStatement(TargetClass target) {target.superField1 = "exprUpdated";}}