package test;
protected class SourceClass extends ParentSource {static class FirstStaticNested {}static class SecondStaticNested {}
private TargetClass target = new TargetClass();
@Overridepublic final int methodToMove() throws IllegalArgumentException {switch (target.field) {case 3:TargetClass.MemberInner inner = new TargetClass.MemberInner();inner.value = target.field;target.callOverloaded(inner);break;default:if (target.field < 0) throw new IllegalArgumentException();}return target.field;}}
class ParentSource {public int methodToMove() {return 0;}}
public class TargetClass {public int field;
public class MemberInner {int value;}
protected void callOverloaded(MemberInner inner) {}protected void callOverloaded(int val) {}
@Overridepublic int methodToMove() {return 1;}}