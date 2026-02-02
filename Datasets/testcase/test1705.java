package test;
import otherpackage.OthersClass;
protected class SourceClass {static int staticField;
{new Runnable() {};new Thread() {};}
class MemberInner {class InnerRec {protected int varargsMethod(TargetClass... targets) {super.toString();variableCall();
// LabeledStatement in diff_package_others with static modifier and this.field accessOthersClass.Label: {static int val = targets[0].thisField;break OthersClass.Label;}
// ThisExpression with abstract modifier (2 instances)abstract class ThisExpr1 {void method() { SourceClass.this.variableCall(); }}abstract class ThisExpr2 {void method() { InnerRec.this.variableCall(); }}
// Depends on static fieldint fieldVal = SourceClass.staticField;
// Call static method in ternary operatorObject result = (targets.length > 0) ? new SourceClass().privateStaticMethod() : null;
return 0;}
private void variableCall() {}
private static Object privateStaticMethod() {return new Object();}}}}
protected class TargetClass {int thisField;
{new Runnable() {};}}
package otherpackage;
public class OthersClass {}