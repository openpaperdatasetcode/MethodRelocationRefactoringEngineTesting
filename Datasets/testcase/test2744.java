package test.same;
public enum SourceEnum {INSTANCE;
class Inner {record InnerRec() {/**
Method Javadoc
*/
private int varargsMethod(int... values) {
TargetEnum.MemberInner inner = TargetEnum.INSTANCE.new MemberInner();
int sum = 0;
for (int v : values) {
sum += v;
if (sum > 10) {
break;
}
}
sum += inner.calculate();
return SourceEnum.this.ordinal() + sum;
}
}
}
void createLocal() {class LocalInner {}Runnable anon = new Runnable() {public void run() {}};}}
private enum TargetEnum {INSTANCE;
class MemberInner {int field;
int calculate() {return field;}}}