package test.refactoring;
import java.util.ArrayList;import java.util.Collection;import java.util.List;import java.io.IOException;
abstract class EnumBase {}interface EnumInterface {}
protected enum SourceEnum extends EnumBase implements EnumInterface {INSTANCE1, INSTANCE2;
private String outerPrivate = "sourcePrivateData";private int innerVar;
class FirstInner {private List<String> overloadMethod(TargetEnum.TargetInner targetInner) {List<String> list = new ArrayList<>();list.add(targetInner.getData());return list;}}
class SecondInner {void useRecursion(TargetEnum.TargetInner targetInner) throws IOException {INSTANCE1.recursiveMethod(targetInner, 3);}}
{FirstInner firstInner = new FirstInner();TargetEnum.TargetInner targetInner = TargetEnum.VALUE1.new TargetInner();List rawList = firstInner.overloadMethod(targetInner);}
public void recursiveMethod(TargetEnum.TargetInner targetInner, int depth) throws IOException {if (depth <= 0) return;
this.innerVar = depth;TargetEnum.TargetInner newInner = new TargetEnum.VALUE2().new TargetInner();
for (int i = 0; i < 5; i++) {if (i % 2 == 0) continue;variableCall(targetInner, i);}
newInner.setData(outerPrivate);Collection<TargetEnum.TargetInner> coll = List.of(targetInner, newInner);Object callResult = TargetEnum.VALUE1.callOverridden(coll);
recursiveMethod(newInner, depth - 1);}
private void variableCall(TargetEnum.TargetInner inner, int val) {inner.setCount(inner.getCount() + val);}}
public enum TargetEnum {VALUE1, VALUE2;
class TargetInner {private String data;private int count;
public String getData() { return data; }public void setData(String data) { this.data = data; }public int getCount() { return count; }public void setCount(int count) { this.count = count; }}
public Object callOverridden(Collection<TargetEnum.TargetInner> coll) {return new TargetInterface() {@Overridepublic Object process(Collection<TargetEnum.TargetInner> c) {return c.size();}}.process(coll);}
private interface TargetInterface {Object process(Collection<TargetEnum.TargetInner> c);}}