package samepkg;
private class SourceClass {class MemberInner {abstract class RecursiveInner {synchronized abstract void abstractMethod(TargetClass targetParam);}
class ConcreteInner extends RecursiveInner {@Overridesynchronized void abstractMethod(TargetClass targetParam) {int num1 = 2;int num2 = 2;
Object obj = targetParam;TargetClass castedTarget = (TargetClass) obj;TargetClass.TargetLocalDependent dependent = castedTarget.new TargetLocalDependent();
SourceClass.this.useOuterThis();}}}
void useOuterThis() {}
{Runnable r = new Runnable() {@Overridepublic void run() {}};}}
public class TargetClass {void methodWithLocal() {class TargetLocalDependent {}new TargetLocalDependent();}}