import java.util.ArrayList;import java.util.List;
record SourceClass(int id, TargetClass targetField) {static class StaticNested {record InnerRecord(int value) {public int process(TargetClass... targets) {class LocalInner {int getValue() {return 5;}}LocalInner local = new LocalInner();List rawList = new ArrayList();rawList.add(new TargetClass(10).inner.value);
int sum = 0;for (TargetClass t : targets) {sum += t.inner.getValue();sum += local.getValue();}
return new SourceClass(1, new TargetClass(20)).id() + sum;}}}}
protected record TargetClass(int num) {class MemberInner {int value = num;int getValue() {return value;}}MemberInner inner = new MemberInner();}
