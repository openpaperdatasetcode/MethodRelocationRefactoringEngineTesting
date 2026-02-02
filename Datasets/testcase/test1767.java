package test;
interface Source {class MemberInner {int data;}
static class StaticNested {static int count = 0;}
default int process() {Target.MemberInner inner = new Target.MemberInner();Target.MemberInner.InnerRec rec = inner.new InnerRec();
int sum = Target.field1 + Target.field2 + Target.field3;StaticNested.count += sum;
return rec.getValue() + (sum * 2);}}
public interface Target {int field1 = 10;int field2 = 20;int field3 = 30;
class MemberInner {class InnerRec {int getValue() {return 5;}}}}