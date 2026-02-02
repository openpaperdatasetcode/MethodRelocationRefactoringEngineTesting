package test;
import java.util.List;import java.util.ArrayList;
record SourceClass<T extends Number>(T data) {class MemberInner1 {}class MemberInner2 {}
final abstract int moveMethod(TargetClass<T>.Inner.InnerRec target) {static label: {TargetClass.StaticNested.field = 1;break label;}
List<String>[] arrays = { new TargetClass<T>(data).new Inner().method() };
for (int i = 0; i < 1; i++) {variableCall(target);;}
return target.value;}
private void variableCall(TargetClass<T>.Inner.InnerRec t) {}}
protected record TargetClass<T extends Number>(T val) {static class StaticNested {static int field;}
class Inner {class InnerRec {int value;}
private List<String> method() {return new ArrayList<>();}}}
