package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {public static class StaticNested {public class SourceInner {private int count = 0;
synchronized void recursiveMethod(TargetClass target, int depth) {if (depth <= 0) return;
class LocalInner {void process() {target.processItems("item1");}}new LocalInner().process();
SourceInner inner = new SourceInner();inner.recursiveMethod(target, depth - 1);}}}
{class LocalClass {}}}
private abstract class TargetClass extends BaseClass {public void processItems(String... items) {super.processItems();List<String> list = new ArrayList<>();for (String item : items) {list.add(item);}}
void someMethod() {class TargetLocalInner {final List<String> callInTernary(TargetClass target) {return (target != null) ? target.superMethod(1) : new ArrayList<>();}}}}
abstract class BaseClass {public void processItems() {}public List<String> superMethod(int arg) {return new ArrayList<>();}}