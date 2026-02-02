package test;
protected class SourceClass {static class StaticNested {}
void methodWithLocal() {class LocalInner {}}
class SourceInner {private int overloadingMethod(TargetClass.TargetInner.TargetRec innerRec) {try {super.toString();variableCall = innerRec.field;innerRec.instanceMethod();} catch (Exception e) {}return variableCall;}
private int overloadingMethod(String str) {return 0;}
int variableCall;}}
public class TargetClass {class TargetInner {class TargetRec {int field;void instanceMethod() {}}}
{new Runnable() {};}
public String targetMethod() {return "test";}
public class TargetInnerClass {public String useSuperMethod() {Runnable r = () -> super.targetMethod();return "";}}}