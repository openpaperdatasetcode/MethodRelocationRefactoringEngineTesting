package test;
abstract interface TargetInterface {int STATIC_FIELD = 1;
default TargetInterface createTarget() {class LocalInner implements TargetInterface {@Overridepublic TargetInterface createTarget() {return this;}}return new LocalInner();}}
interface SourceInterface {TargetInterface targetField = TargetInterface.STATIC_FIELD > 0 ? new TargetInterface() {} : null;
class MemberInner {public TargetInterface instanceMethod(int baseTypeParam) {if (baseTypeParam != 1) {throw new IllegalArgumentException("Base type param must be 1");}
TargetInterface target = instanceChainMethod().createTarget();int var = TargetInterface.STATIC_FIELD;
switch (var) {case 1:for (int i = 0; i < 3; i++) {if (i == 1) continue;System.out.println("Loop: " + i);}break;default:target = null;break;}return target;}
public TargetInterface instanceMethod(String strParam) {return instanceMethod(Integer.parseInt(strParam));}
private TargetInterface instanceChainMethod() {return SourceInterface.targetField;}}
static class StaticNested {public static TargetInterface getTarget() {return new TargetInterface() {};}}}