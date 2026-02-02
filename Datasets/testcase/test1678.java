package test;
public enum SourceEnum {INSTANCE;
{new Runnable() {};new Thread() {};}
class MemberInner {class InnerRec {int instanceMethod(TargetEnum.Inner param) {TargetEnum.Inner inner = new TargetEnum.Inner();variableCall();SourceEnum.this.toString();
switch (param) {case VALUE:break;default:break;}
int i = 0;while (i < 5) {i = TargetEnum.Inner.staticMethod();i++;}
return i;}
private void variableCall() {}}}}
protected enum TargetEnum {VALUE;
class Inner {static int staticMethod() {class LocalInner {}return 0;}}}