package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
private class SourceClass<T, U> implements Iterable<T>, PermitsInterface {protected TargetClass<T, U> sourceMethod(TargetClass<T, U> targetParam, String labelParam) {LabeledLoop:for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue LabeledLoop;}super.field = i;targetParam.setList(this.createStringList(3));}
class LocalInner {public String getValue() {return labelParam;}}LocalInner inner = new LocalInner();String varCall = inner.getValue();
TargetClass<T, U> result = new TargetClass<>(varCall);result.setInnerField(inner);return result;}
public List<String> createStringList(int size) {List<String> list = new ArrayList<>();for (int i = 0; i < size; i++) {list.add("item" + i);}return list;}}
abstract class TargetClass<T, U> {protected int field;private List<String> list;private SourceClass<T, U>.LocalInner innerField;
public TargetClass(String param) {}
public void setList(List<String> list) {this.list = list;}
public void setInnerField(SourceClass<T, U>.LocalInner innerField) {this.innerField = innerField;}}
interface PermitsInterface {}
// Test classpublic class MoveMethodTest5470 {public static void main(String[] args) {SourceClass<String, Integer> source = new SourceClass<>();TargetClass<String, Integer> target = new TargetClass<>("test") {};TargetClass<String, Integer> result = source.sourceMethod(target, "label");}}