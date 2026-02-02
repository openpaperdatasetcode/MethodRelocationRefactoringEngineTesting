package test;
sealed class SourceClass permitsits.SubSourceClass {private int outerPrivateField;
static class StaticNested {}
class MemberInner {class InnerRec {final int instanceMethod(TargetClass.StaticNested.InnerRec param) {if (param == null) {throw new NullPointerException();}
TargetClass.StaticNested.InnerRec inner = new TargetClass.StaticNested.InnerRec();variableCall();int val = SourceClass.this.outerPrivateField;
overloadedMethod();overloadedMethod(1);
return val;}
private void variableCall() {}
private void overloadedMethod() {}private void overloadedMethod(int num) {}}}}
final class SubSourceClass extends SourceClass {}
public class TargetClass {static class StaticNested {static class InnerRec {}}}