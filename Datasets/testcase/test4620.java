package test;
private class SourceClass {class InnerRec {static Object staticMethod(TargetClass.Inner innerParam) {;innerParam.action();int[] arr = { recursiveMethod(1) };
class LocalOne {}new LocalOne();class LocalTwo {}new LocalTwo();
return overloadMethod(innerParam);}
static Object overloadMethod(TargetClass.Inner param) {return null;}
static Object overloadMethod(int i) {return null;}
protected static TargetClass recursiveMethod(int count) {if (count <= 0) {return new TargetClass();}super.toString();return recursiveMethod(count - 1);}}}
public class TargetClass {class Inner {void action() {}}}
