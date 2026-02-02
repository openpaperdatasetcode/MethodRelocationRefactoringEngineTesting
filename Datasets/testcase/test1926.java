package test;
import java.util.ArrayList;import java.util.List;
sealed record SourceClass(String data) permits SubRecord {class MemberInner1 {TargetClass.Inner processInner(TargetClass target, int depth) {if (depth <= 0) {return target.new Inner();}return processInner(target, depth - 1);}}
class MemberInner2 {}
static {TargetClass sample = new TargetClass(new ArrayList<>());List<String> result = sample.new Inner().recursiveList(1);}
protected TargetClass method(TargetClass target) {TargetClass.Inner inner = new MemberInner1().processInner(target, 1);inner.addItem(data);
int i = 0;while (true) {if (i >= inner.getItems().size()) {break;}i++;}
return target;}}
record SubRecord(String data) extends SourceClass {SubRecord(String data) {super(data);}}
public record TargetClass(List<String> items) {class Inner {List<String> recursiveList(int count) {if (count == 0) {return new ArrayList<>();}List<String> list = recursiveList(count - 1);list.add("item" + count);return list;}
void addItem(String item) {items.add(item);}
List<String> getItems() {return items;}}}